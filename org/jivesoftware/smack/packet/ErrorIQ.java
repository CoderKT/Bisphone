package org.jivesoftware.smack.packet;

import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.util.Objects;

public class ErrorIQ extends SimpleIQ {
    public static final String ELEMENT = "error";

    public ErrorIQ(XMPPError xMPPError) {
        super(ELEMENT, null);
        Objects.requireNonNull(xMPPError, "XMPPError must not be null");
        setType(Type.error);
        setError(xMPPError);
    }
}
