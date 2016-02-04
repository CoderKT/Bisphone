package app.common.entity;

public class HistoryGroupEntity extends HistoryEntity {
    private String f2263b;

    public class Builder extends app.common.entity.HistoryEntity.Builder {
        private String f2262a;

        public /* synthetic */ HistoryEntity m4446b() {
            return m4445a();
        }

        public Builder m4447t(String str) {
            this.f2262a = str;
            return this;
        }

        public Builder m4444a(HistoryEntity historyEntity, String str) {
            m4351a(historyEntity.m4407a());
            m4359a(historyEntity.m4412b());
            m4365b(historyEntity.m4414c());
            m4369c(historyEntity.m4416d());
            m4356a(historyEntity.m4418e());
            m4353a(historyEntity.m4420g());
            m4376e(historyEntity.m4423j());
            m4372d(historyEntity.m4419f());
            m4378f(historyEntity.m4424k().toString());
            this.f2262a = str;
            return this;
        }

        public HistoryGroupEntity m4445a() {
            return new HistoryGroupEntity(this);
        }
    }

    protected HistoryGroupEntity(Builder builder) {
        super(builder);
        this.f2263b = builder.f2262a;
    }

    public String m4449M() {
        return this.f2263b;
    }

    public void m4450e(String str) {
        this.f2263b = str;
    }

    public static HistoryGroupEntity m4448a(HistoryNormalMessageEntity historyNormalMessageEntity) {
        Builder builder = new Builder();
        builder.m4351a(historyNormalMessageEntity.m4407a());
        builder.m4359a(historyNormalMessageEntity.m4412b());
        builder.m4365b(historyNormalMessageEntity.m4414c());
        builder.m4378f(historyNormalMessageEntity.m4424k().toString());
        builder.m4369c(historyNormalMessageEntity.m4416d());
        builder.m4356a(historyNormalMessageEntity.m4418e());
        builder.m4372d(historyNormalMessageEntity.m4419f());
        builder.m4353a(historyNormalMessageEntity.m4420g());
        builder.m4376e(historyNormalMessageEntity.m4423j());
        builder.m4447t(historyNormalMessageEntity.m4456M());
        return builder.m4445a();
    }
}
