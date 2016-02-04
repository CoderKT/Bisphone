package org.pjsip.pjsua2;

public class TimeVal {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TimeVal(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(TimeVal timeVal) {
        return timeVal == null ? 0 : timeVal.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TimeVal(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSec(int i) {
        pjsua2JNI.TimeVal_sec_set(this.swigCPtr, this, i);
    }

    public int getSec() {
        return pjsua2JNI.TimeVal_sec_get(this.swigCPtr, this);
    }

    public void setMsec(int i) {
        pjsua2JNI.TimeVal_msec_set(this.swigCPtr, this, i);
    }

    public int getMsec() {
        return pjsua2JNI.TimeVal_msec_get(this.swigCPtr, this);
    }

    public TimeVal() {
        this(pjsua2JNI.new_TimeVal(), true);
    }
}
