package com.loopj.android.http;

import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jivesoftware.smack.util.TLSUtils;

public class MySSLSocketFactory extends SSLSocketFactory {
    final SSLContext f6609a;

    /* renamed from: com.loopj.android.http.MySSLSocketFactory.1 */
    class C08941 implements X509TrustManager {
        final /* synthetic */ MySSLSocketFactory f6600a;

        C08941(MySSLSocketFactory mySSLSocketFactory) {
            this.f6600a = mySSLSocketFactory;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public MySSLSocketFactory(KeyStore keyStore) {
        super(keyStore);
        this.f6609a = SSLContext.getInstance(TLSUtils.TLS);
        C08941 c08941 = new C08941(this);
        this.f6609a.init(null, new TrustManager[]{c08941}, null);
    }

    public static KeyStore m10723a() {
        KeyStore instance;
        Throwable th;
        try {
            instance = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                instance.load(null, null);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return instance;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            instance = null;
            th = th4;
            th.printStackTrace();
            return instance;
        }
        return instance;
    }

    public static SSLSocketFactory m10724b() {
        try {
            SSLSocketFactory mySSLSocketFactory = new MySSLSocketFactory(m10723a());
            mySSLSocketFactory.m10718a(SSLSocketFactory.f6601b);
            return mySSLSocketFactory;
        } catch (Throwable th) {
            th.printStackTrace();
            return SSLSocketFactory.m10709d();
        }
    }

    public Socket m10725a(Socket socket, String str, int i, boolean z) {
        return this.f6609a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket m10726c() {
        return this.f6609a.getSocketFactory().createSocket();
    }
}
