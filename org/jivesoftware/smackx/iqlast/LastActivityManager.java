package org.jivesoftware.smackx.iqlast;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.iqlast.packet.LastActivity;
import org.jivesoftware.smackx.search.UserSearch;
import se.emilsjolander.stickylistheaders.C1128R;

public class LastActivityManager extends Manager {
    private static boolean enabledPerDefault;
    private static final Map<XMPPConnection, LastActivityManager> instances;
    private boolean enabled;
    private volatile long lastMessageSent;

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.1 */
    final class C10741 implements ConnectionCreationListener {
        C10741() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            LastActivityManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.2 */
    class C10752 implements StanzaListener {
        C10752() {
        }

        public void processPacket(Stanza stanza) {
            Mode mode = ((Presence) stanza).getMode();
            if (mode != null) {
                switch (C10785.$SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[mode.ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        LastActivityManager.this.resetIdleTime();
                    default:
                }
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.3 */
    class C10763 implements StanzaListener {
        C10763() {
        }

        public void processPacket(Stanza stanza) {
            if (((Message) stanza).getType() != Type.error) {
                LastActivityManager.this.resetIdleTime();
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.4 */
    class C10774 extends AbstractIqRequestHandler {
        C10774(String str, String str2, IQ.Type type, IQRequestHandler.Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            if (!LastActivityManager.this.enabled) {
                return IQ.createErrorResponse(iq, new XMPPError(Condition.not_acceptable));
            }
            IQ lastActivity = new LastActivity();
            lastActivity.setType(IQ.Type.result);
            lastActivity.setTo(iq.getFrom());
            lastActivity.setFrom(iq.getTo());
            lastActivity.setStanzaId(iq.getStanzaId());
            lastActivity.setLastActivity(LastActivityManager.this.getIdleTime());
            return lastActivity;
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.5 */
    /* synthetic */ class C10785 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode = new int[Mode.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[Mode.available.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[Mode.chat.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        instances = new WeakHashMap();
        enabledPerDefault = true;
        XMPPConnectionRegistry.addConnectionCreationListener(new C10741());
    }

    public static void setEnabledPerDefault(boolean z) {
        enabledPerDefault = z;
    }

    public static synchronized LastActivityManager getInstanceFor(XMPPConnection xMPPConnection) {
        LastActivityManager lastActivityManager;
        synchronized (LastActivityManager.class) {
            lastActivityManager = (LastActivityManager) instances.get(xMPPConnection);
            if (lastActivityManager == null) {
                lastActivityManager = new LastActivityManager(xMPPConnection);
            }
        }
        return lastActivityManager;
    }

    private LastActivityManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.enabled = false;
        xMPPConnection.addPacketSendingListener(new C10752(), StanzaTypeFilter.PRESENCE);
        xMPPConnection.addPacketSendingListener(new C10763(), StanzaTypeFilter.MESSAGE);
        xMPPConnection.registerIQRequestHandler(new C10774(UserSearch.ELEMENT, LastActivity.NAMESPACE, IQ.Type.get, IQRequestHandler.Mode.async));
        if (enabledPerDefault) {
            enable();
        }
        resetIdleTime();
        instances.put(xMPPConnection, this);
    }

    public synchronized void enable() {
        ServiceDiscoveryManager.getInstanceFor(connection()).addFeature(LastActivity.NAMESPACE);
        this.enabled = true;
    }

    public synchronized void disable() {
        ServiceDiscoveryManager.getInstanceFor(connection()).removeFeature(LastActivity.NAMESPACE);
        this.enabled = false;
    }

    private void resetIdleTime() {
        this.lastMessageSent = System.currentTimeMillis();
    }

    private long getIdleTime() {
        return (System.currentTimeMillis() - this.lastMessageSent) / 1000;
    }

    public LastActivity getLastActivity(String str) {
        return (LastActivity) connection().createPacketCollectorAndSend(new LastActivity(str)).nextResultOrThrow();
    }

    public boolean isLastActivitySupported(String str) {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, LastActivity.NAMESPACE);
    }
}
