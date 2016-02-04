package app.xmpp.packet.media;

public class VideoPX extends MediaPX {
    private int f5216a;
    private int f5217b;
    private int f5218c;
    private int f5219d;
    private String f5220e;
    private String f5221f;
    private String f5222g;

    public VideoPX(int i, int i2, int i3, int i4, String str, String str2, String str3) {
        this.f5216a = i;
        this.f5217b = i2;
        this.f5218c = i3;
        this.f5219d = i4;
        this.f5220e = str;
        this.f5221f = str2;
        this.f5222g = str3;
    }

    public int m7700a() {
        return this.f5216a;
    }

    public int m7701b() {
        return this.f5217b;
    }

    public int m7702c() {
        return this.f5218c;
    }

    public int m7703d() {
        return this.f5219d;
    }

    public String m7704e() {
        return this.f5220e;
    }

    public String m7705f() {
        return this.f5221f;
    }

    public String m7706g() {
        return this.f5222g;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("media").append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/media").append("' ").append("type").append("='").append("video").append("'>").append("<").append("width").append(">").append(this.f5216a).append("</").append("width").append(">").append("<").append("height").append(">").append(this.f5217b).append("</").append("height").append(">").append("<").append("size").append(">").append(this.f5218c).append("</").append("size").append(">").append("<").append("duration").append(">").append(this.f5219d).append("</").append("duration").append(">").append("<").append("token").append(">").append(this.f5220e).append("</").append("token").append(">").append("<").append("thumbnail").append(">").append(this.f5221f).append("</").append("thumbnail").append(">").append("<").append("caption").append(">").append(this.f5222g).append("</").append("caption").append(">").append("</").append("media").append(">");
        return stringBuilder;
    }
}
