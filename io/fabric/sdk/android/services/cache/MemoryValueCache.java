package io.fabric.sdk.android.services.cache;

import android.content.Context;

public class MemoryValueCache<T> extends AbstractValueCache<T> {
    private T f8197a;

    public MemoryValueCache() {
        this(null);
    }

    public MemoryValueCache(ValueCache<T> valueCache) {
        super(valueCache);
    }

    protected T m12967a(Context context) {
        return this.f8197a;
    }

    protected void m12968a(Context context, T t) {
        this.f8197a = t;
    }
}
