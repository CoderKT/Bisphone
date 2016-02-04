package org.pjsip.pjsua2;

public class MediaFormat {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected MediaFormat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MediaFormat mediaFormat) {
        return mediaFormat == null ? 0 : mediaFormat.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFormat(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setId(long j) {
        pjsua2JNI.MediaFormat_id_set(this.swigCPtr, this, j);
    }

    public long getId() {
        return pjsua2JNI.MediaFormat_id_get(this.swigCPtr, this);
    }

    public void setType(pjmedia_type org_pjsip_pjsua2_pjmedia_type) {
        pjsua2JNI.MediaFormat_type_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_type.swigValue());
    }

    public pjmedia_type getType() {
        return pjmedia_type.swigToEnum(pjsua2JNI.MediaFormat_type_get(this.swigCPtr, this));
    }

    public MediaFormat() {
        this(pjsua2JNI.new_MediaFormat(), true);
    }
}
