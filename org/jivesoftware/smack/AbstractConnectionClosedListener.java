package org.jivesoftware.smack;

public abstract class AbstractConnectionClosedListener extends AbstractConnectionListener {
    public abstract void connectionTerminated();

    public final void connectionClosed() {
        connectionTerminated();
    }

    public final void connectionClosedOnError(Exception exception) {
        connectionTerminated();
    }
}
