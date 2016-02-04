package org.pjsip.pjsua2;

public class PendingJob {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected PendingJob(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(PendingJob pendingJob) {
        return pendingJob == null ? 0 : pendingJob.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PendingJob(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void execute(boolean z) {
        pjsua2JNI.PendingJob_execute(this.swigCPtr, this, z);
    }
}
