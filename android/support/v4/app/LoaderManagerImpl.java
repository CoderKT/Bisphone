package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCanceledListener;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* compiled from: LoaderManager */
class LoaderManagerImpl extends LoaderManager {
    static boolean f263a;
    final SparseArrayCompat<LoaderInfo> f264b;
    final SparseArrayCompat<LoaderInfo> f265c;
    final String f266d;
    boolean f267e;
    boolean f268f;
    private FragmentHostCallback f269g;

    /* compiled from: LoaderManager */
    final class LoaderInfo implements OnLoadCanceledListener<Object>, OnLoadCompleteListener<Object> {
        final int f248a;
        final Bundle f249b;
        LoaderCallbacks<Object> f250c;
        Loader<Object> f251d;
        boolean f252e;
        boolean f253f;
        Object f254g;
        boolean f255h;
        boolean f256i;
        boolean f257j;
        boolean f258k;
        boolean f259l;
        boolean f260m;
        LoaderInfo f261n;
        final /* synthetic */ LoaderManagerImpl f262o;

        void m484a() {
            if (this.f256i && this.f257j) {
                this.f255h = true;
            } else if (!this.f255h) {
                this.f255h = true;
                if (LoaderManagerImpl.f263a) {
                    Log.v("LoaderManager", "  Starting: " + this);
                }
                if (this.f251d == null && this.f250c != null) {
                    this.f251d = this.f250c.m480a(this.f248a, this.f249b);
                }
                if (this.f251d == null) {
                    return;
                }
                if (!this.f251d.getClass().isMemberClass() || Modifier.isStatic(this.f251d.getClass().getModifiers())) {
                    if (!this.f260m) {
                        this.f251d.m612a(this.f248a, this);
                        this.f251d.m613a((OnLoadCanceledListener) this);
                        this.f260m = true;
                    }
                    this.f251d.m611a();
                    return;
                }
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.f251d);
            }
        }

        void m487b() {
            if (LoaderManagerImpl.f263a) {
                Log.v("LoaderManager", "  Retaining: " + this);
            }
            this.f256i = true;
            this.f257j = this.f255h;
            this.f255h = false;
            this.f250c = null;
        }

        void m488c() {
            if (this.f256i) {
                if (LoaderManagerImpl.f263a) {
                    Log.v("LoaderManager", "  Finished Retaining: " + this);
                }
                this.f256i = false;
                if (!(this.f255h == this.f257j || this.f255h)) {
                    m490e();
                }
            }
            if (this.f255h && this.f252e && !this.f258k) {
                m485a(this.f251d, this.f254g);
            }
        }

        void m489d() {
            if (this.f255h && this.f258k) {
                this.f258k = false;
                if (this.f252e) {
                    m485a(this.f251d, this.f254g);
                }
            }
        }

        void m490e() {
            if (LoaderManagerImpl.f263a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.f255h = false;
            if (!this.f256i && this.f251d != null && this.f260m) {
                this.f260m = false;
                this.f251d.m614a((OnLoadCompleteListener) this);
                this.f251d.m617b(this);
                this.f251d.m618c();
            }
        }

        void m491f() {
            String str;
            LoaderCallbacks loaderCallbacks = null;
            if (LoaderManagerImpl.f263a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.f259l = true;
            boolean z = this.f253f;
            this.f253f = false;
            if (this.f250c != null && this.f251d != null && this.f252e && z) {
                if (LoaderManagerImpl.f263a) {
                    Log.v("LoaderManager", "  Reseting: " + this);
                }
                if (this.f262o.f269g != null) {
                    String str2 = this.f262o.f269g.f140d.f199v;
                    this.f262o.f269g.f140d.f199v = "onLoaderReset";
                    str = str2;
                } else {
                    str = null;
                }
                try {
                    this.f250c.m481a(this.f251d);
                } finally {
                    loaderCallbacks = this.f262o.f269g;
                    if (loaderCallbacks != null) {
                        loaderCallbacks = this.f262o.f269g.f140d;
                        loaderCallbacks.f199v = str;
                    }
                }
            }
            this.f250c = loaderCallbacks;
            this.f254g = loaderCallbacks;
            this.f252e = false;
            if (this.f251d != null) {
                if (this.f260m) {
                    this.f260m = false;
                    this.f251d.m614a((OnLoadCompleteListener) this);
                    this.f251d.m617b(this);
                }
                this.f251d.m620e();
            }
            if (this.f261n != null) {
                this.f261n.m491f();
            }
        }

        void m485a(Loader<Object> loader, Object obj) {
            String str;
            if (this.f250c != null) {
                if (this.f262o.f269g != null) {
                    String str2 = this.f262o.f269g.f140d.f199v;
                    this.f262o.f269g.f140d.f199v = "onLoadFinished";
                    str = str2;
                } else {
                    str = null;
                }
                try {
                    if (LoaderManagerImpl.f263a) {
                        Log.v("LoaderManager", "  onLoadFinished in " + loader + ": " + loader.m610a(obj));
                    }
                    this.f250c.m482a((Loader) loader, obj);
                    this.f253f = true;
                } finally {
                    if (this.f262o.f269g != null) {
                        this.f262o.f269g.f140d.f199v = str;
                    }
                }
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append("LoaderInfo{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" #");
            stringBuilder.append(this.f248a);
            stringBuilder.append(" : ");
            DebugUtils.m763a(this.f251d, stringBuilder);
            stringBuilder.append("}}");
            return stringBuilder.toString();
        }

        public void m486a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.f248a);
            printWriter.print(" mArgs=");
            printWriter.println(this.f249b);
            printWriter.print(str);
            printWriter.print("mCallbacks=");
            printWriter.println(this.f250c);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.f251d);
            if (this.f251d != null) {
                this.f251d.m615a(str + "  ", fileDescriptor, printWriter, strArr);
            }
            if (this.f252e || this.f253f) {
                printWriter.print(str);
                printWriter.print("mHaveData=");
                printWriter.print(this.f252e);
                printWriter.print("  mDeliveredData=");
                printWriter.println(this.f253f);
                printWriter.print(str);
                printWriter.print("mData=");
                printWriter.println(this.f254g);
            }
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.print(this.f255h);
            printWriter.print(" mReportNextStart=");
            printWriter.print(this.f258k);
            printWriter.print(" mDestroyed=");
            printWriter.println(this.f259l);
            printWriter.print(str);
            printWriter.print("mRetaining=");
            printWriter.print(this.f256i);
            printWriter.print(" mRetainingStarted=");
            printWriter.print(this.f257j);
            printWriter.print(" mListenerRegistered=");
            printWriter.println(this.f260m);
            if (this.f261n != null) {
                printWriter.print(str);
                printWriter.println("Pending Loader ");
                printWriter.print(this.f261n);
                printWriter.println(":");
                this.f261n.m486a(str + "  ", fileDescriptor, printWriter, strArr);
            }
        }
    }

