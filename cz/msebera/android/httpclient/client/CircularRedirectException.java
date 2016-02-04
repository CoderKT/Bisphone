package cz.msebera.android.httpclient.client;

public class CircularRedirectException extends RedirectException {
    public CircularRedirectException(String str) {
        super(str);
    }
}
