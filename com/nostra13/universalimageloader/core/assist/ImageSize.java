package com.nostra13.universalimageloader.core.assist;

import org.jivesoftware.smackx.xdata.packet.DataForm;

public class ImageSize {
    private final int f7025a;
    private final int f7026b;

    public ImageSize(int i, int i2) {
        this.f7025a = i;
        this.f7026b = i2;
    }

    public ImageSize(int i, int i2, int i3) {
        if (i3 % 180 == 0) {
            this.f7025a = i;
            this.f7026b = i2;
            return;
        }
        this.f7025a = i2;
        this.f7026b = i;
    }

    public int m11167a() {
        return this.f7025a;
    }

    public int m11170b() {
        return this.f7026b;
    }

    public ImageSize m11169a(int i) {
        return new ImageSize(this.f7025a / i, this.f7026b / i);
    }

    public ImageSize m11168a(float f) {
        return new ImageSize((int) (((float) this.f7025a) * f), (int) (((float) this.f7026b) * f));
    }

    public String toString() {
        return this.f7025a + DataForm.ELEMENT + this.f7026b;
    }
}
