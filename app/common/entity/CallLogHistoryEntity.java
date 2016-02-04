package app.common.entity;

import app.common.collection.ObservableCollectionItem;

public class CallLogHistoryEntity extends ObservableCollectionItem implements Comparable<CallLogHistoryEntity> {
    private CallLogEntity f2053b;
    private ContactEntityLite f2054c;
    private int f2055d;
    private long f2056e;

    public /* synthetic */ int compareTo(Object obj) {
        return m4114a((CallLogHistoryEntity) obj);
    }

    public CallLogHistoryEntity(CallLogEntity callLogEntity, ContactEntityLite contactEntityLite) {
        this.f2055d = 1;
        this.f2053b = callLogEntity;
        this.f2054c = contactEntityLite;
        this.f2056e = callLogEntity.m4112f();
        this.f2055d = 1;
    }

    public ContactEntityLite m4117b() {
        return this.f2054c;
    }

    public long m4118c() {
        return this.f2056e;
    }

    public void m4115a(CallLogEntity callLogEntity) {
        this.f2053b = callLogEntity;
    }

    public void m4116a(ContactEntityLite contactEntityLite) {
        this.f2054c = contactEntityLite;
    }

    public int m4119d() {
        return this.f2055d;
    }

    public CallLogEntity m4120e() {
        return this.f2053b;
    }

    public void m4121f() {
        this.f2055d++;
        m4056a();
    }

    public int m4114a(CallLogHistoryEntity callLogHistoryEntity) {
        if (callLogHistoryEntity == null) {
            return -1;
        }
        return Long.compare(callLogHistoryEntity.m4120e().m4112f(), m4120e().m4112f());
    }
}
