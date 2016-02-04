package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.ProtocolException;

public class AuthenticationException extends ProtocolException {
    public AuthenticationException(String str) {
        super(str);
    }

    public AuthenticationException(String str, Throwable th) {
        super(str, th);
    }
}
