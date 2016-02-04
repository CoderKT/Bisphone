package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Fragment implements ComponentCallbacks, OnCreateContextMenuListener {
    private static final SimpleArrayMap<String, Class<?>> f81a;
    static final Object f82j;
    boolean f83A;
    int f84B;
    FragmentManagerImpl f85C;
    FragmentHostCallback f86D;
    FragmentManagerImpl f87E;
    Fragment f88F;
    int f89G;
    int f90H;
    String f91I;
    boolean f92J;
    boolean f93K;
    boolean f94L;
    boolean f95M;
    boolean f96N;
    boolean f97O;
    boolean f98P;
    int f99Q;
    ViewGroup f100R;
    View f101S;
    View f102T;
    boolean f103U;
    boolean f104V;
    LoaderManagerImpl f105W;
    boolean f106X;
    boolean f107Y;
    Object f108Z;
    Object aa;
    Object ab;
    Object ac;
    Object ad;
    Object ae;
    Boolean af;
    Boolean ag;
    SharedElementCallback ah;
    SharedElementCallback ai;
    int f109k;
    View f110l;
    int f111m;
    Bundle f112n;
    SparseArray<Parcelable> f113o;
    int f114p;
    String f115q;
    Bundle f116r;
    Fragment f117s;
    int f118t;
    int f119u;
    boolean f120v;
    boolean f121w;
    boolean f122x;
    boolean f123y;
    boolean f124z;

    /* renamed from: android.support.v4.app.Fragment.1 */
    class C00051 extends FragmentContainer {
        final /* synthetic */ Fragment f134a;

        C00051(Fragment fragment) {
            this.f134a = fragment;
        }

        public View m265a(int i) {
            if (this.f134a.f101S != null) {
                return this.f134a.f101S.findViewById(i);
            }
            throw new IllegalStateException("Fragment does not have a view");
        }

        public boolean m266a() {
            return this.f134a.f101S != null;
        }
    }

    public class InstantiationException extends RuntimeException {
        public InstantiationException(String str, Exception exception) {
            super(str, exception);
        }
    }

    public class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        final Bundle f135a;

        /* renamed from: android.support.v4.app.Fragment.SavedState.1 */
        final class C00061 implements Creator<SavedState> {
            C00061() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m267a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m268a(i);
            }

            public SavedState m267a(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            public SavedState[] m268a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Bundle bundle) {
            this.f135a = bundle;
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            this.f135a = parcel.readBundle();
            if (classLoader != null && this.f135a != null) {
                this.f135a.setClassLoader(classLoader);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.f135a);
        }

        static {
            CREATOR = new C00061();
        }
    }

    static {
        f81a = new SimpleArrayMap();
        f82j = new Object();
    }

    public Fragment() {
        this.f109k = 0;
        this.f114p = -1;
        this.f118t = -1;
        this.f97O = true;
        this.f104V = true;
        this.f108Z = null;
        this.aa = f82j;
        this.ab = null;
        this.ac = f82j;
        this.ad = null;
        this.ae = f82j;
        this.ah = null;
        this.ai = null;
    }

    public static Fragment m168a(Context context, String str) {
        return m169a(context, str, null);
    }

    public static Fragment m169a(Context context, String str, Bundle bundle) {
        try {
            Class cls = (Class) f81a.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                f81a.put(str, cls);
            }
            Fragment fragment = (Fragment) cls.newInstance();
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.f116r = bundle;
            }
            return fragment;
        } catch (Exception e) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e);
        } catch (Exception e2) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e2);
        } catch (Exception e22) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e22);
        }
    }

    static boolean m170b(Context context, String str) {
        try {
            Class cls = (Class) f81a.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                f81a.put(str, cls);
            }
            return Fragment.class.isAssignableFrom(cls);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    final void m220f(Bundle bundle) {
        if (this.f113o != null) {
            this.f102T.restoreHierarchyState(this.f113o);
            this.f113o = null;
        }
        this.f98P = false;
        m226h(bundle);
        if (!this.f98P) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    final void m186a(int i, Fragment fragment) {
        this.f114p = i;
        if (fragment != null) {
            this.f115q = fragment.f115q + ":" + this.f114p;
        } else {
            this.f115q = "android:fragment:" + this.f114p;
        }
    }

    final boolean m222f() {
        return this.f84B > 0;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        DebugUtils.m763a(this, stringBuilder);
        if (this.f114p >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.f114p);
        }
        if (this.f89G != 0) {
            stringBuilder.append(" id=0x");
            stringBuilder.append(Integer.toHexString(this.f89G));
        }
        if (this.f91I != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.f91I);
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void m224g(Bundle bundle) {
        if (this.f114p >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        this.f116r = bundle;
    }

    public final Bundle m223g() {
        return this.f116r;
    }

    public void m196a(SavedState savedState) {
        if (this.f114p >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        Bundle bundle = (savedState == null || savedState.f135a == null) ? null : savedState.f135a;
        this.f112n = bundle;
    }

    public Context m225h() {
        return this.f86D == null ? null : this.f86D.m285g();
    }

    public final FragmentActivity m227i() {
        return this.f86D == null ? null : (FragmentActivity) this.f86D.m284f();
    }

    public final Resources m229j() {
        if (this.f86D != null) {
            return this.f86D.m285g().getResources();
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final String a_(int i) {
        return m229j().getString(i);
    }

    public final FragmentManager m231k() {
        return this.f85C;
    }

    public final FragmentManager m233l() {
        if (this.f87E == null) {
            m174D();
            if (this.f109k >= 5) {
                this.f87E.m424o();
            } else if (this.f109k >= 4) {
                this.f87E.m423n();
            } else if (this.f109k >= 2) {
                this.f87E.m422m();
            } else if (this.f109k >= 1) {
                this.f87E.m421l();
            }
        }
        return this.f87E;
    }

    public final boolean m234m() {
        return this.f86D != null && this.f120v;
    }

    public final boolean m235n() {
        return this.f121w;
    }

    public final boolean m236o() {
        return this.f92J;
    }

    public void m209c(boolean z) {
    }

    public void m215d(boolean z) {
        if (this.f96N != z) {
            this.f96N = z;
            if (m234m() && !m236o()) {
                this.f86D.m281c();
            }
        }
    }

    public void m219e(boolean z) {
        if (this.f97O != z) {
            this.f97O = z;
            if (this.f96N && m234m() && !m236o()) {
                this.f86D.m281c();
            }
        }
    }

    public void m221f(boolean z) {
        if (!this.f104V && z && this.f109k < 4) {
            this.f85C.m399b(this);
        }
        this.f104V = z;
        this.f103U = !z;
    }

    public void m192a(Intent intent) {
        if (this.f86D == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.f86D.m271a(this, intent, -1);
    }

    public void m193a(Intent intent, int i) {
        if (this.f86D == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.f86D.m271a(this, intent, i);
    }

    public void m185a(int i, int i2, Intent intent) {
    }

    public void m187a(int i, String[] strArr, int[] iArr) {
    }

    public LayoutInflater m202b(Bundle bundle) {
        LayoutInflater b = this.f86D.m278b();
        m233l();
        LayoutInflaterCompat.m919a(b, this.f87E.m431v());
        return b;
    }

    public void m191a(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.f98P = true;
        Activity f = this.f86D == null ? null : this.f86D.m284f();
        if (f != null) {
            this.f98P = false;
            m189a(f, attributeSet, bundle);
        }
    }

    @Deprecated
    public void m189a(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.f98P = true;
    }

    public void m190a(Context context) {
        this.f98P = true;
        Activity f = this.f86D == null ? null : this.f86D.m284f();
        if (f != null) {
            this.f98P = false;
            m188a(f);
        }
    }

    @Deprecated
    public void m188a(Activity activity) {
        this.f98P = true;
    }

    public Animation m184a(int i, boolean z, int i2) {
        return null;
    }

    public void m195a(Bundle bundle) {
        this.f98P = true;
    }

    public View m183a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void m199a(View view, Bundle bundle) {
    }

    public View m237p() {
        return this.f101S;
    }

    public void m213d(Bundle bundle) {
        this.f98P = true;
    }

    public void m226h(Bundle bundle) {
        this.f98P = true;
    }

    public void m208c() {
        this.f98P = true;
        if (!this.f106X) {
            this.f106X = true;
            if (!this.f107Y) {
                this.f107Y = true;
                this.f105W = this.f86D.m269a(this.f115q, this.f106X, false);
            }
            if (this.f105W != null) {
                this.f105W.m496b();
            }
        }
    }

    public void m238q() {
        this.f98P = true;
    }

    public void m218e(Bundle bundle) {
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.f98P = true;
    }

    public void m239r() {
        this.f98P = true;
    }

    public void m212d() {
        this.f98P = true;
    }

    public void onLowMemory() {
        this.f98P = true;
    }

    public void m217e() {
        this.f98P = true;
    }

    public void m240s() {
        this.f98P = true;
        if (!this.f107Y) {
            this.f107Y = true;
            this.f105W = this.f86D.m269a(this.f115q, this.f106X, false);
        }
        if (this.f105W != null) {
            this.f105W.m502h();
        }
    }

    void m241t() {
        this.f114p = -1;
        this.f115q = null;
        this.f120v = false;
        this.f121w = false;
        this.f122x = false;
        this.f123y = false;
        this.f124z = false;
        this.f83A = false;
        this.f84B = 0;
        this.f85C = null;
        this.f87E = null;
        this.f86D = null;
        this.f89G = 0;
        this.f90H = 0;
        this.f91I = null;
        this.f92J = false;
        this.f93K = false;
        this.f95M = false;
        this.f105W = null;
        this.f106X = false;
        this.f107Y = false;
    }

    public void m204b() {
        this.f98P = true;
    }

    public void m198a(Menu menu, MenuInflater menuInflater) {
    }

    public void m197a(Menu menu) {
    }

    public void m242u() {
    }

    public boolean m201a(MenuItem menuItem) {
        return false;
    }

    public void m205b(Menu menu) {
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        m227i().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean m207b(MenuItem menuItem) {
        return false;
    }

    public Object m243v() {
        return this.f108Z;
    }

    public Object m244w() {
        return this.aa == f82j ? m243v() : this.aa;
    }

    public Object m245x() {
        return this.ab;
    }

    public Object m246y() {
        return this.ac == f82j ? m245x() : this.ac;
    }

    public Object m247z() {
        return this.ad;
    }

    public Object m171A() {
        return this.ae == f82j ? m247z() : this.ae;
    }

    public boolean m172B() {
        return this.ag == null ? true : this.ag.booleanValue();
    }

    public boolean m173C() {
        return this.af == null ? true : this.af.booleanValue();
    }

    public void m200a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.f89G));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.f90H));
        printWriter.print(" mTag=");
        printWriter.println(this.f91I);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f109k);
        printWriter.print(" mIndex=");
        printWriter.print(this.f114p);
        printWriter.print(" mWho=");
        printWriter.print(this.f115q);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.f84B);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.f120v);
        printWriter.print(" mRemoving=");
        printWriter.print(this.f121w);
        printWriter.print(" mResumed=");
        printWriter.print(this.f122x);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.f123y);
        printWriter.print(" mInLayout=");
        printWriter.println(this.f124z);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.f92J);
        printWriter.print(" mDetached=");
        printWriter.print(this.f93K);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.f97O);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.f96N);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.f94L);
        printWriter.print(" mRetaining=");
        printWriter.print(this.f95M);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.f104V);
        if (this.f85C != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.f85C);
        }
        if (this.f86D != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.f86D);
        }
        if (this.f88F != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.f88F);
        }
        if (this.f116r != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f116r);
        }
        if (this.f112n != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f112n);
        }
        if (this.f113o != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f113o);
        }
        if (this.f117s != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.f117s);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.f119u);
        }
        if (this.f99Q != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(this.f99Q);
        }
        if (this.f100R != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.f100R);
        }
        if (this.f101S != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.f101S);
        }
        if (this.f102T != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.f101S);
        }
        if (this.f110l != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(this.f110l);
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(this.f111m);
        }
        if (this.f105W != null) {
            printWriter.print(str);
            printWriter.println("Loader Manager:");
            this.f105W.m494a(str + "  ", fileDescriptor, printWriter, strArr);
        }
        if (this.f87E != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.f87E + ":");
            this.f87E.m392a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    void m174D() {
        this.f87E = new FragmentManagerImpl();
        this.f87E.m390a(this.f86D, new C00051(this), this);
    }

    void m228i(Bundle bundle) {
        if (this.f87E != null) {
            this.f87E.m420k();
        }
        this.f98P = false;
        m195a(bundle);
        if (!this.f98P) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
        } else if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            if (parcelable != null) {
                if (this.f87E == null) {
                    m174D();
                }
                this.f87E.m386a(parcelable, null);
                this.f87E.m421l();
            }
        }
    }

    View m203b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f87E != null) {
            this.f87E.m420k();
        }
        return m183a(layoutInflater, viewGroup, bundle);
    }

    void m230j(Bundle bundle) {
        if (this.f87E != null) {
            this.f87E.m420k();
        }
        this.f98P = false;
        m213d(bundle);
        if (!this.f98P) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
        } else if (this.f87E != null) {
            this.f87E.m422m();
        }
    }

    void m175E() {
        if (this.f87E != null) {
            this.f87E.m420k();
            this.f87E.m416g();
        }
        this.f98P = false;
        m208c();
        if (this.f98P) {
            if (this.f87E != null) {
                this.f87E.m423n();
            }
            if (this.f105W != null) {
                this.f105W.m501g();
                return;
            }
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
    }

    void m176F() {
        if (this.f87E != null) {
            this.f87E.m420k();
            this.f87E.m416g();
        }
        this.f98P = false;
        m238q();
        if (!this.f98P) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        } else if (this.f87E != null) {
            this.f87E.m424o();
            this.f87E.m416g();
        }
    }

    void m194a(Configuration configuration) {
        onConfigurationChanged(configuration);
        if (this.f87E != null) {
            this.f87E.m384a(configuration);
        }
    }

    void m177G() {
        onLowMemory();
        if (this.f87E != null) {
            this.f87E.m430u();
        }
    }

    boolean m206b(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.f92J) {
            return false;
        }
        if (this.f96N && this.f97O) {
            z = true;
            m198a(menu, menuInflater);
        }
        if (this.f87E != null) {
            return z | this.f87E.m395a(menu, menuInflater);
        }
        return z;
    }

    boolean m210c(Menu menu) {
        boolean z = false;
        if (this.f92J) {
            return false;
        }
        if (this.f96N && this.f97O) {
            z = true;
            m197a(menu);
        }
        if (this.f87E != null) {
            return z | this.f87E.m394a(menu);
        }
        return z;
    }

    boolean m211c(MenuItem menuItem) {
        if (!this.f92J) {
            if (this.f96N && this.f97O && m201a(menuItem)) {
                return true;
            }
            if (this.f87E != null && this.f87E.m396a(menuItem)) {
                return true;
            }
        }
        return false;
    }

    boolean m216d(MenuItem menuItem) {
        if (!this.f92J) {
            if (m207b(menuItem)) {
                return true;
            }
            if (this.f87E != null && this.f87E.m403b(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void m214d(Menu menu) {
        if (!this.f92J) {
            if (this.f96N && this.f97O) {
                m205b(menu);
            }
            if (this.f87E != null) {
                this.f87E.m401b(menu);
            }
        }
    }

    void m232k(Bundle bundle) {
        m218e(bundle);
        if (this.f87E != null) {
            Parcelable j = this.f87E.m419j();
            if (j != null) {
                bundle.putParcelable("android:support:fragments", j);
            }
        }
    }

    void m178H() {
        if (this.f87E != null) {
            this.f87E.m425p();
        }
        this.f98P = false;
        m239r();
        if (!this.f98P) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    void m179I() {
        if (this.f87E != null) {
            this.f87E.m426q();
        }
        this.f98P = false;
        m212d();
        if (!this.f98P) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
        }
    }

    void m180J() {
        if (this.f87E != null) {
            this.f87E.m427r();
        }
        if (this.f106X) {
            this.f106X = false;
            if (!this.f107Y) {
                this.f107Y = true;
                this.f105W = this.f86D.m269a(this.f115q, this.f106X, false);
            }
            if (this.f105W == null) {
                return;
            }
            if (this.f86D.m289k()) {
                this.f105W.m498d();
            } else {
                this.f105W.m497c();
            }
        }
    }

    void m181K() {
        if (this.f87E != null) {
            this.f87E.m428s();
        }
        this.f98P = false;
        m217e();
        if (!this.f98P) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        } else if (this.f105W != null) {
            this.f105W.m500f();
        }
    }

    void m182L() {
        if (this.f87E != null) {
            this.f87E.m429t();
        }
        this.f98P = false;
        m240s();
        if (!this.f98P) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
        }
    }
}
