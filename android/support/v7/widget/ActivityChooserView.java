package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import se.emilsjolander.stickylistheaders.C1128R;

public class ActivityChooserView extends ViewGroup {
    ActionProvider f1297a;
    private final ActivityChooserViewAdapter f1298b;
    private final Callbacks f1299c;
    private final LinearLayoutCompat f1300d;
    private final FrameLayout f1301e;
    private final ImageView f1302f;
    private final FrameLayout f1303g;
    private final int f1304h;
    private final DataSetObserver f1305i;
    private final OnGlobalLayoutListener f1306j;
    private ListPopupWindow f1307k;
    private OnDismissListener f1308l;
    private boolean f1309m;
    private int f1310n;
    private boolean f1311o;
    private int f1312p;

    class ActivityChooserViewAdapter extends BaseAdapter {
        final /* synthetic */ ActivityChooserView f1289a;
        private ActivityChooserModel f1290b;
        private int f1291c;
        private boolean f1292d;
        private boolean f1293e;
        private boolean f1294f;

        public void m2713a(ActivityChooserModel activityChooserModel) {
            ActivityChooserModel d = this.f1289a.f1298b.m2718d();
            if (d != null && this.f1289a.isShown()) {
                d.unregisterObserver(this.f1289a.f1305i);
            }
            this.f1290b = activityChooserModel;
            if (activityChooserModel != null && this.f1289a.isShown()) {
                activityChooserModel.registerObserver(this.f1289a.f1305i);
            }
            notifyDataSetChanged();
        }

