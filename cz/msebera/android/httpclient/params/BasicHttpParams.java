package cz.msebera.android.httpclient.params;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public class BasicHttpParams extends AbstractHttpParams implements Serializable, Cloneable {
    private final Map<String, Object> f7905a;

    public BasicHttpParams() {
        this.f7905a = new ConcurrentHashMap();
    }

    public Object m12666a(String str) {
        return this.f7905a.get(str);
    }

    public HttpParams m12665a(String str, Object obj) {
        if (str != null) {
            if (obj != null) {
                this.f7905a.put(str, obj);
            } else {
                this.f7905a.remove(str);
            }
        }
        return this;
    }

    public Object clone() {
        HttpParams httpParams = (BasicHttpParams) super.clone();
        m12667a(httpParams);
        return httpParams;
    }

    public void m12667a(HttpParams httpParams) {
        for (Entry entry : this.f7905a.entrySet()) {
            httpParams.m12083a((String) entry.getKey(), entry.getValue());
        }
    }
}
