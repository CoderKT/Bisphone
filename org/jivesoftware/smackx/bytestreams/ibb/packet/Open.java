package org.jivesoftware.smackx.bytestreams.ibb.packet;

import java.util.Locale;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType;

public class Open extends IQ {
    public static final String ELEMENT = "open";
    public static final String NAMESPACE = "http://jabber.org/protocol/ibb";
    private final int blockSize;
    private final String sessionID;
    private final StanzaType stanza;

    public Open(String str, int i, StanzaType stanzaType) {
        super(ELEMENT, NAMESPACE);
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Session ID must not be null or empty");
        } else if (i <= 0) {
            throw new IllegalArgumentException("Block size must be greater than zero");
        } else {
            this.sessionID = str;
            this.blockSize = i;
            this.stanza = stanzaType;
            setType(Type.set);
        }
    }

    public Open(String str, int i) {
        this(str, i, StanzaType.IQ);
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public StanzaType getStanza() {
        return this.stanza;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.attribute("block-size", Integer.toString(this.blockSize));
        iQChildElementXmlStringBuilder.attribute("sid", this.sessionID);
        iQChildElementXmlStringBuilder.attribute("stanza", this.stanza.toString().toLowerCase(Locale.US));
        iQChildElementXmlStringBuilder.setEmptyElement();
        return iQChildElementXmlStringBuilder;
    }
}
