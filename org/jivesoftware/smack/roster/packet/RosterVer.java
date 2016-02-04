package org.jivesoftware.smack.roster.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class RosterVer implements ExtensionElement {
    public static final String ELEMENT = "ver";
    public static final RosterVer INSTANCE;
    public static final String NAMESPACE = "urn:xmpp:features:rosterver";

    static {
        INSTANCE = new RosterVer();
    }

    private RosterVer() {
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.closeEmptyElement();
        return xmlStringBuilder;
    }
}
