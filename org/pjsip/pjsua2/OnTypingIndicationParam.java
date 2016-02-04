package org.pjsip.pjsua2;

public class OnTypingIndicationParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected OnTypingIndicationParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(OnTypingIndicationParam onTypingIndicationParam) {
        return onTypingIndicationParam == null ? 0 : onTypingIndicationParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnTypingIndicationParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setFromUri(String str) {
        pjsua2JNI.OnTypingIndicationParam_fromUri_set(this.swigCPtr, this, str);
    }

    public String getFromUri() {
        return pjsua2JNI.OnTypingIndicationParam_fromUri_get(this.swigCPtr, this);
    }

    public void setToUri(String str) {
        pjsua2JNI.OnTypingIndicationParam_toUri_set(this.swigCPtr, this, str);
    }

    public String getToUri() {
        return pjsua2JNI.OnTypingIndicationParam_toUri_get(this.swigCPtr, this);
    }

    public void setContactUri(String str) {
        pjsua2JNI.OnTypingIndicationParam_contactUri_set(this.swigCPtr, this, str);
    }

    public String getContactUri() {
        return pjsua2JNI.OnTypingIndicationParam_contactUri_get(this.swigCPtr, this);
    }

    public void setIsTyping(boolean z) {
        pjsua2JNI.OnTypingIndicationParam_isTyping_set(this.swigCPtr, this, z);
    }

    public boolean getIsTyping() {
        return pjsua2JNI.OnTypingIndicationParam_isTyping_get(this.swigCPtr, this);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnTypingIndicationParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long OnTypingIndicationParam_rdata_get = pjsua2JNI.OnTypingIndicationParam_rdata_get(this.swigCPtr, this);
        return OnTypingIndicationParam_rdata_get == 0 ? null : new SipRxData(OnTypingIndicationParam_rdata_get, false);
    }

    public OnTypingIndicationParam() {
        this(pjsua2JNI.new_OnTypingIndicationParam(), true);
    }
}
