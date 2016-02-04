package org.jivesoftware.smackx.shim.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class Header implements ExtensionElement {
    public static final String ELEMENT = "header";
    private final String name;
    private final String value;

    public Header(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return HeadersExtension.NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
        xmlStringBuilder.attribute("name", this.name);
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.escape(this.value);
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }
}
