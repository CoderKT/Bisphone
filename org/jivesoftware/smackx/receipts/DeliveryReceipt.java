package org.jivesoftware.smackx.receipts;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class DeliveryReceipt implements ExtensionElement {
    public static final String ELEMENT = "received";
    public static final String NAMESPACE = "urn:xmpp:receipts";
    private final String id;

    public class Provider extends EmbeddedExtensionProvider<DeliveryReceipt> {
        protected DeliveryReceipt createReturnExtension(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
            return new DeliveryReceipt((String) map.get("id"));
        }
    }

    public DeliveryReceipt(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.attribute("id", this.id);
        xmlStringBuilder.closeEmptyElement();
        return xmlStringBuilder;
    }

    @Deprecated
    public static DeliveryReceipt getFrom(Message message) {
        return from(message);
    }

    public static DeliveryReceipt from(Message message) {
        return (DeliveryReceipt) message.getExtension(ELEMENT, NAMESPACE);
    }
}
