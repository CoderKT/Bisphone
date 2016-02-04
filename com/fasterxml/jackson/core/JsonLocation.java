package com.fasterxml.jackson.core;

import java.io.Serializable;

public class JsonLocation implements Serializable {
    public static final JsonLocation NA;
    final int _columnNr;
    final int _lineNr;
    final transient Object _sourceRef;
    final long _totalBytes;
    final long _totalChars;

    static {
        NA = new JsonLocation("N/A", -1, -1, -1, -1);
    }

    public JsonLocation(Object obj, long j, int i, int i2) {
        this(obj, -1, j, i, i2);
    }

    public JsonLocation(Object obj, long j, long j2, int i, int i2) {
        this._sourceRef = obj;
        this._totalBytes = j;
        this._totalChars = j2;
        this._lineNr = i;
        this._columnNr = i2;
    }

    public long getByteOffset() {
        return this._totalBytes;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(80);
        stringBuilder.append("[Source: ");
        if (this._sourceRef == null) {
            stringBuilder.append("UNKNOWN");
        } else {
            stringBuilder.append(this._sourceRef.toString());
        }
        stringBuilder.append("; line: ");
        stringBuilder.append(this._lineNr);
        stringBuilder.append(", column: ");
        stringBuilder.append(this._columnNr);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public int hashCode() {
        return ((((this._sourceRef == null ? 1 : this._sourceRef.hashCode()) ^ this._lineNr) + this._columnNr) ^ ((int) this._totalChars)) + ((int) this._totalBytes);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof JsonLocation)) {
            return false;
        }
        JsonLocation jsonLocation = (JsonLocation) obj;
        if (this._sourceRef == null) {
            if (jsonLocation._sourceRef != null) {
                return false;
            }
        } else if (!this._sourceRef.equals(jsonLocation._sourceRef)) {
            return false;
        }
        if (!(this._lineNr == jsonLocation._lineNr && this._columnNr == jsonLocation._columnNr && this._totalChars == jsonLocation._totalChars && getByteOffset() == jsonLocation.getByteOffset())) {
            z = false;
        }
        return z;
    }
}
