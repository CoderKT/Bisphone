package cz.msebera.android.httpclient.params;

import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;

@Deprecated
public final class HttpProtocolParams {
    public static String m12679a(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        String str = (String) httpParams.m12084a("http.protocol.element-charset");
        if (str == null) {
            return HTTP.f7913b.name();
        }
        return str;
    }

    public static void m12681a(HttpParams httpParams, String str) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12083a("http.protocol.content-charset", (Object) str);
    }

    public static ProtocolVersion m12682b(HttpParams httpParams) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        Object a = httpParams.m12084a("http.protocol.version");
        if (a == null) {
            return HttpVersion.f7210c;
        }
        return (ProtocolVersion) a;
    }

    public static void m12680a(HttpParams httpParams, ProtocolVersion protocolVersion) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12083a("http.protocol.version", (Object) protocolVersion);
    }

    public static void m12683b(HttpParams httpParams, String str) {
        Args.m12722a((Object) httpParams, "HTTP parameters");
        httpParams.m12083a("http.useragent", (Object) str);
    }
}
