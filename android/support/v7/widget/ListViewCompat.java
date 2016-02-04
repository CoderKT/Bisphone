package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;
import se.emilsjolander.stickylistheaders.C1128R;

public class ListViewCompat extends ListView {
    private static final int[] f1563g;
    final Rect f1564a;
    int f1565b;
    int f1566c;
    int f1567d;
    int f1568e;
    protected int f1569f;
    private Field f1570h;
    private GateKeeperDrawable f1571i;

    class GateKeeperDrawable extends DrawableWrapper {
        private boolean f1584a;

        public GateKeeperDrawable(Drawable drawable) {
            super(drawable);
            this.f1584a = true;
        }

        void m3258a(boolean z) {
            this.f1584a = z;
        }

        public boolean setState(int[] iArr) {
            if (this.f1584a) {
                return super.setState(iArr);
            }
            return false;
        }

        public void draw(Canvas canvas) {
            if (this.f1584a) {
                super.draw(canvas);
            }
        }

        public void setHotspot(float f, float f2) {
            if (this.f1584a) {
                super.setHotspot(f, f2);
            }
        }

        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.f1584a) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        public boolean setVisible(boolean z, boolean z2) {
            if (this.f1584a) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    static {
        f1563g = new int[]{0};
    }

    public ListViewCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1564a = new Rect();
        this.f1565b = 0;
        this.f1566c = 0;
        this.f1567d = 0;
        this.f1568e = 0;
        try {
            this.f1570h = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f1570h.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void setSelector(Drawable drawable) {
        this.f1571i = drawable != null ? new GateKeeperDrawable(drawable) : null;
        super.setSelector(this.f1571i);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1565b = rect.left;
        this.f1566c = rect.top;
        this.f1567d = rect.right;
        this.f1568e = rect.bottom;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        setSelectorEnabled(true);
        m3249b();
    }

    protected void dispatchDraw(Canvas canvas) {
        m3247a(canvas);
        super.dispatchDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f1569f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    protected void m3249b() {
        Drawable selector = getSelector();
        if (selector != null && m3251c()) {
            selector.setState(getDrawableState());
        }
    }

    protected boolean m3251c() {
        return m3248a() && isPressed();
    }

    protected boolean m3248a() {
        return false;
    }

    protected void m3247a(Canvas canvas) {
        if (!this.f1564a.isEmpty()) {
            Drawable selector = getSelector();
            if (selector != null) {
                selector.setBounds(this.f1564a);
                selector.draw(canvas);
            }
        }
    }

    protected void m3246a(int i, View view, float f, float f2) {
        m3245a(i, view);
        Drawable selector = getSelector();
        if (selector != null && i != -1) {
            DrawableCompat.m657a(selector, f, f2);
        }
    }

    protected void m3245a(int i, View view) {
        boolean z = true;
        Drawable selector = getSelector();
        boolean z2 = (selector == null || i == -1) ? false : true;
        if (z2) {
            selector.setVisible(false, false);
        }
        m3250b(i, view);
        if (z2) {
            Rect rect = this.f1564a;
            float exactCenterX = rect.exactCenterX();
            float exactCenterY = rect.exactCenterY();
            if (getVisibility() != 0) {
                z = false;
            }
            selector.setVisible(z, false);
            DrawableCompat.m657a(selector, exactCenterX, exactCenterY);
        }
    }

    protected void m3250b(int i, View view) {
        Rect rect = this.f1564a;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f1565b;
        rect.top -= this.f1566c;
        rect.right += this.f1567d;
        rect.bottom += this.f1568e;
        try {
            boolean z = this.f1570h.getBoolean(this);
            if (view.isEnabled() != z) {
                this.f1570h.set(this, Boolean.valueOf(!z));
                if (i != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public int m3244a(int i, int i2, int i3, int i4, int i5) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        listPaddingBottom += listPaddingTop;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int i6 = 0;
        View view = null;
        int i7 = 0;
        int count = adapter.getCount();
        int i8 = 0;
        while (i8 < count) {
            View view2;
            listPaddingTop = adapter.getItemViewType(i8);
            if (listPaddingTop != i7) {
                int i9 = listPaddingTop;
                view2 = null;
                i7 = i9;
            } else {
                view2 = view;
            }
            view = adapter.getView(i8, view2, this);
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            if (layoutParams.height > 0) {
                listPaddingTop = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            } else {
                listPaddingTop = MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, listPaddingTop);
            view.forceLayout();
            if (i8 > 0) {
                listPaddingTop = listPaddingBottom + dividerHeight;
            } else {
                listPaddingTop = listPaddingBottom;
            }
            listPaddingTop += view.getMeasuredHeight();
            if (listPaddingTop < i4) {
                if (i5 >= 0 && i8 >= i5) {
                    i6 = listPaddingTop;
                }
                i8++;
                listPaddingBottom = listPaddingTop;
            } else if (i5 < 0 || i8 <= i5 || i6 <= 0 || listPaddingTop == i4) {
                return i4;
            } else {
                return i6;
            }
        }
        return listPaddingBottom;
    }

    protected void setSelectorEnabled(boolean z) {
        if (this.f1571i != null) {
            this.f1571i.m3258a(z);
        }
    }
}
