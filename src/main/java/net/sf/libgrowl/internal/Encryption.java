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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

      final StringBuilder hexString = new StringBuilder();
      for (int i = 0; i < result.length; i++) {
        hexString.append(Integer.toHexString(0xFF & result[i]));
      }
      // System.out.println("MD5: " + hexString.toString());
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }
}
