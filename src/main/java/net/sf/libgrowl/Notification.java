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

import net.sf.libgrowl.internal.IPriority;
import net.sf.libgrowl.internal.Icon;
import net.sf.libgrowl.internal.ResourceIcon;
import net.sf.libgrowl.internal.UrlIcon;

/**
 * notification to be displayed in Growl
 * 
 * @author Bananeweizen
 * 
 */
public class Notification {

  private Application mApplication;
  private NotificationType mNotificationType;
  private String mTitle;
  private String mText;
  private String mId;
  private Icon mIcon;
  private int mPriority = IPriority.NORMAL;
  private boolean mSticky = false;
  private String mUrl;
  private String mContext;
  private String mContextType;
  private boolean mSubscribe = false;

  /**
   * Create a new notification of the given notification type for the given
   * application. The notification has the given title and message. The icon
   * depends on the notification type.
   * 
   * @param application
   * @param notificationType
   * @param title
   * @param message
   * @param id
   *          A unique ID for the notification. If present, serves as a hint to
   *          the notification system that this notification should replace any
   *          existing on-screen notification with the same ID. This can be used
   *          to update an existing notification. The notification system may
   *          ignore this hint.
   */
  public Notification(final Application application,
      final NotificationType notificationType, final String title,
      final String message, final String id) {
    mApplication = application;
    mNotificationType = notificationType;
    mTitle = title;
    mText = message;
    mId = id;
  }

  /**
   * Create a new notification of the given notification type for the given
   * application. The notification has the given title and message. The icon
   * depends on the notification type.
   * 
   * @param application
   * @param notificationType
   * @param title
   * @param message
   */
  public Notification(final Application application,
      final NotificationType notificationType, final String title,
      final String message) {
    this(application, notificationType, title, message, null);
  }

  /**
   * get the application this notification was registered for
   * 
   * @return application
   */
  public Application getApplication() {
    return mApplication;
  }

  /**
   * get the notification type of the notification
   * 
   * @return notification type
   */
  public NotificationType getNotificationType() {
    return mNotificationType;
  }

  /**
   * get the ID of this notification
   * <p>
   * A unique ID for the notification. If present, serves as a hint to the
   * notification system that this notification should replace any existing
   * on-screen notification with the same ID. This can be used to update an
   * existing notification. The notification system may ignore this hint.
   * </p>
   * 
   * @return ID or <code>null</code>
   */
  public String getId() {
    return mId;
  }

  /**
   * get the notification's title
   * 
   * @return notification title
   */
  public String getTitle() {
    return mTitle;
  }

  /**
   * get the notification's text
   * 
   * @return notification text
   */
  public String getText() {
    return mText;
  }
  
  /**
   * set the icon to use for this notification. this is independent of the
   * application icon
   * 
   * @param iconUrl
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
  
  public void setIcon(final File iconFile) {
    if (iconFile == null) {
      mIcon = null;
    } else {
      mIcon = new ResourceIcon(iconFile);
    }
  }

  /**
   * get the icon for this notification
   * 
   * @return icon or <code>null</code>
   */
  public Icon getIcon() {
    return mIcon;
  }

  /**
   * The notification's text (default: "")
   * 
   * @param text
   */
  public void setText(final String text) {
    mText = text;
  }

  /**
   * set an ID for the notification
   * <p>
   * A unique ID for the notification. If present, serves as a hint to the
   * notification system that this notification should replace any existing
   * on-screen notification with the same ID. This can be used to update an
   * existing notification. The notification system may ignore this hint.
   * </p>
   * 
   * <p>
   * default value: none
   * </p>
   * 
   * @param id
   */
  public void setId(final String id) {
    mId = id;
  }

  /**
   * gets the notifications priority
   * 
   * @see IPriority
   * @return priority
   */
  public int getPriority() {
    return mPriority;
  }

  /**
   * set the priority of the notification
   * <p>
   * A higher number indicates a higher priority. This is a display hint for the
   * receiver which may be ignored. It must be one of the {@link IPriority}
   * values.
   * </p>
   * 
   * <p>
   * default value: {@link IPriority#NORMAL}
   * </p>
   * 
   * @param priority
   *          new priority
   */
  public void setPriority(final int priority) {
    mPriority = priority;
  }

  /**
   * Indicates if the notification should remain displayed until dismissed by
   * the user (default false).
   * 
   * @return sticky
   */
  public boolean getSticky() {
    return mSticky;
  }

  /**
   * Set if the notification should remain displayed until dismissed by the user
   * (default: non sticky).
   * 
   * @param sticky
   */
  public void setSticky(final boolean sticky) {
    mSticky = sticky;
  }
  
  /**
   * Indicates if the notification should remain displayed until dismissed by
   * the user (default false).
   * 
   * @return sticky
   */
  public String getCallBackURL() {
    return mUrl;
  }

  /**
   * Set if the notification should remain displayed until dismissed by the user
   * (default: non sticky).
   * 
   * @param sticky
   */
  public void setCallBackURL(final String url) {
    mUrl = url;
  }
  
  public void setSubscribe(boolean mSubscribe) {
	this.mSubscribe = mSubscribe;
  }

  public boolean isSubscribe() {
	return mSubscribe;
  }

  public String getContext() {
	return mContext;
  }

  public String getContextType() {
	return mContextType;
  }

  public void setCallBackSocket(String context, String contextType) {
	mContext = context;
	mContextType = contextType;
  }
  
}
