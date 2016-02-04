package com.loopj.android.http;

import cz.msebera.android.httpclient.client.methods.HttpEntityEnclosingRequestBase;
import java.net.URI;

public final class HttpDelete extends HttpEntityEnclosingRequestBase {
    public HttpDelete(String str) {
        m10652a(URI.create(str));
    }

    public String m10661a() {
        return "DELETE";
    }
}
