package cz.msebera.android.httpclient.conn.ssl;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.jivesoftware.smack.util.TLSUtils;

public class SSLContextBuilder {
    private String f7381a;
    private Set<KeyManager> f7382b;
    private Set<TrustManager> f7383c;
    private SecureRandom f7384d;

    class TrustManagerDelegate implements X509TrustManager {
        private final X509TrustManager f7379a;
        private final TrustStrategy f7380b;

        TrustManagerDelegate(X509TrustManager x509TrustManager, TrustStrategy trustStrategy) {
            this.f7379a = x509TrustManager;
            this.f7380b = trustStrategy;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            this.f7379a.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            if (!this.f7380b.m11763a(x509CertificateArr, str)) {
                this.f7379a.checkServerTrusted(x509CertificateArr, str);
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.f7379a.getAcceptedIssuers();
        }
    }

    public SSLContextBuilder() {
        this.f7382b = new HashSet();
        this.f7383c = new HashSet();
    }

    public SSLContextBuilder m11758a(KeyStore keyStore, TrustStrategy trustStrategy) {
        int i = 0;
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers != null) {
            if (trustStrategy != null) {
                for (int i2 = 0; i2 < trustManagers.length; i2++) {
                    TrustManager trustManager = trustManagers[i2];
                    if (trustManager instanceof X509TrustManager) {
                        trustManagers[i2] = new TrustManagerDelegate((X509TrustManager) trustManager, trustStrategy);
                    }
                }
            }
            int length = trustManagers.length;
            while (i < length) {
                this.f7383c.add(trustManagers[i]);
                i++;
            }
        }
        return this;
    }

    public SSLContextBuilder m11757a(KeyStore keyStore) {
        return m11758a(keyStore, null);
    }

    public SSLContext m11759a() {
        KeyManager[] keyManagerArr;
        TrustManager[] trustManagerArr;
        SSLContext instance = SSLContext.getInstance(this.f7381a != null ? this.f7381a : TLSUtils.TLS);
        if (this.f7382b.isEmpty()) {
            keyManagerArr = null;
        } else {
            keyManagerArr = (KeyManager[]) this.f7382b.toArray(new KeyManager[this.f7382b.size()]);
        }
        if (this.f7383c.isEmpty()) {
            trustManagerArr = null;
        } else {
            trustManagerArr = (TrustManager[]) this.f7383c.toArray(new TrustManager[this.f7383c.size()]);
        }
        instance.init(keyManagerArr, trustManagerArr, this.f7384d);
        return instance;
    }
}
