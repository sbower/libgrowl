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

/**
 * all priorities allowed for use in the {@link Notification} type.
 * 
 * @author Bananeweizen
 * 
 */
public interface IPriority {
  /**
   * highest priority
   */
  public static final int EMERGENCY = 2;
  /**
   * high priority
   */
  public static final int HIGH = 1;
  /**
   * default priority
   */
  public static final int NORMAL = 0;
  /**
   * low priority
   */
  public static final int MODERATE = -1;
  /**
   * lowest priority
   */
  public static final int VERYLOW = -2;
}
