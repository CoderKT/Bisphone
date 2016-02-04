package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public class MapSerializer extends ContainerSerializer<Map<?, ?>> implements ContextualSerializer {
    protected static final JavaType UNSPECIFIED_TYPE;
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final Object _filterId;
    protected final HashSet<String> _ignoredEntries;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected final boolean _sortKeys;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    static {
        UNSPECIFIED_TYPE = TypeFactory.unknownType();
    }

    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2) {
        super(Map.class, false);
        this._ignoredEntries = hashSet;
        this._keyType = javaType;
        this._valueType = javaType2;
        this._valueTypeIsStatic = z;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = PropertySerializerMap.emptyMap();
        this._property = null;
        this._filterId = null;
        this._sortKeys = false;
    }

    protected MapSerializer(MapSerializer mapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet) {
        super(Map.class, false);
        this._ignoredEntries = hashSet;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = mapSerializer._valueTypeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = beanProperty;
        this._filterId = mapSerializer._filterId;
        this._sortKeys = mapSerializer._sortKeys;
    }

    protected MapSerializer(MapSerializer mapSerializer, TypeSerializer typeSerializer) {
        super(Map.class, false);
        this._ignoredEntries = mapSerializer._ignoredEntries;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = mapSerializer._keySerializer;
        this._valueSerializer = mapSerializer._valueSerializer;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = mapSerializer._property;
        this._filterId = mapSerializer._filterId;
        this._sortKeys = mapSerializer._sortKeys;
    }

    protected MapSerializer(MapSerializer mapSerializer, Object obj, boolean z) {
        super(Map.class, false);
        this._ignoredEntries = mapSerializer._ignoredEntries;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = mapSerializer._valueTypeSerializer;
        this._keySerializer = mapSerializer._keySerializer;
        this._valueSerializer = mapSerializer._valueSerializer;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = mapSerializer._property;
        this._filterId = obj;
        this._sortKeys = z;
    }

    public MapSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new MapSerializer(this, typeSerializer);
    }

    public MapSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet, boolean z) {
        MapSerializer mapSerializer = new MapSerializer(this, beanProperty, jsonSerializer, jsonSerializer2, hashSet);
        if (z != mapSerializer._sortKeys) {
            return new MapSerializer(mapSerializer, this._filterId, z);
        }
        return mapSerializer;
    }

    public MapSerializer withFilterId(Object obj) {
        return this._filterId == obj ? this : new MapSerializer(this, obj, this._sortKeys);
    }

    public static MapSerializer construct(String[] strArr, JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2, Object obj) {
        JavaType javaType2;
        JavaType javaType3;
        boolean z2;
        boolean z3 = false;
        HashSet toSet = toSet(strArr);
        if (javaType == null) {
            javaType2 = UNSPECIFIED_TYPE;
            javaType3 = javaType2;
        } else {
            javaType3 = javaType.getKeyType();
            javaType2 = javaType.getContentType();
        }
        if (!z) {
            if (javaType2 != null && javaType2.isFinal()) {
                z3 = true;
            }
            z2 = z3;
        } else if (javaType2.getRawClass() == Object.class) {
            z2 = false;
        } else {
            z2 = z;
        }
        MapSerializer mapSerializer = new MapSerializer(toSet, javaType3, javaType2, z2, typeSerializer, jsonSerializer, jsonSerializer2);
        if (obj != null) {
            return mapSerializer.withFilterId(obj);
        }
        return mapSerializer;
    }

    private static HashSet<String> toSet(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        HashSet<String> hashSet = new HashSet(strArr.length);
        for (Object add : strArr) {
            hashSet.add(add);
        }
        return hashSet;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer jsonSerializer;
        JsonSerializer findKeySerializer;
        boolean z;
        HashSet hashSet;
        JsonSerializer jsonSerializer2 = null;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        Annotated member = beanProperty == null ? null : beanProperty.getMember();
        if (member == null || annotationIntrospector == null) {
            jsonSerializer = null;
        } else {
            Object findKeySerializer2 = annotationIntrospector.findKeySerializer(member);
            if (findKeySerializer2 != null) {
                jsonSerializer = serializerProvider.serializerInstance(member, findKeySerializer2);
            } else {
                jsonSerializer = null;
            }
            Object findContentSerializer = annotationIntrospector.findContentSerializer(member);
            JsonSerializer jsonSerializer3;
            if (findContentSerializer != null) {
                jsonSerializer3 = jsonSerializer;
                jsonSerializer = serializerProvider.serializerInstance(member, findContentSerializer);
                jsonSerializer2 = jsonSerializer3;
            } else {
                jsonSerializer3 = jsonSerializer;
                jsonSerializer = null;
                jsonSerializer2 = jsonSerializer3;
            }
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._valueSerializer;
        }
        JsonSerializer findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider, beanProperty, jsonSerializer);
        if (findConvertingContentSerializer != null) {
            findConvertingContentSerializer = serializerProvider.handleSecondaryContextualization(findConvertingContentSerializer, beanProperty);
        } else if ((this._valueTypeIsStatic && this._valueType.getRawClass() != Object.class) || hasContentTypeAnnotation(serializerProvider, beanProperty)) {
            findConvertingContentSerializer = serializerProvider.findValueSerializer(this._valueType, beanProperty);
        }
        if (jsonSerializer2 == null) {
            jsonSerializer = this._keySerializer;
        } else {
            jsonSerializer = jsonSerializer2;
        }
        if (jsonSerializer == null) {
            findKeySerializer = serializerProvider.findKeySerializer(this._keyType, beanProperty);
        } else {
            findKeySerializer = serializerProvider.handleSecondaryContextualization(jsonSerializer, beanProperty);
        }
        Collection collection = this._ignoredEntries;
        if (annotationIntrospector == null || member == null) {
            z = false;
            Collection collection2 = collection;
        } else {
            HashSet hashSet2;
            String[] findPropertiesToIgnore = annotationIntrospector.findPropertiesToIgnore(member);
            if (findPropertiesToIgnore != null) {
                hashSet2 = collection == null ? new HashSet() : new HashSet(collection);
                for (Object add : findPropertiesToIgnore) {
                    hashSet2.add(add);
                }
            } else {
                hashSet2 = collection;
            }
            Boolean findSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(member);
            boolean z2 = findSerializationSortAlphabetically != null && findSerializationSortAlphabetically.booleanValue();
            z = z2;
            hashSet = hashSet2;
        }
        JsonSerializer<?> withResolved = withResolved(beanProperty, findKeySerializer, findConvertingContentSerializer, hashSet, z);
        if (beanProperty == null) {
            return withResolved;
        }
        Object findFilterId = annotationIntrospector.findFilterId(beanProperty.getMember());
        if (findFilterId != null) {
            return withResolved.withFilterId(findFilterId);
        }
        return withResolved;
    }

    public boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public boolean hasSingleElement(Map<?, ?> map) {
        return map.size() == 1;
    }

    public void serialize(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        if (!map.isEmpty()) {
            if (this._filterId != null) {
                serializeFilteredFields(map, jsonGenerator, serializerProvider, findPropertyFilter(serializerProvider, this._filterId, map));
                jsonGenerator.writeEndObject();
                return;
            }
            if (this._sortKeys || serializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map = _orderEntries(map);
            }
            if (this._valueSerializer != null) {
                serializeFieldsUsing(map, jsonGenerator, serializerProvider, this._valueSerializer);
            } else {
                serializeFields(map, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(map, jsonGenerator);
        if (!map.isEmpty()) {
            if (this._sortKeys || serializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map = _orderEntries(map);
            }
            if (this._valueSerializer != null) {
                serializeFieldsUsing(map, jsonGenerator, serializerProvider, this._valueSerializer);
            } else {
                serializeFields(map, jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForObject(map, jsonGenerator);
    }

    public void serializeFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(map, jsonGenerator, serializerProvider);
            return;
        }
        Object obj;
        JsonSerializer jsonSerializer = this._keySerializer;
        HashSet hashSet = this._ignoredEntries;
        if (serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
            obj = null;
        } else {
            obj = 1;
        }
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
            } else if ((obj == null || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                propertySerializerMap = propertySerializerMap2;
            } else {
                JsonSerializer jsonSerializer2;
                Class cls = value.getClass();
                JsonSerializer serializerFor = propertySerializerMap2.serializerFor(cls);
                JsonSerializer jsonSerializer3;
                if (serializerFor == null) {
                    if (this._valueType.hasGenericTypes()) {
                        serializerFor = _findAndAddDynamic(propertySerializerMap2, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
                    } else {
                        serializerFor = _findAndAddDynamic(propertySerializerMap2, cls, serializerProvider);
                    }
                    jsonSerializer3 = serializerFor;
                    propertySerializerMap = this._dynamicValueSerializers;
                    jsonSerializer2 = jsonSerializer3;
                } else {
                    jsonSerializer3 = serializerFor;
                    propertySerializerMap = propertySerializerMap2;
                    jsonSerializer2 = jsonSerializer3;
                }
                try {
                    jsonSerializer2.serialize(value, jsonGenerator, serializerProvider);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) map, "" + key);
                }
            }
            propertySerializerMap2 = propertySerializerMap;
        }
    }

    protected void serializeFieldsUsing(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        JsonSerializer jsonSerializer2 = this._keySerializer;
        HashSet hashSet = this._ignoredEntries;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        Object obj = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES) ? 1 : null;
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
            } else if ((obj == null || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer2.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else if (typeSerializer == null) {
                try {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) map, "" + key);
                }
            } else {
                jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
            }
        }
    }

    public void serializeFilteredFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyFilter propertyFilter) {
        Object obj;
        HashSet hashSet = this._ignoredEntries;
        if (serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
            obj = null;
        } else {
            obj = 1;
        }
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        PropertyWriter mapProperty = new MapProperty(this._valueTypeSerializer);
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        for (Entry entry : map.entrySet()) {
            JsonSerializer findNullKeySerializer;
            JsonSerializer defaultNullValueSerializer;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key == null) {
                findNullKeySerializer = serializerProvider.findNullKeySerializer(this._keyType, this._property);
            } else if ((obj == null || value != null) && (hashSet == null || !hashSet.contains(key))) {
                findNullKeySerializer = this._keySerializer;
            }
            if (value == null) {
                propertySerializerMap = propertySerializerMap2;
                defaultNullValueSerializer = serializerProvider.getDefaultNullValueSerializer();
            } else {
                Class cls = value.getClass();
                JsonSerializer serializerFor = propertySerializerMap2.serializerFor(cls);
                JsonSerializer jsonSerializer;
                if (serializerFor == null) {
                    if (this._valueType.hasGenericTypes()) {
                        serializerFor = _findAndAddDynamic(propertySerializerMap2, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
                    } else {
                        serializerFor = _findAndAddDynamic(propertySerializerMap2, cls, serializerProvider);
                    }
                    jsonSerializer = serializerFor;
                    propertySerializerMap = this._dynamicValueSerializers;
                    defaultNullValueSerializer = jsonSerializer;
                } else {
                    jsonSerializer = serializerFor;
                    propertySerializerMap = propertySerializerMap2;
                    defaultNullValueSerializer = jsonSerializer;
                }
            }
            mapProperty.reset(key, value, findNullKeySerializer, defaultNullValueSerializer);
            try {
                propertyFilter.serializeAsField(map, jsonGenerator, serializerProvider, mapProperty);
            } catch (Throwable e) {
                wrapAndThrow(serializerProvider, e, (Object) map, "" + key);
            }
            propertySerializerMap2 = propertySerializerMap;
        }
    }

    protected void serializeTypedFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer jsonSerializer = this._keySerializer;
        HashSet hashSet = this._ignoredEntries;
        Object obj = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES) ? 1 : null;
        Object obj2 = null;
        JsonSerializer jsonSerializer2 = null;
        for (Entry entry : map.entrySet()) {
            Object obj3;
            JsonSerializer jsonSerializer3;
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
            } else if ((obj == null || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                obj3 = obj2;
                jsonSerializer3 = jsonSerializer2;
            } else {
                Class cls = value.getClass();
                if (cls == obj2) {
                    obj3 = obj2;
                    jsonSerializer3 = jsonSerializer2;
                } else {
                    JsonSerializer findValueSerializer;
                    if (this._valueType.hasGenericTypes()) {
                        findValueSerializer = serializerProvider.findValueSerializer(serializerProvider.constructSpecializedType(this._valueType, cls), this._property);
                    } else {
                        findValueSerializer = serializerProvider.findValueSerializer(cls, this._property);
                    }
                    jsonSerializer2 = findValueSerializer;
                    jsonSerializer3 = findValueSerializer;
                    Class cls2 = cls;
                }
                try {
                    jsonSerializer2.serializeWithType(value, jsonGenerator, serializerProvider, this._valueTypeSerializer);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) map, "" + key);
                }
            }
            jsonSerializer2 = jsonSerializer3;
            obj2 = obj3;
        }
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
        SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer((Class) cls, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSecondarySerializer.map) {
            this._dynamicValueSerializers = findAndAddSecondarySerializer.map;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) {
        SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSecondarySerializer.map) {
            this._dynamicValueSerializers = findAndAddSecondarySerializer.map;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    protected Map<?, ?> _orderEntries(Map<?, ?> map) {
        return map instanceof SortedMap ? map : new TreeMap(map);
    }
}
