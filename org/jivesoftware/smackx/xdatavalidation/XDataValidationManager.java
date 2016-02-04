package org.jivesoftware.smackx.xdatavalidation;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement;

public class XDataValidationManager {

    /* renamed from: org.jivesoftware.smackx.xdatavalidation.XDataValidationManager.1 */
    final class C11221 implements ConnectionCreationListener {
        C11221() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(ValidateElement.NAMESPACE);
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C11221());
    }
}
