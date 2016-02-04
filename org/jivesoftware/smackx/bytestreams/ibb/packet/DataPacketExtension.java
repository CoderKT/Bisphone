package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smack.util.stringencoder.Base64;

public class DataPacketExtension implements ExtensionElement {
    public static final String ELEMENT = "data";
    public static final String NAMESPACE = "http://jabber.org/protocol/ibb";
    private final String data;
    private byte[] decodedData;
    private final long seq;
    private final String sessionID;

    public DataPacketExtension(String str, long j, String str2) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        } else if (j < 0 || j > 65535) {
            throw new IllegalArgumentException("Sequence must not be between 0 and 65535");
        } else if (str2 == null) {
            throw new IllegalArgumentException("Data must not be null");
        } else {
            this.sessionID = str;
            this.seq = j;
            this.data = str2;
        }
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public long getSeq() {
        return this.seq;
    }

    public String getData() {
        return this.data;
    }

    public byte[] getDecodedData() {
        if (this.decodedData != null) {
            return this.decodedData;
        }
        if (this.data.matches(".*={1,2}+.+")) {
            return null;
        }
        this.decodedData = Base64.decode(this.data);
        return this.decodedData;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder iQChildElementBuilder = getIQChildElementBuilder(new IQChildElementXmlStringBuilder((ExtensionElement) this));
        iQChildElementBuilder.closeElement((NamedElement) this);
        return iQChildElementBuilder;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.attribute("seq", Long.toString(this.seq));
        iQChildElementXmlStringBuilder.attribute("sid", this.sessionID);
        iQChildElementXmlStringBuilder.rightAngleBracket();
        iQChildElementXmlStringBuilder.append(this.data);
        return iQChildElementXmlStringBuilder;
    }
}
