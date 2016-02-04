package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Closeable;
import java.io.Flushable;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class JsonGenerator implements Closeable, Flushable {

    public enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        WRITE_BIGDECIMAL_AS_PLAIN(false),
        FLUSH_PASSED_TO_STREAM(true),
        ESCAPE_NON_ASCII(false),
        STRICT_DUPLICATE_DETECTION(false);
        
        private final boolean _defaultState;
        private final int _mask;

        public static int collectDefaults() {
            int i = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        private Feature(boolean z) {
            this._defaultState = z;
            this._mask = 1 << ordinal();
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return this._mask;
        }
    }

    public abstract void flush();

    public abstract JsonGenerator useDefaultPrettyPrinter();

    public abstract int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i);

    public abstract void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2);

    public abstract void writeBoolean(boolean z);

    public abstract void writeEndArray();

    public abstract void writeEndObject();

    public abstract void writeFieldName(SerializableString serializableString);

    public abstract void writeFieldName(String str);

    public abstract void writeNull();

    public abstract void writeNumber(double d);

    public abstract void writeNumber(float f);

    public abstract void writeNumber(int i);

    public abstract void writeNumber(long j);

    public abstract void writeNumber(String str);

    public abstract void writeNumber(BigDecimal bigDecimal);

    public abstract void writeNumber(BigInteger bigInteger);

    public abstract void writeObject(Object obj);

    public abstract void writeRawValue(String str);

    public abstract void writeStartArray();

    public abstract void writeStartObject();

    public abstract void writeString(SerializableString serializableString);

    public abstract void writeString(String str);

    public abstract void writeString(char[] cArr, int i, int i2);

    protected JsonGenerator() {
    }

    public boolean canWriteObjectId() {
        return false;
    }

    public boolean canWriteTypeId() {
        return false;
    }

    public boolean canWriteBinaryNatively() {
        return false;
    }

    public boolean canOmitFields() {
        return true;
    }

    public void writeBinary(byte[] bArr, int i, int i2) {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, i, i2);
    }

    public void writeBinary(byte[] bArr) {
        writeBinary(Base64Variants.getDefaultVariant(), bArr, 0, bArr.length);
    }

    public int writeBinary(InputStream inputStream, int i) {
        return writeBinary(Base64Variants.getDefaultVariant(), inputStream, i);
    }

    public void writeNumber(short s) {
        writeNumber((int) s);
    }

    public void writeObjectId(Object obj) {
        throw new JsonGenerationException("No native support for writing Object Ids");
    }

    public void writeObjectRef(Object obj) {
        throw new JsonGenerationException("No native support for writing Object Ids");
    }

    public void writeTypeId(Object obj) {
        throw new JsonGenerationException("No native support for writing Type Ids");
    }

    public void writeStringField(String str, String str2) {
        writeFieldName(str);
        writeString(str2);
    }

    public final void writeArrayFieldStart(String str) {
        writeFieldName(str);
        writeStartArray();
    }

    public final void writeObjectFieldStart(String str) {
        writeFieldName(str);
        writeStartObject();
    }

    public void writeOmittedField(String str) {
    }

    public void copyCurrentEvent(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            _reportError("No current event to copy");
        }
        NumberType numberType;
        switch (currentToken.id()) {
            case -1:
                _reportError("No current event to copy");
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                writeEndObject();
                return;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                writeStartArray();
                return;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                writeEndArray();
                return;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                writeFieldName(jsonParser.getCurrentName());
                return;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                if (jsonParser.hasTextCharacters()) {
                    writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                    return;
                } else {
                    writeString(jsonParser.getText());
                    return;
                }
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                numberType = jsonParser.getNumberType();
                if (numberType == NumberType.INT) {
                    writeNumber(jsonParser.getIntValue());
                    return;
                } else if (numberType == NumberType.BIG_INTEGER) {
                    writeNumber(jsonParser.getBigIntegerValue());
                    return;
                } else {
                    writeNumber(jsonParser.getLongValue());
                    return;
                }
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                numberType = jsonParser.getNumberType();
                if (numberType == NumberType.BIG_DECIMAL) {
                    writeNumber(jsonParser.getDecimalValue());
                    return;
                } else if (numberType == NumberType.FLOAT) {
                    writeNumber(jsonParser.getFloatValue());
                    return;
                } else {
                    writeNumber(jsonParser.getDoubleValue());
                    return;
                }
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                writeBoolean(true);
                return;
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                writeBoolean(false);
                return;
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                writeNull();
                return;
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                writeObject(jsonParser.getEmbeddedObject());
                return;
            default:
                _throwInternal();
                return;
        }
        writeStartObject();
    }

    public void copyCurrentStructure(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            _reportError("No current event to copy");
        }
        int id = currentToken.id();
        if (id == 5) {
            writeFieldName(jsonParser.getCurrentName());
            id = jsonParser.nextToken().id();
        }
        switch (id) {
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

    protected void _reportError(String str) {
        throw new JsonGenerationException(str);
    }

    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by generator of type " + getClass().getName());
    }
}
