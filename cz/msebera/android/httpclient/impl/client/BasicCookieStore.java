package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieIdentityComparator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class BasicCookieStore implements CookieStore, Serializable {
    private final TreeSet<Cookie> f7569a;

    public BasicCookieStore() {
        this.f7569a = new TreeSet(new CookieIdentityComparator());
    }

    public synchronized void m12078a(Cookie cookie) {
        if (cookie != null) {
            this.f7569a.remove(cookie);
            if (!cookie.m11769a(new Date())) {
                this.f7569a.add(cookie);
            }
        }
    }

    public synchronized List<Cookie> m12077a() {
        return new ArrayList(this.f7569a);
    }

    public synchronized String toString() {
        return this.f7569a.toString();
    }
}
