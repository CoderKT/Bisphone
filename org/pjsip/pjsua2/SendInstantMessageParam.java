package org.pjsip.pjsua2;

public class SendInstantMessageParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SendInstantMessageParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SendInstantMessageParam sendInstantMessageParam) {
        return sendInstantMessageParam == null ? 0 : sendInstantMessageParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SendInstantMessageParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setContentType(String str) {
        pjsua2JNI.SendInstantMessageParam_contentType_set(this.swigCPtr, this, str);
    }

    public String getContentType() {
        return pjsua2JNI.SendInstantMessageParam_contentType_get(this.swigCPtr, this);
    }

    public void setContent(String str) {
        pjsua2JNI.SendInstantMessageParam_content_set(this.swigCPtr, this, str);
    }

    public String getContent() {
        return pjsua2JNI.SendInstantMessageParam_content_get(this.swigCPtr, this);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.SendInstantMessageParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public SipTxOption getTxOption() {
        long SendInstantMessageParam_txOption_get = pjsua2JNI.SendInstantMessageParam_txOption_get(this.swigCPtr, this);
        return SendInstantMessageParam_txOption_get == 0 ? null : new SipTxOption(SendInstantMessageParam_txOption_get, false);
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SendInstantMessageParam_userData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getUserData() {
        long SendInstantMessageParam_userData_get = pjsua2JNI.SendInstantMessageParam_userData_get(this.swigCPtr, this);
        return SendInstantMessageParam_userData_get == 0 ? null : new SWIGTYPE_p_void(SendInstantMessageParam_userData_get, false);
    }

    public SendInstantMessageParam() {
        this(pjsua2JNI.new_SendInstantMessageParam(), true);
    }
}
