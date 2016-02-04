package org.pjsip.pjsua2;

public class ToneDigit extends pjmedia_tone_digit {
    private long swigCPtr;

    protected ToneDigit(long j, boolean z) {
        super(pjsua2JNI.ToneDigit_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(ToneDigit toneDigit) {
        return toneDigit == null ? 0 : toneDigit.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDigit(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public ToneDigit() {
        this(pjsua2JNI.new_ToneDigit(), true);
    }
}
