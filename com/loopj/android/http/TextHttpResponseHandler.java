package com.loopj.android.http;

import cz.msebera.android.httpclient.Header;
import org.jivesoftware.smack.util.StringUtils;

public abstract class TextHttpResponseHandler extends AsyncHttpResponseHandler {
    public abstract void m5506a(int i, Header[] headerArr, String str);

    public abstract void m5507a(int i, Header[] headerArr, String str, Throwable th);

    public TextHttpResponseHandler() {
        this(StringUtils.UTF8);
    }

    public TextHttpResponseHandler(String str) {
        m5482a(str);
    }

    public static String m5505a(byte[] bArr, String str) {
        String str2 = bArr == null ? null : new String(bArr, str);
        if (str2 != null) {
            try {
                if (str2.startsWith("\ufeff")) {
                    return str2.substring(1);
                }
            } catch (Throwable e) {
                AsyncHttpClient.f6518a.m10682b("TextHttpRH", "Encoding response into string failed", e);
                return null;
            }
        }
        return str2;
    }

    public void m5508a(int i, Header[] headerArr, byte[] bArr) {
        m5506a(i, headerArr, m5505a(bArr, m5501h()));
    }

    public void m5509a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        m5507a(i, headerArr, m5505a(bArr, m5501h()), th);
    }
}