        public int getItemViewType(int i) {
            if (this.f1294f && i == getCount() - 1) {
                return 1;
            }
            return 0;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public int getCount() {
            int a = this.f1290b.m2705a();
            if (!(this.f1292d || this.f1290b.m2709b() == null)) {
                a--;
            }
            a = Math.min(a, this.f1291c);
            if (this.f1294f) {
                return a + 1;
            }
            return a;
        }

        public Object getItem(int i) {
            switch (getItemViewType(i)) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    if (!(this.f1292d || this.f1290b.m2709b() == null)) {
                        i++;
                    }
                    return this.f1290b.m2707a(i);
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return null;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    if (view == null || view.getId() != C0057R.id.list_item) {
                        view = LayoutInflater.from(this.f1289a.getContext()).inflate(C0057R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                    }
                    PackageManager packageManager = this.f1289a.getContext().getPackageManager();
                    ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
                    ((ImageView) view.findViewById(C0057R.id.icon)).setImageDrawable(resolveInfo.loadIcon(packageManager));
                    ((TextView) view.findViewById(C0057R.id.title)).setText(resolveInfo.loadLabel(packageManager));
                    if (this.f1292d && i == 0 && this.f1293e) {
                        ViewCompat.m1168b(view, true);
                        return view;
                    }
                    ViewCompat.m1168b(view, false);
                    return view;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    if (view != null && view.getId() == 1) {
                        return view;
                    }
                    view = LayoutInflater.from(this.f1289a.getContext()).inflate(C0057R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                    view.setId(1);
                    ((TextView) view.findViewById(C0057R.id.title)).setText(this.f1289a.getContext().getString(C0057R.string.abc_activity_chooser_view_see_all));
                    return view;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public int m2711a() {
            int i = 0;
            int i2 = this.f1291c;
            this.f1291c = Integer.MAX_VALUE;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            View view = null;
            int i3 = 0;
            while (i < count) {
                view = getView(i, view, null);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i3 = Math.max(i3, view.getMeasuredWidth());
                i++;
            }
            this.f1291c = i2;
            return i3;
        }

        public void m2712a(int i) {
            if (this.f1291c != i) {
                this.f1291c = i;
                notifyDataSetChanged();
            }
        }

        public ResolveInfo m2716b() {
            return this.f1290b.m2709b();
        }

        public void m2714a(boolean z) {
            if (this.f1294f != z) {
                this.f1294f = z;
                notifyDataSetChanged();
            }
        }

        public int m2717c() {
            return this.f1290b.m2705a();
        }

        public ActivityChooserModel m2718d() {
            return this.f1290b;
        }

        public void m2715a(boolean z, boolean z2) {
            if (this.f1292d != z || this.f1293e != z2) {
                this.f1292d = z;
                this.f1293e = z2;
                notifyDataSetChanged();
            }
        }

        public boolean m2719e() {
            return this.f1292d;
        }
    }

    class Callbacks implements OnClickListener, OnLongClickListener, OnItemClickListener, OnDismissListener {
        final /* synthetic */ ActivityChooserView f1295a;

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            switch (((ActivityChooserViewAdapter) adapterView.getAdapter()).getItemViewType(i)) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f1295a.m2732b();
                    if (!this.f1295a.f1309m) {
                        if (!this.f1295a.f1298b.m2719e()) {
                            i++;
                        }
                        Intent b = this.f1295a.f1298b.m2718d().m2708b(i);
                        if (b != null) {
                            b.addFlags(524288);
                            this.f1295a.getContext().startActivity(b);
                        }
                    } else if (i > 0) {
                        this.f1295a.f1298b.m2718d().m2710c(i);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f1295a.m2722a(Integer.MAX_VALUE);
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void onClick(View view) {
            if (view == this.f1295a.f1303g) {
                this.f1295a.m2732b();
                Intent b = this.f1295a.f1298b.m2718d().m2708b(this.f1295a.f1298b.m2718d().m2706a(this.f1295a.f1298b.m2716b()));
                if (b != null) {
                    b.addFlags(524288);
                    this.f1295a.getContext().startActivity(b);
                }
            } else if (view == this.f1295a.f1301e) {
                this.f1295a.f1309m = false;
                this.f1295a.m2722a(this.f1295a.f1310n);
            } else {
                throw new IllegalArgumentException();
            }
        }

        public boolean onLongClick(View view) {
            if (view == this.f1295a.f1303g) {
                if (this.f1295a.f1298b.getCount() > 0) {
                    this.f1295a.f1309m = true;
                    this.f1295a.m2722a(this.f1295a.f1310n);
                }
                return true;
            }
            throw new IllegalArgumentException();
        }

        public void onDismiss() {
            m2720a();
            if (this.f1295a.f1297a != null) {
                this.f1295a.f1297a.m890a(false);
            }
        }

        private void m2720a() {
            if (this.f1295a.f1308l != null) {
                this.f1295a.f1308l.onDismiss();
            }
        }
    }

    public class InnerLayout extends LinearLayoutCompat {
        private static final int[] f1296a;

        static {
            f1296a = new int[]{16842964};
        }

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TintTypedArray a = TintTypedArray.m3758a(context, attributeSet, f1296a);
            setBackgroundDrawable(a.m3762a(0));
            a.m3763a();
        }
    }

    public void setActivityChooserModel(ActivityChooserModel activityChooserModel) {
        this.f1298b.m2713a(activityChooserModel);
        if (m2733c()) {
            m2732b();
            m2731a();
        }
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f1302f.setImageDrawable(drawable);
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.f1302f.setContentDescription(getContext().getString(i));
    }

    public void setProvider(ActionProvider actionProvider) {
        this.f1297a = actionProvider;
    }

    public boolean m2731a() {
        if (m2733c() || !this.f1311o) {
            return false;
        }
        this.f1309m = false;
        m2722a(this.f1310n);
        return true;
    }

    private void m2722a(int i) {
        if (this.f1298b.m2718d() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.f1306j);
        boolean z = this.f1303g.getVisibility() == 0;
        int c = this.f1298b.m2717c();
        int i2;
        if (z) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i == Integer.MAX_VALUE || c <= r3 + i) {
            this.f1298b.m2714a(false);
            this.f1298b.m2712a(i);
        } else {
            this.f1298b.m2714a(true);
            this.f1298b.m2712a(i - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (!listPopupWindow.m2828k()) {
            if (this.f1309m || !z) {
                this.f1298b.m2715a(true, z);
            } else {
                this.f1298b.m2715a(false, false);
            }
            listPopupWindow.m2821f(Math.min(this.f1298b.m2711a(), this.f1304h));
            listPopupWindow.m2814c();
            if (this.f1297a != null) {
                this.f1297a.m890a(true);
            }
            listPopupWindow.m2830m().setContentDescription(getContext().getString(C0057R.string.abc_activitychooserview_choose_application));
        }
    }

    public boolean m2732b() {
        if (m2733c()) {
            getListPopupWindow().m2826i();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.f1306j);
            }
        }
        return true;
    }

    public boolean m2733c() {
        return getListPopupWindow().m2828k();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityChooserModel d = this.f1298b.m2718d();
        if (d != null) {
            d.registerObserver(this.f1305i);
        }
        this.f1311o = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityChooserModel d = this.f1298b.m2718d();
        if (d != null) {
            d.unregisterObserver(this.f1305i);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f1306j);
        }
        if (m2733c()) {
            m2732b();
        }
        this.f1311o = false;
    }

    protected void onMeasure(int i, int i2) {
        View view = this.f1300d;
        if (this.f1303g.getVisibility() != 0) {
            i2 = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), 1073741824);
        }
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f1300d.layout(0, 0, i3 - i, i4 - i2);
        if (!m2733c()) {
            m2732b();
        }
    }

    public ActivityChooserModel getDataModel() {
        return this.f1298b.m2718d();
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.f1308l = onDismissListener;
    }

    public void setInitialActivityCount(int i) {
        this.f1310n = i;
    }

    public void setDefaultActionButtonContentDescription(int i) {
        this.f1312p = i;
    }

    private ListPopupWindow getListPopupWindow() {
        if (this.f1307k == null) {
            this.f1307k = new ListPopupWindow(getContext());
            this.f1307k.m2810a(this.f1298b);
            this.f1307k.m2808a((View) this);
            this.f1307k.m2812a(true);
            this.f1307k.m2809a(this.f1299c);
            this.f1307k.m2811a(this.f1299c);
        }
        return this.f1307k;
    }
}
