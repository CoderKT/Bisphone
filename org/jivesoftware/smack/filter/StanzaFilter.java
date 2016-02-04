package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Stanza;

public interface StanzaFilter {
    boolean accept(Stanza stanza);
}
