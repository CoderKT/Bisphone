package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public final class ArrayBuilders {
    private BooleanBuilder _booleanBuilder;
    private ByteBuilder _byteBuilder;
    private DoubleBuilder _doubleBuilder;
    private FloatBuilder _floatBuilder;
    private IntBuilder _intBuilder;
    private LongBuilder _longBuilder;
    private ShortBuilder _shortBuilder;

    /* renamed from: com.fasterxml.jackson.databind.util.ArrayBuilders.1 */
    final class C06421 {
        final /* synthetic */ Object val$defaultValue;
        final /* synthetic */ Class val$defaultValueType;
        final /* synthetic */ int val$length;

        C06421(Class cls, int i, Object obj) {
            this.val$defaultValueType = cls;
            this.val$length = i;
            this.val$defaultValue = obj;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.val$defaultValueType || Array.getLength(obj) != this.val$length) {
                return false;
            }
            for (int i = 0; i < this.val$length; i++) {
                Object obj2 = Array.get(this.val$defaultValue, i);
                Object obj3 = Array.get(obj, i);
                if (obj2 != obj3 && obj2 != null && !obj2.equals(obj3)) {
                    return false;
                }
            }
            return true;
        }
    }

    public final class BooleanBuilder extends PrimitiveArrayBuilder<boolean[]> {
        public final boolean[] _constructArray(int i) {
            return new boolean[i];
        }
    }

    public final class ByteBuilder extends PrimitiveArrayBuilder<byte[]> {
        public final byte[] _constructArray(int i) {
            return new byte[i];
        }
    }

    public final class DoubleBuilder extends PrimitiveArrayBuilder<double[]> {
        public final double[] _constructArray(int i) {
            return new double[i];
        }
    }

    public final class FloatBuilder extends PrimitiveArrayBuilder<float[]> {
        public final float[] _constructArray(int i) {
            return new float[i];
        }
    }

    public final class IntBuilder extends PrimitiveArrayBuilder<int[]> {
        public final int[] _constructArray(int i) {
            return new int[i];
        }
    }

    public final class LongBuilder extends PrimitiveArrayBuilder<long[]> {
        public final long[] _constructArray(int i) {
            return new long[i];
        }
    }

    public final class ShortBuilder extends PrimitiveArrayBuilder<short[]> {
        public final short[] _constructArray(int i) {
            return new short[i];
        }
    }

    public ArrayBuilders() {
        this._booleanBuilder = null;
        this._byteBuilder = null;
        this._shortBuilder = null;
        this._intBuilder = null;
        this._longBuilder = null;
        this._floatBuilder = null;
        this._doubleBuilder = null;
    }

    public BooleanBuilder getBooleanBuilder() {
        if (this._booleanBuilder == null) {
            this._booleanBuilder = new BooleanBuilder();
        }
        return this._booleanBuilder;
    }

    public ByteBuilder getByteBuilder() {
        if (this._byteBuilder == null) {
            this._byteBuilder = new ByteBuilder();
        }
        return this._byteBuilder;
    }

    public ShortBuilder getShortBuilder() {
        if (this._shortBuilder == null) {
            this._shortBuilder = new ShortBuilder();
        }
        return this._shortBuilder;
    }

    public IntBuilder getIntBuilder() {
        if (this._intBuilder == null) {
            this._intBuilder = new IntBuilder();
        }
        return this._intBuilder;
    }

    public LongBuilder getLongBuilder() {
        if (this._longBuilder == null) {
            this._longBuilder = new LongBuilder();
        }
        return this._longBuilder;
    }

    public FloatBuilder getFloatBuilder() {
        if (this._floatBuilder == null) {
            this._floatBuilder = new FloatBuilder();
        }
        return this._floatBuilder;
    }

    public DoubleBuilder getDoubleBuilder() {
        if (this._doubleBuilder == null) {
            this._doubleBuilder = new DoubleBuilder();
        }
        return this._doubleBuilder;
    }

    public static Object getArrayComparator(Object obj) {
        return new C06421(obj.getClass(), Array.getLength(obj), obj);
    }

    public static <T> HashSet<T> arrayToSet(T[] tArr) {
        HashSet<T> hashSet = new HashSet();
        if (tArr != null) {
            for (Object add : tArr) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }

    public static <T> HashSet<T> setAndArray(Set<T> set, T[] tArr) {
        HashSet<T> hashSet = new HashSet();
        if (set != null) {
            hashSet.addAll(set);
        }
        if (tArr != null) {
            for (Object add : tArr) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }
}
