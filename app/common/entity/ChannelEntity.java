package app.common.entity;

public class ChannelEntity {
    private String f2075a;
    private String f2076b;
    private String f2077c;
    private String f2078d;
    private State f2079e;
    private String f2080f;
    private String f2081g;
    private long f2082h;
    private boolean f2083i;
    private boolean f2084j;
    private boolean f2085k;
    private long f2086l;
    private long f2087m;
    private int f2088n;

    public class Builder {
        private String f2057a;
        private String f2058b;
        private String f2059c;
        private String f2060d;
        private State f2061e;
        private String f2062f;
        private String f2063g;
        private long f2064h;
        private boolean f2065i;
        private boolean f2066j;
        private boolean f2067k;
        private long f2068l;
        private int f2069m;
        private long f2070n;

        public Builder m4138a(String str) {
            this.f2057a = str;
            return this;
        }

        public Builder m4142b(String str) {
            this.f2058b = str;
            return this;
        }

        public Builder m4145c(String str) {
            this.f2059c = str;
            return this;
        }

        public Builder m4147d(String str) {
            this.f2060d = str;
            return this;
        }

        public Builder m4137a(State state) {
            this.f2061e = state;
            return this;
        }

        public Builder m4148e(String str) {
            this.f2062f = str;
            return this;
        }

        public Builder m4149f(String str) {
            this.f2063g = str;
            return this;
        }

        public Builder m4136a(long j) {
            this.f2064h = j;
            return this;
        }

        public Builder m4139a(boolean z) {
            this.f2065i = z;
            return this;
        }

        public Builder m4143b(boolean z) {
            this.f2066j = z;
            return this;
        }

        public Builder m4146c(boolean z) {
            this.f2067k = z;
            return this;
        }

        public Builder m4141b(long j) {
            this.f2068l = j;
            return this;
        }

        public Builder m4144c(long j) {
            this.f2070n = j;
            return this;
        }

        public Builder m4135a(int i) {
            this.f2069m = i;
            return this;
        }

        public ChannelEntity m4140a() {
            return new ChannelEntity();
        }
    }

    public enum State {
        invited,
        joined,
        blocked
    }

    public String m4150a() {
        return this.f2076b;
    }

    public String m4152b() {
        return this.f2077c;
    }

    public String m4153c() {
        return this.f2078d;
    }

    public String m4154d() {
        return this.f2080f;
    }

    public String m4155e() {
        return this.f2081g;
    }

    public long m4156f() {
        return this.f2082h;
    }

    public boolean m4157g() {
        return this.f2083i;
    }

    public boolean m4158h() {
        return this.f2084j;
    }

    public long m4159i() {
        return this.f2086l;
    }

    public int m4160j() {
        return this.f2088n;
    }

    public long m4161k() {
        return this.f2087m;
    }

    public void m4151a(long j) {
        this.f2086l = j;
    }

    private ChannelEntity(Builder builder) {
        this.f2075a = builder.f2057a;
        this.f2076b = builder.f2058b;
        this.f2077c = builder.f2059c;
        this.f2078d = builder.f2060d;
        this.f2079e = builder.f2061e;
        this.f2080f = builder.f2062f;
        this.f2081g = builder.f2063g;
        this.f2082h = builder.f2064h;
        this.f2083i = builder.f2065i;
        this.f2084j = builder.f2066j;
        this.f2085k = builder.f2067k;
        this.f2086l = builder.f2068l;
        this.f2088n = builder.f2069m;
    }
}
