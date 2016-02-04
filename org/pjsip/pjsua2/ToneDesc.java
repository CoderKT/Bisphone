package org.pjsip.pjsua2;

public class ToneDesc extends pjmedia_tone_desc {
    private long swigCPtr;

    protected ToneDesc(long j, boolean z) {
        super(pjsua2JNI.ToneDesc_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(ToneDesc toneDesc) {
        return toneDesc == null ? 0 : toneDesc.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDesc(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public ToneDesc() {
        this(pjsua2JNI.new_ToneDesc(), true);
    }
}
