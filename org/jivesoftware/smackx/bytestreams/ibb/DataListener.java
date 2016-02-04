package org.jivesoftware.smackx.bytestreams.ibb;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;

class DataListener extends AbstractIqRequestHandler {
    private final InBandBytestreamManager manager;

    public DataListener(InBandBytestreamManager inBandBytestreamManager) {
        super(DataPacketExtension.ELEMENT, Open.NAMESPACE, Type.set, Mode.async);
        this.manager = inBandBytestreamManager;
    }

    public IQ handleIQRequest(IQ iq) {
        Data data = (Data) iq;
        InBandBytestreamSession inBandBytestreamSession = (InBandBytestreamSession) this.manager.getSessions().get(data.getDataPacketExtension().getSessionID());
        if (inBandBytestreamSession == null) {
            try {
                this.manager.replyItemNotFoundPacket(data);
            } catch (NotConnectedException e) {
            }
        } else {
            inBandBytestreamSession.processIQPacket(data);
        }
        return null;
    }
}
