package org.jivesoftware.smackx.muc.packet;

import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class Destroy implements NamedElement {
    public static final String ELEMENT = "destroy";
    private String jid;
    private String reason;

    public String getJid() {
        return this.jid;
    }

    public String getReason() {
        return this.reason;
    }

    public void setJid(String str) {
        this.jid = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
        xmlStringBuilder.optAttribute("jid", getJid());
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.optElement("reason", getReason());
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }

    public String getElementName() {
        return ELEMENT;
    }
}
