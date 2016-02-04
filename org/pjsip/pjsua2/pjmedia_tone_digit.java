package org.pjsip.pjsua2;

public class pjmedia_tone_digit {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected pjmedia_tone_digit(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit) {
        return org_pjsip_pjsua2_pjmedia_tone_digit == null ? 0 : org_pjsip_pjsua2_pjmedia_tone_digit.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_pjmedia_tone_digit(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setDigit(char c) {
        pjsua2JNI.pjmedia_tone_digit_digit_set(this.swigCPtr, this, c);
    }

    public char getDigit() {
        return pjsua2JNI.pjmedia_tone_digit_digit_get(this.swigCPtr, this);
    }

    public void setOn_msec(short s) {
        pjsua2JNI.pjmedia_tone_digit_on_msec_set(this.swigCPtr, this, s);
    }

    public short getOn_msec() {
        return pjsua2JNI.pjmedia_tone_digit_on_msec_get(this.swigCPtr, this);
    }

    public void setOff_msec(short s) {
        pjsua2JNI.pjmedia_tone_digit_off_msec_set(this.swigCPtr, this, s);
    }

    public short getOff_msec() {
        return pjsua2JNI.pjmedia_tone_digit_off_msec_get(this.swigCPtr, this);
    }

    public void setVolume(short s) {
        pjsua2JNI.pjmedia_tone_digit_volume_set(this.swigCPtr, this, s);
    }

    public short getVolume() {
        return pjsua2JNI.pjmedia_tone_digit_volume_get(this.swigCPtr, this);
    }

    public pjmedia_tone_digit() {
        this(pjsua2JNI.new_pjmedia_tone_digit(), true);
    }
}
