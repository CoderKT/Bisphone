package cz.msebera.android.httpclient.client.entity;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.utils.URLEncodedUtils;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.nio.charset.Charset;
import java.util.List;

public class UrlEncodedFormEntity extends StringEntity {
    public UrlEncodedFormEntity(List<? extends NameValuePair> list, String str) {
        String str2;
        if (str != null) {
            str2 = str;
        } else {
            str2 = HTTP.f7912a.name();
        }
        super(URLEncodedUtils.m11601a((List) list, str2), ContentType.m11824a("application/x-www-form-urlencoded", str));
    }

    public UrlEncodedFormEntity(Iterable<? extends NameValuePair> iterable, Charset charset) {
        super(URLEncodedUtils.m11596a((Iterable) iterable, charset != null ? charset : HTTP.f7912a), ContentType.m11825a("application/x-www-form-urlencoded", charset));
    }
}
