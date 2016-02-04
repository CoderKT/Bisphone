package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;

public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
    public static final DefaultHttpRequestRetryHandler f7578a;
    private final int f7579b;
    private final boolean f7580c;
    private final Set<Class<? extends IOException>> f7581d;

    static {
        f7578a = new DefaultHttpRequestRetryHandler();
    }

    protected DefaultHttpRequestRetryHandler(int i, boolean z, Collection<Class<? extends IOException>> collection) {
        this.f7579b = i;
        this.f7580c = z;
        this.f7581d = new HashSet();
        for (Class add : collection) {
            this.f7581d.add(add);
        }
    }

    public DefaultHttpRequestRetryHandler(int i, boolean z) {
        this(i, z, Arrays.asList(new Class[]{InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class}));
    }

    public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }

    public boolean m12104a(IOException iOException, int i, HttpContext httpContext) {
        Args.m12722a((Object) iOException, "Exception parameter");
        Args.m12722a((Object) httpContext, "HTTP context");
        if (i > this.f7579b) {
            return false;
        }
        if (this.f7581d.contains(iOException.getClass())) {
            return false;
        }
        for (Class isInstance : this.f7581d) {
            if (isInstance.isInstance(iOException)) {
                return false;
            }
        }
        HttpClientContext a = HttpClientContext.m11538a(httpContext);
        HttpRequest m = a.m11535m();
        if (m12105b(m)) {
            return false;
        }
        if (m12103a(m)) {
            return true;
        }
        if (!a.m11536n() || this.f7580c) {
            return true;
        }
        return false;
    }

    protected boolean m12103a(HttpRequest httpRequest) {
        return !(httpRequest instanceof HttpEntityEnclosingRequest);
    }

    @Deprecated
    protected boolean m12105b(HttpRequest httpRequest) {
        HttpRequest n;
        if (httpRequest instanceof RequestWrapper) {
            n = ((RequestWrapper) httpRequest).m12141n();
        } else {
            n = httpRequest;
        }
        return (n instanceof HttpUriRequest) && ((HttpUriRequest) n).m10647j();
    }
}
