package cz.msebera.android.httpclient.client.utils;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.conn.util.InetAddressUtils;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class URIBuilder {
    private String f7308a;
    private String f7309b;
    private String f7310c;
    private String f7311d;
    private String f7312e;
    private String f7313f;
    private int f7314g;
    private String f7315h;
    private String f7316i;
    private String f7317j;
    private List<NameValuePair> f7318k;
    private String f7319l;
    private String f7320m;
    private String f7321n;

    public URIBuilder() {
        this.f7314g = -1;
    }

    public URIBuilder(URI uri) {
        m11571a(uri);
    }

    private List<NameValuePair> m11570a(String str, Charset charset) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return URLEncodedUtils.m11602a(str, charset);
    }

    public URI m11581a() {
        return new URI(m11573e());
    }

    private String m11573e() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f7308a != null) {
            stringBuilder.append(this.f7308a).append(':');
        }
        if (this.f7309b != null) {
            stringBuilder.append(this.f7309b);
        } else {
            if (this.f7310c != null) {
                stringBuilder.append("//").append(this.f7310c);
            } else if (this.f7313f != null) {
                stringBuilder.append("//");
                if (this.f7312e != null) {
                    stringBuilder.append(this.f7312e).append("@");
                } else if (this.f7311d != null) {
                    stringBuilder.append(m11574f(this.f7311d)).append("@");
                }
                if (InetAddressUtils.m11767d(this.f7313f)) {
                    stringBuilder.append("[").append(this.f7313f).append("]");
                } else {
                    stringBuilder.append(this.f7313f);
                }
                if (this.f7314g >= 0) {
                    stringBuilder.append(":").append(this.f7314g);
                }
            }
            if (this.f7316i != null) {
                stringBuilder.append(m11577i(this.f7316i));
            } else if (this.f7315h != null) {
                stringBuilder.append(m11575g(m11577i(this.f7315h)));
            }
            if (this.f7317j != null) {
                stringBuilder.append("?").append(this.f7317j);
            } else if (this.f7318k != null) {
                stringBuilder.append("?").append(m11572b(this.f7318k));
            } else if (this.f7319l != null) {
                stringBuilder.append("?").append(m11576h(this.f7319l));
            }
        }
        if (this.f7321n != null) {
            stringBuilder.append("#").append(this.f7321n);
        } else if (this.f7320m != null) {
            stringBuilder.append("#").append(m11576h(this.f7320m));
        }
        return stringBuilder.toString();
    }

    private void m11571a(URI uri) {
        this.f7308a = uri.getScheme();
        this.f7309b = uri.getRawSchemeSpecificPart();
        this.f7310c = uri.getRawAuthority();
        this.f7313f = uri.getHost();
        this.f7314g = uri.getPort();
        this.f7312e = uri.getRawUserInfo();
        this.f7311d = uri.getUserInfo();
        this.f7316i = uri.getRawPath();
        this.f7315h = uri.getPath();
        this.f7317j = uri.getRawQuery();
        this.f7318k = m11570a(uri.getRawQuery(), Consts.f7197a);
        this.f7321n = uri.getRawFragment();
        this.f7320m = uri.getFragment();
    }

    private String m11574f(String str) {
        return URLEncodedUtils.m11604b(str, Consts.f7197a);
    }

    private String m11575g(String str) {
        return URLEncodedUtils.m11606d(str, Consts.f7197a);
    }

    private String m11572b(List<NameValuePair> list) {
        return URLEncodedUtils.m11596a((Iterable) list, Consts.f7197a);
    }

    private String m11576h(String str) {
        return URLEncodedUtils.m11605c(str, Consts.f7197a);
    }

    public URIBuilder m11579a(String str) {
        this.f7308a = str;
        return this;
    }

    public URIBuilder m11582b(String str) {
        this.f7311d = str;
        this.f7309b = null;
        this.f7310c = null;
        this.f7312e = null;
        return this;
    }

    public URIBuilder m11584c(String str) {
        this.f7313f = str;
        this.f7309b = null;
        this.f7310c = null;
        return this;
    }

    public URIBuilder m11578a(int i) {
        if (i < 0) {
            i = -1;
        }
        this.f7314g = i;
        this.f7309b = null;
        this.f7310c = null;
        return this;
    }

    public URIBuilder m11586d(String str) {
        this.f7315h = str;
        this.f7309b = null;
        this.f7316i = null;
        return this;
    }

    public URIBuilder m11580a(List<NameValuePair> list) {
        if (this.f7318k == null) {
            this.f7318k = new ArrayList();
        }
        this.f7318k.addAll(list);
        this.f7317j = null;
        this.f7309b = null;
        this.f7319l = null;
        return this;
    }

    public URIBuilder m11588e(String str) {
        this.f7320m = str;
        this.f7321n = null;
        return this;
    }

    public String m11583b() {
        return this.f7311d;
    }

    public String m11585c() {
        return this.f7313f;
    }

    public String m11587d() {
        return this.f7315h;
    }

    public String toString() {
        return m11573e();
    }

    private static String m11577i(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        while (i < str.length() && str.charAt(i) == '/') {
            i++;
        }
        if (i > 1) {
            return str.substring(i - 1);
        }
        return str;
    }
}
