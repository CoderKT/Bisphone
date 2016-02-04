package org.pjsip.pjsua2;

public class TsxStateEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TsxStateEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(TsxStateEvent tsxStateEvent) {
        return tsxStateEvent == null ? 0 : tsxStateEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TsxStateEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setTsx(SipTransaction sipTransaction) {
        pjsua2JNI.TsxStateEvent_tsx_set(this.swigCPtr, this, SipTransaction.getCPtr(sipTransaction), sipTransaction);
    }

    public SipTransaction getTsx() {
        long TsxStateEvent_tsx_get = pjsua2JNI.TsxStateEvent_tsx_get(this.swigCPtr, this);
        return TsxStateEvent_tsx_get == 0 ? null : new SipTransaction(TsxStateEvent_tsx_get, false);
    }

    public void setPrevState(pjsip_tsx_state_e org_pjsip_pjsua2_pjsip_tsx_state_e) {
        pjsua2JNI.TsxStateEvent_prevState_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_tsx_state_e.swigValue());
    }

    public pjsip_tsx_state_e getPrevState() {
        return pjsip_tsx_state_e.swigToEnum(pjsua2JNI.TsxStateEvent_prevState_get(this.swigCPtr, this));
    }

    public void setType(pjsip_event_id_e org_pjsip_pjsua2_pjsip_event_id_e) {
        pjsua2JNI.TsxStateEvent_type_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_event_id_e.swigValue());
    }

    public pjsip_event_id_e getType() {
        return pjsip_event_id_e.swigToEnum(pjsua2JNI.TsxStateEvent_type_get(this.swigCPtr, this));
    }

    public TsxStateEvent() {
        this(pjsua2JNI.new_TsxStateEvent(), true);
    }
}
