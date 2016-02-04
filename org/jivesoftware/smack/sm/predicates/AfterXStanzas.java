package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

public class AfterXStanzas implements StanzaFilter {
    final int count;
    int currentCount;

    public AfterXStanzas(int i) {
        this.count = i;
        this.currentCount = 0;
    }

    public synchronized boolean accept(Stanza stanza) {
        boolean z;
        this.currentCount++;
        if (this.currentCount == this.count) {
            resetCounter();
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized void resetCounter() {
        this.currentCount = 0;
    }
}
