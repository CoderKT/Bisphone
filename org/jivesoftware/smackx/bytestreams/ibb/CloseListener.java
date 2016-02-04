package org.jivesoftware.smackx.bytestreams.ibb;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;

class CloseListener extends AbstractIqRequestHandler {
    private final InBandBytestreamManager manager;

    protected CloseListener(InBandBytestreamManager inBandBytestreamManager) {
        super(Close.ELEMENT, Open.NAMESPACE, Type.set, Mode.async);
        this.manager = inBandBytestreamManager;
    }

    public IQ handleIQRequest(IQ iq) {
        Close close = (Close) iq;
        InBandBytestreamSession inBandBytestreamSession = (InBandBytestreamSession) this.manager.getSessions().get(close.getSessionID());
        if (inBandBytestreamSession == null) {
            try {
                this.manager.replyItemNotFoundPacket(close);
            } catch (NotConnectedException e) {
            }
        } else {
            try {
                inBandBytestreamSession.closeByPeer(close);
                this.manager.getSessions().remove(close.getSessionID());
            } catch (NotConnectedException e2) {
            }
        }
        return null;
    }
}
