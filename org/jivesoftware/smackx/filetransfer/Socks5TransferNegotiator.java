package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager;
import org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamRequest;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.si.packet.StreamInitiation;

public class Socks5TransferNegotiator extends StreamNegotiator {
    private XMPPConnection connection;
    private Socks5BytestreamManager manager;

    class ByteStreamRequest extends Socks5BytestreamRequest {
        private ByteStreamRequest(Socks5BytestreamManager socks5BytestreamManager, Bytestream bytestream) {
            super(socks5BytestreamManager, bytestream);
        }
    }

    Socks5TransferNegotiator(XMPPConnection xMPPConnection) {
        this.connection = xMPPConnection;
        this.manager = Socks5BytestreamManager.getBytestreamManager(this.connection);
    }

    public OutputStream createOutgoingStream(String str, String str2, String str3) {
        try {
            return this.manager.establishSession(str3, str).getOutputStream();
        } catch (Throwable e) {
            throw new SmackException("error establishing SOCKS5 Bytestream", e);
        } catch (Throwable e2) {
            throw new SmackException("error establishing SOCKS5 Bytestream", e2);
        }
    }

    public InputStream createIncomingStream(StreamInitiation streamInitiation) {
        this.manager.ignoreBytestreamRequestOnce(streamInitiation.getSessionID());
        return negotiateIncomingStream(initiateIncomingStream(this.connection, streamInitiation));
    }

    public void newStreamInitiation(String str, String str2) {
        this.manager.ignoreBytestreamRequestOnce(str2);
    }

    public String[] getNamespaces() {
        return new String[]{Bytestream.NAMESPACE};
    }

    InputStream negotiateIncomingStream(Stanza stanza) {
        try {
            InputStream pushbackInputStream = new PushbackInputStream(new ByteStreamRequest((Bytestream) stanza, null).accept().getInputStream());
            pushbackInputStream.unread(pushbackInputStream.read());
            return pushbackInputStream;
        } catch (Throwable e) {
            throw new SmackException("Error establishing input stream", e);
        }
    }
}
