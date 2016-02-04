package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamRequest;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamSession;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.si.packet.StreamInitiation;

public class IBBTransferNegotiator extends StreamNegotiator {
    private XMPPConnection connection;
    private InBandBytestreamManager manager;

    class ByteStreamRequest extends InBandBytestreamRequest {
        private ByteStreamRequest(InBandBytestreamManager inBandBytestreamManager, Open open) {
            super(inBandBytestreamManager, open);
        }
    }

    protected IBBTransferNegotiator(XMPPConnection xMPPConnection) {
        this.connection = xMPPConnection;
        this.manager = InBandBytestreamManager.getByteStreamManager(xMPPConnection);
    }

    public OutputStream createOutgoingStream(String str, String str2, String str3) {
        InBandBytestreamSession establishSession = this.manager.establishSession(str3, str);
        establishSession.setCloseBothStreamsEnabled(true);
        return establishSession.getOutputStream();
    }

    public InputStream createIncomingStream(StreamInitiation streamInitiation) {
        this.manager.ignoreBytestreamRequestOnce(streamInitiation.getSessionID());
        return negotiateIncomingStream(initiateIncomingStream(this.connection, streamInitiation));
    }

    public void newStreamInitiation(String str, String str2) {
        this.manager.ignoreBytestreamRequestOnce(str2);
    }

    public String[] getNamespaces() {
        return new String[]{Open.NAMESPACE};
    }

    InputStream negotiateIncomingStream(Stanza stanza) {
        InBandBytestreamSession accept = new ByteStreamRequest((Open) stanza, null).accept();
        accept.setCloseBothStreamsEnabled(true);
        return accept.getInputStream();
    }
}
