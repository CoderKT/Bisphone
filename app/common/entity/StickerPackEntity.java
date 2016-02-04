package app.common.entity;

import app.storage.Storage;
import java.io.File;

public class StickerPackEntity {
    private int f2282a;
    private String f2283b;
    private String f2284c;
    private boolean f2285d;
    private boolean f2286e;
    private int f2287f;
    private boolean f2288g;

    public StickerPackEntity(int i, String str, String str2, boolean z, boolean z2, int i2, boolean z3) {
        m4481a(i);
        m4482a(str);
        m4486b(str2);
        m4483a(z);
        m4487b(z2);
        m4485b(i2);
        m4489c(z3);
    }

    public int m4480a() {
        return this.f2282a;
    }

    public String m4484b() {
        return this.f2283b;
    }

    public String m4488c() {
        return this.f2284c;
    }

    public boolean m4490d() {
        return this.f2285d;
    }

    public boolean m4491e() {
        return this.f2286e;
    }

    public int m4492f() {
        return this.f2287f;
    }

    public void m4481a(int i) {
        this.f2282a = i;
    }

    public void m4482a(String str) {
        this.f2283b = str;
    }

    public void m4486b(String str) {
        this.f2284c = str;
    }

    public void m4483a(boolean z) {
        this.f2285d = z;
    }

    public void m4487b(boolean z) {
        this.f2286e = z;
    }

    public void m4485b(int i) {
        this.f2287f = i;
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
        if (this.f2282a != ((StickerPackEntity) obj).f2282a) {
            return false;
        }
        return true;
    }

    public static String m4478c(int i) {
        return Storage.m6955i() + File.separator + i + File.separator + "icon.png";
    }

    public static String m4479d(int i) {
        return Storage.m6955i() + File.separator + i + File.separator + "preview.jpeg";
    }

    public boolean m4493g() {
        return this.f2288g;
    }

    public void m4489c(boolean z) {
        this.f2288g = z;
    }
}
