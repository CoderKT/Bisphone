package org.pjsip.pjsua2;

public class OnSelectAccountParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnSelectAccountParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnSelectAccountParam onSelectAccountParam) {
        return onSelectAccountParam == null ? 0 : onSelectAccountParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnSelectAccountParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnSelectAccountParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long OnSelectAccountParam_rdata_get = pjsua2JNI.OnSelectAccountParam_rdata_get(this.swigCPtr, this);
        return OnSelectAccountParam_rdata_get == 0 ? null : new SipRxData(OnSelectAccountParam_rdata_get, false);
    }

    public void setAccountIndex(int i) {
        pjsua2JNI.OnSelectAccountParam_accountIndex_set(this.swigCPtr, this, i);
    }

    public int getAccountIndex() {
        return pjsua2JNI.OnSelectAccountParam_accountIndex_get(this.swigCPtr, this);
    }

    public OnSelectAccountParam() {
        this(pjsua2JNI.new_OnSelectAccountParam(), true);
    }
}
