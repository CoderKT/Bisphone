package org.jivesoftware.smackx.pubsub.util;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormNode;
import org.jivesoftware.smackx.pubsub.PubSubElementType;

public class NodeUtils {
    public static ConfigureForm getFormFromPacket(Stanza stanza, PubSubElementType pubSubElementType) {
        return new ConfigureForm(((FormNode) stanza.getExtension(pubSubElementType.getElementName(), pubSubElementType.getNamespace().getXmlns())).getForm());
    }
}
