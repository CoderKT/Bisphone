package org.jivesoftware.smack.compress.packet;

import org.jivesoftware.smack.packet.FullStreamElement;

public class Compressed extends FullStreamElement {
    public static final String ELEMENT = "compressed";
    public static final Compressed INSTANCE;
    public static final String NAMESPACE = "http://jabber.org/protocol/compress";

    static {
        INSTANCE = new Compressed();
    }

    private Compressed() {
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public String toXML() {
        return "<compressed xmlns='http://jabber.org/protocol/compress'/>";
    }
}
