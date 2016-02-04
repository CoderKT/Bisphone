package org.pjsip.pjsua2;

public class BuddyInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected BuddyInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(BuddyInfo buddyInfo) {
        return buddyInfo == null ? 0 : buddyInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_BuddyInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setUri(String str) {
        pjsua2JNI.BuddyInfo_uri_set(this.swigCPtr, this, str);
    }

    public String getUri() {
        return pjsua2JNI.BuddyInfo_uri_get(this.swigCPtr, this);
    }

    public void setContact(String str) {
        pjsua2JNI.BuddyInfo_contact_set(this.swigCPtr, this, str);
    }

    public String getContact() {
        return pjsua2JNI.BuddyInfo_contact_get(this.swigCPtr, this);
    }

    public void setPresMonitorEnabled(boolean z) {
        pjsua2JNI.BuddyInfo_presMonitorEnabled_set(this.swigCPtr, this, z);
    }

    public boolean getPresMonitorEnabled() {
        return pjsua2JNI.BuddyInfo_presMonitorEnabled_get(this.swigCPtr, this);
    }

    public void setSubState(pjsip_evsub_state org_pjsip_pjsua2_pjsip_evsub_state) {
        pjsua2JNI.BuddyInfo_subState_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_evsub_state.swigValue());
    }

    public pjsip_evsub_state getSubState() {
        return pjsip_evsub_state.swigToEnum(pjsua2JNI.BuddyInfo_subState_get(this.swigCPtr, this));
    }

    public void setSubStateName(String str) {
        pjsua2JNI.BuddyInfo_subStateName_set(this.swigCPtr, this, str);
    }

    public String getSubStateName() {
        return pjsua2JNI.BuddyInfo_subStateName_get(this.swigCPtr, this);
    }

    public void setSubTermCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.BuddyInfo_subTermCode_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getSubTermCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.BuddyInfo_subTermCode_get(this.swigCPtr, this));
    }

    public void setSubTermReason(String str) {
        pjsua2JNI.BuddyInfo_subTermReason_set(this.swigCPtr, this, str);
    }

    public String getSubTermReason() {
        return pjsua2JNI.BuddyInfo_subTermReason_get(this.swigCPtr, this);
    }

    public void setPresStatus(PresenceStatus presenceStatus) {
        pjsua2JNI.BuddyInfo_presStatus_set(this.swigCPtr, this, PresenceStatus.getCPtr(presenceStatus), presenceStatus);
    }

    public PresenceStatus getPresStatus() {
        long BuddyInfo_presStatus_get = pjsua2JNI.BuddyInfo_presStatus_get(this.swigCPtr, this);
        return BuddyInfo_presStatus_get == 0 ? null : new PresenceStatus(BuddyInfo_presStatus_get, false);
    }

    public BuddyInfo() {
        this(pjsua2JNI.new_BuddyInfo(), true);
    }
}
