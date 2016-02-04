package org.jivesoftware.smack.sasl.provided;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.StringUtils;
import org.jxmpp.util.XmppStringUtils;

public class SASLExternalMechanism extends SASLMechanism {
    public static final String NAME = "EXTERNAL";

    protected void authenticateInternal(CallbackHandler callbackHandler) {
    }

    protected byte[] getAuthenticationText() {
        if (StringUtils.isNullOrEmpty(this.authenticationId)) {
            return null;
        }
        return SASLMechanism.toBytes(XmppStringUtils.m13442a(this.authenticationId, this.serviceName));
    }

    public String getName() {
        return NAME;
    }

    public int getPriority() {
        return 510;
    }

    protected SASLMechanism newInstance() {
        return new SASLExternalMechanism();
    }

    public void checkIfSuccessfulOrThrow() {
    }
}
