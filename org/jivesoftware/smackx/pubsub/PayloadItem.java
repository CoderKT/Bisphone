package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.ExtensionElement;

public class PayloadItem<E extends ExtensionElement> extends Item {
    private E payload;

    public PayloadItem(E e) {
        if (e == null) {
            throw new IllegalArgumentException("payload cannot be 'null'");
        }
        this.payload = e;
    }

    public PayloadItem(String str, E e) {
        super(str);
        if (e == null) {
            throw new IllegalArgumentException("payload cannot be 'null'");
        }
        this.payload = e;
    }

    public PayloadItem(String str, String str2, E e) {
        super(str, str2);
        if (e == null) {
            throw new IllegalArgumentException("payload cannot be 'null'");
        }
        this.payload = e;
    }

    public E getPayload() {
        return this.payload;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder("<item");
        if (getId() != null) {
            stringBuilder.append(" id='");
            stringBuilder.append(getId());
            stringBuilder.append("'");
        }
        if (getNode() != null) {
            stringBuilder.append(" node='");
            stringBuilder.append(getNode());
            stringBuilder.append("'");
        }
        stringBuilder.append(">");
        stringBuilder.append(this.payload.toXML());
        stringBuilder.append("</item>");
        return stringBuilder.toString();
    }

    public String toString() {
        return getClass().getName() + " | Content [" + toXML() + "]";
    }
}
