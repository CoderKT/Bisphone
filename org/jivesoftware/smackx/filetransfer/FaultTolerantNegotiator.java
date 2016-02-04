package org.jivesoftware.smackx.filetransfer;

import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.si.packet.StreamInitiation;

public class FaultTolerantNegotiator extends StreamNegotiator {
    private final XMPPConnection connection;
    private final StreamNegotiator primaryNegotiator;
    private final StreamNegotiator secondaryNegotiator;

    public FaultTolerantNegotiator(XMPPConnection xMPPConnection, StreamNegotiator streamNegotiator, StreamNegotiator streamNegotiator2) {
        this.primaryNegotiator = streamNegotiator;
        this.secondaryNegotiator = streamNegotiator2;
        this.connection = xMPPConnection;
    }

    public void newStreamInitiation(String str, String str2) {
        this.primaryNegotiator.newStreamInitiation(str, str2);
        this.secondaryNegotiator.newStreamInitiation(str, str2);
    }

    InputStream negotiateIncomingStream(Stanza stanza) {
        throw new UnsupportedOperationException("Negotiation only handled by create incoming stream method.");
    }

    public InputStream createIncomingStream(StreamInitiation streamInitiation) {
        Stanza initiateIncomingStream = initiateIncomingStream(this.connection, streamInitiation);
        try {
            return determineNegotiator(initiateIncomingStream).negotiateIncomingStream(initiateIncomingStream);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private StreamNegotiator determineNegotiator(Stanza stanza) {
        if (stanza instanceof Bytestream) {
            return this.primaryNegotiator;
        }
        if (stanza instanceof Open) {
            return this.secondaryNegotiator;
        }
        throw new IllegalStateException("Unknown stream initation type");
    }

    public OutputStream createOutgoingStream(String str, String str2, String str3) {
        try {
            return this.primaryNegotiator.createOutgoingStream(str, str2, str3);
        } catch (Exception e) {
            return this.secondaryNegotiator.createOutgoingStream(str, str2, str3);
        }
    }

    public String[] getNamespaces() {
        Object namespaces = this.primaryNegotiator.getNamespaces();
        Object namespaces2 = this.secondaryNegotiator.getNamespaces();
        Object obj = new String[(namespaces.length + namespaces2.length)];
        System.arraycopy(namespaces, 0, obj, 0, namespaces.length);
        System.arraycopy(namespaces2, 0, obj, namespaces.length, namespaces2.length);
        return obj;
    }
}
