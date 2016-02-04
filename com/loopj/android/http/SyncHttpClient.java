package com.loopj.android.http;

import android.content.Context;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HttpContext;

public class SyncHttpClient extends AsyncHttpClient {
    public SyncHttpClient() {
        super(false, 80, 443);
    }

    protected RequestHandle m10776b(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, ResponseHandlerInterface responseHandlerInterface, Context context) {
        if (str != null) {
            httpUriRequest.m10610a("Content-Type", str);
        }
        responseHandlerInterface.m5459a(true);
        m10570a(defaultHttpClient, httpContext, httpUriRequest, str, responseHandlerInterface, context).run();
        return new RequestHandle(null);
    }
}
