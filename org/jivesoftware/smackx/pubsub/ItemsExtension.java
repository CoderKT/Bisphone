package org.jivesoftware.smackx.pubsub;

import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;

public class ItemsExtension extends NodeExtension implements EmbeddedPacketExtension {
    protected List<? extends ExtensionElement> items;
    protected Boolean notify;
    protected ItemsElementType type;

    public enum ItemsElementType {
        items(PubSubElementType.ITEMS, "max_items"),
        retract(PubSubElementType.RETRACT, "notify");
        
        private String att;
        private PubSubElementType elem;

        private ItemsElementType(PubSubElementType pubSubElementType, String str) {
            this.elem = pubSubElementType;
            this.att = str;
        }

        public PubSubElementType getNodeElement() {
            return this.elem;
        }

        public String getElementAttribute() {
            return this.att;
        }
    }

    public ItemsExtension(ItemsElementType itemsElementType, String str, List<? extends ExtensionElement> list) {
        super(itemsElementType.getNodeElement(), str);
        this.type = itemsElementType;
        this.items = list;
    }

    public ItemsExtension(String str, List<? extends ExtensionElement> list, boolean z) {
        super(ItemsElementType.retract.getNodeElement(), str);
        this.type = ItemsElementType.retract;
        this.items = list;
        this.notify = Boolean.valueOf(z);
    }

    public ItemsElementType getItemsElementType() {
        return this.type;
    }

    public List<ExtensionElement> getExtensions() {
        return getItems();
    }

    public List<? extends ExtensionElement> getItems() {
        return this.items;
    }

    public boolean getNotify() {
        return this.notify.booleanValue();
    }

    public CharSequence toXML() {
        if (this.items == null || this.items.size() == 0) {
            return super.toXML();
        }
        StringBuilder stringBuilder = new StringBuilder("<");
        stringBuilder.append(getElementName());
        stringBuilder.append(" node='");
        stringBuilder.append(getNode());
        if (this.notify != null) {
            stringBuilder.append("' ");
            stringBuilder.append(this.type.getElementAttribute());
            stringBuilder.append("='");
            stringBuilder.append(this.notify.equals(Boolean.TRUE) ? 1 : 0);
            stringBuilder.append("'>");
        } else {
            stringBuilder.append("'>");
            for (ExtensionElement toXML : this.items) {
                stringBuilder.append(toXML.toXML());
            }
        }
        stringBuilder.append("</");
        stringBuilder.append(getElementName());
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    public String toString() {
        return getClass().getName() + "Content [" + toXML() + "]";
    }
}
