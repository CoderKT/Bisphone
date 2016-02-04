package app.xmpp.packet.media;

public class PhotoPX extends MediaPX {
    private int f5209a;
    private int f5210b;
    private String f5211c;
    private String f5212d;
    private String f5213e;

    public PhotoPX(int i, int i2, String str, String str2, String str3) {
        this.f5209a = i;
        this.f5210b = i2;
        this.f5211c = str;
        this.f5212d = str2;
        this.f5213e = str3;
    }

    public int m7693a() {
        return this.f5209a;
    }

    public int m7694b() {
        return this.f5210b;
    }

    public String m7695c() {
        return this.f5211c;
    }

    public String m7696d() {
        return this.f5212d;
    }

    public String m7697e() {
        return this.f5213e;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("media").append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/media").append("' ").append("type").append("='").append("photo").append("'>").append("<").append("width").append(">").append(this.f5209a).append("</").append("width").append(">").append("<").append("height").append(">").append(this.f5210b).append("</").append("height").append(">").append("<").append("token").append(">").append(this.f5211c).append("</").append("token").append(">").append("<").append("caption").append(">").append(this.f5213e).append("</").append("caption").append(">").append("<").append("thumbnail").append(">").append(this.f5212d).append("</").append("thumbnail").append(">").append("</").append("media").append(">");
        return stringBuilder;
    }
}
