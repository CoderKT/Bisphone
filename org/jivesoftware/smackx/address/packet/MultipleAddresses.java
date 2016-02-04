package org.jivesoftware.smackx.address.packet;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class MultipleAddresses implements ExtensionElement {
    public static final String ELEMENT = "addresses";
    public static final String NAMESPACE = "http://jabber.org/protocol/address";
    private List<Address> addresses;

    public class Address implements NamedElement {
        public static final String ELEMENT = "address";
        private boolean delivered;
        private String description;
        private String jid;
        private String node;
        private final Type type;
        private String uri;

        private Address(Type type) {
            this.type = type;
        }

        public Type getType() {
            return this.type;
        }

        public String getJid() {
            return this.jid;
        }

        private void setJid(String str) {
            this.jid = str;
        }

        public String getNode() {
            return this.node;
        }

        private void setNode(String str) {
            this.node = str;
        }

        public String getDescription() {
            return this.description;
        }

        private void setDescription(String str) {
            this.description = str;
        }

        public boolean isDelivered() {
            return this.delivered;
        }

        private void setDelivered(boolean z) {
            this.delivered = z;
        }

        public String getUri() {
            return this.uri;
        }

        private void setUri(String str) {
            this.uri = str;
        }

        public String getElementName() {
            return ELEMENT;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.halfOpenElement((NamedElement) this).attribute("type", this.type);
            xmlStringBuilder.optAttribute("jid", this.jid);
            xmlStringBuilder.optAttribute("node", this.node);
            xmlStringBuilder.optAttribute("desc", this.description);
            if (this.description != null && this.description.trim().length() > 0) {
                xmlStringBuilder.append((CharSequence) " desc=\"");
                xmlStringBuilder.append(this.description).append((CharSequence) "\"");
            }
            xmlStringBuilder.optBooleanAttribute("delivered", this.delivered);
            xmlStringBuilder.optAttribute("uri", this.uri);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }
    }

    public enum Type {
        bcc,
        cc,
        noreply,
        replyroom,
        replyto,
        to,
        ofrom
    }

    public MultipleAddresses() {
        this.addresses = new ArrayList();
    }

    public void addAddress(Type type, String str, String str2, String str3, boolean z, String str4) {
        Address address = new Address(null);
        address.setJid(str);
        address.setNode(str2);
        address.setDescription(str3);
        address.setDelivered(z);
        address.setUri(str4);
        this.addresses.add(address);
    }

    public void setNoReply() {
        this.addresses.add(new Address(null));
    }

    public List<Address> getAddressesOfType(Type type) {
        List<Address> arrayList = new ArrayList(this.addresses.size());
        for (Address address : this.addresses) {
            if (address.getType().equals(type)) {
                arrayList.add(address);
            }
        }
        return arrayList;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.rightAngleBracket();
        for (Address toXML : this.addresses) {
            xmlStringBuilder.append(toXML.toXML());
        }
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }
}
