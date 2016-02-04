package org.pjsip.pjsua2;

public class SipRxData {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SipRxData(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SipRxData sipRxData) {
        return sipRxData == null ? 0 : sipRxData.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipRxData(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setInfo(String str) {
        pjsua2JNI.SipRxData_info_set(this.swigCPtr, this, str);
    }

    public String getInfo() {
        return pjsua2JNI.SipRxData_info_get(this.swigCPtr, this);
    }

    public void setWholeMsg(String str) {
        pjsua2JNI.SipRxData_wholeMsg_set(this.swigCPtr, this, str);
    }

    public String getWholeMsg() {
        return pjsua2JNI.SipRxData_wholeMsg_get(this.swigCPtr, this);
    }

    public void setSrcAddress(String str) {
        pjsua2JNI.SipRxData_srcAddress_set(this.swigCPtr, this, str);
    }

    public String getSrcAddress() {
        return pjsua2JNI.SipRxData_srcAddress_get(this.swigCPtr, this);
    }

    public void setPjRxData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipRxData_pjRxData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getPjRxData() {
        long SipRxData_pjRxData_get = pjsua2JNI.SipRxData_pjRxData_get(this.swigCPtr, this);
        return SipRxData_pjRxData_get == 0 ? null : new SWIGTYPE_p_void(SipRxData_pjRxData_get, false);
    }

    public SipRxData() {
        this(pjsua2JNI.new_SipRxData(), true);
    }
}
