package com.edmodo.cropper.util;

import android.content.Context;
import android.util.Pair;
import android.util.TypedValue;
import com.edmodo.cropper.cropwindow.CropOverlayView;
import com.edmodo.cropper.cropwindow.handle.Handle;
import se.emilsjolander.stickylistheaders.C1128R;

public class HandleUtil {

    /* renamed from: com.edmodo.cropper.util.HandleUtil.1 */
    /* synthetic */ class C06171 {
        static final /* synthetic */ int[] f5622a;

        static {
            f5622a = new int[Handle.values().length];
            try {
                f5622a[Handle.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5622a[Handle.TOP_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5622a[Handle.BOTTOM_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f5622a[Handle.BOTTOM_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f5622a[Handle.LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f5622a[Handle.TOP.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f5622a[Handle.RIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f5622a[Handle.BOTTOM.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f5622a[Handle.CENTER.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public static float m8271a(Context context) {
        return TypedValue.applyDimension(1, 24.0f, context.getResources().getDisplayMetrics());
    }

    public static Handle m8273a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (m8275a(f, f2, f3, f4, f7)) {
            return Handle.TOP_LEFT;
        }
        if (m8275a(f, f2, f5, f4, f7)) {
            return Handle.TOP_RIGHT;
        }
        if (m8275a(f, f2, f3, f6, f7)) {
            return Handle.BOTTOM_LEFT;
        }
        if (m8275a(f, f2, f5, f6, f7)) {
            return Handle.BOTTOM_RIGHT;
        }
        if (m8278c(f, f2, f3, f4, f5, f6) && m8274a()) {
            return Handle.CENTER;
        }
        if (m8276a(f, f2, f3, f5, f4, f7)) {
            return Handle.TOP;
        }
        if (m8276a(f, f2, f3, f5, f6, f7)) {
            return Handle.BOTTOM;
        }
        if (m8277b(f, f2, f3, f4, f6, f7)) {
            return Handle.LEFT;
        }
        if (m8277b(f, f2, f5, f4, f6, f7)) {
            return Handle.RIGHT;
        }
        return (!m8278c(f, f2, f3, f4, f5, f6) || m8274a()) ? null : Handle.CENTER;
    }

    public static Pair<Float, Float> m8272a(Handle handle, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = 0.0f;
        if (handle == null) {
            return null;
        }
        float f8;
        switch (C06171.f5622a[handle.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                f8 = f3 - f;
                f7 = f4 - f2;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                f8 = f5 - f;
                f7 = f4 - f2;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                f8 = f3 - f;
                f7 = f6 - f2;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                f8 = f5 - f;
                f7 = f6 - f2;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                f8 = f3 - f;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                f8 = 0.0f;
                f7 = f4 - f2;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                f8 = f5 - f;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                f8 = 0.0f;
                f7 = f6 - f2;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                f8 = ((f5 + f3) / 2.0f) - f;
                f7 = ((f4 + f6) / 2.0f) - f2;
                break;
            default:
                f8 = 0.0f;
                break;
        }
        return new Pair(Float.valueOf(f8), Float.valueOf(f7));
    }

    private static boolean m8275a(float f, float f2, float f3, float f4, float f5) {
        if (Math.abs(f - f3) > f5 || Math.abs(f2 - f4) > f5) {
            return false;
        }
        return true;
    }

    private static boolean m8276a(float f, float f2, float f3, float f4, float f5, float f6) {
        if (f <= f3 || f >= f4 || Math.abs(f2 - f5) > f6) {
            return false;
        }
        return true;
    }

    private static boolean m8277b(float f, float f2, float f3, float f4, float f5, float f6) {
        if (Math.abs(f - f3) > f6 || f2 <= f4 || f2 >= f5) {
            return false;
        }
        return true;
    }

    private static boolean m8278c(float f, float f2, float f3, float f4, float f5, float f6) {
        if (f <= f3 || f >= f5 || f2 <= f4 || f2 >= f6) {
            return false;
        }
        return true;
    }

    private static boolean m8274a() {
        return !CropOverlayView.m8231b();
    }
}
