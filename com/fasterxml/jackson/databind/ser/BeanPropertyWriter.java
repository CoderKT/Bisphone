package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

public class BeanPropertyWriter extends PropertyWriter implements BeanProperty {
    public static final Object MARKER_FOR_EMPTY;
    protected final Method _accessorMethod;
    protected final JavaType _cfgSerializationType;
    protected final Annotations _contextAnnotations;
    protected final JavaType _declaredType;
    protected transient PropertySerializerMap _dynamicSerializers;
    protected final Field _field;
    protected final Class<?>[] _includeInViews;
    protected HashMap<Object, Object> _internalSettings;
    protected final AnnotatedMember _member;
    protected final PropertyMetadata _metadata;
    protected final SerializedString _name;
    protected JavaType _nonTrivialBaseType;
    protected JsonSerializer<Object> _nullSerializer;
    protected JsonSerializer<Object> _serializer;
    protected final boolean _suppressNulls;
    protected final Object _suppressableValue;
    protected TypeSerializer _typeSerializer;
    protected final PropertyName _wrapperName;

    static {
        MARKER_FOR_EMPTY = new Object();
    }

    public BeanPropertyWriter(BeanPropertyDefinition beanPropertyDefinition, AnnotatedMember annotatedMember, Annotations annotations, JavaType javaType, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, JavaType javaType2, boolean z, Object obj) {
        this._member = annotatedMember;
        this._contextAnnotations = annotations;
        this._name = new SerializedString(beanPropertyDefinition.getName());
        this._wrapperName = beanPropertyDefinition.getWrapperName();
        this._declaredType = javaType;
        this._serializer = jsonSerializer;
        this._dynamicSerializers = jsonSerializer == null ? PropertySerializerMap.emptyMap() : null;
        this._typeSerializer = typeSerializer;
        this._cfgSerializationType = javaType2;
        this._metadata = beanPropertyDefinition.getMetadata();
        if (annotatedMember instanceof AnnotatedField) {
            this._accessorMethod = null;
            this._field = (Field) annotatedMember.getMember();
        } else if (annotatedMember instanceof AnnotatedMethod) {
            this._accessorMethod = (Method) annotatedMember.getMember();
            this._field = null;
        } else {
            throw new IllegalArgumentException("Can not pass member of type " + annotatedMember.getClass().getName());
        }
        this._suppressNulls = z;
        this._suppressableValue = obj;
        this._includeInViews = beanPropertyDefinition.findViews();
        this._nullSerializer = null;
    }

