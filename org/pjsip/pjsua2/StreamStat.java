package org.pjsip.pjsua2;

public class StreamStat {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected StreamStat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(StreamStat streamStat) {
        return streamStat == null ? 0 : streamStat.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_StreamStat(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setRtcp(RtcpStat rtcpStat) {
        pjsua2JNI.StreamStat_rtcp_set(this.swigCPtr, this, RtcpStat.getCPtr(rtcpStat), rtcpStat);
    }

    public RtcpStat getRtcp() {
        long StreamStat_rtcp_get = pjsua2JNI.StreamStat_rtcp_get(this.swigCPtr, this);
        return StreamStat_rtcp_get == 0 ? null : new RtcpStat(StreamStat_rtcp_get, false);
    }

    public void setJbuf(JbufState jbufState) {
        pjsua2JNI.StreamStat_jbuf_set(this.swigCPtr, this, JbufState.getCPtr(jbufState), jbufState);
    }

    public JbufState getJbuf() {
        long StreamStat_jbuf_get = pjsua2JNI.StreamStat_jbuf_get(this.swigCPtr, this);
        return StreamStat_jbuf_get == 0 ? null : new JbufState(StreamStat_jbuf_get, false);
    }

    public StreamStat() {
        this(pjsua2JNI.new_StreamStat(), true);
    }
}
