package org.jivesoftware.smack.compress.packet;

import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.FullStreamElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class Compress extends FullStreamElement {
    public static final String ELEMENT = "compress";
    public static final String NAMESPACE = "http://jabber.org/protocol/compress";
    public final String method;

    public class Feature implements ExtensionElement {
        public static final String ELEMENT = "compression";
        public final List<String> methods;

        public Feature(List<String> list) {
            this.methods = list;
        }

        public List<String> getMethods() {
            return Collections.unmodifiableList(this.methods);
        }

        public String getNamespace() {
            return Compress.NAMESPACE;
        }

        public String getElementName() {
            return ELEMENT;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
            xmlStringBuilder.rightAngleBracket();
            for (String element : this.methods) {
                xmlStringBuilder.element("method", element);
            }
            xmlStringBuilder.closeElement((NamedElement) this);
            return xmlStringBuilder;
        }
    }

    public Compress(String str) {
        this.method = str;
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
        xmlStringBuilder.element("method", this.method);
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }
}
