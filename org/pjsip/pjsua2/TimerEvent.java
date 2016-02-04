package org.pjsip.pjsua2;

public class TimerEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TimerEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(TimerEvent timerEvent) {
        return timerEvent == null ? 0 : timerEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TimerEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setEntry(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.TimerEvent_entry_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getEntry() {
        long TimerEvent_entry_get = pjsua2JNI.TimerEvent_entry_get(this.swigCPtr, this);
        return TimerEvent_entry_get == 0 ? null : new SWIGTYPE_p_void(TimerEvent_entry_get, false);
    }

    public TimerEvent() {
        this(pjsua2JNI.new_TimerEvent(), true);
    }
}
