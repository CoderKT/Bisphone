package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.util.XmlStringBuilder;

public class GetItemsRequest extends NodeExtension {
    protected final int maxItems;
    protected final String subId;

    public GetItemsRequest(String str) {
        this(str, null, -1);
    }

    public GetItemsRequest(String str, String str2) {
        this(str, str2, -1);
    }

    public GetItemsRequest(String str, int i) {
        this(str, null, i);
    }

    public GetItemsRequest(String str, String str2, int i) {
        super(PubSubElementType.ITEMS, str);
        this.maxItems = i;
        this.subId = str2;
    }

    public String getSubscriptionId() {
        return this.subId;
    }

    public int getMaxItems() {
        return this.maxItems;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement(getElementName());
        xmlStringBuilder.attribute("node", getNode());
        xmlStringBuilder.optAttribute("subid", getSubscriptionId());
        xmlStringBuilder.optIntAttribute("max_items", getMaxItems());
        xmlStringBuilder.closeEmptyElement();
        return xmlStringBuilder;
    }
}
