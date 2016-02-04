package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TreeMap;
import se.emilsjolander.stickylistheaders.C1128R;

public class TokenBuffer extends JsonGenerator {
    protected static final int DEFAULT_GENERATOR_FEATURES;
    protected int _appendAt;
    protected boolean _closed;
    protected Segment _first;
    protected int _generatorFeatures;
    protected boolean _hasNativeId;
    protected boolean _hasNativeObjectIds;
    protected boolean _hasNativeTypeIds;
    protected Segment _last;
    protected boolean _mayHaveNativeIds;
    protected ObjectCodec _objectCodec;
    protected Object _objectId;
    protected Object _typeId;
    protected JsonWriteContext _writeContext;

    /* renamed from: com.fasterxml.jackson.databind.util.TokenBuffer.1 */
    /* synthetic */ class C06471 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType;
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        static {
            $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType = new int[NumberType.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[NumberType.BIG_INTEGER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[NumberType.BIG_DECIMAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[NumberType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[NumberType.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 6;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 7;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 9;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 10;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 12;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    public final class Parser extends ParserMinimalBase {
        protected transient ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected final boolean _hasNativeIds;
        protected final boolean _hasNativeObjectIds;
        protected final boolean _hasNativeTypeIds;
        protected JsonLocation _location;
        protected JsonReadContext _parsingContext;
        protected Segment _segment;
        protected int _segmentPtr;

        public Parser(Segment segment, ObjectCodec objectCodec, boolean z, boolean z2) {
            super(0);
            this._location = null;
            this._segment = segment;
            this._segmentPtr = -1;
            this._codec = objectCodec;
            this._parsingContext = JsonReadContext.createRootContext(null);
            this._hasNativeTypeIds = z;
            this._hasNativeObjectIds = z2;
            this._hasNativeIds = z | z2;
        }

        public void setLocation(JsonLocation jsonLocation) {
            this._location = jsonLocation;
        }

        public ObjectCodec getCodec() {
            return this._codec;
        }

        public void close() {
            if (!this._closed) {
                this._closed = true;
            }
        }

        public JsonToken nextToken() {
            if (this._closed || this._segment == null) {
                return null;
            }
            int i = this._segmentPtr + 1;
            this._segmentPtr = i;
            if (i >= 16) {
                this._segmentPtr = 0;
                this._segment = this._segment.next();
                if (this._segment == null) {
                    return null;
                }
            }
            this._currToken = this._segment.type(this._segmentPtr);
            if (this._currToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                this._parsingContext.setCurrentName(_currentObject instanceof String ? (String) _currentObject : _currentObject.toString());
            } else if (this._currToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
            } else if (this._currToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
            } else if (this._currToken == JsonToken.END_OBJECT || this._currToken == JsonToken.END_ARRAY) {
                this._parsingContext = this._parsingContext.getParent();
                if (this._parsingContext == null) {
                    this._parsingContext = JsonReadContext.createRootContext(null);
                }
            }
            return this._currToken;
        }

        public JsonLocation getTokenLocation() {
            return getCurrentLocation();
        }

        public JsonLocation getCurrentLocation() {
            return this._location == null ? JsonLocation.NA : this._location;
        }

        public String getCurrentName() {
            return this._parsingContext.getCurrentName();
        }

        public String getText() {
            Object _currentObject;
            if (this._currToken == JsonToken.VALUE_STRING || this._currToken == JsonToken.FIELD_NAME) {
                _currentObject = _currentObject();
                if (_currentObject instanceof String) {
                    return (String) _currentObject;
                }
                return _currentObject == null ? null : _currentObject.toString();
            } else if (this._currToken == null) {
                return null;
            } else {
                switch (C06471.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                        _currentObject = _currentObject();
                        if (_currentObject != null) {
                            return _currentObject.toString();
                        }
                        return null;
                    default:
                        return this._currToken.asString();
                }
            }
        }

        public char[] getTextCharacters() {
            String text = getText();
            return text == null ? null : text.toCharArray();
        }

        public int getTextLength() {
            String text = getText();
            return text == null ? 0 : text.length();
        }

        public int getTextOffset() {
            return 0;
        }

        public boolean hasTextCharacters() {
            return false;
        }

        public BigInteger getBigIntegerValue() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigInteger) {
                return (BigInteger) numberValue;
            }
            if (getNumberType() == NumberType.BIG_DECIMAL) {
                return ((BigDecimal) numberValue).toBigInteger();
            }
            return BigInteger.valueOf(numberValue.longValue());
        }

        public BigDecimal getDecimalValue() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigDecimal) {
                return (BigDecimal) numberValue;
            }
            switch (C06471.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[getNumberType().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    return BigDecimal.valueOf(numberValue.longValue());
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return new BigDecimal((BigInteger) numberValue);
                default:
                    return BigDecimal.valueOf(numberValue.doubleValue());
            }
        }

        public double getDoubleValue() {
            return getNumberValue().doubleValue();
        }

        public float getFloatValue() {
            return getNumberValue().floatValue();
        }

        public int getIntValue() {
            if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
                return ((Number) _currentObject()).intValue();
            }
            return getNumberValue().intValue();
        }

        public long getLongValue() {
            return getNumberValue().longValue();
        }

        public NumberType getNumberType() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof Integer) {
                return NumberType.INT;
            }
            if (numberValue instanceof Long) {
                return NumberType.LONG;
            }
            if (numberValue instanceof Double) {
                return NumberType.DOUBLE;
            }
            if (numberValue instanceof BigDecimal) {
                return NumberType.BIG_DECIMAL;
            }
            if (numberValue instanceof BigInteger) {
                return NumberType.BIG_INTEGER;
            }
            if (numberValue instanceof Float) {
                return NumberType.FLOAT;
            }
            if (numberValue instanceof Short) {
                return NumberType.INT;
            }
            return null;
        }

        public final Number getNumberValue() {
            _checkIsNumber();
            Object _currentObject = _currentObject();
            if (_currentObject instanceof Number) {
                return (Number) _currentObject;
            }
            if (_currentObject instanceof String) {
                String str = (String) _currentObject;
                if (str.indexOf(46) >= 0) {
                    return Double.valueOf(Double.parseDouble(str));
                }
                return Long.valueOf(Long.parseLong(str));
            } else if (_currentObject == null) {
                return null;
            } else {
                throw new IllegalStateException("Internal error: entry should be a Number, but is of type " + _currentObject.getClass().getName());
            }
        }

        public Object getEmbeddedObject() {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return _currentObject();
            }
            return null;
        }

