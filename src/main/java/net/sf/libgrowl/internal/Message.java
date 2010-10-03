/*
 * Copyright Michael Keppler
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.libgrowl.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import net.sf.libgrowl.IResponse;

public abstract class Message implements IProtocol {

  private StringBuilder mBuffer;

  /**
   * container for all resources which need to be sent to Growl
   */
  private HashMap<String, byte[]> mResources = new HashMap<String, byte[]>();

  /**
   * name of the sending machine
   */
  private static String MACHINE_NAME;

  static {
    try {
      MACHINE_NAME = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * platform version of the sending machine
   */
  private static String PLATFORM_VERSION = System.getProperty("os.version");

  /**
   * platform of the sending machine
   */
  private static String PLATFORM_NAME = System.getProperty("os.name");

  protected Message(final String messageType, String password) {
    mBuffer = new StringBuilder();
    
    if (password.equals("")) {
        mBuffer.append(IProtocol.GNTP_VERSION).append(' ').append(messageType)
        .append(' ').append(IProtocol.ENCRYPTION_NONE).append(IProtocol.LINE_BREAK);
    } else {
        mBuffer.append(IProtocol.GNTP_VERSION).append(' ').append(messageType)
        .append(' ').append(IProtocol.ENCRYPTION_NONE).append(' ')
        .append(IProtocol.KEY_HASH_MD5).append(':').append(Encryption.generateKeyHash(password))
        .append(IProtocol.LINE_BREAK);
    }

    header(HEADER_ORIGIN_MACHINE_NAME, MACHINE_NAME);
    header(HEADER_ORIGIN_SOFTWARE_NAME, "libgrowl");
    header(HEADER_ORIGIN_SOFTWARE_VERSION, "0.1");
    header(HEADER_ORIGIN_PLATFORM_NAME, PLATFORM_NAME);
    header(HEADER_ORIGIN_PLATFORM_VERSION, PLATFORM_VERSION);
  }

  protected void header(final String headerName,
      final boolean value) {
    header(headerName, value ? "True" : "False");
  }

  protected void header(final String headerName,
      final String value) {
    // filter out any \r\n in the header values
    mBuffer.append(headerName).append(": ").append(
        value.replaceAll(IProtocol.LINE_BREAK, "\n")).append(
        IProtocol.LINE_BREAK);
  }

  protected void header(final String headerName,
      final int value) {
    header(headerName, String.valueOf(value));
  }

  protected void lineBreak() {
    mBuffer.append(IProtocol.LINE_BREAK);
  }

  public int send(final String host, int port) {
    String response = null;
    try {
      writeResources();
      // always have a line break and an empty line at the message end
      String messageText = mBuffer.toString();
      while (!messageText.endsWith(IProtocol.LINE_BREAK + IProtocol.LINE_BREAK)) {
        messageText = messageText + IProtocol.LINE_BREAK;
      }
      // now start the communication
      final Socket socket = new Socket(host, port);
      socket.setSoTimeout(10000);
      final BufferedReader in = new BufferedReader(new InputStreamReader(socket
          .getInputStream()));
      final OutputStreamWriter out = new OutputStreamWriter(socket
          .getOutputStream(), "UTF-8");
      final PrintWriter writer = new PrintWriter(out);
      writer.write(messageText);
      writer.flush();
      System.out.println("------------------------");
      System.out.println(messageText);

      final StringBuilder buffer = new StringBuilder();
      String line = in.readLine();
      while (line != null && !line.isEmpty()) {
        buffer.append(line).append(IProtocol.LINE_BREAK);
        line = in.readLine();
      }
      response = buffer.toString();
      writer.close();
      out.close();
      in.close();
      socket.close();
      System.out.println("------------------------");
      System.out.println(response);
    } catch (UnknownHostException e) {
//      e.printStackTrace();
      return IResponse.ERROR;
    } catch (IOException e) {
//      e.printStackTrace();
      return IResponse.ERROR;
    }
    return getError(response);
  }

  /**
   * write the collected resources to the output stream
   */
  private void writeResources() {
    for (Map.Entry<String, byte[]> entry : mResources.entrySet()) {
      lineBreak();
      final String id = entry.getKey();
      byte[] data = entry.getValue();
      if (data == null) {
        data = new byte[0];
      }
      header(IProtocol.HEADER_IDENTIFIER, id);
      header(IProtocol.HEADER_LENGTH, data.length);
      lineBreak();
      try {
        mBuffer.append(new String(data, "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      /*
       * for (byte b : data) { mBuffer.append((char) b); }
       */
      lineBreak();
    }
  }

  private int getError(final String response) {
    if (response == null) {
      return IResponse.ERROR;
    }
    if (response.contains("-OK")) {
      return IResponse.OK;
    }
    return IResponse.ERROR;
  }

  /**
   * add a resource to the internally remembered resources, which are
   * automatically added to the end of the message
   *
   * @param resourceId
   * @param data
   */
  protected void addResourceInternal(final String resourceId, final byte[] data) {
    mResources.put(resourceId, data);
  }
}
