package org.jivesoftware.smackx.iqprivate.packet;

public interface PrivateData {
    String getElementName();

    String getNamespace();

    CharSequence toXML();
}
