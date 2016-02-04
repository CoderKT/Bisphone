package org.jivesoftware.smack.iqrequest;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;

public interface IQRequestHandler {

    public enum Mode {
        sync,
        async
    }

    String getElement();

    Mode getMode();

    String getNamespace();

    Type getType();

    IQ handleIQRequest(IQ iq);
}
