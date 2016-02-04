package org.jivesoftware.smackx.pep.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;

public abstract class PEPItem implements ExtensionElement {
    String id;

    abstract String getItemDetailsXML();

    abstract String getNode();

    public PEPItem(String str) {
        this.id = str;
    }

    public String getElementName() {
        return Item.ELEMENT;
    }

    public String getNamespace() {
        return PubSub.NAMESPACE;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" id=\"").append(this.id).append("\">");
        stringBuilder.append(getItemDetailsXML());
        stringBuilder.append("</").append(getElementName()).append(">");
        return stringBuilder.toString();
    }
}
