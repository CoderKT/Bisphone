package org.jivesoftware.smackx.pubsub;

public class Item extends NodeExtension {
    private String id;

    public Item() {
        super(PubSubElementType.ITEM);
    }

    public Item(String str) {
        super(PubSubElementType.ITEM);
        this.id = str;
    }

    public Item(String str, String str2) {
        super(PubSubElementType.ITEM_EVENT, str2);
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public String getNamespace() {
        return null;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder("<item");
        if (this.id != null) {
            stringBuilder.append(" id='");
            stringBuilder.append(this.id);
            stringBuilder.append("'");
        }
        if (getNode() != null) {
            stringBuilder.append(" node='");
            stringBuilder.append(getNode());
            stringBuilder.append("'");
        }
        stringBuilder.append("/>");
        return stringBuilder.toString();
    }

    public String toString() {
        return getClass().getName() + " | Content [" + toXML() + "]";
    }
}
