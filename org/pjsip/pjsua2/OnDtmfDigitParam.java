package org.pjsip.pjsua2;

public class OnDtmfDigitParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnDtmfDigitParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnDtmfDigitParam onDtmfDigitParam) {
        return onDtmfDigitParam == null ? 0 : onDtmfDigitParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnDtmfDigitParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setDigit(String str) {
        pjsua2JNI.OnDtmfDigitParam_digit_set(this.swigCPtr, this, str);
    }

    public String getDigit() {
        return pjsua2JNI.OnDtmfDigitParam_digit_get(this.swigCPtr, this);
    }

    public OnDtmfDigitParam() {
        this(pjsua2JNI.new_OnDtmfDigitParam(), true);
    }
}
