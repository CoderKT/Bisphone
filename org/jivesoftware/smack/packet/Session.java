package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class Session extends SimpleIQ {
    public static final String ELEMENT = "session";
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-session";

    public class Feature implements ExtensionElement {
        public static final String OPTIONAL_ELEMENT = "optional";
        private final boolean optional;

        public Feature(boolean z) {
            this.optional = z;
        }

        public boolean isOptional() {
            return this.optional;
        }

        public String getElementName() {
            return Session.ELEMENT;
        }

        public String getNamespace() {
            return Session.NAMESPACE;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
            if (this.optional) {
                xmlStringBuilder.rightAngleBracket();
                xmlStringBuilder.emptyElement(OPTIONAL_ELEMENT);
                xmlStringBuilder.closeElement((NamedElement) this);
            } else {
                xmlStringBuilder.closeEmptyElement();
            }
            return xmlStringBuilder;
        }
    }

    public Session() {
        super(ELEMENT, NAMESPACE);
        setType(Type.set);
    }
}
