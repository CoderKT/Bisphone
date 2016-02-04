package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.ProtocolException;

public class RedirectException extends ProtocolException {
    public RedirectException(String str) {
        super(str);
    }
}
