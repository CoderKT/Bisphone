package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.util.StringUtils;

public class OnceForThisStanza implements StanzaFilter {
    private final XMPPTCPConnection connection;
    private final String id;

    public static void setup(XMPPTCPConnection xMPPTCPConnection, Stanza stanza) {
        xMPPTCPConnection.addRequestAckPredicate(new OnceForThisStanza(xMPPTCPConnection, stanza));
    }

    private OnceForThisStanza(XMPPTCPConnection xMPPTCPConnection, Stanza stanza) {
        this.connection = xMPPTCPConnection;
        this.id = stanza.getStanzaId();
        if (StringUtils.isNullOrEmpty(this.id)) {
            throw new IllegalArgumentException("Stanza ID must be set");
        }
    }

    public boolean accept(Stanza stanza) {
        CharSequence stanzaId = stanza.getStanzaId();
        if (StringUtils.isNullOrEmpty(stanzaId) || !this.id.equals(stanzaId)) {
            return false;
        }
        this.connection.removeRequestAckPredicate(this);
        return true;
    }
}
