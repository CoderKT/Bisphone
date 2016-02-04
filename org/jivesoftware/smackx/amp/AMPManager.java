package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smackx.amp.packet.AMPExtension;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

public class AMPManager {

    /* renamed from: org.jivesoftware.smackx.amp.AMPManager.1 */
    final class C10341 implements ConnectionCreationListener {
        C10341() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            AMPManager.setServiceEnabled(xMPPConnection, true);
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C10341());
    }

    public static synchronized void setServiceEnabled(XMPPConnection xMPPConnection, boolean z) {
        synchronized (AMPManager.class) {
            if (isServiceEnabled(xMPPConnection) != z) {
                if (z) {
                    ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(AMPExtension.NAMESPACE);
                } else {
                    ServiceDiscoveryManager.getInstanceFor(xMPPConnection).removeFeature(AMPExtension.NAMESPACE);
                }
            }
        }
    }

    public static boolean isServiceEnabled(XMPPConnection xMPPConnection) {
        xMPPConnection.getServiceName();
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).includesFeature(AMPExtension.NAMESPACE);
    }

    public static boolean isActionSupported(XMPPConnection xMPPConnection, Action action) {
        return isFeatureSupportedByServer(xMPPConnection, "http://jabber.org/protocol/amp?action=" + action.toString(), AMPExtension.NAMESPACE);
    }

    public static boolean isConditionSupported(XMPPConnection xMPPConnection, String str) {
        return isFeatureSupportedByServer(xMPPConnection, "http://jabber.org/protocol/amp?condition=" + str, AMPExtension.NAMESPACE);
    }

    private static boolean isFeatureSupportedByServer(XMPPConnection xMPPConnection, String str, String str2) {
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).supportsFeature(str2, str);
    }
}
