package net.sf.libgrowl;

import net.sf.libgrowl.internal.GenericResponse;

public class NotificationResponse extends GenericResponse {
	private final String ORIGIN_SOFTWARE_NAME = "Notification-ID";
		
	private String notificationID;
	
	public NotificationResponse(String responseString) {
		super(responseString);	
		setNotificationID(getCustomHeaders().get(ORIGIN_SOFTWARE_NAME));
	}
	
	private void setNotificationID(String notificationID) {
		this.notificationID = notificationID;
	}

	public String getNotificationID() {
		return notificationID;
	}
	
}
