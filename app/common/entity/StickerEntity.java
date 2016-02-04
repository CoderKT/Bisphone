package app.common.entity;

import app.storage.Storage;
import java.io.File;

public class StickerEntity {
    private int f2280a;
    private int f2281b;

    public StickerEntity(int i, int i2) {
        m4474a(i);
        m4476b(i2);
    }

    public int m4473a() {
        return this.f2280a;
    }

    public int m4475b() {
        return this.f2281b;
    }

    public void m4474a(int i) {
        this.f2280a = i;
    }

    public void m4476b(int i) {
        this.f2281b = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.f2280a != ((StickerEntity) obj).f2280a) {
            return false;
        }
        return true;
    }

    public String m4477c() {
        return m4472a(this.f2280a, this.f2281b);
    }

    public static String m4472a(int i, int i2) {
        return Storage.m6955i() + File.separator + i2 + File.separator + i + ".png";
    }
}
