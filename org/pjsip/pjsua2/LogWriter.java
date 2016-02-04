package org.pjsip.pjsua2;

public class LogWriter {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected LogWriter(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(LogWriter logWriter) {
        return logWriter == null ? 0 : logWriter.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_LogWriter(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.LogWriter_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.LogWriter_change_ownership(this, this.swigCPtr, true);
    }

    public void write(LogEntry logEntry) {
        pjsua2JNI.LogWriter_write(this.swigCPtr, this, LogEntry.getCPtr(logEntry), logEntry);
    }

    public LogWriter() {
        this(pjsua2JNI.new_LogWriter(), true);
        pjsua2JNI.LogWriter_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
