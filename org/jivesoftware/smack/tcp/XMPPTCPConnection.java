package org.jivesoftware.smack.tcp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.PasswordCallback;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.AlreadyConnectedException;
import org.jivesoftware.smack.SmackException.AlreadyLoggedInException;
import org.jivesoftware.smack.SmackException.ConnectionException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.SmackException.SecurityRequiredByClientException;
import org.jivesoftware.smack.SmackException.SecurityRequiredByServerException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.SynchronizationPoint;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.StreamErrorException;
import org.jivesoftware.smack.compress.packet.Compress;
import org.jivesoftware.smack.compress.packet.Compress.Feature;
import org.jivesoftware.smack.compress.packet.Compressed;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.StartTls;
import org.jivesoftware.smack.packet.StreamOpen;
import org.jivesoftware.smack.sm.SMUtils;
import org.jivesoftware.smack.sm.StreamManagementException.StreamManagementCounterError;
import org.jivesoftware.smack.sm.StreamManagementException.StreamManagementNotEnabledException;
import org.jivesoftware.smack.sm.packet.StreamManagement;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckAnswer;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smack.sm.packet.StreamManagement.Enable;
import org.jivesoftware.smack.sm.packet.StreamManagement.Resume;
import org.jivesoftware.smack.sm.packet.StreamManagement.StreamManagementFeature;
import org.jivesoftware.smack.sm.predicates.Predicate;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jivesoftware.smack.util.ArrayBlockingQueueWithShutdown;
import org.jivesoftware.smack.util.Async;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.TLSUtils;
import org.jivesoftware.smack.util.dns.HostAddress;
import org.jxmpp.util.XmppStringUtils;
import org.xmlpull.v1.XmlPullParser;

public class XMPPTCPConnection extends AbstractXMPPConnection {
    private static final Logger LOGGER;
    private static final int QUEUE_SIZE = 500;
    private static BundleAndDeferCallback defaultBundleAndDeferCallback;
    private static boolean useSmDefault;
    private static boolean useSmResumptionDefault;
    private BundleAndDeferCallback bundleAndDeferCallback;
    private long clientHandledStanzasCount;
    private final SynchronizationPoint<XMPPException> compressSyncPoint;
    private final XMPPTCPConnectionConfiguration config;
    private boolean disconnectedButResumeable;
    private final SynchronizationPoint<Exception> initalOpenStreamSend;
    private final SynchronizationPoint<XMPPException> maybeCompressFeaturesReceived;
    protected PacketReader packetReader;
    protected PacketWriter packetWriter;
    private final Set<StanzaFilter> requestAckPredicates;
    private long serverHandledStanzasCount;
    private int smClientMaxResumptionTime;
    private final SynchronizationPoint<XMPPException> smEnabledSyncPoint;
    private final SynchronizationPoint<XMPPException> smResumedSyncPoint;
    private int smServerMaxResumptimTime;
    private String smSessionId;
    private boolean smWasEnabledAtLeastOnce;
    private Socket socket;
    private volatile boolean socketClosed;
    private final Collection<StanzaListener> stanzaAcknowledgedListeners;
    private final Map<String, StanzaListener> stanzaIdAcknowledgedListeners;
    private BlockingQueue<Stanza> unacknowledgedStanzas;
    private boolean useSm;
    private boolean useSmResumption;
    private boolean usingTLS;

    /* renamed from: org.jivesoftware.smack.tcp.XMPPTCPConnection.1 */
    class C10181 extends AbstractConnectionListener {
        C10181() {
        }

