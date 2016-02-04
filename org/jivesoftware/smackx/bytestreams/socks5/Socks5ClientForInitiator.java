package org.jivesoftware.smackx.bytestreams.socks5;

import java.lang.ref.WeakReference;
import java.net.Socket;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost;

class Socks5ClientForInitiator extends Socks5Client {
    private WeakReference<XMPPConnection> connection;
    private String sessionID;
    private String target;

    public Socks5ClientForInitiator(StreamHost streamHost, String str, XMPPConnection xMPPConnection, String str2, String str3) {
        super(streamHost, str);
        this.connection = new WeakReference(xMPPConnection);
        this.sessionID = str2;
        this.target = str3;
    }

    public Socket getSocket(int i) {
        Socket socket;
        if (this.streamHost.getJID().equals(((XMPPConnection) this.connection.get()).getUser())) {
            socket = Socks5Proxy.getSocks5Proxy().getSocket(this.digest);
            if (socket == null) {
                throw new SmackException("target is not connected to SOCKS5 proxy");
            }
        }
        socket = super.getSocket(i);
        try {
            activate();
        } catch (XMPPException e) {
            socket.close();
            throw e;
        } catch (NoResponseException e2) {
            socket.close();
            throw e2;
        }
        return socket;
    }

    private void activate() {
        ((XMPPConnection) this.connection.get()).createPacketCollectorAndSend(createStreamHostActivation()).nextResultOrThrow();
    }

    private Bytestream createStreamHostActivation() {
        Bytestream bytestream = new Bytestream(this.sessionID);
        bytestream.setMode(null);
        bytestream.setType(Type.set);
        bytestream.setTo(this.streamHost.getJID());
        bytestream.setToActivate(this.target);
        return bytestream;
    }
}
