package org.jivesoftware.smackx.shim.packet;

import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class HeadersExtension implements ExtensionElement {
    public static final String ELEMENT = "headers";
    public static final String NAMESPACE = "http://jabber.org/protocol/shim";
    private final List<Header> headers;

    public HeadersExtension(List<Header> list) {
        if (list != null) {
            this.headers = Collections.unmodifiableList(list);
        } else {
            this.headers = Collections.emptyList();
        }
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.append(this.headers);
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }

    public static HeadersExtension from(Stanza stanza) {
        return (HeadersExtension) stanza.getExtension(ELEMENT, NAMESPACE);
    }
}
