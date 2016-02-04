package org.jivesoftware.smackx.xhtmlim;

import java.util.List;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.xhtmlim.packet.XHTMLExtension;

public class XHTMLManager {

    /* renamed from: org.jivesoftware.smackx.xhtmlim.XHTMLManager.1 */
    final class C11241 implements ConnectionCreationListener {
        C11241() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            XHTMLManager.setServiceEnabled(xMPPConnection, true);
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C11241());
    }

    public static List<CharSequence> getBodies(Message message) {
        XHTMLExtension from = XHTMLExtension.from(message);
        if (from != null) {
            return from.getBodies();
        }
        return null;
    }

    public static void addBody(Message message, XHTMLText xHTMLText) {
        XHTMLExtension from = XHTMLExtension.from(message);
        if (from == null) {
            from = new XHTMLExtension();
            message.addExtension(from);
        }
        from.addBody(xHTMLText.toXML());
    }

    public static boolean isXHTMLMessage(Message message) {
        return message.getExtension(XHTMLExtension.ELEMENT, XHTMLExtension.NAMESPACE) != null;
    }

    public static synchronized void setServiceEnabled(XMPPConnection xMPPConnection, boolean z) {
        synchronized (XHTMLManager.class) {
            if (isServiceEnabled(xMPPConnection) != z) {
                if (z) {
                    ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(XHTMLExtension.NAMESPACE);
                } else {
                    ServiceDiscoveryManager.getInstanceFor(xMPPConnection).removeFeature(XHTMLExtension.NAMESPACE);
                }
            }
        }
    }

    public static boolean isServiceEnabled(XMPPConnection xMPPConnection) {
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).includesFeature(XHTMLExtension.NAMESPACE);
    }

    public static boolean isServiceEnabled(XMPPConnection xMPPConnection, String str) {
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).supportsFeature(str, XHTMLExtension.NAMESPACE);
    }
}
