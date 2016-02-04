package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

public class ForEveryMessage implements StanzaFilter {
    public static final ForEveryMessage INSTANCE;

    static {
        INSTANCE = new ForEveryMessage();
    }

    private ForEveryMessage() {
    }

    public boolean accept(Stanza stanza) {
        if (stanza instanceof Message) {
            return true;
        }
        return false;
    }
}
