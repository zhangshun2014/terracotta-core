/* 
 * The contents of this file are subject to the Terracotta Public License Version
 * 2.0 (the "License"); You may not use this file except in compliance with the
 * License. You may obtain a copy of the License at 
 *
 *      http://terracotta.org/legal/terracotta-public-license.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Covered Software is Terracotta Platform.
 *
 * The Initial Developer of the Covered Software is 
 *      Terracotta, Inc., a Software AG company
 */
package com.terracotta.toolkit.object;

import org.terracotta.toolkit.config.Configuration;

import com.terracotta.toolkit.config.ConfigChangeListener;

import java.io.Serializable;

/**
 * A <tt>ClusteredObject</tt> that itself contains one or more other <tt>ClusteredObject</tt>s and a common
 * configuration
 */
public interface ToolkitObjectStripe<C extends TCToolkitObject> extends TCToolkitObject, Iterable<C> {
  /**
   * Returns the configuration associated with this {@link ToolkitObjectStripe}
   */
  Configuration getConfiguration();

  /**
   * Change config parameter and propagate it to the L2.
   */
  void setConfigField(String key, Serializable value);

  void addConfigChangeListener(ConfigChangeListener listener);
}
