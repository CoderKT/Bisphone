package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

public class ForEveryStanza implements StanzaFilter {
    public static final ForEveryStanza INSTANCE;

    static {
        INSTANCE = new ForEveryStanza();
    }

    private ForEveryStanza() {
    }

    public boolean accept(Stanza stanza) {
        return true;
    }
}
