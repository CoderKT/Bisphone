package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.support.v7.appcompat.C0057R;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import se.emilsjolander.stickylistheaders.C1128R;

class AlertController {
    private TextView f707A;
    private TextView f708B;
    private View f709C;
    private ListAdapter f710D;
    private int f711E;
    private int f712F;
    private int f713G;
    private int f714H;
    private int f715I;
    private int f716J;
    private int f717K;
    private int f718L;
    private Handler f719M;
    private final OnClickListener f720N;
    private final Context f721a;
    private final AppCompatDialog f722b;
    private final Window f723c;
    private CharSequence f724d;
    private CharSequence f725e;
    private ListView f726f;
    private View f727g;
    private int f728h;
    private int f729i;
    private int f730j;
    private int f731k;
    private int f732l;
    private boolean f733m;
    private Button f734n;
    private CharSequence f735o;
    private Message f736p;
    private Button f737q;
    private CharSequence f738r;
    private Message f739s;
    private Button f740t;
    private CharSequence f741u;
    private Message f742v;
    private NestedScrollView f743w;
    private int f744x;
    private Drawable f745y;
    private ImageView f746z;

    /* renamed from: android.support.v7.app.AlertController.1 */
    class C00361 implements OnClickListener {
        final /* synthetic */ AlertController f642a;

        C00361(AlertController alertController) {
            this.f642a = alertController;
        }

        public void onClick(View view) {
            Message obtain;
            if (view == this.f642a.f734n && this.f642a.f736p != null) {
                obtain = Message.obtain(this.f642a.f736p);
            } else if (view == this.f642a.f737q && this.f642a.f739s != null) {
                obtain = Message.obtain(this.f642a.f739s);
            } else if (view != this.f642a.f740t || this.f642a.f742v == null) {
                obtain = null;
            } else {
                obtain = Message.obtain(this.f642a.f742v);
            }
            if (obtain != null) {
                obtain.sendToTarget();
            }
            this.f642a.f719M.obtainMessage(1, this.f642a.f722b).sendToTarget();
        }
    }

    /* renamed from: android.support.v7.app.AlertController.2 */
    class C00372 implements OnScrollChangeListener {
        final /* synthetic */ View f643a;
        final /* synthetic */ View f644b;
        final /* synthetic */ AlertController f645c;

        C00372(AlertController alertController, View view, View view2) {
            this.f645c = alertController;
            this.f643a = view;
            this.f644b = view2;
        }

