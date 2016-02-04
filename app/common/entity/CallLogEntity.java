package app.common.entity;

public class CallLogEntity {
    private Integer f2046a;
    private String f2047b;
    private Type f2048c;
    private boolean f2049d;
    private int f2050e;
    private long f2051f;
    private float f2052g;

    public class Builder {
        private Integer f2035a;
        private String f2036b;
        private Type f2037c;
        private boolean f2038d;
        private int f2039e;
        private long f2040f;
        private float f2041g;

        public Builder m4103a(Integer num) {
            this.f2035a = num;
            return this;
        }

        public Builder m4104a(String str) {
            this.f2036b = str;
            return this;
        }

        public Builder m4102a(Type type) {
            this.f2037c = type;
            return this;
        }

        public Builder m4105a(boolean z) {
            this.f2038d = z;
            return this;
        }

        public Builder m4100a(int i) {
            this.f2039e = i;
            return this;
        }

        public Builder m4101a(long j) {
            this.f2040f = j;
            return this;
        }

        public Builder m4099a(float f) {
            this.f2041g = f;
            return this;
        }

        public CallLogEntity m4106a() {
            return new CallLogEntity();
        }
    }

    public enum Type {
        incoming,
        outgoing,
        missed
    }

    public Integer m4107a() {
        return this.f2046a;
    }

    public String m4108b() {
        return this.f2047b;
    }

    public Type m4109c() {
        return this.f2048c;
    }

    public boolean m4110d() {
        return this.f2049d;
    }

    public int m4111e() {
        return this.f2050e;
    }

    public long m4112f() {
        return this.f2051f;
    }

    public float m4113g() {
        return this.f2052g;
    }

    private CallLogEntity(Builder builder) {
        this.f2046a = builder.f2035a;
        this.f2047b = builder.f2036b;
        this.f2048c = builder.f2037c;
        this.f2049d = builder.f2038d;
        this.f2050e = builder.f2039e;
        this.f2051f = builder.f2040f;
        this.f2052g = builder.f2041g;
    }
}
