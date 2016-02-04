package org.jivesoftware.smackx.iqprivate.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;

public class PrivateDataIQ extends IQ {
    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:private";
    private final String getElement;
    private final String getNamespace;
    private final PrivateData privateData;

    public PrivateDataIQ(PrivateData privateData) {
        this(privateData, null, null);
        setType(Type.set);
    }

    public PrivateDataIQ(String str, String str2) {
        this(null, str, str2);
        setType(Type.get);
    }

    private PrivateDataIQ(PrivateData privateData, String str, String str2) {
        super(ELEMENT, NAMESPACE);
        this.privateData = privateData;
        this.getElement = str;
        this.getNamespace = str2;
    }

    public PrivateData getPrivateData() {
        return this.privateData;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        if (this.privateData != null) {
            iQChildElementXmlStringBuilder.append(this.privateData.toXML());
        } else {
            iQChildElementXmlStringBuilder.halfOpenElement(this.getElement).xmlnsAttribute(this.getNamespace).closeEmptyElement();
        }
        return iQChildElementXmlStringBuilder;
    }
}
