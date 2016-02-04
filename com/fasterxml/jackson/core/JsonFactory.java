package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.ref.SoftReference;

public class JsonFactory implements Serializable {
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS;
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS;
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS;
    private static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR;
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected ObjectCodec _objectCodec;
    protected int _parserFeatures;
    protected final transient BytesToNameCanonicalizer _rootByteSymbols;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;

    public enum Feature {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true),
        FAIL_ON_SYMBOL_HASH_OVERFLOW(true);
        
        private final boolean _defaultState;

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
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i) {
            return (getMask() & i) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    static {
        DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
        DEFAULT_PARSER_FEATURE_FLAGS = com.fasterxml.jackson.core.JsonParser.Feature.collectDefaults();
        DEFAULT_GENERATOR_FEATURE_FLAGS = com.fasterxml.jackson.core.JsonGenerator.Feature.collectDefaults();
        DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        _recyclerRef = new ThreadLocal();
    }

    public JsonFactory(ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
    }

    public boolean requiresPropertyOrdering() {
        return false;
    }

    public boolean canUseCharArrays() {
        return true;
    }

    public JsonFactory setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public JsonParser createParser(Reader reader) {
        IOContext _createContext = _createContext(reader, false);
        return _createParser(_decorate(reader, _createContext), _createContext);
    }

    public JsonParser createParser(String str) {
        int length = str.length();
        if (this._inputDecorator != null || length > 32768 || !canUseCharArrays()) {
            return createParser(new StringReader(str));
        }
        IOContext _createContext = _createContext(str, true);
        char[] allocTokenBuffer = _createContext.allocTokenBuffer(length);
        str.getChars(0, length, allocTokenBuffer, 0);
        return _createParser(allocTokenBuffer, 0, length, _createContext, true);
    }

    protected JsonParser _createParser(Reader reader, IOContext iOContext) {
        return new ReaderBasedJsonParser(iOContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures));
    }

    protected JsonParser _createParser(char[] cArr, int i, int i2, IOContext iOContext, boolean z) {
        return new ReaderBasedJsonParser(iOContext, this._parserFeatures, null, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures), cArr, i, i + i2, z);
    }

    protected final Reader _decorate(Reader reader, IOContext iOContext) {
        if (this._inputDecorator == null) {
            return reader;
        }
        Reader decorate = this._inputDecorator.decorate(iOContext, reader);
        if (decorate != null) {
            return decorate;
        }
        return reader;
    }

    public BufferRecycler _getBufferRecycler() {
        SoftReference softReference = (SoftReference) _recyclerRef.get();
        BufferRecycler bufferRecycler = softReference == null ? null : (BufferRecycler) softReference.get();
        if (bufferRecycler != null) {
            return bufferRecycler;
        }
        bufferRecycler = new BufferRecycler();
        _recyclerRef.set(new SoftReference(bufferRecycler));
        return bufferRecycler;
    }

    protected IOContext _createContext(Object obj, boolean z) {
        return new IOContext(_getBufferRecycler(), obj, z);
    }
}
