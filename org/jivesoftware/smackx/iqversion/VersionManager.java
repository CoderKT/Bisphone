package org.jivesoftware.smackx.iqversion;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.iqversion.packet.Version;
import org.jivesoftware.smackx.search.UserSearch;

public class VersionManager extends Manager {
    private static final Map<XMPPConnection, VersionManager> INSTANCES;
    private static boolean autoAppendSmackVersion;
    private static Version defaultVersion;
    private Version ourVersion;

    /* renamed from: org.jivesoftware.smackx.iqversion.VersionManager.1 */
    final class C10791 implements ConnectionCreationListener {
        C10791() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            VersionManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqversion.VersionManager.2 */
    class C10802 extends AbstractIqRequestHandler {
        C10802(String str, String str2, Type type, Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            if (VersionManager.this.ourVersion == null) {
                return IQ.createErrorResponse(iq, new XMPPError(Condition.not_acceptable));
            }
            return Version.createResultFor(iq, VersionManager.this.ourVersion);
        }
    }

    static {
        INSTANCES = new WeakHashMap();
        autoAppendSmackVersion = true;
        XMPPConnectionRegistry.addConnectionCreationListener(new C10791());
    }

    public static void setDefaultVersion(String str, String str2) {
        setDefaultVersion(str, str2, null);
    }

    public static void setDefaultVersion(String str, String str2, String str3) {
        defaultVersion = generateVersionFrom(str, str2, str3);
    }

    private VersionManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.ourVersion = defaultVersion;
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(Version.NAMESPACE);
        xMPPConnection.registerIQRequestHandler(new C10802(UserSearch.ELEMENT, Version.NAMESPACE, Type.get, Mode.async));
    }

    public static synchronized VersionManager getInstanceFor(XMPPConnection xMPPConnection) {
        VersionManager versionManager;
        synchronized (VersionManager.class) {
            versionManager = (VersionManager) INSTANCES.get(xMPPConnection);
            if (versionManager == null) {
                versionManager = new VersionManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, versionManager);
            }
        }
        return versionManager;
    }

    public static void setAutoAppendSmackVersion(boolean z) {
        autoAppendSmackVersion = z;
    }

    public void setVersion(String str, String str2) {
        setVersion(str, str2, null);
    }

    public void setVersion(String str, String str2, String str3) {
        this.ourVersion = generateVersionFrom(str, str2, str3);
    }

    public void unsetVersion() {
        this.ourVersion = null;
    }

    public boolean isSupported(String str) {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, Version.NAMESPACE);
    }

    public Version getVersion(String str) {
        if (isSupported(str)) {
            return (Version) connection().createPacketCollectorAndSend(new Version(str)).nextResultOrThrow();
        }
        return null;
    }

    private static Version generateVersionFrom(String str, String str2, String str3) {
        if (autoAppendSmackVersion) {
            str = str + " (Smack " + SmackConfiguration.getVersion() + ')';
        }
        return new Version(str, str2, str3);
    }
}
