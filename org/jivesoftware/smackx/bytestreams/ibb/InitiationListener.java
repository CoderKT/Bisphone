package org.jivesoftware.smackx.bytestreams.ibb;

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
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.filetransfer.StreamNegotiator;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement.OpenValidateElement;

class InitiationListener extends AbstractIqRequestHandler {
    private static final Logger LOGGER;
    private final ExecutorService initiationListenerExecutor;
    private final InBandBytestreamManager manager;

    /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InitiationListener.1 */
    class C10401 implements Runnable {
        final /* synthetic */ IQ val$packet;

        C10401(IQ iq) {
            this.val$packet = iq;
        }

        public void run() {
            try {
                InitiationListener.this.processRequest(this.val$packet);
            } catch (Throwable e) {
                InitiationListener.LOGGER.log(Level.WARNING, "proccessRequest", e);
            }
        }
    }

    static {
        LOGGER = Logger.getLogger(InitiationListener.class.getName());
    }

    protected InitiationListener(InBandBytestreamManager inBandBytestreamManager) {
        super(OpenValidateElement.METHOD, Open.NAMESPACE, Type.set, Mode.async);
        this.manager = inBandBytestreamManager;
        this.initiationListenerExecutor = Executors.newCachedThreadPool();
    }

    public IQ handleIQRequest(IQ iq) {
        this.initiationListenerExecutor.execute(new C10401(iq));
        return null;
    }

    private void processRequest(Stanza stanza) {
        Open open = (Open) stanza;
        if (open.getBlockSize() > this.manager.getMaximumBlockSize()) {
            this.manager.replyResourceConstraintPacket(open);
            return;
        }
        StreamNegotiator.signal(open.getFrom() + '\t' + open.getSessionID(), open);
        if (!this.manager.getIgnoredBytestreamRequests().remove(open.getSessionID())) {
            BytestreamRequest inBandBytestreamRequest = new InBandBytestreamRequest(this.manager, open);
            BytestreamListener userListener = this.manager.getUserListener(open.getFrom());
            if (userListener != null) {
                userListener.incomingBytestreamRequest(inBandBytestreamRequest);
            } else if (this.manager.getAllRequestListeners().isEmpty()) {
                this.manager.replyRejectPacket(open);
            } else {
                for (BytestreamListener userListener2 : this.manager.getAllRequestListeners()) {
                    userListener2.incomingBytestreamRequest(inBandBytestreamRequest);
                }
            }
        }
    }

    protected void shutdown() {
        this.initiationListenerExecutor.shutdownNow();
    }
}