        public void connectionClosedOnError(Exception exception) {
            if (exception instanceof StreamErrorException) {
                XMPPTCPConnection.this.dropSmState();
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.tcp.XMPPTCPConnection.2 */
    class C10192 implements Runnable {
        final /* synthetic */ String val$id;

        C10192(String str) {
            this.val$id = str;
        }

        public void run() {
            XMPPTCPConnection.this.stanzaIdAcknowledgedListeners.remove(this.val$id);
        }
    }

    /* renamed from: org.jivesoftware.smack.tcp.XMPPTCPConnection.3 */
    class C10203 implements Runnable {
        final /* synthetic */ List val$ackedStanzas;

        C10203(List list) {
            this.val$ackedStanzas = list;
        }

        public void run() {
            for (Stanza stanza : this.val$ackedStanzas) {
                for (StanzaListener processPacket : XMPPTCPConnection.this.stanzaAcknowledgedListeners) {
                    try {
                        StanzaListener processPacket2;
                        processPacket2.processPacket(stanza);
                    } catch (Throwable e) {
                        XMPPTCPConnection.LOGGER.log(Level.FINER, "Received not connected exception", e);
                    }
                }
                CharSequence stanzaId = stanza.getStanzaId();
                if (!StringUtils.isNullOrEmpty(stanzaId)) {
                    processPacket2 = (StanzaListener) XMPPTCPConnection.this.stanzaIdAcknowledgedListeners.remove(stanzaId);
                    if (processPacket2 != null) {
                        try {
                            processPacket2.processPacket(stanza);
                        } catch (Throwable e2) {
                            XMPPTCPConnection.LOGGER.log(Level.FINER, "Received not connected exception", e2);
                        }
                    }
                }
            }
        }
    }

    public class PacketReader {
        static final /* synthetic */ boolean $assertionsDisabled;
        private volatile boolean done;
        XmlPullParser parser;

        /* renamed from: org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketReader.1 */
        class C10211 implements Runnable {
            C10211() {
            }

            public void run() {
                PacketReader.this.parsePackets();
            }
        }

        static {
            $assertionsDisabled = !XMPPTCPConnection.class.desiredAssertionStatus();
        }

        protected PacketReader() {
        }

        void init() {
            this.done = false;
            Async.go(new C10211(), "Smack Packet Reader (" + XMPPTCPConnection.this.getConnectionCounter() + ")");
        }

        void shutdown() {
            this.done = true;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void parsePackets() {
            /*
            r8 = this;
            r4 = 2;
            r1 = 0;
            r2 = -1;
            r3 = 1;
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.initalOpenStreamSend;	 Catch:{ Exception -> 0x004d }
            r0.checkIfSuccessOrWait();	 Catch:{ Exception -> 0x004d }
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = r0.getEventType();	 Catch:{ Exception -> 0x004d }
        L_0x0013:
            r5 = r8.done;	 Catch:{ Exception -> 0x004d }
            if (r5 != 0) goto L_0x005f;
        L_0x0017:
            switch(r0) {
                case 1: goto L_0x03d9;
                case 2: goto L_0x0021;
                case 3: goto L_0x03c4;
                default: goto L_0x001a;
            };	 Catch:{ Exception -> 0x004d }
        L_0x001a:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = r0.next();	 Catch:{ Exception -> 0x004d }
            goto L_0x0013;
        L_0x0021:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r5 = r0.getName();	 Catch:{ Exception -> 0x004d }
            r0 = r5.hashCode();	 Catch:{ Exception -> 0x004d }
            switch(r0) {
                case -1867169789: goto L_0x00bc;
                case -1609594047: goto L_0x00d4;
                case -1281977283: goto L_0x00e0;
                case -1276666629: goto L_0x0074;
                case -1086574198: goto L_0x00a6;
                case -891990144: goto L_0x007e;
                case -369449087: goto L_0x00c8;
                case -309519186: goto L_0x009c;
                case -290659267: goto L_0x0092;
                case 97: goto L_0x00f8;
                case 114: goto L_0x0104;
                case 3368: goto L_0x006a;
                case 96784904: goto L_0x0088;
                case 954925063: goto L_0x0060;
                case 1097547223: goto L_0x00ec;
                case 1402633315: goto L_0x00b0;
                default: goto L_0x002e;
            };	 Catch:{ Exception -> 0x004d }
        L_0x002e:
            r0 = r2;
        L_0x002f:
            switch(r0) {
                case 0: goto L_0x0110;
                case 1: goto L_0x0110;
                case 2: goto L_0x0110;
                case 3: goto L_0x0139;
                case 4: goto L_0x017b;
                case 5: goto L_0x0187;
                case 6: goto L_0x0190;
                case 7: goto L_0x01ac;
                case 8: goto L_0x020b;
                case 9: goto L_0x021c;
                case 10: goto L_0x0237;
                case 11: goto L_0x024c;
                case 12: goto L_0x02b6;
                case 13: goto L_0x030b;
                case 14: goto L_0x0390;
                case 15: goto L_0x03a1;
                default: goto L_0x0032;
            };	 Catch:{ Exception -> 0x004d }
        L_0x0032:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;	 Catch:{ Exception -> 0x004d }
            r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004d }
            r6.<init>();	 Catch:{ Exception -> 0x004d }
            r7 = "Unknown top level stream element: ";
            r6 = r6.append(r7);	 Catch:{ Exception -> 0x004d }
            r5 = r6.append(r5);	 Catch:{ Exception -> 0x004d }
            r5 = r5.toString();	 Catch:{ Exception -> 0x004d }
            r0.warning(r5);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x004d:
            r0 = move-exception;
            r1 = r8.done;
            if (r1 != 0) goto L_0x005f;
        L_0x0052:
            r1 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;
            r1 = r1.isSocketClosed();
            if (r1 != 0) goto L_0x005f;
        L_0x005a:
            r1 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;
            r1.notifyConnectionError(r0);
        L_0x005f:
            return;
        L_0x0060:
            r0 = "message";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x0068:
            r0 = r1;
            goto L_0x002f;
        L_0x006a:
            r0 = "iq";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x0072:
            r0 = r3;
            goto L_0x002f;
        L_0x0074:
            r0 = "presence";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x007c:
            r0 = r4;
            goto L_0x002f;
        L_0x007e:
            r0 = "stream";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x0086:
            r0 = 3;
            goto L_0x002f;
        L_0x0088:
            r0 = "error";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x0090:
            r0 = 4;
            goto L_0x002f;
        L_0x0092:
            r0 = "features";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x009a:
            r0 = 5;
            goto L_0x002f;
        L_0x009c:
            r0 = "proceed";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x00a4:
            r0 = 6;
            goto L_0x002f;
        L_0x00a6:
            r0 = "failure";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x00ae:
            r0 = 7;
            goto L_0x002f;
        L_0x00b0:
            r0 = "challenge";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x00b8:
            r0 = 8;
            goto L_0x002f;
        L_0x00bc:
            r0 = "success";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x00c4:
            r0 = 9;
            goto L_0x002f;
        L_0x00c8:
            r0 = "compressed";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x00d0:
            r0 = 10;
            goto L_0x002f;
        L_0x00d4:
            r0 = "enabled";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x00dc:
            r0 = 11;
            goto L_0x002f;
        L_0x00e0:
            r0 = "failed";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x00e8:
            r0 = 12;
            goto L_0x002f;
        L_0x00ec:
            r0 = "resumed";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x00f4:
            r0 = 13;
            goto L_0x002f;
        L_0x00f8:
            r0 = "a";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x0100:
            r0 = 14;
            goto L_0x002f;
        L_0x0104:
            r0 = "r";
            r0 = r5.equals(r0);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x002e;
        L_0x010c:
            r0 = 15;
            goto L_0x002f;
        L_0x0110:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ all -> 0x0128 }
            r5 = r8.parser;	 Catch:{ all -> 0x0128 }
            r0.parseAndProcessStanza(r5);	 Catch:{ all -> 0x0128 }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r6 = r5.clientHandledStanzasCount;	 Catch:{ Exception -> 0x004d }
            r6 = org.jivesoftware.smack.sm.SMUtils.incrementHeight(r6);	 Catch:{ Exception -> 0x004d }
            r0.clientHandledStanzasCount = r6;	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x0128:
            r0 = move-exception;
            r1 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r2 = r2.clientHandledStanzasCount;	 Catch:{ Exception -> 0x004d }
            r2 = org.jivesoftware.smack.sm.SMUtils.incrementHeight(r2);	 Catch:{ Exception -> 0x004d }
            r1.clientHandledStanzasCount = r2;	 Catch:{ Exception -> 0x004d }
            throw r0;	 Catch:{ Exception -> 0x004d }
        L_0x0139:
            r0 = "jabber:client";
            r5 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r6 = 0;
            r5 = r5.getNamespace(r6);	 Catch:{ Exception -> 0x004d }
            r0 = r0.equals(r5);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x001a;
        L_0x0148:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r6 = "";
            r7 = "id";
            r5 = r5.getAttributeValue(r6, r7);	 Catch:{ Exception -> 0x004d }
            r0.streamId = r5;	 Catch:{ Exception -> 0x004d }
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r5 = "";
            r6 = "from";
            r0 = r0.getAttributeValue(r5, r6);	 Catch:{ Exception -> 0x004d }
            r5 = $assertionsDisabled;	 Catch:{ Exception -> 0x004d }
            if (r5 != 0) goto L_0x001a;
        L_0x0165:
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.config;	 Catch:{ Exception -> 0x004d }
            r5 = r5.getServiceName();	 Catch:{ Exception -> 0x004d }
            r0 = r0.equals(r5);	 Catch:{ Exception -> 0x004d }
            if (r0 != 0) goto L_0x001a;
        L_0x0175:
            r0 = new java.lang.AssertionError;	 Catch:{ Exception -> 0x004d }
            r0.<init>();	 Catch:{ Exception -> 0x004d }
            throw r0;	 Catch:{ Exception -> 0x004d }
        L_0x017b:
            r0 = new org.jivesoftware.smack.XMPPException$StreamErrorException;	 Catch:{ Exception -> 0x004d }
            r1 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r1 = org.jivesoftware.smack.util.PacketParserUtils.parseStreamError(r1);	 Catch:{ Exception -> 0x004d }
            r0.<init>(r1);	 Catch:{ Exception -> 0x004d }
            throw r0;	 Catch:{ Exception -> 0x004d }
        L_0x0187:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0.parseFeatures(r5);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x0190:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x019c }
            r0.proceedTLSReceived();	 Catch:{ Exception -> 0x019c }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x019c }
            r0.openStream();	 Catch:{ Exception -> 0x019c }
            goto L_0x001a;
        L_0x019c:
            r0 = move-exception;
            r1 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r1 = r1.saslFeatureReceived;	 Catch:{ Exception -> 0x004d }
            r2 = new org.jivesoftware.smack.SmackException;	 Catch:{ Exception -> 0x004d }
            r2.<init>(r0);	 Catch:{ Exception -> 0x004d }
            r1.reportFailure(r2);	 Catch:{ Exception -> 0x004d }
            throw r0;	 Catch:{ Exception -> 0x004d }
        L_0x01ac:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r5 = 0;
            r0 = r0.getNamespace(r5);	 Catch:{ Exception -> 0x004d }
            r5 = r0.hashCode();	 Catch:{ Exception -> 0x004d }
            switch(r5) {
                case -1570142914: goto L_0x01dd;
                case 919182852: goto L_0x01c9;
                case 2117926358: goto L_0x01d3;
                default: goto L_0x01ba;
            };	 Catch:{ Exception -> 0x004d }
        L_0x01ba:
            r0 = r2;
        L_0x01bb:
            switch(r0) {
                case 0: goto L_0x01c0;
                case 1: goto L_0x01e7;
                case 2: goto L_0x01fa;
                default: goto L_0x01be;
            };	 Catch:{ Exception -> 0x004d }
        L_0x01be:
            goto L_0x001a;
        L_0x01c0:
            r0 = new org.jivesoftware.smack.XMPPException$XMPPErrorException;	 Catch:{ Exception -> 0x004d }
            r1 = "TLS negotiation has failed";
            r2 = 0;
            r0.<init>(r1, r2);	 Catch:{ Exception -> 0x004d }
            throw r0;	 Catch:{ Exception -> 0x004d }
        L_0x01c9:
            r5 = "urn:ietf:params:xml:ns:xmpp-tls";
            r0 = r0.equals(r5);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x01ba;
        L_0x01d1:
            r0 = r1;
            goto L_0x01bb;
        L_0x01d3:
            r5 = "http://jabber.org/protocol/compress";
            r0 = r0.equals(r5);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x01ba;
        L_0x01db:
            r0 = r3;
            goto L_0x01bb;
        L_0x01dd:
            r5 = "urn:ietf:params:xml:ns:xmpp-sasl";
            r0 = r0.equals(r5);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x01ba;
        L_0x01e5:
            r0 = r4;
            goto L_0x01bb;
        L_0x01e7:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.compressSyncPoint;	 Catch:{ Exception -> 0x004d }
            r5 = new org.jivesoftware.smack.XMPPException$XMPPErrorException;	 Catch:{ Exception -> 0x004d }
            r6 = "Could not establish compression";
            r7 = 0;
            r5.<init>(r6, r7);	 Catch:{ Exception -> 0x004d }
            r0.reportFailure(r5);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x01fa:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.util.PacketParserUtils.parseSASLFailure(r0);	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.getSASLAuthentication();	 Catch:{ Exception -> 0x004d }
            r5.authenticationFailed(r0);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x020b:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = r0.nextText();	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.getSASLAuthentication();	 Catch:{ Exception -> 0x004d }
            r5.challengeReceived(r0);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x021c:
            r0 = new org.jivesoftware.smack.sasl.packet.SaslStreamElements$Success;	 Catch:{ Exception -> 0x004d }
            r5 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r5 = r5.nextText();	 Catch:{ Exception -> 0x004d }
            r0.<init>(r5);	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5.openStream();	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.getSASLAuthentication();	 Catch:{ Exception -> 0x004d }
            r5.authenticated(r0);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x0237:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0.initReaderAndWriter();	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0.openStream();	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.compressSyncPoint;	 Catch:{ Exception -> 0x004d }
            r0.reportSuccess();	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x024c:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.sm.provider.ParseStreamManagement.enabled(r0);	 Catch:{ Exception -> 0x004d }
            r5 = r0.isResumeSet();	 Catch:{ Exception -> 0x004d }
            if (r5 == 0) goto L_0x02af;
        L_0x0258:
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r6 = r0.getId();	 Catch:{ Exception -> 0x004d }
            r5.smSessionId = r6;	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.smSessionId;	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.util.StringUtils.isNullOrEmpty(r5);	 Catch:{ Exception -> 0x004d }
            if (r5 == 0) goto L_0x0285;
        L_0x026d:
            r0 = new org.jivesoftware.smack.XMPPException$XMPPErrorException;	 Catch:{ Exception -> 0x004d }
            r1 = "Stream Management 'enabled' element with resume attribute but without session id received";
            r2 = new org.jivesoftware.smack.packet.XMPPError;	 Catch:{ Exception -> 0x004d }
            r3 = org.jivesoftware.smack.packet.XMPPError.Condition.bad_request;	 Catch:{ Exception -> 0x004d }
            r2.<init>(r3);	 Catch:{ Exception -> 0x004d }
            r0.<init>(r1, r2);	 Catch:{ Exception -> 0x004d }
            r1 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r1 = r1.smEnabledSyncPoint;	 Catch:{ Exception -> 0x004d }
            r1.reportFailure(r0);	 Catch:{ Exception -> 0x004d }
            throw r0;	 Catch:{ Exception -> 0x004d }
        L_0x0285:
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.getMaxResumptionTime();	 Catch:{ Exception -> 0x004d }
            r5.smServerMaxResumptimTime = r0;	 Catch:{ Exception -> 0x004d }
        L_0x028e:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r6 = 0;
            r0.clientHandledStanzasCount = r6;	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = 1;
            r0.smWasEnabledAtLeastOnce = r5;	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.smEnabledSyncPoint;	 Catch:{ Exception -> 0x004d }
            r0.reportSuccess();	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;	 Catch:{ Exception -> 0x004d }
            r5 = "Stream Management (XEP-198): succesfully enabled";
            r0.fine(r5);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x02af:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = 0;
            r0.smSessionId = r5;	 Catch:{ Exception -> 0x004d }
            goto L_0x028e;
        L_0x02b6:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.sm.provider.ParseStreamManagement.failed(r0);	 Catch:{ Exception -> 0x004d }
            r5 = new org.jivesoftware.smack.packet.XMPPError;	 Catch:{ Exception -> 0x004d }
            r0 = r0.getXMPPErrorCondition();	 Catch:{ Exception -> 0x004d }
            r5.<init>(r0);	 Catch:{ Exception -> 0x004d }
            r0 = new org.jivesoftware.smack.XMPPException$XMPPErrorException;	 Catch:{ Exception -> 0x004d }
            r6 = "Stream Management failed";
            r0.<init>(r6, r5);	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.smResumedSyncPoint;	 Catch:{ Exception -> 0x004d }
            r5 = r5.requestSent();	 Catch:{ Exception -> 0x004d }
            if (r5 == 0) goto L_0x02e3;
        L_0x02d8:
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.smResumedSyncPoint;	 Catch:{ Exception -> 0x004d }
            r5.reportFailure(r0);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x02e3:
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.smEnabledSyncPoint;	 Catch:{ Exception -> 0x004d }
            r5 = r5.requestSent();	 Catch:{ Exception -> 0x004d }
            if (r5 != 0) goto L_0x02f7;
        L_0x02ef:
            r0 = new java.lang.IllegalStateException;	 Catch:{ Exception -> 0x004d }
            r1 = "Failed element received but SM was not previously enabled";
            r0.<init>(r1);	 Catch:{ Exception -> 0x004d }
            throw r0;	 Catch:{ Exception -> 0x004d }
        L_0x02f7:
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.smEnabledSyncPoint;	 Catch:{ Exception -> 0x004d }
            r5.reportFailure(r0);	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.lastFeaturesReceived;	 Catch:{ Exception -> 0x004d }
            r0.reportSuccess();	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x030b:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.sm.provider.ParseStreamManagement.resumed(r0);	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.smSessionId;	 Catch:{ Exception -> 0x004d }
            r6 = r0.getPrevId();	 Catch:{ Exception -> 0x004d }
            r5 = r5.equals(r6);	 Catch:{ Exception -> 0x004d }
            if (r5 != 0) goto L_0x0331;
        L_0x0321:
            r1 = new org.jivesoftware.smack.sm.StreamManagementException$StreamIdDoesNotMatchException;	 Catch:{ Exception -> 0x004d }
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r2 = r2.smSessionId;	 Catch:{ Exception -> 0x004d }
            r0 = r0.getPrevId();	 Catch:{ Exception -> 0x004d }
            r1.<init>(r2, r0);	 Catch:{ Exception -> 0x004d }
            throw r1;	 Catch:{ Exception -> 0x004d }
        L_0x0331:
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.smResumedSyncPoint;	 Catch:{ Exception -> 0x004d }
            r5.reportSuccess();	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r5 = r5.smEnabledSyncPoint;	 Catch:{ Exception -> 0x004d }
            r5.reportSuccess();	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r6 = r0.getHandledCount();	 Catch:{ Exception -> 0x004d }
            r5.processHandledCount(r6);	 Catch:{ Exception -> 0x004d }
            r5 = new java.util.ArrayList;	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.unacknowledgedStanzas;	 Catch:{ Exception -> 0x004d }
            r0 = r0.size();	 Catch:{ Exception -> 0x004d }
            r5.<init>(r0);	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.unacknowledgedStanzas;	 Catch:{ Exception -> 0x004d }
            r0.drainTo(r5);	 Catch:{ Exception -> 0x004d }
            r6 = r5.iterator();	 Catch:{ Exception -> 0x004d }
        L_0x0368:
            r0 = r6.hasNext();	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x037a;
        L_0x036e:
            r0 = r6.next();	 Catch:{ Exception -> 0x004d }
            r0 = (org.jivesoftware.smack.packet.Stanza) r0;	 Catch:{ Exception -> 0x004d }
            r7 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r7.sendStanzaInternal(r0);	 Catch:{ Exception -> 0x004d }
            goto L_0x0368;
        L_0x037a:
            r0 = r5.isEmpty();	 Catch:{ Exception -> 0x004d }
            if (r0 != 0) goto L_0x0385;
        L_0x0380:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0.requestSmAcknowledgementInternal();	 Catch:{ Exception -> 0x004d }
        L_0x0385:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;	 Catch:{ Exception -> 0x004d }
            r5 = "Stream Management (XEP-198): Stream resumed";
            r0.fine(r5);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x0390:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.sm.provider.ParseStreamManagement.ackAnswer(r0);	 Catch:{ Exception -> 0x004d }
            r5 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r6 = r0.getHandledCount();	 Catch:{ Exception -> 0x004d }
            r5.processHandledCount(r6);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x03a1:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            org.jivesoftware.smack.sm.provider.ParseStreamManagement.ackRequest(r0);	 Catch:{ Exception -> 0x004d }
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0 = r0.smEnabledSyncPoint;	 Catch:{ Exception -> 0x004d }
            r0 = r0.wasSuccessful();	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x03b9;
        L_0x03b2:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0.sendSmAcknowledgementInternal();	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x03b9:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;	 Catch:{ Exception -> 0x004d }
            r5 = "SM Ack Request received while SM is not enabled";
            r0.warning(r5);	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x03c4:
            r0 = r8.parser;	 Catch:{ Exception -> 0x004d }
            r0 = r0.getName();	 Catch:{ Exception -> 0x004d }
            r5 = "stream";
            r0 = r0.equals(r5);	 Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x001a;
        L_0x03d2:
            r0 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x004d }
            r0.disconnect();	 Catch:{ Exception -> 0x004d }
            goto L_0x001a;
        L_0x03d9:
            r0 = new org.jivesoftware.smack.SmackException;	 Catch:{ Exception -> 0x004d }
            r1 = "Parser got END_DOCUMENT event. This could happen e.g. if the server closed the connection without sending a closing stream element";
            r0.<init>(r1);	 Catch:{ Exception -> 0x004d }
            throw r0;	 Catch:{ Exception -> 0x004d }
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketReader.parsePackets():void");
        }
    }

    public class PacketWriter {
        public static final int QUEUE_SIZE = 500;
        private volatile boolean instantShutdown;
        private final ArrayBlockingQueueWithShutdown<Element> queue;
        private boolean shouldBundleAndDefer;
        protected SynchronizationPoint<NoResponseException> shutdownDone;
        protected volatile Long shutdownTimestamp;

        /* renamed from: org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter.1 */
        class C10221 implements Runnable {
            C10221() {
            }

            public void run() {
                PacketWriter.this.writePackets();
            }
        }

        protected PacketWriter() {
            this.queue = new ArrayBlockingQueueWithShutdown(QUEUE_SIZE, true);
            this.shutdownDone = new SynchronizationPoint(XMPPTCPConnection.this);
            this.shutdownTimestamp = null;
        }

        void init() {
            this.shutdownDone.init();
            this.shutdownTimestamp = null;
            if (XMPPTCPConnection.this.unacknowledgedStanzas != null) {
                drainWriterQueueToUnacknowledgedStanzas();
            }
            this.queue.start();
            Async.go(new C10221(), "Smack Packet Writer (" + XMPPTCPConnection.this.getConnectionCounter() + ")");
        }

        private boolean done() {
            return this.shutdownTimestamp != null;
        }

        protected void throwNotConnectedExceptionIfDoneAndResumptionNotPossible() {
            if (done() && !XMPPTCPConnection.this.isSmResumptionPossible()) {
                throw new NotConnectedException();
            }
        }

        protected void sendStreamElement(Element element) {
            throwNotConnectedExceptionIfDoneAndResumptionNotPossible();
            Object obj = null;
            while (obj == null) {
                try {
                    this.queue.put(element);
                    obj = 1;
                } catch (Throwable e) {
                    throwNotConnectedExceptionIfDoneAndResumptionNotPossible();
                    XMPPTCPConnection.LOGGER.log(Level.WARNING, "Sending thread was interrupted", e);
                }
            }
        }

        void shutdown(boolean z) {
            this.instantShutdown = z;
            this.shutdownTimestamp = Long.valueOf(System.currentTimeMillis());
            this.queue.shutdown();
            try {
                this.shutdownDone.checkIfSuccessOrWait();
            } catch (Throwable e) {
                XMPPTCPConnection.LOGGER.log(Level.WARNING, "shutdownDone was not marked as successful by the writer thread", e);
            }
        }

        private Element nextStreamElement() {
            if (this.queue.isEmpty()) {
                this.shouldBundleAndDefer = true;
            }
            try {
                return (Element) this.queue.take();
            } catch (Throwable e) {
                if (!this.queue.isShutdown()) {
                    XMPPTCPConnection.LOGGER.log(Level.WARNING, "Packet writer thread was interrupted. Don't do that. Use disconnect() instead.", e);
                }
                return null;
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void writePackets() {
            /*
            r12 = this;
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r2.openStream();	 Catch:{ Exception -> 0x00d3 }
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r2 = r2.initalOpenStreamSend;	 Catch:{ Exception -> 0x00d3 }
            r2.reportSuccess();	 Catch:{ Exception -> 0x00d3 }
        L_0x000e:
            r2 = r12.done();	 Catch:{ Exception -> 0x00d3 }
            if (r2 != 0) goto L_0x0122;
        L_0x0014:
            r3 = r12.nextStreamElement();	 Catch:{ Exception -> 0x00d3 }
            if (r3 == 0) goto L_0x000e;
        L_0x001a:
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r2 = r2.bundleAndDeferCallback;	 Catch:{ Exception -> 0x00d3 }
            if (r2 == 0) goto L_0x005f;
        L_0x0022:
            r4 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r4 = r4.isAuthenticated();	 Catch:{ Exception -> 0x00d3 }
            if (r4 == 0) goto L_0x005f;
        L_0x002a:
            r4 = r12.shouldBundleAndDefer;	 Catch:{ Exception -> 0x00d3 }
            if (r4 == 0) goto L_0x005f;
        L_0x002e:
            r4 = 0;
            r12.shouldBundleAndDefer = r4;	 Catch:{ Exception -> 0x00d3 }
            r6 = new java.util.concurrent.atomic.AtomicBoolean;	 Catch:{ Exception -> 0x00d3 }
            r6.<init>();	 Catch:{ Exception -> 0x00d3 }
            r4 = new org.jivesoftware.smack.tcp.BundleAndDefer;	 Catch:{ Exception -> 0x00d3 }
            r4.<init>(r6);	 Catch:{ Exception -> 0x00d3 }
            r2 = r2.getBundleAndDeferMillis(r4);	 Catch:{ Exception -> 0x00d3 }
            if (r2 <= 0) goto L_0x005f;
        L_0x0041:
            r4 = (long) r2;	 Catch:{ Exception -> 0x00d3 }
            r8 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00d3 }
            monitor-enter(r6);	 Catch:{ Exception -> 0x00d3 }
        L_0x0047:
            r7 = r6.get();	 Catch:{ all -> 0x00f6 }
            if (r7 != 0) goto L_0x005e;
        L_0x004d:
            r10 = 0;
            r7 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
            if (r7 <= 0) goto L_0x005e;
        L_0x0053:
            r6.wait(r4);	 Catch:{ all -> 0x00f6 }
            r4 = (long) r2;	 Catch:{ all -> 0x00f6 }
            r10 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00f6 }
            r10 = r10 - r8;
            r4 = r4 - r10;
            goto L_0x0047;
        L_0x005e:
            monitor-exit(r6);	 Catch:{ all -> 0x00f6 }
        L_0x005f:
            r2 = 0;
            r4 = r3 instanceof org.jivesoftware.smack.packet.Stanza;	 Catch:{ Exception -> 0x00d3 }
            if (r4 == 0) goto L_0x0109;
        L_0x0064:
            r0 = r3;
            r0 = (org.jivesoftware.smack.packet.Stanza) r0;	 Catch:{ Exception -> 0x00d3 }
            r2 = r0;
        L_0x0068:
            r4 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r4 = r4.unacknowledgedStanzas;	 Catch:{ Exception -> 0x00d3 }
            if (r4 == 0) goto L_0x00a8;
        L_0x0070:
            if (r2 == 0) goto L_0x00a8;
        L_0x0072:
            r4 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r4 = r4.unacknowledgedStanzas;	 Catch:{ Exception -> 0x00d3 }
            r4 = r4.size();	 Catch:{ Exception -> 0x00d3 }
            r4 = (double) r4;	 Catch:{ Exception -> 0x00d3 }
            r6 = 4645744490609377280; // 0x4079000000000000 float:0.0 double:400.0;
            r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r4 != 0) goto L_0x009f;
        L_0x0083:
            r4 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r4 = r4.writer;	 Catch:{ Exception -> 0x00d3 }
            r5 = org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest.INSTANCE;	 Catch:{ Exception -> 0x00d3 }
            r5 = r5.toXML();	 Catch:{ Exception -> 0x00d3 }
            r5 = r5.toString();	 Catch:{ Exception -> 0x00d3 }
            r4.write(r5);	 Catch:{ Exception -> 0x00d3 }
            r4 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r4 = r4.writer;	 Catch:{ Exception -> 0x00d3 }
            r4.flush();	 Catch:{ Exception -> 0x00d3 }
        L_0x009f:
            r4 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ InterruptedException -> 0x011b }
            r4 = r4.unacknowledgedStanzas;	 Catch:{ InterruptedException -> 0x011b }
            r4.put(r2);	 Catch:{ InterruptedException -> 0x011b }
        L_0x00a8:
            r4 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r4 = r4.writer;	 Catch:{ Exception -> 0x00d3 }
            r3 = r3.toXML();	 Catch:{ Exception -> 0x00d3 }
            r3 = r3.toString();	 Catch:{ Exception -> 0x00d3 }
            r4.write(r3);	 Catch:{ Exception -> 0x00d3 }
            r3 = r12.queue;	 Catch:{ Exception -> 0x00d3 }
            r3 = r3.isEmpty();	 Catch:{ Exception -> 0x00d3 }
            if (r3 == 0) goto L_0x00ca;
        L_0x00c1:
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r3 = r3.writer;	 Catch:{ Exception -> 0x00d3 }
            r3.flush();	 Catch:{ Exception -> 0x00d3 }
        L_0x00ca:
            if (r2 == 0) goto L_0x000e;
        L_0x00cc:
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r3.firePacketSendingListeners(r2);	 Catch:{ Exception -> 0x00d3 }
            goto L_0x000e;
        L_0x00d3:
            r2 = move-exception;
            r3 = r12.done();	 Catch:{ all -> 0x00f9 }
            if (r3 != 0) goto L_0x01ad;
        L_0x00da:
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ all -> 0x00f9 }
            r3 = r3.isSocketClosed();	 Catch:{ all -> 0x00f9 }
            if (r3 != 0) goto L_0x01ad;
        L_0x00e2:
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ all -> 0x00f9 }
            r3.notifyConnectionError(r2);	 Catch:{ all -> 0x00f9 }
        L_0x00e7:
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;
            r3 = "Reporting shutdownDone success in writer thread";
            r2.fine(r3);
            r2 = r12.shutdownDone;
            r2.reportSuccess();
        L_0x00f5:
            return;
        L_0x00f6:
            r2 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x00f6 }
            throw r2;	 Catch:{ Exception -> 0x00d3 }
        L_0x00f9:
            r2 = move-exception;
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;
            r4 = "Reporting shutdownDone success in writer thread";
            r3.fine(r4);
            r3 = r12.shutdownDone;
            r3.reportSuccess();
            throw r2;
        L_0x0109:
            r4 = r3 instanceof org.jivesoftware.smack.sm.packet.StreamManagement.Enable;	 Catch:{ Exception -> 0x00d3 }
            if (r4 == 0) goto L_0x0068;
        L_0x010d:
            r4 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r5 = new java.util.concurrent.ArrayBlockingQueue;	 Catch:{ Exception -> 0x00d3 }
            r6 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
            r5.<init>(r6);	 Catch:{ Exception -> 0x00d3 }
            r4.unacknowledgedStanzas = r5;	 Catch:{ Exception -> 0x00d3 }
            goto L_0x0068;
        L_0x011b:
            r2 = move-exception;
            r3 = new java.lang.IllegalStateException;	 Catch:{ Exception -> 0x00d3 }
            r3.<init>(r2);	 Catch:{ Exception -> 0x00d3 }
            throw r3;	 Catch:{ Exception -> 0x00d3 }
        L_0x0122:
            r2 = r12.instantShutdown;	 Catch:{ Exception -> 0x00d3 }
            if (r2 != 0) goto L_0x019d;
        L_0x0126:
            r2 = r12.queue;	 Catch:{ Exception -> 0x0148 }
            r2 = r2.isEmpty();	 Catch:{ Exception -> 0x0148 }
            if (r2 != 0) goto L_0x0186;
        L_0x012e:
            r2 = r12.queue;	 Catch:{ Exception -> 0x0148 }
            r2 = r2.remove();	 Catch:{ Exception -> 0x0148 }
            r2 = (org.jivesoftware.smack.packet.Element) r2;	 Catch:{ Exception -> 0x0148 }
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x0148 }
            r3 = r3.writer;	 Catch:{ Exception -> 0x0148 }
            r2 = r2.toXML();	 Catch:{ Exception -> 0x0148 }
            r2 = r2.toString();	 Catch:{ Exception -> 0x0148 }
            r3.write(r2);	 Catch:{ Exception -> 0x0148 }
            goto L_0x0126;
        L_0x0148:
            r2 = move-exception;
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;	 Catch:{ Exception -> 0x00d3 }
            r4 = java.util.logging.Level.WARNING;	 Catch:{ Exception -> 0x00d3 }
            r5 = "Exception flushing queue during shutdown, ignore and continue";
            r3.log(r4, r5, r2);	 Catch:{ Exception -> 0x00d3 }
        L_0x0154:
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x0190 }
            r2 = r2.writer;	 Catch:{ Exception -> 0x0190 }
            r3 = "</stream:stream>";
            r2.write(r3);	 Catch:{ Exception -> 0x0190 }
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x0190 }
            r2 = r2.writer;	 Catch:{ Exception -> 0x0190 }
            r2.flush();	 Catch:{ Exception -> 0x0190 }
        L_0x0168:
            r2 = r12.queue;	 Catch:{ Exception -> 0x00d3 }
            r2.clear();	 Catch:{ Exception -> 0x00d3 }
        L_0x016d:
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x01ba }
            r2 = r2.writer;	 Catch:{ Exception -> 0x01ba }
            r2.close();	 Catch:{ Exception -> 0x01ba }
        L_0x0176:
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;
            r3 = "Reporting shutdownDone success in writer thread";
            r2.fine(r3);
            r2 = r12.shutdownDone;
            r2.reportSuccess();
            goto L_0x00f5;
        L_0x0186:
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x0148 }
            r2 = r2.writer;	 Catch:{ Exception -> 0x0148 }
            r2.flush();	 Catch:{ Exception -> 0x0148 }
            goto L_0x0154;
        L_0x0190:
            r2 = move-exception;
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;	 Catch:{ Exception -> 0x00d3 }
            r4 = java.util.logging.Level.WARNING;	 Catch:{ Exception -> 0x00d3 }
            r5 = "Exception writing closing stream element";
            r3.log(r4, r5, r2);	 Catch:{ Exception -> 0x00d3 }
            goto L_0x0168;
        L_0x019d:
            r2 = r12.instantShutdown;	 Catch:{ Exception -> 0x00d3 }
            if (r2 == 0) goto L_0x016d;
        L_0x01a1:
            r2 = org.jivesoftware.smack.tcp.XMPPTCPConnection.this;	 Catch:{ Exception -> 0x00d3 }
            r2 = r2.isSmEnabled();	 Catch:{ Exception -> 0x00d3 }
            if (r2 == 0) goto L_0x016d;
        L_0x01a9:
            r12.drainWriterQueueToUnacknowledgedStanzas();	 Catch:{ Exception -> 0x00d3 }
            goto L_0x016d;
        L_0x01ad:
            r3 = org.jivesoftware.smack.tcp.XMPPTCPConnection.LOGGER;	 Catch:{ all -> 0x00f9 }
            r4 = java.util.logging.Level.FINE;	 Catch:{ all -> 0x00f9 }
            r5 = "Ignoring Exception in writePackets()";
            r3.log(r4, r5, r2);	 Catch:{ all -> 0x00f9 }
            goto L_0x00e7;
        L_0x01ba:
            r2 = move-exception;
            goto L_0x0176;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter.writePackets():void");
        }

        private void drainWriterQueueToUnacknowledgedStanzas() {
            Object<Element> arrayList = new ArrayList(this.queue.size());
            this.queue.drainTo(arrayList);
            for (Element element : arrayList) {
                if (element instanceof Stanza) {
                    XMPPTCPConnection.this.unacknowledgedStanzas.add((Stanza) element);
                }
            }
        }
    }

    static {
        LOGGER = Logger.getLogger(XMPPTCPConnection.class.getName());
        useSmDefault = false;
        useSmResumptionDefault = true;
    }

    public XMPPTCPConnection(XMPPTCPConnectionConfiguration xMPPTCPConnectionConfiguration) {
        super(xMPPTCPConnectionConfiguration);
        this.disconnectedButResumeable = false;
        this.socketClosed = false;
        this.usingTLS = false;
        this.initalOpenStreamSend = new SynchronizationPoint(this);
        this.maybeCompressFeaturesReceived = new SynchronizationPoint(this);
        this.compressSyncPoint = new SynchronizationPoint(this);
        this.bundleAndDeferCallback = defaultBundleAndDeferCallback;
        this.smResumedSyncPoint = new SynchronizationPoint(this);
        this.smEnabledSyncPoint = new SynchronizationPoint(this);
        this.smClientMaxResumptionTime = -1;
        this.smServerMaxResumptimTime = -1;
        this.useSm = useSmDefault;
        this.useSmResumption = useSmResumptionDefault;
        this.serverHandledStanzasCount = 0;
        this.clientHandledStanzasCount = 0;
        this.smWasEnabledAtLeastOnce = false;
        this.stanzaAcknowledgedListeners = new ConcurrentLinkedQueue();
        this.stanzaIdAcknowledgedListeners = new ConcurrentHashMap();
        this.requestAckPredicates = new LinkedHashSet();
        this.config = xMPPTCPConnectionConfiguration;
        addConnectionListener(new C10181());
    }

    public XMPPTCPConnection(CharSequence charSequence, String str) {
        this(XmppStringUtils.m13441a(charSequence.toString()), str, XmppStringUtils.m13444b(charSequence.toString()));
    }

    public XMPPTCPConnection(CharSequence charSequence, String str, String str2) {
        this(((Builder) ((Builder) XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword(charSequence, str)).setServiceName(str2)).build());
    }

    protected void throwNotConnectedExceptionIfAppropriate() {
        if (this.packetWriter == null) {
            throw new NotConnectedException();
        }
        this.packetWriter.throwNotConnectedExceptionIfDoneAndResumptionNotPossible();
    }

    protected void throwAlreadyConnectedExceptionIfAppropriate() {
        if (isConnected() && !this.disconnectedButResumeable) {
            throw new AlreadyConnectedException();
        }
    }

    protected void throwAlreadyLoggedInExceptionIfAppropriate() {
        if (isAuthenticated() && !this.disconnectedButResumeable) {
            throw new AlreadyLoggedInException();
        }
    }

    protected void afterSuccessfulLogin(boolean z) {
        this.disconnectedButResumeable = false;
        super.afterSuccessfulLogin(z);
    }

    protected synchronized void loginNonAnonymously(String str, String str2, String str3) {
        if (this.saslAuthentication.hasNonAnonymousAuthentication()) {
            if (str2 != null) {
                this.saslAuthentication.authenticate(str, str2, str3);
            } else {
                this.saslAuthentication.authenticate(str3, this.config.getCallbackHandler());
            }
            if (this.config.isCompressionEnabled()) {
                useCompression();
            }
            if (isSmResumptionPossible()) {
                this.smResumedSyncPoint.sendAndWaitForResponse(new Resume(this.clientHandledStanzasCount, this.smSessionId));
                if (this.smResumedSyncPoint.wasSuccessful()) {
                    afterSuccessfulLogin(true);
                } else {
                    LOGGER.fine("Stream resumption failed, continuing with normal stream establishment process");
                }
            }
            bindResourceAndEstablishSession(str3);
            Object<Stanza> linkedList = new LinkedList();
            if (this.unacknowledgedStanzas != null) {
                this.unacknowledgedStanzas.drainTo(linkedList);
                dropSmState();
            }
            if (isSmAvailable() && this.useSm) {
                this.serverHandledStanzasCount = 0;
                this.smEnabledSyncPoint.sendAndWaitForResponseOrThrow(new Enable(this.useSmResumption, this.smClientMaxResumptionTime));
                synchronized (this.requestAckPredicates) {
                    if (this.requestAckPredicates.isEmpty()) {
                        this.requestAckPredicates.add(Predicate.forMessagesOrAfter5Stanzas());
                    }
                }
            }
            for (Stanza sendStanzaInternal : linkedList) {
                sendStanzaInternal(sendStanzaInternal);
            }
            afterSuccessfulLogin(false);
        } else {
            throw new SmackException("No non-anonymous SASL authentication mechanism available");
        }
    }

    public synchronized void loginAnonymously() {
        this.saslFeatureReceived.checkIfSuccessOrWaitOrThrow();
        if (this.saslAuthentication.hasAnonymousAuthentication()) {
            this.saslAuthentication.authenticateAnonymously();
            if (this.config.isCompressionEnabled()) {
                useCompression();
            }
            bindResourceAndEstablishSession(null);
            afterSuccessfulLogin(false);
        } else {
            throw new SmackException("No anonymous SASL authentication mechanism available");
        }
    }

    public boolean isSecureConnection() {
        return this.usingTLS;
    }

    public boolean isSocketClosed() {
        return this.socketClosed;
    }

    protected void shutdown() {
        if (isSmEnabled()) {
            try {
                sendSmAcknowledgementInternal();
            } catch (Throwable e) {
                LOGGER.log(Level.FINE, "Can not send final SM ack as connection is not connected", e);
            }
        }
        shutdown(false);
    }

    public synchronized void instantShutdown() {
        shutdown(true);
    }

    private void shutdown(boolean z) {
        if (!this.disconnectedButResumeable) {
            if (this.packetReader != null) {
                this.packetReader.shutdown();
            }
            if (this.packetWriter != null) {
                this.packetWriter.shutdown(z);
            }
            this.socketClosed = true;
            try {
                this.socket.close();
            } catch (Throwable e) {
                LOGGER.log(Level.WARNING, "shutdown", e);
            }
            setWasAuthenticated();
            if (isSmResumptionPossible() && z) {
                this.disconnectedButResumeable = true;
            } else {
                this.disconnectedButResumeable = false;
                this.smSessionId = null;
            }
            this.authenticated = false;
            this.connected = false;
            this.usingTLS = false;
            this.reader = null;
            this.writer = null;
            this.maybeCompressFeaturesReceived.init();
            this.compressSyncPoint.init();
            this.smResumedSyncPoint.init();
            this.smEnabledSyncPoint.init();
            this.initalOpenStreamSend.init();
        }
    }

    public void send(PlainStreamElement plainStreamElement) {
        this.packetWriter.sendStreamElement(plainStreamElement);
    }

    protected void sendStanzaInternal(Stanza stanza) {
        this.packetWriter.sendStreamElement(stanza);
        if (isSmEnabled()) {
            for (StanzaFilter accept : this.requestAckPredicates) {
                if (accept.accept(stanza)) {
                    requestSmAcknowledgementInternal();
                    return;
                }
            }
        }
    }

    private void connectUsingConfiguration() {
        SocketFactory socketFactory;
        List populateHostAddresses = populateHostAddresses();
        SocketFactory socketFactory2 = this.config.getSocketFactory();
        if (socketFactory2 == null) {
            socketFactory = SocketFactory.getDefault();
        } else {
            socketFactory = socketFactory2;
        }
        for (HostAddress hostAddress : this.hostAddresses) {
            String fqdn = hostAddress.getFQDN();
            int port = hostAddress.getPort();
            this.socket = socketFactory.createSocket();
            Iterator it;
            try {
                it = Arrays.asList(InetAddress.getAllByName(fqdn)).iterator();
                if (it.hasNext()) {
                    while (it.hasNext()) {
                        InetAddress inetAddress = (InetAddress) it.next();
                        String str = inetAddress + " at port " + port;
                        LOGGER.finer("Trying to establish TCP connection to " + str);
                        this.socket.connect(new InetSocketAddress(inetAddress, port), this.config.getConnectTimeout());
                        LOGGER.finer("Established TCP connection to " + str);
                        this.host = fqdn;
                        this.port = port;
                        return;
                    }
                    continue;
                } else {
                    LOGGER.warning("InetAddress.getAllByName() returned empty result array.");
                    throw new UnknownHostException(fqdn);
                }
            } catch (Exception e) {
                if (!it.hasNext()) {
                    throw e;
                }
            } catch (Exception e2) {
                hostAddress.setException(e2);
                populateHostAddresses.add(hostAddress);
            }
        }
        throw ConnectionException.from(populateHostAddresses);
    }

    private void initConnection() {
        Object obj = (this.packetReader == null || this.packetWriter == null) ? 1 : null;
        this.compressionHandler = null;
        initReaderAndWriter();
        if (obj != null) {
            this.packetWriter = new PacketWriter();
            this.packetReader = new PacketReader();
            if (this.config.isDebuggerEnabled()) {
                addAsyncStanzaListener(this.debugger.getReaderListener(), null);
                if (this.debugger.getWriterListener() != null) {
                    addPacketSendingListener(this.debugger.getWriterListener(), null);
                }
            }
        }
        this.packetWriter.init();
        this.packetReader.init();
        if (obj != null) {
            for (ConnectionCreationListener connectionCreated : AbstractXMPPConnection.getConnectionCreationListeners()) {
                connectionCreated.connectionCreated(this);
            }
        }
    }

    private void initReaderAndWriter() {
        InputStream inputStream = this.socket.getInputStream();
        OutputStream outputStream = this.socket.getOutputStream();
        if (this.compressionHandler != null) {
            inputStream = this.compressionHandler.getInputStream(inputStream);
            outputStream = this.compressionHandler.getOutputStream(outputStream);
        }
        this.writer = new OutputStreamWriter(outputStream, StringUtils.UTF8);
        this.reader = new BufferedReader(new InputStreamReader(inputStream, StringUtils.UTF8));
        initDebugger();
    }

    private void proceedTLSReceived() {
        KeyManager[] keyManagerArr;
        SSLContext instance;
        SSLContext customSSLContext = this.config.getCustomSSLContext();
        if (this.config.getCallbackHandler() == null) {
            keyManagerArr = null;
        } else if (customSSLContext == null) {
            PasswordCallback passwordCallback;
            KeyStore keyStore;
            if (this.config.getKeystoreType().equals("NONE")) {
                passwordCallback = null;
                keyStore = null;
            } else if (this.config.getKeystoreType().equals("PKCS11")) {
                try {
                    Constructor constructor = Class.forName("sun.security.pkcs11.SunPKCS11").getConstructor(new Class[]{InputStream.class});
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(("name = SmartCard\nlibrary = " + this.config.getPKCS11Library()).getBytes());
                    Provider provider = (Provider) constructor.newInstance(new Object[]{byteArrayInputStream});
                    Security.addProvider(provider);
                    keyStore = KeyStore.getInstance("PKCS11", provider);
                    passwordCallback = new PasswordCallback("PKCS11 Password: ", false);
                    this.config.getCallbackHandler().handle(new Callback[]{passwordCallback});
                    keyStore.load(null, passwordCallback.getPassword());
                } catch (Exception e) {
                    passwordCallback = null;
                    keyStore = null;
                }
            } else if (this.config.getKeystoreType().equals("Apple")) {
                KeyStore instance2 = KeyStore.getInstance("KeychainStore", "Apple");
                instance2.load(null, null);
                keyStore = instance2;
                passwordCallback = null;
            } else {
                keyStore = KeyStore.getInstance(this.config.getKeystoreType());
                try {
                    passwordCallback = new PasswordCallback("Keystore Password: ", false);
                    this.config.getCallbackHandler().handle(new Callback[]{passwordCallback});
                    keyStore.load(new FileInputStream(this.config.getKeystorePath()), passwordCallback.getPassword());
                } catch (Exception e2) {
                    passwordCallback = null;
                    keyStore = null;
                }
            }
            KeyManagerFactory instance3 = KeyManagerFactory.getInstance("SunX509");
            if (passwordCallback == null) {
                try {
                    instance3.init(keyStore, null);
                } catch (NullPointerException e3) {
                    keyManagerArr = null;
                }
            } else {
                instance3.init(keyStore, passwordCallback.getPassword());
                passwordCallback.clearPassword();
            }
            keyManagerArr = instance3.getKeyManagers();
        } else {
            keyManagerArr = null;
        }
        if (customSSLContext == null) {
            instance = SSLContext.getInstance(TLSUtils.TLS);
            instance.init(keyManagerArr, null, new SecureRandom());
        } else {
            instance = customSSLContext;
        }
        Socket socket = this.socket;
        this.socket = instance.getSocketFactory().createSocket(socket, this.host, socket.getPort(), true);
        initReaderAndWriter();
        SSLSocket sSLSocket = (SSLSocket) this.socket;
        TLSUtils.setEnabledProtocolsAndCiphers(sSLSocket, this.config.getEnabledSSLProtocols(), this.config.getEnabledSSLCiphers());
        sSLSocket.startHandshake();
        HostnameVerifier hostnameVerifier = getConfiguration().getHostnameVerifier();
        if (hostnameVerifier == null) {
            throw new IllegalStateException("No HostnameVerifier set. Use connectionConfiguration.setHostnameVerifier() to configure.");
        } else if (hostnameVerifier.verify(getServiceName(), sSLSocket.getSession())) {
            this.usingTLS = true;
        } else {
            throw new CertificateException("Hostname verification of certificate failed. Certificate does not authenticate " + getServiceName());
        }
    }

    private XMPPInputOutputStream maybeGetCompressionHandler() {
        Feature feature = (Feature) getFeature(Feature.ELEMENT, Compressed.NAMESPACE);
        if (feature == null) {
            return null;
        }
        for (XMPPInputOutputStream xMPPInputOutputStream : SmackConfiguration.getCompresionHandlers()) {
            if (feature.getMethods().contains(xMPPInputOutputStream.getCompressionMethod())) {
                return xMPPInputOutputStream;
            }
        }
        return null;
    }

    public boolean isUsingCompression() {
        return this.compressionHandler != null && this.compressSyncPoint.wasSuccessful();
    }

    private void useCompression() {
        this.maybeCompressFeaturesReceived.checkIfSuccessOrWait();
        XMPPInputOutputStream maybeGetCompressionHandler = maybeGetCompressionHandler();
        this.compressionHandler = maybeGetCompressionHandler;
        if (maybeGetCompressionHandler != null) {
            this.compressSyncPoint.sendAndWaitForResponseOrThrow(new Compress(this.compressionHandler.getCompressionMethod()));
        } else {
            LOGGER.warning("Could not enable compression because no matching handler/method pair was found");
        }
    }

    protected void connectInternal() {
        connectUsingConfiguration();
        this.socketClosed = false;
        initConnection();
        this.saslFeatureReceived.checkIfSuccessOrWaitOrThrow();
        this.connected = true;
        callConnectionConnectedListener();
        if (this.wasAuthenticated) {
            login();
            notifyReconnection();
        }
    }

    private synchronized void notifyConnectionError(Exception exception) {
        if (!((this.packetReader == null || this.packetReader.done) && (this.packetWriter == null || this.packetWriter.done()))) {
            instantShutdown();
            callConnectionClosedOnErrorListener(exception);
        }
    }

    protected void setWriter(Writer writer) {
        this.writer = writer;
    }

    protected void afterFeaturesReceived() {
        StartTls startTls = (StartTls) getFeature(StartTls.ELEMENT, StartTls.NAMESPACE);
        if (startTls != null) {
            if (startTls.required() && this.config.getSecurityMode() == SecurityMode.disabled) {
                notifyConnectionError(new SecurityRequiredByServerException());
                return;
            } else if (this.config.getSecurityMode() != SecurityMode.disabled) {
                send(new StartTls());
            }
        }
        if (!isSecureConnection() && startTls == null && getConfiguration().getSecurityMode() == SecurityMode.required) {
            throw new SecurityRequiredByClientException();
        } else if (getSASLAuthentication().authenticationSuccessful()) {
            this.maybeCompressFeaturesReceived.reportSuccess();
        }
    }

    void openStream() {
        CharSequence serviceName = getServiceName();
        CharSequence charSequence = null;
        CharSequence username = this.config.getUsername();
        if (username != null) {
            charSequence = XmppStringUtils.m13440a(username, serviceName);
        }
        send(new StreamOpen(serviceName, charSequence, getStreamId()));
        try {
            this.packetReader.parser = PacketParserUtils.newXmppParser(this.reader);
        } catch (Throwable e) {
            throw new SmackException(e);
        }
    }

    public static void setUseStreamManagementDefault(boolean z) {
        useSmDefault = z;
    }

    @Deprecated
    public static void setUseStreamManagementResumptiodDefault(boolean z) {
        setUseStreamManagementResumptionDefault(z);
    }

    public static void setUseStreamManagementResumptionDefault(boolean z) {
        if (z) {
            setUseStreamManagementDefault(z);
        }
        useSmResumptionDefault = z;
    }

    public void setUseStreamManagement(boolean z) {
        this.useSm = z;
    }

    public void setUseStreamManagementResumption(boolean z) {
        if (z) {
            setUseStreamManagement(z);
        }
        this.useSmResumption = z;
    }

    public void setPreferredResumptionTime(int i) {
        this.smClientMaxResumptionTime = i;
    }

    public boolean addRequestAckPredicate(StanzaFilter stanzaFilter) {
        boolean add;
        synchronized (this.requestAckPredicates) {
            add = this.requestAckPredicates.add(stanzaFilter);
        }
        return add;
    }

    public boolean removeRequestAckPredicate(StanzaFilter stanzaFilter) {
        boolean remove;
        synchronized (this.requestAckPredicates) {
            remove = this.requestAckPredicates.remove(stanzaFilter);
        }
        return remove;
    }

    public void removeAllRequestAckPredicates() {
        synchronized (this.requestAckPredicates) {
            this.requestAckPredicates.clear();
        }
    }

    public void requestSmAcknowledgement() {
        if (isSmEnabled()) {
            requestSmAcknowledgementInternal();
            return;
        }
        throw new StreamManagementNotEnabledException();
    }

    private void requestSmAcknowledgementInternal() {
        this.packetWriter.sendStreamElement(AckRequest.INSTANCE);
    }

    public void sendSmAcknowledgement() {
        if (isSmEnabled()) {
            sendSmAcknowledgementInternal();
            return;
        }
        throw new StreamManagementNotEnabledException();
    }

    private void sendSmAcknowledgementInternal() {
        this.packetWriter.sendStreamElement(new AckAnswer(this.clientHandledStanzasCount));
    }

    public void addStanzaAcknowledgedListener(StanzaListener stanzaListener) {
        this.stanzaAcknowledgedListeners.add(stanzaListener);
    }

    public boolean removeStanzaAcknowledgedListener(StanzaListener stanzaListener) {
        return this.stanzaAcknowledgedListeners.remove(stanzaListener);
    }

    public void removeAllStanzaAcknowledgedListeners() {
        this.stanzaAcknowledgedListeners.clear();
    }

    public StanzaListener addStanzaIdAcknowledgedListener(String str, StanzaListener stanzaListener) {
        if (this.smWasEnabledAtLeastOnce) {
            schedule(new C10192(str), (long) Math.min(getMaxSmResumptionTime(), 43200), TimeUnit.SECONDS);
            return (StanzaListener) this.stanzaIdAcknowledgedListeners.put(str, stanzaListener);
        }
        throw new StreamManagementNotEnabledException();
    }

    public StanzaListener removeStanzaIdAcknowledgedListener(String str) {
        return (StanzaListener) this.stanzaIdAcknowledgedListeners.remove(str);
    }

    public void removeAllStanzaIdAcknowledgedListeners() {
        this.stanzaIdAcknowledgedListeners.clear();
    }

    public boolean isSmAvailable() {
        return hasFeature(StreamManagementFeature.ELEMENT, StreamManagement.NAMESPACE);
    }

    public boolean isSmEnabled() {
        return this.smEnabledSyncPoint.wasSuccessful();
    }

    public boolean streamWasResumed() {
        return this.smResumedSyncPoint.wasSuccessful();
    }

    public boolean isDisconnectedButSmResumptionPossible() {
        return this.disconnectedButResumeable && isSmResumptionPossible();
    }

    public boolean isSmResumptionPossible() {
        if (this.smSessionId == null) {
            return false;
        }
        Long l = this.packetWriter.shutdownTimestamp;
        if (l == null) {
            return true;
        }
        if (System.currentTimeMillis() <= l.longValue() + (((long) getMaxSmResumptionTime()) * 1000)) {
            return true;
        }
        return false;
    }

    private void dropSmState() {
        this.smSessionId = null;
        this.unacknowledgedStanzas = null;
    }

    public int getMaxSmResumptionTime() {
        int i;
        int i2 = Integer.MAX_VALUE;
        if (this.smClientMaxResumptionTime > 0) {
            i = this.smClientMaxResumptionTime;
        } else {
            i = Integer.MAX_VALUE;
        }
        if (this.smServerMaxResumptimTime > 0) {
            i2 = this.smServerMaxResumptimTime;
        }
        return Math.min(i, i2);
    }

    private void processHandledCount(long j) {
        Object obj;
        long calculateDelta = SMUtils.calculateDelta(j, this.serverHandledStanzasCount);
        List<Stanza> arrayList = new ArrayList(calculateDelta <= 2147483647L ? (int) calculateDelta : Integer.MAX_VALUE);
        for (long j2 = 0; j2 < calculateDelta; j2++) {
            Stanza stanza = (Stanza) this.unacknowledgedStanzas.poll();
            if (stanza == null) {
                throw new StreamManagementCounterError(j, this.serverHandledStanzasCount, calculateDelta, arrayList);
            }
            arrayList.add(stanza);
        }
        if (this.stanzaAcknowledgedListeners.isEmpty()) {
            for (Stanza stanza2 : arrayList) {
                String stanzaId = stanza2.getStanzaId();
                if (stanzaId != null && this.stanzaIdAcknowledgedListeners.containsKey(stanzaId)) {
                    int i = 1;
                    break;
                }
            }
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            asyncGo(new C10203(arrayList));
        }
        this.serverHandledStanzasCount = j;
    }

    public static void setDefaultBundleAndDeferCallback(BundleAndDeferCallback bundleAndDeferCallback) {
        defaultBundleAndDeferCallback = bundleAndDeferCallback;
    }

    public void setBundleandDeferCallback(BundleAndDeferCallback bundleAndDeferCallback) {
        this.bundleAndDeferCallback = bundleAndDeferCallback;
    }
}
