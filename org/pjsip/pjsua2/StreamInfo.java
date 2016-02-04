package org.pjsip.pjsua2;

public class StreamInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected StreamInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(StreamInfo streamInfo) {
        return streamInfo == null ? 0 : streamInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_StreamInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setType(pjmedia_type org_pjsip_pjsua2_pjmedia_type) {
        pjsua2JNI.StreamInfo_type_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_type.swigValue());
    }

    public pjmedia_type getType() {
        return pjmedia_type.swigToEnum(pjsua2JNI.StreamInfo_type_get(this.swigCPtr, this));
    }

    public void setProto(pjmedia_tp_proto org_pjsip_pjsua2_pjmedia_tp_proto) {
        pjsua2JNI.StreamInfo_proto_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_tp_proto.swigValue());
    }

    public pjmedia_tp_proto getProto() {
        return pjmedia_tp_proto.swigToEnum(pjsua2JNI.StreamInfo_proto_get(this.swigCPtr, this));
    }

    public void setDir(pjmedia_dir org_pjsip_pjsua2_pjmedia_dir) {
        pjsua2JNI.StreamInfo_dir_set(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_dir.swigValue());
    }

    public pjmedia_dir getDir() {
        return pjmedia_dir.swigToEnum(pjsua2JNI.StreamInfo_dir_get(this.swigCPtr, this));
    }

    public void setRemoteRtpAddress(String str) {
        pjsua2JNI.StreamInfo_remoteRtpAddress_set(this.swigCPtr, this, str);
    }

    public String getRemoteRtpAddress() {
        return pjsua2JNI.StreamInfo_remoteRtpAddress_get(this.swigCPtr, this);
    }

    public void setRemoteRtcpAddress(String str) {
        pjsua2JNI.StreamInfo_remoteRtcpAddress_set(this.swigCPtr, this, str);
    }

    public String getRemoteRtcpAddress() {
        return pjsua2JNI.StreamInfo_remoteRtcpAddress_get(this.swigCPtr, this);
    }

    public void setTxPt(long j) {
        pjsua2JNI.StreamInfo_txPt_set(this.swigCPtr, this, j);
    }

    public long getTxPt() {
        return pjsua2JNI.StreamInfo_txPt_get(this.swigCPtr, this);
    }

    public void setRxPt(long j) {
        pjsua2JNI.StreamInfo_rxPt_set(this.swigCPtr, this, j);
    }

    public long getRxPt() {
        return pjsua2JNI.StreamInfo_rxPt_get(this.swigCPtr, this);
    }

    public void setCodecName(String str) {
        pjsua2JNI.StreamInfo_codecName_set(this.swigCPtr, this, str);
    }

    public String getCodecName() {
        return pjsua2JNI.StreamInfo_codecName_get(this.swigCPtr, this);
    }

    public void setCodecClockRate(long j) {
        pjsua2JNI.StreamInfo_codecClockRate_set(this.swigCPtr, this, j);
    }

    public long getCodecClockRate() {
        return pjsua2JNI.StreamInfo_codecClockRate_get(this.swigCPtr, this);
    }

    public void setCodecParam(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.StreamInfo_codecParam_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getCodecParam() {
        long StreamInfo_codecParam_get = pjsua2JNI.StreamInfo_codecParam_get(this.swigCPtr, this);
        return StreamInfo_codecParam_get == 0 ? null : new SWIGTYPE_p_void(StreamInfo_codecParam_get, false);
    }

    public StreamInfo() {
        this(pjsua2JNI.new_StreamInfo(), true);
    }
}
