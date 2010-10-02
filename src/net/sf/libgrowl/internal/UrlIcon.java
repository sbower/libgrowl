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


/**
 * icon created using an URL
 * 
 * @author Bananeweizen
 * 
 */
public class UrlIcon extends Icon {

  private String mUrl;

  public UrlIcon(final String iconUrl) {
    mUrl = iconUrl;
  }

  @Override
  public void header(final String headerName, final Message message) {
    message.header(headerName, mUrl);
  }

}
