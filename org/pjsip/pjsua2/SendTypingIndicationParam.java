package org.pjsip.pjsua2;

public class SendTypingIndicationParam {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SendTypingIndicationParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SendTypingIndicationParam sendTypingIndicationParam) {
        return sendTypingIndicationParam == null ? 0 : sendTypingIndicationParam.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SendTypingIndicationParam(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setIsTyping(boolean z) {
        pjsua2JNI.SendTypingIndicationParam_isTyping_set(this.swigCPtr, this, z);
    }

    public boolean getIsTyping() {
        return pjsua2JNI.SendTypingIndicationParam_isTyping_get(this.swigCPtr, this);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.SendTypingIndicationParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public SipTxOption getTxOption() {
        long SendTypingIndicationParam_txOption_get = pjsua2JNI.SendTypingIndicationParam_txOption_get(this.swigCPtr, this);
        return SendTypingIndicationParam_txOption_get == 0 ? null : new SipTxOption(SendTypingIndicationParam_txOption_get, false);
    }

    public SendTypingIndicationParam() {
        this(pjsua2JNI.new_SendTypingIndicationParam(), true);
    }
}
