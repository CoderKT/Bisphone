package io.fabric.sdk.android.services.common;

public abstract class Crash {
    private final String f8226a;

    public class FatalException extends Crash {
        public FatalException(String str) {
            super(str);
        }
    }

    public class LoggedException extends Crash {
        public LoggedException(String str) {
            super(str);
        }
    }

    public Crash(String str) {
        this.f8226a = str;
    }

    public String m13040a() {
        return this.f8226a;
    }
}
