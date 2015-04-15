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
package com.tc.config.test.schema;

public class UpdateCheckConfigBuilder extends BaseConfigBuilder {

  public UpdateCheckConfigBuilder() {
    super(3, new String[] { "enabled", "period-days" });
  }

  public void setEnabled(boolean enabled) {
    setProperty("enabled", enabled);
  }

  public void setPeriodDays(int periodDays) {
    setProperty("period-days", periodDays);
  }

  @Override
  public String toString() {
    String out = "";

    out += openElement("update-check");
    out += element("enabled");
    out += element("period-days");
    out += closeElement("update-check");

    return out;
  }
}
