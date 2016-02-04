package cz.msebera.android.httpclient.params;

import cz.msebera.android.httpclient.config.MessageConstraints;

@Deprecated
public final class HttpParamConfig {
    public static MessageConstraints m12678a(HttpParams httpParams) {
        return MessageConstraints.m11612d().m11611b(httpParams.m12081a("http.connection.max-header-count", -1)).m11609a(httpParams.m12081a("http.connection.max-line-length", -1)).m11610a();
    }
}
