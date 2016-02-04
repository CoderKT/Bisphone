package org.pjsip.pjsua2;

public class TransportInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TransportInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(TransportInfo transportInfo) {
        return transportInfo == null ? 0 : transportInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TransportInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setId(int i) {
        pjsua2JNI.TransportInfo_id_set(this.swigCPtr, this, i);
    }

    public int getId() {
        return pjsua2JNI.TransportInfo_id_get(this.swigCPtr, this);
    }

    public void setType(pjsip_transport_type_e org_pjsip_pjsua2_pjsip_transport_type_e) {
        pjsua2JNI.TransportInfo_type_set(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_transport_type_e.swigValue());
    }

    public pjsip_transport_type_e getType() {
        return pjsip_transport_type_e.swigToEnum(pjsua2JNI.TransportInfo_type_get(this.swigCPtr, this));
    }

    public void setTypeName(String str) {
        pjsua2JNI.TransportInfo_typeName_set(this.swigCPtr, this, str);
    }

    public String getTypeName() {
        return pjsua2JNI.TransportInfo_typeName_get(this.swigCPtr, this);
    }

    public void setInfo(String str) {
        pjsua2JNI.TransportInfo_info_set(this.swigCPtr, this, str);
    }

    public String getInfo() {
        return pjsua2JNI.TransportInfo_info_get(this.swigCPtr, this);
    }

    public void setFlags(long j) {
        pjsua2JNI.TransportInfo_flags_set(this.swigCPtr, this, j);
    }

    public long getFlags() {
        return pjsua2JNI.TransportInfo_flags_get(this.swigCPtr, this);
    }

    public void setLocalAddress(String str) {
        pjsua2JNI.TransportInfo_localAddress_set(this.swigCPtr, this, str);
    }

    public String getLocalAddress() {
        return pjsua2JNI.TransportInfo_localAddress_get(this.swigCPtr, this);
    }

    public void setLocalName(String str) {
        pjsua2JNI.TransportInfo_localName_set(this.swigCPtr, this, str);
    }

    public String getLocalName() {
        return pjsua2JNI.TransportInfo_localName_get(this.swigCPtr, this);
    }

    public void setUsageCount(long j) {
        pjsua2JNI.TransportInfo_usageCount_set(this.swigCPtr, this, j);
    }

    public long getUsageCount() {
        return pjsua2JNI.TransportInfo_usageCount_get(this.swigCPtr, this);
    }

    public TransportInfo() {
        this(pjsua2JNI.new_TransportInfo(), true);
    }
}
