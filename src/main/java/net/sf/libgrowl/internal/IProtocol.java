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
 * @author Bananeweizen
 * 
 */
public interface IProtocol {

  /**
   * no encryption
   */
  static final String ENCRYPTION_NONE = "NONE";
  /**
   * md5 kasy hash
   */
  static final String KEY_HASH_MD5 = "MD5";
  /**
   * GNTP version
   */
  static final String GNTP_VERSION = "GNTP/1.0";
  /**
   * Optional - The icon of the application
   */
  static final String HEADER_APPLICATION_ICON = "Application-Icon";
  /**
   * Required - The name of the application that is registering
   */
  static final String HEADER_APPLICATION_NAME = "Application-Name";
  /**
   * Optional - The name of the notification that is displayed to the user
   * (defaults to the same value as Notification-Name)
   */
  static final String HEADER_NOTIFICATION_DISPLAY_NAME = "Notification-Display-Name";
  /**
   * Optional - Indicates if the notification should be enabled by default
   * (defaults to False)
   */
  static final String HEADER_NOTIFICATION_ENABLED = "Notification-Enabled";
  /**
   * Optional - The icon to display with the notification.
   */
  static final String HEADER_NOTIFICATION_ICON = "Notification-Icon";
  /**
   * Optional - A unique ID for the notification. If present, serves as a hint
   * to the notification system that this notification should replace any
   * existing on-screen notification with the same ID. This can be used to
   * update an existing notification. The notification system may ignore this
   * hint.
   */
  static final String HEADER_NOTIFICATION_ID = "Notification-ID";
  /**
   * Required - The name (type) of the notification being registered
   */
  static final String HEADER_NOTIFICATION_NAME = "Notification-Name";
  /**
   * Optional - A higher number indicates a higher priority. This is a display
   * hint for the receiver which may be ignored. (valid values are between -2
   * and 2, defaults to 0)
   * 
   * @see IPriority
   */
  static final String HEADER_NOTIFICATION_PRIORITY = "Notification-Priority";
  /**
   * Optional - Indicates if the notification should remain displayed until
   * dismissed by the user. (default to False)
   */
  static final String HEADER_NOTIFICATION_STICKY = "Notification-Sticky";
  /**
   * Optional - The notification's text. (defaults to "")
   */
  static final String HEADER_NOTIFICATION_TEXT = "Notification-Text";
  /**
   * Required - The notification's title
   */
  static final String HEADER_NOTIFICATION_TITLE = "Notification-Title";
  /**
   * Required - The number of notifications being registered
   */
  static final String HEADER_NOTIFICATIONS_COUNT = "Notifications-Count";
  /**
   * Optional - The machine name/host name of the sending computer
   */
  static final String HEADER_ORIGIN_MACHINE_NAME = "Origin-Machine-Name";
  /**
   * Optional - The identify of the sending computer OS/platform. Example: Mac
   * OS X
   */
  static final String HEADER_ORIGIN_PLATFORM_NAME = "Origin-Platform-Name";
  /**
   * Optional - The version of the the sending computer OS/platform. Example:
   * 10.6
   */
  static final String HEADER_ORIGIN_PLATFORM_VERSION = "Origin-Platform-Version";
  /**
   * Optional - The identity of the sending framework. Example:
   * GrowlAIRConnector
   */
  static final String HEADER_ORIGIN_SOFTWARE_NAME = "Origin-Software-Name";
  /**
   * Optional - The version of the sending framework. Example: 1.2
   */
  static final String HEADER_ORIGIN_SOFTWARE_VERSION = "Origin-Software-Version";
  /**
   * line break for protocol lines
   */
  static final String LINE_BREAK = "\r\n";
  /**
   * message type NOTIFICATION
   */
  static final String MESSAGETYPE_NOTIFY = "NOTIFY";
  /**
   * message type REGISTER
   */
  static final String MESSAGETYPE_REGISTER = "REGISTER";
  /**
   * message type SUBSCRIBE
   */
  static final String MESSAGETYPE_SUBSCRIBE = "SUBSCRIBE";
  /**
   * message type OK
   */
  static final String MESSAGETYPE_OK = "-OK";
  /**
   * message type ERROR
   */
  static final String MESSAGETYPE_ERROR = "-ERROR";
  /**
   * message type CALLBACK
   */
  static final String MESSAGETYPE_CALLBACK = "-CALLBACK ";
  /**
   * Optional - Come on a response denotes the type of action
   */
  static final String HEADER_RESPONSE_ACTION = "Response-Action";
  /**
   * Required - A unique id (UUID) that identifies the subscriber
   */
  static final String HEADER_SUBSCRIBER_ID = "Subscriber-ID";
  /**
   * Required - The friendly name of the subscribing machine
   */
  static final String HEADER_SUBSCRIBER_NAME = "Subscriber-Name";
  /**
   * Optional - The port that the subscriber will listen for notifications on 
   * (defaults to the standard 23053)
   */
  static final String HEADER_SUBSCRIBER_PORT = "Subscriber-Port";
  /**
   * Optional - Any data (will be passed back in the callback unmodified)
   */
  static final String HEADER_NOTIFICATION_CALLBACK_ID = "Notification-Callback-ID";
  /**
   * Optional - Any data (will be passed back in the callback unmodified)
   */
  static final String HEADER_NOTIFICATION_CALLBACK_CONTEXT = "Notification-Callback-Context";
  /**
   * Optional, but Required if 'Notification-Callback-Context' is passed This does not need to be of any pre-defined type, 
   * it is only a convenience to the sending application.
   */
  static final String HEADER_NOTIFICATION_CALLBACK_CONTEXT_TYPE = "Notification-Callback-Context-Type";
  /**
   * Optional - An alternate target for callbacks from this notification. If passed, the standard behavior of 
   * performing the callback over the original socket will be ignored and the callback data will be passed to 
   * this target instead. 
   */
  static final String HEADER_NOTIFICATION_CALLBACK_TARGET = "Notification-Callback-Target";
  /**
   * default port for communication with Growl
   */
  static final int DEFAULT_GROWL_PORT = 23053;
  /**
   * binary data identifier
   */
  static final String X_GROWL_RESOURCE = "x-growl-resource://";
  /**
   * identifier of following resource
   */
  static final String HEADER_IDENTIFIER = "Identifier";
  /**
   * byte length of following resource
   */
  static final String HEADER_LENGTH = "Length";

}
