package org.pjsip.pjsua2;

public class MathStat {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected MathStat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MathStat mathStat) {
        return mathStat == null ? 0 : mathStat.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MathStat(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        pjsua2JNI.MathStat_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return pjsua2JNI.MathStat_n_get(this.swigCPtr, this);
    }

    public void setMax(int i) {
        pjsua2JNI.MathStat_max_set(this.swigCPtr, this, i);
    }

    public int getMax() {
        return pjsua2JNI.MathStat_max_get(this.swigCPtr, this);
    }

    public void setMin(int i) {
        pjsua2JNI.MathStat_min_set(this.swigCPtr, this, i);
    }

    public int getMin() {
        return pjsua2JNI.MathStat_min_get(this.swigCPtr, this);
    }

    public void setLast(int i) {
        pjsua2JNI.MathStat_last_set(this.swigCPtr, this, i);
    }

    public int getLast() {
        return pjsua2JNI.MathStat_last_get(this.swigCPtr, this);
    }

    public void setMean(int i) {
        pjsua2JNI.MathStat_mean_set(this.swigCPtr, this, i);
    }

    public int getMean() {
        return pjsua2JNI.MathStat_mean_get(this.swigCPtr, this);
    }

    public MathStat() {
        this(pjsua2JNI.new_MathStat(), true);
    }
}
