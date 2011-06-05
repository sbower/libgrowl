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
package net.sf.libgrowl;

import java.util.ArrayList;

import net.sf.libgrowl.internal.IProtocol;
import net.sf.libgrowl.internal.IResponse;
import net.sf.libgrowl.internal.Message;
import net.sf.libgrowl.internal.NotifyMessage;
import net.sf.libgrowl.internal.RegisterMessage;
import net.sf.libgrowl.internal.SubscribeMessage;

/**
 * GrowlConnector is the entry point for sending notifications to Growl. Typical
 * use looks like this:
 * <p>
 * <code>
 * // connect to Growl on the given host<br>
 * GrowlConnector growl = new GrowlConnector("hostname");<br>
 * <br>
 * // give your application a name and icon (optionally)<br>
 * Application downloadApp = new Application("Downloader", "http://example.com/icon.png");<br>
 * <br>
 * // create reusable notification types, their names are used in the Growl settings<br>
 * NotificationType downloadStarted = new NotificationType("Download started", "c:\started.png");<br>
 * NotificationType downloadFinished = new NotificationType("Download finished", "c:\finished.jpg");<br>
 * NotificationType[] notificationTypes = new NotificationType[] { downloadStarted, downloadFinished };<br>
 * <br>
 * // now register the application in growl<br>
 * growl.register(downloadApp, notificationTypes);<br>
 * <br>
 * // create a notification with specific title and message<br> 
 * Notification ubuntuDownload = new Notification(downloadApp, downloadStarted, "Ubuntu 9.4", "654 MB");<br>
 * <br>
 * // finally send the notification<br>  
 * growl.notify(ubuntuDownload);<br>
 * </code>
 * </p>
 * 
 * @author Bananeweizen, sbower
 * 
 */
public class GrowlConnector {

  private String mHost;
  private int mPort;
  private ArrayList<NotificationType> mRegisteredNotifications = new ArrayList<NotificationType>();
  private String mPassword = "";
  private IResponse lastResponse;
  
  /**
   * create a growl connection to localhost, port 23053
   */
  public GrowlConnector() {
    this("localhost");
  }

  /**
   * create a growl connection to the given host, port 23053
   * 
   * @param host
   *          host name
   */
  public GrowlConnector(final String host) {
    this(host, IProtocol.DEFAULT_GROWL_PORT);
  }

  /**
   * create a growl connection to the given host on the given port number
   * 
   * @param host
   *          host name
   * @param port
   *          port number
   */
  public GrowlConnector(final String host, final int port) {
    mHost = host;
    mPort = port;
  }

  /**
   * registers your application with Growl
   * <p>
   * Only after registering an application, it can send notifications. You can
   * re-register your application as often as you want (e.g. during every
   * program start), Growl will be able to handle this.
   * </p>
   * 
   * @param application
   *          your application
   * @param notificationTypes
   *          all notification types supported by your application
   * @return response, see {@link IResponse}
   */
 
  public final int register(final Application application,
      final NotificationType[] notificationTypes) {
    final Message message = new RegisterMessage(application, notificationTypes, mPassword);
    final int result = message.send(mHost, mPort);
    if (result == IResponse.OK) {
      setNotificationsRegistered(notificationTypes);
    }
    setLastResponse(message.getResponse());
    return result;
  }
  /**
   * subscribes your application with Growl
   * <p>
   * This will allow you to subscribe to all growls sent
   * to the growl machine the message is sent to.
   * </p>
   * @return response, see {@link IResponse}
   */
 
  public final int subsribe() {
    final Message message = new SubscribeMessage(mPassword);
    final int result = message.send(mHost, mPort);
    setLastResponse(message.getResponse());
    return result;
  }
  /**
   * remember the registered notification types internally to detect wrong usage
   * of the {@link GrowlConnector#notify(Notification)} method
   * 
   * @param notificationTypes
   */
  public void setPassword(String password) {
	  this.mPassword = password;
  }
  
  /**
   * remember the registered notification types internally to detect wrong usage
   * of the {@link GrowlConnector#notify(Notification)} method
   * 
   * @param notificationTypes
   */
  private void setNotificationsRegistered(
      final NotificationType[] notificationTypes) {
    for (NotificationType notificationType : notificationTypes) {
      mRegisteredNotifications.add(notificationType);
    }
  }

  /**
   * sends a notification to Growl
   * <p>
   * Your application must have been registered first, see
   * {@link #register(Application, NotificationType[])}
   * </p>
   * 
   * @param notification
   *          notification to send to Growl
   * @return response, see {@link IResponse}
   */
  public final int notify(final Notification notification) {
    if (!isRegistered(notification.getNotificationType())) {
      System.err.println("You need to register the notification type "
          + notification.getNotificationType().getDisplayName()
          + " before using it in notifications.");
    }
    final Message message = new NotifyMessage(notification, mPassword);
    final int result = message.send(mHost, mPort);
    setLastResponse(message.getResponse());
    return result;
  }
 
  private boolean isRegistered(final NotificationType notificationType) {
    return mRegisteredNotifications.contains(notificationType);
  }

  private void setLastResponse(IResponse lastResponse) {
	this.lastResponse = lastResponse;
  }

  public IResponse getLastResponse() {
	return lastResponse;
  }

/**
   * command line interface to send a message to Growl. You can use this like<br>
   * <code>
   * java -jar libgrowl.jar "host" "Application name" "Notification type" "Notification title" "Notification text"
   * </code>
   * 
   * @param args
   *          array of arguments, must be in this order:
   *          <ul>
   *          <li>host name where Growl is running</li>
   *          <li>application name to be displayed in Growl settings</li>
   *          <li>notification type name to be displayed in Growl settings</li>
   *          <li>notification title</li>
   *          <li>notification text</li>
   *          </ul>
   */
  public static void main(final String[] args) {
    final String host = args[0];
    final String appName = args[1];
    final String notificationName = args[2];
    final String title = args[3];
    final String message = args[4];
    final GrowlConnector growl = new GrowlConnector(host);
    final Application application = new Application(appName);
    final NotificationType notificationType = new NotificationType(
        notificationName);
    final NotificationType[] notificationTypes = new NotificationType[] { notificationType };
    growl.register(application, notificationTypes);
    final Notification notification = new Notification(application,
        notificationType,
        title, message);
    growl.notify(notification);
  }
}