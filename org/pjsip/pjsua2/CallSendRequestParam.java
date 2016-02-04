package org.pjsip.pjsua2;

public class CallSendRequestParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CallSendRequestParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CallSendRequestParam callSendRequestParam) {
        return callSendRequestParam == null ? 0 : callSendRequestParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallSendRequestParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMethod(String str) {
        pjsua2JNI.CallSendRequestParam_method_set(this.swigCPtr, this, str);
    }

    public String getMethod() {
        return pjsua2JNI.CallSendRequestParam_method_get(this.swigCPtr, this);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.CallSendRequestParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public SipTxOption getTxOption() {
        long CallSendRequestParam_txOption_get = pjsua2JNI.CallSendRequestParam_txOption_get(this.swigCPtr, this);
        return CallSendRequestParam_txOption_get == 0 ? null : new SipTxOption(CallSendRequestParam_txOption_get, false);
    }

    public CallSendRequestParam() {
        this(pjsua2JNI.new_CallSendRequestParam(), true);
    }
}
