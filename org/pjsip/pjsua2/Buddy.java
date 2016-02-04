package org.pjsip.pjsua2;

public class Buddy {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Buddy(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Buddy buddy) {
        return buddy == null ? 0 : buddy.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Buddy(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.Buddy_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.Buddy_change_ownership(this, this.swigCPtr, true);
    }

    public Buddy() {
        this(pjsua2JNI.new_Buddy(), true);
        pjsua2JNI.Buddy_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    public void create(Account account, BuddyConfig buddyConfig) {
        pjsua2JNI.Buddy_create(this.swigCPtr, this, Account.getCPtr(account), account, BuddyConfig.getCPtr(buddyConfig), buddyConfig);
    }

    public boolean isValid() {
        return pjsua2JNI.Buddy_isValid(this.swigCPtr, this);
    }

    public BuddyInfo getInfo() {
        return new BuddyInfo(pjsua2JNI.Buddy_getInfo(this.swigCPtr, this), true);
    }

    public void subscribePresence(boolean z) {
        pjsua2JNI.Buddy_subscribePresence(this.swigCPtr, this, z);
    }

    public void updatePresence() {
        pjsua2JNI.Buddy_updatePresence(this.swigCPtr, this);
    }

    public void sendInstantMessage(SendInstantMessageParam sendInstantMessageParam) {
        pjsua2JNI.Buddy_sendInstantMessage(this.swigCPtr, this, SendInstantMessageParam.getCPtr(sendInstantMessageParam), sendInstantMessageParam);
    }

    public void sendTypingIndication(SendTypingIndicationParam sendTypingIndicationParam) {
        pjsua2JNI.Buddy_sendTypingIndication(this.swigCPtr, this, SendTypingIndicationParam.getCPtr(sendTypingIndicationParam), sendTypingIndicationParam);
    }

    public void onBuddyState() {
        if (getClass() == Buddy.class) {
            pjsua2JNI.Buddy_onBuddyState(this.swigCPtr, this);
        } else {
            pjsua2JNI.Buddy_onBuddyStateSwigExplicitBuddy(this.swigCPtr, this);
        }
    }
}
