/*
 * Copyright (C) 2000 - 2014 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception. You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.silverpeas.util.lang;

import org.silverpeas.util.ServiceProvider;

import java.util.Map;

/**
 * This wrapper interface permits to bootstrap different System mechanism according to the context
 * of execution.
 * @author Yohann Chastagnier
 */
public interface SystemWrapper {

  /**
   * Gets the wrapped {@link System} instance.
   * @return the instance of the System Wrapper.
   */
  public static SystemWrapper get() {
    return ServiceProvider.getService(SystemWrapper.class);
  }

  /**
   * Gets the value of a environment variable.
   * @param name the name of the variable.
   * @return the value of the requested environment variable.
   */
  String getenv(String name);

  /**
   * Gets all the environment variables.
   * @return the map of environment variables.
   */
  Map<String, String> getenv();
}