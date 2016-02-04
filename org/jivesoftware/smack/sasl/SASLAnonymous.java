package org.jivesoftware.smack.sasl;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter;

public class SASLAnonymous extends SASLMechanism {
    public static final String NAME = "ANONYMOUS";

    public String getName() {
        return NAME;
    }

    public int getPriority() {
        return PacketWriter.QUEUE_SIZE;
    }

    protected void authenticateInternal(CallbackHandler callbackHandler) {
    }

    protected byte[] getAuthenticationText() {
        return null;
    }

    public SASLAnonymous newInstance() {
        return new SASLAnonymous();
    }

    public void checkIfSuccessfulOrThrow() {
    }
}
