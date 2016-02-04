package cz.msebera.android.httpclient.cookie;

public class CookieRestrictionViolationException extends MalformedCookieException {
    public CookieRestrictionViolationException(String str) {
        super(str);
    }
}
