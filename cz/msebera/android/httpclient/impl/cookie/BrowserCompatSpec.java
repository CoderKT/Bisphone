package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpecFactory.SecurityLevel;
import cz.msebera.android.httpclient.message.BasicHeaderElement;
import cz.msebera.android.httpclient.message.BasicHeaderValueFormatter;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import cz.msebera.android.httpclient.util.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import se.emilsjolander.stickylistheaders.C1128R;

public class BrowserCompatSpec extends CookieSpecBase {
    private static final String[] f7770a;
    private final String[] f7771b;

    /* renamed from: cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpec.1 */
    class C09411 extends BasicPathHandler {
        final /* synthetic */ BrowserCompatSpec f7768a;

        C09411(BrowserCompatSpec browserCompatSpec) {
            this.f7768a = browserCompatSpec;
        }

        public void m12436a(Cookie cookie, CookieOrigin cookieOrigin) {
        }
    }

    /* renamed from: cz.msebera.android.httpclient.impl.cookie.BrowserCompatSpec.2 */
    /* synthetic */ class C09422 {
        static final /* synthetic */ int[] f7769a;

        static {
            f7769a = new int[SecurityLevel.values().length];
            try {
                f7769a[SecurityLevel.SECURITYLEVEL_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7769a[SecurityLevel.SECURITYLEVEL_IE_MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        f7770a = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z"};
    }

    public BrowserCompatSpec(String[] strArr, SecurityLevel securityLevel) {
        if (strArr != null) {
            this.f7771b = (String[]) strArr.clone();
        } else {
            this.f7771b = f7770a;
        }
        switch (C09422.f7769a[securityLevel.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                m12390a("path", new BasicPathHandler());
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                m12390a("path", new C09411(this));
                break;
            default:
                throw new RuntimeException("Unknown security level");
        }
        m12390a("domain", new BasicDomainHandler());
        m12390a("max-age", new BasicMaxAgeHandler());
        m12390a("secure", new BasicSecureHandler());
        m12390a("comment", new BasicCommentHandler());
        m12390a("expires", new BasicExpiresHandler(this.f7771b));
        m12390a("version", new BrowserCompatVersionAttributeHandler());
    }

    public BrowserCompatSpec(String[] strArr) {
        this(strArr, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public BrowserCompatSpec() {
        this(null, SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public List<Cookie> m12444a(Header header, CookieOrigin cookieOrigin) {
        Args.m12722a((Object) header, "Header");
        Args.m12722a((Object) cookieOrigin, "Cookie origin");
        if (header.m11361c().equalsIgnoreCase("Set-Cookie")) {
            HeaderElement[] e = header.m11363e();
            int i = 0;
            int i2 = 0;
            for (HeaderElement headerElement : e) {
                if (headerElement.m11367a("version") != null) {
                    i2 = 1;
                }
                if (headerElement.m11367a("expires") != null) {
                    i = 1;
                }
            }
            if (i == 0 && r0 != 0) {
                return m12439a(e, cookieOrigin);
            }
            CharArrayBuffer a;
            ParserCursor parserCursor;
            NetscapeDraftHeaderParser netscapeDraftHeaderParser = NetscapeDraftHeaderParser.f7777a;
            if (header instanceof FormattedHeader) {
                a = ((FormattedHeader) header).m11364a();
                parserCursor = new ParserCursor(((FormattedHeader) header).m11365b(), a.m12757c());
            } else {
                String d = header.m11362d();
                if (d == null) {
                    throw new MalformedCookieException("Header value is null");
                }
                a = new CharArrayBuffer(d.length());
                a.m12751a(d);
                parserCursor = new ParserCursor(0, a.m12757c());
            }
            HeaderElement a2 = netscapeDraftHeaderParser.m12460a(a, parserCursor);
            Object a3 = a2.m11368a();
            String b = a2.m11369b();
            if (a3 == null || TextUtils.m12772b(a3)) {
                throw new MalformedCookieException("Cookie name may not be empty");
            }
            SetCookie basicClientCookie = new BasicClientCookie(a3, b);
            basicClientCookie.m12406e(CookieSpecBase.m12437a(cookieOrigin));
            basicClientCookie.m12404d(CookieSpecBase.m12438b(cookieOrigin));
            NameValuePair[] c = a2.m11370c();
            for (i2 = c.length - 1; i2 >= 0; i2--) {
                NameValuePair nameValuePair = c[i2];
                String toLowerCase = nameValuePair.m11403a().toLowerCase(Locale.ENGLISH);
                basicClientCookie.m12395a(toLowerCase, nameValuePair.m11404b());
                CookieAttributeHandler a4 = m12389a(toLowerCase);
                if (a4 != null) {
                    a4.m11780a(basicClientCookie, nameValuePair.m11404b());
                }
            }
            if (i != 0) {
                basicClientCookie.m12394a(0);
            }
            return Collections.singletonList(basicClientCookie);
        }
        throw new MalformedCookieException("Unrecognized cookie header '" + header.toString() + "'");
    }

    private static boolean m12442b(String str) {
        return str != null && str.startsWith("\"") && str.endsWith("\"");
    }

    public List<Header> m12445a(List<Cookie> list) {
        Args.m12723a((Collection) list, "List of cookies");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(list.size() * 20);
        charArrayBuffer.m12751a("Cookie");
        charArrayBuffer.m12751a(": ");
        for (int i = 0; i < list.size(); i++) {
            Cookie cookie = (Cookie) list.get(i);
            if (i > 0) {
                charArrayBuffer.m12751a("; ");
            }
            String a = cookie.m11768a();
            String b = cookie.m11770b();
            if (cookie.m11776h() <= 0 || m12442b(b)) {
                charArrayBuffer.m12751a(a);
                charArrayBuffer.m12751a("=");
                if (b != null) {
                    charArrayBuffer.m12751a(b);
                }
            } else {
                BasicHeaderValueFormatter.f7863b.m12579a(charArrayBuffer, new BasicHeaderElement(a, b), false);
            }
        }
        List<Header> arrayList = new ArrayList(1);
        arrayList.add(new BufferedHeader(charArrayBuffer));
        return arrayList;
    }

    public int m12443a() {
        return 0;
    }

    public Header m12446b() {
        return null;
    }

    public String toString() {
        return "compatibility";
    }
}
