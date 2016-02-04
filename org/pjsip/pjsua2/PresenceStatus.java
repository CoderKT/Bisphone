package org.pjsip.pjsua2;

public class PresenceStatus {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected PresenceStatus(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(PresenceStatus presenceStatus) {
        return presenceStatus == null ? 0 : presenceStatus.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PresenceStatus(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStatus(pjsua_buddy_status org_pjsip_pjsua2_pjsua_buddy_status) {
        pjsua2JNI.PresenceStatus_status_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsua_buddy_status.swigValue());
    }

    public pjsua_buddy_status getStatus() {
        return pjsua_buddy_status.swigToEnum(pjsua2JNI.PresenceStatus_status_get(this.swigCPtr, this));
    }

    public void setStatusText(String str) {
        pjsua2JNI.PresenceStatus_statusText_set(this.swigCPtr, this, str);
    }

    public String getStatusText() {
        return pjsua2JNI.PresenceStatus_statusText_get(this.swigCPtr, this);
    }

    public void setActivity(pjrpid_activity org_pjsip_pjsua2_pjrpid_activity) {
        pjsua2JNI.PresenceStatus_activity_set(this.swigCPtr, this, org_pjsip_pjsua2_pjrpid_activity.swigValue());
    }

    public pjrpid_activity getActivity() {
        return pjrpid_activity.swigToEnum(pjsua2JNI.PresenceStatus_activity_get(this.swigCPtr, this));
    }

    public void setNote(String str) {
        pjsua2JNI.PresenceStatus_note_set(this.swigCPtr, this, str);
    }

    public String getNote() {
        return pjsua2JNI.PresenceStatus_note_get(this.swigCPtr, this);
    }

    public void setRpidId(String str) {
        pjsua2JNI.PresenceStatus_rpidId_set(this.swigCPtr, this, str);
    }

    public String getRpidId() {
        return pjsua2JNI.PresenceStatus_rpidId_get(this.swigCPtr, this);
    }

    public PresenceStatus() {
        this(pjsua2JNI.new_PresenceStatus(), true);
    }
}
