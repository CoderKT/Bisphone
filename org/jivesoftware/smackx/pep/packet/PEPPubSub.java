package org.jivesoftware.smackx.pep.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class PEPPubSub extends IQ {
    public static final String ELEMENT = "pubsub";
    public static final String NAMESPACE = "http://jabber.org/protocol/pubsub";
    private final PEPItem item;

    public PEPPubSub(PEPItem pEPItem) {
        super(ELEMENT, NAMESPACE);
        this.item = pEPItem;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        iQChildElementXmlStringBuilder.openElement("publish").attribute("node", this.item.getNode()).rightAngleBracket();
        iQChildElementXmlStringBuilder.append(this.item.toXML());
        iQChildElementXmlStringBuilder.closeElement("publish");
        return iQChildElementXmlStringBuilder;
    }
}
