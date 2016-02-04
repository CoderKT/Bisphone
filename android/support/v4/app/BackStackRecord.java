package android.support.v4.app;

import android.os.Build.VERSION;
import android.support.v4.app.FragmentTransitionCompat21.EpicenterView;
import android.support.v4.app.FragmentTransitionCompat21.ViewRetriever;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import se.emilsjolander.stickylistheaders.C1128R;

final class BackStackRecord extends FragmentTransaction implements Runnable {
    static final boolean f48a;
    final FragmentManagerImpl f49b;
    Op f50c;
    Op f51d;
    int f52e;
    int f53f;
    int f54g;
    int f55h;
    int f56i;
    int f57j;
    int f58k;
    boolean f59l;
    boolean f60m;
    String f61n;
    boolean f62o;
    int f63p;
    int f64q;
    CharSequence f65r;
    int f66s;
    CharSequence f67t;
    ArrayList<String> f68u;
    ArrayList<String> f69v;

    /* renamed from: android.support.v4.app.BackStackRecord.1 */
    class C00021 implements ViewRetriever {
        final /* synthetic */ Fragment f19a;
        final /* synthetic */ BackStackRecord f20b;

        C00021(BackStackRecord backStackRecord, Fragment fragment) {
            this.f20b = backStackRecord;
            this.f19a = fragment;
        }

        public View m108a() {
            return this.f19a.m237p();
        }
    }

    /* renamed from: android.support.v4.app.BackStackRecord.2 */
    class C00032 implements OnPreDrawListener {
        final /* synthetic */ View f21a;
        final /* synthetic */ Object f22b;
        final /* synthetic */ ArrayList f23c;
        final /* synthetic */ TransitionState f24d;
        final /* synthetic */ boolean f25e;
        final /* synthetic */ Fragment f26f;
        final /* synthetic */ Fragment f27g;
        final /* synthetic */ BackStackRecord f28h;

        C00032(BackStackRecord backStackRecord, View view, Object obj, ArrayList arrayList, TransitionState transitionState, boolean z, Fragment fragment, Fragment fragment2) {
            this.f28h = backStackRecord;
            this.f21a = view;
            this.f22b = obj;
            this.f23c = arrayList;
            this.f24d = transitionState;
            this.f25e = z;
            this.f26f = fragment;
            this.f27g = fragment2;
        }

