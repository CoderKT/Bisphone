package app.common.entity;

import app.common.entity.HistoryEntity.Type;

public class ConversationNormalEntity {
    private long f2197a;
    private String f2198b;
    private String f2199c;
    private int f2200d;
    private String f2201e;
    private String f2202f;
    private Type f2203g;
    private long f2204h;
    private int f2205i;

    public class Builder {
        private long f2188a;
        private String f2189b;
        private String f2190c;
        private int f2191d;
        private String f2192e;
        private String f2193f;
        private Type f2194g;
        private long f2195h;
        private int f2196i;

        public Builder m4316a(String str) {
            this.f2193f = str;
            return this;
        }

        public Builder m4314a(long j) {
            this.f2195h = j;
            return this;
        }

        public Builder m4319b(long j) {
            this.f2188a = j;
            return this;
        }

        public Builder m4313a(int i) {
            this.f2191d = i;
            return this;
        }

        public Builder m4318b(int i) {
            this.f2196i = i;
            return this;
        }

        public Builder m4320b(String str) {
            this.f2192e = str;
            return this;
        }

        public Builder m4321c(String str) {
            this.f2190c = str;
            return this;
        }

        public Builder m4322d(String str) {
            this.f2189b = str;
            return this;
        }

        public Builder m4315a(Type type) {
            this.f2194g = type;
            return this;
        }

        public ConversationNormalEntity m4317a() {
            return new ConversationNormalEntity(this.f2188a, this.f2189b, this.f2190c, this.f2191d, this.f2192e, this.f2193f, this.f2194g, this.f2195h, this.f2196i);
        }
    }

    public ConversationNormalEntity(long j, String str, String str2, int i, String str3, String str4, Type type, long j2, int i2) {
        this.f2197a = j;
        this.f2199c = str2;
        this.f2200d = i;
        this.f2201e = str3;
        this.f2198b = str;
        this.f2202f = str4;
        this.f2203g = type;
        this.f2205i = i2;
        this.f2204h = j2;
    }

    public long m4323a() {
        return this.f2197a;
    }

    public int m4328b() {
        return this.f2200d;
    }

    public String m4331c() {
        return this.f2201e;
    }

    public String m4333d() {
        return this.f2198b;
    }

    public String m4335e() {
        return this.f2199c;
    }

    public String m4336f() {
        return this.f2202f;
    }

    public Type m4337g() {
        return this.f2203g;
    }

    public long m4338h() {
        return this.f2204h;
    }

    public int m4339i() {
        return this.f2205i;
    }

    public void m4324a(int i) {
        this.f2200d = i;
    }

    public void m4340j() {
        this.f2200d++;
        this.f2205i++;
    }

    public void m4327a(String str) {
        this.f2201e = str;
    }

    public void m4330b(String str) {
        this.f2199c = str;
    }

    public void m4332c(String str) {
        this.f2198b = str;
    }

    public void m4334d(String str) {
        this.f2202f = str;
    }

    public void m4326a(Type type) {
        this.f2203g = type;
    }

    public void m4325a(long j) {
        this.f2204h = j;
    }

    public void m4329b(int i) {
        this.f2205i = i;
    }
}
