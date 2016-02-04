package org.pjsip.pjsua2;

public class MediaFormatVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected MediaFormatVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MediaFormatVector mediaFormatVector) {
        return mediaFormatVector == null ? 0 : mediaFormatVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFormatVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public MediaFormatVector() {
        this(pjsua2JNI.new_MediaFormatVector__SWIG_0(), true);
    }

    public MediaFormatVector(long j) {
        this(pjsua2JNI.new_MediaFormatVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.MediaFormatVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.MediaFormatVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.MediaFormatVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.MediaFormatVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.MediaFormatVector_clear(this.swigCPtr, this);
    }

    public void add(MediaFormat mediaFormat) {
        pjsua2JNI.MediaFormatVector_add(this.swigCPtr, this, MediaFormat.getCPtr(mediaFormat), mediaFormat);
    }

    public MediaFormat get(int i) {
        long MediaFormatVector_get = pjsua2JNI.MediaFormatVector_get(this.swigCPtr, this, i);
        return MediaFormatVector_get == 0 ? null : new MediaFormat(MediaFormatVector_get, false);
    }

    public void set(int i, MediaFormat mediaFormat) {
        pjsua2JNI.MediaFormatVector_set(this.swigCPtr, this, i, MediaFormat.getCPtr(mediaFormat), mediaFormat);
    }
}
