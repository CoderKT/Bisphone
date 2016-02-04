package org.jivesoftware.smackx.pubsub.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.pubsub.PubSubElementType;

public class PubSub extends IQ {
    public static final String ELEMENT = "pubsub";
    public static final String NAMESPACE = "http://jabber.org/protocol/pubsub";

    public PubSub() {
        super(ELEMENT, NAMESPACE);
    }

    public PubSub(PubSubNamespace pubSubNamespace) {
        super(ELEMENT, pubSubNamespace.getXmlns());
    }

    public PubSub(String str, Type type, PubSubNamespace pubSubNamespace) {
        String str2 = ELEMENT;
        if (pubSubNamespace == null) {
            pubSubNamespace = PubSubNamespace.BASIC;
        }
        super(str2, pubSubNamespace.getXmlns());
        setTo(str);
        setType(type);
    }

    public String getElementName() {
        return ELEMENT;
    }

    public <PE extends ExtensionElement> PE getExtension(PubSubElementType pubSubElementType) {
        return getExtension(pubSubElementType.getElementName(), pubSubElementType.getNamespace().getXmlns());
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        return iQChildElementXmlStringBuilder;
    }

    public static PubSub createPubsubPacket(String str, Type type, ExtensionElement extensionElement, PubSubNamespace pubSubNamespace) {
        PubSub pubSub = new PubSub(str, type, pubSubNamespace);
        pubSub.addExtension(extensionElement);
        return pubSub;
    }
}
