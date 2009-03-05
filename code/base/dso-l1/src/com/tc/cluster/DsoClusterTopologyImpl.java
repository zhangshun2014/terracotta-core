/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 */
package com.tc.cluster;

import com.tc.logging.TCLogger;
import com.tc.logging.TCLogging;
import com.tc.net.ClientID;
import com.tc.net.NodeID;
import com.tcclient.cluster.DsoNode;
import com.tcclient.cluster.DsoNodeImpl;
import com.tcclient.cluster.DsoNodeInternal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DsoClusterTopologyImpl implements DsoClusterTopology {
  private static final TCLogger                  LOGGER         = TCLogging.getLogger(DsoClusterTopologyImpl.class);

  private final Map<NodeID, DsoNodeInternal>     nodes          = new HashMap<NodeID, DsoNodeInternal>();

  private final ReentrantReadWriteLock           nodesLock      = new ReentrantReadWriteLock();
  private final ReentrantReadWriteLock.ReadLock  nodesReadLock  = nodesLock.readLock();
  private final ReentrantReadWriteLock.WriteLock nodesWriteLock = nodesLock.writeLock();

  public Collection<DsoNode> getNodes() {
    nodesReadLock.lock();
    try {
      // yucky cast hack for generics
      return (Collection) Collections.unmodifiableCollection(nodes.values());
    } finally {
      nodesReadLock.unlock();
    }
  }

  DsoNodeInternal getDsoNode(final NodeID nodeId) {
    nodesReadLock.lock();
    try {
      DsoNodeInternal node = nodes.get(nodeId);
      if (node != null) { return node; }
    } finally {
      nodesReadLock.unlock();
    }

    return registerDsoNode(nodeId);
  }

  DsoNodeInternal getAndRemoveDsoNode(final NodeID nodeId) {
    nodesWriteLock.lock();
    try {
      DsoNodeInternal node = nodes.remove(nodeId);
      // Disabling this assertion until cluster events properly support AA
      // Assert.assertNotNull(node);
      LOGGER.warn("there was no existing node for ID "+nodeId);
      return node;
    } finally {
      nodesWriteLock.unlock();
    }
  }

  DsoNodeInternal registerDsoNode(final NodeID nodeId) {
    final ClientID clientId = (ClientID) nodeId;
    final DsoNodeInternal node = new DsoNodeImpl(clientId.toString(), clientId.getChannelID().toLong());
    nodesWriteLock.lock();
    try {
      nodes.put(nodeId, node);
      return node;
    } finally {
      nodesWriteLock.unlock();
    }
  }
}