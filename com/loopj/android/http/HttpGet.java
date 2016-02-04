package com.loopj.android.http;

import cz.msebera.android.httpclient.client.methods.HttpEntityEnclosingRequestBase;
import java.net.URI;

public final class HttpGet extends HttpEntityEnclosingRequestBase {
    public HttpGet(String str) {
        m10652a(URI.create(str));
    }

    public String m10662a() {
        return "GET";
    }
}
