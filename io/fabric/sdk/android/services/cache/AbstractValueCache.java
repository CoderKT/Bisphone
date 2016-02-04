package io.fabric.sdk.android.services.cache;

import android.content.Context;

public abstract class AbstractValueCache<T> implements ValueCache<T> {
    private final ValueCache<T> f8196a;

    protected abstract T m12964a(Context context);

    protected abstract void m12966a(Context context, T t);

    public AbstractValueCache(ValueCache<T> valueCache) {
        this.f8196a = valueCache;
    }

    public final synchronized T m12965a(Context context, ValueLoader<T> valueLoader) {
        T a;
        a = m12964a(context);
        if (a == null) {
            a = this.f8196a != null ? this.f8196a.m12962a(context, valueLoader) : valueLoader.m8215c(context);
            m12963b(context, a);
        }
        return a;
    }

    private void m12963b(Context context, T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        m12966a(context, (Object) t);
    }
}
