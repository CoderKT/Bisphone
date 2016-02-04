package app.events;

public class ConnectionListenerEvent {
    ConnectionType f2408a;

    public enum ConnectionType {
        connecting,
        connected,
        authenticated,
        disconnected,
        waitingForNetwork
    }

    public ConnectionListenerEvent(ConnectionType connectionType) {
        this.f2408a = connectionType;
    }

    public ConnectionType m4846a() {
        return this.f2408a;
    }
}
