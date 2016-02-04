package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;

public final class StanzaTypeFilter implements StanzaFilter {
    public static final StanzaTypeFilter IQ;
    public static final StanzaTypeFilter MESSAGE;
    public static final StanzaTypeFilter PRESENCE;
    private final Class<? extends Stanza> packetType;

    static {
        PRESENCE = new StanzaTypeFilter(Presence.class);
        MESSAGE = new StanzaTypeFilter(Message.class);
        IQ = new StanzaTypeFilter(IQ.class);
    }

    public StanzaTypeFilter(Class<? extends Stanza> cls) {
        this.packetType = cls;
    }

    public boolean accept(Stanza stanza) {
        return this.packetType.isInstance(stanza);
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + this.packetType.getName();
    }
}
