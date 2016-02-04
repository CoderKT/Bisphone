package org.pjsip.pjsua2;

public class TxErrorEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TxErrorEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(TxErrorEvent txErrorEvent) {
        return txErrorEvent == null ? 0 : txErrorEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TxErrorEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setTdata(SipTxData sipTxData) {
        pjsua2JNI.TxErrorEvent_tdata_set(this.swigCPtr, this, SipTxData.getCPtr(sipTxData), sipTxData);
    }

    public SipTxData getTdata() {
        long TxErrorEvent_tdata_get = pjsua2JNI.TxErrorEvent_tdata_get(this.swigCPtr, this);
        return TxErrorEvent_tdata_get == 0 ? null : new SipTxData(TxErrorEvent_tdata_get, false);
    }

    public void setTsx(SipTransaction sipTransaction) {
        pjsua2JNI.TxErrorEvent_tsx_set(this.swigCPtr, this, SipTransaction.getCPtr(sipTransaction), sipTransaction);
    }

    public SipTransaction getTsx() {
        long TxErrorEvent_tsx_get = pjsua2JNI.TxErrorEvent_tsx_get(this.swigCPtr, this);
        return TxErrorEvent_tsx_get == 0 ? null : new SipTransaction(TxErrorEvent_tsx_get, false);
    }

    public TxErrorEvent() {
        this(pjsua2JNI.new_TxErrorEvent(), true);
    }
}
