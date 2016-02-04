package org.jivesoftware.smackx.bytestreams.socks5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamRequest;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.filetransfer.StreamNegotiator;
import org.jivesoftware.smackx.search.UserSearch;

final class InitiationListener extends AbstractIqRequestHandler {
    private static final Logger LOGGER;
    private final ExecutorService initiationListenerExecutor;
    private final Socks5BytestreamManager manager;

    /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.InitiationListener.1 */
    class C10411 implements Runnable {
        final /* synthetic */ IQ val$packet;

        C10411(IQ iq) {
            this.val$packet = iq;
        }

        public void run() {
            try {
                InitiationListener.this.processRequest(this.val$packet);
            } catch (Throwable e) {
                InitiationListener.LOGGER.log(Level.WARNING, "process request", e);
            }
        }
    }

    static {
        LOGGER = Logger.getLogger(InitiationListener.class.getName());
    }

    protected InitiationListener(Socks5BytestreamManager socks5BytestreamManager) {
        super(UserSearch.ELEMENT, Bytestream.NAMESPACE, Type.set, Mode.async);
        this.manager = socks5BytestreamManager;
        this.initiationListenerExecutor = Executors.newCachedThreadPool();
    }

    public IQ handleIQRequest(IQ iq) {
        this.initiationListenerExecutor.execute(new C10411(iq));
        return null;
    }

    private void processRequest(Stanza stanza) {
        Bytestream bytestream = (Bytestream) stanza;
        StreamNegotiator.signal(bytestream.getFrom() + '\t' + bytestream.getSessionID(), bytestream);
        if (!this.manager.getIgnoredBytestreamRequests().remove(bytestream.getSessionID())) {
            BytestreamRequest socks5BytestreamRequest = new Socks5BytestreamRequest(this.manager, bytestream);
            BytestreamListener userListener = this.manager.getUserListener(bytestream.getFrom());
            if (userListener != null) {
                userListener.incomingBytestreamRequest(socks5BytestreamRequest);
            } else if (this.manager.getAllRequestListeners().isEmpty()) {
                this.manager.replyRejectPacket(bytestream);
            } else {
                for (BytestreamListener userListener2 : this.manager.getAllRequestListeners()) {
                    userListener2.incomingBytestreamRequest(socks5BytestreamRequest);
                }
            }
        }
    }

    protected void shutdown() {
        this.initiationListenerExecutor.shutdownNow();
    }
}
