package cz.msebera.android.httpclient.config;

public class MessageConstraints implements Cloneable {
    public static final MessageConstraints f7333a;
    private final int f7334b;
    private final int f7335c;

    public class Builder {
        private int f7331a;
        private int f7332b;

        Builder() {
            this.f7331a = -1;
            this.f7332b = -1;
        }

        public Builder m11609a(int i) {
            this.f7331a = i;
            return this;
        }

        public Builder m11611b(int i) {
            this.f7332b = i;
            return this;
        }

        public MessageConstraints m11610a() {
            return new MessageConstraints(this.f7331a, this.f7332b);
        }
    }

    protected /* synthetic */ Object clone() {
        return m11615c();
    }

    static {
        f7333a = new Builder().m11610a();
    }

    MessageConstraints(int i, int i2) {
        this.f7334b = i;
        this.f7335c = i2;
    }

    public int m11613a() {
        return this.f7334b;
    }

    public int m11614b() {
        return this.f7335c;
    }

    protected MessageConstraints m11615c() {
        return (MessageConstraints) super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[maxLineLength=").append(this.f7334b).append(", maxHeaderCount=").append(this.f7335c).append("]");
        return stringBuilder.toString();
    }

    public static Builder m11612d() {
        return new Builder();
    }
}
