package org.pjsip.pjsua2;

public class RxMsgEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected RxMsgEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(RxMsgEvent rxMsgEvent) {
        return rxMsgEvent == null ? 0 : rxMsgEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RxMsgEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.RxMsgEvent_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public SipRxData getRdata() {
        long RxMsgEvent_rdata_get = pjsua2JNI.RxMsgEvent_rdata_get(this.swigCPtr, this);
        return RxMsgEvent_rdata_get == 0 ? null : new SipRxData(RxMsgEvent_rdata_get, false);
    }

    public RxMsgEvent() {
        this(pjsua2JNI.new_RxMsgEvent(), true);
    }
}
