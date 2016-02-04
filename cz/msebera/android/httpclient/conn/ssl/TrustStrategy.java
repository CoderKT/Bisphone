package cz.msebera.android.httpclient.conn.ssl;

import java.security.cert.X509Certificate;

public interface TrustStrategy {
    boolean m11763a(X509Certificate[] x509CertificateArr, String str);
}
