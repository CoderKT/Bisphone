package org.pjsip.pjsua2;

public class ToneGenerator extends AudioMedia {
    private long swigCPtr;

    protected ToneGenerator(long j, boolean z) {
        super(pjsua2JNI.ToneGenerator_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(ToneGenerator toneGenerator) {
        return toneGenerator == null ? 0 : toneGenerator.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneGenerator(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public ToneGenerator() {
        this(pjsua2JNI.new_ToneGenerator(), true);
    }

    public void createToneGenerator(long j, long j2) {
        pjsua2JNI.ToneGenerator_createToneGenerator__SWIG_0(this.swigCPtr, this, j, j2);
    }

    public void createToneGenerator(long j) {
        pjsua2JNI.ToneGenerator_createToneGenerator__SWIG_1(this.swigCPtr, this, j);
    }

    public void createToneGenerator() {
        pjsua2JNI.ToneGenerator_createToneGenerator__SWIG_2(this.swigCPtr, this);
    }

    public boolean isBusy() {
        return pjsua2JNI.ToneGenerator_isBusy(this.swigCPtr, this);
    }

    public void stop() {
        pjsua2JNI.ToneGenerator_stop(this.swigCPtr, this);
    }

    public void rewind() {
        pjsua2JNI.ToneGenerator_rewind(this.swigCPtr, this);
    }

    public void play(ToneDescVector toneDescVector, boolean z) {
        pjsua2JNI.ToneGenerator_play__SWIG_0(this.swigCPtr, this, ToneDescVector.getCPtr(toneDescVector), toneDescVector, z);
    }

    public void play(ToneDescVector toneDescVector) {
        pjsua2JNI.ToneGenerator_play__SWIG_1(this.swigCPtr, this, ToneDescVector.getCPtr(toneDescVector), toneDescVector);
    }

    public void playDigits(ToneDigitVector toneDigitVector, boolean z) {
        pjsua2JNI.ToneGenerator_playDigits__SWIG_0(this.swigCPtr, this, ToneDigitVector.getCPtr(toneDigitVector), toneDigitVector, z);
    }

    public void playDigits(ToneDigitVector toneDigitVector) {
        pjsua2JNI.ToneGenerator_playDigits__SWIG_1(this.swigCPtr, this, ToneDigitVector.getCPtr(toneDigitVector), toneDigitVector);
    }

    public ToneDigitMapVector getDigitMap() {
        return new ToneDigitMapVector(pjsua2JNI.ToneGenerator_getDigitMap(this.swigCPtr, this), true);
    }

    public void setDigitMap(ToneDigitMapVector toneDigitMapVector) {
        pjsua2JNI.ToneGenerator_setDigitMap(this.swigCPtr, this, ToneDigitMapVector.getCPtr(toneDigitMapVector), toneDigitMapVector);
    }
}
