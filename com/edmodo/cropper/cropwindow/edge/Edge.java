package com.edmodo.cropper.cropwindow.edge;

import android.graphics.Rect;
import com.edmodo.cropper.util.AspectRatioUtil;
import se.emilsjolander.stickylistheaders.C1128R;

public enum Edge {
    LEFT,
    TOP,
    RIGHT,
    BOTTOM;
    
    private float f5603e;

    /* renamed from: com.edmodo.cropper.cropwindow.edge.Edge.1 */
    /* synthetic */ class C06161 {
        static final /* synthetic */ int[] f5597a;

        static {
            f5597a = new int[Edge.values().length];
            try {
                f5597a[Edge.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5597a[Edge.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5597a[Edge.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f5597a[Edge.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public void m8244a(float f) {
        this.f5603e = f;
    }

    public void m8249b(float f) {
        this.f5603e += f;
    }

    public float m8242a() {
        return this.f5603e;
    }

    public void m8245a(float f, float f2, Rect rect, float f3, float f4) {
        switch (C06161.f5597a[ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f5603e = m8235a(f, rect, f3, f4);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f5603e = m8240c(f2, rect, f3, f4);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f5603e = m8238b(f, rect, f3, f4);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f5603e = m8241d(f2, rect, f3, f4);
            default:
        }
    }

    public void m8250c(float f) {
        float a = LEFT.m8242a();
        float a2 = TOP.m8242a();
        float a3 = RIGHT.m8242a();
        float a4 = BOTTOM.m8242a();
        switch (C06161.f5597a[ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f5603e = AspectRatioUtil.m8267b(a2, a3, a4, f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f5603e = AspectRatioUtil.m8268c(a, a3, a4, f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f5603e = AspectRatioUtil.m8269d(a, a2, a4, f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f5603e = AspectRatioUtil.m8270e(a, a2, a3, f);
            default:
        }
    }

    public boolean m8247a(Edge edge, Rect rect, float f) {
        float b = edge.m8248b(rect);
        float f2;
        float a;
        float a2;
        float f3;
        switch (C06161.f5597a[ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (edge.equals(TOP)) {
                    f2 = (float) rect.top;
                    a = BOTTOM.m8242a() - b;
                    a2 = RIGHT.m8242a();
                    return m8236a(f2, AspectRatioUtil.m8267b(f2, a2, a, f), a, a2, rect);
                } else if (edge.equals(BOTTOM)) {
                    a = (float) rect.bottom;
                    f2 = TOP.m8242a() - b;
                    a2 = RIGHT.m8242a();
                    return m8236a(f2, AspectRatioUtil.m8267b(f2, a2, a, f), a, a2, rect);
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (edge.equals(LEFT)) {
                    f3 = (float) rect.left;
                    a2 = RIGHT.m8242a() - b;
                    a = BOTTOM.m8242a();
                    return m8236a(AspectRatioUtil.m8268c(f3, a2, a, f), f3, a, a2, rect);
                } else if (edge.equals(RIGHT)) {
                    a2 = (float) rect.right;
                    f3 = LEFT.m8242a() - b;
                    a = BOTTOM.m8242a();
                    return m8236a(AspectRatioUtil.m8268c(f3, a2, a, f), f3, a, a2, rect);
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                if (edge.equals(TOP)) {
                    f2 = (float) rect.top;
                    a = BOTTOM.m8242a() - b;
                    f3 = LEFT.m8242a();
                    return m8236a(f2, f3, a, AspectRatioUtil.m8269d(f3, f2, a, f), rect);
                } else if (edge.equals(BOTTOM)) {
                    a = (float) rect.bottom;
                    f2 = TOP.m8242a() - b;
                    f3 = LEFT.m8242a();
                    return m8236a(f2, f3, a, AspectRatioUtil.m8269d(f3, f2, a, f), rect);
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                if (edge.equals(LEFT)) {
                    f3 = (float) rect.left;
                    a2 = RIGHT.m8242a() - b;
                    f2 = TOP.m8242a();
                    return m8236a(f2, f3, AspectRatioUtil.m8270e(f3, f2, a2, f), a2, rect);
                } else if (edge.equals(RIGHT)) {
                    a2 = (float) rect.right;
                    f3 = LEFT.m8242a() - b;
                    f2 = TOP.m8242a();
                    return m8236a(f2, f3, AspectRatioUtil.m8270e(f3, f2, a2, f), a2, rect);
                }
                break;
        }
        return true;
    }

    private boolean m8236a(float f, float f2, float f3, float f4, Rect rect) {
        return f < ((float) rect.top) || f2 < ((float) rect.left) || f3 > ((float) rect.bottom) || f4 > ((float) rect.right);
    }

    public float m8243a(Rect rect) {
        float f = this.f5603e;
        switch (C06161.f5597a[ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f5603e = (float) rect.left;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f5603e = (float) rect.top;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f5603e = (float) rect.right;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f5603e = (float) rect.bottom;
                break;
        }
        return this.f5603e - f;
    }

    public float m8248b(Rect rect) {
        float f;
        float f2 = this.f5603e;
        switch (C06161.f5597a[ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                f = (float) rect.left;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                f = (float) rect.top;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                f = (float) rect.right;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                f = (float) rect.bottom;
                break;
            default:
                f = f2;
                break;
        }
        return f - f2;
    }

    public static float m8237b() {
        return RIGHT.m8242a() - LEFT.m8242a();
    }

    public static float m8239c() {
        return BOTTOM.m8242a() - TOP.m8242a();
    }

    public boolean m8246a(Rect rect, float f) {
        switch (C06161.f5597a[ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (this.f5603e - ((float) rect.left) >= f) {
                    return false;
                }
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (this.f5603e - ((float) rect.top) >= f) {
                    return false;
                }
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                if (((float) rect.right) - this.f5603e >= f) {
                    return false;
                }
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                if (((float) rect.bottom) - this.f5603e >= f) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    private static float m8235a(float f, Rect rect, float f2, float f3) {
        float f4 = Float.POSITIVE_INFINITY;
        if (f - ((float) rect.left) < f2) {
            return (float) rect.left;
        }
        float a;
        if (f >= RIGHT.m8242a() - 40.0f) {
            a = RIGHT.m8242a() - 40.0f;
        } else {
            a = Float.POSITIVE_INFINITY;
        }
        if ((RIGHT.m8242a() - f) / f3 <= 40.0f) {
            f4 = RIGHT.m8242a() - (40.0f * f3);
        }
        return Math.min(f, Math.min(a, f4));
    }

    private static float m8238b(float f, Rect rect, float f2, float f3) {
        float f4 = Float.NEGATIVE_INFINITY;
        if (((float) rect.right) - f < f2) {
            return (float) rect.right;
        }
        float a;
        if (f <= LEFT.m8242a() + 40.0f) {
            a = LEFT.m8242a() + 40.0f;
        } else {
            a = Float.NEGATIVE_INFINITY;
        }
        if ((f - LEFT.m8242a()) / f3 <= 40.0f) {
            f4 = LEFT.m8242a() + (40.0f * f3);
        }
        return Math.max(f, Math.max(a, f4));
    }

    private static float m8240c(float f, Rect rect, float f2, float f3) {
        float f4 = Float.POSITIVE_INFINITY;
        if (f - ((float) rect.top) < f2) {
            return (float) rect.top;
        }
        float a;
        if (f >= BOTTOM.m8242a() - 40.0f) {
            a = BOTTOM.m8242a() - 40.0f;
        } else {
            a = Float.POSITIVE_INFINITY;
        }
        if ((BOTTOM.m8242a() - f) * f3 <= 40.0f) {
            f4 = BOTTOM.m8242a() - (40.0f / f3);
        }
        return Math.min(f, Math.min(a, f4));
    }

    private static float m8241d(float f, Rect rect, float f2, float f3) {
        float f4 = Float.NEGATIVE_INFINITY;
        if (((float) rect.bottom) - f < f2) {
            return (float) rect.bottom;
        }
        float a;
        if (f <= TOP.m8242a() + 40.0f) {
            a = TOP.m8242a() + 40.0f;
        } else {
            a = Float.NEGATIVE_INFINITY;
        }
        if ((f - TOP.m8242a()) * f3 <= 40.0f) {
            f4 = TOP.m8242a() + (40.0f / f3);
        }
        return Math.max(f, Math.max(f4, a));
    }
}
