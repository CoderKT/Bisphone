package org.pjsip.pjsua2;

public class RtcpSdes {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected RtcpSdes(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(RtcpSdes rtcpSdes) {
        return rtcpSdes == null ? 0 : rtcpSdes.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RtcpSdes(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setCname(String str) {
        pjsua2JNI.RtcpSdes_cname_set(this.swigCPtr, this, str);
    }

    public String getCname() {
        return pjsua2JNI.RtcpSdes_cname_get(this.swigCPtr, this);
    }

    public void setName(String str) {
        pjsua2JNI.RtcpSdes_name_set(this.swigCPtr, this, str);
    }

    public String getName() {
        return pjsua2JNI.RtcpSdes_name_get(this.swigCPtr, this);
    }

    public void setEmail(String str) {
        pjsua2JNI.RtcpSdes_email_set(this.swigCPtr, this, str);
    }

    public String getEmail() {
        return pjsua2JNI.RtcpSdes_email_get(this.swigCPtr, this);
    }

    public void setPhone(String str) {
        pjsua2JNI.RtcpSdes_phone_set(this.swigCPtr, this, str);
    }

    public String getPhone() {
        return pjsua2JNI.RtcpSdes_phone_get(this.swigCPtr, this);
    }

    public void setLoc(String str) {
        pjsua2JNI.RtcpSdes_loc_set(this.swigCPtr, this, str);
    }

    public String getLoc() {
        return pjsua2JNI.RtcpSdes_loc_get(this.swigCPtr, this);
    }

    public void setTool(String str) {
        pjsua2JNI.RtcpSdes_tool_set(this.swigCPtr, this, str);
    }

    public String getTool() {
        return pjsua2JNI.RtcpSdes_tool_get(this.swigCPtr, this);
    }

    public void setNote(String str) {
        pjsua2JNI.RtcpSdes_note_set(this.swigCPtr, this, str);
    }

    public String getNote() {
        return pjsua2JNI.RtcpSdes_note_get(this.swigCPtr, this);
    }

    public RtcpSdes() {
        this(pjsua2JNI.new_RtcpSdes(), true);
    }
}
