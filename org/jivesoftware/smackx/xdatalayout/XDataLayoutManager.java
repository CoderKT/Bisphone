package org.jivesoftware.smackx.xdatalayout;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout;

public class XDataLayoutManager {

    /* renamed from: org.jivesoftware.smackx.xdatalayout.XDataLayoutManager.1 */
    final class C11211 implements ConnectionCreationListener {
        C11211() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(DataLayout.NAMESPACE);
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C11211());
    }
}
