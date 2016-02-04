package org.jivesoftware.smackx.pubsub;

import java.util.Locale;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

public enum FormNodeType {
    CONFIGURE_OWNER,
    CONFIGURE,
    OPTIONS,
    DEFAULT;

    public PubSubElementType getNodeElement() {
        return PubSubElementType.valueOf(toString());
    }

    public static FormNodeType valueOfFromElementName(String str, String str2) {
        if ("configure".equals(str) && PubSubNamespace.OWNER.getXmlns().equals(str2)) {
            return CONFIGURE_OWNER;
        }
        return valueOf(str.toUpperCase(Locale.US));
    }
}
