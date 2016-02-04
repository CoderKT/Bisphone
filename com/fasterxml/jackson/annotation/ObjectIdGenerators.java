package com.fasterxml.jackson.annotation;

public class ObjectIdGenerators {

    abstract class Base<T> extends ObjectIdGenerator<T> {
        protected final Class<?> _scope;

        protected Base(Class<?> cls) {
            this._scope = cls;
        }

        public final Class<?> getScope() {
            return this._scope;
        }

        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator.getClass() == getClass() && objectIdGenerator.getScope() == this._scope;
        }
    }

    public abstract class None extends ObjectIdGenerator<Object> {
    }

    public abstract class PropertyGenerator extends Base<Object> {
        public /* bridge */ /* synthetic */ boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }

        protected PropertyGenerator(Class<?> cls) {
            super(cls);
        }
    }
}
