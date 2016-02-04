package org.pjsip.pjsua2;

public class TxMsgEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TxMsgEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(TxMsgEvent txMsgEvent) {
        return txMsgEvent == null ? 0 : txMsgEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TxMsgEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setTdata(SipTxData sipTxData) {
        pjsua2JNI.TxMsgEvent_tdata_set(this.swigCPtr, this, SipTxData.getCPtr(sipTxData), sipTxData);
    }

    public SipTxData getTdata() {
        long TxMsgEvent_tdata_get = pjsua2JNI.TxMsgEvent_tdata_get(this.swigCPtr, this);
        return TxMsgEvent_tdata_get == 0 ? null : new SipTxData(TxMsgEvent_tdata_get, false);
    }

    public TxMsgEvent() {
        this(pjsua2JNI.new_TxMsgEvent(), true);
    }
}
