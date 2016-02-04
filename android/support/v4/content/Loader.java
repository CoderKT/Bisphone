package android.support.v4.content;

import android.support.v4.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader<D> {
    int f342a;
    OnLoadCompleteListener<D> f343b;
    OnLoadCanceledListener<D> f344c;
    boolean f345d;
    boolean f346e;
    boolean f347f;
    boolean f348g;
    boolean f349h;

    public interface OnLoadCanceledListener<D> {
    }

    public interface OnLoadCompleteListener<D> {
    }

    public void m612a(int i, OnLoadCompleteListener<D> onLoadCompleteListener) {
        if (this.f343b != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.f343b = onLoadCompleteListener;
        this.f342a = i;
    }

    public void m614a(OnLoadCompleteListener<D> onLoadCompleteListener) {
        if (this.f343b == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.f343b != onLoadCompleteListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.f343b = null;
        }
    }

    public void m613a(OnLoadCanceledListener<D> onLoadCanceledListener) {
        if (this.f344c != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.f344c = onLoadCanceledListener;
    }

    public void m617b(OnLoadCanceledListener<D> onLoadCanceledListener) {
        if (this.f344c == null) {
            throw new IllegalStateException("No listener register");
        } else if (this.f344c != onLoadCanceledListener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        } else {
            this.f344c = null;
        }
    }

    public final void m611a() {
        this.f345d = true;
        this.f347f = false;
        this.f346e = false;
        m616b();
    }

    protected void m616b() {
    }

    public void m618c() {
        this.f345d = false;
        m619d();
    }

    protected void m619d() {
    }

    public void m620e() {
        m621f();
        this.f347f = true;
        this.f345d = false;
        this.f346e = false;
        this.f348g = false;
        this.f349h = false;
    }

    protected void m621f() {
    }

    public String m610a(D d) {
        StringBuilder stringBuilder = new StringBuilder(64);
        DebugUtils.m763a(d, stringBuilder);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        DebugUtils.m763a(this, stringBuilder);
        stringBuilder.append(" id=");
        stringBuilder.append(this.f342a);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void m615a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.f342a);
        printWriter.print(" mListener=");
        printWriter.println(this.f343b);
        if (this.f345d || this.f348g || this.f349h) {
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f345d);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.f348g);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.f349h);
        }
        if (this.f346e || this.f347f) {
            printWriter.print(str);
            printWriter.print("mAbandoned=");
            printWriter.print(this.f346e);
            printWriter.print(" mReset=");
            printWriter.println(this.f347f);
        }
    }
}
