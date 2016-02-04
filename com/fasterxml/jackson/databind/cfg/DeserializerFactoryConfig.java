package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import java.io.Serializable;

public class DeserializerFactoryConfig implements Serializable {
    protected static final KeyDeserializers[] DEFAULT_KEY_DESERIALIZERS;
    protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS;
    protected static final Deserializers[] NO_DESERIALIZERS;
    protected static final BeanDeserializerModifier[] NO_MODIFIERS;
    protected static final ValueInstantiators[] NO_VALUE_INSTANTIATORS;
    protected final AbstractTypeResolver[] _abstractTypeResolvers;
    protected final Deserializers[] _additionalDeserializers;
    protected final KeyDeserializers[] _additionalKeyDeserializers;
    protected final BeanDeserializerModifier[] _modifiers;
    protected final ValueInstantiators[] _valueInstantiators;

    static {
        NO_DESERIALIZERS = new Deserializers[0];
        NO_MODIFIERS = new BeanDeserializerModifier[0];
        NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];
        NO_VALUE_INSTANTIATORS = new ValueInstantiators[0];
        DEFAULT_KEY_DESERIALIZERS = new KeyDeserializers[]{new StdKeyDeserializers()};
    }

    public DeserializerFactoryConfig() {
        this(null, null, null, null, null);
    }

    protected DeserializerFactoryConfig(Deserializers[] deserializersArr, KeyDeserializers[] keyDeserializersArr, BeanDeserializerModifier[] beanDeserializerModifierArr, AbstractTypeResolver[] abstractTypeResolverArr, ValueInstantiators[] valueInstantiatorsArr) {
        if (deserializersArr == null) {
            deserializersArr = NO_DESERIALIZERS;
        }
        this._additionalDeserializers = deserializersArr;
        if (keyDeserializersArr == null) {
            keyDeserializersArr = DEFAULT_KEY_DESERIALIZERS;
        }
        this._additionalKeyDeserializers = keyDeserializersArr;
        if (beanDeserializerModifierArr == null) {
            beanDeserializerModifierArr = NO_MODIFIERS;
        }
        this._modifiers = beanDeserializerModifierArr;
        if (abstractTypeResolverArr == null) {
            abstractTypeResolverArr = NO_ABSTRACT_TYPE_RESOLVERS;
        }
        this._abstractTypeResolvers = abstractTypeResolverArr;
        if (valueInstantiatorsArr == null) {
            valueInstantiatorsArr = NO_VALUE_INSTANTIATORS;
        }
        this._valueInstantiators = valueInstantiatorsArr;
    }

    public boolean hasKeyDeserializers() {
        return this._additionalKeyDeserializers.length > 0;
    }

    public boolean hasDeserializerModifiers() {
        return this._modifiers.length > 0;
    }

    public boolean hasAbstractTypeResolvers() {
        return this._abstractTypeResolvers.length > 0;
    }

    public boolean hasValueInstantiators() {
        return this._valueInstantiators.length > 0;
    }

    public Iterable<Deserializers> deserializers() {
        return new ArrayIterator(this._additionalDeserializers);
    }

    public Iterable<KeyDeserializers> keyDeserializers() {
        return new ArrayIterator(this._additionalKeyDeserializers);
    }

    public Iterable<BeanDeserializerModifier> deserializerModifiers() {
        return new ArrayIterator(this._modifiers);
    }

    public Iterable<AbstractTypeResolver> abstractTypeResolvers() {
        return new ArrayIterator(this._abstractTypeResolvers);
    }

    public Iterable<ValueInstantiators> valueInstantiators() {
        return new ArrayIterator(this._valueInstantiators);
    }
}
