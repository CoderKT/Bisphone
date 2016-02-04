package cz.msebera.android.httpclient;

import java.io.IOException;

public class MessageConstraintException extends IOException {
    public MessageConstraintException(String str) {
        super(str);
    }
}
