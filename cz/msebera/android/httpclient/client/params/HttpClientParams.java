package cz.msebera.android.httpclient.client.params;

import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;

@Deprecated
public class HttpClientParams {
    public static boolean m11525a(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12085a("http.protocol.handle-redirects", true);
    }

    public static boolean m11526b(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12085a("http.protocol.handle-authentication", true);
    }

    public static long m11527c(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        Long l = (Long) httpParams.m12084a("http.conn-manager.timeout");
        if (l != null) {
            return l.longValue();
        }
        return (long) HttpConnectionParams.m12676e(httpParams);
    }
}
