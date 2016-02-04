package cz.msebera.android.httpclient;

import cz.msebera.android.httpclient.params.HttpParams;

public interface HttpMessage {
    void m10608a(Header header);

    @Deprecated
    void m10609a(HttpParams httpParams);

    void m10610a(String str, String str2);

    void m10611a(Header[] headerArr);

    boolean m10612a(String str);

    void m10613b(Header header);

    void m10614b(String str, String str2);

    Header[] m10615b(String str);

    Header m10616c(String str);

    ProtocolVersion m10617d();

    void m10618d(String str);

    HeaderIterator m10619e(String str);

    Header[] m10620e();

    HeaderIterator m10621f();

    @Deprecated
    HttpParams m10622g();
}
