package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.EventManger;
import org.jivesoftware.smack.util.EventManger.Callback;
import org.jivesoftware.smackx.si.packet.StreamInitiation;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public abstract class StreamNegotiator {
    protected static final EventManger<String, IQ, NotConnectedException> initationSetEvents;

    /* renamed from: org.jivesoftware.smackx.filetransfer.StreamNegotiator.1 */
    class C10731 implements Callback<NotConnectedException> {
        final /* synthetic */ XMPPConnection val$connection;
        final /* synthetic */ StreamInitiation val$response;

        C10731(XMPPConnection xMPPConnection, StreamInitiation streamInitiation) {
            this.val$connection = xMPPConnection;
            this.val$response = streamInitiation;
        }

        public void action() {
            this.val$connection.sendStanza(this.val$response);
        }
    }

    public abstract InputStream createIncomingStream(StreamInitiation streamInitiation);

    public abstract OutputStream createOutgoingStream(String str, String str2, String str3);

    public abstract String[] getNamespaces();

    abstract InputStream negotiateIncomingStream(Stanza stanza);

    protected abstract void newStreamInitiation(String str, String str2);

    static {
        initationSetEvents = new EventManger();
    }

    protected static StreamInitiation createInitiationAccept(StreamInitiation streamInitiation, String[] strArr) {
        StreamInitiation streamInitiation2 = new StreamInitiation();
        streamInitiation2.setTo(streamInitiation.getFrom());
        streamInitiation2.setFrom(streamInitiation.getTo());
        streamInitiation2.setType(Type.result);
        streamInitiation2.setStanzaId(streamInitiation.getStanzaId());
        DataForm dataForm = new DataForm(DataForm.Type.submit);
        FormField formField = new FormField("stream-method");
        for (String addValue : strArr) {
            formField.addValue(addValue);
        }
        dataForm.addField(formField);
        streamInitiation2.setFeatureNegotiationForm(dataForm);
        return streamInitiation2;
    }

    protected final IQ initiateIncomingStream(XMPPConnection xMPPConnection, StreamInitiation streamInitiation) {
        StreamInitiation createInitiationAccept = createInitiationAccept(streamInitiation, getNamespaces());
        newStreamInitiation(streamInitiation.getFrom(), streamInitiation.getSessionID());
        try {
            IQ iq = (IQ) initationSetEvents.performActionAndWaitForEvent(streamInitiation.getFrom().toString() + '\t' + streamInitiation.getSessionID(), xMPPConnection.getPacketReplyTimeout(), new C10731(xMPPConnection, createInitiationAccept));
            if (iq == null) {
                throw NoResponseException.newWith(xMPPConnection);
            }
            XMPPErrorException.ifHasErrorThenThrow(iq);
            return iq;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static void signal(String str, IQ iq) {
        initationSetEvents.signalEvent(str, iq);
    }
}
