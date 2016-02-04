package org.pjsip.pjsua2;

public class Media {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Media(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Media media) {
        return media == null ? 0 : media.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Media(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public pjmedia_type getType() {
        return pjmedia_type.swigToEnum(pjsua2JNI.Media_getType(this.swigCPtr, this));
    }
}
