package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TypeParser implements Serializable {
    protected final TypeFactory _factory;

    final class MyTokenizer extends StringTokenizer {
        protected int _index;
        protected final String _input;
        protected String _pushbackToken;

        public MyTokenizer(String str) {
            super(str, "<,>", true);
            this._input = str;
        }

        public boolean hasMoreTokens() {
            return this._pushbackToken != null || super.hasMoreTokens();
        }

        public String nextToken() {
            String str;
            if (this._pushbackToken != null) {
                str = this._pushbackToken;
                this._pushbackToken = null;
            } else {
                str = super.nextToken();
            }
            this._index += str.length();
            return str;
        }

        public void pushBack(String str) {
            this._pushbackToken = str;
            this._index -= str.length();
        }

        public String getAllInput() {
            return this._input;
        }

        public String getRemainingInput() {
            return this._input.substring(this._index);
        }
    }

    public TypeParser(TypeFactory typeFactory) {
        this._factory = typeFactory;
    }

    public JavaType parse(String str) {
        MyTokenizer myTokenizer = new MyTokenizer(str.trim());
        JavaType parseType = parseType(myTokenizer);
        if (!myTokenizer.hasMoreTokens()) {
            return parseType;
        }
        throw _problem(myTokenizer, "Unexpected tokens after complete type");
    }

    protected JavaType parseType(MyTokenizer myTokenizer) {
        if (myTokenizer.hasMoreTokens()) {
            Class findClass = findClass(myTokenizer.nextToken(), myTokenizer);
            if (myTokenizer.hasMoreTokens()) {
                String nextToken = myTokenizer.nextToken();
                if ("<".equals(nextToken)) {
                    return this._factory._fromParameterizedClass(findClass, parseTypes(myTokenizer));
                }
                myTokenizer.pushBack(nextToken);
            }
            return this._factory._fromClass(findClass, null);
        }
        throw _problem(myTokenizer, "Unexpected end-of-string");
    }

    protected List<JavaType> parseTypes(MyTokenizer myTokenizer) {
        List arrayList = new ArrayList();
        while (myTokenizer.hasMoreTokens()) {
            arrayList.add(parseType(myTokenizer));
            if (!myTokenizer.hasMoreTokens()) {
                break;
            }
            String nextToken = myTokenizer.nextToken();
            if (">".equals(nextToken)) {
                return arrayList;
            }
            if (!",".equals(nextToken)) {
                throw _problem(myTokenizer, "Unexpected token '" + nextToken + "', expected ',' or '>')");
            }
        }
        throw _problem(myTokenizer, "Unexpected end-of-string");
    }

    protected Class<?> findClass(String str, MyTokenizer myTokenizer) {
        try {
            return ClassUtil.findClass(str);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            throw _problem(myTokenizer, "Can not locate class '" + str + "', problem: " + e.getMessage());
        }
    }

    protected IllegalArgumentException _problem(MyTokenizer myTokenizer, String str) {
        return new IllegalArgumentException("Failed to parse type '" + myTokenizer.getAllInput() + "' (remaining: '" + myTokenizer.getRemainingInput() + "'): " + str);
    }
}
