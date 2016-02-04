package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

/* compiled from: FragmentManager */
final class FragmentManagerImpl extends FragmentManager implements LayoutInflaterFactory {
    static final Interpolator f174A;
    static final Interpolator f175B;
    static final Interpolator f176C;
    static final Interpolator f177D;
    static boolean f178a;
    static final boolean f179b;
    static Field f180r;
    ArrayList<Runnable> f181c;
    Runnable[] f182d;
    boolean f183e;
    ArrayList<Fragment> f184f;
    ArrayList<Fragment> f185g;
    ArrayList<Integer> f186h;
    ArrayList<BackStackRecord> f187i;
    ArrayList<Fragment> f188j;
    ArrayList<BackStackRecord> f189k;
    ArrayList<Integer> f190l;
    ArrayList<OnBackStackChangedListener> f191m;
    int f192n;
    FragmentHostCallback f193o;
    FragmentContainer f194p;
    Fragment f195q;
    boolean f196s;
    boolean f197t;
    boolean f198u;
    String f199v;
    boolean f200w;
    Bundle f201x;
    SparseArray<Parcelable> f202y;
    Runnable f203z;

    /* renamed from: android.support.v4.app.FragmentManagerImpl.1 */
    class FragmentManager implements Runnable {
        final /* synthetic */ FragmentManagerImpl f162a;

        FragmentManager(FragmentManagerImpl fragmentManagerImpl) {
            this.f162a = fragmentManagerImpl;
        }

        public void run() {
            this.f162a.m416g();
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl.4 */
    class FragmentManager implements Runnable {
        final /* synthetic */ int f163a;
        final /* synthetic */ int f164b;
        final /* synthetic */ FragmentManagerImpl f165c;

        FragmentManager(FragmentManagerImpl fragmentManagerImpl, int i, int i2) {
            this.f165c = fragmentManagerImpl;
            this.f163a = i;
            this.f164b = i2;
        }

        public void run() {
            this.f165c.m393a(this.f165c.f193o.m286h(), null, this.f163a, this.f164b);
        }
    }

    /* compiled from: FragmentManager */
    class AnimateOnHWLayerIfNeededListener implements AnimationListener {
        private AnimationListener f166a;
        private boolean f167b;
        private View f168c;

        /* renamed from: android.support.v4.app.FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.1 */
        class FragmentManager implements Runnable {
            final /* synthetic */ AnimateOnHWLayerIfNeededListener f171a;

            FragmentManager(AnimateOnHWLayerIfNeededListener animateOnHWLayerIfNeededListener) {
                this.f171a = animateOnHWLayerIfNeededListener;
            }

            public void run() {
                ViewCompat.m1158a(this.f171a.f168c, 2, null);
            }
        }

        /* renamed from: android.support.v4.app.FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.2 */
        class FragmentManager implements Runnable {
            final /* synthetic */ AnimateOnHWLayerIfNeededListener f172a;

            FragmentManager(AnimateOnHWLayerIfNeededListener animateOnHWLayerIfNeededListener) {
                this.f172a = animateOnHWLayerIfNeededListener;
            }

            public void run() {
                ViewCompat.m1158a(this.f172a.f168c, 0, null);
            }
        }

        public AnimateOnHWLayerIfNeededListener(View view, Animation animation) {
            this.f166a = null;
            this.f167b = false;
            this.f168c = null;
            if (view != null && animation != null) {
                this.f168c = view;
            }
        }

        public AnimateOnHWLayerIfNeededListener(View view, Animation animation, AnimationListener animationListener) {
            this.f166a = null;
            this.f167b = false;
            this.f168c = null;
            if (view != null && animation != null) {
                this.f166a = animationListener;
                this.f168c = view;
            }
        }

        public void onAnimationStart(Animation animation) {
            if (this.f168c != null) {
                this.f167b = FragmentManagerImpl.m366a(this.f168c, animation);
                if (this.f167b) {
                    this.f168c.post(new FragmentManager(this));
                }
            }
            if (this.f166a != null) {
                this.f166a.onAnimationStart(animation);
            }
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f168c != null && this.f167b) {
                this.f168c.post(new FragmentManager(this));
            }
            if (this.f166a != null) {
                this.f166a.onAnimationEnd(animation);
            }
        }

        public void onAnimationRepeat(Animation animation) {
            if (this.f166a != null) {
                this.f166a.onAnimationRepeat(animation);
            }
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl.5 */
    class FragmentManager extends AnimateOnHWLayerIfNeededListener {
        final /* synthetic */ Fragment f169a;
        final /* synthetic */ FragmentManagerImpl f170b;

        FragmentManager(FragmentManagerImpl fragmentManagerImpl, View view, Animation animation, Fragment fragment) {
            this.f170b = fragmentManagerImpl;
            this.f169a = fragment;
            super(view, animation);
        }

        public void onAnimationEnd(Animation animation) {
            super.onAnimationEnd(animation);
            if (this.f169a.f110l != null) {
                this.f169a.f110l = null;
                this.f170b.m388a(this.f169a, this.f169a.f111m, 0, 0, false);
            }
        }
    }

    /* compiled from: FragmentManager */
    class FragmentTag {
        public static final int[] f173a;

        static {
            f173a = new int[]{16842755, 16842960, 16842961};
        }
    }

    FragmentManagerImpl() {
        this.f192n = 0;
        this.f201x = null;
        this.f202y = null;
        this.f203z = new FragmentManager(this);
    }

    static {
        boolean z = false;
        f178a = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        f179b = z;
        f180r = null;
        f174A = new DecelerateInterpolator(2.5f);
        f175B = new DecelerateInterpolator(1.5f);
        f176C = new AccelerateInterpolator(2.5f);
        f177D = new AccelerateInterpolator(1.5f);
    }

    static boolean m367a(Animation animation) {
        if (animation instanceof AlphaAnimation) {
            return true;
        }
        if (!(animation instanceof AnimationSet)) {
            return false;
        }
        List animations = ((AnimationSet) animation).getAnimations();
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i) instanceof AlphaAnimation) {
                return true;
            }
        }
        return false;
    }

    static boolean m366a(View view, Animation animation) {
        return VERSION.SDK_INT >= 19 && ViewCompat.m1178g(view) == 0 && ViewCompat.m1189r(view) && m367a(animation);
    }

    private void m365a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        if (this.f193o != null) {
            try {
                this.f193o.m274a("  ", null, printWriter, new String[0]);
            } catch (Throwable e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                m392a("  ", null, printWriter, new String[0]);
            } catch (Throwable e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        }
        throw runtimeException;
    }

    public FragmentTransaction m377a() {
        return new BackStackRecord(this);
    }

    public boolean m402b() {
        return m416g();
    }

    public boolean m406c() {
        m371w();
        m402b();
        return m393a(this.f193o.m286h(), null, -1, 0);
    }

