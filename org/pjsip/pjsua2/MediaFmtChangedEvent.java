package org.pjsip.pjsua2;

public class MediaFmtChangedEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected MediaFmtChangedEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MediaFmtChangedEvent mediaFmtChangedEvent) {
        return mediaFmtChangedEvent == null ? 0 : mediaFmtChangedEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFmtChangedEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNewWidth(long j) {
        pjsua2JNI.MediaFmtChangedEvent_newWidth_set(this.swigCPtr, this, j);
    }

    public long getNewWidth() {
        return pjsua2JNI.MediaFmtChangedEvent_newWidth_get(this.swigCPtr, this);
    }

    public void setNewHeight(long j) {
        pjsua2JNI.MediaFmtChangedEvent_newHeight_set(this.swigCPtr, this, j);
    }

    public long getNewHeight() {
        return pjsua2JNI.MediaFmtChangedEvent_newHeight_get(this.swigCPtr, this);
    }

    public MediaFmtChangedEvent() {
        this(pjsua2JNI.new_MediaFmtChangedEvent(), true);
    }
}
