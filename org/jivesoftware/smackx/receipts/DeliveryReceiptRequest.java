package org.jivesoftware.smackx.receipts;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class DeliveryReceiptRequest implements ExtensionElement {
    public static final String ELEMENT = "request";

    public class Provider extends ExtensionElementProvider<DeliveryReceiptRequest> {
        public DeliveryReceiptRequest parse(XmlPullParser xmlPullParser, int i) {
            return new DeliveryReceiptRequest();
        }
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return DeliveryReceipt.NAMESPACE;
    }

    public String toXML() {
        return "<request xmlns='urn:xmpp:receipts'/>";
    }

    @Deprecated
    public static DeliveryReceiptRequest getFrom(Stanza stanza) {
        return from(stanza);
    }

    public static DeliveryReceiptRequest from(Stanza stanza) {
        return (DeliveryReceiptRequest) stanza.getExtension(ELEMENT, DeliveryReceipt.NAMESPACE);
    }

    public static String addTo(Message message) {
        if (message.getStanzaId() == null) {
            message.setStanzaId(StanzaIdUtil.newStanzaId());
        }
        message.addExtension(new DeliveryReceiptRequest());
        return message.getStanzaId();
    }
}
