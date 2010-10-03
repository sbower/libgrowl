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

import net.sf.libgrowl.Application;
import net.sf.libgrowl.NotificationType;

public class RegisterMessage extends Message {

  public RegisterMessage(final Application application,
      final NotificationType[] notificationTypes, final String password) {
    super(IProtocol.MESSAGETYPE_REGISTER, password);

    // application name, required
    header(HEADER_APPLICATION_NAME, application.getName());

    // application icon, optional
    Icon icon = application.getIcon();
    if (icon != null) {
      icon.header(HEADER_APPLICATION_ICON, this);
    }

    // notification count, required
    header(HEADER_NOTIFICATIONS_COUNT, notificationTypes.length);

    // notification headers, separated by an empty line each
    for (NotificationType notificationType : notificationTypes) {
      lineBreak();
      // type, required
      header(HEADER_NOTIFICATION_NAME, notificationType.getType());
      // display name, optional
      header(HEADER_NOTIFICATION_DISPLAY_NAME, notificationType
          .getDisplayName());
      // enabled, optional
      header(HEADER_NOTIFICATION_ENABLED, notificationType.isEnabled());
      
      // icon, optional
      icon = notificationType.getIcon();
      if (icon != null) {
        icon.header(HEADER_NOTIFICATION_ICON, this);
      }
    }
  }

}
