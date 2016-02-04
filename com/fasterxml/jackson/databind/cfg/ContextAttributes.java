package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public abstract class ContextAttributes {

    public class Impl extends ContextAttributes implements Serializable {
        protected static final Impl EMPTY;
        protected static final Object NULL_SURROGATE;
        protected transient Map<Object, Object> _nonShared;
        protected final Map<Object, Object> _shared;

        static {
            EMPTY = new Impl(Collections.emptyMap());
            NULL_SURROGATE = new Object();
        }

        protected Impl(Map<Object, Object> map) {
            this._shared = map;
            this._nonShared = null;
        }

        public static ContextAttributes getEmpty() {
            return EMPTY;
        }
    }

    public static ContextAttributes getEmpty() {
        return Impl.getEmpty();
    }
}
