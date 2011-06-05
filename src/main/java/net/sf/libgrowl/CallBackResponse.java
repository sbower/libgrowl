/*
 * Copyright Shawn bower
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

import net.sf.libgrowl.internal.GenericResponse;
import net.sf.libgrowl.internal.IProtocol;

public class CallBackResponse extends GenericResponse {
	private final String NOTIFICATION_CALLBACK_RESULT = "Notification-Callback-Result";
	private final String NOTIFICATION_CALLBACK_TIMESTAMP = "Notification-Callback-Timestamp";

	private String applicationName;
	private String notificationID;
	private String notificationCallbackResult;
	private String notificationCallbackTimeStamp;
	private String notificationCallbackContext;
	private String notificationCallbackContextType;
		
	public CallBackResponse(String responseString) {
		super(responseString);
		setNotificationID(getCustomHeaders().get(IProtocol.HEADER_NOTIFICATION_ID));
		setApplicationName(getCustomHeaders().get(IProtocol.HEADER_APPLICATION_NAME));
		setNotificationCallbackResult(getCustomHeaders().get(NOTIFICATION_CALLBACK_RESULT));
		setNotificationCallbackTimeStamp(getCustomHeaders().get(NOTIFICATION_CALLBACK_TIMESTAMP));
		setNotificationCallbackContext(getCustomHeaders().get(IProtocol.HEADER_NOTIFICATION_CALLBACK_CONTEXT));
		setNotificationCallbackContextType(getCustomHeaders().get(IProtocol.HEADER_NOTIFICATION_CALLBACK_CONTEXT_TYPE));
	}

	public String getNotificationID() {
		return notificationID;
	}

	private void setNotificationID(String notificationID) {
		this.notificationID = notificationID;
	}

	public String getApplicationName() {
		return applicationName;
	}

	private void setApplicationName(String applicationNmae) {
		this.applicationName = applicationNmae;
	}
	
	public String getNotificationCallbackResult() {
		return notificationCallbackResult;
	}

	private void setNotificationCallbackResult(String notificationCallbackResuslt) {
		this.notificationCallbackResult = notificationCallbackResuslt;
	}

	public String getNotificationCallbackTimeStamp() {
		return notificationCallbackTimeStamp;
	}

	private void setNotificationCallbackTimeStamp(
			String notificationCallbackTimeStamp) {
		this.notificationCallbackTimeStamp = notificationCallbackTimeStamp;
	}

	public String getNotificationCallbackContext() {
		return notificationCallbackContext;
	}

	private void setNotificationCallbackContext(String notificationCallbackContext) {
		this.notificationCallbackContext = notificationCallbackContext;
	}

	public String getNotificationCallbackContextType() {
		return notificationCallbackContextType;
	}

	private void setNotificationCallbackContextType(
			String notificationCallbackContextType) {
		this.notificationCallbackContextType = notificationCallbackContextType;
	}

}
