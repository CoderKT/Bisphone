package com.fasterxml.jackson.core.json;

import app.C0110R;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import se.emilsjolander.stickylistheaders.C1128R;

public class ReaderBasedJsonParser extends ParserBase {
    protected static final int[] _icLatin1;
    protected boolean _bufferRecyclable;
    protected final int _hashSeed;
    protected char[] _inputBuffer;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    static {
        _icLatin1 = CharTypes.getInputCodeLatin1();
    }

    public ReaderBasedJsonParser(IOContext iOContext, int i, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer, char[] cArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._tokenIncomplete = false;
        this._reader = reader;
        this._inputBuffer = cArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._objectCodec = objectCodec;
        this._symbols = charsToNameCanonicalizer;
        this._hashSeed = charsToNameCanonicalizer.hashSeed();
        this._bufferRecyclable = z;
    }

    public ReaderBasedJsonParser(IOContext iOContext, int i, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        super(iOContext, i);
        this._tokenIncomplete = false;
        this._reader = reader;
        this._inputBuffer = iOContext.allocTokenBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._objectCodec = objectCodec;
        this._symbols = charsToNameCanonicalizer;
        this._hashSeed = charsToNameCanonicalizer.hashSeed();
        this._bufferRecyclable = true;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    protected boolean loadMore() {
        this._currInputProcessed += (long) this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        if (this._reader == null) {
            return false;
        }
        int read = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
        if (read > 0) {
            this._inputPtr = 0;
            this._inputEnd = read;
            return true;
        }
        _closeInput();
        if (read != 0) {
            return false;
        }
        throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
    }

    protected char getNextChar(String str) {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(str);
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return cArr[i];
    }

    protected void _closeInput() {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    protected void _releaseBuffers() {
        super._releaseBuffers();
        this._symbols.release();
        if (this._bufferRecyclable) {
            char[] cArr = this._inputBuffer;
            if (cArr != null) {
                this._inputBuffer = null;
                this._ioContext.releaseTokenBuffer(cArr);
            }
        }
    }

    public final String getText() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return _getText2(jsonToken);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public final String getValueAsString() {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return super.getValueAsString(null);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public final String getValueAsString(String str) {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return super.getValueAsString(str);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken.id()) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return this._parsingContext.getCurrentName();
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return this._textBuffer.contentsAsString();
            default:
                return jsonToken.asString();
        }
    }

    public final char[] getTextCharacters() {
        if (this._currToken == null) {
            return null;
        }
        switch (this._currToken.id()) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                if (!this._nameCopied) {
                    String currentName = this._parsingContext.getCurrentName();
                    int length = currentName.length();
                    if (this._nameCopyBuffer == null) {
                        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                    } else if (this._nameCopyBuffer.length < length) {
                        this._nameCopyBuffer = new char[length];
                    }
                    currentName.getChars(0, length, this._nameCopyBuffer, 0);
                    this._nameCopied = true;
                }
                return this._nameCopyBuffer;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                break;
            default:
                return this._currToken.asCharArray();
        }
        return this._textBuffer.getTextBuffer();
    }

