package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonStreamContext;

public class JsonWriteContext extends JsonStreamContext {
    protected JsonWriteContext _child;
    protected String _currentName;
    protected final DupDetector _dups;
    protected boolean _gotName;
    protected final JsonWriteContext _parent;

    protected JsonWriteContext(int i, JsonWriteContext jsonWriteContext, DupDetector dupDetector) {
        this._child = null;
        this._type = i;
        this._parent = jsonWriteContext;
        this._dups = dupDetector;
        this._index = -1;
    }

    protected JsonWriteContext reset(int i) {
        this._type = i;
        this._index = -1;
        this._currentName = null;
        this._gotName = false;
        if (this._dups != null) {
            this._dups.reset();
        }
        return this;
    }

    public static JsonWriteContext createRootContext(DupDetector dupDetector) {
        return new JsonWriteContext(0, null, dupDetector);
    }

    public JsonWriteContext createChildArrayContext() {
        JsonWriteContext jsonWriteContext = this._child;
        if (jsonWriteContext != null) {
            return jsonWriteContext.reset(1);
        }
        JsonWriteContext jsonWriteContext2 = new JsonWriteContext(1, this, this._dups == null ? null : this._dups.child());
        this._child = jsonWriteContext2;
        return jsonWriteContext2;
    }

    public JsonWriteContext createChildObjectContext() {
        JsonWriteContext jsonWriteContext = this._child;
        if (jsonWriteContext != null) {
            return jsonWriteContext.reset(2);
        }
        JsonWriteContext jsonWriteContext2 = new JsonWriteContext(2, this, this._dups == null ? null : this._dups.child());
        this._child = jsonWriteContext2;
        return jsonWriteContext2;
    }

    public final JsonWriteContext getParent() {
        return this._parent;
    }

    public final int writeFieldName(String str) {
        this._gotName = true;
        this._currentName = str;
        if (this._dups != null) {
            _checkDup(this._dups, str);
        }
        if (this._index < 0) {
            return 0;
        }
        return 1;
    }

    private final void _checkDup(DupDetector dupDetector, String str) {
        if (dupDetector.isDup(str)) {
            throw new JsonGenerationException("Duplicate field '" + str + "'");
        }
    }

    protected void appendDesc(StringBuilder stringBuilder) {
        if (this._type == 2) {
            stringBuilder.append('{');
            if (this._currentName != null) {
                stringBuilder.append('\"');
                stringBuilder.append(this._currentName);
                stringBuilder.append('\"');
            } else {
                stringBuilder.append('?');
            }
            stringBuilder.append('}');
        } else if (this._type == 1) {
            stringBuilder.append('[');
            stringBuilder.append(getCurrentIndex());
            stringBuilder.append(']');
        } else {
            stringBuilder.append("/");
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        appendDesc(stringBuilder);
        return stringBuilder.toString();
    }
}
