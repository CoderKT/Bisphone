package org.jivesoftware.smack.sasl;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements.AuthMechanism;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements.Response;
import org.jivesoftware.smack.util.StringTransformer;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.stringencoder.Base64;

public abstract class SASLMechanism implements Comparable<SASLMechanism> {
    public static final String CRAMMD5 = "CRAM-MD5";
    public static final String DIGESTMD5 = "DIGEST-MD5";
    public static final String EXTERNAL = "EXTERNAL";
    public static final String GSSAPI = "GSSAPI";
    public static final String PLAIN = "PLAIN";
    private static StringTransformer saslPrepTransformer;
    protected String authenticationId;
    protected XMPPConnection connection;
    protected String host;
    protected String password;
    protected String serviceName;

    protected abstract void authenticateInternal(CallbackHandler callbackHandler);

    public abstract void checkIfSuccessfulOrThrow();

    protected abstract byte[] getAuthenticationText();

    public abstract String getName();

    public abstract int getPriority();

    protected abstract SASLMechanism newInstance();

    public static void setSaslPrepTransformer(StringTransformer stringTransformer) {
        saslPrepTransformer = stringTransformer;
    }

    public final void authenticate(String str, String str2, String str3, String str4) {
        this.authenticationId = str;
        this.host = str2;
        this.serviceName = str3;
        this.password = str4;
        authenticateInternal();
        authenticate();
    }

    protected void authenticateInternal() {
    }

    public void authenticate(String str, String str2, CallbackHandler callbackHandler) {
        this.host = str;
        this.serviceName = str2;
        authenticateInternal(callbackHandler);
        authenticate();
    }

    private final void authenticate() {
        String str;
        byte[] authenticationText = getAuthenticationText();
        if (authenticationText == null || authenticationText.length <= 0) {
            str = "=";
        } else {
            str = Base64.encodeToString(authenticationText);
        }
        this.connection.send(new AuthMechanism(getName(), str));
    }

    public final void challengeReceived(String str, boolean z) {
        byte[] evaluateChallenge = evaluateChallenge(Base64.decode(str));
        if (!z) {
            PlainStreamElement response;
            if (evaluateChallenge == null) {
                response = new Response();
            } else {
                response = new Response(Base64.encodeToString(evaluateChallenge));
            }
            this.connection.send(response);
        }
    }

    protected byte[] evaluateChallenge(byte[] bArr) {
        return null;
    }

    public final int compareTo(SASLMechanism sASLMechanism) {
        return getPriority() - sASLMechanism.getPriority();
    }

    public SASLMechanism instanceForAuthentication(XMPPConnection xMPPConnection) {
        SASLMechanism newInstance = newInstance();
        newInstance.connection = xMPPConnection;
        return newInstance;
    }

    protected static byte[] toBytes(String str) {
        return StringUtils.toBytes(str);
    }

    protected static String saslPrep(String str) {
        StringTransformer stringTransformer = saslPrepTransformer;
        if (stringTransformer != null) {
            return stringTransformer.transform(str);
        }
        return str;
    }
}
