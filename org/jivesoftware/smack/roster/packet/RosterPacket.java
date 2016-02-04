package org.jivesoftware.smack.roster.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class RosterPacket extends IQ {
    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:roster";
    private final List<Item> rosterItems;
    private String rosterVersion;

    public class Item {
        public static final String GROUP = "group";
        private final Set<String> groupNames;
        private ItemStatus itemStatus;
        private ItemType itemType;
        private String name;
        private String user;

        public Item(String str, String str2) {
            this.user = str.toLowerCase(Locale.US);
            this.name = str2;
            this.itemType = null;
            this.itemStatus = null;
            this.groupNames = new CopyOnWriteArraySet();
        }

        public String getUser() {
            return this.user;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public ItemType getItemType() {
            return this.itemType;
        }

        public void setItemType(ItemType itemType) {
            this.itemType = itemType;
        }

        public ItemStatus getItemStatus() {
            return this.itemStatus;
        }

        public void setItemStatus(ItemStatus itemStatus) {
            this.itemStatus = itemStatus;
        }

        public Set<String> getGroupNames() {
            return Collections.unmodifiableSet(this.groupNames);
        }

        public void addGroupName(String str) {
            this.groupNames.add(str);
        }

        public void removeGroupName(String str) {
            this.groupNames.remove(str);
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.halfOpenElement(org.jivesoftware.smackx.xdata.packet.DataForm.Item.ELEMENT).attribute("jid", this.user);
            xmlStringBuilder.optAttribute("name", this.name);
            xmlStringBuilder.optAttribute("subscription", this.itemType);
            xmlStringBuilder.optAttribute("ask", this.itemStatus);
            xmlStringBuilder.rightAngleBracket();
            for (String escape : this.groupNames) {
                xmlStringBuilder.openElement(GROUP).escape(escape).closeElement(GROUP);
            }
            xmlStringBuilder.closeElement(org.jivesoftware.smackx.xdata.packet.DataForm.Item.ELEMENT);
            return xmlStringBuilder;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.name == null ? 0 : this.name.hashCode()) + (((this.itemType == null ? 0 : this.itemType.hashCode()) + (((this.itemStatus == null ? 0 : this.itemStatus.hashCode()) + (((this.groupNames == null ? 0 : this.groupNames.hashCode()) + 31) * 31)) * 31)) * 31)) * 31;
            if (this.user != null) {
                i = this.user.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Item item = (Item) obj;
            if (this.groupNames == null) {
                if (item.groupNames != null) {
                    return false;
                }
            } else if (!this.groupNames.equals(item.groupNames)) {
                return false;
            }
            if (this.itemStatus != item.itemStatus) {
                return false;
            }
            if (this.itemType != item.itemType) {
                return false;
            }
            if (this.name == null) {
                if (item.name != null) {
                    return false;
                }
            } else if (!this.name.equals(item.name)) {
                return false;
            }
            if (this.user == null) {
                if (item.user != null) {
                    return false;
                }
                return true;
            } else if (this.user.equals(item.user)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public enum ItemStatus {
        subscribe,
        unsubscribe;
        
        public static final ItemStatus SUBSCRIPTION_PENDING;
        public static final ItemStatus UNSUBSCRIPTION_PENDING;

        static {
            SUBSCRIPTION_PENDING = subscribe;
            UNSUBSCRIPTION_PENDING = unsubscribe;
        }

        public static ItemStatus fromString(String str) {
            ItemStatus itemStatus = null;
            if (str != null) {
                try {
                    itemStatus = valueOf(str);
                } catch (IllegalArgumentException e) {
                }
            }
            return itemStatus;
        }
    }

    public enum ItemType {
        none,
        to,
        from,
        both,
        remove
    }

    public RosterPacket() {
        super(ELEMENT, NAMESPACE);
        this.rosterItems = new ArrayList();
    }

    public void addRosterItem(Item item) {
        synchronized (this.rosterItems) {
            this.rosterItems.add(item);
        }
    }

    public int getRosterItemCount() {
        int size;
        synchronized (this.rosterItems) {
            size = this.rosterItems.size();
        }
        return size;
    }

    public List<Item> getRosterItems() {
        List arrayList;
        synchronized (this.rosterItems) {
            arrayList = new ArrayList(this.rosterItems);
        }
        return arrayList;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.optAttribute(RosterVer.ELEMENT, this.rosterVersion);
        iQChildElementXmlStringBuilder.rightAngleBracket();
        synchronized (this.rosterItems) {
            for (Item toXML : this.rosterItems) {
                iQChildElementXmlStringBuilder.append(toXML.toXML());
            }
        }
        return iQChildElementXmlStringBuilder;
    }

    public String getVersion() {
        return this.rosterVersion;
    }

    public void setVersion(String str) {
        this.rosterVersion = str;
    }
}
