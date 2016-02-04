package org.pjsip.pjsua2;

public class UserEvent {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected UserEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(UserEvent userEvent) {
        return userEvent == null ? 0 : userEvent.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_UserEvent(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setUser1(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.UserEvent_user1_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getUser1() {
        long UserEvent_user1_get = pjsua2JNI.UserEvent_user1_get(this.swigCPtr, this);
        return UserEvent_user1_get == 0 ? null : new SWIGTYPE_p_void(UserEvent_user1_get, false);
    }

    public void setUser2(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.UserEvent_user2_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getUser2() {
        long UserEvent_user2_get = pjsua2JNI.UserEvent_user2_get(this.swigCPtr, this);
        return UserEvent_user2_get == 0 ? null : new SWIGTYPE_p_void(UserEvent_user2_get, false);
    }

    public void setUser3(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.UserEvent_user3_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getUser3() {
        long UserEvent_user3_get = pjsua2JNI.UserEvent_user3_get(this.swigCPtr, this);
        return UserEvent_user3_get == 0 ? null : new SWIGTYPE_p_void(UserEvent_user3_get, false);
    }

    public void setUser4(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.UserEvent_user4_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getUser4() {
        long UserEvent_user4_get = pjsua2JNI.UserEvent_user4_get(this.swigCPtr, this);
        return UserEvent_user4_get == 0 ? null : new SWIGTYPE_p_void(UserEvent_user4_get, false);
    }

    public UserEvent() {
        this(pjsua2JNI.new_UserEvent(), true);
    }
}
