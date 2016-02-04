package org.pjsip.pjsua2;

public class MediaEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected MediaEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MediaEvent mediaEvent) {
        return mediaEvent == null ? 0 : mediaEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setType(pjmedia_event_type org_pjsip_pjsua2_pjmedia_event_type) {
        pjsua2JNI.MediaEvent_type_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_event_type.swigValue());
    }

    public pjmedia_event_type getType() {
        return pjmedia_event_type.swigToEnum(pjsua2JNI.MediaEvent_type_get(this.swigCPtr, this));
    }

    public void setPjMediaEvent(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.MediaEvent_pjMediaEvent_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getPjMediaEvent() {
        long MediaEvent_pjMediaEvent_get = pjsua2JNI.MediaEvent_pjMediaEvent_get(this.swigCPtr, this);
        return MediaEvent_pjMediaEvent_get == 0 ? null : new SWIGTYPE_p_void(MediaEvent_pjMediaEvent_get, false);
    }

    public MediaEvent() {
        this(pjsua2JNI.new_MediaEvent(), true);
    }
}
