package org.jivesoftware.smack.util;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jivesoftware.smack.ConnectionConfiguration.Builder;
import org.jivesoftware.smack.SmackException.SecurityNotPossibleException;

public class TLSUtils {
    private static final HostnameVerifier DOES_NOT_VERIFY_VERIFIER;
    public static final String PROTO_SSL3 = "SSLv3";
    public static final String PROTO_TLSV1 = "TLSv1";
    public static final String PROTO_TLSV1_1 = "TLSv1.1";
    public static final String PROTO_TLSV1_2 = "TLSv1.2";
    public static final String SSL = "SSL";
    public static final String TLS = "TLS";

    /* renamed from: org.jivesoftware.smack.util.TLSUtils.1 */
    final class C10301 implements HostnameVerifier {
        C10301() {
        }

        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    public class AcceptAllTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static <B extends Builder<B, ?>> B setTLSOnly(B b) {
        b.setEnabledSSLProtocols(new String[]{PROTO_TLSV1_2, PROTO_TLSV1_1, PROTO_TLSV1});
        return b;
    }

    public static <B extends Builder<B, ?>> B setSSLv3AndTLSOnly(B b) {
        b.setEnabledSSLProtocols(new String[]{PROTO_TLSV1_2, PROTO_TLSV1_1, PROTO_TLSV1, PROTO_SSL3});
        return b;
    }

    public static <B extends Builder<B, ?>> B acceptAllCertificates(B b) {
        SSLContext instance = SSLContext.getInstance(TLS);
        instance.init(null, new TrustManager[]{new AcceptAllTrustManager()}, new SecureRandom());
        b.setCustomSSLContext(instance);
        return b;
    }

    static {
        DOES_NOT_VERIFY_VERIFIER = new C10301();
    }

    public static <B extends Builder<B, ?>> B disableHostnameVerificationForTlsCertificicates(B b) {
        b.setHostnameVerifier(DOES_NOT_VERIFY_VERIFIER);
        return b;
    }

    public static void setEnabledProtocolsAndCiphers(SSLSocket sSLSocket, String[] strArr, String[] strArr2) {
        Collection hashSet;
        Collection hashSet2;
        Set hashSet3;
        if (strArr != null) {
            hashSet = new HashSet(Arrays.asList(strArr));
            hashSet2 = new HashSet(Arrays.asList(sSLSocket.getSupportedProtocols()));
            hashSet3 = new HashSet(hashSet2);
            hashSet3.retainAll(hashSet);
            if (hashSet3.isEmpty()) {
                throw new SecurityNotPossibleException("Request to enable SSL/TLS protocols '" + StringUtils.collectionToString(hashSet) + "', but only '" + StringUtils.collectionToString(hashSet2) + "' are supported.");
            }
            sSLSocket.setEnabledProtocols((String[]) hashSet3.toArray(new String[hashSet3.size()]));
        }
        if (strArr2 != null) {
            hashSet = new HashSet(Arrays.asList(strArr2));
            hashSet2 = new HashSet(Arrays.asList(sSLSocket.getEnabledCipherSuites()));
            hashSet3 = new HashSet(hashSet2);
            hashSet3.retainAll(hashSet);
            if (hashSet3.isEmpty()) {
                throw new SecurityNotPossibleException("Request to enable SSL/TLS ciphers '" + StringUtils.collectionToString(hashSet) + "', but only '" + StringUtils.collectionToString(hashSet2) + "' are supported.");
            }
            sSLSocket.setEnabledCipherSuites((String[]) hashSet3.toArray(new String[hashSet3.size()]));
        }
    }
}
