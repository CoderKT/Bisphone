package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ParserBase extends ParserMinimalBase {
    static final BigDecimal BD_MAX_INT;
    static final BigDecimal BD_MAX_LONG;
    static final BigDecimal BD_MIN_INT;
    static final BigDecimal BD_MIN_LONG;
    static final BigInteger BI_MAX_INT;
    static final BigInteger BI_MAX_LONG;
    static final BigInteger BI_MIN_INT;
    static final BigInteger BI_MIN_LONG;
    protected byte[] _binaryValue;
    protected ByteArrayBuilder _byteArrayBuilder;
    protected boolean _closed;
    protected long _currInputProcessed;
    protected int _currInputRow;
    protected int _currInputRowStart;
    protected int _expLength;
    protected int _fractLength;
    protected int _inputEnd;
    protected int _inputPtr;
    protected int _intLength;
    protected final IOContext _ioContext;
    protected boolean _nameCopied;
    protected char[] _nameCopyBuffer;
    protected JsonToken _nextToken;
    protected int _numTypesValid;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    protected int _tokenInputCol;
    protected int _tokenInputRow;
    protected long _tokenInputTotal;

    protected abstract void _closeInput();

    protected abstract void _finishString();

    protected abstract boolean loadMore();

    static {
        BI_MIN_INT = BigInteger.valueOf(-2147483648L);
        BI_MAX_INT = BigInteger.valueOf(2147483647L);
        BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
        BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
        BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
        BD_MIN_INT = new BigDecimal(BI_MIN_INT);
        BD_MAX_INT = new BigDecimal(BI_MAX_INT);
    }

    protected ParserBase(IOContext iOContext, int i) {
        DupDetector dupDetector = null;
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._currInputProcessed = 0;
        this._currInputRow = 1;
        this._currInputRowStart = 0;
        this._tokenInputTotal = 0;
        this._tokenInputRow = 1;
        this._tokenInputCol = 0;
        this._nameCopyBuffer = null;
        this._nameCopied = false;
        this._byteArrayBuilder = null;
        this._numTypesValid = 0;
        this._features = i;
        this._ioContext = iOContext;
        this._textBuffer = iOContext.constructTextBuffer();
        if (Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i)) {
            dupDetector = DupDetector.rootDetector(this);
        }
        this._parsingContext = JsonReadContext.createRootContext(dupDetector);
    }

    public String getCurrentName() {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            return this._parsingContext.getParent().getCurrentName();
        }
        return this._parsingContext.getCurrentName();
    }

    public void close() {
        if (!this._closed) {
            this._closed = true;
            try {
                _closeInput();
            } finally {
                _releaseBuffers();
            }
        }
    }

    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), -1, getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
    }

    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), -1, this._currInputProcessed + ((long) this._inputPtr), this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return true;
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    public Object getEmbeddedObject() {
        return null;
    }

    public long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }

    public int getTokenLineNr() {
        return this._tokenInputRow;
    }

    public int getTokenColumnNr() {
        int i = this._tokenInputCol;
        return i < 0 ? i : i + 1;
    }

    protected final void loadMoreGuaranteed() {
        if (!loadMore()) {
            _reportInvalidEOF();
        }
    }

    protected void _releaseBuffers() {
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }

    protected void _handleEOF() {
        if (!this._parsingContext.inRoot()) {
            _reportInvalidEOF(": expected close marker for " + this._parsingContext.getTypeDesc() + " (from " + this._parsingContext.getStartLocation(this._ioContext.getSourceReference()) + ")");
        }
    }

    protected final int _eofAsNextChar() {
        _handleEOF();
        return -1;
    }

    protected void _reportMismatchedEndMarker(int i, char c) {
        _reportError("Unexpected close marker '" + ((char) i) + "': expected '" + c + "' (for " + this._parsingContext.getTypeDesc() + " starting at " + ("" + this._parsingContext.getStartLocation(this._ioContext.getSourceReference())) + ")");
    }

    public ByteArrayBuilder _getByteArrayBuilder() {
        if (this._byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            this._byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    protected final JsonToken reset(boolean z, int i, int i2, int i3) {
        if (i2 >= 1 || i3 >= 1) {
            return resetFloat(z, i, i2, i3);
        }
        return resetInt(z, i);
    }

    protected final JsonToken resetInt(boolean z, int i) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    protected final JsonToken resetFloat(boolean z, int i, int i2, int i3) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = i2;
        this._expLength = i3;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    protected final JsonToken resetAsNaN(String str, double d) {
        this._textBuffer.resetWithString(str);
        this._numberDouble = d;
        this._numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public Number getNumberValue() {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((this._numTypesValid & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((this._numTypesValid & 4) != 0) {
                return this._numberBigInt;
            }
            return this._numberBigDecimal;
        } else if ((this._numTypesValid & 16) != 0) {
            return this._numberBigDecimal;
        } else {
            if ((this._numTypesValid & 8) == 0) {
                _throwInternal();
            }
            return Double.valueOf(this._numberDouble);
        }
    }

    public NumberType getNumberType() {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 1) != 0) {
                return NumberType.INT;
            }
            if ((this._numTypesValid & 2) != 0) {
                return NumberType.LONG;
            }
            return NumberType.BIG_INTEGER;
        } else if ((this._numTypesValid & 16) != 0) {
            return NumberType.BIG_DECIMAL;
        } else {
            return NumberType.DOUBLE;
        }
    }

    public int getIntValue() {
        if ((this._numTypesValid & 1) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public long getLongValue() {
        if ((this._numTypesValid & 2) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    public BigInteger getBigIntegerValue() {
        if ((this._numTypesValid & 4) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    public float getFloatValue() {
        return (float) getDoubleValue();
    }

    public double getDoubleValue() {
        if ((this._numTypesValid & 8) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    public BigDecimal getDecimalValue() {
        if ((this._numTypesValid & 16) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    protected void _parseNumericValue(int i) {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            char[] textBuffer = this._textBuffer.getTextBuffer();
            int textOffset = this._textBuffer.getTextOffset();
            int i2 = this._intLength;
            if (this._numberNegative) {
                textOffset++;
            }
            if (i2 <= 9) {
                textOffset = NumberInput.parseInt(textBuffer, textOffset, i2);
                if (this._numberNegative) {
                    textOffset = -textOffset;
                }
                this._numberInt = textOffset;
                this._numTypesValid = 1;
            } else if (i2 <= 18) {
                long parseLong = NumberInput.parseLong(textBuffer, textOffset, i2);
                if (this._numberNegative) {
                    parseLong = -parseLong;
                }
                if (i2 == 10) {
                    if (this._numberNegative) {
                        if (parseLong >= -2147483648L) {
                            this._numberInt = (int) parseLong;
                            this._numTypesValid = 1;
                            return;
                        }
                    } else if (parseLong <= 2147483647L) {
                        this._numberInt = (int) parseLong;
                        this._numTypesValid = 1;
                        return;
                    }
                }
                this._numberLong = parseLong;
                this._numTypesValid = 2;
            } else {
                _parseSlowInt(i, textBuffer, textOffset, i2);
            }
        } else if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            _parseSlowFloat(i);
        } else {
            _reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
        }
    }

    private void _parseSlowFloat(int i) {
        if (i == 16) {
            try {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = 16;
                return;
            } catch (Throwable e) {
                _wrapError("Malformed numeric value '" + this._textBuffer.contentsAsString() + "'", e);
                return;
            }
        }
        this._numberDouble = this._textBuffer.contentsAsDouble();
        this._numTypesValid = 8;
    }

    private void _parseSlowInt(int i, char[] cArr, int i2, int i3) {
        String contentsAsString = this._textBuffer.contentsAsString();
        try {
            if (NumberInput.inLongRange(cArr, i2, i3, this._numberNegative)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = 2;
                return;
            }
            this._numberBigInt = new BigInteger(contentsAsString);
            this._numTypesValid = 4;
        } catch (Throwable e) {
            _wrapError("Malformed numeric value '" + contentsAsString + "'", e);
        }
    }

    protected void convertNumberToInt() {
        if ((this._numTypesValid & 2) != 0) {
            int i = (int) this._numberLong;
            if (((long) i) != this._numberLong) {
                _reportError("Numeric value (" + getText() + ") out of range of int");
            }
            this._numberInt = i;
        } else if ((this._numTypesValid & 4) != 0) {
            if (BI_MIN_INT.compareTo(this._numberBigInt) > 0 || BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        } else if ((this._numTypesValid & 8) != 0) {
            if (this._numberDouble < -2.147483648E9d || this._numberDouble > 2.147483647E9d) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }

    protected void convertNumberToLong() {
        if ((this._numTypesValid & 1) != 0) {
            this._numberLong = (long) this._numberInt;
        } else if ((this._numTypesValid & 4) != 0) {
            if (BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        } else if ((this._numTypesValid & 8) != 0) {
            if (this._numberDouble < -9.223372036854776E18d || this._numberDouble > 9.223372036854776E18d) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    protected void convertNumberToBigInteger() {
        if ((this._numTypesValid & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf((long) this._numberInt);
        } else if ((this._numTypesValid & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    protected void convertNumberToDouble() {
        if ((this._numTypesValid & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberDouble = (double) this._numberLong;
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberDouble = (double) this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 8;
    }

    protected void convertNumberToBigDecimal() {
        if ((this._numTypesValid & 8) != 0) {
            this._numberBigDecimal = NumberInput.parseBigDecimal(getText());
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf((long) this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 16;
    }

    protected void reportUnexpectedNumberChar(int i, String str) {
        String str2 = "Unexpected character (" + ParserMinimalBase._getCharDesc(i) + ") in numeric value";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    protected void reportInvalidNumber(String str) {
        _reportError("Invalid numeric value: " + str);
    }

    protected void reportOverflowInt() {
        _reportError("Numeric value (" + getText() + ") out of range of int (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
    }

    protected void reportOverflowLong() {
        _reportError("Numeric value (" + getText() + ") out of range of long (" + Long.MIN_VALUE + " - " + Long.MAX_VALUE + ")");
    }

    protected char _decodeEscaped() {
        throw new UnsupportedOperationException();
    }

    protected final int _decodeBase64Escape(Base64Variant base64Variant, char c, int i) {
        if (c != '\\') {
            throw reportInvalidBase64Char(base64Variant, c, i);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped <= ' ' && i == 0) {
            return -1;
        }
        int decodeBase64Char = base64Variant.decodeBase64Char(_decodeEscaped);
        if (decodeBase64Char >= 0) {
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i);
    }

    protected IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2) {
        return reportInvalidBase64Char(base64Variant, i, i2, null);
    }

    protected IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2, String str) {
        String str2;
        if (i <= 32) {
            str2 = "Illegal white space character (code 0x" + Integer.toHexString(i) + ") as character #" + (i2 + 1) + " of 4-char base64 unit: can only used between units";
        } else if (base64Variant.usesPaddingChar(i)) {
            str2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(i) || Character.isISOControl(i)) {
            str2 = "Illegal character (code 0x" + Integer.toHexString(i) + ") in base64 content";
        } else {
            str2 = "Illegal character '" + ((char) i) + "' (code 0x" + Integer.toHexString(i) + ") in base64 content";
        }
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        return new IllegalArgumentException(str2);
    }
}
