package org.pjsip.pjsua2;

public class OnNatCheckStunServersCompleteParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnNatCheckStunServersCompleteParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam) {
        return onNatCheckStunServersCompleteParam == null ? 0 : onNatCheckStunServersCompleteParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnNatCheckStunServersCompleteParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnNatCheckStunServersCompleteParam_userData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getUserData() {
        long OnNatCheckStunServersCompleteParam_userData_get = pjsua2JNI.OnNatCheckStunServersCompleteParam_userData_get(this.swigCPtr, this);
        return OnNatCheckStunServersCompleteParam_userData_get == 0 ? null : new SWIGTYPE_p_void(OnNatCheckStunServersCompleteParam_userData_get, false);
    }

    public void setStatus(int i) {
        pjsua2JNI.OnNatCheckStunServersCompleteParam_status_set(this.swigCPtr, this, i);
    }

    public int getStatus() {
        return pjsua2JNI.OnNatCheckStunServersCompleteParam_status_get(this.swigCPtr, this);
    }

    public void setName(String str) {
        pjsua2JNI.OnNatCheckStunServersCompleteParam_name_set(this.swigCPtr, this, str);
    }

    public String getName() {
        return pjsua2JNI.OnNatCheckStunServersCompleteParam_name_get(this.swigCPtr, this);
    }

    public void setAddr(String str) {
        pjsua2JNI.OnNatCheckStunServersCompleteParam_addr_set(this.swigCPtr, this, str);
    }

    public String getAddr() {
        return pjsua2JNI.OnNatCheckStunServersCompleteParam_addr_get(this.swigCPtr, this);
    }

    public OnNatCheckStunServersCompleteParam() {
        this(pjsua2JNI.new_OnNatCheckStunServersCompleteParam(), true);
    }
}
