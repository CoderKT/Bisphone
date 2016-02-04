package org.pjsip.pjsua2;

public class RtcpStreamStat {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected RtcpStreamStat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(RtcpStreamStat rtcpStreamStat) {
        return rtcpStreamStat == null ? 0 : rtcpStreamStat.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_RtcpStreamStat(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setUpdate(TimeVal timeVal) {
        pjsua2JNI.RtcpStreamStat_update_set(this.swigCPtr, this, TimeVal.getCPtr(timeVal), timeVal);
    }

    public TimeVal getUpdate() {
        long RtcpStreamStat_update_get = pjsua2JNI.RtcpStreamStat_update_get(this.swigCPtr, this);
        return RtcpStreamStat_update_get == 0 ? null : new TimeVal(RtcpStreamStat_update_get, false);
    }

    public void setUpdateCount(long j) {
        pjsua2JNI.RtcpStreamStat_updateCount_set(this.swigCPtr, this, j);
    }

    public long getUpdateCount() {
        return pjsua2JNI.RtcpStreamStat_updateCount_get(this.swigCPtr, this);
    }

    public void setPkt(long j) {
        pjsua2JNI.RtcpStreamStat_pkt_set(this.swigCPtr, this, j);
    }

    public long getPkt() {
        return pjsua2JNI.RtcpStreamStat_pkt_get(this.swigCPtr, this);
    }

    public void setBytes(long j) {
        pjsua2JNI.RtcpStreamStat_bytes_set(this.swigCPtr, this, j);
    }

    public long getBytes() {
        return pjsua2JNI.RtcpStreamStat_bytes_get(this.swigCPtr, this);
    }

    public void setDiscard(long j) {
        pjsua2JNI.RtcpStreamStat_discard_set(this.swigCPtr, this, j);
    }

    public long getDiscard() {
        return pjsua2JNI.RtcpStreamStat_discard_get(this.swigCPtr, this);
    }

    public void setLoss(long j) {
        pjsua2JNI.RtcpStreamStat_loss_set(this.swigCPtr, this, j);
    }

    public long getLoss() {
        return pjsua2JNI.RtcpStreamStat_loss_get(this.swigCPtr, this);
    }

    public void setReorder(long j) {
        pjsua2JNI.RtcpStreamStat_reorder_set(this.swigCPtr, this, j);
    }

    public long getReorder() {
        return pjsua2JNI.RtcpStreamStat_reorder_get(this.swigCPtr, this);
    }

    public void setDup(long j) {
        pjsua2JNI.RtcpStreamStat_dup_set(this.swigCPtr, this, j);
    }

    public long getDup() {
        return pjsua2JNI.RtcpStreamStat_dup_get(this.swigCPtr, this);
    }

    public void setLossPeriodUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStreamStat_lossPeriodUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public MathStat getLossPeriodUsec() {
        long RtcpStreamStat_lossPeriodUsec_get = pjsua2JNI.RtcpStreamStat_lossPeriodUsec_get(this.swigCPtr, this);
        return RtcpStreamStat_lossPeriodUsec_get == 0 ? null : new MathStat(RtcpStreamStat_lossPeriodUsec_get, false);
    }

    public void setJitterUsec(MathStat mathStat) {
        pjsua2JNI.RtcpStreamStat_jitterUsec_set(this.swigCPtr, this, MathStat.getCPtr(mathStat), mathStat);
    }

    public MathStat getJitterUsec() {
        long RtcpStreamStat_jitterUsec_get = pjsua2JNI.RtcpStreamStat_jitterUsec_get(this.swigCPtr, this);
        return RtcpStreamStat_jitterUsec_get == 0 ? null : new MathStat(RtcpStreamStat_jitterUsec_get, false);
    }

    public RtcpStreamStat() {
        this(pjsua2JNI.new_RtcpStreamStat(), true);
    }
}
