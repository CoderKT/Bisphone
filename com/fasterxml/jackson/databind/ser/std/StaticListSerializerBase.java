package com.fasterxml.jackson.databind.ser.std;

import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>> extends StdSerializer<T> {
    protected StaticListSerializerBase(Class<?> cls) {
        super(cls, false);
    }

    public boolean isEmpty(T t) {
        return t == null || t.size() == 0;
    }
}
