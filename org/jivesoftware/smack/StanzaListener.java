package org.jivesoftware.smack;

import org.jivesoftware.smack.packet.Stanza;

public interface StanzaListener {
    void processPacket(Stanza stanza);
}
