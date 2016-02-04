package com.loopj.android.http;

import cz.msebera.android.httpclient.client.methods.HttpUriRequest;

public abstract class RangeFileAsyncHttpResponseHandler extends FileAsyncHttpResponseHandler {
    private long f6610a;
    private boolean f6611b;

    public void m10727a(HttpUriRequest httpUriRequest) {
        if (this.d.exists() && this.d.canWrite()) {
            this.f6610a = this.d.length();
        }
        if (this.f6610a > 0) {
            this.f6611b = true;
            httpUriRequest.m10614b("Range", "bytes=" + this.f6610a + "-");
        }
    }
}
