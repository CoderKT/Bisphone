package app.xmpp.packet.media;

public class AudioPX extends MediaPX {
    private int f5201a;
    private int f5202b;
    private String f5203c;

    public AudioPX(int i, int i2, String str) {
        this.f5201a = i;
        this.f5202b = i2;
        this.f5203c = str;
    }

    public int m7679a() {
        return this.f5201a;
    }

    public int m7680b() {
        return this.f5202b;
    }

    public String m7681c() {
        return this.f5203c;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("media").append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/media").append("' ").append("type").append("='").append("audio").append("'>").append("<").append("size").append(">").append(this.f5201a).append("</").append("size").append(">").append("<").append("duration").append(">").append(this.f5202b).append("</").append("duration").append(">").append("<").append("token").append(">").append(this.f5203c).append("</").append("token").append(">").append("</").append("media").append(">");
        return stringBuilder;
    }
}
