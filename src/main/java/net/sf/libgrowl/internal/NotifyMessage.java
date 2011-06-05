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

import net.sf.libgrowl.Notification;
import java.util.UUID;

public class NotifyMessage extends Message {

  public NotifyMessage(final Notification notification, final String password) {
    super(IProtocol.MESSAGETYPE_NOTIFY, password);

    // application name, required
    header(IProtocol.HEADER_APPLICATION_NAME, notification.getApplication()
        .getName());

    // notification name, required
    header(IProtocol.HEADER_NOTIFICATION_NAME, notification.getNotificationType()
        .getType());

    // id, optional
    final String id = notification.getId();
    if (id != null) {
      header(IProtocol.HEADER_NOTIFICATION_ID, id);
    }

    // title, required
    header(IProtocol.HEADER_NOTIFICATION_TITLE, notification.getTitle());

    // text, optional
    final String text = notification.getText();
    if (text != null) {
      header(IProtocol.HEADER_NOTIFICATION_TEXT, text);
    }

    // sticky, optional
    header(IProtocol.HEADER_NOTIFICATION_STICKY, notification.getSticky());

    // priority, optional
    header(IProtocol.HEADER_NOTIFICATION_PRIORITY, notification.getPriority());

    // icon, optional
    final Icon icon = notification.getIcon();
    if (icon != null) {
      icon.header(IProtocol.HEADER_NOTIFICATION_ICON, this);
    }
    
    final String callBackUrl = notification.getCallBackURL();
    if (callBackUrl != null) {
    	header(IProtocol.HEADER_NOTIFICATION_CALLBACK_TARGET, callBackUrl);
    }
    
    final String context = notification.getContext();
    final String contextType = notification.getContextType();
    if (context != null && 
    		contextType != null) {
    	header(IProtocol.HEADER_NOTIFICATION_CALLBACK_CONTEXT, context);
    	header(IProtocol.HEADER_NOTIFICATION_CALLBACK_CONTEXT_TYPE, contextType);
    	this.setCallBack(true);
    }
  }
}
