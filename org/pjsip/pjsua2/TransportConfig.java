package org.pjsip.pjsua2;

public class TransportConfig extends PersistentObject {
    private long swigCPtr;

    protected TransportConfig(long j, boolean z) {
        super(pjsua2JNI.TransportConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(TransportConfig transportConfig) {
        return transportConfig == null ? 0 : transportConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TransportConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setPort(long j) {
        pjsua2JNI.TransportConfig_port_set(this.swigCPtr, this, j);
    }

    public long getPort() {
        return pjsua2JNI.TransportConfig_port_get(this.swigCPtr, this);
    }

    public void setPortRange(long j) {
        pjsua2JNI.TransportConfig_portRange_set(this.swigCPtr, this, j);
    }

    public long getPortRange() {
        return pjsua2JNI.TransportConfig_portRange_get(this.swigCPtr, this);
    }

    public void setPublicAddress(String str) {
        pjsua2JNI.TransportConfig_publicAddress_set(this.swigCPtr, this, str);
    }

    public String getPublicAddress() {
        return pjsua2JNI.TransportConfig_publicAddress_get(this.swigCPtr, this);
    }

    public void setBoundAddress(String str) {
        pjsua2JNI.TransportConfig_boundAddress_set(this.swigCPtr, this, str);
    }

    public String getBoundAddress() {
        return pjsua2JNI.TransportConfig_boundAddress_get(this.swigCPtr, this);
    }

    public void setTlsConfig(TlsConfig tlsConfig) {
        pjsua2JNI.TransportConfig_tlsConfig_set(this.swigCPtr, this, TlsConfig.getCPtr(tlsConfig), tlsConfig);
    }

    public TlsConfig getTlsConfig() {
        long TransportConfig_tlsConfig_get = pjsua2JNI.TransportConfig_tlsConfig_get(this.swigCPtr, this);
        return TransportConfig_tlsConfig_get == 0 ? null : new TlsConfig(TransportConfig_tlsConfig_get, false);
    }

    public void setQosType(pj_qos_type org_pjsip_pjsua2_pj_qos_type) {
        pjsua2JNI.TransportConfig_qosType_set(this.swigCPtr, this, org_pjsip_pjsua2_pj_qos_type.swigValue());
    }

    public pj_qos_type getQosType() {
        return pj_qos_type.swigToEnum(pjsua2JNI.TransportConfig_qosType_get(this.swigCPtr, this));
    }

    public void setQosParams(pj_qos_params org_pjsip_pjsua2_pj_qos_params) {
        pjsua2JNI.TransportConfig_qosParams_set(this.swigCPtr, this, pj_qos_params.getCPtr(org_pjsip_pjsua2_pj_qos_params), org_pjsip_pjsua2_pj_qos_params);
    }

    public pj_qos_params getQosParams() {
        long TransportConfig_qosParams_get = pjsua2JNI.TransportConfig_qosParams_get(this.swigCPtr, this);
        return TransportConfig_qosParams_get == 0 ? null : new pj_qos_params(TransportConfig_qosParams_get, false);
    }

    public TransportConfig() {
        this(pjsua2JNI.new_TransportConfig(), true);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.TransportConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.TransportConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }
}
