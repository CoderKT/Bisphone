package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import java.util.Collection;
import java.util.Comparator;

public class FuzzyKeyMemoryCache implements MemoryCache {
    private final MemoryCache f6849a;
    private final Comparator<String> f6850b;

    public FuzzyKeyMemoryCache(MemoryCache memoryCache, Comparator<String> comparator) {
        this.f6849a = memoryCache;
        this.f6850b = comparator;
    }

    public boolean m10976a(String str, Bitmap bitmap) {
        synchronized (this.f6849a) {
            for (String str2 : this.f6849a.m10970a()) {
                if (this.f6850b.compare(str, str2) == 0) {
                    break;
                }
            }
            String str22 = null;
            if (str22 != null) {
                this.f6849a.m10972b(str22);
            }
        }
        return this.f6849a.m10971a(str, bitmap);
    }

    public Bitmap m10974a(String str) {
        return this.f6849a.m10969a(str);
    }

    public Bitmap m10977b(String str) {
        return this.f6849a.m10972b(str);
    }

    public void m10978b() {
        this.f6849a.m10973b();
    }

    public Collection<String> m10975a() {
        return this.f6849a.m10970a();
    }
}
