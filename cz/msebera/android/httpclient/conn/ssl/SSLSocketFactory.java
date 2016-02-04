package cz.msebera.android.httpclient.conn.ssl;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.conn.HttpInetSocketAddress;
import cz.msebera.android.httpclient.conn.scheme.HostNameResolver;
import cz.msebera.android.httpclient.conn.scheme.LayeredSchemeSocketFactory;
import cz.msebera.android.httpclient.conn.scheme.LayeredSocketFactory;
import cz.msebera.android.httpclient.conn.scheme.SchemeLayeredSocketFactory;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

@Deprecated
public class SSLSocketFactory implements LayeredSchemeSocketFactory, LayeredSocketFactory, SchemeLayeredSocketFactory {
    public static final X509HostnameVerifier f6601b;
    public static final X509HostnameVerifier f6602c;
    public static final X509HostnameVerifier f6603d;
    private final javax.net.ssl.SSLSocketFactory f6604a;
    private final HostNameResolver f6605e;
    private volatile X509HostnameVerifier f6606f;
    private final String[] f6607g;
    private final String[] f6608h;

    static {
        f6601b = new AllowAllHostnameVerifier();
        f6602c = new BrowserCompatHostnameVerifier();
        f6603d = new StrictHostnameVerifier();
    }

    public static SSLSocketFactory m10709d() {
        return new SSLSocketFactory(SSLContexts.m11760a(), f6602c);
    }

    public SSLSocketFactory(KeyStore keyStore) {
        this(SSLContexts.m11761b().m11757a(keyStore).m11759a(), f6602c);
    }

    public SSLSocketFactory(SSLContext sSLContext, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) Args.m12722a((Object) sSLContext, "SSL context")).getSocketFactory(), null, null, x509HostnameVerifier);
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this.f6604a = (javax.net.ssl.SSLSocketFactory) Args.m12722a((Object) sSLSocketFactory, "SSL socket factory");
        this.f6607g = strArr;
        this.f6608h = strArr2;
        if (x509HostnameVerifier == null) {
            x509HostnameVerifier = f6602c;
        }
        this.f6606f = x509HostnameVerifier;
        this.f6605e = null;
    }

    public Socket m10711a(HttpParams httpParams) {
        return m10712a((HttpContext) null);
    }

    public Socket m10722c() {
        return m10712a((HttpContext) null);
    }

    public Socket m10717a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        HttpHost a;
        Args.m12722a((Object) inetSocketAddress, "Remote address");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        if (inetSocketAddress instanceof HttpInetSocketAddress) {
            a = ((HttpInetSocketAddress) inetSocketAddress).m11650a();
        } else {
            a = new HttpHost(inetSocketAddress.getHostName(), inetSocketAddress.getPort(), "https");
        }
        int a2 = HttpConnectionParams.m12668a(httpParams);
        int e = HttpConnectionParams.m12676e(httpParams);
        socket.setSoTimeout(a2);
        return m10710a(e, socket, a, inetSocketAddress, inetSocketAddress2, null);
    }

    public boolean m10720a(Socket socket) {
        Args.m12722a((Object) socket, "Socket");
        Asserts.m12729a(socket instanceof SSLSocket, "Socket not created by this factory");
        Asserts.m12729a(!socket.isClosed(), "Socket is closed");
        return true;
    }

    public Socket m10713a(Socket socket, String str, int i, HttpParams httpParams) {
        return m10714a(socket, str, i, (HttpContext) null);
    }

    public Socket m10721b(Socket socket, String str, int i, boolean z) {
        return m10714a(socket, str, i, (HttpContext) null);
    }

    public void m10718a(X509HostnameVerifier x509HostnameVerifier) {
        Args.m12722a((Object) x509HostnameVerifier, "Hostname verifier");
        this.f6606f = x509HostnameVerifier;
    }

    public Socket m10715a(Socket socket, String str, int i, InetAddress inetAddress, int i2, HttpParams httpParams) {
        InetAddress a;
        if (this.f6605e != null) {
            a = this.f6605e.m11718a(str);
        } else {
            a = InetAddress.getByName(str);
        }
        InetSocketAddress inetSocketAddress = null;
        if (inetAddress != null || i2 > 0) {
            if (i2 <= 0) {
                i2 = 0;
            }
            inetSocketAddress = new InetSocketAddress(inetAddress, i2);
        }
        return m10717a(socket, new HttpInetSocketAddress(new HttpHost(str, i), a, i), inetSocketAddress, httpParams);
    }

    public Socket m10716a(Socket socket, String str, int i, boolean z) {
        return m10721b(socket, str, i, z);
    }

    protected void m10719a(SSLSocket sSLSocket) {
    }

    private void m10708b(SSLSocket sSLSocket) {
        if (this.f6607g != null) {
            sSLSocket.setEnabledProtocols(this.f6607g);
        }
        if (this.f6608h != null) {
            sSLSocket.setEnabledCipherSuites(this.f6608h);
        }
        m10719a(sSLSocket);
    }

    public Socket m10712a(HttpContext httpContext) {
        SSLSocket sSLSocket = (SSLSocket) this.f6604a.createSocket();
        m10708b(sSLSocket);
        return sSLSocket;
    }

    public Socket m10710a(int i, Socket socket, HttpHost httpHost, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpContext httpContext) {
        Args.m12722a((Object) httpHost, "HTTP host");
        Args.m12722a((Object) inetSocketAddress, "Remote address");
        Socket a = socket != null ? socket : m10712a(httpContext);
        if (inetSocketAddress2 != null) {
            a.bind(inetSocketAddress2);
        }
        try {
            a.connect(inetSocketAddress, i);
            if (!(a instanceof SSLSocket)) {
                return m10714a(a, httpHost.m11384a(), inetSocketAddress.getPort(), httpContext);
            }
            SSLSocket sSLSocket = (SSLSocket) a;
            sSLSocket.startHandshake();
            m10707a(sSLSocket, httpHost.m11384a());
            return a;
        } catch (IOException e) {
            try {
                a.close();
            } catch (IOException e2) {
            }
            throw e;
        }
    }

    public Socket m10714a(Socket socket, String str, int i, HttpContext httpContext) {
        SSLSocket sSLSocket = (SSLSocket) this.f6604a.createSocket(socket, str, i, true);
        m10708b(sSLSocket);
        sSLSocket.startHandshake();
        m10707a(sSLSocket, str);
        return sSLSocket;
    }

    private void m10707a(SSLSocket sSLSocket, String str) {
        try {
            this.f6606f.m11742a(str, sSLSocket);
        } catch (IOException e) {
            try {
                sSLSocket.close();
            } catch (Exception e2) {
            }
            throw e;
        }
    }
}
