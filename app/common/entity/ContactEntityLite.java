package app.common.entity;

import app.common.entity.ContactEntity.TYPE;

public class ContactEntityLite {
    private TYPE f2131a;
    private String f2132b;
    private boolean f2133c;
    private String f2134d;
    private String f2135e;
    private String f2136f;
    private String f2137g;
    private String f2138h;

    public class Builder {
        private TYPE f2123a;
        private String f2124b;
        private boolean f2125c;
        private String f2126d;
        private String f2127e;
        private String f2128f;
        private String f2129g;
        private String f2130h;

        public Builder m4223a(TYPE type) {
            this.f2123a = type;
            return this;
        }

        public Builder m4224a(String str) {
            this.f2124b = str;
            return this;
        }

        public Builder m4225a(boolean z) {
            this.f2125c = z;
            return this;
        }

        public Builder m4227b(String str) {
            this.f2126d = str;
            return this;
        }

        public Builder m4228c(String str) {
            this.f2127e = str;
            return this;
        }

        public Builder m4229d(String str) {
            this.f2128f = str;
            return this;
        }

        public Builder m4230e(String str) {
            this.f2129g = str;
            return this;
        }

        public Builder m4231f(String str) {
            this.f2130h = str;
            return this;
        }

        public ContactEntityLite m4226a() {
            return new ContactEntityLite();
        }
    }

    public TYPE m4232a() {
        return this.f2131a;
    }

    public String m4234b() {
        return this.f2132b;
    }

    public boolean m4236c() {
        return this.f2133c;
    }

    public String m4237d() {
        return this.f2134d;
    }

    public String m4238e() {
        return this.f2135e;
    }

    public String m4239f() {
        return this.f2136f;
    }

    public String m4240g() {
        return this.f2137g;
    }

    public String m4241h() {
        return this.f2138h;
    }

    public void m4233a(String str) {
        this.f2137g = str;
    }

    public void m4235b(String str) {
        this.f2138h = str;
    }

    private ContactEntityLite(Builder builder) {
        this.f2131a = builder.f2123a;
        this.f2132b = builder.f2124b;
        this.f2133c = builder.f2125c;
        this.f2134d = builder.f2126d;
        this.f2135e = builder.f2127e;
        this.f2136f = builder.f2128f;
        this.f2137g = builder.f2129g;
        this.f2138h = builder.f2130h;
    }
}
