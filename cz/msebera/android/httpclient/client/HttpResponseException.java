package cz.msebera.android.httpclient.client;

public class HttpResponseException extends ClientProtocolException {
    private final int f7247a;

    public HttpResponseException(int i, String str) {
        super(str);
        this.f7247a = i;
    }
}
