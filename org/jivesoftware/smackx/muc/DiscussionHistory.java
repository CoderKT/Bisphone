package org.jivesoftware.smackx.muc;

import java.util.Date;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History;

public class DiscussionHistory {
    private int maxChars;
    private int maxStanzas;
    private int seconds;
    private Date since;

    public DiscussionHistory() {
        this.maxChars = -1;
        this.maxStanzas = -1;
        this.seconds = -1;
    }

    public int getMaxChars() {
        return this.maxChars;
    }

    public int getMaxStanzas() {
        return this.maxStanzas;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public Date getSince() {
        return this.since;
    }

    public void setMaxChars(int i) {
        this.maxChars = i;
    }

    public void setMaxStanzas(int i) {
        this.maxStanzas = i;
    }

    public void setSeconds(int i) {
        this.seconds = i;
    }

    public void setSince(Date date) {
        this.since = date;
    }

    private boolean isConfigured() {
        return this.maxChars > -1 || this.maxStanzas > -1 || this.seconds > -1 || this.since != null;
    }

    History getMUCHistory() {
        if (!isConfigured()) {
            return null;
        }
        History history = new History();
        if (this.maxChars > -1) {
            history.setMaxChars(this.maxChars);
        }
        if (this.maxStanzas > -1) {
            history.setMaxStanzas(this.maxStanzas);
        }
        if (this.seconds > -1) {
            history.setSeconds(this.seconds);
        }
        if (this.since == null) {
            return history;
        }
        history.setSince(this.since);
        return history;
    }
}
