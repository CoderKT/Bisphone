package org.jivesoftware.smackx.nick.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class Nick implements ExtensionElement {
    public static final String ELEMENT_NAME = "nick";
    public static final String NAMESPACE = "http://jabber.org/protocol/nick";
    private String name;

    public class Provider extends ExtensionElementProvider<Nick> {
        public Nick parse(XmlPullParser xmlPullParser, int i) {
            xmlPullParser.next();
            String text = xmlPullParser.getText();
            while (xmlPullParser.getEventType() != 3) {
                xmlPullParser.next();
            }
            return new Nick(text);
        }
    }

    public Nick(String str) {
        this.name = null;
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getElementName() {
        return ELEMENT_NAME;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(ELEMENT_NAME).append(" xmlns=\"").append(NAMESPACE).append("\">");
        stringBuilder.append(getName());
        stringBuilder.append("</").append(ELEMENT_NAME).append('>');
        return stringBuilder.toString();
    }
}
