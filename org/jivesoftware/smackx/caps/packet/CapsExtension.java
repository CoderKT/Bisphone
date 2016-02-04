package org.jivesoftware.smackx.caps.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.packet.RosterVer;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class CapsExtension implements ExtensionElement {
    public static final String ELEMENT = "c";
    public static final String NAMESPACE = "http://jabber.org/protocol/caps";
    private final String hash;
    private final String node;
    private final String ver;

    public CapsExtension(String str, String str2, String str3) {
        this.node = str;
        this.ver = str2;
        this.hash = str3;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public String getNode() {
        return this.node;
    }

    public String getVer() {
        return this.ver;
    }

    public String getHash() {
        return this.hash;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.attribute("hash", this.hash).attribute("node", this.node).attribute(RosterVer.ELEMENT, this.ver);
        xmlStringBuilder.closeEmptyElement();
        return xmlStringBuilder;
    }

    public static CapsExtension from(Stanza stanza) {
        return (CapsExtension) stanza.getExtension(ELEMENT, NAMESPACE);
    }
}
