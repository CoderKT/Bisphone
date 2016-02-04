package app.common.entity;

import app.Main;

public class HistoryNormalMessageEntity extends HistoryEntity {
    private String f2265b;

    public class Builder extends app.common.entity.HistoryEntity.Builder {
        private String f2264a;

        public /* synthetic */ HistoryEntity m4454b() {
            return m4453a();
        }

        public Builder m4455t(String str) {
            this.f2264a = str;
            return this;
        }

        public Builder m4452a(HistoryEntity historyEntity, String str) {
            m4351a(historyEntity.m4407a());
            m4359a(historyEntity.m4412b());
            m4365b(historyEntity.m4414c());
            m4369c(historyEntity.m4416d());
            m4356a(historyEntity.m4418e());
            m4353a(historyEntity.m4420g());
            m4376e(historyEntity.m4423j());
            m4372d(historyEntity.m4419f());
            m4378f(historyEntity.m4424k().toString());
            this.f2264a = str;
            return this;
        }

        public HistoryNormalMessageEntity m4453a() {
            return new HistoryNormalMessageEntity(this);
        }
    }

    protected HistoryNormalMessageEntity(Builder builder) {
        super(builder);
        this.f2265b = builder.f2264a;
    }

    public String m4456M() {
        return this.f2265b;
    }

    public void m4459e(String str) {
        this.f2265b = str;
    }

    public boolean m4457N() {
        try {
            return this.a.getBoolean("is_broadcast");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return false;
        }
    }

    public void m4458a(boolean z) {
        try {
            this.a.put("is_broadcast", z);
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
    }
}
