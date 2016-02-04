package android.support.v7.widget;

import android.annotation.TargetApi;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.CollapsibleActionView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import org.jivesoftware.smackx.search.UserSearch;
import se.emilsjolander.stickylistheaders.C1128R;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
    static final AutoCompleteTextViewReflector f1745a;
    private static final boolean f1746b;
    private boolean f1747A;
    private boolean f1748B;
    private int f1749C;
    private boolean f1750D;
    private CharSequence f1751E;
    private boolean f1752F;
    private int f1753G;
    private SearchableInfo f1754H;
    private Bundle f1755I;
    private Runnable f1756J;
    private final Runnable f1757K;
    private Runnable f1758L;
    private final WeakHashMap<String, ConstantState> f1759M;
    private final SearchAutoComplete f1760c;
    private final View f1761d;
    private final View f1762e;
    private final ImageView f1763f;
    private final ImageView f1764g;
    private final ImageView f1765h;
    private final ImageView f1766i;
    private final ImageView f1767j;
    private final Drawable f1768k;
    private final int f1769l;
    private final int f1770m;
    private final Intent f1771n;
    private final Intent f1772o;
    private final CharSequence f1773p;
    private OnQueryTextListener f1774q;
    private OnCloseListener f1775r;
    private OnFocusChangeListener f1776s;
    private OnSuggestionListener f1777t;
    private OnClickListener f1778u;
    private boolean f1779v;
    private boolean f1780w;
    private CursorAdapter f1781x;
    private boolean f1782y;
    private CharSequence f1783z;

    class AutoCompleteTextViewReflector {
        private Method f1739a;
        private Method f1740b;
        private Method f1741c;
        private Method f1742d;

        AutoCompleteTextViewReflector() {
            try {
                this.f1739a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f1739a.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            try {
                this.f1740b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f1740b.setAccessible(true);
            } catch (NoSuchMethodException e2) {
            }
            try {
                this.f1741c = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.f1741c.setAccessible(true);
            } catch (NoSuchMethodException e3) {
            }
            try {
                this.f1742d = InputMethodManager.class.getMethod("showSoftInputUnchecked", new Class[]{Integer.TYPE, ResultReceiver.class});
                this.f1742d.setAccessible(true);
            } catch (NoSuchMethodException e4) {
            }
        }

        void m3669a(AutoCompleteTextView autoCompleteTextView) {
            if (this.f1739a != null) {
                try {
                    this.f1739a.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        void m3671b(AutoCompleteTextView autoCompleteTextView) {
            if (this.f1740b != null) {
                try {
                    this.f1740b.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        void m3670a(AutoCompleteTextView autoCompleteTextView, boolean z) {
            if (this.f1741c != null) {
                try {
                    this.f1741c.invoke(autoCompleteTextView, new Object[]{Boolean.valueOf(z)});
                } catch (Exception e) {
                }
            }
        }
    }

    public interface OnCloseListener {
        boolean m3672a();
    }

    public interface OnQueryTextListener {
        boolean m3673a(String str);
    }

    public interface OnSuggestionListener {
    }

    public class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        private int f1743a;
        private SearchView f1744b;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, C0057R.attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.f1743a = getThreshold();
        }

        void setSearchView(SearchView searchView) {
            this.f1744b = searchView;
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f1743a = i;
        }

        protected void replaceText(CharSequence charSequence) {
        }

        public void performCompletion() {
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f1744b.hasFocus() && getVisibility() == 0) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                if (SearchView.m3678a(getContext())) {
                    SearchView.f1745a.m3670a(this, true);
                }
            }
        }

        protected void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.f1744b.m3699d();
        }

        public boolean enoughToFilter() {
            return this.f1743a <= 0 || super.enoughToFilter();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                DispatcherState keyDispatcherState;
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState == null) {
                        return true;
                    }
                    keyDispatcherState.startTracking(keyEvent, this);
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f1744b.clearFocus();
                        this.f1744b.setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }
    }

    static {
        boolean z;
        if (VERSION.SDK_INT >= 8) {
            z = true;
        } else {
            z = false;
        }
        f1746b = z;
        f1745a = new AutoCompleteTextViewReflector();
    }

    int getSuggestionRowLayout() {
        return this.f1769l;
    }

    int getSuggestionCommitIconResId() {
        return this.f1770m;
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f1754H = searchableInfo;
        if (this.f1754H != null) {
            if (f1746b) {
                m3688l();
            }
            m3687k();
        }
        boolean z = f1746b && m3682e();
        this.f1750D = z;
        if (this.f1750D) {
            this.f1760c.setPrivateImeOptions("nm");
        }
        m3677a(m3698c());
    }

    public void setAppSearchData(Bundle bundle) {
        this.f1755I = bundle;
    }

    public void setImeOptions(int i) {
        this.f1760c.setImeOptions(i);
    }

    public int getImeOptions() {
        return this.f1760c.getImeOptions();
    }

    public void setInputType(int i) {
        this.f1760c.setInputType(i);
    }

    public int getInputType() {
        return this.f1760c.getInputType();
    }

    public boolean requestFocus(int i, Rect rect) {
        if (this.f1748B || !isFocusable()) {
            return false;
        }
        if (m3698c()) {
            return super.requestFocus(i, rect);
        }
        boolean requestFocus = this.f1760c.requestFocus(i, rect);
        if (requestFocus) {
            m3677a(false);
        }
        return requestFocus;
    }

    public void clearFocus() {
        this.f1748B = true;
        setImeVisibility(false);
        super.clearFocus();
        this.f1760c.clearFocus();
        this.f1748B = false;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.f1774q = onQueryTextListener;
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.f1775r = onCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.f1776s = onFocusChangeListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
        this.f1777t = onSuggestionListener;
    }

    public void setOnSearchClickListener(OnClickListener onClickListener) {
        this.f1778u = onClickListener;
    }

    public CharSequence getQuery() {
        return this.f1760c.getText();
    }

    public void m3696a(CharSequence charSequence, boolean z) {
        this.f1760c.setText(charSequence);
        if (charSequence != null) {
            this.f1760c.setSelection(this.f1760c.length());
            this.f1751E = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            m3689m();
        }
    }

    public void setQueryHint(CharSequence charSequence) {
        this.f1783z = charSequence;
        m3687k();
    }

    public CharSequence getQueryHint() {
        if (this.f1783z != null) {
            return this.f1783z;
        }
        if (!f1746b || this.f1754H == null || this.f1754H.getHintId() == 0) {
            return this.f1773p;
        }
        return getContext().getText(this.f1754H.getHintId());
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.f1779v != z) {
            this.f1779v = z;
            m3677a(z);
            m3687k();
        }
    }

    public void setIconified(boolean z) {
        if (z) {
            m3691o();
        } else {
            m3692p();
        }
    }

    public boolean m3698c() {
        return this.f1780w;
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.f1782y = z;
        m3677a(m3698c());
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.f1747A = z;
        if (this.f1781x instanceof SuggestionsAdapter) {
            ((SuggestionsAdapter) this.f1781x).m3719a(z ? 2 : 1);
        }
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter) {
        this.f1781x = cursorAdapter;
        this.f1760c.setAdapter(this.f1781x);
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.f1781x;
    }

    public void setMaxWidth(int i) {
        this.f1749C = i;
        requestLayout();
    }

    public int getMaxWidth() {
        return this.f1749C;
    }

    protected void onMeasure(int i, int i2) {
        if (m3698c()) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        switch (mode) {
            case Integer.MIN_VALUE:
                if (this.f1749C <= 0) {
                    size = Math.min(getPreferredWidth(), size);
                    break;
                } else {
                    size = Math.min(this.f1749C, size);
                    break;
                }
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                if (this.f1749C <= 0) {
                    size = getPreferredWidth();
                    break;
                } else {
                    size = this.f1749C;
                    break;
                }
            case 1073741824:
                if (this.f1749C > 0) {
                    size = Math.min(this.f1749C, size);
                    break;
                }
                break;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), i2);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(C0057R.dimen.abc_search_view_preferred_width);
    }

    private void m3677a(boolean z) {
        boolean z2;
        boolean z3 = true;
        int i = 8;
        this.f1780w = z;
        int i2 = z ? 0 : 8;
        if (TextUtils.isEmpty(this.f1760c.getText())) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.f1763f.setVisibility(i2);
        m3680b(z2);
        View view = this.f1761d;
        if (z) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        view.setVisibility(i2);
        if (!(this.f1767j.getDrawable() == null || this.f1779v)) {
            i = 0;
        }
        this.f1767j.setVisibility(i);
        m3685h();
        if (z2) {
            z3 = false;
        }
        m3681c(z3);
        m3684g();
    }

    @TargetApi(8)
    private boolean m3682e() {
        if (this.f1754H == null || !this.f1754H.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.f1754H.getVoiceSearchLaunchWebSearch()) {
            intent = this.f1771n;
        } else if (this.f1754H.getVoiceSearchLaunchRecognizer()) {
            intent = this.f1772o;
        }
        if (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) {
            return false;
        }
        return true;
    }

    private boolean m3683f() {
        return (this.f1782y || this.f1750D) && !m3698c();
    }

    private void m3680b(boolean z) {
        int i = 8;
        if (this.f1782y && m3683f() && hasFocus() && (z || !this.f1750D)) {
            i = 0;
        }
        this.f1764g.setVisibility(i);
    }

    private void m3684g() {
        int i = 8;
        if (m3683f() && (this.f1764g.getVisibility() == 0 || this.f1766i.getVisibility() == 0)) {
            i = 0;
        }
        this.f1762e.setVisibility(i);
    }

    private void m3685h() {
        int i = 1;
        int i2 = 0;
        int i3 = !TextUtils.isEmpty(this.f1760c.getText()) ? 1 : 0;
        if (i3 == 0 && (!this.f1779v || this.f1752F)) {
            i = 0;
        }
        ImageView imageView = this.f1765h;
        if (i == 0) {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        Drawable drawable = this.f1765h.getDrawable();
        if (drawable != null) {
            drawable.setState(i3 != 0 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
        }
    }

    private void m3686i() {
        post(this.f1757K);
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.f1757K);
        post(this.f1758L);
        super.onDetachedFromWindow();
    }

    private void setImeVisibility(boolean z) {
        if (z) {
            post(this.f1756J);
            return;
        }
        removeCallbacks(this.f1756J);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    void m3695a(CharSequence charSequence) {
        setQuery(charSequence);
    }

    private CharSequence m3679b(CharSequence charSequence) {
        if (!this.f1779v || this.f1768k == null) {
            return charSequence;
        }
        int textSize = (int) (((double) this.f1760c.getTextSize()) * 1.25d);
        this.f1768k.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.f1768k), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private void m3687k() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.f1760c;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(m3679b(queryHint));
    }

    @TargetApi(8)
    private void m3688l() {
        int i = 1;
        this.f1760c.setThreshold(this.f1754H.getSuggestThreshold());
        this.f1760c.setImeOptions(this.f1754H.getImeOptions());
        int inputType = this.f1754H.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.f1754H.getSuggestAuthority() != null) {
                inputType = (inputType | 65536) | 524288;
            }
        }
        this.f1760c.setInputType(inputType);
        if (this.f1781x != null) {
            this.f1781x.m1727a(null);
        }
        if (this.f1754H.getSuggestAuthority() != null) {
            this.f1781x = new SuggestionsAdapter(getContext(), this, this.f1754H, this.f1759M);
            this.f1760c.setAdapter(this.f1781x);
            SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter) this.f1781x;
            if (this.f1747A) {
                i = 2;
            }
            suggestionsAdapter.m3719a(i);
        }
    }

    private void m3681c(boolean z) {
        int i;
        if (this.f1750D && !m3698c() && z) {
            i = 0;
            this.f1764g.setVisibility(8);
        } else {
            i = 8;
        }
        this.f1766i.setVisibility(i);
    }

    private void m3689m() {
        CharSequence text = this.f1760c.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            if (this.f1774q == null || !this.f1774q.m3673a(text.toString())) {
                if (this.f1754H != null) {
                    m3675a(0, null, text.toString());
                }
                setImeVisibility(false);
                m3690n();
            }
        }
    }

    private void m3690n() {
        this.f1760c.dismissDropDown();
    }

    private void m3691o() {
        if (!TextUtils.isEmpty(this.f1760c.getText())) {
            this.f1760c.setText("");
            this.f1760c.requestFocus();
            setImeVisibility(true);
        } else if (!this.f1779v) {
        } else {
            if (this.f1775r == null || !this.f1775r.m3672a()) {
                clearFocus();
                m3677a(true);
            }
        }
    }

    private void m3692p() {
        m3677a(false);
        this.f1760c.requestFocus();
        setImeVisibility(true);
        if (this.f1778u != null) {
            this.f1778u.onClick(this);
        }
    }

    void m3699d() {
        m3677a(m3698c());
        m3686i();
        if (this.f1760c.hasFocus()) {
            m3693q();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        m3686i();
    }

    public void m3697b() {
        m3696a((CharSequence) "", false);
        clearFocus();
        m3677a(true);
        this.f1760c.setImeOptions(this.f1753G);
        this.f1752F = false;
    }

    public void m3694a() {
        if (!this.f1752F) {
            this.f1752F = true;
            this.f1753G = this.f1760c.getImeOptions();
            this.f1760c.setImeOptions(this.f1753G | 33554432);
            this.f1760c.setText("");
            setIconified(false);
        }
    }

    private void setQuery(CharSequence charSequence) {
        this.f1760c.setText(charSequence);
        this.f1760c.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    private void m3675a(int i, String str, String str2) {
        getContext().startActivity(m3674a("android.intent.action.SEARCH", null, null, str2, i, str));
    }

    private Intent m3674a(String str, Uri uri, String str2, String str3, int i, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.f1751E);
        if (str3 != null) {
            intent.putExtra(UserSearch.ELEMENT, str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        if (this.f1755I != null) {
            intent.putExtra("app_data", this.f1755I);
        }
        if (i != 0) {
            intent.putExtra("action_key", i);
            intent.putExtra("action_msg", str4);
        }
        if (f1746b) {
            intent.setComponent(this.f1754H.getSearchActivity());
        }
        return intent;
    }

    private void m3693q() {
        f1745a.m3669a(this.f1760c);
        f1745a.m3671b(this.f1760c);
    }

    static boolean m3678a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
