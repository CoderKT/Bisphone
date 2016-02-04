package io.fabric.sdk.android.services.network;

import io.fabric.sdk.android.Fabric;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

class PinningTrustManager implements X509TrustManager {
    private final TrustManager[] f8361a;
    private final SystemKeyStore f8362b;
    private final long f8363c;
    private final List<byte[]> f8364d;
    private final Set<X509Certificate> f8365e;

    public PinningTrustManager(SystemKeyStore systemKeyStore, PinningInfoProvider pinningInfoProvider) {
        this.f8364d = new LinkedList();
        this.f8365e = Collections.synchronizedSet(new HashSet());
        this.f8361a = m13240a(systemKeyStore);
        this.f8362b = systemKeyStore;
        this.f8363c = pinningInfoProvider.m7929d();
        for (String a : pinningInfoProvider.m7928c()) {
            this.f8364d.add(m13239a(a));
        }
    }

    private TrustManager[] m13240a(SystemKeyStore systemKeyStore) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init(systemKeyStore.f8366a);
            return instance.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        }
    }

    private boolean m13238a(X509Certificate x509Certificate) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] equals : this.f8364d) {
                if (Arrays.equals(equals, digest)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            throw new CertificateException(e);
        }
    }

    private void m13237a(X509Certificate[] x509CertificateArr, String str) {
        for (TrustManager trustManager : this.f8361a) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
    }

    private void m13236a(X509Certificate[] x509CertificateArr) {
        if (this.f8363c == -1 || System.currentTimeMillis() - this.f8363c <= 15552000000L) {
            X509Certificate[] a = CertificateChainCleaner.m13161a(x509CertificateArr, this.f8362b);
            int length = a.length;
            int i = 0;
            while (i < length) {
                if (!m13238a(a[i])) {
                    i++;
                } else {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        Fabric.m12905g().m12871c("Fabric", "Certificate pins are stale, (" + (System.currentTimeMillis() - this.f8363c) + " millis vs " + 15552000000L + " millis) " + "falling back to system trust.");
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (!this.f8365e.contains(x509CertificateArr[0])) {
            m13237a(x509CertificateArr, str);
            m13236a(x509CertificateArr);
            this.f8365e.add(x509CertificateArr[0]);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    private byte[] m13239a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
