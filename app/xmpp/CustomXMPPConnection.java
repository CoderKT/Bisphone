package app.xmpp;

import java.util.Set;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class CustomXMPPConnection extends XMPPTCPConnection {
    public CustomXMPPConnection(XMPPTCPConnectionConfiguration xMPPTCPConnectionConfiguration) {
        super(xMPPTCPConnectionConfiguration);
    }

    public Set<ConnectionListener> m7310a() {
        return this.connectionListeners;
    }
}
