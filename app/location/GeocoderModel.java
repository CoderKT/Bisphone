package app.location;

public class GeocoderModel {
    private String f3125a;
    private String f3126b;
    private String f3127c;
    private String f3128d;
    private String f3129e;

    public GeocoderModel() {
        this.f3125a = "";
        this.f3126b = "";
        this.f3127c = "";
        this.f3128d = "";
        this.f3129e = "";
    }

    public void m5613a(String str) {
        this.f3125a = str;
    }

    public String m5612a() {
        return this.f3126b;
    }

    public void m5615b(String str) {
        this.f3126b = str;
    }

    public String m5614b() {
        return this.f3128d;
    }

    public void m5617c(String str) {
        this.f3128d = str;
    }

    public void m5618d(String str) {
        this.f3129e = str;
    }

    public String toString() {
        String str = this.f3125a;
        m5611e(str);
        str = str + this.f3126b;
        m5611e(str);
        str = str + this.f3127c;
        m5611e(str);
        str = str + ", " + this.f3128d.replace("|||", " ");
        m5611e(str);
        str = str + ", " + this.f3129e;
        if (str.trim().length() < 5) {
            return "";
        }
        return str;
    }

    private void m5611e(String str) {
        if (str.length() > 0 && !Character.isWhitespace(str.charAt(str.length() - 1))) {
            str + ", ";
        }
    }

    public String m5616c() {
        return new StringBuilder().append(this.f3125a).append(", ").append(this.f3126b).append(", ").append(this.f3127c).toString().replace(" , ", " ").trim().length() > 5 ? (this.f3125a + ", " + this.f3126b + ", " + this.f3127c).replace(" , ", " ").trim() : "";
    }
}
