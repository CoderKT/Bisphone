package io.fabric.sdk.android.services.network;

import io.fabric.sdk.android.DefaultLogger;
import io.fabric.sdk.android.Logger;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import se.emilsjolander.stickylistheaders.C1128R;

public class DefaultHttpRequestFactory implements HttpRequestFactory {
    private final Logger f8333a;
    private PinningInfoProvider f8334b;
    private SSLSocketFactory f8335c;
    private boolean f8336d;

    /* renamed from: io.fabric.sdk.android.services.network.DefaultHttpRequestFactory.1 */
    /* synthetic */ class C09721 {
        static final /* synthetic */ int[] f8332a;

        static {
            f8332a = new int[HttpMethod.values().length];
            try {
                f8332a[HttpMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8332a[HttpMethod.POST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8332a[HttpMethod.PUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8332a[HttpMethod.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public DefaultHttpRequestFactory() {
        this(new DefaultLogger());
    }

    public DefaultHttpRequestFactory(Logger logger) {
        this.f8333a = logger;
    }

    public void m13169a(PinningInfoProvider pinningInfoProvider) {
        if (this.f8334b != pinningInfoProvider) {
            this.f8334b = pinningInfoProvider;
            m13164a();
        }
    }

    private synchronized void m13164a() {
        this.f8336d = false;
        this.f8335c = null;
    }

    public HttpRequest m13168a(HttpMethod httpMethod, String str, Map<String, String> map) {
        HttpRequest a;
        switch (C09721.f8332a[httpMethod.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                a = HttpRequest.m13182a((CharSequence) str, (Map) map, true);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                a = HttpRequest.m13187b((CharSequence) str, (Map) map, true);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                a = HttpRequest.m13190d((CharSequence) str);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                a = HttpRequest.m13191e((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (m13165a(str) && this.f8334b != null) {
            SSLSocketFactory b = m13166b();
            if (b != null) {
                ((HttpsURLConnection) a.m13209a()).setSSLSocketFactory(b);
            }
        }
        return a;
    }

    private boolean m13165a(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith("https");
    }

    private synchronized SSLSocketFactory m13166b() {
        if (this.f8335c == null && !this.f8336d) {
            this.f8335c = m13167c();
        }
        return this.f8335c;
    }

    private synchronized SSLSocketFactory m13167c() {
        SSLSocketFactory a;
        this.f8336d = true;
        try {
            a = NetworkUtils.m13235a(this.f8334b);
            this.f8333a.m12867a("Fabric", "Custom SSL pinning enabled");
        } catch (Throwable e) {
            this.f8333a.m12874d("Fabric", "Exception while validating pinned certs", e);
            a = null;
        }
        return a;
    }
}
