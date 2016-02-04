package cz.msebera.android.httpclient.client;

import java.io.IOException;

public class ClientProtocolException extends IOException {
    public ClientProtocolException(String str) {
        super(str);
    }

    public ClientProtocolException(Throwable th) {
        initCause(th);
    }
}