    static {
        f263a = false;
    }

    LoaderManagerImpl(String str, FragmentHostCallback fragmentHostCallback, boolean z) {
        this.f264b = new SparseArrayCompat();
        this.f265c = new SparseArrayCompat();
        this.f266d = str;
        this.f269g = fragmentHostCallback;
        this.f267e = z;
    }

    void m493a(FragmentHostCallback fragmentHostCallback) {
        this.f269g = fragmentHostCallback;
    }

    void m496b() {
        if (f263a) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.f267e) {
            Throwable runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
            return;
        }
        this.f267e = true;
        for (int b = this.f264b.m792b() - 1; b >= 0; b--) {
            ((LoaderInfo) this.f264b.m793b(b)).m484a();
        }
    }

    void m497c() {
        if (f263a) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (this.f267e) {
            for (int b = this.f264b.m792b() - 1; b >= 0; b--) {
                ((LoaderInfo) this.f264b.m793b(b)).m490e();
            }
            this.f267e = false;
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
    }

    void m498d() {
        if (f263a) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (this.f267e) {
            this.f268f = true;
            this.f267e = false;
            for (int b = this.f264b.m792b() - 1; b >= 0; b--) {
                ((LoaderInfo) this.f264b.m793b(b)).m487b();
            }
            return;
        }
        Throwable runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
    }

    void m499e() {
        if (this.f268f) {
            if (f263a) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.f268f = false;
            for (int b = this.f264b.m792b() - 1; b >= 0; b--) {
                ((LoaderInfo) this.f264b.m793b(b)).m488c();
            }
        }
    }

    void m500f() {
        for (int b = this.f264b.m792b() - 1; b >= 0; b--) {
            ((LoaderInfo) this.f264b.m793b(b)).f258k = true;
        }
    }

    void m501g() {
        for (int b = this.f264b.m792b() - 1; b >= 0; b--) {
            ((LoaderInfo) this.f264b.m793b(b)).m489d();
        }
    }

    void m502h() {
        int b;
        if (!this.f268f) {
            if (f263a) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (b = this.f264b.m792b() - 1; b >= 0; b--) {
                ((LoaderInfo) this.f264b.m793b(b)).m491f();
            }
            this.f264b.m794c();
        }
        if (f263a) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (b = this.f265c.m792b() - 1; b >= 0; b--) {
            ((LoaderInfo) this.f265c.m793b(b)).m491f();
        }
        this.f265c.m794c();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("LoaderManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        DebugUtils.m763a(this.f269g, stringBuilder);
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void m494a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        if (this.f264b.m792b() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            String str2 = str + "    ";
            for (int i2 = 0; i2 < this.f264b.m792b(); i2++) {
                LoaderInfo loaderInfo = (LoaderInfo) this.f264b.m793b(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f264b.m790a(i2));
                printWriter.print(": ");
                printWriter.println(loaderInfo.toString());
                loaderInfo.m486a(str2, fileDescriptor, printWriter, strArr);
            }
        }
        if (this.f265c.m792b() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            String str3 = str + "    ";
            while (i < this.f265c.m792b()) {
                loaderInfo = (LoaderInfo) this.f265c.m793b(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f265c.m790a(i));
                printWriter.print(": ");
                printWriter.println(loaderInfo.toString());
                loaderInfo.m486a(str3, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    public boolean m495a() {
        int b = this.f264b.m792b();
        boolean z = false;
        for (int i = 0; i < b; i++) {
            int i2;
            LoaderInfo loaderInfo = (LoaderInfo) this.f264b.m793b(i);
            if (!loaderInfo.f255h || loaderInfo.f253f) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            z |= i2;
        }
        return z;
    }
}