    public void m380a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Bad id: " + i);
        }
        m391a(new FragmentManager(this, i, i2), false);
    }

    public void m385a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.f114p < 0) {
            m365a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, fragment.f114p);
    }

    public Fragment m375a(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        if (i >= this.f184f.size()) {
            m365a(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        }
        Fragment fragment = (Fragment) this.f184f.get(i);
        if (fragment != null) {
            return fragment;
        }
        m365a(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        return fragment;
    }

    public List<Fragment> m407d() {
        return this.f184f;
    }

    public SavedState m373a(Fragment fragment) {
        if (fragment.f114p < 0) {
            m365a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.f109k <= 0) {
            return null;
        }
        Bundle g = m415g(fragment);
        if (g != null) {
            return new SavedState(g);
        }
        return null;
    }

    public boolean m412e() {
        return this.f198u;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("FragmentManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        if (this.f195q != null) {
            DebugUtils.m763a(this.f195q, stringBuilder);
        } else {
            DebugUtils.m763a(this.f193o, stringBuilder);
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void m392a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int i;
        Fragment fragment;
        int i2 = 0;
        String str2 = str + "    ";
        if (this.f184f != null) {
            size = this.f184f.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(":");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f184f.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment);
                    if (fragment != null) {
                        fragment.m200a(str2, fileDescriptor, printWriter, strArr);
                    }
                }
            }
        }
        if (this.f185g != null) {
            size = this.f185g.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Added Fragments:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f185g.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.f188j != null) {
            size = this.f188j.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Fragments Created Menus:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f188j.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.f187i != null) {
            size = this.f187i.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack:");
                for (i = 0; i < size; i++) {
                    BackStackRecord backStackRecord = (BackStackRecord) this.f187i.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(backStackRecord.toString());
                    backStackRecord.m156a(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        synchronized (this) {
            if (this.f189k != null) {
                int size2 = this.f189k.size();
                if (size2 > 0) {
                    printWriter.print(str);
                    printWriter.println("Back Stack Indices:");
                    for (i = 0; i < size2; i++) {
                        backStackRecord = (BackStackRecord) this.f189k.get(i);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i);
                        printWriter.print(": ");
                        printWriter.println(backStackRecord);
                    }
                }
            }
            if (this.f190l != null && this.f190l.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.f190l.toArray()));
            }
        }
        if (this.f181c != null) {
            i = this.f181c.size();
            if (i > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                while (i2 < i) {
                    Runnable runnable = (Runnable) this.f181c.get(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i2);
                    printWriter.print(": ");
                    printWriter.println(runnable);
                    i2++;
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f193o);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f194p);
        if (this.f195q != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f195q);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f192n);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.f197t);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.f198u);
        if (this.f196s) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f196s);
        }
        if (this.f199v != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.f199v);
        }
        if (this.f186h != null && this.f186h.size() > 0) {
            printWriter.print(str);
            printWriter.print("  mAvailIndices: ");
            printWriter.println(Arrays.toString(this.f186h.toArray()));
        }
    }

    static Animation m364a(Context context, float f, float f2, float f3, float f4) {
        Animation animationSet = new AnimationSet(false);
        Animation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f174A);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(f3, f4);
        scaleAnimation.setInterpolator(f175B);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    static Animation m363a(Context context, float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(f175B);
        alphaAnimation.setDuration(220);
        return alphaAnimation;
    }

    Animation m379a(Fragment fragment, int i, boolean z, int i2) {
        Animation a = fragment.m184a(i, z, fragment.f99Q);
        if (a != null) {
            return a;
        }
        if (fragment.f99Q != 0) {
            a = AnimationUtils.loadAnimation(this.f193o.m285g(), fragment.f99Q);
            if (a != null) {
                return a;
            }
        }
        if (i == 0) {
            return null;
        }
        int b = m368b(i, z);
        if (b < 0) {
            return null;
        }
        switch (b) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return m364a(this.f193o.m285g(), 1.125f, 1.0f, 0.0f, 1.0f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return m364a(this.f193o.m285g(), 1.0f, 0.975f, 1.0f, 0.0f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return m364a(this.f193o.m285g(), 0.975f, 1.0f, 0.0f, 1.0f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return m364a(this.f193o.m285g(), 1.0f, 1.075f, 1.0f, 0.0f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return m363a(this.f193o.m285g(), 0.0f, 1.0f);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return m363a(this.f193o.m285g(), 1.0f, 0.0f);
            default:
                if (i2 == 0 && this.f193o.m282d()) {
                    i2 = this.f193o.m283e();
                }
                if (i2 == 0) {
                    return null;
                }
                return null;
        }
    }

    public void m399b(Fragment fragment) {
        if (!fragment.f103U) {
            return;
        }
        if (this.f183e) {
            this.f200w = true;
            return;
        }
        fragment.f103U = false;
        m388a(fragment, this.f192n, 0, 0, false);
    }

    private void m369b(View view, Animation animation) {
        if (view != null && animation != null && m366a(view, animation)) {
            AnimationListener animationListener;
            try {
                if (f180r == null) {
                    f180r = Animation.class.getDeclaredField("mListener");
                    f180r.setAccessible(true);
                }
                animationListener = (AnimationListener) f180r.get(animation);
            } catch (Throwable e) {
                Log.e("FragmentManager", "No field with the name mListener is found in Animation class", e);
                animationListener = null;
            } catch (Throwable e2) {
                Log.e("FragmentManager", "Cannot access Animation's mListener field", e2);
                animationListener = null;
            }
            animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(view, animation, animationListener));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m388a(android.support.v4.app.Fragment r11, int r12, int r13, int r14, boolean r15) {
        /*
        r10 = this;
        r9 = 4;
        r6 = 3;
        r5 = 1;
        r3 = 0;
        r7 = 0;
        r0 = r11.f120v;
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = r11.f93K;
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        if (r12 <= r5) goto L_0x0010;
    L_0x000f:
        r12 = r5;
    L_0x0010:
        r0 = r11.f121w;
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r11.f109k;
        if (r12 <= r0) goto L_0x001a;
    L_0x0018:
        r12 = r11.f109k;
    L_0x001a:
        r0 = r11.f103U;
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r11.f109k;
        if (r0 >= r9) goto L_0x0025;
    L_0x0022:
        if (r12 <= r6) goto L_0x0025;
    L_0x0024:
        r12 = r6;
    L_0x0025:
        r0 = r11.f109k;
        if (r0 >= r12) goto L_0x0274;
    L_0x0029:
        r0 = r11.f123y;
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r11.f124z;
        if (r0 != 0) goto L_0x0032;
    L_0x0031:
        return;
    L_0x0032:
        r0 = r11.f110l;
        if (r0 == 0) goto L_0x0040;
    L_0x0036:
        r11.f110l = r7;
        r2 = r11.f111m;
        r0 = r10;
        r1 = r11;
        r4 = r3;
        r0.m388a(r1, r2, r3, r4, r5);
    L_0x0040:
        r0 = r11.f109k;
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x013e;
            case 2: goto L_0x020f;
            case 3: goto L_0x020f;
            case 4: goto L_0x0230;
            default: goto L_0x0045;
        };
    L_0x0045:
        r11.f109k = r12;
        goto L_0x0031;
    L_0x0048:
        r0 = f178a;
        if (r0 == 0) goto L_0x0064;
    L_0x004c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0064:
        r0 = r11.f112n;
        if (r0 == 0) goto L_0x00ac;
    L_0x0068:
        r0 = r11.f112n;
        r1 = r10.f193o;
        r1 = r1.m285g();
        r1 = r1.getClassLoader();
        r0.setClassLoader(r1);
        r0 = r11.f112n;
        r1 = "android:view_state";
        r0 = r0.getSparseParcelableArray(r1);
        r11.f113o = r0;
        r0 = r11.f112n;
        r1 = "android:target_state";
        r0 = r10.m375a(r0, r1);
        r11.f117s = r0;
        r0 = r11.f117s;
        if (r0 == 0) goto L_0x0099;
    L_0x008f:
        r0 = r11.f112n;
        r1 = "android:target_req_state";
        r0 = r0.getInt(r1, r3);
        r11.f119u = r0;
    L_0x0099:
        r0 = r11.f112n;
        r1 = "android:user_visible_hint";
        r0 = r0.getBoolean(r1, r5);
        r11.f104V = r0;
        r0 = r11.f104V;
        if (r0 != 0) goto L_0x00ac;
    L_0x00a7:
        r11.f103U = r5;
        if (r12 <= r6) goto L_0x00ac;
    L_0x00ab:
        r12 = r6;
    L_0x00ac:
        r0 = r10.f193o;
        r11.f86D = r0;
        r0 = r10.f195q;
        r11.f88F = r0;
        r0 = r10.f195q;
        if (r0 == 0) goto L_0x00ec;
    L_0x00b8:
        r0 = r10.f195q;
        r0 = r0.f87E;
    L_0x00bc:
        r11.f85C = r0;
        r11.f98P = r3;
        r0 = r10.f193o;
        r0 = r0.m285g();
        r11.m190a(r0);
        r0 = r11.f98P;
        if (r0 != 0) goto L_0x00f3;
    L_0x00cd:
        r0 = new android.support.v4.app.SuperNotCalledException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " did not call through to super.onAttach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00ec:
        r0 = r10.f193o;
        r0 = r0.m287i();
        goto L_0x00bc;
    L_0x00f3:
        r0 = r11.f88F;
        if (r0 != 0) goto L_0x00fc;
    L_0x00f7:
        r0 = r10.f193o;
        r0.m279b(r11);
    L_0x00fc:
        r0 = r11.f95M;
        if (r0 != 0) goto L_0x0105;
    L_0x0100:
        r0 = r11.f112n;
        r11.m228i(r0);
    L_0x0105:
        r11.f95M = r3;
        r0 = r11.f123y;
        if (r0 == 0) goto L_0x013e;
    L_0x010b:
        r0 = r11.f112n;
        r0 = r11.m202b(r0);
        r1 = r11.f112n;
        r0 = r11.m203b(r0, r7, r1);
        r11.f101S = r0;
        r0 = r11.f101S;
        if (r0 == 0) goto L_0x0263;
    L_0x011d:
        r0 = r11.f101S;
        r11.f102T = r0;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 11;
        if (r0 < r1) goto L_0x0259;
    L_0x0127:
        r0 = r11.f101S;
        android.support.v4.view.ViewCompat.m1165a(r0, r3);
    L_0x012c:
        r0 = r11.f92J;
        if (r0 == 0) goto L_0x0137;
    L_0x0130:
        r0 = r11.f101S;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x0137:
        r0 = r11.f101S;
        r1 = r11.f112n;
        r11.m199a(r0, r1);
    L_0x013e:
        if (r12 <= r5) goto L_0x020f;
    L_0x0140:
        r0 = f178a;
        if (r0 == 0) goto L_0x015c;
    L_0x0144:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x015c:
        r0 = r11.f123y;
        if (r0 != 0) goto L_0x01ff;
    L_0x0160:
        r0 = r11.f90H;
        if (r0 == 0) goto L_0x03d1;
    L_0x0164:
        r0 = r10.f194p;
        r1 = r11.f90H;
        r0 = r0.m263a(r1);
        r0 = (android.view.ViewGroup) r0;
        if (r0 != 0) goto L_0x01b3;
    L_0x0170:
        r1 = r11.f83A;
        if (r1 != 0) goto L_0x01b3;
    L_0x0174:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "No view found for id 0x";
        r2 = r2.append(r4);
        r4 = r11.f90H;
        r4 = java.lang.Integer.toHexString(r4);
        r2 = r2.append(r4);
        r4 = " (";
        r2 = r2.append(r4);
        r4 = r11.m229j();
        r8 = r11.f90H;
        r4 = r4.getResourceName(r8);
        r2 = r2.append(r4);
        r4 = ") for fragment ";
        r2 = r2.append(r4);
        r2 = r2.append(r11);
        r2 = r2.toString();
        r1.<init>(r2);
        r10.m365a(r1);
    L_0x01b3:
        r11.f100R = r0;
        r1 = r11.f112n;
        r1 = r11.m202b(r1);
        r2 = r11.f112n;
        r1 = r11.m203b(r1, r0, r2);
        r11.f101S = r1;
        r1 = r11.f101S;
        if (r1 == 0) goto L_0x0271;
    L_0x01c7:
        r1 = r11.f101S;
        r11.f102T = r1;
        r1 = android.os.Build.VERSION.SDK_INT;
        r2 = 11;
        if (r1 < r2) goto L_0x0267;
    L_0x01d1:
        r1 = r11.f101S;
        android.support.v4.view.ViewCompat.m1165a(r1, r3);
    L_0x01d6:
        if (r0 == 0) goto L_0x01ed;
    L_0x01d8:
        r1 = r10.m379a(r11, r13, r5, r14);
        if (r1 == 0) goto L_0x01e8;
    L_0x01de:
        r2 = r11.f101S;
        r10.m369b(r2, r1);
        r2 = r11.f101S;
        r2.startAnimation(r1);
    L_0x01e8:
        r1 = r11.f101S;
        r0.addView(r1);
    L_0x01ed:
        r0 = r11.f92J;
        if (r0 == 0) goto L_0x01f8;
    L_0x01f1:
        r0 = r11.f101S;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x01f8:
        r0 = r11.f101S;
        r1 = r11.f112n;
        r11.m199a(r0, r1);
    L_0x01ff:
        r0 = r11.f112n;
        r11.m230j(r0);
        r0 = r11.f101S;
        if (r0 == 0) goto L_0x020d;
    L_0x0208:
        r0 = r11.f112n;
        r11.m220f(r0);
    L_0x020d:
        r11.f112n = r7;
    L_0x020f:
        if (r12 <= r6) goto L_0x0230;
    L_0x0211:
        r0 = f178a;
        if (r0 == 0) goto L_0x022d;
    L_0x0215:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x022d:
        r11.m175E();
    L_0x0230:
        if (r12 <= r9) goto L_0x0045;
    L_0x0232:
        r0 = f178a;
        if (r0 == 0) goto L_0x024e;
    L_0x0236:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x024e:
        r11.f122x = r5;
        r11.m176F();
        r11.f112n = r7;
        r11.f113o = r7;
        goto L_0x0045;
    L_0x0259:
        r0 = r11.f101S;
        r0 = android.support.v4.app.NoSaveStateFrameLayout.m509a(r0);
        r11.f101S = r0;
        goto L_0x012c;
    L_0x0263:
        r11.f102T = r7;
        goto L_0x013e;
    L_0x0267:
        r1 = r11.f101S;
        r1 = android.support.v4.app.NoSaveStateFrameLayout.m509a(r1);
        r11.f101S = r1;
        goto L_0x01d6;
    L_0x0271:
        r11.f102T = r7;
        goto L_0x01ff;
    L_0x0274:
        r0 = r11.f109k;
        if (r0 <= r12) goto L_0x0045;
    L_0x0278:
        r0 = r11.f109k;
        switch(r0) {
            case 1: goto L_0x027f;
            case 2: goto L_0x02ff;
            case 3: goto L_0x02de;
            case 4: goto L_0x02bd;
            case 5: goto L_0x0299;
            default: goto L_0x027d;
        };
    L_0x027d:
        goto L_0x0045;
    L_0x027f:
        if (r12 >= r5) goto L_0x0045;
    L_0x0281:
        r0 = r10.f198u;
        if (r0 == 0) goto L_0x0290;
    L_0x0285:
        r0 = r11.f110l;
        if (r0 == 0) goto L_0x0290;
    L_0x0289:
        r0 = r11.f110l;
        r11.f110l = r7;
        r0.clearAnimation();
    L_0x0290:
        r0 = r11.f110l;
        if (r0 == 0) goto L_0x036e;
    L_0x0294:
        r11.f111m = r12;
        r12 = r5;
        goto L_0x0045;
    L_0x0299:
        r0 = 5;
        if (r12 >= r0) goto L_0x02bd;
    L_0x029c:
        r0 = f178a;
        if (r0 == 0) goto L_0x02b8;
    L_0x02a0:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02b8:
        r11.m178H();
        r11.f122x = r3;
    L_0x02bd:
        if (r12 >= r9) goto L_0x02de;
    L_0x02bf:
        r0 = f178a;
        if (r0 == 0) goto L_0x02db;
    L_0x02c3:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02db:
        r11.m179I();
    L_0x02de:
        if (r12 >= r6) goto L_0x02ff;
    L_0x02e0:
        r0 = f178a;
        if (r0 == 0) goto L_0x02fc;
    L_0x02e4:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STOPPED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02fc:
        r11.m180J();
    L_0x02ff:
        r0 = 2;
        if (r12 >= r0) goto L_0x027f;
    L_0x0302:
        r0 = f178a;
        if (r0 == 0) goto L_0x031e;
    L_0x0306:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x031e:
        r0 = r11.f101S;
        if (r0 == 0) goto L_0x0331;
    L_0x0322:
        r0 = r10.f193o;
        r0 = r0.m277a(r11);
        if (r0 == 0) goto L_0x0331;
    L_0x032a:
        r0 = r11.f113o;
        if (r0 != 0) goto L_0x0331;
    L_0x032e:
        r10.m414f(r11);
    L_0x0331:
        r11.m181K();
        r0 = r11.f101S;
        if (r0 == 0) goto L_0x0366;
    L_0x0338:
        r0 = r11.f100R;
        if (r0 == 0) goto L_0x0366;
    L_0x033c:
        r0 = r10.f192n;
        if (r0 <= 0) goto L_0x03ce;
    L_0x0340:
        r0 = r10.f198u;
        if (r0 != 0) goto L_0x03ce;
    L_0x0344:
        r0 = r10.m379a(r11, r13, r3, r14);
    L_0x0348:
        if (r0 == 0) goto L_0x035f;
    L_0x034a:
        r1 = r11.f101S;
        r11.f110l = r1;
        r11.f111m = r12;
        r1 = r11.f101S;
        r2 = new android.support.v4.app.FragmentManagerImpl$5;
        r2.<init>(r10, r1, r0, r11);
        r0.setAnimationListener(r2);
        r1 = r11.f101S;
        r1.startAnimation(r0);
    L_0x035f:
        r0 = r11.f100R;
        r1 = r11.f101S;
        r0.removeView(r1);
    L_0x0366:
        r11.f100R = r7;
        r11.f101S = r7;
        r11.f102T = r7;
        goto L_0x027f;
    L_0x036e:
        r0 = f178a;
        if (r0 == 0) goto L_0x038a;
    L_0x0372:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x038a:
        r0 = r11.f95M;
        if (r0 != 0) goto L_0x0391;
    L_0x038e:
        r11.m182L();
    L_0x0391:
        r11.f98P = r3;
        r11.m204b();
        r0 = r11.f98P;
        if (r0 != 0) goto L_0x03b9;
    L_0x039a:
        r0 = new android.support.v4.app.SuperNotCalledException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " did not call through to super.onDetach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x03b9:
        if (r15 != 0) goto L_0x0045;
    L_0x03bb:
        r0 = r11.f95M;
        if (r0 != 0) goto L_0x03c4;
    L_0x03bf:
        r10.m410e(r11);
        goto L_0x0045;
    L_0x03c4:
        r11.f86D = r7;
        r11.f88F = r7;
        r11.f85C = r7;
        r11.f87E = r7;
        goto L_0x0045;
    L_0x03ce:
        r0 = r7;
        goto L_0x0348;
    L_0x03d1:
        r0 = r7;
        goto L_0x01b3;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.a(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    void m404c(Fragment fragment) {
        m388a(fragment, this.f192n, 0, 0, false);
    }

    void m383a(int i, boolean z) {
        m381a(i, 0, 0, z);
    }

    void m381a(int i, int i2, int i3, boolean z) {
        if (this.f193o == null && i != 0) {
            throw new IllegalStateException("No host");
        } else if (z || this.f192n != i) {
            this.f192n = i;
            if (this.f184f != null) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < this.f184f.size()) {
                    int a;
                    Fragment fragment = (Fragment) this.f184f.get(i4);
                    if (fragment != null) {
                        m388a(fragment, i, i2, i3, false);
                        if (fragment.f105W != null) {
                            a = i5 | fragment.f105W.m495a();
                            i4++;
                            i5 = a;
                        }
                    }
                    a = i5;
                    i4++;
                    i5 = a;
                }
                if (i5 == 0) {
                    m413f();
                }
                if (this.f196s && this.f193o != null && this.f192n == 5) {
                    this.f193o.m281c();
                    this.f196s = false;
                }
            }
        }
    }

    void m413f() {
        if (this.f184f != null) {
            for (int i = 0; i < this.f184f.size(); i++) {
                Fragment fragment = (Fragment) this.f184f.get(i);
                if (fragment != null) {
                    m399b(fragment);
                }
            }
        }
    }

    void m408d(Fragment fragment) {
        if (fragment.f114p < 0) {
            if (this.f186h == null || this.f186h.size() <= 0) {
                if (this.f184f == null) {
                    this.f184f = new ArrayList();
                }
                fragment.m186a(this.f184f.size(), this.f195q);
                this.f184f.add(fragment);
            } else {
                fragment.m186a(((Integer) this.f186h.remove(this.f186h.size() - 1)).intValue(), this.f195q);
                this.f184f.set(fragment.f114p, fragment);
            }
            if (f178a) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }

    void m410e(Fragment fragment) {
        if (fragment.f114p >= 0) {
            if (f178a) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.f184f.set(fragment.f114p, null);
            if (this.f186h == null) {
                this.f186h = new ArrayList();
            }
            this.f186h.add(Integer.valueOf(fragment.f114p));
            this.f193o.m273a(fragment.f115q);
            fragment.m241t();
        }
    }

    public void m389a(Fragment fragment, boolean z) {
        if (this.f185g == null) {
            this.f185g = new ArrayList();
        }
        if (f178a) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        m408d(fragment);
        if (!fragment.f93K) {
            if (this.f185g.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            this.f185g.add(fragment);
            fragment.f120v = true;
            fragment.f121w = false;
            if (fragment.f96N && fragment.f97O) {
                this.f196s = true;
            }
            if (z) {
                m404c(fragment);
            }
        }
    }

    public void m387a(Fragment fragment, int i, int i2) {
        if (f178a) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.f84B);
        }
        boolean z = !fragment.m222f();
        if (!fragment.f93K || z) {
            int i3;
            if (this.f185g != null) {
                this.f185g.remove(fragment);
            }
            if (fragment.f96N && fragment.f97O) {
                this.f196s = true;
            }
            fragment.f120v = false;
            fragment.f121w = true;
            if (z) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            m388a(fragment, i3, i, i2, false);
        }
    }

    public void m400b(Fragment fragment, int i, int i2) {
        if (f178a) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.f92J) {
            fragment.f92J = true;
            if (fragment.f101S != null) {
                Animation a = m379a(fragment, i, false, i2);
                if (a != null) {
                    m369b(fragment.f101S, a);
                    fragment.f101S.startAnimation(a);
                }
                fragment.f101S.setVisibility(8);
            }
            if (fragment.f120v && fragment.f96N && fragment.f97O) {
                this.f196s = true;
            }
            fragment.m209c(true);
        }
    }

    public void m405c(Fragment fragment, int i, int i2) {
        if (f178a) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.f92J) {
            fragment.f92J = false;
            if (fragment.f101S != null) {
                Animation a = m379a(fragment, i, true, i2);
                if (a != null) {
                    m369b(fragment.f101S, a);
                    fragment.f101S.startAnimation(a);
                }
                fragment.f101S.setVisibility(0);
            }
            if (fragment.f120v && fragment.f96N && fragment.f97O) {
                this.f196s = true;
            }
            fragment.m209c(false);
        }
    }

    public void m409d(Fragment fragment, int i, int i2) {
        if (f178a) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.f93K) {
            fragment.f93K = true;
            if (fragment.f120v) {
                if (this.f185g != null) {
                    if (f178a) {
                        Log.v("FragmentManager", "remove from detach: " + fragment);
                    }
                    this.f185g.remove(fragment);
                }
                if (fragment.f96N && fragment.f97O) {
                    this.f196s = true;
                }
                fragment.f120v = false;
                m388a(fragment, 1, i, i2, false);
            }
        }
    }

    public void m411e(Fragment fragment, int i, int i2) {
        if (f178a) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.f93K) {
            fragment.f93K = false;
            if (!fragment.f120v) {
                if (this.f185g == null) {
                    this.f185g = new ArrayList();
                }
                if (this.f185g.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (f178a) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                this.f185g.add(fragment);
                fragment.f120v = true;
                if (fragment.f96N && fragment.f97O) {
                    this.f196s = true;
                }
                m388a(fragment, this.f192n, i, i2, false);
            }
        }
    }

    public Fragment m374a(int i) {
        int size;
        Fragment fragment;
        if (this.f185g != null) {
            for (size = this.f185g.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f185g.get(size);
                if (fragment != null && fragment.f89G == i) {
                    return fragment;
                }
            }
        }
        if (this.f184f != null) {
            for (size = this.f184f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f184f.get(size);
                if (fragment != null && fragment.f89G == i) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment m376a(String str) {
        int size;
        Fragment fragment;
        if (!(this.f185g == null || str == null)) {
            for (size = this.f185g.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f185g.get(size);
                if (fragment != null && str.equals(fragment.f91I)) {
                    return fragment;
                }
            }
        }
        if (!(this.f184f == null || str == null)) {
            for (size = this.f184f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f184f.get(size);
                if (fragment != null && str.equals(fragment.f91I)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    private void m371w() {
        if (this.f197t) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.f199v != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.f199v);
        }
    }

    public void m391a(Runnable runnable, boolean z) {
        if (!z) {
            m371w();
        }
        synchronized (this) {
            if (this.f198u || this.f193o == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
            if (this.f181c == null) {
                this.f181c = new ArrayList();
            }
            this.f181c.add(runnable);
            if (this.f181c.size() == 1) {
                this.f193o.m286h().removeCallbacks(this.f203z);
                this.f193o.m286h().post(this.f203z);
            }
        }
    }

    public int m372a(BackStackRecord backStackRecord) {
        int size;
        synchronized (this) {
            if (this.f190l == null || this.f190l.size() <= 0) {
                if (this.f189k == null) {
                    this.f189k = new ArrayList();
                }
                size = this.f189k.size();
                if (f178a) {
                    Log.v("FragmentManager", "Setting back stack index " + size + " to " + backStackRecord);
                }
                this.f189k.add(backStackRecord);
            } else {
                size = ((Integer) this.f190l.remove(this.f190l.size() - 1)).intValue();
                if (f178a) {
                    Log.v("FragmentManager", "Adding back stack index " + size + " with " + backStackRecord);
                }
                this.f189k.set(size, backStackRecord);
            }
        }
        return size;
    }

    public void m382a(int i, BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.f189k == null) {
                this.f189k = new ArrayList();
            }
            int size = this.f189k.size();
            if (i < size) {
                if (f178a) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + backStackRecord);
                }
                this.f189k.set(i, backStackRecord);
            } else {
                while (size < i) {
                    this.f189k.add(null);
                    if (this.f190l == null) {
                        this.f190l = new ArrayList();
                    }
                    if (f178a) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.f190l.add(Integer.valueOf(size));
                    size++;
                }
                if (f178a) {
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + backStackRecord);
                }
                this.f189k.add(backStackRecord);
            }
        }
    }

    public void m397b(int i) {
        synchronized (this) {
            this.f189k.set(i, null);
            if (this.f190l == null) {
                this.f190l = new ArrayList();
            }
            if (f178a) {
                Log.v("FragmentManager", "Freeing back stack index " + i);
            }
            this.f190l.add(Integer.valueOf(i));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m416g() {
        /*
        r6 = this;
        r0 = 1;
        r2 = 0;
        r1 = r6.f183e;
        if (r1 == 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalStateException;
        r1 = "Recursive entry to executePendingTransactions";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        r1 = android.os.Looper.myLooper();
        r3 = r6.f193o;
        r3 = r3.m286h();
        r3 = r3.getLooper();
        if (r1 == r3) goto L_0x0026;
    L_0x001e:
        r0 = new java.lang.IllegalStateException;
        r1 = "Must be called from main thread of process";
        r0.<init>(r1);
        throw r0;
    L_0x0026:
        r1 = r2;
    L_0x0027:
        monitor-enter(r6);
        r3 = r6.f181c;	 Catch:{ all -> 0x009b }
        if (r3 == 0) goto L_0x0034;
    L_0x002c:
        r3 = r6.f181c;	 Catch:{ all -> 0x009b }
        r3 = r3.size();	 Catch:{ all -> 0x009b }
        if (r3 != 0) goto L_0x005c;
    L_0x0034:
        monitor-exit(r6);	 Catch:{ all -> 0x009b }
        r0 = r6.f200w;
        if (r0 == 0) goto L_0x00a9;
    L_0x0039:
        r3 = r2;
        r4 = r2;
    L_0x003b:
        r0 = r6.f184f;
        r0 = r0.size();
        if (r3 >= r0) goto L_0x00a2;
    L_0x0043:
        r0 = r6.f184f;
        r0 = r0.get(r3);
        r0 = (android.support.v4.app.Fragment) r0;
        if (r0 == 0) goto L_0x0058;
    L_0x004d:
        r5 = r0.f105W;
        if (r5 == 0) goto L_0x0058;
    L_0x0051:
        r0 = r0.f105W;
        r0 = r0.m495a();
        r4 = r4 | r0;
    L_0x0058:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x003b;
    L_0x005c:
        r1 = r6.f181c;	 Catch:{ all -> 0x009b }
        r3 = r1.size();	 Catch:{ all -> 0x009b }
        r1 = r6.f182d;	 Catch:{ all -> 0x009b }
        if (r1 == 0) goto L_0x006b;
    L_0x0066:
        r1 = r6.f182d;	 Catch:{ all -> 0x009b }
        r1 = r1.length;	 Catch:{ all -> 0x009b }
        if (r1 >= r3) goto L_0x006f;
    L_0x006b:
        r1 = new java.lang.Runnable[r3];	 Catch:{ all -> 0x009b }
        r6.f182d = r1;	 Catch:{ all -> 0x009b }
    L_0x006f:
        r1 = r6.f181c;	 Catch:{ all -> 0x009b }
        r4 = r6.f182d;	 Catch:{ all -> 0x009b }
        r1.toArray(r4);	 Catch:{ all -> 0x009b }
        r1 = r6.f181c;	 Catch:{ all -> 0x009b }
        r1.clear();	 Catch:{ all -> 0x009b }
        r1 = r6.f193o;	 Catch:{ all -> 0x009b }
        r1 = r1.m286h();	 Catch:{ all -> 0x009b }
        r4 = r6.f203z;	 Catch:{ all -> 0x009b }
        r1.removeCallbacks(r4);	 Catch:{ all -> 0x009b }
        monitor-exit(r6);	 Catch:{ all -> 0x009b }
        r6.f183e = r0;
        r1 = r2;
    L_0x008a:
        if (r1 >= r3) goto L_0x009e;
    L_0x008c:
        r4 = r6.f182d;
        r4 = r4[r1];
        r4.run();
        r4 = r6.f182d;
        r5 = 0;
        r4[r1] = r5;
        r1 = r1 + 1;
        goto L_0x008a;
    L_0x009b:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x009b }
        throw r0;
    L_0x009e:
        r6.f183e = r2;
        r1 = r0;
        goto L_0x0027;
    L_0x00a2:
        if (r4 != 0) goto L_0x00a9;
    L_0x00a4:
        r6.f200w = r2;
        r6.m413f();
    L_0x00a9:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.g():boolean");
    }

    void m417h() {
        if (this.f191m != null) {
            for (int i = 0; i < this.f191m.size(); i++) {
                ((OnBackStackChangedListener) this.f191m.get(i)).m349a();
            }
        }
    }

    void m398b(BackStackRecord backStackRecord) {
        if (this.f187i == null) {
            this.f187i = new ArrayList();
        }
        this.f187i.add(backStackRecord);
        m417h();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean m393a(android.os.Handler r12, java.lang.String r13, int r14, int r15) {
        /*
        r11 = this;
        r4 = 0;
        r2 = 1;
        r3 = 0;
        r0 = r11.f187i;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r3;
    L_0x0008:
        if (r13 != 0) goto L_0x0037;
    L_0x000a:
        if (r14 >= 0) goto L_0x0037;
    L_0x000c:
        r0 = r15 & 1;
        if (r0 != 0) goto L_0x0037;
    L_0x0010:
        r0 = r11.f187i;
        r0 = r0.size();
        r0 = r0 + -1;
        if (r0 < 0) goto L_0x0007;
    L_0x001a:
        r1 = r11.f187i;
        r0 = r1.remove(r0);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        r1 = new android.util.SparseArray;
        r1.<init>();
        r3 = new android.util.SparseArray;
        r3.<init>();
        r0.m155a(r1, r3);
        r0.m148a(r2, r4, r1, r3);
        r11.m417h();
    L_0x0035:
        r3 = r2;
        goto L_0x0007;
    L_0x0037:
        r0 = -1;
        if (r13 != 0) goto L_0x003c;
    L_0x003a:
        if (r14 < 0) goto L_0x008b;
    L_0x003c:
        r0 = r11.f187i;
        r0 = r0.size();
        r1 = r0 + -1;
    L_0x0044:
        if (r1 < 0) goto L_0x005a;
    L_0x0046:
        r0 = r11.f187i;
        r0 = r0.get(r1);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        if (r13 == 0) goto L_0x0081;
    L_0x0050:
        r5 = r0.m163c();
        r5 = r13.equals(r5);
        if (r5 == 0) goto L_0x0081;
    L_0x005a:
        if (r1 < 0) goto L_0x0007;
    L_0x005c:
        r0 = r15 & 1;
        if (r0 == 0) goto L_0x008a;
    L_0x0060:
        r1 = r1 + -1;
    L_0x0062:
        if (r1 < 0) goto L_0x008a;
    L_0x0064:
        r0 = r11.f187i;
        r0 = r0.get(r1);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        if (r13 == 0) goto L_0x0078;
    L_0x006e:
        r5 = r0.m163c();
        r5 = r13.equals(r5);
        if (r5 != 0) goto L_0x007e;
    L_0x0078:
        if (r14 < 0) goto L_0x008a;
    L_0x007a:
        r0 = r0.f63p;
        if (r14 != r0) goto L_0x008a;
    L_0x007e:
        r1 = r1 + -1;
        goto L_0x0062;
    L_0x0081:
        if (r14 < 0) goto L_0x0087;
    L_0x0083:
        r0 = r0.f63p;
        if (r14 == r0) goto L_0x005a;
    L_0x0087:
        r1 = r1 + -1;
        goto L_0x0044;
    L_0x008a:
        r0 = r1;
    L_0x008b:
        r1 = r11.f187i;
        r1 = r1.size();
        r1 = r1 + -1;
        if (r0 == r1) goto L_0x0007;
    L_0x0095:
        r6 = new java.util.ArrayList;
        r6.<init>();
        r1 = r11.f187i;
        r1 = r1.size();
        r1 = r1 + -1;
    L_0x00a2:
        if (r1 <= r0) goto L_0x00b0;
    L_0x00a4:
        r5 = r11.f187i;
        r5 = r5.remove(r1);
        r6.add(r5);
        r1 = r1 + -1;
        goto L_0x00a2;
    L_0x00b0:
        r0 = r6.size();
        r7 = r0 + -1;
        r8 = new android.util.SparseArray;
        r8.<init>();
        r9 = new android.util.SparseArray;
        r9.<init>();
        r1 = r3;
    L_0x00c1:
        if (r1 > r7) goto L_0x00d0;
    L_0x00c3:
        r0 = r6.get(r1);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        r0.m155a(r8, r9);
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x00c1;
    L_0x00d0:
        r5 = r4;
        r4 = r3;
    L_0x00d2:
        if (r4 > r7) goto L_0x0108;
    L_0x00d4:
        r0 = f178a;
        if (r0 == 0) goto L_0x00f4;
    L_0x00d8:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r10 = "Popping back stack state: ";
        r1 = r1.append(r10);
        r10 = r6.get(r4);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x00f4:
        r0 = r6.get(r4);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        if (r4 != r7) goto L_0x0106;
    L_0x00fc:
        r1 = r2;
    L_0x00fd:
        r1 = r0.m148a(r1, r5, r8, r9);
        r0 = r4 + 1;
        r4 = r0;
        r5 = r1;
        goto L_0x00d2;
    L_0x0106:
        r1 = r3;
        goto L_0x00fd;
    L_0x0108:
        r11.m417h();
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.a(android.os.Handler, java.lang.String, int, int):boolean");
    }

    ArrayList<Fragment> m418i() {
        ArrayList<Fragment> arrayList = null;
        if (this.f184f != null) {
            for (int i = 0; i < this.f184f.size(); i++) {
                Fragment fragment = (Fragment) this.f184f.get(i);
                if (fragment != null && fragment.f94L) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                    fragment.f95M = true;
                    fragment.f118t = fragment.f117s != null ? fragment.f117s.f114p : -1;
                    if (f178a) {
                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                    }
                }
            }
        }
        return arrayList;
    }

    void m414f(Fragment fragment) {
        if (fragment.f102T != null) {
            if (this.f202y == null) {
                this.f202y = new SparseArray();
            } else {
                this.f202y.clear();
            }
            fragment.f102T.saveHierarchyState(this.f202y);
            if (this.f202y.size() > 0) {
                fragment.f113o = this.f202y;
                this.f202y = null;
            }
        }
    }

    Bundle m415g(Fragment fragment) {
        Bundle bundle;
        if (this.f201x == null) {
            this.f201x = new Bundle();
        }
        fragment.m232k(this.f201x);
        if (this.f201x.isEmpty()) {
            bundle = null;
        } else {
            bundle = this.f201x;
            this.f201x = null;
        }
        if (fragment.f101S != null) {
            m414f(fragment);
        }
        if (fragment.f113o != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.f113o);
        }
        if (!fragment.f104V) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.f104V);
        }
        return bundle;
    }

    Parcelable m419j() {
        BackStackState[] backStackStateArr = null;
        m416g();
        if (f179b) {
            this.f197t = true;
        }
        if (this.f184f == null || this.f184f.size() <= 0) {
            return null;
        }
        int size = this.f184f.size();
        FragmentState[] fragmentStateArr = new FragmentState[size];
        int i = 0;
        boolean z = false;
        while (i < size) {
            boolean z2;
            Fragment fragment = (Fragment) this.f184f.get(i);
            if (fragment != null) {
                if (fragment.f114p < 0) {
                    m365a(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.f114p));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                fragmentStateArr[i] = fragmentState;
                if (fragment.f109k <= 0 || fragmentState.f219j != null) {
                    fragmentState.f219j = fragment.f112n;
                } else {
                    fragmentState.f219j = m415g(fragment);
                    if (fragment.f117s != null) {
                        if (fragment.f117s.f114p < 0) {
                            m365a(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.f117s));
                        }
                        if (fragmentState.f219j == null) {
                            fragmentState.f219j = new Bundle();
                        }
                        m385a(fragmentState.f219j, "android:target_state", fragment.f117s);
                        if (fragment.f119u != 0) {
                            fragmentState.f219j.putInt("android:target_req_state", fragment.f119u);
                        }
                    }
                }
                if (f178a) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.f219j);
                }
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        if (z) {
            int[] iArr;
            int i2;
            FragmentManagerState fragmentManagerState;
            if (this.f185g != null) {
                i = this.f185g.size();
                if (i > 0) {
                    iArr = new int[i];
                    for (i2 = 0; i2 < i; i2++) {
                        iArr[i2] = ((Fragment) this.f185g.get(i2)).f114p;
                        if (iArr[i2] < 0) {
                            m365a(new IllegalStateException("Failure saving state: active " + this.f185g.get(i2) + " has cleared index: " + iArr[i2]));
                        }
                        if (f178a) {
                            Log.v("FragmentManager", "saveAllState: adding fragment #" + i2 + ": " + this.f185g.get(i2));
                        }
                    }
                    if (this.f187i != null) {
                        i = this.f187i.size();
                        if (i > 0) {
                            backStackStateArr = new BackStackState[i];
                            for (i2 = 0; i2 < i; i2++) {
                                backStackStateArr[i2] = new BackStackState((BackStackRecord) this.f187i.get(i2));
                                if (f178a) {
                                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f187i.get(i2));
                                }
                            }
                        }
                    }
                    fragmentManagerState = new FragmentManagerState();
                    fragmentManagerState.f204a = fragmentStateArr;
                    fragmentManagerState.f205b = iArr;
                    fragmentManagerState.f206c = backStackStateArr;
                    return fragmentManagerState;
                }
            }
            iArr = null;
            if (this.f187i != null) {
                i = this.f187i.size();
                if (i > 0) {
                    backStackStateArr = new BackStackState[i];
                    for (i2 = 0; i2 < i; i2++) {
                        backStackStateArr[i2] = new BackStackState((BackStackRecord) this.f187i.get(i2));
                        if (f178a) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f187i.get(i2));
                        }
                    }
                }
            }
            fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.f204a = fragmentStateArr;
            fragmentManagerState.f205b = iArr;
            fragmentManagerState.f206c = backStackStateArr;
            return fragmentManagerState;
        } else if (!f178a) {
            return null;
        } else {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
    }

    void m386a(Parcelable parcelable, List<Fragment> list) {
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.f204a != null) {
                int i;
                Fragment fragment;
                int i2;
                if (list != null) {
                    for (i = 0; i < list.size(); i++) {
                        fragment = (Fragment) list.get(i);
                        if (f178a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                        }
                        FragmentState fragmentState = fragmentManagerState.f204a[fragment.f114p];
                        fragmentState.f220k = fragment;
                        fragment.f113o = null;
                        fragment.f84B = 0;
                        fragment.f124z = false;
                        fragment.f120v = false;
                        fragment.f117s = null;
                        if (fragmentState.f219j != null) {
                            fragmentState.f219j.setClassLoader(this.f193o.m285g().getClassLoader());
                            fragment.f113o = fragmentState.f219j.getSparseParcelableArray("android:view_state");
                            fragment.f112n = fragmentState.f219j;
                        }
                    }
                }
                this.f184f = new ArrayList(fragmentManagerState.f204a.length);
                if (this.f186h != null) {
                    this.f186h.clear();
                }
                for (i2 = 0; i2 < fragmentManagerState.f204a.length; i2++) {
                    FragmentState fragmentState2 = fragmentManagerState.f204a[i2];
                    if (fragmentState2 != null) {
                        Fragment a = fragmentState2.m447a(this.f193o, this.f195q);
                        if (f178a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i2 + ": " + a);
                        }
                        this.f184f.add(a);
                        fragmentState2.f220k = null;
                    } else {
                        this.f184f.add(null);
                        if (this.f186h == null) {
                            this.f186h = new ArrayList();
                        }
                        if (f178a) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + i2);
                        }
                        this.f186h.add(Integer.valueOf(i2));
                    }
                }
                if (list != null) {
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        fragment = (Fragment) list.get(i3);
                        if (fragment.f118t >= 0) {
                            if (fragment.f118t < this.f184f.size()) {
                                fragment.f117s = (Fragment) this.f184f.get(fragment.f118t);
                            } else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.f118t);
                                fragment.f117s = null;
                            }
                        }
                    }
                }
                if (fragmentManagerState.f205b != null) {
                    this.f185g = new ArrayList(fragmentManagerState.f205b.length);
                    for (i = 0; i < fragmentManagerState.f205b.length; i++) {
                        fragment = (Fragment) this.f184f.get(fragmentManagerState.f205b[i]);
                        if (fragment == null) {
                            m365a(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.f205b[i]));
                        }
                        fragment.f120v = true;
                        if (f178a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + fragment);
                        }
                        if (this.f185g.contains(fragment)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.f185g.add(fragment);
                    }
                } else {
                    this.f185g = null;
                }
                if (fragmentManagerState.f206c != null) {
                    this.f187i = new ArrayList(fragmentManagerState.f206c.length);
                    for (i2 = 0; i2 < fragmentManagerState.f206c.length; i2++) {
                        BackStackRecord a2 = fragmentManagerState.f206c[i2].m166a(this);
                        if (f178a) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a2.f63p + "): " + a2);
                            a2.m157a("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
                        }
                        this.f187i.add(a2);
                        if (a2.f63p >= 0) {
                            m382a(a2.f63p, a2);
                        }
                    }
                    return;
                }
                this.f187i = null;
            }
        }
    }

    public void m390a(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment) {
        if (this.f193o != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f193o = fragmentHostCallback;
        this.f194p = fragmentContainer;
        this.f195q = fragment;
    }

    public void m420k() {
        this.f197t = false;
    }

    public void m421l() {
        this.f197t = false;
        m383a(1, false);
    }

    public void m422m() {
        this.f197t = false;
        m383a(2, false);
    }

    public void m423n() {
        this.f197t = false;
        m383a(4, false);
    }

    public void m424o() {
        this.f197t = false;
        m383a(5, false);
    }

    public void m425p() {
        m383a(4, false);
    }

    public void m426q() {
        this.f197t = true;
        m383a(3, false);
    }

    public void m427r() {
        m383a(2, false);
    }

    public void m428s() {
        m383a(1, false);
    }

    public void m429t() {
        this.f198u = true;
        m416g();
        m383a(0, false);
        this.f193o = null;
        this.f194p = null;
        this.f195q = null;
    }

    public void m384a(Configuration configuration) {
        if (this.f185g != null) {
            for (int i = 0; i < this.f185g.size(); i++) {
                Fragment fragment = (Fragment) this.f185g.get(i);
                if (fragment != null) {
                    fragment.m194a(configuration);
                }
            }
        }
    }

    public void m430u() {
        if (this.f185g != null) {
            for (int i = 0; i < this.f185g.size(); i++) {
                Fragment fragment = (Fragment) this.f185g.get(i);
                if (fragment != null) {
                    fragment.m177G();
                }
            }
        }
    }

    public boolean m395a(Menu menu, MenuInflater menuInflater) {
        boolean z;
        Fragment fragment;
        int i = 0;
        ArrayList arrayList = null;
        if (this.f185g != null) {
            int i2 = 0;
            z = false;
            while (i2 < this.f185g.size()) {
                fragment = (Fragment) this.f185g.get(i2);
                if (fragment != null && fragment.m206b(menu, menuInflater)) {
                    z = true;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                }
                i2++;
                z = z;
            }
        } else {
            z = false;
        }
        if (this.f188j != null) {
            while (i < this.f188j.size()) {
                fragment = (Fragment) this.f188j.get(i);
                if (arrayList == null || !arrayList.contains(fragment)) {
                    fragment.m242u();
                }
                i++;
            }
        }
        this.f188j = arrayList;
        return z;
    }

    public boolean m394a(Menu menu) {
        if (this.f185g == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.f185g.size(); i++) {
            Fragment fragment = (Fragment) this.f185g.get(i);
            if (fragment != null && fragment.m210c(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean m396a(MenuItem menuItem) {
        if (this.f185g == null) {
            return false;
        }
        for (int i = 0; i < this.f185g.size(); i++) {
            Fragment fragment = (Fragment) this.f185g.get(i);
            if (fragment != null && fragment.m211c(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean m403b(MenuItem menuItem) {
        if (this.f185g == null) {
            return false;
        }
        for (int i = 0; i < this.f185g.size(); i++) {
            Fragment fragment = (Fragment) this.f185g.get(i);
            if (fragment != null && fragment.m216d(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void m401b(Menu menu) {
        if (this.f185g != null) {
            for (int i = 0; i < this.f185g.size(); i++) {
                Fragment fragment = (Fragment) this.f185g.get(i);
                if (fragment != null) {
                    fragment.m214d(menu);
                }
            }
        }
    }

    public static int m370c(int i) {
        switch (i) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    public static int m368b(int i, boolean z) {
        switch (i) {
            case 4097:
                return z ? 1 : 2;
            case 4099:
                return z ? 5 : 6;
            case 8194:
                return z ? 3 : 4;
            default:
                return -1;
        }
    }

    public View m378a(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String string;
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FragmentTag.f173a);
        if (attributeValue == null) {
            string = obtainStyledAttributes.getString(0);
        } else {
            string = attributeValue;
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string2 = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.m170b(this.f193o.m285g(), string)) {
            return null;
        }
        int id;
        if (view != null) {
            id = view.getId();
        } else {
            id = 0;
        }
        if (id == -1 && resourceId == -1 && string2 == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + string);
        }
        Fragment fragment;
        Fragment a = resourceId != -1 ? m374a(resourceId) : null;
        if (a == null && string2 != null) {
            a = m376a(string2);
        }
        if (a == null && id != -1) {
            a = m374a(id);
        }
        if (f178a) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + string + " existing=" + a);
        }
        if (a == null) {
            Fragment a2 = Fragment.m168a(context, string);
            a2.f123y = true;
            a2.f89G = resourceId != 0 ? resourceId : id;
            a2.f90H = id;
            a2.f91I = string2;
            a2.f124z = true;
            a2.f85C = this;
            a2.f86D = this.f193o;
            a2.m191a(this.f193o.m285g(), attributeSet, a2.f112n);
            m389a(a2, true);
            fragment = a2;
        } else if (a.f124z) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + string);
        } else {
            a.f124z = true;
            if (!a.f95M) {
                a.m191a(this.f193o.m285g(), attributeSet, a.f112n);
            }
            fragment = a;
        }
        if (this.f192n >= 1 || !fragment.f123y) {
            m404c(fragment);
        } else {
            m388a(fragment, 1, 0, 0, false);
        }
        if (fragment.f101S == null) {
            throw new IllegalStateException("Fragment " + string + " did not create a view.");
        }
        if (resourceId != 0) {
            fragment.f101S.setId(resourceId);
        }
        if (fragment.f101S.getTag() == null) {
            fragment.f101S.setTag(string2);
        }
        return fragment.f101S;
    }

    LayoutInflaterFactory m431v() {
        return this;
    }
}
