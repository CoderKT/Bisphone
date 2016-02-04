package org.pjsip.pjsua2;

public class LogEntry {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected LogEntry(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(LogEntry logEntry) {
        return logEntry == null ? 0 : logEntry.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_LogEntry(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setLevel(int i) {
        pjsua2JNI.LogEntry_level_set(this.swigCPtr, this, i);
    }

    public int getLevel() {
        return pjsua2JNI.LogEntry_level_get(this.swigCPtr, this);
    }

    public void setMsg(String str) {
        pjsua2JNI.LogEntry_msg_set(this.swigCPtr, this, str);
    }

    public String getMsg() {
        return pjsua2JNI.LogEntry_msg_get(this.swigCPtr, this);
    }

    public void setThreadId(int i) {
        pjsua2JNI.LogEntry_threadId_set(this.swigCPtr, this, i);
    }

    public int getThreadId() {
        return pjsua2JNI.LogEntry_threadId_get(this.swigCPtr, this);
    }

    public void setThreadName(String str) {
        pjsua2JNI.LogEntry_threadName_set(this.swigCPtr, this, str);
    }

    public String getThreadName() {
        return pjsua2JNI.LogEntry_threadName_get(this.swigCPtr, this);
    }

    public LogEntry() {
        this(pjsua2JNI.new_LogEntry(), true);
    }
}
