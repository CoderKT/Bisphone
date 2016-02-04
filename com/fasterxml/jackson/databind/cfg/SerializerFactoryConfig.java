package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import java.io.Serializable;

public final class SerializerFactoryConfig implements Serializable {
    protected static final BeanSerializerModifier[] NO_MODIFIERS;
    protected static final Serializers[] NO_SERIALIZERS;
    protected final Serializers[] _additionalKeySerializers;
    protected final Serializers[] _additionalSerializers;
    protected final BeanSerializerModifier[] _modifiers;

    static {
        NO_SERIALIZERS = new Serializers[0];
        NO_MODIFIERS = new BeanSerializerModifier[0];
    }

    public SerializerFactoryConfig() {
        this(null, null, null);
    }

    protected SerializerFactoryConfig(Serializers[] serializersArr, Serializers[] serializersArr2, BeanSerializerModifier[] beanSerializerModifierArr) {
        if (serializersArr == null) {
            serializersArr = NO_SERIALIZERS;
        }
        this._additionalSerializers = serializersArr;
        if (serializersArr2 == null) {
            serializersArr2 = NO_SERIALIZERS;
        }
        this._additionalKeySerializers = serializersArr2;
        if (beanSerializerModifierArr == null) {
            beanSerializerModifierArr = NO_MODIFIERS;
        }
        this._modifiers = beanSerializerModifierArr;
    }

    public boolean hasKeySerializers() {
        return this._additionalKeySerializers.length > 0;
    }

    public boolean hasSerializerModifiers() {
        return this._modifiers.length > 0;
    }

    public Iterable<Serializers> serializers() {
        return new ArrayIterator(this._additionalSerializers);
    }

    public Iterable<Serializers> keySerializers() {
        return new ArrayIterator(this._additionalKeySerializers);
    }

    public Iterable<BeanSerializerModifier> serializerModifiers() {
        return new ArrayIterator(this._modifiers);
    }
}
