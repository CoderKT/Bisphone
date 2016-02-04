package com.nostra13.universalimageloader.core;

import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

public final class DisplayImageOptions {
    private final int f6886a;
    private final int f6887b;
    private final int f6888c;
    private final Drawable f6889d;
    private final Drawable f6890e;
    private final Drawable f6891f;
    private final boolean f6892g;
    private final boolean f6893h;
    private final boolean f6894i;
    private final ImageScaleType f6895j;
    private final Options f6896k;
    private final int f6897l;
    private final boolean f6898m;
    private final Object f6899n;
    private final BitmapProcessor f6900o;
    private final BitmapProcessor f6901p;
    private final BitmapDisplayer f6902q;
    private final Handler f6903r;
    private final boolean f6904s;

    public class Builder {
        private int f6867a;
        private int f6868b;
        private int f6869c;
        private Drawable f6870d;
        private Drawable f6871e;
        private Drawable f6872f;
        private boolean f6873g;
        private boolean f6874h;
        private boolean f6875i;
        private ImageScaleType f6876j;
        private Options f6877k;
        private int f6878l;
        private boolean f6879m;
        private Object f6880n;
        private BitmapProcessor f6881o;
        private BitmapProcessor f6882p;
        private BitmapDisplayer f6883q;
        private Handler f6884r;
        private boolean f6885s;

        public Builder() {
            this.f6867a = 0;
            this.f6868b = 0;
            this.f6869c = 0;
            this.f6870d = null;
            this.f6871e = null;
            this.f6872f = null;
            this.f6873g = false;
            this.f6874h = false;
            this.f6875i = false;
            this.f6876j = ImageScaleType.IN_SAMPLE_POWER_OF_2;
            this.f6877k = new Options();
            this.f6878l = 0;
            this.f6879m = false;
            this.f6880n = null;
            this.f6881o = null;
            this.f6882p = null;
            this.f6883q = DefaultConfigurationFactory.m10996c();
            this.f6884r = null;
            this.f6885s = false;
        }

        public Builder m11022a(boolean z) {
            this.f6874h = z;
            return this;
        }

        public Builder m11024b(boolean z) {
            this.f6875i = z;
            return this;
        }

        public Builder m11021a(ImageScaleType imageScaleType) {
            this.f6876j = imageScaleType;
            return this;
        }

        public Builder m11019a(Config config) {
            if (config == null) {
                throw new IllegalArgumentException("bitmapConfig can't be null");
            }
            this.f6877k.inPreferredConfig = config;
            return this;
        }

        public Builder m11025c(boolean z) {
            this.f6879m = z;
            return this;
        }

        Builder m11026d(boolean z) {
            this.f6885s = z;
            return this;
        }

        public Builder m11020a(DisplayImageOptions displayImageOptions) {
            this.f6867a = displayImageOptions.f6886a;
            this.f6868b = displayImageOptions.f6887b;
            this.f6869c = displayImageOptions.f6888c;
            this.f6870d = displayImageOptions.f6889d;
            this.f6871e = displayImageOptions.f6890e;
            this.f6872f = displayImageOptions.f6891f;
            this.f6873g = displayImageOptions.f6892g;
            this.f6874h = displayImageOptions.f6893h;
            this.f6875i = displayImageOptions.f6894i;
            this.f6876j = displayImageOptions.f6895j;
            this.f6877k = displayImageOptions.f6896k;
            this.f6878l = displayImageOptions.f6897l;
            this.f6879m = displayImageOptions.f6898m;
            this.f6880n = displayImageOptions.f6899n;
            this.f6881o = displayImageOptions.f6900o;
            this.f6882p = displayImageOptions.f6901p;
            this.f6883q = displayImageOptions.f6902q;
            this.f6884r = displayImageOptions.f6903r;
            this.f6885s = displayImageOptions.f6904s;
            return this;
        }

        public DisplayImageOptions m11023a() {
            return new DisplayImageOptions();
        }
    }

    private DisplayImageOptions(Builder builder) {
        this.f6886a = builder.f6867a;
        this.f6887b = builder.f6868b;
        this.f6888c = builder.f6869c;
        this.f6889d = builder.f6870d;
        this.f6890e = builder.f6871e;
        this.f6891f = builder.f6872f;
        this.f6892g = builder.f6873g;
        this.f6893h = builder.f6874h;
        this.f6894i = builder.f6875i;
        this.f6895j = builder.f6876j;
        this.f6896k = builder.f6877k;
        this.f6897l = builder.f6878l;
        this.f6898m = builder.f6879m;
        this.f6899n = builder.f6880n;
        this.f6900o = builder.f6881o;
        this.f6901p = builder.f6882p;
        this.f6902q = builder.f6883q;
        this.f6903r = builder.f6884r;
        this.f6904s = builder.f6885s;
    }

    public boolean m11048a() {
        return (this.f6889d == null && this.f6886a == 0) ? false : true;
    }

    public boolean m11050b() {
        return (this.f6890e == null && this.f6887b == 0) ? false : true;
    }

    public boolean m11052c() {
        return (this.f6891f == null && this.f6888c == 0) ? false : true;
    }

    public boolean m11053d() {
        return this.f6900o != null;
    }

    public boolean m11054e() {
        return this.f6901p != null;
    }

    public boolean m11055f() {
        return this.f6897l > 0;
    }

    public Drawable m11047a(Resources resources) {
        return this.f6886a != 0 ? resources.getDrawable(this.f6886a) : this.f6889d;
    }

    public Drawable m11049b(Resources resources) {
        return this.f6887b != 0 ? resources.getDrawable(this.f6887b) : this.f6890e;
    }

    public Drawable m11051c(Resources resources) {
        return this.f6888c != 0 ? resources.getDrawable(this.f6888c) : this.f6891f;
    }

    public boolean m11056g() {
        return this.f6892g;
    }

    public boolean m11057h() {
        return this.f6893h;
    }

    public boolean m11058i() {
        return this.f6894i;
    }

    public ImageScaleType m11059j() {
        return this.f6895j;
    }

    public Options m11060k() {
        return this.f6896k;
    }

    public int m11061l() {
        return this.f6897l;
    }

    public boolean m11062m() {
        return this.f6898m;
    }

    public Object m11063n() {
        return this.f6899n;
    }

    public BitmapProcessor m11064o() {
        return this.f6900o;
    }

    public BitmapProcessor m11065p() {
        return this.f6901p;
    }

    public BitmapDisplayer m11066q() {
        return this.f6902q;
    }

    public Handler m11067r() {
        return this.f6903r;
    }

    boolean m11068s() {
        return this.f6904s;
    }

    public static DisplayImageOptions m11046t() {
        return new Builder().m11023a();
    }
}
