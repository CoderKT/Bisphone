package com.nostra13.universalimageloader.core.assist;

import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import se.emilsjolander.stickylistheaders.C1128R;

public enum ViewScaleType {
    FIT_INSIDE,
    CROP;

    /* renamed from: com.nostra13.universalimageloader.core.assist.ViewScaleType.1 */
    /* synthetic */ class C09221 {
        static final /* synthetic */ int[] f7034a;

        static {
            f7034a = new int[ScaleType.values().length];
            try {
                f7034a[ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7034a[ScaleType.FIT_XY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7034a[ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7034a[ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f7034a[ScaleType.CENTER_INSIDE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f7034a[ScaleType.MATRIX.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f7034a[ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f7034a[ScaleType.CENTER_CROP.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static ViewScaleType m11171a(ImageView imageView) {
        switch (C09221.f7034a[imageView.getScaleType().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return FIT_INSIDE;
            default:
                return CROP;
        }
    }
}
