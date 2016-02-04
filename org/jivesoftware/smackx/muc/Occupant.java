package org.jivesoftware.smackx.muc;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.XmppStringUtils;

public class Occupant {
    private final MUCAffiliation affiliation;
    private final String jid;
    private final String nick;
    private final MUCRole role;

    Occupant(MUCItem mUCItem) {
        this.jid = mUCItem.getJid();
        this.affiliation = mUCItem.getAffiliation();
        this.role = mUCItem.getRole();
        this.nick = mUCItem.getNick();
    }

    Occupant(Presence presence) {
        MUCItem item = ((MUCUser) presence.getExtension(DataForm.ELEMENT, MUCUser.NAMESPACE)).getItem();
        this.jid = item.getJid();
        this.affiliation = item.getAffiliation();
        this.role = item.getRole();
        this.nick = XmppStringUtils.m13446c(presence.getFrom());
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

    public boolean equals(Object obj) {
        if (!(obj instanceof Occupant)) {
            return false;
        }
        return this.jid.equals(((Occupant) obj).jid);
    }

    public int hashCode() {
        return (this.nick != null ? this.nick.hashCode() : 0) + (((((this.affiliation.hashCode() * 17) + this.role.hashCode()) * 17) + this.jid.hashCode()) * 17);
    }
}
