package cz.msebera.android.httpclient.params;

@Deprecated
public abstract class AbstractHttpParams implements HttpParams {
    protected AbstractHttpParams() {
    }

    public long m12090a(String str, long j) {
        Object a = m12084a(str);
        return a == null ? j : ((Long) a).longValue();
    }

    public HttpParams m12093b(String str, long j) {
        m12083a(str, (Object) Long.valueOf(j));
        return this;
    }

    public int m12089a(String str, int i) {
        Object a = m12084a(str);
        return a == null ? i : ((Integer) a).intValue();
    }

    public HttpParams m12092b(String str, int i) {
        m12083a(str, (Object) Integer.valueOf(i));
        return this;
    }

    public boolean m12091a(String str, boolean z) {
        Object a = m12084a(str);
        return a == null ? z : ((Boolean) a).booleanValue();
    }

    public HttpParams m12094b(String str, boolean z) {
        m12083a(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }
}
