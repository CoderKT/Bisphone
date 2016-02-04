package org.pjsip.pjsua2;

public class pj_qos_params {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected pj_qos_params(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(pj_qos_params org_pjsip_pjsua2_pj_qos_params) {
        return org_pjsip_pjsua2_pj_qos_params == null ? 0 : org_pjsip_pjsua2_pj_qos_params.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_pj_qos_params(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setFlags(short s) {
        pjsua2JNI.pj_qos_params_flags_set(this.swigCPtr, this, s);
    }

    public short getFlags() {
        return pjsua2JNI.pj_qos_params_flags_get(this.swigCPtr, this);
    }

    public void setDscp_val(short s) {
        pjsua2JNI.pj_qos_params_dscp_val_set(this.swigCPtr, this, s);
    }

    public short getDscp_val() {
        return pjsua2JNI.pj_qos_params_dscp_val_get(this.swigCPtr, this);
    }

    public void setSo_prio(short s) {
        pjsua2JNI.pj_qos_params_so_prio_set(this.swigCPtr, this, s);
    }

    public short getSo_prio() {
        return pjsua2JNI.pj_qos_params_so_prio_get(this.swigCPtr, this);
    }

    public void setWmm_prio(pj_qos_wmm_prio org_pjsip_pjsua2_pj_qos_wmm_prio) {
        pjsua2JNI.pj_qos_params_wmm_prio_set(this.swigCPtr, this, org_pjsip_pjsua2_pj_qos_wmm_prio.swigValue());
    }

    public pj_qos_wmm_prio getWmm_prio() {
        return pj_qos_wmm_prio.swigToEnum(pjsua2JNI.pj_qos_params_wmm_prio_get(this.swigCPtr, this));
    }

    public pj_qos_params() {
        this(pjsua2JNI.new_pj_qos_params(), true);
    }
}
