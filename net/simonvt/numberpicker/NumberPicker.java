package net.simonvt.numberpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.C0110R;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import se.emilsjolander.stickylistheaders.C1128R;

public class NumberPicker extends LinearLayout {
    private static final TwoDigitFormatter f8496a;
    private static final char[] ah;
    private int f8497A;
    private final Scroller f8498B;
    private final Scroller f8499C;
    private int f8500D;
    private SetSelectionCommand f8501E;
    private ChangeCurrentByOneFromLongPressCommand f8502F;
    private BeginSoftInputOnLongPressCommand f8503G;
    private float f8504H;
    private long f8505I;
    private float f8506J;
    private VelocityTracker f8507K;
    private int f8508L;
    private int f8509M;
    private int f8510N;
    private boolean f8511O;
    private final int f8512P;
    private final boolean f8513Q;
    private final Drawable f8514R;
    private final int f8515S;
    private int f8516T;
    private boolean f8517U;
    private boolean f8518V;
    private int f8519W;
    private int aa;
    private int ab;
    private boolean ac;
    private boolean ad;
    private SupportAccessibilityNodeProvider ae;
    private final PressedStateHelper af;
    private int ag;
    private final ImageButton f8520b;
    private final ImageButton f8521c;
    private final EditText f8522d;
    private final int f8523e;
    private final int f8524f;
    private final int f8525g;
    private final int f8526h;
    private int f8527i;
    private final boolean f8528j;
    private final int f8529k;
    private int f8530l;
    private String[] f8531m;
    private int f8532n;
    private int f8533o;
    private int f8534p;
    private OnValueChangeListener f8535q;
    private OnScrollListener f8536r;
    private Formatter f8537s;
    private long f8538t;
    private final SparseArray<String> f8539u;
    private final int[] f8540v;
    private final Paint f8541w;
    private final Drawable f8542x;
    private int f8543y;
    private int f8544z;

    /* renamed from: net.simonvt.numberpicker.NumberPicker.1 */
    class C09781 implements OnClickListener {
        final /* synthetic */ NumberPicker f8471a;

        C09781(NumberPicker numberPicker) {
            this.f8471a = numberPicker;
        }

        public void onClick(View view) {
            this.f8471a.m13383c();
            this.f8471a.f8522d.clearFocus();
            if (view.getId() == 2131755015) {
                this.f8471a.m13366a(true);
            } else {
                this.f8471a.m13366a(false);
            }
        }
    }

    /* renamed from: net.simonvt.numberpicker.NumberPicker.2 */
    class C09792 implements OnLongClickListener {
        final /* synthetic */ NumberPicker f8472a;

        C09792(NumberPicker numberPicker) {
            this.f8472a = numberPicker;
        }

        public boolean onLongClick(View view) {
            this.f8472a.m13383c();
            this.f8472a.f8522d.clearFocus();
            if (view.getId() == 2131755015) {
                this.f8472a.m13367a(true, 0);
            } else {
                this.f8472a.m13367a(false, 0);
            }
            return true;
        }
    }

    /* renamed from: net.simonvt.numberpicker.NumberPicker.3 */
    class C09803 implements OnFocusChangeListener {
        final /* synthetic */ NumberPicker f8473a;

