package cz.msebera.android.httpclient.params;

import cz.msebera.android.httpclient.util.Args;

@Deprecated
public final class HttpConnectionParams {
    public static int m12668a(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12081a("http.socket.timeout", 0);
    }

    public static void m12669a(HttpParams httpParams, int i) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12086b("http.socket.timeout", i);
    }

    public static boolean m12672b(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12085a("http.socket.reuseaddr", false);
    }

    public static boolean m12674c(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12085a("http.tcp.nodelay", true);
    }

    public static void m12670a(HttpParams httpParams, boolean z) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12088b("http.tcp.nodelay", z);
    }

    public static void m12671b(HttpParams httpParams, int i) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12086b("http.socket.buffer-size", i);
    }

    public static int m12675d(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12081a("http.socket.linger", -1);
    }

    public static int m12676e(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12081a("http.connection.timeout", 0);
    }

    public static void m12673c(HttpParams httpParams, int i) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12086b("http.connection.timeout", i);
    }

    public static boolean m12677f(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        return httpParams.m12085a("http.connection.stalecheck", true);
    }
}
