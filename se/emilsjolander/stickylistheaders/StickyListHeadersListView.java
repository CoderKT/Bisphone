package se.emilsjolander.stickylistheaders;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class StickyListHeadersListView extends FrameLayout {
    private WrapperViewList f8670a;
    private View f8671b;
    private Long f8672c;
    private Integer f8673d;
    private Integer f8674e;
    private OnScrollListener f8675f;
    private AdapterWrapper f8676g;
    private boolean f8677h;
    private boolean f8678i;
    private boolean f8679j;
    private int f8680k;
    private int f8681l;
    private int f8682m;
    private int f8683n;
    private int f8684o;
    private OnHeaderClickListener f8685p;
    private OnStickyHeaderOffsetChangedListener f8686q;
    private OnStickyHeaderChangedListener f8687r;
    private AdapterWrapperDataSetObserver f8688s;
    private Drawable f8689t;
    private int f8690u;

    /* renamed from: se.emilsjolander.stickylistheaders.StickyListHeadersListView.1 */
    class C11291 implements OnClickListener {
        final /* synthetic */ StickyListHeadersListView f8662a;

        C11291(StickyListHeadersListView stickyListHeadersListView) {
            this.f8662a = stickyListHeadersListView;
        }

        public void onClick(View view) {
            this.f8662a.f8685p.m13479a(this.f8662a, this.f8662a.f8671b, this.f8662a.f8673d.intValue(), this.f8662a.f8672c.longValue(), true);
        }
    }

    /* renamed from: se.emilsjolander.stickylistheaders.StickyListHeadersListView.2 */
    class C11302 implements OnClickListener {
        final /* synthetic */ StickyListHeadersListView f8663a;

        C11302(StickyListHeadersListView stickyListHeadersListView) {
            this.f8663a = stickyListHeadersListView;
        }

        public void onClick(View view) {
            this.f8663a.f8685p.m13479a(this.f8663a, this.f8663a.f8671b, this.f8663a.f8673d.intValue(), this.f8663a.f8672c.longValue(), true);
        }
    }

    /* renamed from: se.emilsjolander.stickylistheaders.StickyListHeadersListView.3 */
    class C11313 implements OnTouchListener {
        final /* synthetic */ OnTouchListener f8664a;
        final /* synthetic */ StickyListHeadersListView f8665b;

        C11313(StickyListHeadersListView stickyListHeadersListView, OnTouchListener onTouchListener) {
            this.f8665b = stickyListHeadersListView;
            this.f8664a = onTouchListener;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.f8664a.onTouch(this.f8665b, motionEvent);
        }
    }

    class AdapterWrapperDataSetObserver extends DataSetObserver {
        final /* synthetic */ StickyListHeadersListView f8666a;

        private AdapterWrapperDataSetObserver(StickyListHeadersListView stickyListHeadersListView) {
            this.f8666a = stickyListHeadersListView;
        }

        public void onChanged() {
            this.f8666a.m13488b();
        }

        public void onInvalidated() {
            this.f8666a.m13488b();
        }
    }

    class AdapterWrapperHeaderClickHandler implements OnHeaderClickListener {
        final /* synthetic */ StickyListHeadersListView f8667a;

        private AdapterWrapperHeaderClickHandler(StickyListHeadersListView stickyListHeadersListView) {
            this.f8667a = stickyListHeadersListView;
        }

        public void m13478a(View view, int i, long j) {
            this.f8667a.f8685p.m13479a(this.f8667a, view, i, j, false);
        }
    }

    public interface OnHeaderClickListener {
        void m13479a(StickyListHeadersListView stickyListHeadersListView, View view, int i, long j, boolean z);
    }

    public interface OnStickyHeaderChangedListener {
        void m13480a(StickyListHeadersListView stickyListHeadersListView, View view, int i, long j);
    }

    public interface OnStickyHeaderOffsetChangedListener {
        void m13481a(StickyListHeadersListView stickyListHeadersListView, View view, int i);
    }

    class WrapperListScrollListener implements OnScrollListener {
        final /* synthetic */ StickyListHeadersListView f8668a;

        private WrapperListScrollListener(StickyListHeadersListView stickyListHeadersListView) {
            this.f8668a = stickyListHeadersListView;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.f8668a.f8675f != null) {
                this.f8668a.f8675f.onScroll(absListView, i, i2, i3);
            }
            this.f8668a.m13489b(this.f8668a.f8670a.m13514a());
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (this.f8668a.f8675f != null) {
                this.f8668a.f8675f.onScrollStateChanged(absListView, i);
            }
        }
    }

    class WrapperViewListLifeCycleListener implements LifeCycleListener {
        final /* synthetic */ StickyListHeadersListView f8669a;

        private WrapperViewListLifeCycleListener(StickyListHeadersListView stickyListHeadersListView) {
            this.f8669a = stickyListHeadersListView;
        }

        public void m13483a(Canvas canvas) {
            if (VERSION.SDK_INT < 8) {
                this.f8669a.m13489b(this.f8669a.f8670a.m13514a());
            }
            if (this.f8669a.f8671b == null) {
                return;
            }
            if (this.f8669a.f8678i) {
                canvas.save();
                canvas.clipRect(0, this.f8669a.f8682m, this.f8669a.getRight(), this.f8669a.getBottom());
                this.f8669a.drawChild(canvas, this.f8669a.f8671b, 0);
                canvas.restore();
                return;
            }
            this.f8669a.drawChild(canvas, this.f8669a.f8671b, 0);
        }
    }

    public StickyListHeadersListView(Context context) {
        this(context, null);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C1128R.attr.stickyListHeadersListViewStyle);
    }

    @TargetApi(11)
    public StickyListHeadersListView(Context context, AttributeSet attributeSet, int i) {
        boolean z = true;
        super(context, attributeSet, i);
        this.f8677h = true;
        this.f8678i = true;
        this.f8679j = true;
        this.f8680k = 0;
        this.f8681l = 0;
        this.f8682m = 0;
        this.f8683n = 0;
        this.f8684o = 0;
        this.f8670a = new WrapperViewList(context);
        this.f8689t = this.f8670a.getDivider();
        this.f8690u = this.f8670a.getDividerHeight();
        this.f8670a.setDivider(null);
        this.f8670a.setDividerHeight(0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C1128R.styleable.StickyListHeadersListView, i, 0);
            try {
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C1128R.styleable.StickyListHeadersListView_android_padding, 0);
                this.f8681l = obtainStyledAttributes.getDimensionPixelSize(C1128R.styleable.StickyListHeadersListView_android_paddingLeft, dimensionPixelSize);
                this.f8682m = obtainStyledAttributes.getDimensionPixelSize(C1128R.styleable.StickyListHeadersListView_android_paddingTop, dimensionPixelSize);
                this.f8683n = obtainStyledAttributes.getDimensionPixelSize(C1128R.styleable.StickyListHeadersListView_android_paddingRight, dimensionPixelSize);
                this.f8684o = obtainStyledAttributes.getDimensionPixelSize(C1128R.styleable.StickyListHeadersListView_android_paddingBottom, dimensionPixelSize);
                setPadding(this.f8681l, this.f8682m, this.f8683n, this.f8684o);
                this.f8678i = obtainStyledAttributes.getBoolean(C1128R.styleable.StickyListHeadersListView_android_clipToPadding, true);
                super.setClipToPadding(true);
                this.f8670a.setClipToPadding(this.f8678i);
                int i2 = obtainStyledAttributes.getInt(C1128R.styleable.StickyListHeadersListView_android_scrollbars, 512);
                this.f8670a.setVerticalScrollBarEnabled((i2 & 512) != 0);
                WrapperViewList wrapperViewList = this.f8670a;
                if ((i2 & 256) == 0) {
                    z = false;
                }
                wrapperViewList.setHorizontalScrollBarEnabled(z);
                if (VERSION.SDK_INT >= 9) {
                    this.f8670a.setOverScrollMode(obtainStyledAttributes.getInt(C1128R.styleable.StickyListHeadersListView_android_overScrollMode, 0));
                }
                this.f8670a.setFadingEdgeLength(obtainStyledAttributes.getDimensionPixelSize(C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength, this.f8670a.getVerticalFadingEdgeLength()));
                int i3 = obtainStyledAttributes.getInt(C1128R.styleable.StickyListHeadersListView_android_requiresFadingEdge, 0);
                if (i3 == 4096) {
                    this.f8670a.setVerticalFadingEdgeEnabled(false);
                    this.f8670a.setHorizontalFadingEdgeEnabled(true);
                } else if (i3 == 8192) {
                    this.f8670a.setVerticalFadingEdgeEnabled(true);
                    this.f8670a.setHorizontalFadingEdgeEnabled(false);
                } else {
                    this.f8670a.setVerticalFadingEdgeEnabled(false);
                    this.f8670a.setHorizontalFadingEdgeEnabled(false);
                }
                this.f8670a.setCacheColorHint(obtainStyledAttributes.getColor(C1128R.styleable.StickyListHeadersListView_android_cacheColorHint, this.f8670a.getCacheColorHint()));
                if (VERSION.SDK_INT >= 11) {
                    this.f8670a.setChoiceMode(obtainStyledAttributes.getInt(C1128R.styleable.StickyListHeadersListView_android_choiceMode, this.f8670a.getChoiceMode()));
                }
                this.f8670a.setDrawSelectorOnTop(obtainStyledAttributes.getBoolean(C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop, false));
                this.f8670a.setFastScrollEnabled(obtainStyledAttributes.getBoolean(C1128R.styleable.StickyListHeadersListView_android_fastScrollEnabled, this.f8670a.isFastScrollEnabled()));
                if (VERSION.SDK_INT >= 11) {
                    this.f8670a.setFastScrollAlwaysVisible(obtainStyledAttributes.getBoolean(C1128R.styleable.StickyListHeadersListView_android_fastScrollAlwaysVisible, this.f8670a.isFastScrollAlwaysVisible()));
                }
                this.f8670a.setScrollBarStyle(obtainStyledAttributes.getInt(C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle, 0));
                if (obtainStyledAttributes.hasValue(C1128R.styleable.StickyListHeadersListView_android_listSelector)) {
                    this.f8670a.setSelector(obtainStyledAttributes.getDrawable(C1128R.styleable.StickyListHeadersListView_android_listSelector));
                }
                this.f8670a.setScrollingCacheEnabled(obtainStyledAttributes.getBoolean(C1128R.styleable.StickyListHeadersListView_android_scrollingCache, this.f8670a.isScrollingCacheEnabled()));
                if (obtainStyledAttributes.hasValue(C1128R.styleable.StickyListHeadersListView_android_divider)) {
                    this.f8689t = obtainStyledAttributes.getDrawable(C1128R.styleable.StickyListHeadersListView_android_divider);
                }
                this.f8670a.setStackFromBottom(obtainStyledAttributes.getBoolean(C1128R.styleable.StickyListHeadersListView_android_stackFromBottom, false));
                this.f8690u = obtainStyledAttributes.getDimensionPixelSize(C1128R.styleable.StickyListHeadersListView_android_dividerHeight, this.f8690u);
                this.f8670a.setTranscriptMode(obtainStyledAttributes.getInt(C1128R.styleable.StickyListHeadersListView_android_transcriptMode, 0));
                this.f8677h = obtainStyledAttributes.getBoolean(C1128R.styleable.StickyListHeadersListView_hasStickyHeaders, true);
                this.f8679j = obtainStyledAttributes.getBoolean(C1128R.styleable.StickyListHeadersListView_isDrawingListUnderStickyHeader, true);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.f8670a.m13516a(new WrapperViewListLifeCycleListener());
        this.f8670a.setOnScrollListener(new WrapperListScrollListener());
        addView(this.f8670a);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        m13497d(this.f8671b);
    }

    private void m13494c(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        } else if (layoutParams.height == -1 || layoutParams.width == -2) {
            layoutParams.height = -2;
            layoutParams.width = -1;
            view.setLayoutParams(layoutParams);
        }
    }

    private void m13497d(View view) {
        if (view != null) {
            measureChild(view, MeasureSpec.makeMeasureSpec((getMeasuredWidth() - this.f8681l) - this.f8683n, 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f8670a.layout(0, 0, this.f8670a.getMeasuredWidth(), getHeight());
        if (this.f8671b != null) {
            int i5 = ((MarginLayoutParams) this.f8671b.getLayoutParams()).topMargin;
            this.f8671b.layout(this.f8681l, i5, this.f8671b.getMeasuredWidth() + this.f8681l, this.f8671b.getMeasuredHeight() + i5);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.f8670a.getVisibility() == 0 || this.f8670a.getAnimation() != null) {
            drawChild(canvas, this.f8670a, 0);
        }
    }

    private void m13488b() {
        if (this.f8671b != null) {
            removeView(this.f8671b);
            this.f8671b = null;
            this.f8672c = null;
            this.f8673d = null;
            this.f8674e = null;
            this.f8670a.m13515a(0);
            m13492c();
        }
    }

    private void m13489b(int i) {
        int i2 = 0;
        int count = this.f8676g == null ? 0 : this.f8676g.getCount();
        if (count != 0 && this.f8677h) {
            int headerViewsCount = i - this.f8670a.getHeaderViewsCount();
            if (this.f8670a.getChildCount() > 0 && this.f8670a.getChildAt(0).getBottom() < m13495d()) {
                headerViewsCount++;
            }
            int i3 = this.f8670a.getChildCount() != 0 ? 1 : 0;
            int i4;
            if (i3 == 0 || this.f8670a.getFirstVisiblePosition() != 0 || this.f8670a.getChildAt(0).getTop() < m13495d()) {
                i4 = 0;
            } else {
                i4 = 1;
            }
            if (headerViewsCount > count - 1 || headerViewsCount < 0) {
                i2 = 1;
            }
            if (i3 != 0 && r1 == 0 && r3 == 0) {
                m13493c(headerViewsCount);
            } else {
                m13488b();
            }
        }
    }

    private void m13493c(int i) {
        int min;
        if (this.f8673d == null || this.f8673d.intValue() != i) {
            this.f8673d = Integer.valueOf(i);
            long b = this.f8676g.m13474b(i);
            if (this.f8672c == null || this.f8672c.longValue() != b) {
                this.f8672c = Long.valueOf(b);
                View a = this.f8676g.m13471a(this.f8673d.intValue(), this.f8671b, this);
                if (this.f8671b != a) {
                    if (a == null) {
                        throw new NullPointerException("header may not be null");
                    }
                    m13499e(a);
                }
                m13494c(this.f8671b);
                m13497d(this.f8671b);
                if (this.f8687r != null) {
                    this.f8687r.m13480a(this, this.f8671b, i, this.f8672c.longValue());
                }
                this.f8674e = null;
            }
        }
        int d = m13495d();
        for (int i2 = 0; i2 < this.f8670a.getChildCount(); i2++) {
            View childAt = this.f8670a.getChildAt(i2);
            Object obj = ((childAt instanceof WrapperView) && ((WrapperView) childAt).m13477a()) ? 1 : null;
            boolean a2 = this.f8670a.m13518a(childAt);
            if (childAt.getTop() >= m13495d() && (obj != null || a2)) {
                min = Math.min(childAt.getTop() - this.f8671b.getMeasuredHeight(), d);
                break;
            }
        }
        min = d;
        setHeaderOffet(min);
        if (!this.f8679j) {
            this.f8670a.m13515a(this.f8671b.getMeasuredHeight() + this.f8674e.intValue());
        }
        m13492c();
    }

    private void m13499e(View view) {
        if (this.f8671b != null) {
            removeView(this.f8671b);
        }
        this.f8671b = view;
        addView(this.f8671b);
        if (this.f8685p != null) {
            this.f8671b.setOnClickListener(new C11291(this));
        }
        this.f8671b.setClickable(true);
    }

    private void m13492c() {
        int d = m13495d();
        int childCount = this.f8670a.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.f8670a.getChildAt(i);
            if (childAt instanceof WrapperView) {
                WrapperView wrapperView = (WrapperView) childAt;
                if (wrapperView.m13477a()) {
                    View view = wrapperView.f8659d;
                    if (wrapperView.getTop() < d) {
                        if (view.getVisibility() != 4) {
                            view.setVisibility(4);
                        }
                    } else if (view.getVisibility() != 0) {
                        view.setVisibility(0);
                    }
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void setHeaderOffet(int i) {
        if (this.f8674e == null || this.f8674e.intValue() != i) {
            this.f8674e = Integer.valueOf(i);
            if (VERSION.SDK_INT >= 11) {
                this.f8671b.setTranslationY((float) this.f8674e.intValue());
            } else {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f8671b.getLayoutParams();
                marginLayoutParams.topMargin = this.f8674e.intValue();
                this.f8671b.setLayoutParams(marginLayoutParams);
            }
            if (this.f8686q != null) {
                this.f8686q.m13481a(this, this.f8671b, -this.f8674e.intValue());
            }
        }
    }

    private boolean m13498d(int i) {
        return i == 0 || this.f8676g.m13474b(i) != this.f8676g.m13474b(i - 1);
    }

    public int m13506a(int i) {
        if (m13498d(Math.max(0, i - getHeaderViewsCount()))) {
            return 0;
        }
        View a = this.f8676g.m13471a(i, null, this.f8670a);
        if (a == null) {
            throw new NullPointerException("header may not be null");
        }
        m13494c(a);
        m13497d(a);
        return a.getMeasuredHeight();
    }

    private int m13495d() {
        return (this.f8678i ? this.f8682m : 0) + this.f8680k;
    }

    public void setAreHeadersSticky(boolean z) {
        this.f8677h = z;
        if (z) {
            m13489b(this.f8670a.m13514a());
        } else {
            m13488b();
        }
        this.f8670a.invalidate();
    }

    public boolean m13509a() {
        return this.f8677h;
    }

    @Deprecated
    public boolean getAreHeadersSticky() {
        return m13509a();
    }

    public void setStickyHeaderTopOffset(int i) {
        this.f8680k = i;
        m13489b(this.f8670a.m13514a());
    }

    public int getStickyHeaderTopOffset() {
        return this.f8680k;
    }

    public void setDrawingListUnderStickyHeader(boolean z) {
        this.f8679j = z;
        this.f8670a.m13515a(0);
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.f8685p = onHeaderClickListener;
        if (this.f8676g == null) {
            return;
        }
        if (this.f8685p != null) {
            this.f8676g.m13473a(new AdapterWrapperHeaderClickHandler());
            if (this.f8671b != null) {
                this.f8671b.setOnClickListener(new C11302(this));
                return;
            }
            return;
        }
        this.f8676g.m13473a(null);
    }

    public void setOnStickyHeaderOffsetChangedListener(OnStickyHeaderOffsetChangedListener onStickyHeaderOffsetChangedListener) {
        this.f8686q = onStickyHeaderOffsetChangedListener;
    }

    public void setOnStickyHeaderChangedListener(OnStickyHeaderChangedListener onStickyHeaderChangedListener) {
        this.f8687r = onStickyHeaderChangedListener;
    }

    public int getListChildCount() {
        return this.f8670a.getChildCount();
    }

    public ListView getWrappedList() {
        return this.f8670a;
    }

    private boolean m13501e(int i) {
        if (VERSION.SDK_INT >= i) {
            return true;
        }
        Log.e("StickyListHeaders", "Api lvl must be at least " + i + " to call this method");
        return false;
    }

    public void setAdapter(StickyListHeadersAdapter stickyListHeadersAdapter) {
        if (stickyListHeadersAdapter == null) {
            if (this.f8676g instanceof SectionIndexerAdapterWrapper) {
                ((SectionIndexerAdapterWrapper) this.f8676g).f8661b = null;
            }
            if (this.f8676g != null) {
                this.f8676g.f8649a = null;
            }
            this.f8670a.setAdapter(null);
            m13488b();
            return;
        }
        if (this.f8676g != null) {
            this.f8676g.unregisterDataSetObserver(this.f8688s);
        }
        if (stickyListHeadersAdapter instanceof SectionIndexer) {
            this.f8676g = new SectionIndexerAdapterWrapper(getContext(), stickyListHeadersAdapter);
        } else {
            this.f8676g = new AdapterWrapper(getContext(), stickyListHeadersAdapter);
        }
        this.f8688s = new AdapterWrapperDataSetObserver();
        this.f8676g.registerDataSetObserver(this.f8688s);
        if (this.f8685p != null) {
            this.f8676g.m13473a(new AdapterWrapperHeaderClickHandler());
        } else {
            this.f8676g.m13473a(null);
        }
        this.f8676g.m13472a(this.f8689t, this.f8690u);
        this.f8670a.setAdapter(this.f8676g);
        m13488b();
    }

    public StickyListHeadersAdapter getAdapter() {
        return this.f8676g == null ? null : this.f8676g.f8649a;
    }

    public void setDivider(Drawable drawable) {
        this.f8689t = drawable;
        if (this.f8676g != null) {
            this.f8676g.m13472a(this.f8689t, this.f8690u);
        }
    }

    public void setDividerHeight(int i) {
        this.f8690u = i;
        if (this.f8676g != null) {
            this.f8676g.m13472a(this.f8689t, this.f8690u);
        }
    }

    public Drawable getDivider() {
        return this.f8689t;
    }

    public int getDividerHeight() {
        return this.f8690u;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.f8675f = onScrollListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        if (onTouchListener != null) {
            this.f8670a.setOnTouchListener(new C11313(this, onTouchListener));
        } else {
            this.f8670a.setOnTouchListener(null);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f8670a.setOnItemClickListener(onItemClickListener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.f8670a.setOnItemLongClickListener(onItemLongClickListener);
    }

    public void m13508a(View view) {
        this.f8670a.addHeaderView(view);
    }

    public int getHeaderViewsCount() {
        return this.f8670a.getHeaderViewsCount();
    }

    public void m13510b(View view) {
        this.f8670a.addFooterView(view);
    }

    public int getFooterViewsCount() {
        return this.f8670a.getFooterViewsCount();
    }

    public void setEmptyView(View view) {
        this.f8670a.setEmptyView(view);
    }

    public View getEmptyView() {
        return this.f8670a.getEmptyView();
    }

    public boolean isVerticalScrollBarEnabled() {
        return this.f8670a.isVerticalScrollBarEnabled();
    }

    public boolean isHorizontalScrollBarEnabled() {
        return this.f8670a.isHorizontalScrollBarEnabled();
    }

    public void setVerticalScrollBarEnabled(boolean z) {
        this.f8670a.setVerticalScrollBarEnabled(z);
    }

    public void setHorizontalScrollBarEnabled(boolean z) {
        this.f8670a.setHorizontalScrollBarEnabled(z);
    }

    @TargetApi(9)
    public int getOverScrollMode() {
        if (m13501e(9)) {
            return this.f8670a.getOverScrollMode();
        }
        return 0;
    }

    @TargetApi(9)
    public void setOverScrollMode(int i) {
        if (m13501e(9) && this.f8670a != null) {
            this.f8670a.setOverScrollMode(i);
        }
    }

    public void setSelection(int i) {
        m13507a(i, 0);
    }

    public void m13507a(int i, int i2) {
        int i3 = 0;
        int a = (this.f8676g == null ? 0 : m13506a(i)) + i2;
        if (!this.f8678i) {
            i3 = this.f8682m;
        }
        this.f8670a.setSelectionFromTop(i, a - i3);
    }

    public void setSelector(Drawable drawable) {
        this.f8670a.setSelector(drawable);
    }

    public void setSelector(int i) {
        this.f8670a.setSelector(i);
    }

    public int getFirstVisiblePosition() {
        return this.f8670a.getFirstVisiblePosition();
    }

    public int getLastVisiblePosition() {
        return this.f8670a.getLastVisiblePosition();
    }

    @TargetApi(11)
    public void setChoiceMode(int i) {
        this.f8670a.setChoiceMode(i);
    }

    @TargetApi(11)
    public int getCheckedItemCount() {
        if (m13501e(11)) {
            return this.f8670a.getCheckedItemCount();
        }
        return 0;
    }

    @TargetApi(8)
    public long[] getCheckedItemIds() {
        if (m13501e(8)) {
            return this.f8670a.getCheckedItemIds();
        }
        return null;
    }

    @TargetApi(11)
    public int getCheckedItemPosition() {
        return this.f8670a.getCheckedItemPosition();
    }

    @TargetApi(11)
    public SparseBooleanArray getCheckedItemPositions() {
        return this.f8670a.getCheckedItemPositions();
    }

    public int getCount() {
        return this.f8670a.getCount();
    }

    public void setOnCreateContextMenuListener(OnCreateContextMenuListener onCreateContextMenuListener) {
        this.f8670a.setOnCreateContextMenuListener(onCreateContextMenuListener);
    }

    public boolean showContextMenu() {
        return this.f8670a.showContextMenu();
    }

    public void setClipToPadding(boolean z) {
        if (this.f8670a != null) {
            this.f8670a.setClipToPadding(z);
        }
        this.f8678i = z;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f8681l = i;
        this.f8682m = i2;
        this.f8683n = i3;
        this.f8684o = i4;
        if (this.f8670a != null) {
            this.f8670a.setPadding(i, i2, i3, i4);
        }
        super.setPadding(0, 0, 0, 0);
        requestLayout();
    }

    public int getPaddingLeft() {
        return this.f8681l;
    }

    public int getPaddingTop() {
        return this.f8682m;
    }

    public int getPaddingRight() {
        return this.f8683n;
    }

    public int getPaddingBottom() {
        return this.f8684o;
    }

    public void setFastScrollEnabled(boolean z) {
        this.f8670a.setFastScrollEnabled(z);
    }

    @TargetApi(11)
    public void setFastScrollAlwaysVisible(boolean z) {
        if (m13501e(11)) {
            this.f8670a.setFastScrollAlwaysVisible(z);
        }
    }

    public void setScrollBarStyle(int i) {
        this.f8670a.setScrollBarStyle(i);
    }

    public int getScrollBarStyle() {
        return this.f8670a.getScrollBarStyle();
    }

    @TargetApi(11)
    public void setMultiChoiceModeListener(MultiChoiceModeListener multiChoiceModeListener) {
        if (m13501e(11)) {
            this.f8670a.setMultiChoiceModeListener(multiChoiceModeListener);
        }
    }

    public Parcelable onSaveInstanceState() {
        if (super.onSaveInstanceState() == BaseSavedState.EMPTY_STATE) {
            return this.f8670a.onSaveInstanceState();
        }
        throw new IllegalStateException("Handling non empty state of parent class is not implemented");
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(BaseSavedState.EMPTY_STATE);
        this.f8670a.onRestoreInstanceState(parcelable);
    }

    @TargetApi(14)
    public boolean canScrollVertically(int i) {
        return this.f8670a.canScrollVertically(i);
    }

    public void setTranscriptMode(int i) {
        this.f8670a.setTranscriptMode(i);
    }

    public void setBlockLayoutChildren(boolean z) {
        this.f8670a.m13517a(z);
    }

    public void setStackFromBottom(boolean z) {
        this.f8670a.setStackFromBottom(z);
    }
}
