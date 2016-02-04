package org.jivesoftware.smackx.bytestreams.ibb;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;

public class InBandBytestreamManager implements BytestreamManager {
    public static final int MAXIMUM_BLOCK_SIZE = 65535;
    private static final String SESSION_ID_PREFIX = "jibb_";
    private static final Map<XMPPConnection, InBandBytestreamManager> managers;
    private static final Random randomGenerator;
    private final List<BytestreamListener> allRequestListeners;
    private final CloseListener closeListener;
    private final XMPPConnection connection;
    private final DataListener dataListener;
    private int defaultBlockSize;
    private List<String> ignoredBytestreamRequests;
    private final InitiationListener initiationListener;
    private int maximumBlockSize;
    private final Map<String, InBandBytestreamSession> sessions;
    private StanzaType stanza;
    private final Map<String, BytestreamListener> userListeners;

    /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.1 */
    final class C10361 implements ConnectionCreationListener {

        /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.1.1 */
        class C10351 extends AbstractConnectionClosedListener {
            final /* synthetic */ XMPPConnection val$connection;

            C10351(XMPPConnection xMPPConnection) {
                this.val$connection = xMPPConnection;
            }

            public void connectionTerminated() {
                InBandBytestreamManager.getByteStreamManager(this.val$connection).disableService();
            }

            public void reconnectionSuccessful() {
                InBandBytestreamManager.getByteStreamManager(this.val$connection);
            }
        }

        C10361() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            InBandBytestreamManager.getByteStreamManager(xMPPConnection);
            xMPPConnection.addConnectionListener(new C10351(xMPPConnection));
        }
    }

    public enum StanzaType {
        IQ,
        MESSAGE
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C10361());
        randomGenerator = new Random();
        managers = new HashMap();
    }

    public static synchronized InBandBytestreamManager getByteStreamManager(XMPPConnection xMPPConnection) {
        InBandBytestreamManager inBandBytestreamManager;
        synchronized (InBandBytestreamManager.class) {
            if (xMPPConnection == null) {
                inBandBytestreamManager = null;
            } else {
                inBandBytestreamManager = (InBandBytestreamManager) managers.get(xMPPConnection);
                if (inBandBytestreamManager == null) {
                    inBandBytestreamManager = new InBandBytestreamManager(xMPPConnection);
                    managers.put(xMPPConnection, inBandBytestreamManager);
                }
            }
        }
        return inBandBytestreamManager;
    }

    private InBandBytestreamManager(XMPPConnection xMPPConnection) {
        this.userListeners = new ConcurrentHashMap();
        this.allRequestListeners = Collections.synchronizedList(new LinkedList());
        this.sessions = new ConcurrentHashMap();
        this.defaultBlockSize = 4096;
        this.maximumBlockSize = MAXIMUM_BLOCK_SIZE;
        this.stanza = StanzaType.IQ;
        this.ignoredBytestreamRequests = Collections.synchronizedList(new LinkedList());
        this.connection = xMPPConnection;
        this.initiationListener = new InitiationListener(this);
        xMPPConnection.registerIQRequestHandler(this.initiationListener);
        this.dataListener = new DataListener(this);
        xMPPConnection.registerIQRequestHandler(this.dataListener);
        this.closeListener = new CloseListener(this);
        xMPPConnection.registerIQRequestHandler(this.closeListener);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamListener) {
        this.allRequestListeners.add(bytestreamListener);
    }

    public void removeIncomingBytestreamListener(BytestreamListener bytestreamListener) {
        this.allRequestListeners.remove(bytestreamListener);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamListener, String str) {
        this.userListeners.put(str, bytestreamListener);
    }

    public void removeIncomingBytestreamListener(String str) {
        this.userListeners.remove(str);
    }

    public void ignoreBytestreamRequestOnce(String str) {
        this.ignoredBytestreamRequests.add(str);
    }

    public int getDefaultBlockSize() {
        return this.defaultBlockSize;
    }

    public void setDefaultBlockSize(int i) {
        if (i <= 0 || i > MAXIMUM_BLOCK_SIZE) {
            throw new IllegalArgumentException("Default block size must be between 1 and 65535");
        }
        this.defaultBlockSize = i;
    }

    public int getMaximumBlockSize() {
        return this.maximumBlockSize;
    }

    public void setMaximumBlockSize(int i) {
        if (i <= 0 || i > MAXIMUM_BLOCK_SIZE) {
            throw new IllegalArgumentException("Maximum block size must be between 1 and 65535");
        }
        this.maximumBlockSize = i;
    }

    public StanzaType getStanza() {
        return this.stanza;
    }

    public void setStanza(StanzaType stanzaType) {
        this.stanza = stanzaType;
    }

    public InBandBytestreamSession establishSession(String str) {
        return establishSession(str, getNextSessionID());
    }

    public InBandBytestreamSession establishSession(String str, String str2) {
        IQ open = new Open(str2, this.defaultBlockSize, this.stanza);
        open.setTo(str);
        this.connection.createPacketCollectorAndSend(open).nextResultOrThrow();
        InBandBytestreamSession inBandBytestreamSession = new InBandBytestreamSession(this.connection, open, str);
        this.sessions.put(str2, inBandBytestreamSession);
        return inBandBytestreamSession;
    }

    protected void replyRejectPacket(IQ iq) {
        this.connection.sendStanza(IQ.createErrorResponse(iq, new XMPPError(Condition.not_acceptable)));
    }

    protected void replyResourceConstraintPacket(IQ iq) {
        this.connection.sendStanza(IQ.createErrorResponse(iq, new XMPPError(Condition.resource_constraint)));
    }

    protected void replyItemNotFoundPacket(IQ iq) {
        this.connection.sendStanza(IQ.createErrorResponse(iq, new XMPPError(Condition.item_not_found)));
    }

    private String getNextSessionID() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SESSION_ID_PREFIX);
        stringBuilder.append(Math.abs(randomGenerator.nextLong()));
        return stringBuilder.toString();
    }

    protected XMPPConnection getConnection() {
        return this.connection;
    }

    protected BytestreamListener getUserListener(String str) {
        return (BytestreamListener) this.userListeners.get(str);
    }

    protected List<BytestreamListener> getAllRequestListeners() {
        return this.allRequestListeners;
    }

    protected Map<String, InBandBytestreamSession> getSessions() {
        return this.sessions;
    }

    protected List<String> getIgnoredBytestreamRequests() {
        return this.ignoredBytestreamRequests;
    }

    private void disableService() {
        managers.remove(this.connection);
        this.connection.unregisterIQRequestHandler(this.initiationListener);
        this.connection.unregisterIQRequestHandler(this.dataListener);
        this.connection.unregisterIQRequestHandler(this.closeListener);
        this.initiationListener.shutdown();
        this.userListeners.clear();
        this.allRequestListeners.clear();
        this.sessions.clear();
        this.ignoredBytestreamRequests.clear();
    }
}
