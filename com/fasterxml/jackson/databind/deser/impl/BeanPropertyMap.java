package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BeanPropertyMap implements Serializable, Iterable<SettableBeanProperty> {
    private final Bucket[] _buckets;
    private final int _hashMask;
    private int _nextBucketIndex;
    private final int _size;

    final class Bucket implements Serializable {
        public final int index;
        public final String key;
        public final Bucket next;
        public final SettableBeanProperty value;

        public Bucket(Bucket bucket, String str, SettableBeanProperty settableBeanProperty, int i) {
            this.next = bucket;
            this.key = str;
            this.value = settableBeanProperty;
            this.index = i;
        }
    }

    final class IteratorImpl implements Iterator<SettableBeanProperty> {
        private final Bucket[] _buckets;
        private Bucket _currentBucket;
        private int _nextBucketIndex;

        public IteratorImpl(Bucket[] bucketArr) {
            int i;
            this._buckets = bucketArr;
            int i2 = 0;
            int length = this._buckets.length;
            while (i2 < length) {
                i = i2 + 1;
                Bucket bucket = this._buckets[i2];
                if (bucket != null) {
                    this._currentBucket = bucket;
                    break;
                }
                i2 = i;
            }
            i = i2;
            this._nextBucketIndex = i;
        }

        public boolean hasNext() {
            return this._currentBucket != null;
        }

        public SettableBeanProperty next() {
            Bucket bucket = this._currentBucket;
            if (bucket == null) {
                throw new NoSuchElementException();
            }
            Bucket bucket2 = bucket.next;
            while (bucket2 == null && this._nextBucketIndex < this._buckets.length) {
                Bucket[] bucketArr = this._buckets;
                int i = this._nextBucketIndex;
                this._nextBucketIndex = i + 1;
                bucket2 = bucketArr[i];
            }
            this._currentBucket = bucket2;
            return bucket.value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public BeanPropertyMap(Collection<SettableBeanProperty> collection) {
        this._nextBucketIndex = 0;
        this._size = collection.size();
        int findSize = findSize(this._size);
        this._hashMask = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (SettableBeanProperty settableBeanProperty : collection) {
            String name = settableBeanProperty.getName();
            int hashCode = name.hashCode() & this._hashMask;
            Bucket bucket = bucketArr[hashCode];
            int i = this._nextBucketIndex;
            this._nextBucketIndex = i + 1;
            bucketArr[hashCode] = new Bucket(bucket, name, settableBeanProperty, i);
        }
        this._buckets = bucketArr;
    }

    private BeanPropertyMap(Bucket[] bucketArr, int i, int i2) {
        this._nextBucketIndex = 0;
        this._buckets = bucketArr;
        this._size = i;
        this._hashMask = bucketArr.length - 1;
        this._nextBucketIndex = i2;
    }

    public BeanPropertyMap withProperty(SettableBeanProperty settableBeanProperty) {
        int length = this._buckets.length;
        Object obj = new Bucket[length];
        System.arraycopy(this._buckets, 0, obj, 0, length);
        String name = settableBeanProperty.getName();
        if (find(settableBeanProperty.getName()) == null) {
            length = name.hashCode() & this._hashMask;
            Bucket bucket = obj[length];
            int i = this._nextBucketIndex;
            this._nextBucketIndex = i + 1;
            obj[length] = new Bucket(bucket, name, settableBeanProperty, i);
            return new BeanPropertyMap(obj, this._size + 1, this._nextBucketIndex);
        }
        BeanPropertyMap beanPropertyMap = new BeanPropertyMap(obj, length, this._nextBucketIndex);
        beanPropertyMap.replace(settableBeanProperty);
        return beanPropertyMap;
    }

    public BeanPropertyMap renameAll(NameTransformer nameTransformer) {
        if (nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return this;
        }
        Iterator it = iterator();
        Collection arrayList = new ArrayList();
        while (it.hasNext()) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) it.next();
            Object withSimpleName = settableBeanProperty.withSimpleName(nameTransformer.transform(settableBeanProperty.getName()));
            JsonDeserializer valueDeserializer = withSimpleName.getValueDeserializer();
            if (valueDeserializer != null) {
                JsonDeserializer unwrappingDeserializer = valueDeserializer.unwrappingDeserializer(nameTransformer);
                if (unwrappingDeserializer != valueDeserializer) {
                    withSimpleName = withSimpleName.withValueDeserializer(unwrappingDeserializer);
                }
            }
            arrayList.add(withSimpleName);
        }
        this(arrayList);
        return this;
    }

    public BeanPropertyMap assignIndexes() {
        int i = 0;
        for (Bucket bucket : this._buckets) {
            Bucket bucket2;
            while (bucket2 != null) {
                int i2 = i + 1;
                bucket2.value.assignIndex(i);
                bucket2 = bucket2.next;
                i = i2;
            }
        }
        return this;
    }

    private static final int findSize(int i) {
        int i2 = 2;
        while (i2 < (i <= 32 ? i + i : (i >> 2) + i)) {
            i2 += i2;
        }
        return i2;
    }

    public String toString() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Properties=[");
        for (SettableBeanProperty settableBeanProperty : getPropertiesInInsertionOrder()) {
            if (settableBeanProperty != null) {
                int i2 = i + 1;
                if (i > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(settableBeanProperty.getName());
                stringBuilder.append('(');
                stringBuilder.append(settableBeanProperty.getType());
                stringBuilder.append(')');
                i = i2;
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public Iterator<SettableBeanProperty> iterator() {
        return new IteratorImpl(this._buckets);
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[this._nextBucketIndex];
        for (Bucket bucket : this._buckets) {
            for (Bucket bucket2 = r3[r1]; bucket2 != null; bucket2 = bucket2.next) {
                settableBeanPropertyArr[bucket2.index] = bucket2.value;
            }
        }
        return settableBeanPropertyArr;
    }

    public int size() {
        return this._size;
    }

    public SettableBeanProperty find(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Can not pass null property name");
        }
        int hashCode = this._hashMask & str.hashCode();
        Bucket bucket = this._buckets[hashCode];
        if (bucket == null) {
            return null;
        }
        if (bucket.key == str) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return _findWithEquals(str, hashCode);
            }
        } while (bucket.key != str);
        return bucket.value;
    }

    public void replace(SettableBeanProperty settableBeanProperty) {
        String name = settableBeanProperty.getName();
        int hashCode = name.hashCode() & (this._buckets.length - 1);
        Bucket bucket = this._buckets[hashCode];
        int i = -1;
        Bucket bucket2 = null;
        while (bucket != null) {
            if (i >= 0 || !bucket.key.equals(name)) {
                bucket2 = new Bucket(bucket2, bucket.key, bucket.value, bucket.index);
            } else {
                i = bucket.index;
            }
            bucket = bucket.next;
        }
        if (i < 0) {
            throw new NoSuchElementException("No entry '" + settableBeanProperty + "' found, can't replace");
        }
        this._buckets[hashCode] = new Bucket(bucket2, name, settableBeanProperty, i);
    }

    public void remove(SettableBeanProperty settableBeanProperty) {
        String name = settableBeanProperty.getName();
        int hashCode = name.hashCode() & (this._buckets.length - 1);
        Bucket bucket = this._buckets[hashCode];
        Object obj = null;
        Bucket bucket2 = null;
        while (bucket != null) {
            if (obj == null && bucket.key.equals(name)) {
                obj = 1;
            } else {
                bucket2 = new Bucket(bucket2, bucket.key, bucket.value, bucket.index);
            }
            bucket = bucket.next;
        }
        if (obj == null) {
            throw new NoSuchElementException("No entry '" + settableBeanProperty + "' found, can't remove");
        }
        this._buckets[hashCode] = bucket2;
    }

    private SettableBeanProperty _findWithEquals(String str, int i) {
        for (Bucket bucket = this._buckets[i]; bucket != null; bucket = bucket.next) {
            if (str.equals(bucket.key)) {
                return bucket.value;
            }
        }
        return null;
    }
}
