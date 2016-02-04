package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

public class ForMatchingPredicateOrAfterXStanzas implements StanzaFilter {
    private final AfterXStanzas afterXStanzas;
    private final StanzaFilter predicate;

    public ForMatchingPredicateOrAfterXStanzas(StanzaFilter stanzaFilter, int i) {
        this.predicate = stanzaFilter;
        this.afterXStanzas = new AfterXStanzas(i);
    }

    public boolean accept(Stanza stanza) {
        if (!this.predicate.accept(stanza)) {
            return this.afterXStanzas.accept(stanza);
        }
        this.afterXStanzas.resetCounter();
        return true;
    }
}
