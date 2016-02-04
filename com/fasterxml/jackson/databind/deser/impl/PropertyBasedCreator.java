package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.util.Collection;
import java.util.HashMap;

public final class PropertyBasedCreator {
    protected final Object[] _defaultValues;
    protected final HashMap<String, SettableBeanProperty> _properties;
    protected final SettableBeanProperty[] _propertiesWithInjectables;
    protected final int _propertyCount;
    protected final ValueInstantiator _valueInstantiator;

    protected PropertyBasedCreator(ValueInstantiator valueInstantiator, SettableBeanProperty[] settableBeanPropertyArr, Object[] objArr) {
        this._valueInstantiator = valueInstantiator;
        this._properties = new HashMap();
        int length = settableBeanPropertyArr.length;
        this._propertyCount = length;
        SettableBeanProperty[] settableBeanPropertyArr2 = null;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
            this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
            if (settableBeanProperty.getInjectableValueId() != null) {
                if (settableBeanPropertyArr2 == null) {
                    settableBeanPropertyArr2 = new SettableBeanProperty[length];
                }
                settableBeanPropertyArr2[i] = settableBeanProperty;
            }
        }
        this._defaultValues = objArr;
        this._propertiesWithInjectables = settableBeanPropertyArr2;
    }

    public static PropertyBasedCreator construct(DeserializationContext deserializationContext, ValueInstantiator valueInstantiator, SettableBeanProperty[] settableBeanPropertyArr) {
        int length = settableBeanPropertyArr.length;
        SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[length];
        Object[] objArr = null;
        for (int i = 0; i < length; i++) {
            Object defaultValue;
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
            if (!settableBeanProperty.hasValueDeserializer()) {
                settableBeanProperty = settableBeanProperty.withValueDeserializer(deserializationContext.findContextualValueDeserializer(settableBeanProperty.getType(), settableBeanProperty));
            }
            settableBeanPropertyArr2[i] = settableBeanProperty;
            JsonDeserializer valueDeserializer = settableBeanProperty.getValueDeserializer();
            Object nullValue = valueDeserializer == null ? null : valueDeserializer.getNullValue();
            if (nullValue == null && settableBeanProperty.getType().isPrimitive()) {
                defaultValue = ClassUtil.defaultValue(settableBeanProperty.getType().getRawClass());
            } else {
                defaultValue = nullValue;
            }
            if (defaultValue != null) {
                if (objArr == null) {
                    objArr = new Object[length];
                }
                objArr[i] = defaultValue;
            }
        }
        return new PropertyBasedCreator(valueInstantiator, settableBeanPropertyArr2, objArr);
    }

    public Collection<SettableBeanProperty> properties() {
        return this._properties.values();
    }

    public SettableBeanProperty findCreatorProperty(String str) {
        return (SettableBeanProperty) this._properties.get(str);
    }

    public PropertyValueBuffer startBuilding(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectIdReader objectIdReader) {
        PropertyValueBuffer propertyValueBuffer = new PropertyValueBuffer(jsonParser, deserializationContext, this._propertyCount, objectIdReader);
        if (this._propertiesWithInjectables != null) {
            propertyValueBuffer.inject(this._propertiesWithInjectables);
        }
        return propertyValueBuffer;
    }

    public Object build(DeserializationContext deserializationContext, PropertyValueBuffer propertyValueBuffer) {
        Object handleIdValue = propertyValueBuffer.handleIdValue(deserializationContext, this._valueInstantiator.createFromObjectWith(deserializationContext, propertyValueBuffer.getParameters(this._defaultValues)));
        for (PropertyValue buffered = propertyValueBuffer.buffered(); buffered != null; buffered = buffered.next) {
            buffered.assign(handleIdValue);
        }
        return handleIdValue;
    }
}
