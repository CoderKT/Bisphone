package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements ContextualDeserializer, ResolvableDeserializer {
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final boolean _hasDefaultCreator;
    protected HashSet<String> _ignorableProperties;
    protected final KeyDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected boolean _standardStringKey;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    final class MapReferring extends Referring {
        private final MapReferringAccumulator _parent;
        public final Object key;
        public final Map<Object, Object> next;

        private MapReferring(MapReferringAccumulator mapReferringAccumulator, UnresolvedForwardReference unresolvedForwardReference, Class<?> cls, Object obj) {
            super(unresolvedForwardReference, cls);
            this.next = new LinkedHashMap();
            this._parent = mapReferringAccumulator;
            this.key = obj;
        }

        public void handleResolvedForwardReference(Object obj, Object obj2) {
            this._parent.resolveForwardReference(obj, obj2);
        }
    }

    final class MapReferringAccumulator {
        private List<MapReferring> _accumulator;
        private Map<Object, Object> _result;
        private final Class<?> _valueType;

        public MapReferringAccumulator(Class<?> cls, Map<Object, Object> map) {
            this._accumulator = new ArrayList();
            this._valueType = cls;
            this._result = map;
        }

        public void put(Object obj, Object obj2) {
            if (this._accumulator.isEmpty()) {
                this._result.put(obj, obj2);
            } else {
                ((MapReferring) this._accumulator.get(this._accumulator.size() - 1)).next.put(obj, obj2);
            }
        }

        public Referring handleUnresolvedReference(UnresolvedForwardReference unresolvedForwardReference, Object obj) {
            Referring mapReferring = new MapReferring(unresolvedForwardReference, this._valueType, obj, null);
            this._accumulator.add(mapReferring);
            return mapReferring;
        }

        public void resolveForwardReference(Object obj, Object obj2) {
            Iterator it = this._accumulator.iterator();
            Map map = this._result;
            while (it.hasNext()) {
                MapReferring mapReferring = (MapReferring) it.next();
                if (mapReferring.hasId(obj)) {
                    it.remove();
                    map.put(mapReferring.key, obj2);
                    map.putAll(mapReferring.next);
                    return;
                }
                map = mapReferring.next;
            }
            throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
        }
    }

    public MapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType);
        this._mapType = javaType;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = valueInstantiator;
        this._hasDefaultCreator = valueInstantiator.canCreateUsingDefault();
        this._delegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._standardStringKey = _isStdKeyDeser(javaType, keyDeserializer);
    }

    protected MapDeserializer(MapDeserializer mapDeserializer, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, HashSet<String> hashSet) {
        super(mapDeserializer._mapType);
        this._mapType = mapDeserializer._mapType;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = mapDeserializer._valueInstantiator;
        this._propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        this._delegateDeserializer = mapDeserializer._delegateDeserializer;
        this._hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        this._ignorableProperties = hashSet;
        this._standardStringKey = _isStdKeyDeser(this._mapType, keyDeserializer);
    }

    protected MapDeserializer withResolved(KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, HashSet<String> hashSet) {
        return (this._keyDeserializer == keyDeserializer && this._valueDeserializer == jsonDeserializer && this._valueTypeDeserializer == typeDeserializer && this._ignorableProperties == hashSet) ? this : new MapDeserializer(this, keyDeserializer, (JsonDeserializer) jsonDeserializer, typeDeserializer, (HashSet) hashSet);
    }

    protected final boolean _isStdKeyDeser(JavaType javaType, KeyDeserializer keyDeserializer) {
        if (keyDeserializer == null) {
            return true;
        }
        JavaType keyType = javaType.getKeyType();
        if (keyType == null) {
            return true;
        }
        Class rawClass = keyType.getRawClass();
        if ((rawClass == String.class || rawClass == Object.class) && isDefaultKeyDeserializer(keyDeserializer)) {
            return true;
        }
        return false;
    }

    public void setIgnorableProperties(String[] strArr) {
        HashSet arrayToSet = (strArr == null || strArr.length == 0) ? null : ArrayBuilders.arrayToSet(strArr);
        this._ignorableProperties = arrayToSet;
    }

    public void resolve(DeserializationContext deserializationContext) {
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext.getConfig());
            if (delegateType == null) {
                throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._mapType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            }
            this._delegateDeserializer = findDeserializer(deserializationContext, delegateType, null);
        }
        if (this._valueInstantiator.canCreateFromObjectWith()) {
            this._propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, this._valueInstantiator, this._valueInstantiator.getFromObjectArguments(deserializationContext.getConfig()));
        }
        this._standardStringKey = _isStdKeyDeser(this._mapType, this._keyDeserializer);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        HashSet hashSet;
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        if (keyDeserializer == null) {
            keyDeserializer = deserializationContext.findKeyDeserializer(this._mapType.getKeyType(), beanProperty);
        } else if (keyDeserializer instanceof ContextualKeyDeserializer) {
            keyDeserializer = ((ContextualKeyDeserializer) keyDeserializer).createContextual(deserializationContext, beanProperty);
        }
        JsonDeserializer findConvertingContentDeserializer = findConvertingContentDeserializer(deserializationContext, beanProperty, this._valueDeserializer);
        if (findConvertingContentDeserializer == null) {
            findConvertingContentDeserializer = deserializationContext.findContextualValueDeserializer(this._mapType.getContentType(), beanProperty);
        } else {
            findConvertingContentDeserializer = deserializationContext.handleSecondaryContextualization(findConvertingContentDeserializer, beanProperty);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        Collection collection = this._ignorableProperties;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!(annotationIntrospector == null || beanProperty == null)) {
            String[] findPropertiesToIgnore = annotationIntrospector.findPropertiesToIgnore(beanProperty.getMember());
            if (findPropertiesToIgnore != null) {
                hashSet = collection == null ? new HashSet() : new HashSet(collection);
                for (Object add : findPropertiesToIgnore) {
                    hashSet.add(add);
                }
                return withResolved(keyDeserializer, typeDeserializer, findConvertingContentDeserializer, hashSet);
            }
        }
        hashSet = collection;
        return withResolved(keyDeserializer, typeDeserializer, findConvertingContentDeserializer, hashSet);
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public boolean isCachable() {
        return true;
    }

    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingCreator(jsonParser, deserializationContext);
        }
        if (this._delegateDeserializer != null) {
            return (Map) this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._hasDefaultCreator) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.FIELD_NAME || currentToken == JsonToken.END_OBJECT) {
                Map<Object, Object> map = (Map) this._valueInstantiator.createUsingDefault(deserializationContext);
                if (this._standardStringKey) {
                    _readAndBindStringMap(jsonParser, deserializationContext, map);
                    return map;
                }
                _readAndBind(jsonParser, deserializationContext, map);
                return map;
            } else if (currentToken == JsonToken.VALUE_STRING) {
                return (Map) this._valueInstantiator.createFromString(deserializationContext, jsonParser.getText());
            } else {
                throw deserializationContext.mappingException(getMapClass());
            }
        }
        throw deserializationContext.instantiationException(getMapClass(), "No default constructor found");
    }

    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.FIELD_NAME) {
            if (this._standardStringKey) {
                _readAndBindStringMap(jsonParser, deserializationContext, map);
            } else {
                _readAndBind(jsonParser, deserializationContext, map);
            }
            return map;
        }
        throw deserializationContext.mappingException(getMapClass());
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public final Class<?> getMapClass() {
        return this._mapType.getRawClass();
    }

    protected final void _readAndBind(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) {
        JsonToken jsonToken;
        MapReferringAccumulator mapReferringAccumulator;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        Object obj = jsonDeserializer.getObjectIdReader() != null ? 1 : null;
        if (obj != null) {
            MapReferringAccumulator mapReferringAccumulator2 = new MapReferringAccumulator(this._mapType.getContentType().getRawClass(), map);
            jsonToken = currentToken;
            mapReferringAccumulator = mapReferringAccumulator2;
        } else {
            jsonToken = currentToken;
            mapReferringAccumulator = null;
        }
        while (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            Object deserializeKey = keyDeserializer.deserializeKey(currentName, deserializationContext);
            jsonToken = jsonParser.nextToken();
            if (this._ignorableProperties == null || !this._ignorableProperties.contains(currentName)) {
                try {
                    Object nullValue;
                    if (jsonToken == JsonToken.VALUE_NULL) {
                        nullValue = jsonDeserializer.getNullValue();
                    } else if (typeDeserializer == null) {
                        nullValue = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    } else {
                        nullValue = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    if (obj != null) {
                        mapReferringAccumulator.put(deserializeKey, nullValue);
                    } else {
                        map.put(deserializeKey, nullValue);
                    }
                } catch (UnresolvedForwardReference e) {
                    handleUnresolvedReference(jsonParser, mapReferringAccumulator, deserializeKey, e);
                } catch (Throwable e2) {
                    wrapAndThrow(e2, map, currentName);
                }
            } else {
                jsonParser.skipChildren();
            }
            jsonToken = jsonParser.nextToken();
        }
    }

    protected final void _readAndBindStringMap(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) {
        JsonToken jsonToken;
        MapReferringAccumulator mapReferringAccumulator;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        Object obj = jsonDeserializer.getObjectIdReader() != null ? 1 : null;
        if (obj != null) {
            MapReferringAccumulator mapReferringAccumulator2 = new MapReferringAccumulator(this._mapType.getContentType().getRawClass(), map);
            jsonToken = currentToken;
            mapReferringAccumulator = mapReferringAccumulator2;
        } else {
            jsonToken = currentToken;
            mapReferringAccumulator = null;
        }
        while (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonToken = jsonParser.nextToken();
            if (this._ignorableProperties == null || !this._ignorableProperties.contains(currentName)) {
                try {
                    Object nullValue;
                    if (jsonToken == JsonToken.VALUE_NULL) {
                        nullValue = jsonDeserializer.getNullValue();
                    } else if (typeDeserializer == null) {
                        nullValue = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    } else {
                        nullValue = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    if (obj != null) {
                        mapReferringAccumulator.put(currentName, nullValue);
                    } else {
                        map.put(currentName, nullValue);
                    }
                } catch (UnresolvedForwardReference e) {
                    handleUnresolvedReference(jsonParser, mapReferringAccumulator, currentName, e);
                } catch (Throwable e2) {
                    wrapAndThrow(e2, map, currentName);
                }
            } else {
                jsonParser.skipChildren();
            }
            jsonToken = jsonParser.nextToken();
        }
    }

    public Map<Object, Object> _deserializeUsingCreator(JsonParser jsonParser, DeserializationContext deserializationContext) {
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, null);
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            currentToken = jsonParser.nextToken();
            if (this._ignorableProperties == null || !this._ignorableProperties.contains(currentName)) {
                SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
                if (findCreatorProperty != null) {
                    if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                        jsonParser.nextToken();
                        try {
                            Map<Object, Object> map = (Map) propertyBasedCreator.build(deserializationContext, startBuilding);
                            _readAndBind(jsonParser, deserializationContext, map);
                            return map;
                        } catch (Throwable e) {
                            wrapAndThrow(e, this._mapType.getRawClass(), currentName);
                            return null;
                        }
                    }
                } else {
                    Object nullValue;
                    Object deserializeKey = this._keyDeserializer.deserializeKey(jsonParser.getCurrentName(), deserializationContext);
                    if (currentToken == JsonToken.VALUE_NULL) {
                        nullValue = jsonDeserializer.getNullValue();
                    } else if (typeDeserializer == null) {
                        try {
                            nullValue = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                        } catch (Throwable e2) {
                            wrapAndThrow(e2, this._mapType.getRawClass(), currentName);
                            return null;
                        }
                    } else {
                        nullValue = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    startBuilding.bufferMapProperty(deserializeKey, nullValue);
                }
            } else {
                jsonParser.skipChildren();
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            return (Map) propertyBasedCreator.build(deserializationContext, startBuilding);
        } catch (Throwable e22) {
            wrapAndThrow(e22, this._mapType.getRawClass(), null);
            return null;
        }
    }

    protected void wrapAndThrow(Throwable th, Object obj, String str) {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        } else if (!(th2 instanceof IOException) || (th2 instanceof JsonMappingException)) {
            throw JsonMappingException.wrapWithPath(th2, obj, str);
        } else {
            throw ((IOException) th2);
        }
    }

    private void handleUnresolvedReference(JsonParser jsonParser, MapReferringAccumulator mapReferringAccumulator, Object obj, UnresolvedForwardReference unresolvedForwardReference) {
        if (mapReferringAccumulator == null) {
            throw JsonMappingException.from(jsonParser, "Unresolved forward reference but no identity info.", unresolvedForwardReference);
        }
        unresolvedForwardReference.getRoid().appendReferring(mapReferringAccumulator.handleUnresolvedReference(unresolvedForwardReference, obj));
    }
}
