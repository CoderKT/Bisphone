package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;

public class EmptyResultIQ extends IQ {
    public EmptyResultIQ() {
        super(null, null);
        setType(Type.result);
    }

    public EmptyResultIQ(IQ iq) {
        this();
        if (iq.getType() == Type.get || iq.getType() == Type.set) {
            setStanzaId(iq.getStanzaId());
            setFrom(iq.getTo());
            setTo(iq.getFrom());
            return;
        }
        throw new IllegalArgumentException("IQ must be of type 'set' or 'get'. Original IQ: " + iq.toXML());
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        return null;
    }
}
