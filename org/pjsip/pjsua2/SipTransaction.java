package org.pjsip.pjsua2;

public class SipTransaction {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SipTransaction(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SipTransaction sipTransaction) {
        return sipTransaction == null ? 0 : sipTransaction.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipTransaction(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setRole(pjsip_role_e org_pjsip_pjsua2_pjsip_role_e) {
        pjsua2JNI.SipTransaction_role_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_role_e.swigValue());
    }

    public pjsip_role_e getRole() {
        return pjsip_role_e.swigToEnum(pjsua2JNI.SipTransaction_role_get(this.swigCPtr, this));
    }

    public void setMethod(String str) {
        pjsua2JNI.SipTransaction_method_set(this.swigCPtr, this, str);
    }

    public String getMethod() {
        return pjsua2JNI.SipTransaction_method_get(this.swigCPtr, this);
    }

    public void setStatusCode(int i) {
        pjsua2JNI.SipTransaction_statusCode_set(this.swigCPtr, this, i);
    }

    public int getStatusCode() {
        return pjsua2JNI.SipTransaction_statusCode_get(this.swigCPtr, this);
    }

    public void setStatusText(String str) {
        pjsua2JNI.SipTransaction_statusText_set(this.swigCPtr, this, str);
    }

    public String getStatusText() {
        return pjsua2JNI.SipTransaction_statusText_get(this.swigCPtr, this);
    }

    public void setState(pjsip_tsx_state_e org_pjsip_pjsua2_pjsip_tsx_state_e) {
        pjsua2JNI.SipTransaction_state_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_tsx_state_e.swigValue());
    }

    public pjsip_tsx_state_e getState() {
        return pjsip_tsx_state_e.swigToEnum(pjsua2JNI.SipTransaction_state_get(this.swigCPtr, this));
    }

    public void setLastTx(SipTxData sipTxData) {
        pjsua2JNI.SipTransaction_lastTx_set(this.swigCPtr, this, SipTxData.getCPtr(sipTxData), sipTxData);
    }

    public SipTxData getLastTx() {
        long SipTransaction_lastTx_get = pjsua2JNI.SipTransaction_lastTx_get(this.swigCPtr, this);
        return SipTransaction_lastTx_get == 0 ? null : new SipTxData(SipTransaction_lastTx_get, false);
    }

    public void setPjTransaction(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipTransaction_pjTransaction_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getPjTransaction() {
        long SipTransaction_pjTransaction_get = pjsua2JNI.SipTransaction_pjTransaction_get(this.swigCPtr, this);
        return SipTransaction_pjTransaction_get == 0 ? null : new SWIGTYPE_p_void(SipTransaction_pjTransaction_get, false);
    }

    public SipTransaction() {
        this(pjsua2JNI.new_SipTransaction(), true);
    }
}
