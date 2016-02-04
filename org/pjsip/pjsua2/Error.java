package org.pjsip.pjsua2;

public class Error extends Exception {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Error(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Error error) {
        return error == null ? 0 : error.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Error(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public String getMessage() {
        return getTitle();
    }

    public void setStatus(int i) {
        pjsua2JNI.Error_status_set(this.swigCPtr, this, i);
    }

    public int getStatus() {
        return pjsua2JNI.Error_status_get(this.swigCPtr, this);
    }

    public void setTitle(String str) {
        pjsua2JNI.Error_title_set(this.swigCPtr, this, str);
    }

    public String getTitle() {
        return pjsua2JNI.Error_title_get(this.swigCPtr, this);
    }

    public void setReason(String str) {
        pjsua2JNI.Error_reason_set(this.swigCPtr, this, str);
    }

    public String getReason() {
        return pjsua2JNI.Error_reason_get(this.swigCPtr, this);
    }

    public void setSrcFile(String str) {
        pjsua2JNI.Error_srcFile_set(this.swigCPtr, this, str);
    }

    public String getSrcFile() {
        return pjsua2JNI.Error_srcFile_get(this.swigCPtr, this);
    }

    public void setSrcLine(int i) {
        pjsua2JNI.Error_srcLine_set(this.swigCPtr, this, i);
    }

    public int getSrcLine() {
        return pjsua2JNI.Error_srcLine_get(this.swigCPtr, this);
    }

    public String info(boolean z) {
        return pjsua2JNI.Error_info__SWIG_0(this.swigCPtr, this, z);
    }

    public String info() {
        return pjsua2JNI.Error_info__SWIG_1(this.swigCPtr, this);
    }

    public Error() {
        this(pjsua2JNI.new_Error__SWIG_0(), true);
    }

    public Error(int i, String str, String str2, String str3, int i2) {
        this(pjsua2JNI.new_Error__SWIG_1(i, str, str2, str3, i2), true);
    }
}
