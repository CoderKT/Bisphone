package cz.msebera.android.httpclient.conn;

public class ConnectionPoolTimeoutException extends ConnectTimeoutException {
    public ConnectionPoolTimeoutException(String str) {
        super(str);
    }
}
