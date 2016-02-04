package org.pjsip.pjsua2;

public class AudioDevInfoVector {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected AudioDevInfoVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioDevInfoVector audioDevInfoVector) {
        return audioDevInfoVector == null ? 0 : audioDevInfoVector.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioDevInfoVector(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public AudioDevInfoVector() {
        this(pjsua2JNI.new_AudioDevInfoVector__SWIG_0(), true);
    }

    public AudioDevInfoVector(long j) {
        this(pjsua2JNI.new_AudioDevInfoVector__SWIG_1(j), true);
    }

    public long size() {
        return pjsua2JNI.AudioDevInfoVector_size(this.swigCPtr, this);
    }

    public long capacity() {
        return pjsua2JNI.AudioDevInfoVector_capacity(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.AudioDevInfoVector_reserve(this.swigCPtr, this, j);
    }

    public boolean isEmpty() {
        return pjsua2JNI.AudioDevInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.AudioDevInfoVector_clear(this.swigCPtr, this);
    }

    public void add(AudioDevInfo audioDevInfo) {
        pjsua2JNI.AudioDevInfoVector_add(this.swigCPtr, this, AudioDevInfo.getCPtr(audioDevInfo), audioDevInfo);
    }

    public AudioDevInfo get(int i) {
        long AudioDevInfoVector_get = pjsua2JNI.AudioDevInfoVector_get(this.swigCPtr, this, i);
        return AudioDevInfoVector_get == 0 ? null : new AudioDevInfo(AudioDevInfoVector_get, false);
    }

    public void set(int i, AudioDevInfo audioDevInfo) {
        pjsua2JNI.AudioDevInfoVector_set(this.swigCPtr, this, i, AudioDevInfo.getCPtr(audioDevInfo), audioDevInfo);
    }
}
