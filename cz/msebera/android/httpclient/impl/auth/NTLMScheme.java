package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.auth.AuthenticationException;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.auth.InvalidCredentialsException;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.auth.NTCredentials;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public class NTLMScheme extends AuthSchemeBase {
    private final NTLMEngine f7537a;
    private State f7538b;
    private String f7539c;

    enum State {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        MSG_TYPE1_GENERATED,
        MSG_TYPE2_RECEVIED,
        MSG_TYPE3_GENERATED,
        FAILED
    }

    public NTLMScheme(NTLMEngine nTLMEngine) {
        Args.m12722a((Object) nTLMEngine, "NTLM engine");
        this.f7537a = nTLMEngine;
        this.f7538b = State.UNINITIATED;
        this.f7539c = null;
    }

    public NTLMScheme() {
        this(new NTLMEngineImpl());
    }

    public String m12007a() {
        return "ntlm";
    }

    public String m12009b() {
        return null;
    }

    public boolean m12010c() {
        return true;
    }

    protected void m12008a(CharArrayBuffer charArrayBuffer, int i, int i2) {
        this.f7539c = charArrayBuffer.m12754b(i, i2);
        if (this.f7539c.length() == 0) {
            if (this.f7538b == State.UNINITIATED) {
                this.f7538b = State.CHALLENGE_RECEIVED;
            } else {
                this.f7538b = State.FAILED;
            }
        } else if (this.f7538b.compareTo(State.MSG_TYPE1_GENERATED) < 0) {
            this.f7538b = State.FAILED;
            throw new MalformedChallengeException("Out of sequence NTLM response message");
        } else if (this.f7538b == State.MSG_TYPE1_GENERATED) {
            this.f7538b = State.MSG_TYPE2_RECEVIED;
        }
    }

    public Header m12006a(Credentials credentials, HttpRequest httpRequest) {
        try {
            NTCredentials nTCredentials = (NTCredentials) credentials;
            if (this.f7538b == State.FAILED) {
                throw new AuthenticationException("NTLM authentication failed");
            }
            String a;
            if (this.f7538b == State.CHALLENGE_RECEIVED) {
                a = this.f7537a.m11916a(nTCredentials.m11446d(), nTCredentials.m11447e());
                this.f7538b = State.MSG_TYPE1_GENERATED;
            } else if (this.f7538b == State.MSG_TYPE2_RECEVIED) {
                a = this.f7537a.m11917a(nTCredentials.m11445c(), nTCredentials.m11444b(), nTCredentials.m11446d(), nTCredentials.m11447e(), this.f7539c);
                this.f7538b = State.MSG_TYPE3_GENERATED;
            } else {
                throw new AuthenticationException("Unexpected state: " + this.f7538b);
            }
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
            if (m11886e()) {
                charArrayBuffer.m12751a("Proxy-Authorization");
            } else {
                charArrayBuffer.m12751a("Authorization");
            }
            charArrayBuffer.m12751a(": NTLM ");
            charArrayBuffer.m12751a(a);
            return new BufferedHeader(charArrayBuffer);
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
    }

    public boolean m12011d() {
        return this.f7538b == State.MSG_TYPE3_GENERATED || this.f7538b == State.FAILED;
    }
}
