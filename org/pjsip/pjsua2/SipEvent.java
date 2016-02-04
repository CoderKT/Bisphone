package org.pjsip.pjsua2;

public class SipEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected SipEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SipEvent sipEvent) {
        return sipEvent == null ? 0 : sipEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setType(pjsip_event_id_e org_pjsip_pjsua2_pjsip_event_id_e) {
        pjsua2JNI.SipEvent_type_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_event_id_e.swigValue());
    }

    public pjsip_event_id_e getType() {
        return pjsip_event_id_e.swigToEnum(pjsua2JNI.SipEvent_type_get(this.swigCPtr, this));
    }

    public void setPjEvent(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipEvent_pjEvent_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getPjEvent() {
        long SipEvent_pjEvent_get = pjsua2JNI.SipEvent_pjEvent_get(this.swigCPtr, this);
        return SipEvent_pjEvent_get == 0 ? null : new SWIGTYPE_p_void(SipEvent_pjEvent_get, false);
    }

    public SipEvent() {
        this(pjsua2JNI.new_SipEvent(), true);
    }
}
