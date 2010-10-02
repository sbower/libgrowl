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

import java.io.File;

import javax.swing.ImageIcon;

import net.sf.libgrowl.internal.Icon;
import net.sf.libgrowl.internal.ResourceIcon;
import net.sf.libgrowl.internal.UrlIcon;

/**
 * This class encapsulates an application, that wants to send notifications to
 * Growl
 * 
 * @author Bananeweizen
 * 
 */
public class Application {

  private String mName;
  private Icon mIcon;

  /**
   * create a new application to be registered in Growl (with no icon)
   * 
   * @param applicationName
   *          application name that will be displayed to the user in the Growl
   *          settings dialog
   */
  public Application(final String applicationName) {
    this(applicationName, (String) null);
  }

  /**
   * create a new application to be registered in Growl
   * 
   * @param applicationName
   *          application name that will be displayed to the user in the Growl
   *          settings dialog
   * @param iconUrl
   *          URL or file path of the icon of this application<br>
   *          The icon will be used in the settings dialog of Growl and in the
   *          notification, if no other icon is specified for the notification
   *          type.
   */
  public Application(final String applicationName, final String iconUrl) {
    mName = applicationName;
    if (iconUrl != null) {
      mIcon = new UrlIcon(iconUrl);
    }
  }

  public Application(final String applicationName, final ImageIcon icon) {
    mName = applicationName;
    if (icon != null) {
      mIcon = new ResourceIcon(icon);
    }
  }
  
  public Application(final String applicationName, final File iconFile) {
    mName = applicationName;
    if (iconFile != null) {
      mIcon = new ResourceIcon(iconFile);
    }
  }

  /**
   * get the application name
   * 
   * @return application name
   */
  public String getName() {
    return mName;
  }

  /**
   * get the application icon
   * 
   * @return the application icon or <code>null</code>, if no icon was set for
   *         this application
   */
  public Icon getIcon() {
    return mIcon;
  }

  /**
   * set the icon to be shown in Growl<br>
   * Changing the icon after the application has been registered, requires a
   * repeated registration to see an effect.
   * 
   * @param iconUrl
   *          URL or file path of the icon of this application<br>
   *          The icon will be used in the settings dialog of Growl and in the
   *          notification, if no other icon is specified for the notification
   *          type.
   */
  public void setIcon(final String iconUrl) {
    if (iconUrl == null) {
      mIcon = null;
    } else {
      mIcon = new UrlIcon(iconUrl);
    }
  }

  public void setIcon(final ImageIcon icon) {
    if (icon == null) {
      mIcon = null;
    } else {
      mIcon = new ResourceIcon(icon);
    }
  }
}
