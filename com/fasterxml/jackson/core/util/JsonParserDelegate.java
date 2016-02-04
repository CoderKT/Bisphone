package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(JsonParser jsonParser) {
        this.delegate = jsonParser;
    }

    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    public boolean isEnabled(Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    public void close() {
        this.delegate.close();
    }

    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }

    public int getCurrentTokenId() {
        return this.delegate.getCurrentTokenId();
    }

    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }

    public String getCurrentName() {
        return this.delegate.getCurrentName();
    }

    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    public boolean isExpectedStartArrayToken() {
        return this.delegate.isExpectedStartArrayToken();
    }

    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
    }

    public String getText() {
        return this.delegate.getText();
    }

    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    public char[] getTextCharacters() {
        return this.delegate.getTextCharacters();
    }

    public int getTextLength() {
        return this.delegate.getTextLength();
    }

    public int getTextOffset() {
        return this.delegate.getTextOffset();
    }

    public BigInteger getBigIntegerValue() {
        return this.delegate.getBigIntegerValue();
    }

    public byte getByteValue() {
        return this.delegate.getByteValue();
    }

    public short getShortValue() {
        return this.delegate.getShortValue();
    }

    public BigDecimal getDecimalValue() {
        return this.delegate.getDecimalValue();
    }

    public double getDoubleValue() {
        return this.delegate.getDoubleValue();
    }

    public float getFloatValue() {
        return this.delegate.getFloatValue();
    }

    public int getIntValue() {
        return this.delegate.getIntValue();
    }

    public long getLongValue() {
        return this.delegate.getLongValue();
    }

    public NumberType getNumberType() {
        return this.delegate.getNumberType();
    }

    public Number getNumberValue() {
        return this.delegate.getNumberValue();
    }

    public int getValueAsInt() {
        return this.delegate.getValueAsInt();
    }

    public int getValueAsInt(int i) {
        return this.delegate.getValueAsInt(i);
    }

    public long getValueAsLong() {
        return this.delegate.getValueAsLong();
    }

    public long getValueAsLong(long j) {
        return this.delegate.getValueAsLong(j);
    }

    public String getValueAsString() {
        return this.delegate.getValueAsString();
    }

    public String getValueAsString(String str) {
        return this.delegate.getValueAsString(str);
    }

    public Object getEmbeddedObject() {
        return this.delegate.getEmbeddedObject();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) {
        return this.delegate.getBinaryValue(base64Variant);
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    public JsonToken nextToken() {
        return this.delegate.nextToken();
    }

    public JsonToken nextValue() {
        return this.delegate.nextValue();
    }

    public JsonParser skipChildren() {
        this.delegate.skipChildren();
        return this;
    }

    public boolean canReadObjectId() {
        return this.delegate.canReadObjectId();
    }

    public boolean canReadTypeId() {
        return this.delegate.canReadTypeId();
    }

    public Object getObjectId() {
        return this.delegate.getObjectId();
    }

    public Object getTypeId() {
        return this.delegate.getTypeId();
    }
}
