package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class WrapperViewList extends ListView {
    private LifeCycleListener f8691a;
    private List<View> f8692b;
    private int f8693c;
    private Rect f8694d;
    private Field f8695e;
    private boolean f8696f;
    private boolean f8697g;

    interface LifeCycleListener {
        void m13482a(Canvas canvas);
    }

    public WrapperViewList(Context context) {
        super(context);
        this.f8694d = new Rect();
        this.f8696f = true;
        this.f8697g = false;
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mSelectorRect");
            declaredField.setAccessible(true);
            this.f8694d = (Rect) declaredField.get(this);
            if (VERSION.SDK_INT >= 14) {
                this.f8695e = AbsListView.class.getDeclaredField("mSelectorPosition");
                this.f8695e.setAccessible(true);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public boolean performItemClick(View view, int i, long j) {
        if (view instanceof WrapperView) {
            view = ((WrapperView) view).f8656a;
        }
        return super.performItemClick(view, i, j);
    }

    private void m13511b() {
        if (!this.f8694d.isEmpty()) {
            int c = m13513c();
            if (c >= 0) {
                View childAt = getChildAt(c - m13514a());
                if (childAt instanceof WrapperView) {
                    WrapperView wrapperView = (WrapperView) childAt;
                    this.f8694d.top = wrapperView.f8660e + wrapperView.getTop();
                }
            }
        }
    }

    private int m13513c() {
        if (this.f8695e == null) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i).getBottom() == this.f8694d.bottom) {
                    return i + m13514a();
                }
            }
        } else {
            try {
                return this.f8695e.getInt(this);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        return -1;
    }

    protected void dispatchDraw(Canvas canvas) {
        m13511b();
        if (this.f8693c != 0) {
            canvas.save();
            Rect clipBounds = canvas.getClipBounds();
            clipBounds.top = this.f8693c;
            canvas.clipRect(clipBounds);
            super.dispatchDraw(canvas);
            canvas.restore();
        } else {
            super.dispatchDraw(canvas);
        }
        this.f8691a.m13482a(canvas);
    }

    void m13516a(LifeCycleListener lifeCycleListener) {
        this.f8691a = lifeCycleListener;
    }

    public void addFooterView(View view) {
        super.addFooterView(view);
        m13512b(view);
    }

    public void addFooterView(View view, Object obj, boolean z) {
        super.addFooterView(view, obj, z);
        m13512b(view);
    }

    private void m13512b(View view) {
        if (this.f8692b == null) {
            this.f8692b = new ArrayList();
        }
        this.f8692b.add(view);
    }

    public boolean removeFooterView(View view) {
        if (!super.removeFooterView(view)) {
            return false;
        }
        this.f8692b.remove(view);
        return true;
    }

    boolean m13518a(View view) {
        if (this.f8692b == null) {
            return false;
        }
        return this.f8692b.contains(view);
    }

    void m13515a(int i) {
        this.f8693c = i;
    }

    int m13514a() {
        int firstVisiblePosition = getFirstVisiblePosition();
        if (VERSION.SDK_INT >= 11) {
            return firstVisiblePosition;
        }
        int i;
        for (i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getBottom() >= 0) {
                i += firstVisiblePosition;
                break;
            }
        }
        i = firstVisiblePosition;
        if (!this.f8696f && getPaddingTop() > 0 && i > 0 && getChildAt(0).getTop() > 0) {
            i--;
        }
        return i;
    }

    public void setClipToPadding(boolean z) {
        this.f8696f = z;
        super.setClipToPadding(z);
    }

    public void m13517a(boolean z) {
        this.f8697g = z;
    }

    protected void layoutChildren() {
        if (!this.f8697g) {
            super.layoutChildren();
        }
    }
}
