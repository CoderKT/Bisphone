package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class ParserMinimalBase extends JsonParser {
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;

    protected abstract void _handleEOF();

    public abstract String getText();

    public abstract JsonToken nextToken();

    protected ParserMinimalBase() {
    }

    protected ParserMinimalBase(int i) {
        super(i);
    }

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public final int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? 0 : jsonToken.id();
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public JsonToken nextValue() {
        JsonToken nextToken = nextToken();
        if (nextToken == JsonToken.FIELD_NAME) {
            return nextToken();
        }
        return nextToken;
    }

    public JsonParser skipChildren() {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken == null) {
                    break;
                } else if (nextToken.isStructStart()) {
                    i++;
                } else if (nextToken.isStructEnd()) {
                    i--;
                    if (i == 0) {
                        break;
                    }
                } else {
                    continue;
                }
            }
            _handleEOF();
        }
        return this;
    }

    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }

    public int getValueAsInt(int i) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return i;
        }
        switch (jsonToken.id()) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                String text = getText();
                if (_hasTextualNull(text)) {
                    return 0;
                }
                return NumberInput.parseAsInt(text, i);
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return getIntValue();
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return 1;
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return 0;
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                return 0;
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                Object embeddedObject = getEmbeddedObject();
                if (embeddedObject instanceof Number) {
                    return ((Number) embeddedObject).intValue();
                }
                return i;
            default:
                return i;
        }
    }

    public long getValueAsLong(long j) {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return j;
        }
        switch (jsonToken.id()) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                String text = getText();
                if (_hasTextualNull(text)) {
                    return 0;
                }
                return NumberInput.parseAsLong(text, j);
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return getLongValue();
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return 1;
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                return 0;
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                Object embeddedObject = getEmbeddedObject();
                if (embeddedObject instanceof Number) {
                    return ((Number) embeddedObject).longValue();
                }
                return j;
            default:
                return j;
        }
    }

    public String getValueAsString(String str) {
        return (this._currToken == JsonToken.VALUE_STRING || !(this._currToken == null || this._currToken == JsonToken.VALUE_NULL || !this._currToken.isScalarValue())) ? getText() : str;
    }

    protected void _decodeBase64(String str, ByteArrayBuilder byteArrayBuilder, Base64Variant base64Variant) {
        try {
            base64Variant.decode(str, byteArrayBuilder);
        } catch (IllegalArgumentException e) {
            _reportError(e.getMessage());
        }
    }

    protected boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    protected void _reportUnexpectedChar(int i, String str) {
        if (i < 0) {
            _reportInvalidEOF();
        }
        String str2 = "Unexpected character (" + _getCharDesc(i) + ")";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    protected void _reportInvalidEOF() {
        _reportInvalidEOF(" in " + this._currToken);
    }

    protected void _reportInvalidEOF(String str) {
        _reportError("Unexpected end-of-input" + str);
    }

    protected void _reportInvalidEOFInValue() {
        _reportInvalidEOF(" in a value");
    }

    protected void _reportMissingRootWS(int i) {
        _reportUnexpectedChar(i, "Expected space separating root-level values");
    }

    protected void _throwInvalidSpace(int i) {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    protected void _throwUnquotedSpace(int i, String str) {
        if (!isEnabled(Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i > 32) {
            _reportError("Illegal unquoted character (" + _getCharDesc((char) i) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    protected char _handleUnrecognizedCharacterEscape(char c) {
        if (!(isEnabled(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) || (c == '\'' && isEnabled(Feature.ALLOW_SINGLE_QUOTES)))) {
            _reportError("Unrecognized character escape " + _getCharDesc(c));
        }
        return c;
    }

    protected static final String _getCharDesc(int i) {
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + i + ")";
        }
        if (i > 255) {
            return "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")";
        }
        return "'" + c + "' (code " + i + ")";
    }

    protected final void _reportError(String str) {
        throw _constructError(str);
    }

    protected final void _wrapError(String str, Throwable th) {
        throw _constructError(str, th);
    }

    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(str, getCurrentLocation(), th);
    }
}
