package org.pjsip.pjsua2;

public class CallInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected CallInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(CallInfo callInfo) {
        return callInfo == null ? 0 : callInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setId(int i) {
        pjsua2JNI.CallInfo_id_set(this.swigCPtr, this, i);
    }

    public int getId() {
        return pjsua2JNI.CallInfo_id_get(this.swigCPtr, this);
    }

    public void setRole(pjsip_role_e org_pjsip_pjsua2_pjsip_role_e) {
        pjsua2JNI.CallInfo_role_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_role_e.swigValue());
    }

    public pjsip_role_e getRole() {
        return pjsip_role_e.swigToEnum(pjsua2JNI.CallInfo_role_get(this.swigCPtr, this));
    }

    public void setAccId(int i) {
        pjsua2JNI.CallInfo_accId_set(this.swigCPtr, this, i);
    }

    public int getAccId() {
        return pjsua2JNI.CallInfo_accId_get(this.swigCPtr, this);
    }

    public void setLocalUri(String str) {
        pjsua2JNI.CallInfo_localUri_set(this.swigCPtr, this, str);
    }

    public String getLocalUri() {
        return pjsua2JNI.CallInfo_localUri_get(this.swigCPtr, this);
    }

    public void setLocalContact(String str) {
        pjsua2JNI.CallInfo_localContact_set(this.swigCPtr, this, str);
    }

    public String getLocalContact() {
        return pjsua2JNI.CallInfo_localContact_get(this.swigCPtr, this);
    }

    public void setRemoteUri(String str) {
        pjsua2JNI.CallInfo_remoteUri_set(this.swigCPtr, this, str);
    }

    public String getRemoteUri() {
        return pjsua2JNI.CallInfo_remoteUri_get(this.swigCPtr, this);
    }

    public void setRemoteContact(String str) {
        pjsua2JNI.CallInfo_remoteContact_set(this.swigCPtr, this, str);
    }

    public String getRemoteContact() {
        return pjsua2JNI.CallInfo_remoteContact_get(this.swigCPtr, this);
    }

    public void setCallIdString(String str) {
        pjsua2JNI.CallInfo_callIdString_set(this.swigCPtr, this, str);
    }

    public String getCallIdString() {
        return pjsua2JNI.CallInfo_callIdString_get(this.swigCPtr, this);
    }

    public void setSetting(CallSetting callSetting) {
        pjsua2JNI.CallInfo_setting_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public CallSetting getSetting() {
        long CallInfo_setting_get = pjsua2JNI.CallInfo_setting_get(this.swigCPtr, this);
        return CallInfo_setting_get == 0 ? null : new CallSetting(CallInfo_setting_get, false);
    }

    public void setState(pjsip_inv_state org_pjsip_pjsua2_pjsip_inv_state) {
        pjsua2JNI.CallInfo_state_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_inv_state.swigValue());
    }

    public pjsip_inv_state getState() {
        return pjsip_inv_state.swigToEnum(pjsua2JNI.CallInfo_state_get(this.swigCPtr, this));
    }

    public void setStateText(String str) {
        pjsua2JNI.CallInfo_stateText_set(this.swigCPtr, this, str);
    }

    public String getStateText() {
        return pjsua2JNI.CallInfo_stateText_get(this.swigCPtr, this);
    }

    public void setLastStatusCode(pjsip_status_code org_pjsip_pjsua2_pjsip_status_code) {
        pjsua2JNI.CallInfo_lastStatusCode_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_status_code.swigValue());
    }

    public pjsip_status_code getLastStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.CallInfo_lastStatusCode_get(this.swigCPtr, this));
    }

    public void setLastReason(String str) {
        pjsua2JNI.CallInfo_lastReason_set(this.swigCPtr, this, str);
    }

    public String getLastReason() {
        return pjsua2JNI.CallInfo_lastReason_get(this.swigCPtr, this);
    }

    public void setMedia(CallMediaInfoVector callMediaInfoVector) {
        pjsua2JNI.CallInfo_media_set(this.swigCPtr, this, CallMediaInfoVector.getCPtr(callMediaInfoVector), callMediaInfoVector);
    }

    public CallMediaInfoVector getMedia() {
        long CallInfo_media_get = pjsua2JNI.CallInfo_media_get(this.swigCPtr, this);
        return CallInfo_media_get == 0 ? null : new CallMediaInfoVector(CallInfo_media_get, false);
    }

    public void setProvMedia(CallMediaInfoVector callMediaInfoVector) {
        pjsua2JNI.CallInfo_provMedia_set(this.swigCPtr, this, CallMediaInfoVector.getCPtr(callMediaInfoVector), callMediaInfoVector);
    }

    public CallMediaInfoVector getProvMedia() {
        long CallInfo_provMedia_get = pjsua2JNI.CallInfo_provMedia_get(this.swigCPtr, this);
        return CallInfo_provMedia_get == 0 ? null : new CallMediaInfoVector(CallInfo_provMedia_get, false);
    }

    public void setConnectDuration(TimeVal timeVal) {
        pjsua2JNI.CallInfo_connectDuration_set(this.swigCPtr, this, TimeVal.getCPtr(timeVal), timeVal);
    }

    public TimeVal getConnectDuration() {
        long CallInfo_connectDuration_get = pjsua2JNI.CallInfo_connectDuration_get(this.swigCPtr, this);
        return CallInfo_connectDuration_get == 0 ? null : new TimeVal(CallInfo_connectDuration_get, false);
    }

    public void setTotalDuration(TimeVal timeVal) {
        pjsua2JNI.CallInfo_totalDuration_set(this.swigCPtr, this, TimeVal.getCPtr(timeVal), timeVal);
    }

    public TimeVal getTotalDuration() {
        long CallInfo_totalDuration_get = pjsua2JNI.CallInfo_totalDuration_get(this.swigCPtr, this);
        return CallInfo_totalDuration_get == 0 ? null : new TimeVal(CallInfo_totalDuration_get, false);
    }

    public void setRemOfferer(boolean z) {
        pjsua2JNI.CallInfo_remOfferer_set(this.swigCPtr, this, z);
    }

    public boolean getRemOfferer() {
        return pjsua2JNI.CallInfo_remOfferer_get(this.swigCPtr, this);
    }

    public void setRemAudioCount(long j) {
        pjsua2JNI.CallInfo_remAudioCount_set(this.swigCPtr, this, j);
    }

    public long getRemAudioCount() {
        return pjsua2JNI.CallInfo_remAudioCount_get(this.swigCPtr, this);
    }

    public void setRemVideoCount(long j) {
        pjsua2JNI.CallInfo_remVideoCount_set(this.swigCPtr, this, j);
    }

    public long getRemVideoCount() {
        return pjsua2JNI.CallInfo_remVideoCount_get(this.swigCPtr, this);
    }

    public CallInfo() {
        this(pjsua2JNI.new_CallInfo(), true);
    }
}
