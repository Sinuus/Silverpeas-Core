/**
 * Copyright (C) 2000 - 2015 Silverpeas
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p>
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.util.logging.sys;

import org.silverpeas.util.logging.SilverLogger;
import org.silverpeas.util.logging.SilverLoggerFactory;

import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Map;

/**
 * Implementation of the {@code org.silverpeas.util.logging.LoggerFactory} interface to provide
 * a logger wrapping the default Java logger.
 * @author miguel
 */
public class SysLoggerFactory implements SilverLoggerFactory {

  private static Map<String, WeakLoggerReference> loggers = new Hashtable<>();

  /**
   * Get a {@code org.silverpeas.util.logging.Logger} instance for the specified namespace.
   * If a logger has already been created with the given namespace it is returned, otherwise a new
   * logger is manufactured.
   * </p>
   * This method should not be invoked directly. It is dedicated to be used by the
   * {@code org.silverpeas.util.logging.Logger#getLogger(String)} method.
   * @param namespace the hierarchical dot-separated namespace of the logger mapping the
   * hierachical relationships between the loggers from the root one.
   * @return a Silverpeas logger instance.
   */
  @Override
  public SilverLogger getLogger(final String namespace) {
    WeakLoggerReference weakRef = loggers.get(namespace);
    if (weakRef == null || weakRef.get() == null) {
      if (weakRef != null) {
        loggers.remove(weakRef.getNamespace());
      }

      weakRef = loggers.computeIfAbsent(namespace,
          n -> new WeakLoggerReference(new SysLogger(namespace)));
    }
    return weakRef.get();
  }

  private static class WeakLoggerReference extends WeakReference<SilverLogger> {

    private final String namespace;

    /**
     * Creates a new weak reference that refers to the given object.  The new
     * reference is not registered with any queue.
     * @param referent object the new weak reference will refer to
     */
    public WeakLoggerReference(final SilverLogger referent) {
      super(referent);
      this.namespace = referent.getNamespace();
    }

    public String getNamespace() {
      return namespace;
    }
  }

}