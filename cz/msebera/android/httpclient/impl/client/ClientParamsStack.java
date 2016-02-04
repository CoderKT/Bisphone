package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.params.AbstractHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;

@Deprecated
public class ClientParamsStack extends AbstractHttpParams {
    protected final HttpParams f7571a;
    protected final HttpParams f7572b;
    protected final HttpParams f7573c;
    protected final HttpParams f7574d;

    public ClientParamsStack(HttpParams httpParams, HttpParams httpParams2, HttpParams httpParams3, HttpParams httpParams4) {
        this.f7571a = httpParams;
        this.f7572b = httpParams2;
        this.f7573c = httpParams3;
        this.f7574d = httpParams4;
    }

    public Object m12096a(String str) {
        Args.m12722a((Object) str, "Parameter name");
        Object obj = null;
        if (this.f7574d != null) {
            obj = this.f7574d.m12084a(str);
        }
        if (obj == null && this.f7573c != null) {
            obj = this.f7573c.m12084a(str);
        }
        if (obj == null && this.f7572b != null) {
            obj = this.f7572b.m12084a(str);
        }
        if (obj != null || this.f7571a == null) {
            return obj;
        }
        return this.f7571a.m12084a(str);
    }

    public HttpParams m12095a(String str, Object obj) {
        throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
    }
}