        C09803(NumberPicker numberPicker) {
            this.f8473a = numberPicker;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                this.f8473a.f8522d.selectAll();
                return;
            }
            this.f8473a.f8522d.setSelection(0, 0);
            this.f8473a.m13360a(view);
        }
    }

    class AccessibilityNodeProviderImpl extends AccessibilityNodeProvider {
        final /* synthetic */ NumberPicker f8474a;
        private final Rect f8475b;
        private final int[] f8476c;
        private int f8477d;

        AccessibilityNodeProviderImpl(NumberPicker numberPicker) {
            this.f8474a = numberPicker;
            this.f8475b = new Rect();
            this.f8476c = new int[2];
            this.f8477d = Integer.MIN_VALUE;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            switch (i) {
                case -1:
                    return m13328a(this.f8474a.getScrollX(), this.f8474a.getScrollY(), this.f8474a.getScrollX() + (this.f8474a.getRight() - this.f8474a.getLeft()), this.f8474a.getScrollY() + (this.f8474a.getBottom() - this.f8474a.getTop()));
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return m13329a(1, m13336e(), this.f8474a.getScrollX(), this.f8474a.aa - this.f8474a.f8515S, (this.f8474a.getRight() - this.f8474a.getLeft()) + this.f8474a.getScrollX(), (this.f8474a.getBottom() - this.f8474a.getTop()) + this.f8474a.getScrollY());
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return m13327a();
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return m13329a(3, m13335d(), this.f8474a.getScrollX(), this.f8474a.getScrollY(), (this.f8474a.getRight() - this.f8474a.getLeft()) + this.f8474a.getScrollX(), this.f8474a.f8515S + this.f8474a.f8519W);
                default:
                    return super.createAccessibilityNodeInfo(i);
            }
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            if (TextUtils.isEmpty(str)) {
                return Collections.emptyList();
            }
            String toLowerCase = str.toLowerCase();
            List arrayList = new ArrayList();
            switch (i) {
                case -1:
                    m13332a(toLowerCase, 3, arrayList);
                    m13332a(toLowerCase, 2, arrayList);
                    m13332a(toLowerCase, 1, arrayList);
                    return arrayList;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m13332a(toLowerCase, i, arrayList);
                    return arrayList;
                default:
                    return super.findAccessibilityNodeInfosByText(str, i);
            }
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            boolean z = false;
            switch (i) {
                case -1:
                    switch (i2) {
                        case C0110R.styleable.Theme_imageButtonStyle /*64*/:
                            if (this.f8477d == i) {
                                return false;
                            }
                            this.f8477d = i;
                            this.f8474a.performAccessibilityAction(64, null);
                            return true;
                        case 128:
                            if (this.f8477d != i) {
                                return false;
                            }
                            this.f8477d = Integer.MIN_VALUE;
                            this.f8474a.performAccessibilityAction(128, null);
                            return true;
                        case 4096:
                            if (!this.f8474a.isEnabled()) {
                                return false;
                            }
                            if (!this.f8474a.getWrapSelectorWheel() && this.f8474a.getValue() >= this.f8474a.getMaxValue()) {
                                return false;
                            }
                            this.f8474a.m13366a(true);
                            return true;
                        case 8192:
                            if (!this.f8474a.isEnabled()) {
                                return false;
                            }
                            if (!this.f8474a.getWrapSelectorWheel() && this.f8474a.getValue() <= this.f8474a.getMinValue()) {
                                return false;
                            }
                            this.f8474a.m13366a(false);
                            return true;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    switch (i2) {
                        case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                            if (!this.f8474a.isEnabled()) {
                                return false;
                            }
                            this.f8474a.m13366a(true);
                            m13337a(i, 1);
                            return true;
                        case C0110R.styleable.Theme_imageButtonStyle /*64*/:
                            if (this.f8477d == i) {
                                return false;
                            }
                            this.f8477d = i;
                            m13337a(i, 32768);
                            this.f8474a.invalidate(0, this.f8474a.aa, this.f8474a.getRight(), this.f8474a.getBottom());
                            return true;
                        case 128:
                            if (this.f8477d != i) {
                                return false;
                            }
                            this.f8477d = Integer.MIN_VALUE;
                            m13337a(i, 65536);
                            this.f8474a.invalidate(0, this.f8474a.aa, this.f8474a.getRight(), this.f8474a.getBottom());
                            return true;
                        default:
                            return false;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    switch (i2) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            if (!this.f8474a.isEnabled() || this.f8474a.f8522d.isFocused()) {
                                return false;
                            }
                            return this.f8474a.f8522d.requestFocus();
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            if (!this.f8474a.isEnabled() || !this.f8474a.f8522d.isFocused()) {
                                return false;
                            }
                            this.f8474a.f8522d.clearFocus();
                            return true;
                        case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                            if (!this.f8474a.isEnabled()) {
                                return false;
                            }
                            this.f8474a.m13374b();
                            return true;
                        case C0110R.styleable.Theme_imageButtonStyle /*64*/:
                            if (this.f8477d == i) {
                                return false;
                            }
                            this.f8477d = i;
                            m13337a(i, 32768);
                            this.f8474a.f8522d.invalidate();
                            return true;
                        case 128:
                            if (this.f8477d != i) {
                                return false;
                            }
                            this.f8477d = Integer.MIN_VALUE;
                            m13337a(i, 65536);
                            this.f8474a.f8522d.invalidate();
                            return true;
                        default:
                            return this.f8474a.f8522d.performAccessibilityAction(i2, bundle);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    switch (i2) {
                        case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                            if (!this.f8474a.isEnabled()) {
                                return false;
                            }
                            if (i == 1) {
                                z = true;
                            }
                            this.f8474a.m13366a(z);
                            m13337a(i, 1);
                            return true;
                        case C0110R.styleable.Theme_imageButtonStyle /*64*/:
                            if (this.f8477d == i) {
                                return false;
                            }
                            this.f8477d = i;
                            m13337a(i, 32768);
                            this.f8474a.invalidate(0, 0, this.f8474a.getRight(), this.f8474a.f8519W);
                            return true;
                        case 128:
                            if (this.f8477d != i) {
                                return false;
                            }
                            this.f8477d = Integer.MIN_VALUE;
                            m13337a(i, 65536);
                            this.f8474a.invalidate(0, 0, this.f8474a.getRight(), this.f8474a.f8519W);
                            return true;
                        default:
                            return false;
                    }
            }
            return super.performAction(i, i2, bundle);
        }

        public void m13337a(int i, int i2) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    if (m13334c()) {
                        m13331a(i, i2, m13336e());
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m13330a(i2);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (m13333b()) {
                        m13331a(i, i2, m13335d());
                    }
                default:
            }
        }

        private void m13330a(int i) {
            if (((AccessibilityManager) this.f8474a.getContext().getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
                this.f8474a.f8522d.onInitializeAccessibilityEvent(obtain);
                this.f8474a.f8522d.onPopulateAccessibilityEvent(obtain);
                obtain.setSource(this.f8474a, 2);
                this.f8474a.requestSendAccessibilityEvent(this.f8474a, obtain);
            }
        }

        private void m13331a(int i, int i2, String str) {
            if (((AccessibilityManager) this.f8474a.getContext().getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
                obtain.setClassName(Button.class.getName());
                obtain.setPackageName(this.f8474a.getContext().getPackageName());
                obtain.getText().add(str);
                obtain.setEnabled(this.f8474a.isEnabled());
                obtain.setSource(this.f8474a, i);
                this.f8474a.requestSendAccessibilityEvent(this.f8474a, obtain);
            }
        }

        private void m13332a(String str, int i, List<AccessibilityNodeInfo> list) {
            Object e;
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    e = m13336e();
                    if (!TextUtils.isEmpty(e) && e.toString().toLowerCase().contains(str)) {
                        list.add(createAccessibilityNodeInfo(1));
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    CharSequence text = this.f8474a.f8522d.getText();
                    if (TextUtils.isEmpty(text) || !text.toString().toLowerCase().contains(str)) {
                        text = this.f8474a.f8522d.getText();
                        if (!TextUtils.isEmpty(text) && text.toString().toLowerCase().contains(str)) {
                            list.add(createAccessibilityNodeInfo(2));
                            return;
                        }
                        return;
                    }
                    list.add(createAccessibilityNodeInfo(2));
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    e = m13335d();
                    if (!TextUtils.isEmpty(e) && e.toString().toLowerCase().contains(str)) {
                        list.add(createAccessibilityNodeInfo(3));
                    }
                default:
            }
        }

        private AccessibilityNodeInfo m13327a() {
            AccessibilityNodeInfo createAccessibilityNodeInfo = this.f8474a.f8522d.createAccessibilityNodeInfo();
            createAccessibilityNodeInfo.setSource(this.f8474a, 2);
            if (this.f8477d != 2) {
                createAccessibilityNodeInfo.addAction(64);
            }
            if (this.f8477d == 2) {
                createAccessibilityNodeInfo.addAction(128);
            }
            return createAccessibilityNodeInfo;
        }

        private AccessibilityNodeInfo m13329a(int i, String str, int i2, int i3, int i4, int i5) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(Button.class.getName());
            obtain.setPackageName(this.f8474a.getContext().getPackageName());
            obtain.setSource(this.f8474a, i);
            obtain.setParent(this.f8474a);
            obtain.setText(str);
            obtain.setClickable(true);
            obtain.setLongClickable(true);
            obtain.setEnabled(this.f8474a.isEnabled());
            Rect rect = this.f8475b;
            rect.set(i2, i3, i4, i5);
            obtain.setBoundsInParent(rect);
            int[] iArr = this.f8476c;
            this.f8474a.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            if (this.f8477d != i) {
                obtain.addAction(64);
            }
            if (this.f8477d == i) {
                obtain.addAction(128);
            }
            if (this.f8474a.isEnabled()) {
                obtain.addAction(16);
            }
            return obtain;
        }

        private AccessibilityNodeInfo m13328a(int i, int i2, int i3, int i4) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(NumberPicker.class.getName());
            obtain.setPackageName(this.f8474a.getContext().getPackageName());
            obtain.setSource(this.f8474a);
            if (m13333b()) {
                obtain.addChild(this.f8474a, 3);
            }
            obtain.addChild(this.f8474a, 2);
            if (m13334c()) {
                obtain.addChild(this.f8474a, 1);
            }
            obtain.setParent((View) this.f8474a.getParentForAccessibility());
            obtain.setEnabled(this.f8474a.isEnabled());
            obtain.setScrollable(true);
            if (this.f8477d != -1) {
                obtain.addAction(64);
            }
            if (this.f8477d == -1) {
                obtain.addAction(128);
            }
            if (this.f8474a.isEnabled()) {
                if (this.f8474a.getWrapSelectorWheel() || this.f8474a.getValue() < this.f8474a.getMaxValue()) {
                    obtain.addAction(4096);
                }
                if (this.f8474a.getWrapSelectorWheel() || this.f8474a.getValue() > this.f8474a.getMinValue()) {
                    obtain.addAction(8192);
                }
            }
            return obtain;
        }

        private boolean m13333b() {
            return this.f8474a.getWrapSelectorWheel() || this.f8474a.getValue() > this.f8474a.getMinValue();
        }

        private boolean m13334c() {
            return this.f8474a.getWrapSelectorWheel() || this.f8474a.getValue() < this.f8474a.getMaxValue();
        }

        private String m13335d() {
            int l = this.f8474a.f8534p - 1;
            if (this.f8474a.f8511O) {
                l = this.f8474a.m13381c(l);
            }
            if (l >= this.f8474a.f8532n) {
                return this.f8474a.f8531m == null ? this.f8474a.m13392e(l) : this.f8474a.f8531m[l - this.f8474a.f8532n];
            } else {
                return null;
            }
        }

        private String m13336e() {
            int l = this.f8474a.f8534p + 1;
            if (this.f8474a.f8511O) {
                l = this.f8474a.m13381c(l);
            }
            if (l <= this.f8474a.f8533o) {
                return this.f8474a.f8531m == null ? this.f8474a.m13392e(l) : this.f8474a.f8531m[l - this.f8474a.f8532n];
            } else {
                return null;
            }
        }
    }

    class BeginSoftInputOnLongPressCommand implements Runnable {
        final /* synthetic */ NumberPicker f8478a;

        BeginSoftInputOnLongPressCommand(NumberPicker numberPicker) {
            this.f8478a = numberPicker;
        }

        public void run() {
            this.f8478a.m13374b();
            this.f8478a.f8517U = true;
        }
    }

    class ChangeCurrentByOneFromLongPressCommand implements Runnable {
        final /* synthetic */ NumberPicker f8479a;
        private boolean f8480b;

        ChangeCurrentByOneFromLongPressCommand(NumberPicker numberPicker) {
            this.f8479a = numberPicker;
        }

        private void m13339a(boolean z) {
            this.f8480b = z;
        }

        public void run() {
            this.f8479a.m13366a(this.f8480b);
            this.f8479a.postDelayed(this, this.f8479a.f8538t);
        }
    }

    public class CustomEditText extends EditText {
        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void onEditorAction(int i) {
            super.onEditorAction(i);
            if (i == 6) {
                clearFocus();
            }
        }
    }

    public interface Formatter {
        String m13340a(int i);
    }

    class InputTextFilter extends NumberKeyListener {
        final /* synthetic */ NumberPicker f8481a;

        InputTextFilter(NumberPicker numberPicker) {
            this.f8481a = numberPicker;
        }

        public int getInputType() {
            return 1;
        }

        protected char[] getAcceptedChars() {
            return NumberPicker.ah;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            CharSequence filter;
            String str;
            if (this.f8481a.f8531m == null) {
                filter = super.filter(charSequence, i, i2, spanned, i3, i4);
                if (filter == null) {
                    filter = charSequence.subSequence(i, i2);
                }
                str = String.valueOf(spanned.subSequence(0, i3)) + filter + spanned.subSequence(i4, spanned.length());
                if ("".equals(str)) {
                    return str;
                }
                if (this.f8481a.m13356a(str) > this.f8481a.f8533o) {
                    return "";
                }
                return filter;
            }
            filter = String.valueOf(charSequence.subSequence(i, i2));
            if (TextUtils.isEmpty(filter)) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i3)) + filter + spanned.subSequence(i4, spanned.length());
            String toLowerCase = String.valueOf(str2).toLowerCase();
            for (String str3 : this.f8481a.f8531m) {
                if (str3.toLowerCase().startsWith(toLowerCase)) {
                    this.f8481a.m13384c(str2.length(), str3.length());
                    return str3.subSequence(i3, str3.length());
                }
            }
            return "";
        }
    }

    public interface OnScrollListener {
        void m13341a(NumberPicker numberPicker, int i);
    }

    public interface OnValueChangeListener {
        void m13342a(NumberPicker numberPicker, int i, int i2);
    }

    class PressedStateHelper implements Runnable {
        final /* synthetic */ NumberPicker f8482a;
        private final int f8483b;
        private final int f8484c;
        private int f8485d;
        private int f8486e;

        PressedStateHelper(NumberPicker numberPicker) {
            this.f8482a = numberPicker;
            this.f8483b = 1;
            this.f8484c = 2;
        }

        public void m13343a() {
            this.f8486e = 0;
            this.f8485d = 0;
            this.f8482a.removeCallbacks(this);
            if (this.f8482a.ac) {
                this.f8482a.ac = false;
                this.f8482a.invalidate(0, this.f8482a.aa, this.f8482a.getRight(), this.f8482a.getBottom());
            }
            this.f8482a.ad = false;
            if (this.f8482a.ad) {
                this.f8482a.invalidate(0, 0, this.f8482a.getRight(), this.f8482a.f8519W);
            }
        }

        public void m13344a(int i) {
            m13343a();
            this.f8486e = 1;
            this.f8485d = i;
            this.f8482a.postDelayed(this, (long) ViewConfiguration.getTapTimeout());
        }

        public void m13345b(int i) {
            m13343a();
            this.f8486e = 2;
            this.f8485d = i;
            this.f8482a.post(this);
        }

        public void run() {
            switch (this.f8486e) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    switch (this.f8485d) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            this.f8482a.ac = true;
                            this.f8482a.invalidate(0, this.f8482a.aa, this.f8482a.getRight(), this.f8482a.getBottom());
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            this.f8482a.ad = true;
                            this.f8482a.invalidate(0, 0, this.f8482a.getRight(), this.f8482a.f8519W);
                        default:
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    switch (this.f8485d) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            if (!this.f8482a.ac) {
                                this.f8482a.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                            }
                            NumberPicker.m13369a(this.f8482a, 1);
                            this.f8482a.invalidate(0, this.f8482a.aa, this.f8482a.getRight(), this.f8482a.getBottom());
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            if (!this.f8482a.ad) {
                                this.f8482a.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                            }
                            NumberPicker.m13379b(this.f8482a, 1);
                            this.f8482a.invalidate(0, 0, this.f8482a.getRight(), this.f8482a.f8519W);
                        default:
                    }
                default:
            }
        }
    }

    class SetSelectionCommand implements Runnable {
        final /* synthetic */ NumberPicker f8487a;
        private int f8488b;
        private int f8489c;

        SetSelectionCommand(NumberPicker numberPicker) {
            this.f8487a = numberPicker;
        }

        public void run() {
            this.f8487a.f8522d.setSelection(this.f8488b, this.f8489c);
        }
    }

    class SupportAccessibilityNodeProvider {
        AccessibilityNodeProviderImpl f8490a;
        final /* synthetic */ NumberPicker f8491b;

        private SupportAccessibilityNodeProvider(NumberPicker numberPicker) {
            this.f8491b = numberPicker;
            if (VERSION.SDK_INT >= 16) {
                this.f8490a = new AccessibilityNodeProviderImpl(numberPicker);
            }
        }

        public boolean m13349a(int i, int i2, Bundle bundle) {
            if (this.f8490a != null) {
                return this.f8490a.performAction(i, i2, bundle);
            }
            return false;
        }

        public void m13348a(int i, int i2) {
            if (this.f8490a != null) {
                this.f8490a.m13337a(i, i2);
            }
        }
    }

    class TwoDigitFormatter implements Formatter {
        final StringBuilder f8492a;
        char f8493b;
        java.util.Formatter f8494c;
        final Object[] f8495d;

        TwoDigitFormatter() {
            this.f8492a = new StringBuilder();
            this.f8495d = new Object[1];
            m13350a(Locale.getDefault());
        }

        private void m13350a(Locale locale) {
            this.f8494c = m13352c(locale);
            this.f8493b = m13351b(locale);
        }

        public String m13353a(int i) {
            Locale locale = Locale.getDefault();
            if (this.f8493b != m13351b(locale)) {
                m13350a(locale);
            }
            this.f8495d[0] = Integer.valueOf(i);
            this.f8492a.delete(0, this.f8492a.length());
            this.f8494c.format("%02d", this.f8495d);
            return this.f8494c.toString();
        }

        private static char m13351b(Locale locale) {
            return new DecimalFormatSymbols(locale).getZeroDigit();
        }

        private java.util.Formatter m13352c(Locale locale) {
            return new java.util.Formatter(this.f8492a, locale);
        }
    }

    static /* synthetic */ boolean m13369a(NumberPicker numberPicker, int i) {
        boolean z = (byte) (numberPicker.ac ^ i);
        numberPicker.ac = z;
        return z;
    }

    static /* synthetic */ boolean m13379b(NumberPicker numberPicker, int i) {
        boolean z = (byte) (numberPicker.ad ^ i);
        numberPicker.ad = z;
        return z;
    }

    static {
        f8496a = new TwoDigitFormatter();
        ah = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '\u0660', '\u0661', '\u0662', '\u0663', '\u0664', '\u0665', '\u0666', '\u0667', '\u0668', '\u0669', '\u06f0', '\u06f1', '\u06f2', '\u06f3', '\u06f4', '\u06f5', '\u06f6', '\u06f7', '\u06f8', '\u06f9'};
    }

    public static final Formatter getTwoDigitFormatter() {
        return f8496a;
    }

    public NumberPicker(Context context) {
        this(context, null);
    }

    public NumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130771973);
    }

    public NumberPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.f8538t = 300;
        this.f8539u = new SparseArray();
        this.f8540v = new int[3];
        this.f8544z = Integer.MIN_VALUE;
        this.f8516T = 0;
        this.ag = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0110R.styleable.NumberPicker, i, 0);
        int resourceId = obtainStyledAttributes.getResourceId(8, 0);
        this.f8513Q = resourceId != 0;
        this.f8512P = obtainStyledAttributes.getColor(0, 0);
        this.f8514R = obtainStyledAttributes.getDrawable(1);
        this.f8515S = obtainStyledAttributes.getDimensionPixelSize(2, (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.f8523e = obtainStyledAttributes.getDimensionPixelSize(3, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
        this.f8524f = obtainStyledAttributes.getDimensionPixelSize(4, -1);
        this.f8525g = obtainStyledAttributes.getDimensionPixelSize(5, -1);
        if (this.f8524f == -1 || this.f8525g == -1 || this.f8524f <= this.f8525g) {
            this.f8526h = obtainStyledAttributes.getDimensionPixelSize(6, -1);
            this.f8527i = obtainStyledAttributes.getDimensionPixelSize(7, -1);
            if (this.f8526h == -1 || this.f8527i == -1 || this.f8526h <= this.f8527i) {
                this.f8528j = this.f8527i == -1;
                this.f8542x = obtainStyledAttributes.getDrawable(9);
                obtainStyledAttributes.recycle();
                this.af = new PressedStateHelper(this);
                setWillNotDraw(!this.f8513Q);
                ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(resourceId, this, true);
                OnClickListener c09781 = new C09781(this);
                OnLongClickListener c09792 = new C09792(this);
                if (this.f8513Q) {
                    this.f8520b = null;
                } else {
                    this.f8520b = (ImageButton) findViewById(2131755015);
                    this.f8520b.setOnClickListener(c09781);
                    this.f8520b.setOnLongClickListener(c09792);
                }
                if (this.f8513Q) {
                    this.f8521c = null;
                } else {
                    this.f8521c = (ImageButton) findViewById(2131755014);
                    this.f8521c.setOnClickListener(c09781);
                    this.f8521c.setOnLongClickListener(c09792);
                }
                this.f8522d = (EditText) findViewById(2131755508);
                this.f8522d.setOnFocusChangeListener(new C09803(this));
                this.f8522d.setFilters(new InputFilter[]{new InputTextFilter(this)});
                this.f8522d.setRawInputType(2);
                this.f8522d.setImeOptions(6);
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                this.f8508L = viewConfiguration.getScaledTouchSlop();
                this.f8509M = viewConfiguration.getScaledMinimumFlingVelocity();
                this.f8510N = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
                this.f8529k = (int) this.f8522d.getTextSize();
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setTextAlign(Align.CENTER);
                paint.setTextSize((float) this.f8529k);
                paint.setTypeface(this.f8522d.getTypeface());
                paint.setColor(this.f8522d.getTextColors().getColorForState(ENABLED_STATE_SET, -1));
                this.f8541w = paint;
                this.f8498B = new Scroller(getContext(), null, true);
                this.f8499C = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
                m13401h();
                if (VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
                    setImportantForAccessibility(1);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("minWidth > maxWidth");
        }
        throw new IllegalArgumentException("minHeight > maxHeight");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f8513Q) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int measuredWidth2 = this.f8522d.getMeasuredWidth();
            int measuredHeight2 = this.f8522d.getMeasuredHeight();
            measuredWidth = (measuredWidth - measuredWidth2) / 2;
            measuredHeight = (measuredHeight - measuredHeight2) / 2;
            this.f8522d.layout(measuredWidth, measuredHeight, measuredWidth2 + measuredWidth, measuredHeight2 + measuredHeight);
            if (z) {
                m13397f();
                m13398g();
                this.f8519W = ((getHeight() - this.f8523e) / 2) - this.f8515S;
                this.aa = (this.f8519W + (this.f8515S * 2)) + this.f8523e;
                return;
            }
            return;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        if (this.f8513Q) {
            super.onMeasure(m13354a(i, this.f8527i), m13354a(i2, this.f8525g));
            setMeasuredDimension(m13372b(this.f8526h, getMeasuredWidth(), i), m13372b(this.f8524f, getMeasuredHeight(), i2));
            return;
        }
        super.onMeasure(i, i2);
    }

    private boolean m13370a(Scroller scroller) {
        scroller.m13417a(true);
        int e = scroller.m13422e() - scroller.m13419b();
        int i = this.f8544z - ((this.f8497A + e) % this.f8543y);
        if (i == 0) {
            return false;
        }
        if (Math.abs(i) > this.f8543y / 2) {
            if (i > 0) {
                i -= this.f8543y;
            } else {
                i += this.f8543y;
            }
        }
        scrollBy(0, i + e);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f8513Q || !isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction() & 255) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                m13409l();
                this.f8522d.setVisibility(4);
                float y = motionEvent.getY();
                this.f8504H = y;
                this.f8506J = y;
                this.f8505I = motionEvent.getEventTime();
                this.f8517U = false;
                this.f8518V = false;
                if (this.f8504H < ((float) this.f8519W)) {
                    if (this.f8516T == 0) {
                        this.af.m13344a(2);
                    }
                } else if (this.f8504H > ((float) this.aa) && this.f8516T == 0) {
                    this.af.m13344a(1);
                }
                getParent().requestDisallowInterceptTouchEvent(true);
                if (!this.f8498B.m13418a()) {
                    this.f8498B.m13417a(true);
                    this.f8499C.m13417a(true);
                    m13358a(0);
                    return true;
                } else if (!this.f8499C.m13418a()) {
                    this.f8498B.m13417a(true);
                    this.f8499C.m13417a(true);
                    return true;
                } else if (this.f8504H < ((float) this.f8519W)) {
                    m13383c();
                    m13367a(false, (long) ViewConfiguration.getLongPressTimeout());
                    return true;
                } else if (this.f8504H > ((float) this.aa)) {
                    m13383c();
                    m13367a(true, (long) ViewConfiguration.getLongPressTimeout());
                    return true;
                } else {
                    this.f8518V = true;
                    m13404j();
                    return true;
                }
            default:
                return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !this.f8513Q) {
            return false;
        }
        if (this.f8507K == null) {
            this.f8507K = VelocityTracker.obtain();
        }
        this.f8507K.addMovement(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                m13407k();
                m13403i();
                this.af.m13343a();
                VelocityTracker velocityTracker = this.f8507K;
                velocityTracker.computeCurrentVelocity(1000, (float) this.f8510N);
                int yVelocity = (int) velocityTracker.getYVelocity();
                if (Math.abs(yVelocity) > this.f8509M) {
                    m13375b(yVelocity);
                    m13358a(2);
                } else {
                    yVelocity = (int) motionEvent.getY();
                    long eventTime = motionEvent.getEventTime() - this.f8505I;
                    eventTime = (long) ViewConfiguration.getTapTimeout();
                    if (((int) Math.abs(((float) yVelocity) - this.f8504H)) > this.f8508L) {
                        m13410m();
                    } else if (this.f8518V) {
                        this.f8518V = false;
                        m13374b();
                    } else {
                        yVelocity = (yVelocity / this.f8543y) - 1;
                        if (yVelocity > 0) {
                            m13366a(true);
                            this.af.m13345b(1);
                        } else if (yVelocity < 0) {
                            m13366a(false);
                            this.af.m13345b(2);
                        }
                    }
                    m13358a(0);
                }
                this.f8507K.recycle();
                this.f8507K = null;
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (this.f8517U) {
                    return true;
                }
                float y = motionEvent.getY();
                if (this.f8516T == 1) {
                    scrollBy(0, (int) (y - this.f8506J));
                    invalidate();
                } else if (((int) Math.abs(y - this.f8504H)) > this.f8508L) {
                    m13409l();
                    m13358a(1);
                }
                this.f8506J = y;
                return true;
            default:
                return true;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                m13409l();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
        r5 = this;
        r4 = 20;
        r1 = 1;
        r0 = r6.getKeyCode();
        switch(r0) {
            case 19: goto L_0x0013;
            case 20: goto L_0x0013;
            case 23: goto L_0x000f;
            case 66: goto L_0x000f;
            default: goto L_0x000a;
        };
    L_0x000a:
        r1 = super.dispatchKeyEvent(r6);
    L_0x000e:
        return r1;
    L_0x000f:
        r5.m13409l();
        goto L_0x000a;
    L_0x0013:
        r2 = r5.f8513Q;
        if (r2 == 0) goto L_0x000a;
    L_0x0017:
        r2 = r6.getAction();
        switch(r2) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0053;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x000a;
    L_0x001f:
        r2 = r5.f8511O;
        if (r2 != 0) goto L_0x0025;
    L_0x0023:
        if (r0 != r4) goto L_0x0046;
    L_0x0025:
        r2 = r5.getValue();
        r3 = r5.getMaxValue();
        if (r2 >= r3) goto L_0x000a;
    L_0x002f:
        r5.requestFocus();
        r5.ag = r0;
        r5.m13409l();
        r2 = r5.f8498B;
        r2 = r2.m13418a();
        if (r2 == 0) goto L_0x000e;
    L_0x003f:
        if (r0 != r4) goto L_0x0051;
    L_0x0041:
        r0 = r1;
    L_0x0042:
        r5.m13366a(r0);
        goto L_0x000e;
    L_0x0046:
        r2 = r5.getValue();
        r3 = r5.getMinValue();
        if (r2 <= r3) goto L_0x000a;
    L_0x0050:
        goto L_0x002f;
    L_0x0051:
        r0 = 0;
        goto L_0x0042;
    L_0x0053:
        r2 = r5.ag;
        if (r2 != r0) goto L_0x000a;
    L_0x0057:
        r0 = -1;
        r5.ag = r0;
        goto L_0x000e;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.simonvt.numberpicker.NumberPicker.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                m13409l();
                break;
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.f8513Q) {
            return super.dispatchHoverEvent(motionEvent);
        }
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            int y = (int) motionEvent.getY();
            if (y < this.f8519W) {
                y = 3;
            } else if (y > this.aa) {
                y = 1;
            } else {
                y = 2;
            }
            int action = motionEvent.getAction() & 255;
            SupportAccessibilityNodeProvider supportAccessibilityNodeProvider = getSupportAccessibilityNodeProvider();
            switch (action) {
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    if (!(this.ab == y || this.ab == -1)) {
                        supportAccessibilityNodeProvider.m13348a(this.ab, 256);
                        supportAccessibilityNodeProvider.m13348a(y, 128);
                        this.ab = y;
                        supportAccessibilityNodeProvider.m13349a(y, 64, null);
                        break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    supportAccessibilityNodeProvider.m13348a(y, 128);
                    this.ab = y;
                    supportAccessibilityNodeProvider.m13349a(y, 64, null);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    supportAccessibilityNodeProvider.m13348a(y, 256);
                    this.ab = -1;
                    break;
            }
        }
        return false;
    }

    public void computeScroll() {
        Scroller scroller = this.f8498B;
        if (scroller.m13418a()) {
            scroller = this.f8499C;
            if (scroller.m13418a()) {
                return;
            }
        }
        scroller.m13423f();
        int b = scroller.m13419b();
        if (this.f8500D == 0) {
            this.f8500D = scroller.m13421d();
        }
        scrollBy(0, b - this.f8500D);
        this.f8500D = b;
        if (scroller.m13418a()) {
            m13377b(scroller);
        } else {
            invalidate();
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!this.f8513Q) {
            this.f8520b.setEnabled(z);
        }
        if (!this.f8513Q) {
            this.f8521c.setEnabled(z);
        }
        this.f8522d.setEnabled(z);
    }

    public void scrollBy(int i, int i2) {
        int[] iArr = this.f8540v;
        if (!this.f8511O && i2 > 0 && iArr[1] <= this.f8532n) {
            this.f8497A = this.f8544z;
        } else if (this.f8511O || i2 >= 0 || iArr[1] < this.f8533o) {
            this.f8497A += i2;
            while (this.f8497A - this.f8544z > this.f8530l) {
                this.f8497A -= this.f8543y;
                m13378b(iArr);
                m13359a(iArr[1], true);
                if (!this.f8511O && iArr[1] <= this.f8532n) {
                    this.f8497A = this.f8544z;
                }
            }
            while (this.f8497A - this.f8544z < (-this.f8530l)) {
                this.f8497A += this.f8543y;
                m13368a(iArr);
                m13359a(iArr[1], true);
                if (!this.f8511O && iArr[1] >= this.f8533o) {
                    this.f8497A = this.f8544z;
                }
            }
        } else {
            this.f8497A = this.f8544z;
        }
    }

    public int getSolidColor() {
        return this.f8512P;
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.f8535q = onValueChangeListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.f8536r = onScrollListener;
    }

    public void setFormatter(Formatter formatter) {
        if (formatter != this.f8537s) {
            this.f8537s = formatter;
            m13393e();
            m13401h();
        }
    }

    public void setValue(int i) {
        m13359a(i, false);
    }

    private void m13374b() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            if (this.f8513Q) {
                this.f8522d.setVisibility(0);
            }
            this.f8522d.requestFocus();
            inputMethodManager.showSoftInput(this.f8522d, 0);
        }
    }

    private void m13383c() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null && inputMethodManager.isActive(this.f8522d)) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            if (this.f8513Q) {
                this.f8522d.setVisibility(4);
            }
        }
    }

    private void m13389d() {
        int i = 0;
        if (this.f8528j) {
            int i2;
            int i3;
            if (this.f8531m == null) {
                float f = 0.0f;
                i3 = 0;
                while (i3 <= 9) {
                    float measureText = this.f8541w.measureText(m13396f(i3));
                    if (measureText <= f) {
                        measureText = f;
                    }
                    i3++;
                    f = measureText;
                }
                for (i2 = this.f8533o; i2 > 0; i2 /= 10) {
                    i++;
                }
                i2 = (int) (((float) i) * f);
            } else {
                i2 = 0;
                for (String measureText2 : this.f8531m) {
                    float measureText3 = this.f8541w.measureText(measureText2);
                    if (measureText3 > ((float) i2)) {
                        i2 = (int) measureText3;
                    }
                }
            }
            i2 += this.f8522d.getPaddingLeft() + this.f8522d.getPaddingRight();
            if (this.f8527i != i2) {
                if (i2 > this.f8526h) {
                    this.f8527i = i2;
                } else {
                    this.f8527i = this.f8526h;
                }
                invalidate();
            }
        }
    }

    public boolean getWrapSelectorWheel() {
        return this.f8511O;
    }

    public void setWrapSelectorWheel(boolean z) {
        Object obj = this.f8533o - this.f8532n >= this.f8540v.length ? 1 : null;
        if ((!z || obj != null) && z != this.f8511O) {
            this.f8511O = z;
        }
    }

    public void setOnLongPressUpdateInterval(long j) {
        this.f8538t = j;
    }

    public int getValue() {
        return this.f8534p;
    }

    public int getMinValue() {
        return this.f8532n;
    }

    public void setMinValue(int i) {
        if (this.f8532n != i) {
            if (i < 0) {
                throw new IllegalArgumentException("minValue must be >= 0");
            }
            this.f8532n = i;
            if (this.f8532n > this.f8534p) {
                this.f8534p = this.f8532n;
            }
            setWrapSelectorWheel(this.f8533o - this.f8532n > this.f8540v.length);
            m13393e();
            m13401h();
            m13389d();
            invalidate();
        }
    }

    public int getMaxValue() {
        return this.f8533o;
    }

    public void setMaxValue(int i) {
        if (this.f8533o != i) {
            if (i < 0) {
                throw new IllegalArgumentException("maxValue must be >= 0");
            }
            this.f8533o = i;
            if (this.f8533o < this.f8534p) {
                this.f8534p = this.f8533o;
            }
            setWrapSelectorWheel(this.f8533o - this.f8532n > this.f8540v.length);
            m13393e();
            m13401h();
            m13389d();
            invalidate();
        }
    }

    public String[] getDisplayedValues() {
        return this.f8531m;
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.f8531m != strArr) {
            this.f8531m = strArr;
            if (this.f8531m != null) {
                this.f8522d.setRawInputType(524289);
            } else {
                this.f8522d.setRawInputType(2);
            }
            m13401h();
            m13393e();
            m13389d();
        }
    }

    protected float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    protected float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    protected void onDetachedFromWindow() {
        m13409l();
    }

    protected void onDraw(Canvas canvas) {
        if (this.f8513Q) {
            float right = (float) ((getRight() - getLeft()) / 2);
            float f = (float) this.f8497A;
            if (this.f8542x != null && this.f8516T == 0) {
                if (this.ad) {
                    this.f8542x.setState(PRESSED_ENABLED_STATE_SET);
                    this.f8542x.setBounds(0, 0, getRight(), this.f8519W);
                    this.f8542x.draw(canvas);
                }
                if (this.ac) {
                    this.f8542x.setState(PRESSED_ENABLED_STATE_SET);
                    this.f8542x.setBounds(0, this.aa, getRight(), getBottom());
                    this.f8542x.draw(canvas);
                }
            }
            int[] iArr = this.f8540v;
            float f2 = f;
            for (int i = 0; i < iArr.length; i++) {
                String str = (String) this.f8539u.get(iArr[i]);
                if (i != 1 || this.f8522d.getVisibility() != 0) {
                    canvas.drawText(str, right, f2, this.f8541w);
                }
                f2 += (float) this.f8543y;
            }
            if (this.f8514R != null) {
                int i2 = this.f8519W;
                this.f8514R.setBounds(0, i2, getRight(), this.f8515S + i2);
                this.f8514R.draw(canvas);
                i2 = this.aa;
                this.f8514R.setBounds(0, i2 - this.f8515S, getRight(), i2);
                this.f8514R.draw(canvas);
                return;
            }
            return;
        }
        super.onDraw(canvas);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(NumberPicker.class.getName());
        accessibilityEvent.setScrollable(true);
        accessibilityEvent.setScrollY((this.f8532n + this.f8534p) * this.f8543y);
        accessibilityEvent.setMaxScrollY((this.f8533o - this.f8532n) * this.f8543y);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (!this.f8513Q) {
            return super.getAccessibilityNodeProvider();
        }
        if (this.ae == null) {
            this.ae = new SupportAccessibilityNodeProvider();
        }
        return this.ae.f8490a;
    }

    private int m13354a(int i, int i2) {
        if (i2 == -1) {
            return i;
        }
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        switch (mode) {
            case Integer.MIN_VALUE:
                return MeasureSpec.makeMeasureSpec(Math.min(size, i2), 1073741824);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return MeasureSpec.makeMeasureSpec(i2, 1073741824);
            case 1073741824:
                return i;
            default:
                throw new IllegalArgumentException("Unknown measure mode: " + mode);
        }
    }

    private int m13372b(int i, int i2, int i3) {
        if (i != -1) {
            return m13355a(Math.max(i, i2), i3, 0);
        }
        return i2;
    }

    public static int m13355a(int i, int i2, int i3) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        switch (mode) {
            case Integer.MIN_VALUE:
                if (size < i) {
                    i = size | 16777216;
                    break;
                }
                break;
            case 1073741824:
                i = size;
                break;
        }
        return (-16777216 & i3) | i;
    }

    private void m13393e() {
        this.f8539u.clear();
        int[] iArr = this.f8540v;
        int value = getValue();
        for (int i = 0; i < this.f8540v.length; i++) {
            int i2 = (i - 1) + value;
            if (this.f8511O) {
                i2 = m13381c(i2);
            }
            iArr[i] = i2;
            m13390d(iArr[i]);
        }
    }

    private void m13359a(int i, boolean z) {
        if (this.f8534p != i) {
            int c;
            if (this.f8511O) {
                c = m13381c(i);
            } else {
                c = Math.min(Math.max(i, this.f8532n), this.f8533o);
            }
            int i2 = this.f8534p;
            this.f8534p = c;
            m13401h();
            if (z) {
                m13376b(i2, c);
            }
            m13393e();
            invalidate();
        }
    }

    private void m13366a(boolean z) {
        if (this.f8513Q) {
            this.f8522d.setVisibility(4);
            if (!m13370a(this.f8498B)) {
                m13370a(this.f8499C);
            }
            this.f8500D = 0;
            if (z) {
                this.f8498B.m13415a(0, 0, 0, -this.f8543y, 300);
            } else {
                this.f8498B.m13415a(0, 0, 0, this.f8543y, 300);
            }
            invalidate();
        } else if (z) {
            m13359a(this.f8534p + 1, true);
        } else {
            m13359a(this.f8534p - 1, true);
        }
    }

    private void m13397f() {
        m13393e();
        int[] iArr = this.f8540v;
        this.f8530l = (int) ((((float) ((getBottom() - getTop()) - (iArr.length * this.f8529k))) / ((float) iArr.length)) + 0.5f);
        this.f8543y = this.f8529k + this.f8530l;
        this.f8544z = (this.f8522d.getBaseline() + this.f8522d.getTop()) - (this.f8543y * 1);
        this.f8497A = this.f8544z;
        m13401h();
    }

    private void m13398g() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.f8529k) / 2);
    }

    private void m13377b(Scroller scroller) {
        if (scroller == this.f8498B) {
            if (!m13410m()) {
                m13401h();
            }
            m13358a(0);
        } else if (this.f8516T != 1) {
            m13401h();
        }
    }

    private void m13358a(int i) {
        if (this.f8516T != i) {
            this.f8516T = i;
            if (this.f8536r != null) {
                this.f8536r.m13341a(this, i);
            }
        }
    }

    private void m13375b(int i) {
        this.f8500D = 0;
        if (i > 0) {
            this.f8498B.m13416a(0, 0, 0, i, 0, 0, 0, Integer.MAX_VALUE);
        } else {
            this.f8498B.m13416a(0, Integer.MAX_VALUE, 0, i, 0, 0, 0, Integer.MAX_VALUE);
        }
        invalidate();
    }

    private int m13381c(int i) {
        if (i > this.f8533o) {
            return (this.f8532n + ((i - this.f8533o) % (this.f8533o - this.f8532n))) - 1;
        }
        if (i < this.f8532n) {
            return (this.f8533o - ((this.f8532n - i) % (this.f8533o - this.f8532n))) + 1;
        }
        return i;
    }

    private void m13368a(int[] iArr) {
        int i;
        for (i = 0; i < iArr.length - 1; i++) {
            iArr[i] = iArr[i + 1];
        }
        i = iArr[iArr.length - 2] + 1;
        if (this.f8511O && i > this.f8533o) {
            i = this.f8532n;
        }
        iArr[iArr.length - 1] = i;
        m13390d(i);
    }

    private void m13378b(int[] iArr) {
        int length;
        for (length = iArr.length - 1; length > 0; length--) {
            iArr[length] = iArr[length - 1];
        }
        length = iArr[1] - 1;
        if (this.f8511O && length < this.f8532n) {
            length = this.f8533o;
        }
        iArr[0] = length;
        m13390d(length);
    }

    private void m13390d(int i) {
        SparseArray sparseArray = this.f8539u;
        if (((String) sparseArray.get(i)) == null) {
            Object obj;
            if (i < this.f8532n || i > this.f8533o) {
                obj = "";
            } else if (this.f8531m != null) {
                obj = this.f8531m[i - this.f8532n];
            } else {
                obj = m13392e(i);
            }
            sparseArray.put(i, obj);
        }
    }

    private String m13392e(int i) {
        return this.f8537s != null ? this.f8537s.m13340a(i) : m13396f(i);
    }

    private void m13360a(View view) {
        Object valueOf = String.valueOf(((TextView) view).getText());
        if (TextUtils.isEmpty(valueOf)) {
            m13401h();
        } else {
            m13359a(m13356a(valueOf.toString()), true);
        }
    }

    private boolean m13401h() {
        CharSequence e = this.f8531m == null ? m13392e(this.f8534p) : this.f8531m[this.f8534p - this.f8532n];
        if (TextUtils.isEmpty(e) || e.equals(this.f8522d.getText().toString())) {
            return false;
        }
        this.f8522d.setText(e);
        return true;
    }

    private void m13376b(int i, int i2) {
        if (this.f8535q != null) {
            this.f8535q.m13342a(this, i, this.f8534p);
        }
    }

    private void m13367a(boolean z, long j) {
        if (this.f8502F == null) {
            this.f8502F = new ChangeCurrentByOneFromLongPressCommand(this);
        } else {
            removeCallbacks(this.f8502F);
        }
        this.f8502F.m13339a(z);
        postDelayed(this.f8502F, j);
    }

    private void m13403i() {
        if (this.f8502F != null) {
            removeCallbacks(this.f8502F);
        }
    }

    private void m13404j() {
        if (this.f8503G == null) {
            this.f8503G = new BeginSoftInputOnLongPressCommand(this);
        } else {
            removeCallbacks(this.f8503G);
        }
        postDelayed(this.f8503G, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void m13407k() {
        if (this.f8503G != null) {
            removeCallbacks(this.f8503G);
        }
    }

    private void m13409l() {
        if (this.f8502F != null) {
            removeCallbacks(this.f8502F);
        }
        if (this.f8501E != null) {
            removeCallbacks(this.f8501E);
        }
        if (this.f8503G != null) {
            removeCallbacks(this.f8503G);
        }
        this.af.m13343a();
    }

    private int m13356a(String str) {
        if (this.f8531m == null) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return this.f8532n;
            }
        }
        for (int i = 0; i < this.f8531m.length; i++) {
            str = str.toLowerCase();
            if (this.f8531m[i].toLowerCase().startsWith(str)) {
                return i + this.f8532n;
            }
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            return this.f8532n;
        }
    }

    private void m13384c(int i, int i2) {
        if (this.f8501E == null) {
            this.f8501E = new SetSelectionCommand(this);
        } else {
            removeCallbacks(this.f8501E);
        }
        this.f8501E.f8488b = i;
        this.f8501E.f8489c = i2;
        post(this.f8501E);
    }

    private boolean m13410m() {
        int i = this.f8544z - this.f8497A;
        if (i == 0) {
            return false;
        }
        this.f8500D = 0;
        if (Math.abs(i) > this.f8543y / 2) {
            i += i > 0 ? -this.f8543y : this.f8543y;
        }
        this.f8499C.m13415a(0, 0, 0, i, 800);
        invalidate();
        return true;
    }

    private SupportAccessibilityNodeProvider getSupportAccessibilityNodeProvider() {
        return new SupportAccessibilityNodeProvider();
    }

    private static String m13396f(int i) {
        return String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i)});
    }
}
