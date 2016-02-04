package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;

public class StanzaIdFilter implements StanzaFilter {
    private final String stanzaId;

    public StanzaIdFilter(Stanza stanza) {
        this(stanza.getStanzaId());
    }

    public StanzaIdFilter(String str) {
        this.stanzaId = (String) StringUtils.requireNotNullOrEmpty(str, "Stanza ID must not be null or empty.");
    }

    public boolean accept(Stanza stanza) {
        return this.stanzaId.equals(stanza.getStanzaId());
    }

    public String toString() {
        return getClass().getSimpleName() + ": id=" + this.stanzaId;
    }
}
