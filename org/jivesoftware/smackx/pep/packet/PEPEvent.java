package org.jivesoftware.smackx.pep.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.pubsub.packet.PubSub;

public class PEPEvent implements ExtensionElement {
    PEPItem item;

    public PEPEvent(PEPItem pEPItem) {
        this.item = pEPItem;
    }

    public void addPEPItem(PEPItem pEPItem) {
        this.item = pEPItem;
    }

    public String getElementName() {
        return "event";
    }

    public String getNamespace() {
        return PubSub.NAMESPACE;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\">");
        stringBuilder.append(this.item.toXML());
        stringBuilder.append("</").append(getElementName()).append(">");
        return stringBuilder.toString();
    }
}
