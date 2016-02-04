package org.pjsip.pjsua2;

public class BuddyVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected BuddyVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(BuddyVector buddyVector) {
        return buddyVector == null ? 0 : buddyVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_BuddyVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public BuddyVector() {
        this(pjsua2JNI.new_BuddyVector__SWIG_0(), true);
    }

    public BuddyVector(long j) {
        this(pjsua2JNI.new_BuddyVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.BuddyVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.BuddyVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.BuddyVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.BuddyVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.BuddyVector_clear(this.swigCPtr, this);
    }

    public void add(Buddy buddy) {
        pjsua2JNI.BuddyVector_add(this.swigCPtr, this, Buddy.getCPtr(buddy), buddy);
    }

    public Buddy get(int i) {
        long BuddyVector_get = pjsua2JNI.BuddyVector_get(this.swigCPtr, this, i);
        return BuddyVector_get == 0 ? null : new Buddy(BuddyVector_get, false);
    }

    public void set(int i, Buddy buddy) {
        pjsua2JNI.BuddyVector_set(this.swigCPtr, this, i, Buddy.getCPtr(buddy), buddy);
    }
}
