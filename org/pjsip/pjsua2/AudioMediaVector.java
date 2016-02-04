package org.pjsip.pjsua2;

public class AudioMediaVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected AudioMediaVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioMediaVector audioMediaVector) {
        return audioMediaVector == null ? 0 : audioMediaVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public AudioMediaVector() {
        this(pjsua2JNI.new_AudioMediaVector__SWIG_0(), true);
    }

    public AudioMediaVector(long j) {
        this(pjsua2JNI.new_AudioMediaVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.AudioMediaVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.AudioMediaVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.AudioMediaVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.AudioMediaVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.AudioMediaVector_clear(this.swigCPtr, this);
    }

    public void add(AudioMedia audioMedia) {
        pjsua2JNI.AudioMediaVector_add(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public AudioMedia get(int i) {
        long AudioMediaVector_get = pjsua2JNI.AudioMediaVector_get(this.swigCPtr, this, i);
        return AudioMediaVector_get == 0 ? null : new AudioMedia(AudioMediaVector_get, false);
    }

    public void set(int i, AudioMedia audioMedia) {
        pjsua2JNI.AudioMediaVector_set(this.swigCPtr, this, i, AudioMedia.getCPtr(audioMedia), audioMedia);
    }
}
