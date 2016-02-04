package app.xmpp.packet.media;

public class StickerPX extends MediaPX {
    private int f5214a;
    private int f5215b;

    public StickerPX(int i, int i2) {
        this.f5214a = i;
        this.f5215b = i2;
    }

    public int m7698a() {
        return this.f5214a;
    }

    public int m7699b() {
        return this.f5215b;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("media").append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/media").append("' ").append("type").append("='").append("sticker").append("'>").append("<").append("sticker-id").append(">").append(this.f5214a).append("</").append("sticker-id").append(">").append("<").append("pack-id").append(">").append(this.f5215b).append("</").append("pack-id").append(">").append("</").append("media").append(">");
        return stringBuilder;
    }
}
