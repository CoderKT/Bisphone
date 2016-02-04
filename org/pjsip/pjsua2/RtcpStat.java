package org.pjsip.pjsua2;

public class RtcpStat {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected RtcpStat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(RtcpStat rtcpStat) {
        return rtcpStat == null ? 0 : rtcpStat.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RtcpStat(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStart(TimeVal timeVal) {
        pjsua2JNI.RtcpStat_start_set(this.swigCPtr, this, TimeVal.getCPtr(timeVal), timeVal);
    }

    public TimeVal getStart() {
        long RtcpStat_start_get = pjsua2JNI.RtcpStat_start_get(this.swigCPtr, this);
        return RtcpStat_start_get == 0 ? null : new TimeVal(RtcpStat_start_get, false);
    }

    public void setTxStat(RtcpStreamStat rtcpStreamStat) {
        pjsua2JNI.RtcpStat_txStat_set(this.swigCPtr, this, RtcpStreamStat.getCPtr(rtcpStreamStat), rtcpStreamStat);
    }

    public RtcpStreamStat getTxStat() {
        long RtcpStat_txStat_get = pjsua2JNI.RtcpStat_txStat_get(this.swigCPtr, this);
        return RtcpStat_txStat_get == 0 ? null : new RtcpStreamStat(RtcpStat_txStat_get, false);
    }

    public void setRxStat(RtcpStreamStat rtcpStreamStat) {
        pjsua2JNI.RtcpStat_rxStat_set(this.swigCPtr, this, RtcpStreamStat.getCPtr(rtcpStreamStat), rtcpStreamStat);
    }

    public RtcpStreamStat getRxStat() {
        long RtcpStat_rxStat_get = pjsua2JNI.RtcpStat_rxStat_get(this.swigCPtr, this);
        return RtcpStat_rxStat_get == 0 ? null : new RtcpStreamStat(RtcpStat_rxStat_get, false);
    }

    public void setRttUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStat_rttUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public MathStat getRttUsec() {
        long RtcpStat_rttUsec_get = pjsua2JNI.RtcpStat_rttUsec_get(this.swigCPtr, this);
        return RtcpStat_rttUsec_get == 0 ? null : new MathStat(RtcpStat_rttUsec_get, false);
    }

    public void setRtpTxLastTs(long j) {
        pjsua2JNI.RtcpStat_rtpTxLastTs_set(this.swigCPtr, this, j);
    }

    public long getRtpTxLastTs() {
        return pjsua2JNI.RtcpStat_rtpTxLastTs_get(this.swigCPtr, this);
    }

    public void setRtpTxLastSeq(int i) {
        pjsua2JNI.RtcpStat_rtpTxLastSeq_set(this.swigCPtr, this, i);
    }

    public int getRtpTxLastSeq() {
        return pjsua2JNI.RtcpStat_rtpTxLastSeq_get(this.swigCPtr, this);
    }

    public void setRxIpdvUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStat_rxIpdvUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public MathStat getRxIpdvUsec() {
        long RtcpStat_rxIpdvUsec_get = pjsua2JNI.RtcpStat_rxIpdvUsec_get(this.swigCPtr, this);
        return RtcpStat_rxIpdvUsec_get == 0 ? null : new MathStat(RtcpStat_rxIpdvUsec_get, false);
    }

    public void setRxRawJitterUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStat_rxRawJitterUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public MathStat getRxRawJitterUsec() {
        long RtcpStat_rxRawJitterUsec_get = pjsua2JNI.RtcpStat_rxRawJitterUsec_get(this.swigCPtr, this);
        return RtcpStat_rxRawJitterUsec_get == 0 ? null : new MathStat(RtcpStat_rxRawJitterUsec_get, false);
    }

    public void setPeerSdes(RtcpSdes rtcpSdes) {
        pjsua2JNI.RtcpStat_peerSdes_set(this.swigCPtr, this, RtcpSdes.getCPtr(rtcpSdes), rtcpSdes);
    }

    public RtcpSdes getPeerSdes() {
        long RtcpStat_peerSdes_get = pjsua2JNI.RtcpStat_peerSdes_get(this.swigCPtr, this);
        return RtcpStat_peerSdes_get == 0 ? null : new RtcpSdes(RtcpStat_peerSdes_get, false);
    }

    public RtcpStat() {
        this(pjsua2JNI.new_RtcpStat(), true);
    }
}
