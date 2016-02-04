package android.support.v4.util;

import android.util.Log;
import java.io.Writer;

public class LogWriter extends Writer {
    private final String f384a;
    private StringBuilder f385b;

    public LogWriter(String str) {
        this.f385b = new StringBuilder(128);
        this.f384a = str;
    }

    public void close() {
        m764a();
    }

    public void flush() {
        m764a();
    }

    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                m764a();
            } else {
                this.f385b.append(c);
            }
        }
    }

    private void m764a() {
        if (this.f385b.length() > 0) {
            Log.d(this.f384a, this.f385b.toString());
            this.f385b.delete(0, this.f385b.length());
        }
    }
}