        public boolean onPreDraw() {
            this.f21a.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.f22b != null) {
                FragmentTransitionCompat21.m468a(this.f22b, this.f23c);
                this.f23c.clear();
                ArrayMap a = this.f28h.m121a(this.f24d, this.f25e, this.f26f);
                FragmentTransitionCompat21.m465a(this.f22b, this.f24d.f46d, (Map) a, this.f23c);
                this.f28h.m136a(a, this.f24d);
                this.f28h.m129a(this.f24d, this.f26f, this.f27g, this.f25e, a);
            }
            return true;
        }
    }

    /* renamed from: android.support.v4.app.BackStackRecord.3 */
    class C00043 implements OnPreDrawListener {
        final /* synthetic */ View f29a;
        final /* synthetic */ TransitionState f30b;
        final /* synthetic */ int f31c;
        final /* synthetic */ Object f32d;
        final /* synthetic */ BackStackRecord f33e;

        C00043(BackStackRecord backStackRecord, View view, TransitionState transitionState, int i, Object obj) {
            this.f33e = backStackRecord;
            this.f29a = view;
            this.f30b = transitionState;
            this.f31c = i;
            this.f32d = obj;
        }

        public boolean onPreDraw() {
            this.f29a.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f33e.m128a(this.f30b, this.f31c, this.f32d);
            return true;
        }
    }

    final class Op {
        Op f34a;
        Op f35b;
        int f36c;
        Fragment f37d;
        int f38e;
        int f39f;
        int f40g;
        int f41h;
        ArrayList<Fragment> f42i;

        Op() {
        }
    }

    public class TransitionState {
        public ArrayMap<String, String> f43a;
        public ArrayList<View> f44b;
        public EpicenterView f45c;
        public View f46d;
        final /* synthetic */ BackStackRecord f47e;

        public TransitionState(BackStackRecord backStackRecord) {
            this.f47e = backStackRecord;
            this.f43a = new ArrayMap();
            this.f44b = new ArrayList();
            this.f45c = new EpicenterView();
        }
    }

    static {
        f48a = VERSION.SDK_INT >= 21;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("BackStackEntry{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f63p >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.f63p);
        }
        if (this.f61n != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.f61n);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public void m156a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        m157a(str, printWriter, true);
    }

    public void m157a(String str, PrintWriter printWriter, boolean z) {
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f61n);
            printWriter.print(" mIndex=");
            printWriter.print(this.f63p);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f62o);
            if (this.f57j != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f57j));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.f58k));
            }
            if (!(this.f53f == 0 && this.f54g == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f53f));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f54g));
            }
            if (!(this.f55h == 0 && this.f56i == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f55h));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f56i));
            }
            if (!(this.f64q == 0 && this.f65r == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f64q));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f65r);
            }
            if (!(this.f66s == 0 && this.f67t == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f66s));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f67t);
            }
        }
        if (this.f50c != null) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str2 = str + "    ";
            int i = 0;
            Op op = this.f50c;
            while (op != null) {
                String str3;
                switch (op.f36c) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        str3 = "NULL";
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        str3 = "ADD";
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        str3 = "REPLACE";
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        str3 = "REMOVE";
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        str3 = "HIDE";
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        str3 = "SHOW";
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        str3 = "DETACH";
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        str3 = "ATTACH";
                        break;
                    default:
                        str3 = "cmd=" + op.f36c;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str3);
                printWriter.print(" ");
                printWriter.println(op.f37d);
                if (z) {
                    if (!(op.f38e == 0 && op.f39f == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(op.f38e));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(op.f39f));
                    }
                    if (!(op.f40g == 0 && op.f41h == 0)) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(op.f40g));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(op.f41h));
                    }
                }
                if (op.f42i != null && op.f42i.size() > 0) {
                    for (int i2 = 0; i2 < op.f42i.size(); i2++) {
                        printWriter.print(str2);
                        if (op.f42i.size() == 1) {
                            printWriter.print("Removed: ");
                        } else {
                            if (i2 == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(str2);
                            printWriter.print("  #");
                            printWriter.print(i2);
                            printWriter.print(": ");
                        }
                        printWriter.println(op.f42i.get(i2));
                    }
                }
                op = op.f34a;
                i++;
            }
        }
    }

    public BackStackRecord(FragmentManagerImpl fragmentManagerImpl) {
        this.f60m = true;
        this.f63p = -1;
        this.f49b = fragmentManagerImpl;
    }

    void m154a(Op op) {
        if (this.f50c == null) {
            this.f51d = op;
            this.f50c = op;
        } else {
            op.f35b = this.f51d;
            this.f51d.f34a = op;
            this.f51d = op;
        }
        op.f38e = this.f53f;
        op.f39f = this.f54g;
        op.f40g = this.f55h;
        op.f41h = this.f56i;
        this.f52e++;
    }

    public FragmentTransaction m152a(Fragment fragment, String str) {
        m127a(0, fragment, str, 1);
        return this;
    }

    public FragmentTransaction m149a(int i, Fragment fragment) {
        m127a(i, fragment, null, 1);
        return this;
    }

    public FragmentTransaction m150a(int i, Fragment fragment, String str) {
        m127a(i, fragment, str, 1);
        return this;
    }

    private void m127a(int i, Fragment fragment, String str, int i2) {
        fragment.f85C = this.f49b;
        if (str != null) {
            if (fragment.f91I == null || str.equals(fragment.f91I)) {
                fragment.f91I = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.f91I + " now " + str);
            }
        }
        if (i != 0) {
            if (fragment.f89G == 0 || fragment.f89G == i) {
                fragment.f89G = i;
                fragment.f90H = i;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.f89G + " now " + i);
            }
        }
        Op op = new Op();
        op.f36c = i2;
        op.f37d = fragment;
        m154a(op);
    }

    public FragmentTransaction m159b(int i, Fragment fragment) {
        return m160b(i, fragment, null);
    }

    public FragmentTransaction m160b(int i, Fragment fragment, String str) {
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        m127a(i, fragment, str, 2);
        return this;
    }

    public FragmentTransaction m151a(Fragment fragment) {
        Op op = new Op();
        op.f36c = 3;
        op.f37d = fragment;
        m154a(op);
        return this;
    }

    public FragmentTransaction m161b(Fragment fragment) {
        Op op = new Op();
        op.f36c = 6;
        op.f37d = fragment;
        m154a(op);
        return this;
    }

    public FragmentTransaction m162c(Fragment fragment) {
        Op op = new Op();
        op.f36c = 7;
        op.f37d = fragment;
        m154a(op);
        return this;
    }

    void m153a(int i) {
        if (this.f59l) {
            if (FragmentManagerImpl.f178a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            for (Op op = this.f50c; op != null; op = op.f34a) {
                Fragment fragment;
                if (op.f37d != null) {
                    fragment = op.f37d;
                    fragment.f84B += i;
                    if (FragmentManagerImpl.f178a) {
                        Log.v("FragmentManager", "Bump nesting of " + op.f37d + " to " + op.f37d.f84B);
                    }
                }
                if (op.f42i != null) {
                    for (int size = op.f42i.size() - 1; size >= 0; size--) {
                        fragment = (Fragment) op.f42i.get(size);
                        fragment.f84B += i;
                        if (FragmentManagerImpl.f178a) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.f84B);
                        }
                    }
                }
            }
        }
    }

    public int m146a() {
        return m147a(false);
    }

    public int m158b() {
        return m147a(true);
    }

    int m147a(boolean z) {
        if (this.f62o) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.f178a) {
            Log.v("FragmentManager", "Commit: " + this);
            m156a("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        this.f62o = true;
        if (this.f59l) {
            this.f63p = this.f49b.m372a(this);
        } else {
            this.f63p = -1;
        }
        this.f49b.m391a((Runnable) this, z);
        return this.f63p;
    }

    public void run() {
        if (FragmentManagerImpl.f178a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (!this.f59l || this.f63p >= 0) {
            TransitionState a;
            m153a(1);
            if (f48a) {
                SparseArray sparseArray = new SparseArray();
                SparseArray sparseArray2 = new SparseArray();
                m145b(sparseArray, sparseArray2);
                a = m119a(sparseArray, sparseArray2, false);
            } else {
                a = null;
            }
            int i = a != null ? 0 : this.f58k;
            int i2 = a != null ? 0 : this.f57j;
            Op op = this.f50c;
            while (op != null) {
                int i3 = a != null ? 0 : op.f38e;
                int i4 = a != null ? 0 : op.f39f;
                Fragment fragment;
                switch (op.f36c) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        fragment = op.f37d;
                        fragment.f99Q = i3;
                        this.f49b.m389a(fragment, false);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        Fragment fragment2;
                        Fragment fragment3 = op.f37d;
                        int i5 = fragment3.f90H;
                        if (this.f49b.f185g != null) {
                            fragment2 = fragment3;
                            for (int i6 = 0; i6 < this.f49b.f185g.size(); i6++) {
                                fragment = (Fragment) this.f49b.f185g.get(i6);
                                if (FragmentManagerImpl.f178a) {
                                    Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment);
                                }
                                if (fragment.f90H == i5) {
                                    if (fragment == fragment2) {
                                        fragment2 = null;
                                        op.f37d = null;
                                    } else {
                                        if (op.f42i == null) {
                                            op.f42i = new ArrayList();
                                        }
                                        op.f42i.add(fragment);
                                        fragment.f99Q = i4;
                                        if (this.f59l) {
                                            fragment.f84B++;
                                            if (FragmentManagerImpl.f178a) {
                                                Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.f84B);
                                            }
                                        }
                                        this.f49b.m387a(fragment, i2, i);
                                    }
                                }
                            }
                        } else {
                            fragment2 = fragment3;
                        }
                        if (fragment2 == null) {
                            break;
                        }
                        fragment2.f99Q = i3;
                        this.f49b.m389a(fragment2, false);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        fragment = op.f37d;
                        fragment.f99Q = i4;
                        this.f49b.m387a(fragment, i2, i);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        fragment = op.f37d;
                        fragment.f99Q = i4;
                        this.f49b.m400b(fragment, i2, i);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        fragment = op.f37d;
                        fragment.f99Q = i3;
                        this.f49b.m405c(fragment, i2, i);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        fragment = op.f37d;
                        fragment.f99Q = i4;
                        this.f49b.m409d(fragment, i2, i);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        fragment = op.f37d;
                        fragment.f99Q = i3;
                        this.f49b.m411e(fragment, i2, i);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + op.f36c);
                }
                op = op.f34a;
            }
            this.f49b.m381a(this.f49b.f192n, i2, i, true);
            if (this.f59l) {
                this.f49b.m398b(this);
                return;
            }
            return;
        }
        throw new IllegalStateException("addToBackStack() called after commit()");
    }

    private static void m138a(SparseArray<Fragment> sparseArray, Fragment fragment) {
        if (fragment != null) {
            int i = fragment.f90H;
            if (i != 0 && !fragment.m236o() && fragment.m234m() && fragment.m237p() != null && sparseArray.get(i) == null) {
                sparseArray.put(i, fragment);
            }
        }
    }

    private void m144b(SparseArray<Fragment> sparseArray, Fragment fragment) {
        if (fragment != null) {
            int i = fragment.f90H;
            if (i != 0) {
                sparseArray.put(i, fragment);
            }
        }
    }

    private void m145b(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (this.f49b.f194p.m264a()) {
            for (Op op = this.f50c; op != null; op = op.f34a) {
                switch (op.f36c) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        m144b((SparseArray) sparseArray2, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        Fragment fragment;
                        Fragment fragment2 = op.f37d;
                        if (this.f49b.f185g != null) {
                            fragment = fragment2;
                            for (int i = 0; i < this.f49b.f185g.size(); i++) {
                                Fragment fragment3 = (Fragment) this.f49b.f185g.get(i);
                                if (fragment == null || fragment3.f90H == fragment.f90H) {
                                    if (fragment3 == fragment) {
                                        fragment = null;
                                    } else {
                                        m138a((SparseArray) sparseArray, fragment3);
                                    }
                                }
                            }
                        } else {
                            fragment = fragment2;
                        }
                        m144b((SparseArray) sparseArray2, fragment);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        m138a((SparseArray) sparseArray, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        m138a((SparseArray) sparseArray, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        m144b((SparseArray) sparseArray2, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        m138a((SparseArray) sparseArray, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        m144b((SparseArray) sparseArray2, op.f37d);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void m155a(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (this.f49b.f194p.m264a()) {
            for (Op op = this.f50c; op != null; op = op.f34a) {
                switch (op.f36c) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        m138a((SparseArray) sparseArray, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        if (op.f42i != null) {
                            for (int size = op.f42i.size() - 1; size >= 0; size--) {
                                m144b((SparseArray) sparseArray2, (Fragment) op.f42i.get(size));
                            }
                        }
                        m138a((SparseArray) sparseArray, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        m144b((SparseArray) sparseArray2, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        m144b((SparseArray) sparseArray2, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        m138a((SparseArray) sparseArray, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        m144b((SparseArray) sparseArray2, op.f37d);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        m138a((SparseArray) sparseArray, op.f37d);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public TransitionState m148a(boolean z, TransitionState transitionState, SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        if (FragmentManagerImpl.f178a) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            m156a("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        if (f48a) {
            if (transitionState == null) {
                if (!(sparseArray.size() == 0 && sparseArray2.size() == 0)) {
                    transitionState = m119a((SparseArray) sparseArray, (SparseArray) sparseArray2, true);
                }
            } else if (!z) {
                m132a(transitionState, this.f69v, this.f68u);
            }
        }
        m153a(-1);
        int i = transitionState != null ? 0 : this.f58k;
        int i2 = transitionState != null ? 0 : this.f57j;
        Op op = this.f51d;
        while (op != null) {
            int i3 = transitionState != null ? 0 : op.f40g;
            int i4 = transitionState != null ? 0 : op.f41h;
            Fragment fragment;
            Fragment fragment2;
            switch (op.f36c) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    fragment = op.f37d;
                    fragment.f99Q = i4;
                    this.f49b.m387a(fragment, FragmentManagerImpl.m370c(i2), i);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    fragment = op.f37d;
                    if (fragment != null) {
                        fragment.f99Q = i4;
                        this.f49b.m387a(fragment, FragmentManagerImpl.m370c(i2), i);
                    }
                    if (op.f42i == null) {
                        break;
                    }
                    for (int i5 = 0; i5 < op.f42i.size(); i5++) {
                        fragment2 = (Fragment) op.f42i.get(i5);
                        fragment2.f99Q = i3;
                        this.f49b.m389a(fragment2, false);
                    }
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    fragment2 = op.f37d;
                    fragment2.f99Q = i3;
                    this.f49b.m389a(fragment2, false);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    fragment2 = op.f37d;
                    fragment2.f99Q = i3;
                    this.f49b.m405c(fragment2, FragmentManagerImpl.m370c(i2), i);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    fragment = op.f37d;
                    fragment.f99Q = i4;
                    this.f49b.m400b(fragment, FragmentManagerImpl.m370c(i2), i);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    fragment2 = op.f37d;
                    fragment2.f99Q = i3;
                    this.f49b.m411e(fragment2, FragmentManagerImpl.m370c(i2), i);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    fragment2 = op.f37d;
                    fragment2.f99Q = i3;
                    this.f49b.m409d(fragment2, FragmentManagerImpl.m370c(i2), i);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.f36c);
            }
            op = op.f35b;
        }
        if (z) {
            this.f49b.m381a(this.f49b.f192n, FragmentManagerImpl.m370c(i2), i, true);
            transitionState = null;
        }
        if (this.f63p >= 0) {
            this.f49b.m397b(this.f63p);
            this.f63p = -1;
        }
        return transitionState;
    }

    public String m163c() {
        return this.f61n;
    }

    private TransitionState m119a(SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2, boolean z) {
        int i = 0;
        TransitionState transitionState = new TransitionState(this);
        transitionState.f46d = new View(this.f49b.f193o.m285g());
        int i2 = 0;
        int i3 = 0;
        while (i2 < sparseArray.size()) {
            int i4;
            if (m140a(sparseArray.keyAt(i2), transitionState, z, (SparseArray) sparseArray, (SparseArray) sparseArray2)) {
                i4 = 1;
            } else {
                i4 = i3;
            }
            i2++;
            i3 = i4;
        }
        while (i < sparseArray2.size()) {
            i4 = sparseArray2.keyAt(i);
            if (sparseArray.get(i4) == null && m140a(i4, transitionState, z, (SparseArray) sparseArray, (SparseArray) sparseArray2)) {
                i3 = 1;
            }
            i++;
        }
        if (i3 == 0) {
            return null;
        }
        return transitionState;
    }

    private static Object m125a(Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return FragmentTransitionCompat21.m457a(z ? fragment.m246y() : fragment.m243v());
    }

    private static Object m142b(Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return FragmentTransitionCompat21.m457a(z ? fragment.m244w() : fragment.m245x());
    }

    private static Object m124a(Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return FragmentTransitionCompat21.m476b(z ? fragment2.m171A() : fragment.m247z());
    }

    private static Object m126a(Object obj, Fragment fragment, ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, View view) {
        if (obj != null) {
            return FragmentTransitionCompat21.m458a(obj, fragment.m237p(), arrayList, arrayMap, view);
        }
        return obj;
    }

    private ArrayMap<String, View> m120a(TransitionState transitionState, Fragment fragment, boolean z) {
        ArrayMap arrayMap = new ArrayMap();
        if (this.f68u != null) {
            FragmentTransitionCompat21.m471a((Map) arrayMap, fragment.m237p());
            if (z) {
                arrayMap.m756a(this.f69v);
            } else {
                arrayMap = m123a(this.f68u, this.f69v, arrayMap);
            }
        }
        if (z) {
            if (fragment.ah != null) {
                fragment.ah.m601a(this.f69v, (Map) arrayMap);
            }
            m130a(transitionState, arrayMap, false);
        } else {
            if (fragment.ai != null) {
                fragment.ai.m601a(this.f69v, (Map) arrayMap);
            }
            m143b(transitionState, arrayMap, false);
        }
        return arrayMap;
    }

    private boolean m140a(int i, TransitionState transitionState, boolean z, SparseArray<Fragment> sparseArray, SparseArray<Fragment> sparseArray2) {
        View view = (ViewGroup) this.f49b.f194p.m263a(i);
        if (view == null) {
            return false;
        }
        Object obj;
        ArrayList arrayList;
        Object a;
        View view2;
        ViewRetriever c00021;
        ArrayList arrayList2;
        Map arrayMap;
        boolean z2;
        Object a2;
        Fragment fragment = (Fragment) sparseArray2.get(i);
        Fragment fragment2 = (Fragment) sparseArray.get(i);
        Object a3 = m125a(fragment, z);
        Object a4 = m124a(fragment, fragment2, z);
        Object b = m142b(fragment2, z);
        Map map = null;
        ArrayList arrayList3 = new ArrayList();
        if (a4 != null) {
            map = m120a(transitionState, fragment2, z);
            if (map.isEmpty()) {
                map = null;
                obj = null;
                if (a3 != null && obj == null && b == null) {
                    return false;
                }
                arrayList = new ArrayList();
                a = m126a(b, fragment2, arrayList, (ArrayMap) map, transitionState.f46d);
                if (!(this.f69v == null || map == null)) {
                    view2 = (View) map.get(this.f69v.get(0));
                    if (view2 != null) {
                        if (a != null) {
                            FragmentTransitionCompat21.m464a(a, view2);
                        }
                        if (obj != null) {
                            FragmentTransitionCompat21.m464a(obj, view2);
                        }
                    }
                }
                c00021 = new C00021(this, fragment);
                arrayList2 = new ArrayList();
                arrayMap = new ArrayMap();
                z2 = true;
                if (fragment != null) {
                    z2 = z ? fragment.m173C() : fragment.m172B();
                }
                a2 = FragmentTransitionCompat21.m459a(a3, a, obj, z2);
                if (a2 != null) {
                    FragmentTransitionCompat21.m467a(a3, obj, view, c00021, transitionState.f46d, transitionState.f45c, transitionState.f43a, arrayList2, map, arrayMap, arrayList3);
                    m139a(view, transitionState, i, a2);
                    FragmentTransitionCompat21.m466a(a2, transitionState.f46d, true);
                    m128a(transitionState, i, a2);
                    FragmentTransitionCompat21.m463a((ViewGroup) view, a2);
                    FragmentTransitionCompat21.m462a(view, transitionState.f46d, a3, arrayList2, a, arrayList, obj, arrayList3, a2, transitionState.f44b, arrayMap);
                }
                if (a2 == null) {
                    return true;
                }
                return false;
            }
            SharedElementCallback sharedElementCallback = z ? fragment2.ah : fragment.ah;
            if (sharedElementCallback != null) {
                sharedElementCallback.m600a(new ArrayList(map.keySet()), new ArrayList(map.values()), null);
            }
            m131a(transitionState, view, a4, fragment, fragment2, z, arrayList3);
        }
        obj = a4;
        if (a3 != null) {
        }
        arrayList = new ArrayList();
        a = m126a(b, fragment2, arrayList, (ArrayMap) map, transitionState.f46d);
        view2 = (View) map.get(this.f69v.get(0));
        if (view2 != null) {
            if (a != null) {
                FragmentTransitionCompat21.m464a(a, view2);
            }
            if (obj != null) {
                FragmentTransitionCompat21.m464a(obj, view2);
            }
        }
        c00021 = new C00021(this, fragment);
        arrayList2 = new ArrayList();
        arrayMap = new ArrayMap();
        z2 = true;
        if (fragment != null) {
            if (z) {
            }
        }
        a2 = FragmentTransitionCompat21.m459a(a3, a, obj, z2);
        if (a2 != null) {
            FragmentTransitionCompat21.m467a(a3, obj, view, c00021, transitionState.f46d, transitionState.f45c, transitionState.f43a, arrayList2, map, arrayMap, arrayList3);
            m139a(view, transitionState, i, a2);
            FragmentTransitionCompat21.m466a(a2, transitionState.f46d, true);
            m128a(transitionState, i, a2);
            FragmentTransitionCompat21.m463a((ViewGroup) view, a2);
            FragmentTransitionCompat21.m462a(view, transitionState.f46d, a3, arrayList2, a, arrayList, obj, arrayList3, a2, transitionState.f44b, arrayMap);
        }
        if (a2 == null) {
            return false;
        }
        return true;
    }

    private void m131a(TransitionState transitionState, View view, Object obj, Fragment fragment, Fragment fragment2, boolean z, ArrayList<View> arrayList) {
        view.getViewTreeObserver().addOnPreDrawListener(new C00032(this, view, obj, arrayList, transitionState, z, fragment, fragment2));
    }

    private void m129a(TransitionState transitionState, Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap) {
        SharedElementCallback sharedElementCallback = z ? fragment2.ah : fragment.ah;
        if (sharedElementCallback != null) {
            sharedElementCallback.m602b(new ArrayList(arrayMap.keySet()), new ArrayList(arrayMap.values()), null);
        }
    }

    private void m136a(ArrayMap<String, View> arrayMap, TransitionState transitionState) {
        if (this.f69v != null && !arrayMap.isEmpty()) {
            View view = (View) arrayMap.get(this.f69v.get(0));
            if (view != null) {
                transitionState.f45c.f247a = view;
            }
        }
    }

    private ArrayMap<String, View> m121a(TransitionState transitionState, boolean z, Fragment fragment) {
        ArrayMap b = m141b(transitionState, fragment, z);
        if (z) {
            if (fragment.ai != null) {
                fragment.ai.m601a(this.f69v, (Map) b);
            }
            m130a(transitionState, b, true);
        } else {
            if (fragment.ah != null) {
                fragment.ah.m601a(this.f69v, (Map) b);
            }
            m143b(transitionState, b, true);
        }
        return b;
    }

    private static ArrayMap<String, View> m123a(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayMap<String, View> arrayMap) {
        if (arrayMap.isEmpty()) {
            return arrayMap;
        }
        ArrayMap<String, View> arrayMap2 = new ArrayMap();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = (View) arrayMap.get(arrayList.get(i));
            if (view != null) {
                arrayMap2.put(arrayList2.get(i), view);
            }
        }
        return arrayMap2;
    }

    private ArrayMap<String, View> m141b(TransitionState transitionState, Fragment fragment, boolean z) {
        ArrayMap arrayMap = new ArrayMap();
        View p = fragment.m237p();
        if (p == null || this.f68u == null) {
            return arrayMap;
        }
        FragmentTransitionCompat21.m471a((Map) arrayMap, p);
        if (z) {
            return m123a(this.f68u, this.f69v, arrayMap);
        }
        arrayMap.m756a(this.f69v);
        return arrayMap;
    }

    private void m139a(View view, TransitionState transitionState, int i, Object obj) {
        view.getViewTreeObserver().addOnPreDrawListener(new C00043(this, view, transitionState, i, obj));
    }

    private void m128a(TransitionState transitionState, int i, Object obj) {
        if (this.f49b.f185g != null) {
            for (int i2 = 0; i2 < this.f49b.f185g.size(); i2++) {
                Fragment fragment = (Fragment) this.f49b.f185g.get(i2);
                if (!(fragment.f101S == null || fragment.f100R == null || fragment.f90H != i)) {
                    if (!fragment.f92J) {
                        FragmentTransitionCompat21.m466a(obj, fragment.f101S, false);
                        transitionState.f44b.remove(fragment.f101S);
                    } else if (!transitionState.f44b.contains(fragment.f101S)) {
                        FragmentTransitionCompat21.m466a(obj, fragment.f101S, true);
                        transitionState.f44b.add(fragment.f101S);
                    }
                }
            }
        }
    }

    private static void m137a(ArrayMap<String, String> arrayMap, String str, String str2) {
        if (str != null && str2 != null) {
            for (int i = 0; i < arrayMap.size(); i++) {
                if (str.equals(arrayMap.m753c(i))) {
                    arrayMap.m749a(i, (Object) str2);
                    return;
                }
            }
            arrayMap.put(str, str2);
        }
    }

    private static void m132a(TransitionState transitionState, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                m137a(transitionState.f43a, (String) arrayList.get(i), (String) arrayList2.get(i));
            }
        }
    }

    private void m130a(TransitionState transitionState, ArrayMap<String, View> arrayMap, boolean z) {
        int size = this.f69v == null ? 0 : this.f69v.size();
        for (int i = 0; i < size; i++) {
            String str = (String) this.f68u.get(i);
            View view = (View) arrayMap.get((String) this.f69v.get(i));
            if (view != null) {
                String a = FragmentTransitionCompat21.m460a(view);
                if (z) {
                    m137a(transitionState.f43a, str, a);
                } else {
                    m137a(transitionState.f43a, a, str);
                }
            }
        }
    }

    private void m143b(TransitionState transitionState, ArrayMap<String, View> arrayMap, boolean z) {
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            String str = (String) arrayMap.m752b(i);
            String a = FragmentTransitionCompat21.m460a((View) arrayMap.m753c(i));
            if (z) {
                m137a(transitionState.f43a, str, a);
            } else {
                m137a(transitionState.f43a, a, str);
            }
        }
    }
}
