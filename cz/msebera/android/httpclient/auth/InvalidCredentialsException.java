package cz.msebera.android.httpclient.auth;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException(String str) {
        super(str);
    }
}
