package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDeserializer extends StdDeserializer<AtomicReference<?>> implements ContextualDeserializer {
    protected final JavaType _referencedType;
    protected final JsonDeserializer<?> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public AtomicReferenceDeserializer(JavaType javaType, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(AtomicReference.class);
        this._referencedType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
    }

    public AtomicReferenceDeserializer withResolved(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        return new AtomicReferenceDeserializer(this._referencedType, typeDeserializer, jsonDeserializer);
    }

    public AtomicReference<?> getNullValue() {
        return new AtomicReference();
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(this._referencedType, beanProperty);
        }
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return (jsonDeserializer == this._valueDeserializer && typeDeserializer == this._valueTypeDeserializer) ? this : withResolved(typeDeserializer, jsonDeserializer);
    }

    public AtomicReference<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._valueTypeDeserializer != null) {
            return new AtomicReference(this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer));
        }
        return new AtomicReference(this._valueDeserializer.deserialize(jsonParser, deserializationContext));
    }

    public Object[] deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return (Object[]) typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }
}
