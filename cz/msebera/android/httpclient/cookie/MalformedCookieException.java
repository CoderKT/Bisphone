package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.ProtocolException;

public class MalformedCookieException extends ProtocolException {
    public MalformedCookieException(String str) {
        super(str);
    }
}
