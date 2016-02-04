package cz.msebera.android.httpclient.client.utils;

import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.TextUtils;
import java.net.URI;
import java.util.Iterator;
import java.util.Locale;
import java.util.Stack;

public class URIUtils {
    public static URI m11590a(URI uri, HttpHost httpHost, boolean z) {
        Args.m12722a((Object) uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        URIBuilder uRIBuilder = new URIBuilder(uri);
        if (httpHost != null) {
            uRIBuilder.m11579a(httpHost.m11386c());
            uRIBuilder.m11584c(httpHost.m11384a());
            uRIBuilder.m11578a(httpHost.m11385b());
        } else {
            uRIBuilder.m11579a(null);
            uRIBuilder.m11584c(null);
            uRIBuilder.m11578a(-1);
        }
        if (z) {
            uRIBuilder.m11588e(null);
        }
        if (TextUtils.m12771a(uRIBuilder.m11587d())) {
            uRIBuilder.m11586d("/");
        }
        return uRIBuilder.m11581a();
    }

    public static URI m11589a(URI uri) {
        Args.m12722a((Object) uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        URIBuilder uRIBuilder = new URIBuilder(uri);
        if (uRIBuilder.m11583b() != null) {
            uRIBuilder.m11582b(null);
        }
        if (TextUtils.m12771a(uRIBuilder.m11587d())) {
            uRIBuilder.m11586d("/");
        }
        if (uRIBuilder.m11585c() != null) {
            uRIBuilder.m11584c(uRIBuilder.m11585c().toLowerCase(Locale.ENGLISH));
        }
        uRIBuilder.m11588e(null);
        return uRIBuilder.m11581a();
    }

    public static URI m11591a(URI uri, URI uri2) {
        Args.m12722a((Object) uri, "Base URI");
        Args.m12722a((Object) uri2, "Reference URI");
        String uri3 = uri2.toString();
        if (uri3.startsWith("?")) {
            return m11593b(uri, uri2);
        }
        int i = uri3.length() == 0 ? 1 : 0;
        if (i != 0) {
            uri2 = URI.create("#");
        }
        URI resolve = uri.resolve(uri2);
        if (i != 0) {
            uri3 = resolve.toString();
            resolve = URI.create(uri3.substring(0, uri3.indexOf(35)));
        }
        return m11594c(resolve);
    }

    private static URI m11593b(URI uri, URI uri2) {
        String uri3 = uri.toString();
        if (uri3.indexOf(63) > -1) {
            uri3 = uri3.substring(0, uri3.indexOf(63));
        }
        return URI.create(uri3 + uri2.toString());
    }

    private static URI m11594c(URI uri) {
        if (uri.isOpaque() || uri.getAuthority() == null) {
            return uri;
        }
        Args.m12724a(uri.isAbsolute(), "Base URI must be absolute");
        String path = uri.getPath() == null ? "" : uri.getPath();
        String[] split = path.split("/");
        Stack stack = new Stack();
        for (String str : split) {
            if (!(str.length() == 0 || ".".equals(str))) {
                if (!"..".equals(str)) {
                    stack.push(str);
                } else if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            stringBuilder.append('/').append((String) it.next());
        }
        if (path.lastIndexOf(47) == path.length() - 1) {
            stringBuilder.append('/');
        }
        try {
            URI uri2 = new URI(uri.getScheme().toLowerCase(Locale.ENGLISH), uri.getAuthority().toLowerCase(Locale.ENGLISH), stringBuilder.toString(), null, null);
            if (uri.getQuery() == null && uri.getFragment() == null) {
                return uri2;
            }
            StringBuilder stringBuilder2 = new StringBuilder(uri2.toASCIIString());
            if (uri.getQuery() != null) {
                stringBuilder2.append('?').append(uri.getRawQuery());
            }
            if (uri.getFragment() != null) {
                stringBuilder2.append('#').append(uri.getRawFragment());
            }
            return URI.create(stringBuilder2.toString());
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static HttpHost m11592b(URI uri) {
        if (uri == null) {
            return null;
        }
        HttpHost httpHost;
        if (uri.isAbsolute()) {
            int port = uri.getPort();
            Object host = uri.getHost();
            if (host == null) {
                String authority = uri.getAuthority();
                if (authority != null) {
                    String str;
                    int indexOf = authority.indexOf(64);
                    if (indexOf < 0) {
                        str = authority;
                    } else if (authority.length() > indexOf + 1) {
                        str = authority.substring(indexOf + 1);
                    } else {
                        str = null;
                    }
                    if (str != null) {
                        int indexOf2 = str.indexOf(58);
                        if (indexOf2 >= 0) {
                            indexOf = indexOf2 + 1;
                            int i = indexOf;
                            int i2 = 0;
                            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                                i2++;
                                i++;
                            }
                            if (i2 > 0) {
                                try {
                                    i = Integer.parseInt(str.substring(indexOf, indexOf + i2));
                                } catch (NumberFormatException e) {
                                    i = port;
                                }
                            } else {
                                i = port;
                            }
                            port = i;
                            host = str.substring(0, indexOf2);
                        }
                    }
                    authority = str;
                }
            }
            String scheme = uri.getScheme();
            if (!TextUtils.m12772b(host)) {
                httpHost = new HttpHost(host, port, scheme);
                return httpHost;
            }
        }
        httpHost = null;
        return httpHost;
    }
}
