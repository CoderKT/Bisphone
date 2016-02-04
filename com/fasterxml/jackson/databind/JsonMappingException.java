package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class JsonMappingException extends JsonProcessingException {
    protected LinkedList<Reference> _path;

    public class Reference implements Serializable {
        protected String _fieldName;
        protected Object _from;
        protected int _index;

        protected Reference() {
            this._index = -1;
        }

        public Reference(Object obj, String str) {
            this._index = -1;
            this._from = obj;
            if (str == null) {
                throw new NullPointerException("Can not pass null fieldName");
            }
            this._fieldName = str;
        }

        public Reference(Object obj, int i) {
            this._index = -1;
            this._from = obj;
            this._index = i;
        }

        public String toString() {
            Class cls;
            StringBuilder stringBuilder = new StringBuilder();
            if (this._from instanceof Class) {
                cls = (Class) this._from;
            } else {
                cls = this._from.getClass();
            }
            Package packageR = cls.getPackage();
            if (packageR != null) {
                stringBuilder.append(packageR.getName());
                stringBuilder.append('.');
            }
            stringBuilder.append(cls.getSimpleName());
            stringBuilder.append('[');
            if (this._fieldName != null) {
                stringBuilder.append('\"');
                stringBuilder.append(this._fieldName);
                stringBuilder.append('\"');
            } else if (this._index >= 0) {
                stringBuilder.append(this._index);
            } else {
                stringBuilder.append('?');
            }
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    public JsonMappingException(String str) {
        super(str);
    }

    public JsonMappingException(String str, Throwable th) {
        super(str, th);
    }

    public JsonMappingException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }

    public JsonMappingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }

    public static JsonMappingException from(JsonParser jsonParser, String str) {
        return new JsonMappingException(str, jsonParser == null ? null : jsonParser.getTokenLocation());
    }

    public static JsonMappingException from(JsonParser jsonParser, String str, Throwable th) {
        return new JsonMappingException(str, jsonParser == null ? null : jsonParser.getTokenLocation(), th);
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, String str) {
        return wrapWithPath(th, new Reference(obj, str));
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, int i) {
        return wrapWithPath(th, new Reference(obj, i));
    }

    public static JsonMappingException wrapWithPath(Throwable th, Reference reference) {
        if (th instanceof JsonMappingException) {
            th = (JsonMappingException) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                message = "(was " + th.getClass().getName() + ")";
            }
            th = new JsonMappingException(message, null, th);
        }
        th.prependPath(reference);
        return th;
    }

    public StringBuilder getPathReference(StringBuilder stringBuilder) {
        _appendPathDesc(stringBuilder);
        return stringBuilder;
    }

    public void prependPath(Object obj, String str) {
        prependPath(new Reference(obj, str));
    }

    public void prependPath(Reference reference) {
        if (this._path == null) {
            this._path = new LinkedList();
        }
        if (this._path.size() < 1000) {
            this._path.addFirst(reference);
        }
    }

    public String getLocalizedMessage() {
        return _buildMessage();
    }

    public String getMessage() {
        return _buildMessage();
    }

    protected String _buildMessage() {
        String message = super.getMessage();
        if (this._path == null) {
            return message;
        }
        StringBuilder stringBuilder = message == null ? new StringBuilder() : new StringBuilder(message);
        stringBuilder.append(" (through reference chain: ");
        stringBuilder = getPathReference(stringBuilder);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }

    protected void _appendPathDesc(StringBuilder stringBuilder) {
        if (this._path != null) {
            Iterator it = this._path.iterator();
            while (it.hasNext()) {
                stringBuilder.append(((Reference) it.next()).toString());
                if (it.hasNext()) {
                    stringBuilder.append("->");
                }
            }
        }
    }
}
