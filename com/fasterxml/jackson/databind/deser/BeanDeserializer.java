package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import se.emilsjolander.stickylistheaders.C1128R;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {

    /* renamed from: com.fasterxml.jackson.databind.deser.BeanDeserializer.1 */
    /* synthetic */ class C06191 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public BeanDeserializer(BeanDeserializerBuilder beanDeserializerBuilder, BeanDescription beanDescription, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, HashSet<String> hashSet, boolean z, boolean z2) {
        super(beanDeserializerBuilder, beanDescription, beanPropertyMap, map, hashSet, z, z2);
    }

    protected BeanDeserializer(BeanDeserializerBase beanDeserializerBase) {
        super(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }

    protected BeanDeserializer(BeanDeserializerBase beanDeserializerBase, NameTransformer nameTransformer) {
        super(beanDeserializerBase, nameTransformer);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, ObjectIdReader objectIdReader) {
        super(beanDeserializerBase, objectIdReader);
    }

    public BeanDeserializer(BeanDeserializerBase beanDeserializerBase, HashSet<String> hashSet) {
        super(beanDeserializerBase, (HashSet) hashSet);
    }

    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        return getClass() != BeanDeserializer.class ? this : new BeanDeserializer((BeanDeserializerBase) this, nameTransformer);
    }

    public BeanDeserializer withObjectIdReader(ObjectIdReader objectIdReader) {
        return new BeanDeserializer((BeanDeserializerBase) this, objectIdReader);
    }

    public BeanDeserializer withIgnorableProperties(HashSet<String> hashSet) {
        return new BeanDeserializer((BeanDeserializerBase) this, (HashSet) hashSet);
    }

    protected BeanDeserializerBase asArrayDeserializer() {
        return new BeanAsArrayDeserializer(this, this._beanProperties.getPropertiesInInsertionOrder());
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT) {
            return _deserializeOther(jsonParser, deserializationContext, currentToken);
        }
        if (this._vanillaProcessing) {
            return vanillaDeserialize(jsonParser, deserializationContext, jsonParser.nextToken());
        }
        jsonParser.nextToken();
        if (this._objectIdReader != null) {
            return deserializeWithObjectId(jsonParser, deserializationContext);
        }
        return deserializeFromObject(jsonParser, deserializationContext);
    }

    protected final Object _deserializeOther(JsonParser jsonParser, DeserializationContext deserializationContext, JsonToken jsonToken) {
        switch (C06191.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return deserializeFromString(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return deserializeFromNumber(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return deserializeFromDouble(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return deserializeFromEmbedded(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return deserializeFromBoolean(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return deserializeFromArray(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                if (this._vanillaProcessing) {
                    return vanillaDeserialize(jsonParser, deserializationContext, jsonToken);
                }
                if (this._objectIdReader != null) {
                    return deserializeWithObjectId(jsonParser, deserializationContext);
                }
                return deserializeFromObject(jsonParser, deserializationContext);
            default:
                throw deserializationContext.mappingException(handledType());
        }
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        if (this._injectables != null) {
            injectValues(deserializationContext, obj);
        }
        if (this._unwrappedPropertyHandler != null) {
            return deserializeWithUnwrapped(jsonParser, deserializationContext, obj);
        }
        if (this._externalTypeIdHandler != null) {
            return deserializeWithExternalTypeId(jsonParser, deserializationContext, obj);
        }
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (this._needViewProcesing) {
            Class activeView = deserializationContext.getActiveView();
            if (activeView != null) {
                return deserializeWithView(jsonParser, deserializationContext, obj, activeView);
            }
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                } catch (Throwable e) {
                    wrapAndThrow(e, obj, currentName, deserializationContext);
                }
            } else {
                handleUnknownVanilla(jsonParser, deserializationContext, obj, currentName);
            }
            currentToken = jsonParser.nextToken();
        }
        return obj;
    }

    private final Object vanillaDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, JsonToken jsonToken) {
        Object createUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext);
        while (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, createUsingDefault);
                } catch (Throwable e) {
                    wrapAndThrow(e, createUsingDefault, currentName, deserializationContext);
                }
            } else {
                handleUnknownVanilla(jsonParser, deserializationContext, createUsingDefault, currentName);
            }
            jsonToken = jsonParser.nextToken();
        }
        return createUsingDefault;
    }

    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Object objectId;
        if (!this._nonStandardCreation) {
            Object createUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext);
            if (jsonParser.canReadObjectId()) {
                objectId = jsonParser.getObjectId();
                if (objectId != null) {
                    _handleTypedObjectId(jsonParser, deserializationContext, createUsingDefault, objectId);
                }
            }
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDefault);
            }
            if (this._needViewProcesing) {
                Class activeView = deserializationContext.getActiveView();
                if (activeView != null) {
                    return deserializeWithView(jsonParser, deserializationContext, createUsingDefault, activeView);
                }
            }
            JsonToken currentToken = jsonParser.getCurrentToken();
            while (currentToken == JsonToken.FIELD_NAME) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    try {
                        find.deserializeAndSet(jsonParser, deserializationContext, createUsingDefault);
                    } catch (Throwable e) {
                        wrapAndThrow(e, createUsingDefault, currentName, deserializationContext);
                    }
                } else {
                    handleUnknownVanilla(jsonParser, deserializationContext, createUsingDefault, currentName);
                }
                currentToken = jsonParser.nextToken();
            }
            return createUsingDefault;
        } else if (this._unwrappedPropertyHandler != null) {
            return deserializeWithUnwrapped(jsonParser, deserializationContext);
        } else {
            if (this._externalTypeIdHandler != null) {
                return deserializeWithExternalTypeId(jsonParser, deserializationContext);
            }
            objectId = deserializeFromObjectUsingNonDefault(jsonParser, deserializationContext);
            if (this._injectables == null) {
                return objectId;
            }
            injectValues(deserializationContext, objectId);
            return objectId;
        }
    }

    protected Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Object build;
        Object obj = null;
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, this._objectIdReader);
        JsonToken currentToken = jsonParser.getCurrentToken();
        TokenBuffer tokenBuffer = obj;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    jsonParser.nextToken();
                    try {
                        obj = propertyBasedCreator.build(deserializationContext, startBuilding);
                    } catch (Throwable e) {
                        wrapAndThrow(e, this._beanType.getRawClass(), currentName, deserializationContext);
                    }
                    if (obj.getClass() != this._beanType.getRawClass()) {
                        return handlePolymorphic(jsonParser, deserializationContext, obj, tokenBuffer);
                    }
                    if (tokenBuffer != null) {
                        obj = handleUnknownProperties(deserializationContext, obj, tokenBuffer);
                    }
                    return deserialize(jsonParser, deserializationContext, obj);
                }
            } else if (!startBuilding.readIdProperty(currentName)) {
                findCreatorProperty = this._beanProperties.find(currentName);
                if (findCreatorProperty != null) {
                    startBuilding.bufferProperty(findCreatorProperty, findCreatorProperty.deserialize(jsonParser, deserializationContext));
                } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                    handleIgnoredProperty(jsonParser, deserializationContext, handledType(), currentName);
                } else if (this._anySetter != null) {
                    startBuilding.bufferAnyProperty(this._anySetter, currentName, this._anySetter.deserialize(jsonParser, deserializationContext));
                } else {
                    if (tokenBuffer == null) {
                        tokenBuffer = new TokenBuffer(jsonParser);
                    }
                    tokenBuffer.writeFieldName(currentName);
                    tokenBuffer.copyCurrentStructure(jsonParser);
                }
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            build = propertyBasedCreator.build(deserializationContext, startBuilding);
        } catch (Throwable e2) {
            wrapInstantiationProblem(e2, deserializationContext);
            build = obj;
        }
        if (tokenBuffer == null) {
            return build;
        }
        if (build.getClass() != this._beanType.getRawClass()) {
            return handlePolymorphic(obj, deserializationContext, build, tokenBuffer);
        }
        return handleUnknownProperties(deserializationContext, build, tokenBuffer);
    }

    protected final Object deserializeWithView(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, Class<?> cls) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find == null) {
                handleUnknownVanilla(jsonParser, deserializationContext, obj, currentName);
            } else if (find.visibleInView(cls)) {
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                } catch (Throwable e) {
                    wrapAndThrow(e, obj, currentName, deserializationContext);
                }
            } else {
                jsonParser.skipChildren();
            }
            currentToken = jsonParser.nextToken();
        }
        return obj;
    }

    protected Object deserializeWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._propertyBasedCreator != null) {
            return deserializeUsingPropertyBasedWithUnwrapped(jsonParser, deserializationContext);
        }
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser);
        tokenBuffer.writeStartObject();
        Object createUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext);
        if (this._injectables != null) {
            injectValues(deserializationContext, createUsingDefault);
        }
        Class activeView = this._needViewProcesing ? deserializationContext.getActiveView() : null;
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                if (activeView == null || find.visibleInView(activeView)) {
                    try {
                        find.deserializeAndSet(jsonParser, deserializationContext, createUsingDefault);
                    } catch (Throwable e) {
                        wrapAndThrow(e, createUsingDefault, currentName, deserializationContext);
                    }
                } else {
                    jsonParser.skipChildren();
                }
            } else if (this._ignorableProps == null || !this._ignorableProps.contains(currentName)) {
                tokenBuffer.writeFieldName(currentName);
                tokenBuffer.copyCurrentStructure(jsonParser);
                if (this._anySetter != null) {
                    try {
                        this._anySetter.deserializeAndSet(jsonParser, deserializationContext, createUsingDefault, currentName);
                    } catch (Throwable e2) {
                        wrapAndThrow(e2, createUsingDefault, currentName, deserializationContext);
                    }
                }
            } else {
                handleIgnoredProperty(jsonParser, deserializationContext, createUsingDefault, currentName);
            }
            jsonParser.nextToken();
        }
        tokenBuffer.writeEndObject();
        this._unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, createUsingDefault, tokenBuffer);
        return createUsingDefault;
    }

    protected Object deserializeWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser);
        tokenBuffer.writeStartObject();
        Class activeView = this._needViewProcesing ? deserializationContext.getActiveView() : null;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            jsonParser.nextToken();
            if (find != null) {
                if (activeView == null || find.visibleInView(activeView)) {
                    try {
                        find.deserializeAndSet(jsonParser, deserializationContext, obj);
                    } catch (Throwable e) {
                        wrapAndThrow(e, obj, currentName, deserializationContext);
                    }
                } else {
                    jsonParser.skipChildren();
                }
            } else if (this._ignorableProps == null || !this._ignorableProps.contains(currentName)) {
                tokenBuffer.writeFieldName(currentName);
                tokenBuffer.copyCurrentStructure(jsonParser);
                if (this._anySetter != null) {
                    this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
                }
            } else {
                handleIgnoredProperty(jsonParser, deserializationContext, obj, currentName);
            }
            currentToken = jsonParser.nextToken();
        }
        tokenBuffer.writeEndObject();
        this._unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, obj, tokenBuffer);
        return obj;
    }

    protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) {
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, this._objectIdReader);
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser);
        tokenBuffer.writeStartObject();
        JsonToken currentToken = jsonParser.getCurrentToken();
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    currentToken = jsonParser.nextToken();
                    try {
                        Object build = propertyBasedCreator.build(deserializationContext, startBuilding);
                        while (currentToken == JsonToken.FIELD_NAME) {
                            jsonParser.nextToken();
                            tokenBuffer.copyCurrentStructure(jsonParser);
                            currentToken = jsonParser.nextToken();
                        }
                        tokenBuffer.writeEndObject();
                        if (build.getClass() == this._beanType.getRawClass()) {
                            return this._unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, build, tokenBuffer);
                        }
                        tokenBuffer.close();
                        throw deserializationContext.mappingException("Can not create polymorphic instances with unwrapped values");
                    } catch (Throwable e) {
                        wrapAndThrow(e, this._beanType.getRawClass(), currentName, deserializationContext);
                    }
                } else {
                    continue;
                }
            } else if (!startBuilding.readIdProperty(currentName)) {
                findCreatorProperty = this._beanProperties.find(currentName);
                if (findCreatorProperty != null) {
                    startBuilding.bufferProperty(findCreatorProperty, findCreatorProperty.deserialize(jsonParser, deserializationContext));
                } else if (this._ignorableProps == null || !this._ignorableProps.contains(currentName)) {
                    tokenBuffer.writeFieldName(currentName);
                    tokenBuffer.copyCurrentStructure(jsonParser);
                    if (this._anySetter != null) {
                        startBuilding.bufferAnyProperty(this._anySetter, currentName, this._anySetter.deserialize(jsonParser, deserializationContext));
                    }
                } else {
                    handleIgnoredProperty(jsonParser, deserializationContext, handledType(), currentName);
                }
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            return this._unwrappedPropertyHandler.processUnwrapped(jsonParser, deserializationContext, propertyBasedCreator.build(deserializationContext, startBuilding), tokenBuffer);
        } catch (Throwable e2) {
            wrapInstantiationProblem(e2, deserializationContext);
            return null;
        }
    }

    protected Object deserializeWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._propertyBasedCreator != null) {
            return deserializeUsingPropertyBasedWithExternalTypeId(jsonParser, deserializationContext);
        }
        return deserializeWithExternalTypeId(jsonParser, deserializationContext, this._valueInstantiator.createUsingDefault(deserializationContext));
    }

    protected Object deserializeWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        Class activeView = this._needViewProcesing ? deserializationContext.getActiveView() : null;
        ExternalTypeHandler start = this._externalTypeIdHandler.start();
        JsonToken currentToken = jsonParser.getCurrentToken();
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            if (find != null) {
                if (jsonParser.getCurrentToken().isScalarValue()) {
                    start.handleTypePropertyValue(jsonParser, deserializationContext, currentName, obj);
                }
                if (activeView == null || find.visibleInView(activeView)) {
                    try {
                        find.deserializeAndSet(jsonParser, deserializationContext, obj);
                    } catch (Throwable e) {
                        wrapAndThrow(e, obj, currentName, deserializationContext);
                    }
                } else {
                    jsonParser.skipChildren();
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                handleIgnoredProperty(jsonParser, deserializationContext, obj, currentName);
            } else if (!start.handlePropertyValue(jsonParser, deserializationContext, currentName, obj)) {
                if (this._anySetter != null) {
                    try {
                        this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
                    } catch (Throwable e2) {
                        wrapAndThrow(e2, obj, currentName, deserializationContext);
                    }
                } else {
                    handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
                }
            }
            currentToken = jsonParser.nextToken();
        }
        return start.complete(jsonParser, deserializationContext, obj);
    }

    protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Object obj = null;
        ExternalTypeHandler start = this._externalTypeIdHandler.start();
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, this._objectIdReader);
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser);
        tokenBuffer.writeStartObject();
        JsonToken currentToken = jsonParser.getCurrentToken();
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (start.handlePropertyValue(jsonParser, deserializationContext, currentName, startBuilding)) {
                    continue;
                } else {
                    if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                        currentToken = jsonParser.nextToken();
                        try {
                            obj = propertyBasedCreator.build(deserializationContext, startBuilding);
                            while (currentToken == JsonToken.FIELD_NAME) {
                                jsonParser.nextToken();
                                tokenBuffer.copyCurrentStructure(jsonParser);
                                currentToken = jsonParser.nextToken();
                            }
                            if (obj.getClass() == this._beanType.getRawClass()) {
                                return start.complete(jsonParser, deserializationContext, obj);
                            }
                            throw deserializationContext.mappingException("Can not create polymorphic instances with unwrapped values");
                        } catch (Throwable e) {
                            wrapAndThrow(e, this._beanType.getRawClass(), currentName, deserializationContext);
                        }
                    } else {
                        continue;
                    }
                }
            } else if (!startBuilding.readIdProperty(currentName)) {
                findCreatorProperty = this._beanProperties.find(currentName);
                if (findCreatorProperty != null) {
                    startBuilding.bufferProperty(findCreatorProperty, findCreatorProperty.deserialize(jsonParser, deserializationContext));
                } else if (!start.handlePropertyValue(jsonParser, deserializationContext, currentName, obj)) {
                    if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                        handleIgnoredProperty(jsonParser, deserializationContext, handledType(), currentName);
                    } else if (this._anySetter != null) {
                        startBuilding.bufferAnyProperty(this._anySetter, currentName, this._anySetter.deserialize(jsonParser, deserializationContext));
                    }
                }
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            return start.complete(jsonParser, deserializationContext, startBuilding, propertyBasedCreator);
        } catch (Throwable e2) {
            wrapInstantiationProblem(e2, deserializationContext);
            return obj;
        }
    }
}
