package org.pjsip.pjsua2;

public class ToneDigitMapDigit {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ToneDigitMapDigit(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ToneDigitMapDigit toneDigitMapDigit) {
        return toneDigitMapDigit == null ? 0 : toneDigitMapDigit.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDigitMapDigit(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setDigit(String str) {
        pjsua2JNI.ToneDigitMapDigit_digit_set(this.swigCPtr, this, str);
    }

    public String getDigit() {
        return pjsua2JNI.ToneDigitMapDigit_digit_get(this.swigCPtr, this);
    }

    public void setFreq1(int i) {
        pjsua2JNI.ToneDigitMapDigit_freq1_set(this.swigCPtr, this, i);
    }

    public int getFreq1() {
        return pjsua2JNI.ToneDigitMapDigit_freq1_get(this.swigCPtr, this);
    }

    public void setFreq2(int i) {
        pjsua2JNI.ToneDigitMapDigit_freq2_set(this.swigCPtr, this, i);
    }

    public int getFreq2() {
        return pjsua2JNI.ToneDigitMapDigit_freq2_get(this.swigCPtr, this);
    }

    public ToneDigitMapDigit() {
        this(pjsua2JNI.new_ToneDigitMapDigit(), true);
    }
}
