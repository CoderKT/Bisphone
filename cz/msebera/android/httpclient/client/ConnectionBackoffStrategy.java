package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.HttpResponse;

public interface ConnectionBackoffStrategy {
    boolean m11465a(HttpResponse httpResponse);

    boolean m11466a(Throwable th);
}
