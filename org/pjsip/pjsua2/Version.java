package org.pjsip.pjsua2;

public class Version {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Version(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Version version) {
        return version == null ? 0 : version.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Version(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setMajor(int i) {
        pjsua2JNI.Version_major_set(this.swigCPtr, this, i);
    }

    public int getMajor() {
        return pjsua2JNI.Version_major_get(this.swigCPtr, this);
    }

    public void setMinor(int i) {
        pjsua2JNI.Version_minor_set(this.swigCPtr, this, i);
    }

    public int getMinor() {
        return pjsua2JNI.Version_minor_get(this.swigCPtr, this);
    }

    public void setRev(int i) {
        pjsua2JNI.Version_rev_set(this.swigCPtr, this, i);
    }

    public int getRev() {
        return pjsua2JNI.Version_rev_get(this.swigCPtr, this);
    }

    public void setSuffix(String str) {
        pjsua2JNI.Version_suffix_set(this.swigCPtr, this, str);
    }

    public String getSuffix() {
        return pjsua2JNI.Version_suffix_get(this.swigCPtr, this);
    }

    public void setFull(String str) {
        pjsua2JNI.Version_full_set(this.swigCPtr, this, str);
    }

    public String getFull() {
        return pjsua2JNI.Version_full_get(this.swigCPtr, this);
    }

    public void setNumeric(long j) {
        pjsua2JNI.Version_numeric_set(this.swigCPtr, this, j);
    }

    public long getNumeric() {
        return pjsua2JNI.Version_numeric_get(this.swigCPtr, this);
    }

    public Version() {
        this(pjsua2JNI.new_Version(), true);
    }
}
