package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final boolean f1422a;
    private static final boolean f1423b;
    private static final int[] f1424c;
    private TintManager f1425d;
    private AppCompatBackgroundHelper f1426e;
    private Context f1427f;
    private ForwardingListener f1428g;
    private SpinnerAdapter f1429h;
    private boolean f1430i;
    private DropdownPopup f1431j;
    private int f1432k;
    private final Rect f1433l;

    /* renamed from: android.support.v7.widget.AppCompatSpinner.1 */
    class C00701 extends ForwardingListener {
        final /* synthetic */ DropdownPopup f1377a;
        final /* synthetic */ AppCompatSpinner f1378b;

        C00701(AppCompatSpinner appCompatSpinner, View view, DropdownPopup dropdownPopup) {
            this.f1378b = appCompatSpinner;
            this.f1377a = dropdownPopup;
            super(view);
        }

        public ListPopupWindow m2796a() {
            return this.f1377a;
        }

        public boolean m2797b() {
            if (!this.f1378b.f1431j.m2828k()) {
                this.f1378b.f1431j.m2839c();
            }
            return true;
        }
    }

    class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter f1379a;
        private ListAdapter f1380b;

        public DropDownAdapter(SpinnerAdapter spinnerAdapter, Theme theme) {
            this.f1379a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f1380b = (ListAdapter) spinnerAdapter;
            }
            if (theme == null) {
                return;
            }
            if (AppCompatSpinner.f1422a && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            } else if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                ThemedSpinnerAdapter themedSpinnerAdapter2 = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter2.m3728a() == null) {
                    themedSpinnerAdapter2.m3729a(theme);
                }
            }
        }

        public int getCount() {
            return this.f1379a == null ? 0 : this.f1379a.getCount();
        }

        public Object getItem(int i) {
            return this.f1379a == null ? null : this.f1379a.getItem(i);
        }

        public long getItemId(int i) {
            return this.f1379a == null ? -1 : this.f1379a.getItemId(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return this.f1379a == null ? null : this.f1379a.getDropDownView(i, view, viewGroup);
        }

        public boolean hasStableIds() {
            return this.f1379a != null && this.f1379a.hasStableIds();
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.f1379a != null) {
                this.f1379a.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.f1379a != null) {
                this.f1379a.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f1380b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f1380b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }
    }

    class DropdownPopup extends ListPopupWindow {
        final /* synthetic */ AppCompatSpinner f1418a;
        private CharSequence f1419c;
        private ListAdapter f1420d;
        private final Rect f1421e;

        /* renamed from: android.support.v7.widget.AppCompatSpinner.DropdownPopup.1 */
        class C00711 implements OnItemClickListener {
            final /* synthetic */ AppCompatSpinner f1381a;
            final /* synthetic */ DropdownPopup f1382b;

            C00711(DropdownPopup dropdownPopup, AppCompatSpinner appCompatSpinner) {
                this.f1382b = dropdownPopup;
                this.f1381a = appCompatSpinner;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.f1382b.f1418a.setSelection(i);
                if (this.f1382b.f1418a.getOnItemClickListener() != null) {
                    this.f1382b.f1418a.performItemClick(view, i, this.f1382b.f1420d.getItemId(i));
                }
                this.f1382b.m2826i();
            }
        }

        /* renamed from: android.support.v7.widget.AppCompatSpinner.DropdownPopup.2 */
        class C00722 implements OnGlobalLayoutListener {
            final /* synthetic */ DropdownPopup f1383a;

            C00722(DropdownPopup dropdownPopup) {
                this.f1383a = dropdownPopup;
            }

            public void onGlobalLayout() {
                if (this.f1383a.m2834b(this.f1383a.f1418a)) {
                    this.f1383a.m2838b();
                    super.m2814c();
                    return;
                }
                this.f1383a.m2826i();
            }
        }

        /* renamed from: android.support.v7.widget.AppCompatSpinner.DropdownPopup.3 */
        class C00733 implements OnDismissListener {
            final /* synthetic */ OnGlobalLayoutListener f1384a;
            final /* synthetic */ DropdownPopup f1385b;

            C00733(DropdownPopup dropdownPopup, OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f1385b = dropdownPopup;
                this.f1384a = onGlobalLayoutListener;
            }

            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = this.f1385b.f1418a.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f1384a);
                }
            }
        }

        public DropdownPopup(AppCompatSpinner appCompatSpinner, Context context, AttributeSet attributeSet, int i) {
            this.f1418a = appCompatSpinner;
            super(context, attributeSet, i);
            this.f1421e = new Rect();
            m2808a((View) appCompatSpinner);
            m2812a(true);
            m2806a(0);
            m2809a(new C00711(this, appCompatSpinner));
        }

        public void m2836a(ListAdapter listAdapter) {
            super.m2810a(listAdapter);
            this.f1420d = listAdapter;
        }

        public CharSequence m2835a() {
            return this.f1419c;
        }

        public void m2837a(CharSequence charSequence) {
            this.f1419c = charSequence;
        }

        void m2838b() {
            int i;
            int i2;
            Drawable d = m2816d();
            if (d != null) {
                d.getPadding(this.f1418a.f1433l);
                i = ViewUtils.m3901a(this.f1418a) ? this.f1418a.f1433l.right : -this.f1418a.f1433l.left;
            } else {
                Rect b = this.f1418a.f1433l;
                this.f1418a.f1433l.right = 0;
                b.left = 0;
                i = 0;
            }
            int paddingLeft = this.f1418a.getPaddingLeft();
            int paddingRight = this.f1418a.getPaddingRight();
            int width = this.f1418a.getWidth();
            if (this.f1418a.f1432k == -2) {
                int a = this.f1418a.m2841a((SpinnerAdapter) this.f1420d, m2816d());
                i2 = (this.f1418a.getContext().getResources().getDisplayMetrics().widthPixels - this.f1418a.f1433l.left) - this.f1418a.f1433l.right;
                if (a <= i2) {
                    i2 = a;
                }
                m2821f(Math.max(i2, (width - paddingLeft) - paddingRight));
            } else if (this.f1418a.f1432k == -1) {
                m2821f((width - paddingLeft) - paddingRight);
            } else {
                m2821f(this.f1418a.f1432k);
            }
            if (ViewUtils.m3901a(this.f1418a)) {
                i2 = ((width - paddingRight) - m2824h()) + i;
            } else {
                i2 = i + paddingLeft;
            }
            m2813b(i2);
        }

        public void m2839c() {
            boolean k = m2828k();
            m2838b();
            m2823g(2);
            super.m2814c();
            m2830m().setChoiceMode(1);
            m2825h(this.f1418a.getSelectedItemPosition());
            if (!k) {
                ViewTreeObserver viewTreeObserver = this.f1418a.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    OnGlobalLayoutListener c00722 = new C00722(this);
                    viewTreeObserver.addOnGlobalLayoutListener(c00722);
                    m2811a(new C00733(this, c00722));
                }
            }
        }

        private boolean m2834b(View view) {
            return ViewCompat.m1192u(view) && view.getGlobalVisibleRect(this.f1421e);
        }
    }

    static {
        boolean z;
        if (VERSION.SDK_INT >= 23) {
            z = true;
        } else {
            z = false;
        }
        f1422a = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        f1423b = z;
        f1424c = new int[]{16843505};
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, null);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i, int i2, Theme theme) {
        TypedArray obtainStyledAttributes;
        Throwable e;
        super(context, attributeSet, i);
        this.f1433l = new Rect();
        TintTypedArray a = TintTypedArray.m3759a(context, attributeSet, C0057R.styleable.Spinner, i, 0);
        this.f1425d = a.m3767b();
        this.f1426e = new AppCompatBackgroundHelper(this, this.f1425d);
        if (theme != null) {
            this.f1427f = new ContextThemeWrapper(context, theme);
        } else {
            int g = a.m3775g(C0057R.styleable.Spinner_popupTheme, 0);
            if (g != 0) {
                this.f1427f = new ContextThemeWrapper(context, g);
            } else {
                this.f1427f = !f1422a ? context : null;
            }
        }
        if (this.f1427f != null) {
            DropdownPopup dropdownPopup;
            TintTypedArray a2;
            if (i2 == -1) {
                if (VERSION.SDK_INT >= 11) {
                    try {
                        obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1424c, i, 0);
                        try {
                            if (obtainStyledAttributes.hasValue(0)) {
                                i2 = obtainStyledAttributes.getInt(0, 0);
                            }
                            if (obtainStyledAttributes != null) {
                                obtainStyledAttributes.recycle();
                            }
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e);
                                if (obtainStyledAttributes != null) {
                                    obtainStyledAttributes.recycle();
                                }
                                if (i2 == 1) {
                                    dropdownPopup = new DropdownPopup(this, this.f1427f, attributeSet, i);
                                    a2 = TintTypedArray.m3759a(this.f1427f, attributeSet, C0057R.styleable.Spinner, i, 0);
                                    this.f1432k = a2.m3774f(C0057R.styleable.Spinner_android_dropDownWidth, -2);
                                    dropdownPopup.m2807a(a2.m3762a(C0057R.styleable.Spinner_android_popupBackground));
                                    dropdownPopup.m2837a(a.m3771d(C0057R.styleable.Spinner_android_prompt));
                                    a2.m3763a();
                                    this.f1431j = dropdownPopup;
                                    this.f1428g = new C00701(this, this, dropdownPopup);
                                }
                                a.m3763a();
                                this.f1430i = true;
                                if (this.f1429h != null) {
                                    setAdapter(this.f1429h);
                                    this.f1429h = null;
                                }
                                this.f1426e.m2774a(attributeSet, i);
                            } catch (Throwable th) {
                                e = th;
                                if (obtainStyledAttributes != null) {
                                    obtainStyledAttributes.recycle();
                                }
                                throw e;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        obtainStyledAttributes = null;
                        Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e);
                        if (obtainStyledAttributes != null) {
                            obtainStyledAttributes.recycle();
                        }
                        if (i2 == 1) {
                            dropdownPopup = new DropdownPopup(this, this.f1427f, attributeSet, i);
                            a2 = TintTypedArray.m3759a(this.f1427f, attributeSet, C0057R.styleable.Spinner, i, 0);
                            this.f1432k = a2.m3774f(C0057R.styleable.Spinner_android_dropDownWidth, -2);
                            dropdownPopup.m2807a(a2.m3762a(C0057R.styleable.Spinner_android_popupBackground));
                            dropdownPopup.m2837a(a.m3771d(C0057R.styleable.Spinner_android_prompt));
                            a2.m3763a();
                            this.f1431j = dropdownPopup;
                            this.f1428g = new C00701(this, this, dropdownPopup);
                        }
                        a.m3763a();
                        this.f1430i = true;
                        if (this.f1429h != null) {
                            setAdapter(this.f1429h);
                            this.f1429h = null;
                        }
                        this.f1426e.m2774a(attributeSet, i);
                    } catch (Throwable th2) {
                        e = th2;
                        obtainStyledAttributes = null;
                        if (obtainStyledAttributes != null) {
                            obtainStyledAttributes.recycle();
                        }
                        throw e;
                    }
                }
                i2 = 1;
            }
            if (i2 == 1) {
                dropdownPopup = new DropdownPopup(this, this.f1427f, attributeSet, i);
                a2 = TintTypedArray.m3759a(this.f1427f, attributeSet, C0057R.styleable.Spinner, i, 0);
                this.f1432k = a2.m3774f(C0057R.styleable.Spinner_android_dropDownWidth, -2);
                dropdownPopup.m2807a(a2.m3762a(C0057R.styleable.Spinner_android_popupBackground));
                dropdownPopup.m2837a(a.m3771d(C0057R.styleable.Spinner_android_prompt));
                a2.m3763a();
                this.f1431j = dropdownPopup;
                this.f1428g = new C00701(this, this, dropdownPopup);
            }
        }
        a.m3763a();
        this.f1430i = true;
        if (this.f1429h != null) {
            setAdapter(this.f1429h);
            this.f1429h = null;
        }
        this.f1426e.m2774a(attributeSet, i);
    }

    public Context getPopupContext() {
        if (this.f1431j != null) {
            return this.f1427f;
        }
        if (f1422a) {
            return super.getPopupContext();
        }
        return null;
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        if (this.f1431j != null) {
            this.f1431j.m2807a(drawable);
        } else if (f1423b) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(getPopupContext().getDrawable(i));
    }

    public Drawable getPopupBackground() {
        if (this.f1431j != null) {
            return this.f1431j.m2816d();
        }
        if (f1423b) {
            return super.getPopupBackground();
        }
        return null;
    }

    public void setDropDownVerticalOffset(int i) {
        if (this.f1431j != null) {
            this.f1431j.m2815c(i);
        } else if (f1423b) {
            super.setDropDownVerticalOffset(i);
        }
    }

    public int getDropDownVerticalOffset() {
        if (this.f1431j != null) {
            return this.f1431j.m2822g();
        }
        if (f1423b) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    public void setDropDownHorizontalOffset(int i) {
        if (this.f1431j != null) {
            this.f1431j.m2813b(i);
        } else if (f1423b) {
            super.setDropDownHorizontalOffset(i);
        }
    }

    public int getDropDownHorizontalOffset() {
        if (this.f1431j != null) {
            return this.f1431j.m2820f();
        }
        if (f1423b) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    public void setDropDownWidth(int i) {
        if (this.f1431j != null) {
            this.f1432k = i;
        } else if (f1423b) {
            super.setDropDownWidth(i);
        }
    }

    public int getDropDownWidth() {
        if (this.f1431j != null) {
            return this.f1432k;
        }
        if (f1423b) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (this.f1430i) {
            super.setAdapter(spinnerAdapter);
            if (this.f1431j != null) {
                this.f1431j.m2836a(new DropDownAdapter(spinnerAdapter, (this.f1427f == null ? getContext() : this.f1427f).getTheme()));
                return;
            }
            return;
        }
        this.f1429h = spinnerAdapter;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f1431j != null && this.f1431j.m2828k()) {
            this.f1431j.m2826i();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f1428g == null || !this.f1428g.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f1431j != null && MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), m2841a(getAdapter(), getBackground())), MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        if (this.f1431j == null || this.f1431j.m2828k()) {
            return super.performClick();
        }
        this.f1431j.m2839c();
        return true;
    }

    public void setPrompt(CharSequence charSequence) {
        if (this.f1431j != null) {
            this.f1431j.m2837a(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public CharSequence getPrompt() {
        return this.f1431j != null ? this.f1431j.m2835a() : super.getPrompt();
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.f1426e != null) {
            this.f1426e.m2770a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f1426e != null) {
            this.f1426e.m2773a(drawable);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.f1426e != null) {
            this.f1426e.m2771a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.f1426e != null ? this.f1426e.m2769a() : null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.f1426e != null) {
            this.f1426e.m2772a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        return this.f1426e != null ? this.f1426e.m2775b() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1426e != null) {
            this.f1426e.m2777c();
        }
    }

    private int m2841a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int max2 = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int i = 0;
        max = 0;
        while (max2 < min) {
            View view2;
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != max) {
                view2 = null;
            } else {
                itemViewType = max;
                view2 = view;
            }
            view = spinnerAdapter.getView(max2, view2, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i = Math.max(i, view.getMeasuredWidth());
            max2++;
            max = itemViewType;
        }
        if (drawable == null) {
            return i;
        }
        drawable.getPadding(this.f1433l);
        return (this.f1433l.left + this.f1433l.right) + i;
    }
}
