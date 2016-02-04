package org.jivesoftware.smackx.muc.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.muc.MUCAffiliation;
import org.jivesoftware.smackx.muc.MUCRole;
import org.jivesoftware.smackx.nick.packet.Nick;

public class MUCItem implements NamedElement {
    public static final String ELEMENT = "item";
    private final String actor;
    private final MUCAffiliation affiliation;
    private final String jid;
    private final String nick;
    private final String reason;
    private final MUCRole role;

    public MUCItem(MUCAffiliation mUCAffiliation) {
        this(mUCAffiliation, null, null, null, null, null);
    }

    public MUCItem(MUCRole mUCRole) {
        this(null, mUCRole, null, null, null, null);
    }

    public MUCItem(MUCRole mUCRole, String str) {
        this(null, mUCRole, null, null, null, str);
    }

    public MUCItem(MUCAffiliation mUCAffiliation, String str, String str2) {
        this(mUCAffiliation, null, null, str2, str, null);
    }

    public MUCItem(MUCAffiliation mUCAffiliation, String str) {
        this(mUCAffiliation, null, null, null, str, null);
    }

    public MUCItem(MUCRole mUCRole, String str, String str2) {
        this(null, mUCRole, null, str2, null, str);
    }

    public MUCItem(MUCAffiliation mUCAffiliation, MUCRole mUCRole, String str, String str2, String str3, String str4) {
        this.affiliation = mUCAffiliation;
        this.role = mUCRole;
        this.actor = str;
        this.reason = str2;
        this.jid = str3;
        this.nick = str4;
    }

    public String getActor() {
        return this.actor;
    }

    public String getReason() {
        return this.reason;
    }

    public MUCAffiliation getAffiliation() {
        return this.affiliation;
    }

    public String getJid() {
        return this.jid;
    }

    public String getNick() {
        return this.nick;
    }

    public MUCRole getRole() {
        return this.role;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
        xmlStringBuilder.optAttribute("affiliation", getAffiliation());
        xmlStringBuilder.optAttribute("jid", getJid());
        xmlStringBuilder.optAttribute(Nick.ELEMENT_NAME, getNick());
        xmlStringBuilder.optAttribute("role", getRole());
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.optElement("reason", getReason());
        if (getActor() != null) {
            xmlStringBuilder.halfOpenElement("actor").attribute("jid", getActor()).closeEmptyElement();
        }
        xmlStringBuilder.closeElement(ELEMENT);
        return xmlStringBuilder;
    }

    public String getElementName() {
        return ELEMENT;
    }
}
