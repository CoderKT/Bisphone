package org.jivesoftware.smackx.attention.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class AttentionExtension implements ExtensionElement {
    public static final String ELEMENT_NAME = "attention";
    public static final String NAMESPACE = "urn:xmpp:attention:0";

    public class Provider extends ExtensionElementProvider<AttentionExtension> {
        public AttentionExtension parse(XmlPullParser xmlPullParser, int i) {
            return new AttentionExtension();
        }
    }

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\"/>");
        return stringBuilder.toString();
    }
}
