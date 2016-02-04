package com.fasterxml.jackson.core.sym;

import java.util.concurrent.atomic.AtomicReference;

public final class BytesToNameCanonicalizer {
    protected final boolean _failOnDoS;
    protected boolean _intern;
    protected final BytesToNameCanonicalizer _parent;
    private final int _seed;
    protected final AtomicReference<TableInfo> _tableInfo;

    final class Bucket {
    }

    final class TableInfo {
        public final int collCount;
        public final int collEnd;
        public final Bucket[] collList;
        public final int count;
        public final int longestCollisionList;
        public final int[] mainHash;
        public final int mainHashMask;
        public final Name[] mainNames;

        public TableInfo(int i, int i2, int[] iArr, Name[] nameArr, Bucket[] bucketArr, int i3, int i4, int i5) {
            this.count = i;
            this.mainHashMask = i2;
            this.mainHash = iArr;
            this.mainNames = nameArr;
            this.collList = bucketArr;
            this.collCount = i3;
            this.collEnd = i4;
            this.longestCollisionList = i5;
        }
    }

    private BytesToNameCanonicalizer(int i, boolean z, int i2, boolean z2) {
        int i3 = 16;
        this._parent = null;
        this._seed = i2;
        this._intern = z;
        this._failOnDoS = z2;
        if (i < 16) {
            i = 16;
        } else if (((i - 1) & i) != 0) {
            while (i3 < i) {
                i3 += i3;
            }
            i = i3;
        }
        this._tableInfo = new AtomicReference(initTableInfo(i));
    }

    private TableInfo initTableInfo(int i) {
        return new TableInfo(0, i - 1, new int[i], new Name[i], null, 0, 0, 0);
    }

    public static BytesToNameCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) (currentTimeMillis >>> 32)) + ((int) currentTimeMillis)) | 1);
    }

    protected static BytesToNameCanonicalizer createRoot(int i) {
        return new BytesToNameCanonicalizer(64, true, i, true);
    }
}
