package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;

@Deprecated
public class PacketTypeFilter implements StanzaFilter {
    public static final PacketTypeFilter MESSAGE;
    public static final PacketTypeFilter PRESENCE;
    private final Class<? extends Stanza> packetType;

    static {
        PRESENCE = new PacketTypeFilter(Presence.class);
        MESSAGE = new PacketTypeFilter(Message.class);
    }

    public PacketTypeFilter(Class<? extends Stanza> cls) {
        this.packetType = cls;
    }

    public boolean accept(Stanza stanza) {
        return this.packetType.isInstance(stanza);
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + this.packetType.getName();
    }
}
