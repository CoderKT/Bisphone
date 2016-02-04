package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.ProtocolException;

public class NonRepeatableRequestException extends ProtocolException {
    public NonRepeatableRequestException(String str) {
        super(str);
    }

    public NonRepeatableRequestException(String str, Throwable th) {
        super(str, th);
    }
}