        public void m1928a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
            AlertController.m1943b(nestedScrollView, this.f643a, this.f644b);
        }
    }

    /* renamed from: android.support.v7.app.AlertController.3 */
    class C00383 implements Runnable {
        final /* synthetic */ View f646a;
        final /* synthetic */ View f647b;
        final /* synthetic */ AlertController f648c;

        C00383(AlertController alertController, View view, View view2) {
            this.f648c = alertController;
            this.f646a = view;
            this.f647b = view2;
        }

        public void run() {
            AlertController.m1943b(this.f648c.f743w, this.f646a, this.f647b);
        }
    }

    /* renamed from: android.support.v7.app.AlertController.4 */
    class C00394 implements OnScrollListener {
        final /* synthetic */ View f649a;
        final /* synthetic */ View f650b;
        final /* synthetic */ AlertController f651c;

        C00394(AlertController alertController, View view, View view2) {
            this.f651c = alertController;
            this.f649a = view;
            this.f650b = view2;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            AlertController.m1943b(absListView, this.f649a, this.f650b);
        }
    }

    /* renamed from: android.support.v7.app.AlertController.5 */
    class C00405 implements Runnable {
        final /* synthetic */ View f652a;
        final /* synthetic */ View f653b;
        final /* synthetic */ AlertController f654c;

        C00405(AlertController alertController, View view, View view2) {
            this.f654c = alertController;
            this.f652a = view;
            this.f653b = view2;
        }

        public void run() {
            AlertController.m1943b(this.f654c.f726f, this.f652a, this.f653b);
        }
    }

    public class AlertParams {
        public int f667A;
        public boolean f668B;
        public boolean[] f669C;
        public boolean f670D;
        public boolean f671E;
        public int f672F;
        public OnMultiChoiceClickListener f673G;
        public Cursor f674H;
        public String f675I;
        public String f676J;
        public OnItemSelectedListener f677K;
        public OnPrepareListViewListener f678L;
        public boolean f679M;
        public final Context f680a;
        public final LayoutInflater f681b;
        public int f682c;
        public Drawable f683d;
        public int f684e;
        public CharSequence f685f;
        public View f686g;
        public CharSequence f687h;
        public CharSequence f688i;
        public DialogInterface.OnClickListener f689j;
        public CharSequence f690k;
        public DialogInterface.OnClickListener f691l;
        public CharSequence f692m;
        public DialogInterface.OnClickListener f693n;
        public boolean f694o;
        public OnCancelListener f695p;
        public OnDismissListener f696q;
        public OnKeyListener f697r;
        public CharSequence[] f698s;
        public ListAdapter f699t;
        public DialogInterface.OnClickListener f700u;
        public int f701v;
        public View f702w;
        public int f703x;
        public int f704y;
        public int f705z;

        /* renamed from: android.support.v7.app.AlertController.AlertParams.1 */
        class C00411 extends ArrayAdapter<CharSequence> {
            final /* synthetic */ ListView f655a;
            final /* synthetic */ AlertParams f656b;

            C00411(AlertParams alertParams, Context context, int i, int i2, CharSequence[] charSequenceArr, ListView listView) {
                this.f656b = alertParams;
                this.f655a = listView;
                super(context, i, i2, charSequenceArr);
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                if (this.f656b.f669C != null && this.f656b.f669C[i]) {
                    this.f655a.setItemChecked(i, true);
                }
                return view2;
            }
        }

        /* renamed from: android.support.v7.app.AlertController.AlertParams.2 */
        class C00422 extends CursorAdapter {
            final /* synthetic */ ListView f657a;
            final /* synthetic */ AlertController f658b;
            final /* synthetic */ AlertParams f659c;
            private final int f660d;
            private final int f661e;

            C00422(AlertParams alertParams, Context context, Cursor cursor, boolean z, ListView listView, AlertController alertController) {
                this.f659c = alertParams;
                this.f657a = listView;
                this.f658b = alertController;
                super(context, cursor, z);
                Cursor cursor2 = getCursor();
                this.f660d = cursor2.getColumnIndexOrThrow(this.f659c.f675I);
                this.f661e = cursor2.getColumnIndexOrThrow(this.f659c.f676J);
            }

            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.f660d));
                this.f657a.setItemChecked(cursor.getPosition(), cursor.getInt(this.f661e) == 1);
            }

            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return this.f659c.f681b.inflate(this.f658b.f715I, viewGroup, false);
            }
        }

        /* renamed from: android.support.v7.app.AlertController.AlertParams.3 */
        class C00433 implements OnItemClickListener {
            final /* synthetic */ AlertController f662a;
            final /* synthetic */ AlertParams f663b;

            C00433(AlertParams alertParams, AlertController alertController) {
                this.f663b = alertParams;
                this.f662a = alertController;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.f663b.f700u.onClick(this.f662a.f722b, i);
                if (!this.f663b.f671E) {
                    this.f662a.f722b.dismiss();
                }
            }
        }

        /* renamed from: android.support.v7.app.AlertController.AlertParams.4 */
        class C00444 implements OnItemClickListener {
            final /* synthetic */ ListView f664a;
            final /* synthetic */ AlertController f665b;
            final /* synthetic */ AlertParams f666c;

            C00444(AlertParams alertParams, ListView listView, AlertController alertController) {
                this.f666c = alertParams;
                this.f664a = listView;
                this.f665b = alertController;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.f666c.f669C != null) {
                    this.f666c.f669C[i] = this.f664a.isItemChecked(i);
                }
                this.f666c.f673G.onClick(this.f665b.f722b, i, this.f664a.isItemChecked(i));
            }
        }

        public interface OnPrepareListViewListener {
            void m1929a(ListView listView);
        }

        public AlertParams(Context context) {
            this.f682c = 0;
            this.f684e = 0;
            this.f668B = false;
            this.f672F = -1;
            this.f679M = true;
            this.f680a = context;
            this.f694o = true;
            this.f681b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void m1931a(AlertController alertController) {
            if (this.f686g != null) {
                alertController.m1968b(this.f686g);
            } else {
                if (this.f685f != null) {
                    alertController.m1965a(this.f685f);
                }
                if (this.f683d != null) {
                    alertController.m1963a(this.f683d);
                }
                if (this.f682c != 0) {
                    alertController.m1967b(this.f682c);
                }
                if (this.f684e != 0) {
                    alertController.m1967b(alertController.m1971c(this.f684e));
                }
            }
            if (this.f687h != null) {
                alertController.m1969b(this.f687h);
            }
            if (this.f688i != null) {
                alertController.m1962a(-1, this.f688i, this.f689j, null);
            }
            if (this.f690k != null) {
                alertController.m1962a(-2, this.f690k, this.f691l, null);
            }
            if (this.f692m != null) {
                alertController.m1962a(-3, this.f692m, this.f693n, null);
            }
            if (!(this.f698s == null && this.f674H == null && this.f699t == null)) {
                m1930b(alertController);
            }
            if (this.f702w != null) {
                if (this.f668B) {
                    alertController.m1964a(this.f702w, this.f703x, this.f704y, this.f705z, this.f667A);
                    return;
                }
                alertController.m1972c(this.f702w);
            } else if (this.f701v != 0) {
                alertController.m1961a(this.f701v);
            }
        }

        private void m1930b(AlertController alertController) {
            ListAdapter simpleCursorAdapter;
            ListView listView = (ListView) this.f681b.inflate(alertController.f714H, null);
            if (!this.f670D) {
                int m;
                if (this.f671E) {
                    m = alertController.f716J;
                } else {
                    m = alertController.f717K;
                }
                if (this.f674H != null) {
                    simpleCursorAdapter = new SimpleCursorAdapter(this.f680a, m, this.f674H, new String[]{this.f675I}, new int[]{16908308});
                } else if (this.f699t != null) {
                    simpleCursorAdapter = this.f699t;
                } else {
                    simpleCursorAdapter = new CheckedItemAdapter(this.f680a, m, 16908308, this.f698s);
                }
            } else if (this.f674H == null) {
                simpleCursorAdapter = new C00411(this, this.f680a, alertController.f715I, 16908308, this.f698s, listView);
            } else {
                Object c00422 = new C00422(this, this.f680a, this.f674H, false, listView, alertController);
            }
            if (this.f678L != null) {
                this.f678L.m1929a(listView);
            }
            alertController.f710D = simpleCursorAdapter;
            alertController.f711E = this.f672F;
            if (this.f700u != null) {
                listView.setOnItemClickListener(new C00433(this, alertController));
            } else if (this.f673G != null) {
                listView.setOnItemClickListener(new C00444(this, listView, alertController));
            }
            if (this.f677K != null) {
                listView.setOnItemSelectedListener(this.f677K);
            }
            if (this.f671E) {
                listView.setChoiceMode(1);
            } else if (this.f670D) {
                listView.setChoiceMode(2);
            }
            alertController.f726f = listView;
        }
    }

    final class ButtonHandler extends Handler {
        private WeakReference<DialogInterface> f706a;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.f706a = new WeakReference(dialogInterface);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case -3:
                case -2:
                case -1:
                    ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f706a.get(), message.what);
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    ((DialogInterface) message.obj).dismiss();
                default:
            }
        }
    }

    class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        public CheckedItemAdapter(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        public boolean hasStableIds() {
            return true;
        }

        public long getItemId(int i) {
            return (long) i;
        }
    }

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        this.f733m = false;
        this.f744x = 0;
        this.f711E = -1;
        this.f718L = 0;
        this.f720N = new C00361(this);
        this.f721a = context;
        this.f722b = appCompatDialog;
        this.f723c = window;
        this.f719M = new ButtonHandler(appCompatDialog);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, C0057R.styleable.AlertDialog, C0057R.attr.alertDialogStyle, 0);
        this.f712F = obtainStyledAttributes.getResourceId(C0057R.styleable.AlertDialog_android_layout, 0);
        this.f713G = obtainStyledAttributes.getResourceId(C0057R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.f714H = obtainStyledAttributes.getResourceId(C0057R.styleable.AlertDialog_listLayout, 0);
        this.f715I = obtainStyledAttributes.getResourceId(C0057R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.f716J = obtainStyledAttributes.getResourceId(C0057R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.f717K = obtainStyledAttributes.getResourceId(C0057R.styleable.AlertDialog_listItemLayout, 0);
        obtainStyledAttributes.recycle();
    }

    static boolean m1940a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (m1940a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public void m1960a() {
        this.f722b.m2000a(1);
        this.f722b.setContentView(m1941b());
        m1946c();
    }

    private int m1941b() {
        if (this.f713G == 0) {
            return this.f712F;
        }
        if (this.f718L == 1) {
            return this.f713G;
        }
        return this.f712F;
    }

    public void m1965a(CharSequence charSequence) {
        this.f724d = charSequence;
        if (this.f707A != null) {
            this.f707A.setText(charSequence);
        }
    }

    public void m1968b(View view) {
        this.f709C = view;
    }

    public void m1969b(CharSequence charSequence) {
        this.f725e = charSequence;
        if (this.f708B != null) {
            this.f708B.setText(charSequence);
        }
    }

    public void m1961a(int i) {
        this.f727g = null;
        this.f728h = i;
        this.f733m = false;
    }

    public void m1972c(View view) {
        this.f727g = view;
        this.f728h = 0;
        this.f733m = false;
    }

    public void m1964a(View view, int i, int i2, int i3, int i4) {
        this.f727g = view;
        this.f728h = 0;
        this.f733m = true;
        this.f729i = i;
        this.f730j = i2;
        this.f731k = i3;
        this.f732l = i4;
    }

    public void m1962a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (message == null && onClickListener != null) {
            message = this.f719M.obtainMessage(i, onClickListener);
        }
        switch (i) {
            case -3:
                this.f741u = charSequence;
                this.f742v = message;
            case -2:
                this.f738r = charSequence;
                this.f739s = message;
            case -1:
                this.f735o = charSequence;
                this.f736p = message;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void m1967b(int i) {
        this.f745y = null;
        this.f744x = i;
        if (this.f746z == null) {
            return;
        }
        if (i != 0) {
            this.f746z.setImageResource(this.f744x);
        } else {
            this.f746z.setVisibility(8);
        }
    }

    public void m1963a(Drawable drawable) {
        this.f745y = drawable;
        this.f744x = 0;
        if (this.f746z == null) {
            return;
        }
        if (drawable != null) {
            this.f746z.setImageDrawable(drawable);
        } else {
            this.f746z.setVisibility(8);
        }
    }

    public int m1971c(int i) {
        TypedValue typedValue = new TypedValue();
        this.f721a.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public boolean m1966a(int i, KeyEvent keyEvent) {
        return this.f743w != null && this.f743w.m1811a(keyEvent);
    }

    public boolean m1970b(int i, KeyEvent keyEvent) {
        return this.f743w != null && this.f743w.m1811a(keyEvent);
    }

    private ViewGroup m1933a(View view, View view2) {
        View inflate;
        if (view == null) {
            if (view2 instanceof ViewStub) {
                inflate = ((ViewStub) view2).inflate();
            } else {
                inflate = view2;
            }
            return (ViewGroup) inflate;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            inflate = ((ViewStub) view).inflate();
        } else {
            inflate = view;
        }
        return (ViewGroup) inflate;
    }

    private void m1946c() {
        boolean z;
        boolean z2;
        View findViewById = this.f723c.findViewById(C0057R.id.parentPanel);
        View findViewById2 = findViewById.findViewById(C0057R.id.topPanel);
        View findViewById3 = findViewById.findViewById(C0057R.id.contentPanel);
        View findViewById4 = findViewById.findViewById(C0057R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById.findViewById(C0057R.id.customPanel);
        m1938a(viewGroup);
        View findViewById5 = viewGroup.findViewById(C0057R.id.topPanel);
        View findViewById6 = viewGroup.findViewById(C0057R.id.contentPanel);
        View findViewById7 = viewGroup.findViewById(C0057R.id.buttonPanel);
        ViewGroup a = m1933a(findViewById5, findViewById2);
        ViewGroup a2 = m1933a(findViewById6, findViewById3);
        ViewGroup a3 = m1933a(findViewById7, findViewById4);
        m1947c(a2);
        m1949d(a3);
        m1944b(a);
        boolean z3 = (viewGroup == null || viewGroup.getVisibility() == 8) ? false : true;
        if (a == null || a.getVisibility() == 8) {
            z = false;
        } else {
            z = true;
        }
        if (a3 == null || a3.getVisibility() == 8) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (!(z2 || a2 == null)) {
            findViewById3 = a2.findViewById(C0057R.id.textSpacerNoButtons);
            if (findViewById3 != null) {
                findViewById3.setVisibility(0);
            }
        }
        if (z && this.f743w != null) {
            this.f743w.setClipToPadding(true);
        }
        if (!z3) {
            findViewById3 = this.f726f != null ? this.f726f : this.f743w;
            if (findViewById3 != null) {
                int i;
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
                m1939a(a2, findViewById3, (z2 ? 2 : 0) | i, 3);
            }
        }
        ListView listView = this.f726f;
        if (listView != null && this.f710D != null) {
            listView.setAdapter(this.f710D);
            int i2 = this.f711E;
            if (i2 > -1) {
                listView.setItemChecked(i2, true);
                listView.setSelection(i2);
            }
        }
    }

    private void m1939a(ViewGroup viewGroup, View view, int i, int i2) {
        View view2 = null;
        View findViewById = this.f723c.findViewById(C0057R.id.scrollIndicatorUp);
        View findViewById2 = this.f723c.findViewById(C0057R.id.scrollIndicatorDown);
        if (VERSION.SDK_INT >= 23) {
            ViewCompat.m1157a(view, i, i2);
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
                return;
            }
            return;
        }
        if (findViewById != null && (i & 1) == 0) {
            viewGroup.removeView(findViewById);
            findViewById = null;
        }
        if (findViewById2 == null || (i & 2) != 0) {
            view2 = findViewById2;
        } else {
            viewGroup.removeView(findViewById2);
        }
        if (findViewById != null || view2 != null) {
            if (this.f725e != null) {
                this.f743w.setOnScrollChangeListener(new C00372(this, findViewById, view2));
                this.f743w.post(new C00383(this, findViewById, view2));
            } else if (this.f726f != null) {
                this.f726f.setOnScrollListener(new C00394(this, findViewById, view2));
                this.f726f.post(new C00405(this, findViewById, view2));
            } else {
                if (findViewById != null) {
                    viewGroup.removeView(findViewById);
                }
                if (view2 != null) {
                    viewGroup.removeView(view2);
                }
            }
        }
    }

    private void m1938a(ViewGroup viewGroup) {
        View view;
        boolean z = false;
        if (this.f727g != null) {
            view = this.f727g;
        } else if (this.f728h != 0) {
            view = LayoutInflater.from(this.f721a).inflate(this.f728h, viewGroup, false);
        } else {
            view = null;
        }
        if (view != null) {
            z = true;
        }
        if (!(z && m1940a(view))) {
            this.f723c.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) this.f723c.findViewById(C0057R.id.custom);
            frameLayout.addView(view, new LayoutParams(-1, -1));
            if (this.f733m) {
                frameLayout.setPadding(this.f729i, this.f730j, this.f731k, this.f732l);
            }
            if (this.f726f != null) {
                ((LinearLayout.LayoutParams) viewGroup.getLayoutParams()).weight = 0.0f;
                return;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void m1944b(ViewGroup viewGroup) {
        if (this.f709C != null) {
            viewGroup.addView(this.f709C, 0, new LayoutParams(-1, -2));
            this.f723c.findViewById(C0057R.id.title_template).setVisibility(8);
            return;
        }
        this.f746z = (ImageView) this.f723c.findViewById(16908294);
        if ((!TextUtils.isEmpty(this.f724d) ? 1 : 0) != 0) {
            this.f707A = (TextView) this.f723c.findViewById(C0057R.id.alertTitle);
            this.f707A.setText(this.f724d);
            if (this.f744x != 0) {
                this.f746z.setImageResource(this.f744x);
                return;
            } else if (this.f745y != null) {
                this.f746z.setImageDrawable(this.f745y);
                return;
            } else {
                this.f707A.setPadding(this.f746z.getPaddingLeft(), this.f746z.getPaddingTop(), this.f746z.getPaddingRight(), this.f746z.getPaddingBottom());
                this.f746z.setVisibility(8);
                return;
            }
        }
        this.f723c.findViewById(C0057R.id.title_template).setVisibility(8);
        this.f746z.setVisibility(8);
        viewGroup.setVisibility(8);
    }

    private void m1947c(ViewGroup viewGroup) {
        this.f743w = (NestedScrollView) this.f723c.findViewById(C0057R.id.scrollView);
        this.f743w.setFocusable(false);
        this.f743w.setNestedScrollingEnabled(false);
        this.f708B = (TextView) viewGroup.findViewById(16908299);
        if (this.f708B != null) {
            if (this.f725e != null) {
                this.f708B.setText(this.f725e);
                return;
            }
            this.f708B.setVisibility(8);
            this.f743w.removeView(this.f708B);
            if (this.f726f != null) {
                ViewGroup viewGroup2 = (ViewGroup) this.f743w.getParent();
                int indexOfChild = viewGroup2.indexOfChild(this.f743w);
                viewGroup2.removeViewAt(indexOfChild);
                viewGroup2.addView(this.f726f, indexOfChild, new LayoutParams(-1, -1));
                return;
            }
            viewGroup.setVisibility(8);
        }
    }

    private static void m1943b(View view, View view2, View view3) {
        int i = 0;
        if (view2 != null) {
            view2.setVisibility(ViewCompat.m1170b(view, -1) ? 0 : 4);
        }
        if (view3 != null) {
            if (!ViewCompat.m1170b(view, 1)) {
                i = 4;
            }
            view3.setVisibility(i);
        }
    }

    private void m1949d(ViewGroup viewGroup) {
        int i;
        int i2 = 1;
        this.f734n = (Button) viewGroup.findViewById(16908313);
        this.f734n.setOnClickListener(this.f720N);
        if (TextUtils.isEmpty(this.f735o)) {
            this.f734n.setVisibility(8);
            i = 0;
        } else {
            this.f734n.setText(this.f735o);
            this.f734n.setVisibility(0);
            i = 1;
        }
        this.f737q = (Button) viewGroup.findViewById(16908314);
        this.f737q.setOnClickListener(this.f720N);
        if (TextUtils.isEmpty(this.f738r)) {
            this.f737q.setVisibility(8);
        } else {
            this.f737q.setText(this.f738r);
            this.f737q.setVisibility(0);
            i |= 2;
        }
        this.f740t = (Button) viewGroup.findViewById(16908315);
        this.f740t.setOnClickListener(this.f720N);
        if (TextUtils.isEmpty(this.f741u)) {
            this.f740t.setVisibility(8);
        } else {
            this.f740t.setText(this.f741u);
            this.f740t.setVisibility(0);
            i |= 4;
        }
        if (i == 0) {
            i2 = 0;
        }
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        }
    }
}
