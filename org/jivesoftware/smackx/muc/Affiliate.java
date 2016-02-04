package org.jivesoftware.smackx.muc;

import org.jivesoftware.smackx.muc.packet.MUCItem;

public class Affiliate {
    private final MUCAffiliation affiliation;
    private final String jid;
    private final String nick;
    private final MUCRole role;

    Affiliate(MUCItem mUCItem) {
        this.jid = mUCItem.getJid();
        this.affiliation = mUCItem.getAffiliation();
        this.role = mUCItem.getRole();
        this.nick = mUCItem.getNick();
    }

    public String getJid() {
        return this.jid;
    }

    public MUCAffiliation getAffiliation() {
        return this.affiliation;
    }

    public MUCRole getRole() {
        return this.role;
    }

    public String getNick() {
        return this.nick;
    }
}
