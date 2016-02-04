package app.common.entity;

public class HistoryChannelEntity extends HistoryEntity {

    public class Builder extends app.common.entity.HistoryEntity.Builder {
        public /* synthetic */ HistoryEntity m4393b() {
            return m4392a();
        }

        public HistoryChannelEntity m4392a() {
            return new HistoryChannelEntity(this);
        }
    }

    protected HistoryChannelEntity(Builder builder) {
        super(builder);
    }

    public static HistoryChannelEntity m4440a(HistoryEntity historyEntity) {
        Builder builder = new Builder();
        builder.m4351a(historyEntity.m4407a());
        builder.m4359a(historyEntity.m4412b());
        builder.m4365b(historyEntity.m4414c());
        builder.m4378f(historyEntity.m4424k().toString());
        builder.m4369c(historyEntity.m4416d());
        builder.m4356a(historyEntity.m4418e());
        builder.m4372d(historyEntity.m4419f());
        builder.m4353a(historyEntity.m4420g());
        builder.m4376e(historyEntity.m4423j());
        return builder.m4392a();
    }
}
