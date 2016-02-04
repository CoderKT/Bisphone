package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;

public class PublishItem<T extends Item> extends NodeExtension {
    protected Collection<T> items;

    public PublishItem(String str, T t) {
        super(PubSubElementType.PUBLISH, str);
        this.items = new ArrayList(1);
        this.items.add(t);
    }

    public PublishItem(String str, Collection<T> collection) {
        super(PubSubElementType.PUBLISH, str);
        this.items = collection;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder("<");
        stringBuilder.append(getElementName());
        stringBuilder.append(" node='");
        stringBuilder.append(getNode());
        stringBuilder.append("'>");
        for (Item toXML : this.items) {
            stringBuilder.append(toXML.toXML());
        }
        stringBuilder.append("</publish>");
        return stringBuilder.toString();
    }
}
