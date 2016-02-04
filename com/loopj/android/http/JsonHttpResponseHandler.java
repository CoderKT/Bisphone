package com.loopj.android.http;

import cz.msebera.android.httpclient.Header;
import org.jivesoftware.smack.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonHttpResponseHandler extends TextHttpResponseHandler {
    private boolean f3042a;

    /* renamed from: com.loopj.android.http.JsonHttpResponseHandler.1 */
    class C08901 implements Runnable {
        final /* synthetic */ byte[] f6571a;
        final /* synthetic */ int f6572b;
        final /* synthetic */ Header[] f6573c;
        final /* synthetic */ JsonHttpResponseHandler f6574d;

        /* renamed from: com.loopj.android.http.JsonHttpResponseHandler.1.1 */
        class C08881 implements Runnable {
            final /* synthetic */ Object f6567a;
            final /* synthetic */ C08901 f6568b;

            C08881(C08901 c08901, Object obj) {
                this.f6568b = c08901;
                this.f6567a = obj;
            }

            public void run() {
                if (!this.f6568b.f6574d.f3042a && this.f6567a == null) {
                    this.f6568b.f6574d.m5512a(this.f6568b.f6572b, this.f6568b.f6573c, (String) null);
                } else if (this.f6567a instanceof JSONObject) {
                    this.f6568b.f6574d.m5517a(this.f6568b.f6572b, this.f6568b.f6573c, (JSONObject) this.f6567a);
                } else if (this.f6567a instanceof JSONArray) {
                    this.f6568b.f6574d.m5516a(this.f6568b.f6572b, this.f6568b.f6573c, (JSONArray) this.f6567a);
                } else if (!(this.f6567a instanceof String)) {
                    this.f6568b.f6574d.m5515a(this.f6568b.f6572b, this.f6568b.f6573c, new JSONException("Unexpected response type " + this.f6567a.getClass().getName()), (JSONObject) null);
                } else if (this.f6568b.f6574d.f3042a) {
                    this.f6568b.f6574d.m5513a(this.f6568b.f6572b, this.f6568b.f6573c, (String) this.f6567a, new JSONException("Response cannot be parsed as JSON data"));
                } else {
                    this.f6568b.f6574d.m5512a(this.f6568b.f6572b, this.f6568b.f6573c, (String) this.f6567a);
                }
            }
        }

        /* renamed from: com.loopj.android.http.JsonHttpResponseHandler.1.2 */
        class C08892 implements Runnable {
            final /* synthetic */ JSONException f6569a;
            final /* synthetic */ C08901 f6570b;

            C08892(C08901 c08901, JSONException jSONException) {
                this.f6570b = c08901;
                this.f6569a = jSONException;
            }

            public void run() {
                this.f6570b.f6574d.m5515a(this.f6570b.f6572b, this.f6570b.f6573c, this.f6569a, (JSONObject) null);
            }
        }

        C08901(JsonHttpResponseHandler jsonHttpResponseHandler, byte[] bArr, int i, Header[] headerArr) {
            this.f6574d = jsonHttpResponseHandler;
            this.f6571a = bArr;
            this.f6572b = i;
            this.f6573c = headerArr;
        }

        public void run() {
            try {
                this.f6574d.m5481a(new C08881(this, this.f6574d.m5511a(this.f6571a)));
            } catch (JSONException e) {
                this.f6574d.m5481a(new C08892(this, e));
            }
        }
    }

    /* renamed from: com.loopj.android.http.JsonHttpResponseHandler.2 */
    class C08932 implements Runnable {
        final /* synthetic */ byte[] f6579a;
        final /* synthetic */ int f6580b;
        final /* synthetic */ Header[] f6581c;
        final /* synthetic */ Throwable f6582d;
        final /* synthetic */ JsonHttpResponseHandler f6583e;

        /* renamed from: com.loopj.android.http.JsonHttpResponseHandler.2.1 */
        class C08911 implements Runnable {
            final /* synthetic */ Object f6575a;
            final /* synthetic */ C08932 f6576b;

            C08911(C08932 c08932, Object obj) {
                this.f6576b = c08932;
                this.f6575a = obj;
            }

            public void run() {
                if (!this.f6576b.f6583e.f3042a && this.f6575a == null) {
                    this.f6576b.f6583e.m5513a(this.f6576b.f6580b, this.f6576b.f6581c, (String) null, this.f6576b.f6582d);
                } else if (this.f6575a instanceof JSONObject) {
                    this.f6576b.f6583e.m5515a(this.f6576b.f6580b, this.f6576b.f6581c, this.f6576b.f6582d, (JSONObject) this.f6575a);
                } else if (this.f6575a instanceof JSONArray) {
                    this.f6576b.f6583e.m5514a(this.f6576b.f6580b, this.f6576b.f6581c, this.f6576b.f6582d, (JSONArray) this.f6575a);
                } else if (this.f6575a instanceof String) {
                    this.f6576b.f6583e.m5513a(this.f6576b.f6580b, this.f6576b.f6581c, (String) this.f6575a, this.f6576b.f6582d);
                } else {
                    this.f6576b.f6583e.m5515a(this.f6576b.f6580b, this.f6576b.f6581c, new JSONException("Unexpected response type " + this.f6575a.getClass().getName()), (JSONObject) null);
                }
            }
        }

        /* renamed from: com.loopj.android.http.JsonHttpResponseHandler.2.2 */
        class C08922 implements Runnable {
            final /* synthetic */ JSONException f6577a;
            final /* synthetic */ C08932 f6578b;

            C08922(C08932 c08932, JSONException jSONException) {
                this.f6578b = c08932;
                this.f6577a = jSONException;
            }

            public void run() {
                this.f6578b.f6583e.m5515a(this.f6578b.f6580b, this.f6578b.f6581c, this.f6577a, (JSONObject) null);
            }
        }

        C08932(JsonHttpResponseHandler jsonHttpResponseHandler, byte[] bArr, int i, Header[] headerArr, Throwable th) {
            this.f6583e = jsonHttpResponseHandler;
            this.f6579a = bArr;
            this.f6580b = i;
            this.f6581c = headerArr;
            this.f6582d = th;
        }

        public void run() {
            try {
                this.f6583e.m5481a(new C08911(this, this.f6583e.m5511a(this.f6579a)));
            } catch (JSONException e) {
                this.f6583e.m5481a(new C08922(this, e));
            }
        }
    }

    public JsonHttpResponseHandler() {
        super(StringUtils.UTF8);
        this.f3042a = true;
    }

    public void m5517a(int i, Header[] headerArr, JSONObject jSONObject) {
        AsyncHttpClient.f6518a.m10684d("JsonHttpRH", "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
    }

    public void m5516a(int i, Header[] headerArr, JSONArray jSONArray) {
        AsyncHttpClient.f6518a.m10684d("JsonHttpRH", "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
    }

    public void m5515a(int i, Header[] headerArr, Throwable th, JSONObject jSONObject) {
        AsyncHttpClient.f6518a.m10680a("JsonHttpRH", "onFailure(int, Header[], Throwable, JSONObject) was not overriden, but callback was received", th);
    }

    public void m5514a(int i, Header[] headerArr, Throwable th, JSONArray jSONArray) {
        AsyncHttpClient.f6518a.m10680a("JsonHttpRH", "onFailure(int, Header[], Throwable, JSONArray) was not overriden, but callback was received", th);
    }

    public void m5513a(int i, Header[] headerArr, String str, Throwable th) {
        AsyncHttpClient.f6518a.m10680a("JsonHttpRH", "onFailure(int, Header[], String, Throwable) was not overriden, but callback was received", th);
    }

    public void m5512a(int i, Header[] headerArr, String str) {
        AsyncHttpClient.f6518a.m10684d("JsonHttpRH", "onSuccess(int, Header[], String) was not overriden, but callback was received");
    }

    public final void m5518a(int i, Header[] headerArr, byte[] bArr) {
        if (i != 204) {
            Runnable c08901 = new C08901(this, bArr, i, headerArr);
            if (m5499f() || m5500g()) {
                c08901.run();
                return;
            } else {
                new Thread(c08901).start();
                return;
            }
        }
        m5517a(i, headerArr, new JSONObject());
    }

    public final void m5519a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        if (bArr != null) {
            Runnable c08932 = new C08932(this, bArr, i, headerArr, th);
            if (m5499f() || m5500g()) {
                c08932.run();
                return;
            } else {
                new Thread(c08932).start();
                return;
            }
        }
        AsyncHttpClient.f6518a.m10679a("JsonHttpRH", "response body is null, calling onFailure(Throwable, JSONObject)");
        m5515a(i, headerArr, th, (JSONObject) null);
    }

    protected Object m5511a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        String str;
        Object nextValue;
        Object obj;
        String str2;
        String a = TextHttpResponseHandler.m5505a(bArr, m5501h());
        if (a != null) {
            a = a.trim();
            if (this.f3042a) {
                if (a.startsWith("{") || a.startsWith("[")) {
                    str = a;
                    nextValue = new JSONTokener(a).nextValue();
                    obj = str;
                    if (nextValue == null) {
                        return nextValue;
                    }
                    return obj;
                }
            } else if ((a.startsWith("{") && a.endsWith("}")) || (a.startsWith("[") && a.endsWith("]"))) {
                str = a;
                nextValue = new JSONTokener(a).nextValue();
                str2 = str;
                if (nextValue == null) {
                    return nextValue;
                }
                return obj;
            } else if (a.startsWith("\"") && a.endsWith("\"")) {
                str = a;
                a = a.substring(1, a.length() - 1);
                str2 = str;
                if (nextValue == null) {
                    return obj;
                }
                return nextValue;
            }
        }
        str = a;
        nextValue = null;
        str2 = str;
        if (nextValue == null) {
            return obj;
        }
        return nextValue;
    }
}
