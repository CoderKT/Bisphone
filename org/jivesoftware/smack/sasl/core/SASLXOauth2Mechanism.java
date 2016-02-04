package org.jivesoftware.smack.sasl.core;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.stringencoder.Base64;

public class SASLXOauth2Mechanism extends SASLMechanism {
    public static final String NAME = "X-OAUTH2";

    protected void authenticateInternal(CallbackHandler callbackHandler) {
        throw new UnsupportedOperationException("CallbackHandler not (yet) supported");
    }

    protected byte[] getAuthenticationText() {
        return Base64.encode(SASLMechanism.toBytes('\u0000' + this.authenticationId + '\u0000' + this.password));
    }

    public String getName() {
        return NAME;
    }

    public int getPriority() {
        return 410;
    }

    public SASLXOauth2Mechanism newInstance() {
        return new SASLXOauth2Mechanism();
    }

    public void checkIfSuccessfulOrThrow() {
    }
}
