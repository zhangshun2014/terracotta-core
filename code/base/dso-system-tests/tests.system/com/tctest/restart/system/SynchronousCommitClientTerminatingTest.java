/*
 * All content copyright (c) 2003-2007 Terracotta, Inc., except as may otherwise be noted in a separate copyright
 * notice. All rights reserved.
 */
package com.tctest.restart.system;

import com.tctest.TransparentTestIface;

public class SynchronousCommitClientTerminatingTest extends ClientTerminatingTest {

  public SynchronousCommitClientTerminatingTest() {
    super();
    super.setSynchronousWrite();
    this.disableAllUntil("2007-05-15");
  }

  public void doSetup(TransparentTestIface t) throws Exception {
    t.getTransparentAppConfig().setAttribute(ClientTerminatingTestApp.FORCE_KILL, "true");
  }

}
