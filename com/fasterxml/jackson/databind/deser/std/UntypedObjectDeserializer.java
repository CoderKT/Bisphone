package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import se.emilsjolander.stickylistheaders.C1128R;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer {
    protected static final Object[] NO_OBJECTS;
    @Deprecated
    public static final UntypedObjectDeserializer instance;
    protected JsonDeserializer<Object> _listDeserializer;
    protected JsonDeserializer<Object> _mapDeserializer;
    protected JsonDeserializer<Object> _numberDeserializer;
    protected JsonDeserializer<Object> _stringDeserializer;

    /* renamed from: com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.1 */
    /* synthetic */ class C06261 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    @JacksonStdImpl
    public class Vanilla extends StdDeserializer<Object> {
        public static final Vanilla std;

        static {
            std = new Vanilla();
        }

        public Vanilla() {
            super(Object.class);
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            switch (jsonParser.getCurrentTokenId()) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                        return new LinkedHashMap(2);
                    }
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                            return UntypedObjectDeserializer.NO_OBJECTS;
                        }
                        return new ArrayList(2);
                    } else if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                        return mapArrayToArray(jsonParser, deserializationContext);
                    } else {
                        return mapArray(jsonParser, deserializationContext);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    return jsonParser.getText();
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                        return jsonParser.getBigIntegerValue();
                    }
                    return jsonParser.getNumberValue();
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return Double.valueOf(jsonParser.getDoubleValue());
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    return Boolean.TRUE;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    return Boolean.FALSE;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    return null;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    return jsonParser.getEmbeddedObject();
                default:
                    throw deserializationContext.mappingException(Object.class);
            }
            return mapObject(jsonParser, deserializationContext);
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            switch (jsonParser.getCurrentTokenId()) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    return jsonParser.getText();
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                        return jsonParser.getBigIntegerValue();
                    }
                    return jsonParser.getNumberValue();
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return Double.valueOf(jsonParser.getDoubleValue());
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                    return Boolean.TRUE;
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    return Boolean.FALSE;
                case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                    return null;
                case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                    return jsonParser.getEmbeddedObject();
                default:
                    throw deserializationContext.mappingException(Object.class);
            }
        }

        protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i = 2;
            Object deserialize = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(deserialize);
                return arrayList;
            }
            Object deserialize2 = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                arrayList = new ArrayList(2);
                arrayList.add(deserialize);
                arrayList.add(deserialize2);
                return arrayList;
            }
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            resetAndStart[0] = deserialize;
            resetAndStart[1] = deserialize2;
            Object[] objArr = resetAndStart;
            int i2 = 2;
            do {
                int i3;
                Object deserialize3 = deserialize(jsonParser, deserializationContext);
                i++;
                if (i2 >= objArr.length) {
                    objArr = leaseObjectBuffer.appendCompletedChunk(objArr);
                    i3 = 0;
                } else {
                    i3 = i2;
                }
                i2 = i3 + 1;
                objArr[i3] = deserialize3;
            } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
            List arrayList2 = new ArrayList(i);
            leaseObjectBuffer.completeAndClearBuffer(objArr, i2, arrayList2);
            return arrayList2;
        }

        protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
            String text = jsonParser.getText();
            jsonParser.nextToken();
            Object deserialize = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(2);
                linkedHashMap.put(text, deserialize);
                return linkedHashMap;
            }
            String text2 = jsonParser.getText();
            jsonParser.nextToken();
            Object deserialize2 = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                Object linkedHashMap2 = new LinkedHashMap(4);
                linkedHashMap2.put(text, deserialize);
                linkedHashMap2.put(text2, deserialize2);
                return linkedHashMap2;
            }
            linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put(text, deserialize);
            linkedHashMap2.put(text2, deserialize2);
            do {
                text = jsonParser.getText();
                jsonParser.nextToken();
                linkedHashMap2.put(text, deserialize(jsonParser, deserializationContext));
            } while (jsonParser.nextToken() != JsonToken.END_OBJECT);
            return linkedHashMap2;
        }

        protected Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            int i = 0;
            do {
                int i2;
                Object deserialize = deserialize(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i2 = 0;
                } else {
                    i2 = i;
                }
                i = i2 + 1;
                resetAndStart[i2] = deserialize;
            } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
            return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i);
        }
    }

    static {
        NO_OBJECTS = new Object[0];
        instance = new UntypedObjectDeserializer();
    }

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    public UntypedObjectDeserializer(UntypedObjectDeserializer untypedObjectDeserializer, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, JsonDeserializer<?> jsonDeserializer3, JsonDeserializer<?> jsonDeserializer4) {
        super(Object.class);
        this._mapDeserializer = jsonDeserializer;
        this._listDeserializer = jsonDeserializer2;
        this._stringDeserializer = jsonDeserializer3;
        this._numberDeserializer = jsonDeserializer4;
    }

    public void resolve(DeserializationContext deserializationContext) {
        JavaType constructType = deserializationContext.constructType(Object.class);
        JavaType constructType2 = deserializationContext.constructType(String.class);
        TypeFactory typeFactory = deserializationContext.getTypeFactory();
        this._mapDeserializer = _findCustomDeser(deserializationContext, typeFactory.constructMapType(Map.class, constructType2, constructType));
        this._listDeserializer = _findCustomDeser(deserializationContext, typeFactory.constructCollectionType(List.class, constructType));
        this._stringDeserializer = _findCustomDeser(deserializationContext, constructType2);
        this._numberDeserializer = _findCustomDeser(deserializationContext, typeFactory.constructType(Number.class));
    }

    protected JsonDeserializer<Object> _findCustomDeser(DeserializationContext deserializationContext, JavaType javaType) {
        Object findRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
        if (ClassUtil.isJacksonStdImpl(findRootValueDeserializer)) {
            return null;
        }
        return findRootValueDeserializer;
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        if (this._stringDeserializer == null && this._numberDeserializer == null && this._mapDeserializer == null && this._listDeserializer == null && getClass() == UntypedObjectDeserializer.class) {
            return Vanilla.std;
        }
        JsonDeserializer createContextual;
        JsonDeserializer createContextual2;
        JsonDeserializer createContextual3;
        JsonDeserializer jsonDeserializer = this._mapDeserializer;
        if (jsonDeserializer instanceof ContextualDeserializer) {
            createContextual = ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationContext, beanProperty);
        } else {
            createContextual = jsonDeserializer;
        }
        jsonDeserializer = this._listDeserializer;
        if (jsonDeserializer instanceof ContextualDeserializer) {
            createContextual2 = ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationContext, beanProperty);
        } else {
            createContextual2 = jsonDeserializer;
        }
        jsonDeserializer = this._stringDeserializer;
        if (jsonDeserializer instanceof ContextualDeserializer) {
            createContextual3 = ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationContext, beanProperty);
        } else {
            createContextual3 = jsonDeserializer;
        }
        jsonDeserializer = this._numberDeserializer;
        if (jsonDeserializer instanceof ContextualDeserializer) {
            jsonDeserializer = ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationContext, beanProperty);
        }
        if (createContextual == this._mapDeserializer && createContextual2 == this._listDeserializer && createContextual3 == this._stringDeserializer && jsonDeserializer == this._numberDeserializer) {
            return this;
        }
        return _withResolved(createContextual, createContextual2, createContextual3, jsonDeserializer);
    }

    protected JsonDeserializer<?> _withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, JsonDeserializer<?> jsonDeserializer3, JsonDeserializer<?> jsonDeserializer4) {
        return new UntypedObjectDeserializer(this, jsonDeserializer, jsonDeserializer2, jsonDeserializer3, jsonDeserializer4);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        switch (C06261.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (this._mapDeserializer != null) {
                    return this._mapDeserializer.deserialize(jsonParser, deserializationContext);
                }
                return mapObject(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return mapArrayToArray(jsonParser, deserializationContext);
                }
                if (this._listDeserializer != null) {
                    return this._listDeserializer.deserialize(jsonParser, deserializationContext);
                }
                return mapArray(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return jsonParser.getEmbeddedObject();
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                if (this._stringDeserializer != null) {
                    return this._stringDeserializer.deserialize(jsonParser, deserializationContext);
                }
                return jsonParser.getText();
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser.getBigIntegerValue();
                }
                return jsonParser.getNumberValue();
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return Boolean.TRUE;
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return Boolean.FALSE;
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return null;
            default:
                throw deserializationContext.mappingException(Object.class);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        switch (C06261.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return jsonParser.getEmbeddedObject();
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                if (this._stringDeserializer != null) {
                    return this._stringDeserializer.deserialize(jsonParser, deserializationContext);
                }
                return jsonParser.getText();
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser.getBigIntegerValue();
                }
                return jsonParser.getNumberValue();
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                if (this._numberDeserializer != null) {
                    return this._numberDeserializer.deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return Double.valueOf(jsonParser.getDoubleValue());
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return Boolean.TRUE;
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return Boolean.FALSE;
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return null;
            default:
                throw deserializationContext.mappingException(Object.class);
        }
    }

    protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int i = 2;
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return new ArrayList(2);
        }
        Object deserialize = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(deserialize);
            return arrayList;
        }
        Object deserialize2 = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            arrayList = new ArrayList(2);
            arrayList.add(deserialize);
            arrayList.add(deserialize2);
            return arrayList;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        resetAndStart[0] = deserialize;
        resetAndStart[1] = deserialize2;
        Object[] objArr = resetAndStart;
        int i2 = 2;
        do {
            int i3;
            Object deserialize3 = deserialize(jsonParser, deserializationContext);
            i++;
            if (i2 >= objArr.length) {
                objArr = leaseObjectBuffer.appendCompletedChunk(objArr);
                i3 = 0;
            } else {
                i3 = i2;
            }
            i2 = i3 + 1;
            objArr[i3] = deserialize3;
        } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
        List arrayList2 = new ArrayList(i);
        leaseObjectBuffer.completeAndClearBuffer(objArr, i2, arrayList2);
        return arrayList2;
    }

    protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken == JsonToken.END_OBJECT) {
            return new LinkedHashMap(2);
        }
        String currentName = jsonParser.getCurrentName();
        jsonParser.nextToken();
        Object deserialize = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
            Object linkedHashMap = new LinkedHashMap(2);
            linkedHashMap.put(currentName, deserialize);
            return linkedHashMap;
        }
        String currentName2 = jsonParser.getCurrentName();
        jsonParser.nextToken();
        Object deserialize2 = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
            linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(currentName, deserialize);
            linkedHashMap.put(currentName2, deserialize2);
            return linkedHashMap;
        }
        linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(currentName, deserialize);
        linkedHashMap.put(currentName2, deserialize2);
        do {
            currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            linkedHashMap.put(currentName, deserialize(jsonParser, deserializationContext));
        } while (jsonParser.nextToken() != JsonToken.END_OBJECT);
        return linkedHashMap;
    }

    protected Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return NO_OBJECTS;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        do {
            int i2;
            Object deserialize = deserialize(jsonParser, deserializationContext);
            if (i >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i2 = 0;
            } else {
                i2 = i;
            }
            i = i2 + 1;
            resetAndStart[i2] = deserialize;
        } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
        return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i);
    }
}
