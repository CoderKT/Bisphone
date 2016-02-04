package org.jivesoftware.smackx.vcardtemp;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

public class VCardManager extends Manager {
    public static final String ELEMENT = "vCard";
    private static final Map<XMPPConnection, VCardManager> INSTANCES;
    public static final String NAMESPACE = "vcard-temp";

    /* renamed from: org.jivesoftware.smackx.vcardtemp.VCardManager.1 */
    final class C11171 implements ConnectionCreationListener {
        C11171() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            VCardManager.getInstanceFor(xMPPConnection);
        }
    }

    static {
        INSTANCES = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C11171());
    }

    public static synchronized VCardManager getInstanceFor(XMPPConnection xMPPConnection) {
        VCardManager vCardManager;
        synchronized (VCardManager.class) {
            vCardManager = (VCardManager) INSTANCES.get(xMPPConnection);
            if (vCardManager == null) {
                vCardManager = new VCardManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, vCardManager);
            }
        }
        return vCardManager;
    }

    @Deprecated
    public static boolean isSupported(String str, XMPPConnection xMPPConnection) {
        return getInstanceFor(xMPPConnection).isSupported(str);
    }

    private VCardManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(NAMESPACE);
    }

    public void saveVCard(VCard vCard) {
        vCard.setTo(null);
        vCard.setType(Type.set);
        vCard.setStanzaId(StanzaIdUtil.newStanzaId());
        connection().createPacketCollectorAndSend(vCard).nextResultOrThrow();
    }

    public VCard loadVCard() {
        return loadVCard(null);
    }

    public VCard loadVCard(String str) {
        IQ vCard = new VCard();
        vCard.setTo(str);
        return (VCard) connection().createPacketCollectorAndSend(vCard).nextResultOrThrow();
    }

    public boolean isSupported(String str) {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, NAMESPACE);
    }
}
