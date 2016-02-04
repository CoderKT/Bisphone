package org.jivesoftware.smack;

public class AbstractConnectionListener implements ConnectionListener {
    public void connected(XMPPConnection xMPPConnection) {
    }

    public void authenticated(XMPPConnection xMPPConnection, boolean z) {
    }

    public void connectionClosed() {
    }

    public void connectionClosedOnError(Exception exception) {
    }

    public void reconnectingIn(int i) {
    }

    public void reconnectionFailed(Exception exception) {
    }

    public void reconnectionSuccessful() {
    }
}
