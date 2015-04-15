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
package com.terracotta.management.service;

import org.terracotta.management.ServiceExecutionException;

import com.terracotta.management.resource.OperatorEventEntity;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * An interface for service implementations providing TSA operator events querying facilities.
 * <p/>
 * The timestamp string describes a time since <i>now</i>.
 * The grammar for the timestamp string is as follows:
 * <pre>&lt;numeric value&gt;&lt;unit&gt;</pre>
 * Unit must be in this list:
 * <ul>
 * <li><b>d</b> for days</li>
 * <li><b>h</b> for hours</li>
 * <li><b>m</b> for minutes</li>
 * <li><b>s</b> for seconds</li>
 * </ul>
 * <p/>
 * For instance, these strings are valid:
 * <ul>
 * <li><b>2d</b> means in the last 2 days</li>
 * <li><b>24h</b> means in the last 24 hours</li>
 * <li><b>1m</b> means in the last minute</li>
 * <li><b>10s</b> means in the last 10 seconds</li>
 * </ul>
 *
 * @author Ludovic Orban
 */
public interface OperatorEventsService {

  /**
   * Get the operator events of the specified servers
   * @param serverNames A set of server names, null meaning all of them.
   * @param sinceWhen A string describing a timestamp that will be parsed to filter out events that happened before or at that time, null meaning no such filtering.
   * @param eventTypes A string describing comma-separated event types to filter out events of different types, null meaning no such filtering.
   * @param eventLevels A string describing comma-separated event levels to filter out events of different log levels, null meaning no such filtering.
   * @param filterOutRead true if the read operator events should be filter out, false otherwise.
   * @return a collection of operator events
   * @throws org.terracotta.management.ServiceExecutionException
   */
  Collection<OperatorEventEntity> getOperatorEvents(Set<String> serverNames, String sinceWhen, String eventTypes, String eventLevels, boolean filterOutRead) throws ServiceExecutionException;

  /**
   * Mark operator events as read or unread.
   * @param operatorEventEntities the operator events to mark.
   * @param read true if the operator even should be marked as read, false otherwise.
   * @return true if the event was found and marked, false otherwise.
   * @throws ServiceExecutionException
   */
  boolean markOperatorEvents(Collection<OperatorEventEntity> operatorEventEntities, boolean read) throws ServiceExecutionException;

  /**
   * Returns the number of unread operator events.
   * @throws ServiceExecutionException
   * @param serverNames A set of server names, null meaning all of them.
   */
  Map<String, Integer> getUnreadCount(Set<String> serverNames) throws ServiceExecutionException;
}
