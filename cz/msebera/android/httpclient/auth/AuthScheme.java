package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpRequest;

public interface AuthScheme {
    @Deprecated
    Header m11415a(Credentials credentials, HttpRequest httpRequest);

    String m11416a();

    void m11417a(Header header);

    String m11418b();

    boolean m11419c();

    boolean m11420d();
}
