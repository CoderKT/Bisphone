package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public abstract class SimpleIQ extends IQ {
    protected SimpleIQ(String str, String str2) {
        super(str, str2);
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.setEmptyElement();
        return iQChildElementXmlStringBuilder;
    }
}
