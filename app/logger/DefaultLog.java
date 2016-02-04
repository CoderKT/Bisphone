package app.logger;

import android.util.Log;

public class DefaultLog implements ILog {
    private int f3161a;

    public DefaultLog(int i) {
        this.f3161a = i;
    }

    public void m5688a(String str) {
        if (this.f3161a >= 1) {
            StackTraceElement a = m5686a();
            Log.e(m5687a(a), str + "        @ " + a.toString());
        }
    }

    public void m5690b(String str) {
        if (this.f3161a >= 2) {
            StackTraceElement a = m5686a();
            Log.w(m5687a(a), str + "        @ " + a.toString());
        }
    }

    public void m5692c(String str) {
        if (this.f3161a >= 3) {
            StackTraceElement a = m5686a();
            Log.i(m5687a(a), str + "        @ " + a.toString());
        }
    }

    public void m5694d(String str) {
        if (this.f3161a >= 4) {
            StackTraceElement a = m5686a();
            Log.d(m5687a(a), str + "        @ " + a.toString());
        }
    }

    public void m5696e(String str) {
        if (this.f3161a >= 5) {
            StackTraceElement a = m5686a();
            Log.v(m5687a(a), str + "        @ " + a.toString());
        }
    }

    public void m5689a(Throwable th) {
        if (this.f3161a >= 1) {
            StackTraceElement a = m5686a();
            Log.e(m5687a(a), th.getClass().getName() + "        @ " + a.toString(), th);
        }
    }

    public void m5691b(Throwable th) {
        if (this.f3161a >= 2) {
            StackTraceElement a = m5686a();
            Log.w(m5687a(a), th.getClass().getName() + "        @ " + a.toString(), th);
        }
    }

    public void m5693c(Throwable th) {
        if (this.f3161a >= 4) {
            StackTraceElement a = m5686a();
            Log.d(m5687a(a), th.getClass().getName() + "        @ " + a.toString(), th);
        }
    }

    public void m5695d(Throwable th) {
        if (this.f3161a >= 5) {
            StackTraceElement a = m5686a();
            Log.v(m5687a(a), th.getClass().getName() + "        @ " + a.toString(), th);
        }
    }

    private StackTraceElement m5686a() {
        return Thread.currentThread().getStackTrace()[4];
    }

    private String m5687a(StackTraceElement stackTraceElement) {
        int lastIndexOf = stackTraceElement.getClassName().lastIndexOf(".") + 1;
        int indexOf = stackTraceElement.getClassName().indexOf("$");
        if (indexOf == -1) {
            return stackTraceElement.getClassName().substring(lastIndexOf);
        }
        return stackTraceElement.getClassName().substring(lastIndexOf, indexOf);
    }
}
