package org.jivesoftware.smackx.bytestreams.ibb.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;

public class Data extends IQ {
    private final DataPacketExtension dataPacketExtension;

    public Data(DataPacketExtension dataPacketExtension) {
        super(DataPacketExtension.ELEMENT, Open.NAMESPACE);
        if (dataPacketExtension == null) {
            throw new IllegalArgumentException("Data must not be null");
        }
        this.dataPacketExtension = dataPacketExtension;
        setType(Type.set);
    }

    public DataPacketExtension getDataPacketExtension() {
        return this.dataPacketExtension;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        return this.dataPacketExtension.getIQChildElementBuilder(iQChildElementXmlStringBuilder);
    }
}
