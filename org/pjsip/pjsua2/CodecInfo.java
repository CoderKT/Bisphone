package org.pjsip.pjsua2;

public class CodecInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CodecInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CodecInfo codecInfo) {
        return codecInfo == null ? 0 : codecInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setCodecId(String str) {
        pjsua2JNI.CodecInfo_codecId_set(this.swigCPtr, this, str);
    }

    public String getCodecId() {
        return pjsua2JNI.CodecInfo_codecId_get(this.swigCPtr, this);
    }

    public void setPriority(short s) {
        pjsua2JNI.CodecInfo_priority_set(this.swigCPtr, this, s);
    }

    public short getPriority() {
        return pjsua2JNI.CodecInfo_priority_get(this.swigCPtr, this);
    }

    public void setDesc(String str) {
        pjsua2JNI.CodecInfo_desc_set(this.swigCPtr, this, str);
    }

    public String getDesc() {
        return pjsua2JNI.CodecInfo_desc_get(this.swigCPtr, this);
    }

    public CodecInfo() {
        this(pjsua2JNI.new_CodecInfo(), true);
    }
}
