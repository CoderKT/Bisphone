package cz.msebera.android.httpclient.conn.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;

public interface X509HostnameVerifier extends HostnameVerifier {
    void m11742a(String str, SSLSocket sSLSocket);

    void m11743a(String str, String[] strArr, String[] strArr2);
}
