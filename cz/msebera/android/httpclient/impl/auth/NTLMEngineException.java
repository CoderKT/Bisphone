package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.auth.AuthenticationException;

public class NTLMEngineException extends AuthenticationException {
    public NTLMEngineException(String str) {
        super(str);
    }

    public NTLMEngineException(String str, Throwable th) {
        super(str, th);
    }
}
