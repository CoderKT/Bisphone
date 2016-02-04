package org.jivesoftware.smackx.disco.packet;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;

public class DiscoverItems extends IQ {
    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "http://jabber.org/protocol/disco#items";
    private final List<Item> items;
    private String node;

    public class Item {
        public static final String REMOVE_ACTION = "remove";
        public static final String UPDATE_ACTION = "update";
        private String action;
        private String entityID;
        private String name;
        private String node;

        public Item(String str) {
            this.entityID = str;
        }

        public String getEntityID() {
            return this.entityID;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getNode() {
            return this.node;
        }

        public void setNode(String str) {
            this.node = str;
        }

        public String getAction() {
            return this.action;
        }

        public void setAction(String str) {
            this.action = str;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.halfOpenElement(org.jivesoftware.smackx.xdata.packet.DataForm.Item.ELEMENT);
            xmlStringBuilder.attribute("jid", this.entityID);
            xmlStringBuilder.optAttribute("name", this.name);
            xmlStringBuilder.optAttribute("node", this.node);
            xmlStringBuilder.optAttribute(Action.ATTRIBUTE_NAME, this.action);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }
    }

    public DiscoverItems() {
        super(ELEMENT, NAMESPACE);
        this.items = new LinkedList();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addItems(Collection<Item> collection) {
        if (collection != null) {
            for (Item addItem : collection) {
                addItem(addItem);
            }
        }
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.optAttribute("node", getNode());
        iQChildElementXmlStringBuilder.rightAngleBracket();
        for (Item toXML : this.items) {
            iQChildElementXmlStringBuilder.append(toXML.toXML());
        }
        return iQChildElementXmlStringBuilder;
    }
}
