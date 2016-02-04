package org.jivesoftware.smackx.time;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.time.packet.Time;

public class EntityTimeManager extends Manager {
    private static final Map<XMPPConnection, EntityTimeManager> INSTANCES;
    private static boolean autoEnable;
    private boolean enabled;

    /* renamed from: org.jivesoftware.smackx.time.EntityTimeManager.1 */
    final class C11151 implements ConnectionCreationListener {
        C11151() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            EntityTimeManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.time.EntityTimeManager.2 */
    class C11162 extends AbstractIqRequestHandler {
        C11162(String str, String str2, Type type, Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            if (EntityTimeManager.this.enabled) {
                return Time.createResponse(iq);
            }
            return IQ.createErrorResponse(iq, new XMPPError(Condition.not_acceptable));
        }
    }

    static {
        INSTANCES = new WeakHashMap();
        autoEnable = true;
        XMPPConnectionRegistry.addConnectionCreationListener(new C11151());
    }

    public static void setAutoEnable(boolean z) {
        autoEnable = z;
    }

    public static synchronized EntityTimeManager getInstanceFor(XMPPConnection xMPPConnection) {
        EntityTimeManager entityTimeManager;
        synchronized (EntityTimeManager.class) {
            entityTimeManager = (EntityTimeManager) INSTANCES.get(xMPPConnection);
            if (entityTimeManager == null) {
                entityTimeManager = new EntityTimeManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, entityTimeManager);
            }
        }
        return entityTimeManager;
    }

    private EntityTimeManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.enabled = false;
        if (autoEnable) {
            enable();
        }
        xMPPConnection.registerIQRequestHandler(new C11162(Time.ELEMENT, Time.NAMESPACE, Type.get, Mode.async));
    }

    public synchronized void enable() {
        if (!this.enabled) {
            ServiceDiscoveryManager.getInstanceFor(connection()).addFeature(Time.NAMESPACE);
            this.enabled = true;
        }
    }

    public synchronized void disable() {
        if (this.enabled) {
            ServiceDiscoveryManager.getInstanceFor(connection()).removeFeature(Time.NAMESPACE);
            this.enabled = false;
        }
    }

    public boolean isTimeSupported(String str) {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, Time.NAMESPACE);
    }

    public Time getTime(String str) {
        if (!isTimeSupported(str)) {
            return null;
        }
        return (Time) connection().createPacketCollectorAndSend(new Time()).nextResultOrThrow();
    }
}