    public final int getTextLength() {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken.id()) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return this._parsingContext.getCurrentName().length();
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                break;
            default:
                return this._currToken.asCharArray().length;
        }
        return this._textBuffer.size();
    }

    public final int getTextOffset() {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken.id()) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                break;
            default:
                return 0;
        }
        return this._textBuffer.getTextOffset();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        if (this._tokenIncomplete && this._currToken == JsonToken.VALUE_STRING) {
            byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
            try {
                int _readBinary = _readBinary(base64Variant, outputStream, allocBase64Buffer);
                return _readBinary;
            } finally {
                this._ioContext.releaseBase64Buffer(allocBase64Buffer);
            }
        } else {
            byte[] binaryValue = getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int _readBinary(com.fasterxml.jackson.core.Base64Variant r12, java.io.OutputStream r13, byte[] r14) {
        /*
        r11 = this;
        r10 = 3;
        r9 = 34;
        r8 = -2;
        r1 = 0;
        r0 = r14.length;
        r5 = r0 + -3;
        r0 = r1;
        r2 = r1;
    L_0x000a:
        r3 = r11._inputPtr;
        r4 = r11._inputEnd;
        if (r3 < r4) goto L_0x0013;
    L_0x0010:
        r11.loadMoreGuaranteed();
    L_0x0013:
        r3 = r11._inputBuffer;
        r4 = r11._inputPtr;
        r6 = r4 + 1;
        r11._inputPtr = r6;
        r4 = r3[r4];
        r3 = 32;
        if (r4 <= r3) goto L_0x000a;
    L_0x0021:
        r3 = r12.decodeBase64Char(r4);
        if (r3 >= 0) goto L_0x0038;
    L_0x0027:
        if (r4 != r9) goto L_0x0032;
    L_0x0029:
        r11._tokenIncomplete = r1;
        if (r2 <= 0) goto L_0x0031;
    L_0x002d:
        r0 = r0 + r2;
        r13.write(r14, r1, r2);
    L_0x0031:
        return r0;
    L_0x0032:
        r3 = r11._decodeBase64Escape(r12, r4, r1);
        if (r3 < 0) goto L_0x000a;
    L_0x0038:
        r4 = r3;
        if (r2 <= r5) goto L_0x013b;
    L_0x003b:
        r0 = r0 + r2;
        r13.write(r14, r1, r2);
        r3 = r1;
    L_0x0040:
        r2 = r11._inputPtr;
        r6 = r11._inputEnd;
        if (r2 < r6) goto L_0x0049;
    L_0x0046:
        r11.loadMoreGuaranteed();
    L_0x0049:
        r2 = r11._inputBuffer;
        r6 = r11._inputPtr;
        r7 = r6 + 1;
        r11._inputPtr = r7;
        r6 = r2[r6];
        r2 = r12.decodeBase64Char(r6);
        if (r2 >= 0) goto L_0x005e;
    L_0x0059:
        r2 = 1;
        r2 = r11._decodeBase64Escape(r12, r6, r2);
    L_0x005e:
        r4 = r4 << 6;
        r4 = r4 | r2;
        r2 = r11._inputPtr;
        r6 = r11._inputEnd;
        if (r2 < r6) goto L_0x006a;
    L_0x0067:
        r11.loadMoreGuaranteed();
    L_0x006a:
        r2 = r11._inputBuffer;
        r6 = r11._inputPtr;
        r7 = r6 + 1;
        r11._inputPtr = r7;
        r6 = r2[r6];
        r2 = r12.decodeBase64Char(r6);
        if (r2 >= 0) goto L_0x00d7;
    L_0x007a:
        if (r2 == r8) goto L_0x0091;
    L_0x007c:
        if (r6 != r9) goto L_0x008c;
    L_0x007e:
        r2 = r12.usesPadding();
        if (r2 != 0) goto L_0x008c;
    L_0x0084:
        r4 = r4 >> 4;
        r2 = r3 + 1;
        r4 = (byte) r4;
        r14[r3] = r4;
        goto L_0x0029;
    L_0x008c:
        r2 = 2;
        r2 = r11._decodeBase64Escape(r12, r6, r2);
    L_0x0091:
        if (r2 != r8) goto L_0x00d7;
    L_0x0093:
        r2 = r11._inputPtr;
        r6 = r11._inputEnd;
        if (r2 < r6) goto L_0x009c;
    L_0x0099:
        r11.loadMoreGuaranteed();
    L_0x009c:
        r2 = r11._inputBuffer;
        r6 = r11._inputPtr;
        r7 = r6 + 1;
        r11._inputPtr = r7;
        r2 = r2[r6];
        r6 = r12.usesPaddingChar(r2);
        if (r6 != 0) goto L_0x00ce;
    L_0x00ac:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "expected padding character '";
        r0 = r0.append(r1);
        r1 = r12.getPaddingChar();
        r0 = r0.append(r1);
        r1 = "'";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r0 = r11.reportInvalidBase64Char(r12, r2, r10, r0);
        throw r0;
    L_0x00ce:
        r4 = r4 >> 4;
        r2 = r3 + 1;
        r4 = (byte) r4;
        r14[r3] = r4;
        goto L_0x000a;
    L_0x00d7:
        r4 = r4 << 6;
        r4 = r4 | r2;
        r2 = r11._inputPtr;
        r6 = r11._inputEnd;
        if (r2 < r6) goto L_0x00e3;
    L_0x00e0:
        r11.loadMoreGuaranteed();
    L_0x00e3:
        r2 = r11._inputBuffer;
        r6 = r11._inputPtr;
        r7 = r6 + 1;
        r11._inputPtr = r7;
        r6 = r2[r6];
        r2 = r12.decodeBase64Char(r6);
        if (r2 >= 0) goto L_0x0123;
    L_0x00f3:
        if (r2 == r8) goto L_0x0111;
    L_0x00f5:
        if (r6 != r9) goto L_0x010d;
    L_0x00f7:
        r2 = r12.usesPadding();
        if (r2 != 0) goto L_0x010d;
    L_0x00fd:
        r4 = r4 >> 2;
        r5 = r3 + 1;
        r2 = r4 >> 8;
        r2 = (byte) r2;
        r14[r3] = r2;
        r2 = r5 + 1;
        r3 = (byte) r4;
        r14[r5] = r3;
        goto L_0x0029;
    L_0x010d:
        r2 = r11._decodeBase64Escape(r12, r6, r10);
    L_0x0111:
        if (r2 != r8) goto L_0x0123;
    L_0x0113:
        r4 = r4 >> 2;
        r6 = r3 + 1;
        r2 = r4 >> 8;
        r2 = (byte) r2;
        r14[r3] = r2;
        r2 = r6 + 1;
        r3 = (byte) r4;
        r14[r6] = r3;
        goto L_0x000a;
    L_0x0123:
        r4 = r4 << 6;
        r4 = r4 | r2;
        r2 = r3 + 1;
        r6 = r4 >> 16;
        r6 = (byte) r6;
        r14[r3] = r6;
        r3 = r2 + 1;
        r6 = r4 >> 8;
        r6 = (byte) r6;
        r14[r2] = r6;
        r2 = r3 + 1;
        r4 = (byte) r4;
        r14[r3] = r4;
        goto L_0x000a;
    L_0x013b:
        r3 = r2;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._readBinary(com.fasterxml.jackson.core.Base64Variant, java.io.OutputStream, byte[]):int");
    }

    public final JsonToken nextToken() {
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + ((long) this._inputPtr)) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        JsonToken jsonToken;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            jsonToken = JsonToken.END_OBJECT;
            this._currToken = jsonToken;
            return jsonToken;
        } else {
            if (this._parsingContext.expectComma()) {
                _skipWSOrEnd = _skipComma(_skipWSOrEnd);
            }
            boolean inObject = this._parsingContext.inObject();
            if (inObject) {
                this._parsingContext.setCurrentName(_skipWSOrEnd == 34 ? _parseName() : _handleOddName(_skipWSOrEnd));
                this._currToken = JsonToken.FIELD_NAME;
                _skipWSOrEnd = _skipColon();
            }
            switch (_skipWSOrEnd) {
                case C0110R.styleable.Theme_actionModePasteDrawable /*34*/:
                    this._tokenIncomplete = true;
                    jsonToken = JsonToken.VALUE_STRING;
                    break;
                case C0110R.styleable.Theme_actionDropDownStyle /*45*/:
                    jsonToken = _parseNegNumber();
                    break;
                case C0110R.styleable.Theme_homeAsUpIndicator /*48*/:
                case C0110R.styleable.Theme_actionButtonStyle /*49*/:
                case C0110R.styleable.Theme_buttonBarStyle /*50*/:
                case C0110R.styleable.Theme_buttonBarButtonStyle /*51*/:
                case C0110R.styleable.Theme_selectableItemBackground /*52*/:
                case C0110R.styleable.Theme_selectableItemBackgroundBorderless /*53*/:
                case C0110R.styleable.Theme_borderlessButtonStyle /*54*/:
                case C0110R.styleable.Theme_dividerVertical /*55*/:
                case C0110R.styleable.Theme_dividerHorizontal /*56*/:
                case C0110R.styleable.Theme_activityChooserViewStyle /*57*/:
                    jsonToken = _parsePosNumber(_skipWSOrEnd);
                    break;
                case C0110R.styleable.Theme_alertDialogStyle /*91*/:
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    jsonToken = JsonToken.START_ARRAY;
                    break;
                case C0110R.styleable.Theme_alertDialogCenterButtons /*93*/:
                case 125:
                    _reportUnexpectedChar(_skipWSOrEnd, "expected a value");
                    break;
                case C0110R.styleable.Theme_checkboxStyle /*102*/:
                    _matchFalse();
                    jsonToken = JsonToken.VALUE_FALSE;
                    break;
                case 110:
                    _matchNull();
                    jsonToken = JsonToken.VALUE_NULL;
                    break;
                case 116:
                    break;
                case 123:
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    jsonToken = JsonToken.START_OBJECT;
                    break;
                default:
                    jsonToken = _handleOddValue(_skipWSOrEnd);
                    break;
            }
            _matchTrue();
            jsonToken = JsonToken.VALUE_TRUE;
            if (inObject) {
                this._nextToken = jsonToken;
                return this._currToken;
            }
            this._currToken = jsonToken;
            return jsonToken;
        }
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _parsePosNumber(int i) {
        int i2 = this._inputPtr;
        int i3 = i2 - 1;
        int i4 = this._inputEnd;
        if (i == 48) {
            return _parseNumber2(false, i3);
        }
        int i5 = 1;
        int i6 = i2;
        while (i6 < i4) {
            i2 = i6 + 1;
            char c = this._inputBuffer[i6];
            if (c >= '0' && c <= '9') {
                i5++;
                i6 = i2;
            } else if (c == '.' || c == 'e' || c == 'E') {
                this._inputPtr = i2;
                return _parseFloat(c, i3, i2, false, i5);
            } else {
                i6 = i2 - 1;
                this._inputPtr = i6;
                if (this._parsingContext.inRoot()) {
                    _verifyRootSpace(c);
                }
                this._textBuffer.resetWithShared(this._inputBuffer, i3, i6 - i3);
                return resetInt(false, i5);
            }
        }
        this._inputPtr = i3;
        return _parseNumber2(false, i3);
    }

    private final JsonToken _parseFloat(int i, int i2, int i3, boolean z, int i4) {
        int i5;
        int i6;
        int i7;
        int i8 = 0;
        int i9 = this._inputEnd;
        if (i == 46) {
            i5 = 0;
            i6 = i3;
            while (i6 < i9) {
                i3 = i6 + 1;
                i6 = this._inputBuffer[i6];
                if (i6 < 48 || i6 > 57) {
                    if (i5 == 0) {
                        reportUnexpectedNumberChar(i6, "Decimal point not followed by a digit");
                    }
                    i7 = i5;
                    i5 = i3;
                } else {
                    i5++;
                    i6 = i3;
                }
            }
            return _parseNumber2(z, i2);
        }
        i7 = 0;
        i5 = i3;
        i6 = i;
        if (i6 == C0110R.styleable.Theme_buttonStyleSmall || i6 == 69) {
            if (i5 >= i9) {
                this._inputPtr = i2;
                return _parseNumber2(z, i2);
            }
            int i10;
            char c;
            i6 = i5 + 1;
            char c2 = this._inputBuffer[i5];
            if (c2 != '-' && c2 != '+') {
                i10 = i6;
                c = c2;
                i5 = i10;
            } else if (i6 >= i9) {
                this._inputPtr = i2;
                return _parseNumber2(z, i2);
            } else {
                i5 = i6 + 1;
                i6 = this._inputBuffer[i6];
            }
            while (i6 <= 57 && i6 >= 48) {
                i8++;
                if (i5 >= i9) {
                    this._inputPtr = i2;
                    return _parseNumber2(z, i2);
                }
                i10 = i5 + 1;
                c = this._inputBuffer[i5];
                i5 = i10;
            }
            if (i8 == 0) {
                reportUnexpectedNumberChar(i6, "Exponent indicator not followed by a digit");
            }
        }
        i5--;
        this._inputPtr = i5;
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace(i6);
        }
        this._textBuffer.resetWithShared(this._inputBuffer, i2, i5 - i2);
        return resetFloat(z, i4, i7, i8);
    }

    protected final JsonToken _parseNegNumber() {
        int i = this._inputPtr;
        int i2 = i - 1;
        int i3 = this._inputEnd;
        if (i >= i3) {
            return _parseNumber2(true, i2);
        }
        int i4 = i + 1;
        char c = this._inputBuffer[i];
        if (c > '9' || c < '0') {
            this._inputPtr = i4;
            return _handleInvalidNumberStart(c, true);
        } else if (c == '0') {
            return _parseNumber2(true, i2);
        } else {
            int i5 = 1;
            i = i4;
            while (i < i3) {
                i4 = i + 1;
                char c2 = this._inputBuffer[i];
                if (c2 >= '0' && c2 <= '9') {
                    i5++;
                    i = i4;
                } else if (c2 == '.' || c2 == 'e' || c2 == 'E') {
                    this._inputPtr = i4;
                    return _parseFloat(c2, i2, i4, true, i5);
                } else {
                    i = i4 - 1;
                    this._inputPtr = i;
                    if (this._parsingContext.inRoot()) {
                        _verifyRootSpace(c2);
                    }
                    this._textBuffer.resetWithShared(this._inputBuffer, i2, i - i2);
                    return resetInt(true, i5);
                }
            }
            return _parseNumber2(true, i2);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.fasterxml.jackson.core.JsonToken _parseNumber2(boolean r11, int r12) {
        /*
        r10 = this;
        if (r11 == 0) goto L_0x0004;
    L_0x0002:
        r12 = r12 + 1;
    L_0x0004:
        r10._inputPtr = r12;
        r0 = r10._textBuffer;
        r2 = r0.emptyAndGetCurrentSegment();
        r1 = 0;
        if (r11 == 0) goto L_0x01bc;
    L_0x000f:
        r0 = 1;
        r3 = 45;
        r2[r1] = r3;
    L_0x0014:
        r3 = 0;
        r1 = r10._inputPtr;
        r4 = r10._inputEnd;
        if (r1 >= r4) goto L_0x013a;
    L_0x001b:
        r1 = r10._inputBuffer;
        r4 = r10._inputPtr;
        r5 = r4 + 1;
        r10._inputPtr = r5;
        r1 = r1[r4];
    L_0x0025:
        r4 = 48;
        if (r1 != r4) goto L_0x002d;
    L_0x0029:
        r1 = r10._verifyNoLeadingZeroes();
    L_0x002d:
        r5 = 0;
        r9 = r1;
        r1 = r2;
        r2 = r9;
    L_0x0031:
        r4 = 48;
        if (r2 < r4) goto L_0x01b6;
    L_0x0035:
        r4 = 57;
        if (r2 > r4) goto L_0x01b6;
    L_0x0039:
        r3 = r3 + 1;
        r4 = r1.length;
        if (r0 < r4) goto L_0x0045;
    L_0x003e:
        r0 = r10._textBuffer;
        r1 = r0.finishCurrentSegment();
        r0 = 0;
    L_0x0045:
        r4 = r0 + 1;
        r1[r0] = r2;
        r0 = r10._inputPtr;
        r2 = r10._inputEnd;
        if (r0 < r2) goto L_0x0142;
    L_0x004f:
        r0 = r10.loadMore();
        if (r0 != 0) goto L_0x0142;
    L_0x0055:
        r0 = 0;
        r5 = 1;
        r7 = r3;
        r3 = r1;
        r1 = r0;
    L_0x005a:
        if (r7 != 0) goto L_0x007c;
    L_0x005c:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "Missing integer part (next char ";
        r0 = r0.append(r2);
        r2 = com.fasterxml.jackson.core.base.ParserMinimalBase._getCharDesc(r1);
        r0 = r0.append(r2);
        r2 = ")";
        r0 = r0.append(r2);
        r0 = r0.toString();
        r10.reportInvalidNumber(r0);
    L_0x007c:
        r0 = 0;
        r2 = 46;
        if (r1 != r2) goto L_0x01af;
    L_0x0081:
        r2 = r4 + 1;
        r3[r4] = r1;
    L_0x0085:
        r4 = r10._inputPtr;
        r6 = r10._inputEnd;
        if (r4 < r6) goto L_0x014f;
    L_0x008b:
        r4 = r10.loadMore();
        if (r4 != 0) goto L_0x014f;
    L_0x0091:
        r5 = 1;
        r4 = r5;
        r5 = r1;
    L_0x0094:
        if (r0 != 0) goto L_0x009b;
    L_0x0096:
        r1 = "Decimal point not followed by a digit";
        r10.reportUnexpectedNumberChar(r5, r1);
    L_0x009b:
        r6 = r0;
        r1 = r3;
        r0 = r2;
    L_0x009e:
        r3 = 0;
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r5 == r2) goto L_0x00a7;
    L_0x00a3:
        r2 = 69;
        if (r5 != r2) goto L_0x01a2;
    L_0x00a7:
        r2 = r1.length;
        if (r0 < r2) goto L_0x00b1;
    L_0x00aa:
        r0 = r10._textBuffer;
        r1 = r0.finishCurrentSegment();
        r0 = 0;
    L_0x00b1:
        r2 = r0 + 1;
        r1[r0] = r5;
        r0 = r10._inputPtr;
        r5 = r10._inputEnd;
        if (r0 >= r5) goto L_0x0178;
    L_0x00bb:
        r0 = r10._inputBuffer;
        r5 = r10._inputPtr;
        r8 = r5 + 1;
        r10._inputPtr = r8;
        r5 = r0[r5];
    L_0x00c5:
        r0 = 45;
        if (r5 == r0) goto L_0x00cd;
    L_0x00c9:
        r0 = 43;
        if (r5 != r0) goto L_0x019e;
    L_0x00cd:
        r0 = r1.length;
        if (r2 < r0) goto L_0x019b;
    L_0x00d0:
        r0 = r10._textBuffer;
        r1 = r0.finishCurrentSegment();
        r0 = 0;
    L_0x00d7:
        r2 = r0 + 1;
        r1[r0] = r5;
        r0 = r10._inputPtr;
        r5 = r10._inputEnd;
        if (r0 >= r5) goto L_0x0180;
    L_0x00e1:
        r0 = r10._inputBuffer;
        r5 = r10._inputPtr;
        r8 = r5 + 1;
        r10._inputPtr = r8;
        r0 = r0[r5];
    L_0x00eb:
        r9 = r0;
        r0 = r2;
        r2 = r9;
    L_0x00ee:
        r5 = 57;
        if (r2 > r5) goto L_0x0195;
    L_0x00f2:
        r5 = 48;
        if (r2 < r5) goto L_0x0195;
    L_0x00f6:
        r3 = r3 + 1;
        r5 = r1.length;
        if (r0 < r5) goto L_0x0102;
    L_0x00fb:
        r0 = r10._textBuffer;
        r1 = r0.finishCurrentSegment();
        r0 = 0;
    L_0x0102:
        r5 = r0 + 1;
        r1[r0] = r2;
        r0 = r10._inputPtr;
        r8 = r10._inputEnd;
        if (r0 < r8) goto L_0x0188;
    L_0x010c:
        r0 = r10.loadMore();
        if (r0 != 0) goto L_0x0188;
    L_0x0112:
        r4 = 1;
        r0 = r3;
        r1 = r4;
        r3 = r5;
    L_0x0116:
        if (r0 != 0) goto L_0x011d;
    L_0x0118:
        r4 = "Exponent indicator not followed by a digit";
        r10.reportUnexpectedNumberChar(r2, r4);
    L_0x011d:
        if (r1 != 0) goto L_0x0130;
    L_0x011f:
        r1 = r10._inputPtr;
        r1 = r1 + -1;
        r10._inputPtr = r1;
        r1 = r10._parsingContext;
        r1 = r1.inRoot();
        if (r1 == 0) goto L_0x0130;
    L_0x012d:
        r10._verifyRootSpace(r2);
    L_0x0130:
        r1 = r10._textBuffer;
        r1.setCurrentLength(r3);
        r0 = r10.reset(r11, r7, r6, r0);
        return r0;
    L_0x013a:
        r1 = "No digit following minus sign";
        r1 = r10.getNextChar(r1);
        goto L_0x0025;
    L_0x0142:
        r0 = r10._inputBuffer;
        r2 = r10._inputPtr;
        r6 = r2 + 1;
        r10._inputPtr = r6;
        r2 = r0[r2];
        r0 = r4;
        goto L_0x0031;
    L_0x014f:
        r1 = r10._inputBuffer;
        r4 = r10._inputPtr;
        r6 = r4 + 1;
        r10._inputPtr = r6;
        r1 = r1[r4];
        r4 = 48;
        if (r1 < r4) goto L_0x01ab;
    L_0x015d:
        r4 = 57;
        if (r1 <= r4) goto L_0x0165;
    L_0x0161:
        r4 = r5;
        r5 = r1;
        goto L_0x0094;
    L_0x0165:
        r0 = r0 + 1;
        r4 = r3.length;
        if (r2 < r4) goto L_0x01a9;
    L_0x016a:
        r2 = r10._textBuffer;
        r3 = r2.finishCurrentSegment();
        r2 = 0;
        r4 = r2;
    L_0x0172:
        r2 = r4 + 1;
        r3[r4] = r1;
        goto L_0x0085;
    L_0x0178:
        r0 = "expected a digit for number exponent";
        r5 = r10.getNextChar(r0);
        goto L_0x00c5;
    L_0x0180:
        r0 = "expected a digit for number exponent";
        r0 = r10.getNextChar(r0);
        goto L_0x00eb;
    L_0x0188:
        r0 = r10._inputBuffer;
        r2 = r10._inputPtr;
        r8 = r2 + 1;
        r10._inputPtr = r8;
        r2 = r0[r2];
        r0 = r5;
        goto L_0x00ee;
    L_0x0195:
        r1 = r4;
        r9 = r3;
        r3 = r0;
        r0 = r9;
        goto L_0x0116;
    L_0x019b:
        r0 = r2;
        goto L_0x00d7;
    L_0x019e:
        r0 = r2;
        r2 = r5;
        goto L_0x00ee;
    L_0x01a2:
        r1 = r4;
        r2 = r5;
        r9 = r3;
        r3 = r0;
        r0 = r9;
        goto L_0x011d;
    L_0x01a9:
        r4 = r2;
        goto L_0x0172;
    L_0x01ab:
        r4 = r5;
        r5 = r1;
        goto L_0x0094;
    L_0x01af:
        r6 = r0;
        r0 = r4;
        r4 = r5;
        r5 = r1;
        r1 = r3;
        goto L_0x009e;
    L_0x01b6:
        r7 = r3;
        r4 = r0;
        r3 = r1;
        r1 = r2;
        goto L_0x005a;
    L_0x01bc:
        r0 = r1;
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._parseNumber2(boolean, int):com.fasterxml.jackson.core.JsonToken");
    }

    private final char _verifyNoLeadingZeroes() {
        if (this._inputPtr < this._inputEnd) {
            char c = this._inputBuffer[this._inputPtr];
            if (c < '0' || c > '9') {
                return '0';
            }
        }
        return _verifyNLZ2();
    }

    private char _verifyNLZ2() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return '0';
        }
        char c = this._inputBuffer[this._inputPtr];
        if (c < '0' || c > '9') {
            return '0';
        }
        if (!isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (c != '0') {
            return c;
        }
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return c;
            }
            c = this._inputBuffer[this._inputPtr];
            if (c < '0' || c > '9') {
                return '0';
            }
            this._inputPtr++;
        } while (c == '0');
        return c;
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) {
        double d = Double.NEGATIVE_INFINITY;
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            char[] cArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = cArr[i2];
            String str;
            if (i == 78) {
                str = z ? "-INF" : "+INF";
                _matchToken(str, 3);
                if (isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (!z) {
                        d = Double.POSITIVE_INFINITY;
                    }
                    return resetAsNaN(str, d);
                }
                _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (i == 110) {
                str = z ? "-Infinity" : "+Infinity";
                _matchToken(str, 3);
                if (isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (!z) {
                        d = Double.POSITIVE_INFINITY;
                    }
                    return resetAsNaN(str, d);
                }
                _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    private final void _verifyRootSpace(int i) {
        this._inputPtr++;
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
            case C0110R.styleable.Theme_actionModeCutDrawable /*32*/:
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
            case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                _skipCR();
            default:
                _reportMissingRootWS(i);
        }
    }

    protected final String _parseName() {
        int i;
        int i2 = this._inputPtr;
        int i3 = this._hashSeed;
        int[] iArr = _icLatin1;
        while (i2 < this._inputEnd) {
            char c = this._inputBuffer[i2];
            if (c >= iArr.length || iArr[c] == 0) {
                i3 = (i3 * 33) + c;
                i2++;
            } else {
                if (c == '\"') {
                    i = this._inputPtr;
                    this._inputPtr = i2 + 1;
                    return this._symbols.findSymbol(this._inputBuffer, i, i2 - i, i3);
                }
                i = this._inputPtr;
                this._inputPtr = i2;
                return _parseName2(i, i3, 34);
            }
        }
        i = this._inputPtr;
        this._inputPtr = i2;
        return _parseName2(i, i3, 34);
    }

    private String _parseName2(int i, int i2, int i3) {
        this._textBuffer.resetWithShared(this._inputBuffer, i, this._inputPtr - i);
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            char _decodeEscaped;
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing '" + ((char) i3) + "' for name");
            }
            char[] cArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            char c = cArr[i4];
            if (c <= '\\') {
                if (c == '\\') {
                    _decodeEscaped = _decodeEscaped();
                    i2 = (i2 * 33) + c;
                    i4 = currentSegmentSize + 1;
                    currentSegment[currentSegmentSize] = _decodeEscaped;
                    if (i4 < currentSegment.length) {
                        currentSegment = this._textBuffer.finishCurrentSegment();
                        currentSegmentSize = 0;
                    } else {
                        currentSegmentSize = i4;
                    }
                } else if (c <= i3) {
                    if (c == i3) {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        TextBuffer textBuffer = this._textBuffer;
                        return this._symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i2);
                    } else if (c < ' ') {
                        _throwUnquotedSpace(c, "name");
                    }
                }
            }
            _decodeEscaped = c;
            i2 = (i2 * 33) + c;
            i4 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = _decodeEscaped;
            if (i4 < currentSegment.length) {
                currentSegmentSize = i4;
            } else {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
        }
    }

    protected String _handleOddName(int i) {
        if (i == 39 && isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseAposName();
        }
        if (!isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
        char length = inputCodeLatin1JsNames.length;
        boolean isJavaIdentifierPart = i < length ? inputCodeLatin1JsNames[i] == 0 : Character.isJavaIdentifierPart((char) i);
        if (!isJavaIdentifierPart) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i2 = this._inputPtr;
        int i3 = this._hashSeed;
        int i4 = this._inputEnd;
        if (i2 < i4) {
            do {
                char c = this._inputBuffer[i2];
                int i5;
                if (c < length) {
                    if (inputCodeLatin1JsNames[c] != 0) {
                        i5 = this._inputPtr - 1;
                        this._inputPtr = i2;
                        return this._symbols.findSymbol(this._inputBuffer, i5, i2 - i5, i3);
                    }
                } else if (!Character.isJavaIdentifierPart((char) c)) {
                    i5 = this._inputPtr - 1;
                    this._inputPtr = i2;
                    return this._symbols.findSymbol(this._inputBuffer, i5, i2 - i5, i3);
                }
                i3 = (i3 * 33) + c;
                i2++;
            } while (i2 < i4);
        }
        int i6 = this._inputPtr - 1;
        this._inputPtr = i2;
        return _handleOddName2(i6, i3, inputCodeLatin1JsNames);
    }

    protected String _parseAposName() {
        int i = this._inputPtr;
        int i2 = this._hashSeed;
        int i3 = this._inputEnd;
        if (i < i3) {
            int[] iArr = _icLatin1;
            char length = iArr.length;
            do {
                char c = this._inputBuffer[i];
                if (c != '\'') {
                    if (c < length && iArr[c] != 0) {
                        break;
                    }
                    i2 = (i2 * 33) + c;
                    i++;
                } else {
                    i3 = this._inputPtr;
                    this._inputPtr = i + 1;
                    return this._symbols.findSymbol(this._inputBuffer, i3, i - i3, i2);
                }
            } while (i < i3);
        }
        i3 = this._inputPtr;
        this._inputPtr = i;
        return _parseName2(i3, i2, 39);
    }

    protected JsonToken _handleOddValue(int i) {
        switch (i) {
            case C0110R.styleable.Theme_actionModePopupWindowStyle /*39*/:
                if (isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
                    return _handleApos();
                }
                break;
            case C0110R.styleable.Theme_dialogPreferredPadding /*43*/:
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                char[] cArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(cArr[i2], false);
            case C0110R.styleable.Theme_listPreferredItemPaddingRight /*73*/:
                _matchToken("Infinity", 1);
                if (!isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    _reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                    break;
                }
                return resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
            case C0110R.styleable.Theme_panelBackground /*78*/:
                _matchToken("NaN", 1);
                if (!isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                    break;
                }
                return resetAsNaN("NaN", Double.NaN);
        }
        if (Character.isJavaIdentifierStart(i)) {
            _reportInvalidToken("" + ((char) i), "('true', 'false' or 'null')");
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected JsonToken _handleApos() {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = _decodeEscaped();
                } else if (c <= '\'') {
                    if (c == '\'') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    } else if (c < ' ') {
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            if (currentSegmentSize >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i = 0;
            } else {
                i = currentSegmentSize;
            }
            currentSegmentSize = i + 1;
            emptyAndGetCurrentSegment[i] = c;
        }
    }

    private String _handleOddName2(int i, int i2, int[] iArr) {
        this._textBuffer.resetWithShared(this._inputBuffer, i, this._inputPtr - i);
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        char length = iArr.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (c > length) {
                if (!Character.isJavaIdentifierPart(c)) {
                    break;
                }
            } else if (iArr[c] != 0) {
                break;
            }
            this._inputPtr++;
            i2 = (i2 * 33) + c;
            int i3 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = c;
            if (i3 >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            } else {
                currentSegmentSize = i3;
            }
        }
        this._textBuffer.setCurrentLength(currentSegmentSize);
        TextBuffer textBuffer = this._textBuffer;
        return this._symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i2);
    }

    protected final void _finishString() {
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        if (i < i2) {
            int[] iArr = _icLatin1;
            char length = iArr.length;
            do {
                char c = this._inputBuffer[i];
                if (c >= length || iArr[c] == 0) {
                    i++;
                } else if (c == '\"') {
                    this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, i - this._inputPtr);
                    this._inputPtr = i + 1;
                    return;
                }
            } while (i < i2);
        }
        this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, i - this._inputPtr);
        this._inputPtr = i;
        _finishString2();
    }

    protected void _finishString2() {
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        int[] iArr = _icLatin1;
        char length = iArr.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c < length && iArr[c] != 0) {
                if (c == '\"') {
                    this._textBuffer.setCurrentLength(currentSegmentSize);
                    return;
                } else if (c == '\\') {
                    c = _decodeEscaped();
                } else if (c < ' ') {
                    _throwUnquotedSpace(c, "string value");
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                i = 0;
            } else {
                i = currentSegmentSize;
            }
            currentSegmentSize = i + 1;
            currentSegment[i] = c;
        }
    }

    protected final void _skipString() {
        this._tokenIncomplete = false;
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        char[] cArr = this._inputBuffer;
        while (true) {
            if (i >= i2) {
                this._inputPtr = i;
                if (!loadMore()) {
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                }
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    this._inputPtr = i3;
                    _decodeEscaped();
                    i = this._inputPtr;
                    i2 = this._inputEnd;
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this._inputPtr = i3;
                        return;
                    } else if (c < ' ') {
                        this._inputPtr = i3;
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            i = i3;
        }
    }

    protected final void _skipCR() {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == '\n') {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private final int _skipColon() {
        if (this._inputPtr + 4 >= this._inputEnd) {
            return _skipColon2(false);
        }
        char c = this._inputBuffer[this._inputPtr];
        char[] cArr;
        int i;
        int i2;
        if (c == ':') {
            cArr = this._inputBuffer;
            i = this._inputPtr + 1;
            this._inputPtr = i;
            i2 = cArr[i];
            if (i2 <= 32) {
                if (i2 == 32 || i2 == 9) {
                    cArr = this._inputBuffer;
                    i = this._inputPtr + 1;
                    this._inputPtr = i;
                    i2 = cArr[i];
                    if (i2 > 32) {
                        if (i2 == 47 || i2 == 35) {
                            return _skipColon2(true);
                        }
                        this._inputPtr++;
                        return i2;
                    }
                }
                return _skipColon2(true);
            } else if (i2 == 47 || i2 == 35) {
                return _skipColon2(true);
            } else {
                this._inputPtr++;
                return i2;
            }
        }
        if (c == ' ' || c == '\t') {
            cArr = this._inputBuffer;
            i = this._inputPtr + 1;
            this._inputPtr = i;
            c = cArr[i];
        }
        if (c != ':') {
            return _skipColon2(false);
        }
        cArr = this._inputBuffer;
        i = this._inputPtr + 1;
        this._inputPtr = i;
        i2 = cArr[i];
        if (i2 <= 32) {
            if (i2 == 32 || i2 == 9) {
                cArr = this._inputBuffer;
                i = this._inputPtr + 1;
                this._inputPtr = i;
                i2 = cArr[i];
                if (i2 > 32) {
                    if (i2 == 47 || i2 == 35) {
                        return _skipColon2(true);
                    }
                    this._inputPtr++;
                    return i2;
                }
            }
            return _skipColon2(true);
        } else if (i2 == 47 || i2 == 35) {
            return _skipColon2(true);
        } else {
            this._inputPtr++;
            return i2;
        }
    }

    private final int _skipColon2(boolean z) {
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                if (c == '/') {
                    _skipComment();
                } else if (c != '#' || !_skipYAMLComment()) {
                    if (z) {
                        return c;
                    }
                    if (c != ':') {
                        if (c < ' ') {
                            _throwInvalidSpace(c);
                        }
                        _reportUnexpectedChar(c, "was expecting a colon to separate field name and value");
                    }
                    z = true;
                }
            } else if (c < ' ') {
                if (c == '\n') {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (c == '\r') {
                    _skipCR();
                } else if (c != '\t') {
                    _throwInvalidSpace(c);
                }
            }
        }
    }

    private final int _skipComma(int i) {
        if (i != 44) {
            _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
        }
        while (this._inputPtr < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            int i3 = cArr[i2];
            if (i3 > 32) {
                if (i3 != 47 && i3 != 35) {
                    return i3;
                }
                this._inputPtr--;
                return _skipAfterComma2();
            } else if (i3 < 32) {
                if (i3 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i3 == 13) {
                    _skipCR();
                } else if (i3 != 9) {
                    _throwInvalidSpace(i3);
                }
            }
        }
        return _skipAfterComma2();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int _skipAfterComma2() {
        /*
        r4 = this;
        r3 = 32;
    L_0x0002:
        r0 = r4._inputPtr;
        r1 = r4._inputEnd;
        if (r0 < r1) goto L_0x000e;
    L_0x0008:
        r0 = r4.loadMore();
        if (r0 == 0) goto L_0x004e;
    L_0x000e:
        r0 = r4._inputBuffer;
        r1 = r4._inputPtr;
        r2 = r1 + 1;
        r4._inputPtr = r2;
        r0 = r0[r1];
        if (r0 <= r3) goto L_0x002d;
    L_0x001a:
        r1 = 47;
        if (r0 != r1) goto L_0x0022;
    L_0x001e:
        r4._skipComment();
        goto L_0x0002;
    L_0x0022:
        r1 = 35;
        if (r0 != r1) goto L_0x002c;
    L_0x0026:
        r1 = r4._skipYAMLComment();
        if (r1 != 0) goto L_0x0002;
    L_0x002c:
        return r0;
    L_0x002d:
        if (r0 >= r3) goto L_0x0002;
    L_0x002f:
        r1 = 10;
        if (r0 != r1) goto L_0x003e;
    L_0x0033:
        r0 = r4._currInputRow;
        r0 = r0 + 1;
        r4._currInputRow = r0;
        r0 = r4._inputPtr;
        r4._currInputRowStart = r0;
        goto L_0x0002;
    L_0x003e:
        r1 = 13;
        if (r0 != r1) goto L_0x0046;
    L_0x0042:
        r4._skipCR();
        goto L_0x0002;
    L_0x0046:
        r1 = 9;
        if (r0 == r1) goto L_0x0002;
    L_0x004a:
        r4._throwInvalidSpace(r0);
        goto L_0x0002;
    L_0x004e:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Unexpected end-of-input within/between ";
        r0 = r0.append(r1);
        r1 = r4._parsingContext;
        r1 = r1.getTypeDesc();
        r0 = r0.append(r1);
        r1 = " entries";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r0 = r4._constructError(r0);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._skipAfterComma2():int");
    }

    private final int _skipWSOrEnd() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return _eofAsNextChar();
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = cArr[i];
        if (i2 <= 32) {
            if (i2 != 32) {
                if (i2 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
            while (this._inputPtr < this._inputEnd) {
                cArr = this._inputBuffer;
                i = this._inputPtr;
                this._inputPtr = i + 1;
                i2 = cArr[i];
                if (i2 > 32) {
                    if (i2 != 47 && i2 != 35) {
                        return i2;
                    }
                    this._inputPtr--;
                    return _skipWSOrEnd2();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            }
            return _skipWSOrEnd2();
        } else if (i2 != 47 && i2 != 35) {
            return i2;
        } else {
            this._inputPtr--;
            return _skipWSOrEnd2();
        }
    }

    private int _skipWSOrEnd2() {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return _eofAsNextChar();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = cArr[i];
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    return i2;
                }
            } else if (i2 != 32) {
                if (i2 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
    }

    private void _skipComment() {
        if (!isEnabled(Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = cArr[i];
        if (c == '/') {
            _skipLine();
        } else if (c == '*') {
            _skipCComment();
        } else {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '*') {
                if (c == '*') {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        break;
                    } else if (this._inputBuffer[this._inputPtr] == '/') {
                        this._inputPtr++;
                        return;
                    }
                } else if (c < ' ') {
                    if (c == '\n') {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (c == '\r') {
                        _skipCR();
                    } else if (c != '\t') {
                        _throwInvalidSpace(c);
                    }
                }
            }
        }
        _reportInvalidEOF(" in a comment");
    }

    private boolean _skipYAMLComment() {
        if (!isEnabled(Feature.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        _skipLine();
        return true;
    }

    private void _skipLine() {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                char c = cArr[i];
                if (c < ' ') {
                    if (c == '\n') {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                        return;
                    } else if (c == '\r') {
                        _skipCR();
                        return;
                    } else if (c != '\t') {
                        _throwInvalidSpace(c);
                    }
                }
            } else {
                return;
            }
        }
    }

    protected char _decodeEscaped() {
        int i = 0;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        char[] cArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        char c = cArr[i2];
        switch (c) {
            case C0110R.styleable.Theme_actionModePasteDrawable /*34*/:
            case C0110R.styleable.Theme_spinnerDropDownItemStyle /*47*/:
            case C0110R.styleable.Theme_alertDialogButtonGroupStyle /*92*/:
                return c;
            case C0110R.styleable.Theme_buttonBarNeutralButtonStyle /*98*/:
                return '\b';
            case C0110R.styleable.Theme_checkboxStyle /*102*/:
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'u':
                for (int i3 = 0; i3 < 4; i3++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    char[] cArr2 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    char c2 = cArr2[i4];
                    i4 = CharTypes.charToHex(c2);
                    if (i4 < 0) {
                        _reportUnexpectedChar(c2, "expected a hex-digit for character escape sequence");
                    }
                    i = (i << 4) | i4;
                }
                return (char) i;
            default:
                return _handleUnrecognizedCharacterEscape(c);
        }
    }

    private final void _matchTrue() {
        int i = this._inputPtr;
        if (i + 3 < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            if (cArr[i] == 'r') {
                i++;
                if (cArr[i] == 'u') {
                    i++;
                    if (cArr[i] == 'e') {
                        i++;
                        char c = cArr[i];
                        if (c < '0' || c == ']' || c == '}') {
                            this._inputPtr = i;
                            return;
                        }
                    }
                }
            }
        }
        _matchToken("true", 1);
    }

    private final void _matchFalse() {
        int i = this._inputPtr;
        if (i + 4 < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            if (cArr[i] == 'a') {
                i++;
                if (cArr[i] == 'l') {
                    i++;
                    if (cArr[i] == 's') {
                        i++;
                        if (cArr[i] == 'e') {
                            i++;
                            char c = cArr[i];
                            if (c < '0' || c == ']' || c == '}') {
                                this._inputPtr = i;
                                return;
                            }
                        }
                    }
                }
            }
        }
        _matchToken("false", 1);
    }

    private final void _matchNull() {
        int i = this._inputPtr;
        if (i + 3 < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            if (cArr[i] == 'u') {
                i++;
                if (cArr[i] == 'l') {
                    i++;
                    if (cArr[i] == 'l') {
                        i++;
                        char c = cArr[i];
                        if (c < '0' || c == ']' || c == '}') {
                            this._inputPtr = i;
                            return;
                        }
                    }
                }
            }
        }
        _matchToken("null", 1);
    }

    protected final void _matchToken(String str, int i) {
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidToken(str.substring(0, i));
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i));
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if (this._inputPtr < this._inputEnd || loadMore()) {
            char c = this._inputBuffer[this._inputPtr];
            if (c >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
                _reportInvalidToken(str.substring(0, i));
            }
        }
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) {
        char c;
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c2 = cArr[i];
            if (c2 > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c2);
                if (decodeBase64Char < 0) {
                    if (c2 == '\"') {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, c2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                char c3 = cArr2[i2];
                i = base64Variant.decodeBase64Char(c3);
                if (i < 0) {
                    i = _decodeBase64Escape(base64Variant, c3, 1);
                }
                i |= decodeBase64Char << 6;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                cArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                c3 = cArr[i2];
                decodeBase64Char = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char < 0) {
                    if (decodeBase64Char != -2) {
                        if (c3 != '\"' || base64Variant.usesPadding()) {
                            decodeBase64Char = _decodeBase64Escape(base64Variant, c3, 2);
                        } else {
                            _getByteArrayBuilder.append(i >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        cArr = this._inputBuffer;
                        i2 = this._inputPtr;
                        this._inputPtr = i2 + 1;
                        c = cArr[i2];
                        if (!base64Variant.usesPaddingChar(c)) {
                            break;
                        }
                        _getByteArrayBuilder.append(i >> 4);
                    }
                }
                i = (i << 6) | decodeBase64Char;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                cArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                c3 = cArr[i2];
                decodeBase64Char = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char < 0) {
                    if (decodeBase64Char != -2) {
                        if (c3 != '\"' || base64Variant.usesPadding()) {
                            decodeBase64Char = _decodeBase64Escape(base64Variant, c3, 3);
                        } else {
                            _getByteArrayBuilder.appendTwoBytes(i >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes(decodeBase64Char | (i << 6));
            }
        }
        throw reportInvalidBase64Char(base64Variant, c, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
    }

    protected void _reportInvalidToken(String str) {
        _reportInvalidToken(str, "'null', 'true', 'false' or NaN");
    }

    protected void _reportInvalidToken(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this._inputPtr++;
            stringBuilder.append(c);
        }
        _reportError("Unrecognized token '" + stringBuilder.toString() + "': was expecting " + str2);
    }
}