    protected BeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        this(beanPropertyWriter, beanPropertyWriter._name);
    }

    protected BeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, SerializedString serializedString) {
        this._name = serializedString;
        this._wrapperName = beanPropertyWriter._wrapperName;
        this._member = beanPropertyWriter._member;
        this._contextAnnotations = beanPropertyWriter._contextAnnotations;
        this._declaredType = beanPropertyWriter._declaredType;
        this._accessorMethod = beanPropertyWriter._accessorMethod;
        this._field = beanPropertyWriter._field;
        this._serializer = beanPropertyWriter._serializer;
        this._nullSerializer = beanPropertyWriter._nullSerializer;
        if (beanPropertyWriter._internalSettings != null) {
            this._internalSettings = new HashMap(beanPropertyWriter._internalSettings);
        }
        this._cfgSerializationType = beanPropertyWriter._cfgSerializationType;
        this._dynamicSerializers = beanPropertyWriter._dynamicSerializers;
        this._suppressNulls = beanPropertyWriter._suppressNulls;
        this._suppressableValue = beanPropertyWriter._suppressableValue;
        this._includeInViews = beanPropertyWriter._includeInViews;
        this._typeSerializer = beanPropertyWriter._typeSerializer;
        this._nonTrivialBaseType = beanPropertyWriter._nonTrivialBaseType;
        this._metadata = beanPropertyWriter._metadata;
    }

    public BeanPropertyWriter rename(NameTransformer nameTransformer) {
        String transform = nameTransformer.transform(this._name.getValue());
        return transform.equals(this._name.toString()) ? this : new BeanPropertyWriter(this, new SerializedString(transform));
    }

    public void assignSerializer(JsonSerializer<Object> jsonSerializer) {
        if (this._serializer == null || this._serializer == jsonSerializer) {
            this._serializer = jsonSerializer;
            return;
        }
        throw new IllegalStateException("Can not override serializer");
    }

    public void assignNullSerializer(JsonSerializer<Object> jsonSerializer) {
        if (this._nullSerializer == null || this._nullSerializer == jsonSerializer) {
            this._nullSerializer = jsonSerializer;
            return;
        }
        throw new IllegalStateException("Can not override null serializer");
    }

    public BeanPropertyWriter unwrappingWriter(NameTransformer nameTransformer) {
        return new UnwrappingBeanPropertyWriter(this, nameTransformer);
    }

    public void setNonTrivialBaseType(JavaType javaType) {
        this._nonTrivialBaseType = javaType;
    }

    public String getName() {
        return this._name.getValue();
    }

    public JavaType getType() {
        return this._declaredType;
    }

    public AnnotatedMember getMember() {
        return this._member;
    }

    public boolean hasSerializer() {
        return this._serializer != null;
    }

    public boolean hasNullSerializer() {
        return this._nullSerializer != null;
    }

    public boolean willSuppressNulls() {
        return this._suppressNulls;
    }

    public JavaType getSerializationType() {
        return this._cfgSerializationType;
    }

    public Type getGenericPropertyType() {
        if (this._accessorMethod != null) {
            return this._accessorMethod.getGenericReturnType();
        }
        return this._field.getGenericType();
    }

    public Class<?>[] getViews() {
        return this._includeInViews;
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Object obj2;
        if (this._accessorMethod == null) {
            obj2 = this._field.get(obj);
        } else {
            obj2 = this._accessorMethod.invoke(obj, new Object[0]);
        }
        if (obj2 != null) {
            JsonSerializer jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class cls = obj2.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                jsonSerializer = propertySerializerMap.serializerFor(cls);
                if (jsonSerializer == null) {
                    jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (jsonSerializer.isEmpty(obj2)) {
                        return;
                    }
                } else if (this._suppressableValue.equals(obj2)) {
                    return;
                }
            }
            if (obj2 != obj || !_handleSelfReference(obj, jsonGenerator, serializerProvider, jsonSerializer)) {
                jsonGenerator.writeFieldName(this._name);
                if (this._typeSerializer == null) {
                    jsonSerializer.serialize(obj2, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj2, jsonGenerator, serializerProvider, this._typeSerializer);
                }
            }
        } else if (this._nullSerializer != null) {
            jsonGenerator.writeFieldName(this._name);
            this._nullSerializer.serialize(null, jsonGenerator, serializerProvider);
        }
    }

    public void serializeAsOmittedField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (!jsonGenerator.canOmitFields()) {
            jsonGenerator.writeOmittedField(this._name.getValue());
        }
    }

    public void serializeAsElement(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Object invoke = this._accessorMethod == null ? this._field.get(obj) : this._accessorMethod.invoke(obj, new Object[0]);
        if (invoke != null) {
            JsonSerializer jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class cls = invoke.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                jsonSerializer = propertySerializerMap.serializerFor(cls);
                if (jsonSerializer == null) {
                    jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (jsonSerializer.isEmpty(invoke)) {
                        serializeAsPlaceholder(obj, jsonGenerator, serializerProvider);
                        return;
                    }
                } else if (this._suppressableValue.equals(invoke)) {
                    serializeAsPlaceholder(obj, jsonGenerator, serializerProvider);
                    return;
                }
            }
            if (invoke != obj || !_handleSelfReference(obj, jsonGenerator, serializerProvider, jsonSerializer)) {
                if (this._typeSerializer == null) {
                    jsonSerializer.serialize(invoke, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(invoke, jsonGenerator, serializerProvider, this._typeSerializer);
                }
            }
        } else if (this._nullSerializer != null) {
            this._nullSerializer.serialize(null, jsonGenerator, serializerProvider);
        } else {
            jsonGenerator.writeNull();
        }
    }

    public void serializeAsPlaceholder(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._nullSerializer != null) {
            this._nullSerializer.serialize(null, jsonGenerator, serializerProvider);
        } else {
            jsonGenerator.writeNull();
        }
    }

    protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
        SerializerAndMapResult findAndAddPrimarySerializer;
        if (this._nonTrivialBaseType != null) {
            findAndAddPrimarySerializer = propertySerializerMap.findAndAddPrimarySerializer(serializerProvider.constructSpecializedType(this._nonTrivialBaseType, cls), serializerProvider, (BeanProperty) this);
        } else {
            findAndAddPrimarySerializer = propertySerializerMap.findAndAddPrimarySerializer((Class) cls, serializerProvider, (BeanProperty) this);
        }
        if (propertySerializerMap != findAndAddPrimarySerializer.map) {
            this._dynamicSerializers = findAndAddPrimarySerializer.map;
        }
        return findAndAddPrimarySerializer.serializer;
    }

    public final Object get(Object obj) {
        return this._accessorMethod == null ? this._field.get(obj) : this._accessorMethod.invoke(obj, new Object[0]);
    }

    protected boolean _handleSelfReference(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<?> jsonSerializer) {
        if (!serializerProvider.isEnabled(SerializationFeature.FAIL_ON_SELF_REFERENCES) || jsonSerializer.usesObjectId() || !(jsonSerializer instanceof BeanSerializerBase)) {
            return false;
        }
        throw new JsonMappingException("Direct self-reference leading to cycle");
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(40);
        stringBuilder.append("property '").append(getName()).append("' (");
        if (this._accessorMethod != null) {
            stringBuilder.append("via method ").append(this._accessorMethod.getDeclaringClass().getName()).append("#").append(this._accessorMethod.getName());
        } else {
            stringBuilder.append("field \"").append(this._field.getDeclaringClass().getName()).append("#").append(this._field.getName());
        }
        if (this._serializer == null) {
            stringBuilder.append(", no static serializer");
        } else {
            stringBuilder.append(", static serializer of type " + this._serializer.getClass().getName());
        }
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
