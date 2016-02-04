package app.http.listener;

import cz.msebera.android.httpclient.Header;
import java.io.File;

public abstract class FileTaskListener implements ITaskListener {
    protected final File f1993d;
    protected final boolean f1994e;
    protected final boolean f1995f;
    protected final boolean f1996g;

    public abstract void m3995a(int i, Header[] headerArr, File file);

    public abstract void m3996a(int i, Header[] headerArr, File file, Throwable th);

    public FileTaskListener(File file) {
        this(file, false);
    }

    public FileTaskListener(File file, boolean z) {
        this(file, z, false);
    }

    public FileTaskListener(File file, boolean z, boolean z2) {
        this(file, z, z2, false);
    }

    public FileTaskListener(File file, boolean z, boolean z2, boolean z3) {
        this.f1993d = file;
        this.f1994e = z;
        this.f1995f = z2;
        this.f1996g = z3;
    }

    public File m3993a() {
        return this.f1993d;
    }

    public boolean m4000b() {
        return this.f1994e;
    }

    public boolean m4001c() {
        return this.f1995f;
    }

    public boolean m4002d() {
        return this.f1996g;
    }

    public void m4003e() {
    }

    public void m4004f() {
    }

    public void m3999a(long j, long j2) {
    }

    public void m3994a(int i) {
    }

    public final void m3997a(int i, Header[] headerArr, byte[] bArr) {
        m3995a(i, headerArr, this.f1993d);
    }

    public final void m3998a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        m3996a(i, headerArr, this.f1993d, th);
    }

    public void m4005g() {
    }

    public void m4006h() {
    }
}