        public byte[] getBinaryValue(Base64Variant base64Variant) {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof byte[]) {
                    return (byte[]) _currentObject;
                }
            }
            if (this._currToken != JsonToken.VALUE_STRING) {
                throw _constructError("Current token (" + this._currToken + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
            }
            String text = getText();
            if (text == null) {
                return null;
            }
            ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
            if (byteArrayBuilder == null) {
                byteArrayBuilder = new ByteArrayBuilder(100);
                this._byteBuilder = byteArrayBuilder;
            } else {
                this._byteBuilder.reset();
            }
            _decodeBase64(text, byteArrayBuilder, base64Variant);
            return byteArrayBuilder.toByteArray();
        }

        public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            if (binaryValue == null) {
                return 0;
            }
            outputStream.write(binaryValue, 0, binaryValue.length);
            return binaryValue.length;
        }

        public boolean canReadObjectId() {
            return this._hasNativeObjectIds;
        }

        public boolean canReadTypeId() {
            return this._hasNativeTypeIds;
        }

        public Object getTypeId() {
            return this._segment.findTypeId(this._segmentPtr);
        }

        public Object getObjectId() {
            return this._segment.findObjectId(this._segmentPtr);
        }

        protected final Object _currentObject() {
            return this._segment.get(this._segmentPtr);
        }

        protected final void _checkIsNumber() {
            if (this._currToken == null || !this._currToken.isNumeric()) {
                throw _constructError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
            }
        }

        protected void _handleEOF() {
            _throwInternal();
        }
    }

    public final class Segment {
        private static final JsonToken[] TOKEN_TYPES_BY_INDEX;
        protected TreeMap<Integer, Object> _nativeIds;
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object[] _tokens;

        static {
            TOKEN_TYPES_BY_INDEX = new JsonToken[16];
            Object values = JsonToken.values();
            System.arraycopy(values, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, values.length - 1));
        }

        public Segment() {
            this._tokens = new Object[16];
        }

        public JsonToken type(int i) {
            long j = this._tokenTypes;
            if (i > 0) {
                j >>= i << 2;
            }
            return TOKEN_TYPES_BY_INDEX[((int) j) & 15];
        }

        public Object get(int i) {
            return this._tokens[i];
        }

        public Segment next() {
            return this._next;
        }

        public boolean hasIds() {
            return this._nativeIds != null;
        }

        public Segment append(int i, JsonToken jsonToken) {
            if (i < 16) {
                set(i, jsonToken);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken);
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken, Object obj, Object obj2) {
            if (i < 16) {
                set(i, jsonToken, obj, obj2);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj, obj2);
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken, Object obj) {
            if (i < 16) {
                set(i, jsonToken, obj);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj);
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken, Object obj, Object obj2, Object obj3) {
            if (i < 16) {
                set(i, jsonToken, obj, obj2, obj3);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj, obj2, obj3);
            return this._next;
        }

        private void set(int i, JsonToken jsonToken) {
            long ordinal = (long) jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
        }

        private void set(int i, JsonToken jsonToken, Object obj, Object obj2) {
            long ordinal = (long) jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
            assignNativeIds(i, obj, obj2);
        }

        private void set(int i, JsonToken jsonToken, Object obj) {
            this._tokens[i] = obj;
            long ordinal = (long) jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
        }

        private void set(int i, JsonToken jsonToken, Object obj, Object obj2, Object obj3) {
            this._tokens[i] = obj;
            long ordinal = (long) jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
            assignNativeIds(i, obj2, obj3);
        }

        private final void assignNativeIds(int i, Object obj, Object obj2) {
            if (this._nativeIds == null) {
                this._nativeIds = new TreeMap();
            }
            if (obj != null) {
                this._nativeIds.put(Integer.valueOf(_objectIdIndex(i)), obj);
            }
            if (obj2 != null) {
                this._nativeIds.put(Integer.valueOf(_typeIdIndex(i)), obj2);
            }
        }

        public Object findObjectId(int i) {
            return this._nativeIds == null ? null : this._nativeIds.get(Integer.valueOf(_objectIdIndex(i)));
        }

        public Object findTypeId(int i) {
            return this._nativeIds == null ? null : this._nativeIds.get(Integer.valueOf(_typeIdIndex(i)));
        }

        private final int _typeIdIndex(int i) {
            return i + i;
        }

        private final int _objectIdIndex(int i) {
            return (i + i) + 1;
        }
    }

    static {
        DEFAULT_GENERATOR_FEATURES = Feature.collectDefaults();
    }

    public TokenBuffer(ObjectCodec objectCodec, boolean z) {
        this._hasNativeId = false;
        this._objectCodec = objectCodec;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURES;
        this._writeContext = JsonWriteContext.createRootContext(null);
        Segment segment = new Segment();
        this._last = segment;
        this._first = segment;
        this._appendAt = 0;
        this._hasNativeTypeIds = z;
        this._hasNativeObjectIds = z;
        this._mayHaveNativeIds = this._hasNativeTypeIds | this._hasNativeObjectIds;
    }

    public TokenBuffer(JsonParser jsonParser) {
        this._hasNativeId = false;
        this._objectCodec = jsonParser.getCodec();
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURES;
        this._writeContext = JsonWriteContext.createRootContext(null);
        Segment segment = new Segment();
        this._last = segment;
        this._first = segment;
        this._appendAt = 0;
        this._hasNativeTypeIds = jsonParser.canReadTypeId();
        this._hasNativeObjectIds = jsonParser.canReadObjectId();
        this._mayHaveNativeIds = this._hasNativeTypeIds | this._hasNativeObjectIds;
    }

    public JsonParser asParser() {
        return asParser(this._objectCodec);
    }

    public JsonParser asParser(ObjectCodec objectCodec) {
        return new Parser(this._first, objectCodec, this._hasNativeTypeIds, this._hasNativeObjectIds);
    }

    public JsonParser asParser(JsonParser jsonParser) {
        JsonParser parser = new Parser(this._first, jsonParser.getCodec(), this._hasNativeTypeIds, this._hasNativeObjectIds);
        parser.setLocation(jsonParser.getTokenLocation());
        return parser;
    }

    public JsonToken firstToken() {
        if (this._first != null) {
            return this._first.type(0);
        }
        return null;
    }

    public void serialize(JsonGenerator jsonGenerator) {
        Segment segment = this._first;
        boolean z = this._mayHaveNativeIds;
        boolean z2 = z && segment.hasIds();
        int i = -1;
        Segment segment2 = segment;
        while (true) {
            int i2;
            i++;
            if (i >= 16) {
                Segment next = segment2.next();
                if (next != null) {
                    z2 = z && next.hasIds();
                    i2 = 0;
                    segment = next;
                    boolean z3 = z2;
                } else {
                    return;
                }
            }
            segment = segment2;
            i2 = i;
            z3 = z2;
            JsonToken type = segment.type(i2);
            if (type != null) {
                if (z3) {
                    Object findObjectId = segment.findObjectId(i2);
                    if (findObjectId != null) {
                        jsonGenerator.writeObjectId(findObjectId);
                    }
                    findObjectId = segment.findTypeId(i2);
                    if (findObjectId != null) {
                        jsonGenerator.writeTypeId(findObjectId);
                    }
                }
                Object obj;
                switch (C06471.$SwitchMap$com$fasterxml$jackson$core$JsonToken[type.ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        jsonGenerator.writeStartObject();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        jsonGenerator.writeEndObject();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        jsonGenerator.writeStartArray();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        jsonGenerator.writeEndArray();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        obj = segment.get(i2);
                        if (!(obj instanceof SerializableString)) {
                            jsonGenerator.writeFieldName((String) obj);
                            break;
                        } else {
                            jsonGenerator.writeFieldName((SerializableString) obj);
                            break;
                        }
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        obj = segment.get(i2);
                        if (!(obj instanceof SerializableString)) {
                            jsonGenerator.writeString((String) obj);
                            break;
                        } else {
                            jsonGenerator.writeString((SerializableString) obj);
                            break;
                        }
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        obj = segment.get(i2);
                        if (!(obj instanceof Integer)) {
                            if (!(obj instanceof BigInteger)) {
                                if (!(obj instanceof Long)) {
                                    if (!(obj instanceof Short)) {
                                        jsonGenerator.writeNumber(((Number) obj).intValue());
                                        break;
                                    } else {
                                        jsonGenerator.writeNumber(((Short) obj).shortValue());
                                        break;
                                    }
                                }
                                jsonGenerator.writeNumber(((Long) obj).longValue());
                                break;
                            }
                            jsonGenerator.writeNumber((BigInteger) obj);
                            break;
                        }
                        jsonGenerator.writeNumber(((Integer) obj).intValue());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                        obj = segment.get(i2);
                        if (obj instanceof Double) {
                            jsonGenerator.writeNumber(((Double) obj).doubleValue());
                            break;
                        } else if (obj instanceof BigDecimal) {
                            jsonGenerator.writeNumber((BigDecimal) obj);
                            break;
                        } else if (obj instanceof Float) {
                            jsonGenerator.writeNumber(((Float) obj).floatValue());
                            break;
                        } else if (obj == null) {
                            jsonGenerator.writeNull();
                            break;
                        } else if (obj instanceof String) {
                            jsonGenerator.writeNumber((String) obj);
                            break;
                        } else {
                            throw new JsonGenerationException("Unrecognized value type for VALUE_NUMBER_FLOAT: " + obj.getClass().getName() + ", can not serialize");
                        }
                    case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                        jsonGenerator.writeBoolean(true);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                        jsonGenerator.writeBoolean(false);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                        jsonGenerator.writeNull();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                        jsonGenerator.writeObject(segment.get(i2));
                        break;
                    default:
                        throw new RuntimeException("Internal error: should never end up through this code path");
                }
                z2 = z3;
                i = i2;
                segment2 = segment;
            } else {
                return;
            }
        }
    }

    public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentTokenId() != JsonToken.FIELD_NAME.id()) {
            copyCurrentStructure(jsonParser);
        } else {
            JsonToken nextToken;
            writeStartObject();
            do {
                copyCurrentStructure(jsonParser);
                nextToken = jsonParser.nextToken();
            } while (nextToken == JsonToken.FIELD_NAME);
            if (nextToken != JsonToken.END_OBJECT) {
                throw deserializationContext.mappingException("Expected END_OBJECT after copying contents of a JsonParser into TokenBuffer, got " + nextToken);
            }
            writeEndObject();
        }
        return this;
    }

    public String toString() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[TokenBuffer: ");
        JsonParser asParser = asParser();
        int i2 = (this._hasNativeTypeIds || this._hasNativeObjectIds) ? 1 : 0;
        while (true) {
            JsonToken nextToken = asParser.nextToken();
            if (nextToken == null) {
                break;
            }
            if (i2 != 0) {
                try {
                    _appendNativeIds(stringBuilder);
                } catch (Throwable e) {
                    throw new IllegalStateException(e);
                }
            }
            if (i < 100) {
                if (i > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(nextToken.toString());
                if (nextToken == JsonToken.FIELD_NAME) {
                    stringBuilder.append('(');
                    stringBuilder.append(asParser.getCurrentName());
                    stringBuilder.append(')');
                }
            }
            i++;
        }
        if (i >= 100) {
            stringBuilder.append(" ... (truncated ").append(i - 100).append(" entries)");
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private final void _appendNativeIds(StringBuilder stringBuilder) {
        Object findObjectId = this._last.findObjectId(this._appendAt - 1);
        if (findObjectId != null) {
            stringBuilder.append("[objectId=").append(String.valueOf(findObjectId)).append(']');
        }
        findObjectId = this._last.findTypeId(this._appendAt - 1);
        if (findObjectId != null) {
            stringBuilder.append("[typeId=").append(String.valueOf(findObjectId)).append(']');
        }
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    public boolean canWriteBinaryNatively() {
        return true;
    }

    public void flush() {
    }

    public void close() {
        this._closed = true;
    }

    public final void writeStartArray() {
        _append(JsonToken.START_ARRAY);
        this._writeContext = this._writeContext.createChildArrayContext();
    }

    public final void writeEndArray() {
        _append(JsonToken.END_ARRAY);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    public final void writeStartObject() {
        _append(JsonToken.START_OBJECT);
        this._writeContext = this._writeContext.createChildObjectContext();
    }

    public final void writeEndObject() {
        _append(JsonToken.END_OBJECT);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    public final void writeFieldName(String str) {
        _append(JsonToken.FIELD_NAME, str);
        this._writeContext.writeFieldName(str);
    }

    public void writeFieldName(SerializableString serializableString) {
        _append(JsonToken.FIELD_NAME, serializableString);
        this._writeContext.writeFieldName(serializableString.getValue());
    }

    public void writeString(String str) {
        if (str == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, str);
        }
    }

    public void writeString(char[] cArr, int i, int i2) {
        writeString(new String(cArr, i, i2));
    }

    public void writeString(SerializableString serializableString) {
        if (serializableString == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, serializableString);
        }
    }

    public void writeRawValue(String str) {
        _reportUnsupportedOperation();
    }

    public void writeNumber(short s) {
        _append(JsonToken.VALUE_NUMBER_INT, Short.valueOf(s));
    }

    public void writeNumber(int i) {
        _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    public void writeNumber(long j) {
        _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    public void writeNumber(double d) {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    public void writeNumber(float f) {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    public void writeNumber(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }

    public void writeNumber(BigInteger bigInteger) {
        if (bigInteger == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_INT, bigInteger);
        }
    }

    public void writeNumber(String str) {
        _append(JsonToken.VALUE_NUMBER_FLOAT, str);
    }

    public void writeBoolean(boolean z) {
        _append(z ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
    }

    public void writeNull() {
        _append(JsonToken.VALUE_NULL);
    }

    public void writeObject(Object obj) {
        if (obj == null) {
            writeNull();
        } else if (obj.getClass() == byte[].class) {
            _append(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
        } else if (this._objectCodec == null) {
            _append(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
        } else {
            this._objectCodec.writeValue(this, obj);
        }
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        writeObject(obj);
    }

    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) {
        throw new UnsupportedOperationException();
    }

    public boolean canWriteTypeId() {
        return this._hasNativeTypeIds;
    }

    public boolean canWriteObjectId() {
        return this._hasNativeObjectIds;
    }

    public void writeTypeId(Object obj) {
        this._typeId = obj;
        this._hasNativeId = true;
    }

    public void writeObjectId(Object obj) {
        this._objectId = obj;
        this._hasNativeId = true;
    }

    public void copyCurrentEvent(JsonParser jsonParser) {
        if (this._mayHaveNativeIds) {
            _checkNativeIds(jsonParser);
        }
        switch (C06471.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonParser.getCurrentToken().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                writeStartObject();
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                writeEndObject();
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                writeStartArray();
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                writeEndArray();
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                writeFieldName(jsonParser.getCurrentName());
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                if (jsonParser.hasTextCharacters()) {
                    writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                } else {
                    writeString(jsonParser.getText());
                }
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                switch (C06471.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[jsonParser.getNumberType().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        writeNumber(jsonParser.getIntValue());
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        writeNumber(jsonParser.getBigIntegerValue());
                    default:
                        writeNumber(jsonParser.getLongValue());
                }
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                switch (C06471.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[jsonParser.getNumberType().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        writeNumber(jsonParser.getDecimalValue());
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        writeNumber(jsonParser.getFloatValue());
                    default:
                        writeNumber(jsonParser.getDoubleValue());
                }
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                writeBoolean(true);
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                writeBoolean(false);
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                writeNull();
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                writeObject(jsonParser.getEmbeddedObject());
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
    }

    public void copyCurrentStructure(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.FIELD_NAME) {
            if (this._mayHaveNativeIds) {
                _checkNativeIds(jsonParser);
            }
            writeFieldName(jsonParser.getCurrentName());
            currentToken = jsonParser.nextToken();
        }
        if (this._mayHaveNativeIds) {
            _checkNativeIds(jsonParser);
        }
        switch (C06471.$SwitchMap$com$fasterxml$jackson$core$JsonToken[currentToken.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                writeStartObject();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    copyCurrentStructure(jsonParser);
                }
                writeEndObject();
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                writeStartArray();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    copyCurrentStructure(jsonParser);
                }
                writeEndArray();
            default:
                copyCurrentEvent(jsonParser);
        }
    }

    private final void _checkNativeIds(JsonParser jsonParser) {
        Object typeId = jsonParser.getTypeId();
        this._typeId = typeId;
        if (typeId != null) {
            this._hasNativeId = true;
        }
        typeId = jsonParser.getObjectId();
        this._objectId = typeId;
        if (typeId != null) {
            this._hasNativeId = true;
        }
    }

    protected final void _append(JsonToken jsonToken) {
        Segment append = this._hasNativeId ? this._last.append(this._appendAt, jsonToken, this._objectId, this._typeId) : this._last.append(this._appendAt, jsonToken);
        if (append == null) {
            this._appendAt++;
            return;
        }
        this._last = append;
        this._appendAt = 1;
    }

    protected final void _append(JsonToken jsonToken, Object obj) {
        Segment append;
        if (this._hasNativeId) {
            append = this._last.append(this._appendAt, jsonToken, obj, this._objectId, this._typeId);
        } else {
            append = this._last.append(this._appendAt, jsonToken, obj);
        }
        if (append == null) {
            this._appendAt++;
            return;
        }
        this._last = append;
        this._appendAt = 1;
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }
}
