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

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

class Encryption {
	
  /**
   * get MD5 hash of input value
   * 
   * @param input
   * @return MD5 hash as byte array or <code>null</code>
   */
	
  public static String md5(final byte[] input) {
    MessageDigest md5;
    try {
      md5 = MessageDigest.getInstance("MD5");
      md5.reset();
      md5.update(input);
      final byte[] result = md5.digest();

      return buildHexString(result).toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * get key hash for password
   * GNTP Spec - http://www.growlforwindows.com/gfw/help/gntp.aspx
   * GNTp Key Checker - http://www.growlforwindows.com/gfw/help/keychecker.aspx
   * 
   * @param password
   * @return key hash as String <code>""</code>
   */
  
  public static String generateKeyHash(final String password) {
	  MessageDigest md5;
	  String keyhash  = "";
	  
	  byte [] passBytes = password.getBytes();
	  
	  Random r = new SecureRandom();
	  byte[] salt = new byte[14];
	  r.nextBytes(salt);

	  ByteBuffer bb = ByteBuffer.allocate( passBytes.length + salt.length);
	  bb.put(passBytes);
	  bb.put(salt);
	  
      try {
		md5 = MessageDigest.getInstance("MD5");
		md5.reset();
		md5.update(bb.array());
  
		final byte[] result = md5.digest();
		keyhash = md5(result);
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}

	  keyhash = keyhash + "." + buildHexString(salt);
	  return keyhash;

  }
  
  /*
   * build a hex string from a byte array
   * 
   * @param password
   * @return string if hexadecimal numbers
   */
  
  private static String buildHexString(final byte[] in) {
	  
	final String HEXES = "0123456789ABCDEF";

	final StringBuilder hex = new StringBuilder( 2 * in.length );
	for ( final byte b : in ) {
	  hex.append(HEXES.charAt((b & 0xF0) >> 4))
	     .append(HEXES.charAt((b & 0x0F)));
	}
	return hex.toString();
  }
  
}
