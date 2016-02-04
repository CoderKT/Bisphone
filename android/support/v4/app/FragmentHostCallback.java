package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.util.SimpleArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback<E> extends FragmentContainer {
    private final Activity f137a;
    final Context f138b;
    final int f139c;
    final FragmentManagerImpl f140d;
    private final Handler f141e;
    private SimpleArrayMap<String, LoaderManager> f142f;
    private boolean f143g;
    private LoaderManagerImpl f144h;
    private boolean f145i;
    private boolean f146j;

    FragmentHostCallback(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, fragmentActivity.f151a, 0);
    }

    FragmentHostCallback(Activity activity, Context context, Handler handler, int i) {
        this.f140d = new FragmentManagerImpl();
        this.f137a = activity;
        this.f138b = context;
        this.f141e = handler;
        this.f139c = i;
    }

    public void m274a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public boolean m277a(Fragment fragment) {
        return true;
    }

    public LayoutInflater m278b() {
        return (LayoutInflater) this.f138b.getSystemService("layout_inflater");
    }

    public void m281c() {
    }

    public void m271a(Fragment fragment, Intent intent, int i) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.f138b.startActivity(intent);
    }

    public boolean m282d() {
        return true;
    }

    public int m283e() {
        return this.f139c;
    }

    public View m270a(int i) {
        return null;
    }

    public boolean m276a() {
        return true;
    }

    Activity m284f() {
        return this.f137a;
    }

    Context m285g() {
        return this.f138b;
    }

    Handler m286h() {
        return this.f141e;
    }

    FragmentManagerImpl m287i() {
        return this.f140d;
    }

    LoaderManagerImpl m288j() {
        if (this.f144h != null) {
            return this.f144h;
        }
        this.f145i = true;
        this.f144h = m269a("(root)", this.f146j, true);
        return this.f144h;
    }

    void m273a(String str) {
        if (this.f142f != null) {
            LoaderManagerImpl loaderManagerImpl = (LoaderManagerImpl) this.f142f.get(str);
            if (loaderManagerImpl != null && !loaderManagerImpl.f268f) {
                loaderManagerImpl.m502h();
                this.f142f.remove(str);
            }
        }
    }

    void m279b(Fragment fragment) {
    }

    boolean m289k() {
        return this.f143g;
    }

    void m290l() {
        if (!this.f146j) {
            this.f146j = true;
            if (this.f144h != null) {
                this.f144h.m496b();
            } else if (!this.f145i) {
                this.f144h = m269a("(root)", this.f146j, false);
                if (!(this.f144h == null || this.f144h.f267e)) {
                    this.f144h.m496b();
                }
            }
            this.f145i = true;
        }
    }

    void m275a(boolean z) {
        this.f143g = z;
        if (this.f144h != null && this.f146j) {
            this.f146j = false;
            if (z) {
                this.f144h.m498d();
            } else {
                this.f144h.m497c();
            }
        }
    }

    void m291m() {
        if (this.f144h != null) {
            this.f144h.m502h();
        }
    }

    void m292n() {
        if (this.f142f != null) {
            int size = this.f142f.size();
            LoaderManagerImpl[] loaderManagerImplArr = new LoaderManagerImpl[size];
            for (int i = size - 1; i >= 0; i--) {
                loaderManagerImplArr[i] = (LoaderManagerImpl) this.f142f.m753c(i);
            }
            for (int i2 = 0; i2 < size; i2++) {
                LoaderManagerImpl loaderManagerImpl = loaderManagerImplArr[i2];
                loaderManagerImpl.m499e();
                loaderManagerImpl.m501g();
            }
        }
    }

    LoaderManagerImpl m269a(String str, boolean z, boolean z2) {
        if (this.f142f == null) {
            this.f142f = new SimpleArrayMap();
        }
        LoaderManagerImpl loaderManagerImpl = (LoaderManagerImpl) this.f142f.get(str);
        if (loaderManagerImpl != null) {
            loaderManagerImpl.m493a(this);
            return loaderManagerImpl;
        } else if (!z2) {
            return loaderManagerImpl;
        } else {
            loaderManagerImpl = new LoaderManagerImpl(str, this, z);
            this.f142f.put(str, loaderManagerImpl);
            return loaderManagerImpl;
        }
    }

    SimpleArrayMap<String, LoaderManager> m293o() {
        int i;
        int i2 = 0;
        if (this.f142f != null) {
            int size = this.f142f.size();
            LoaderManagerImpl[] loaderManagerImplArr = new LoaderManagerImpl[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                loaderManagerImplArr[i3] = (LoaderManagerImpl) this.f142f.m753c(i3);
            }
            i = 0;
            while (i2 < size) {
                LoaderManagerImpl loaderManagerImpl = loaderManagerImplArr[i2];
                if (loaderManagerImpl.f268f) {
                    i = 1;
                } else {
                    loaderManagerImpl.m502h();
                    this.f142f.remove(loaderManagerImpl.f266d);
                }
                i2++;
            }
        } else {
            i = 0;
        }
        if (i != 0) {
            return this.f142f;
        }
        return null;
    }

    void m272a(SimpleArrayMap<String, LoaderManager> simpleArrayMap) {
        this.f142f = simpleArrayMap;
    }

    void m280b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.f146j);
        if (this.f144h != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.f144h)));
            printWriter.println(":");
            this.f144h.m494a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }
}
