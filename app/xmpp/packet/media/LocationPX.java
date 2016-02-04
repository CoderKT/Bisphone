package app.xmpp.packet.media;

import org.jivesoftware.smackx.address.packet.MultipleAddresses.Address;

public class LocationPX extends MediaPX {
    private String f5204a;
    private String f5205b;
    private double f5206c;
    private double f5207d;
    private String f5208e;

    public LocationPX(String str, String str2, double d, double d2, String str3) {
        this.f5204a = str;
        this.f5205b = str2;
        this.f5206c = d;
        this.f5207d = d2;
        this.f5208e = str3;
    }

    public String m7682a() {
        return this.f5204a;
    }

    public String m7683b() {
        return this.f5205b;
    }

    public double m7684c() {
        return this.f5206c;
    }

    public double m7685d() {
        return this.f5207d;
    }

    public String m7686e() {
        return this.f5208e;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("media").append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/media").append("' ").append("type").append("='").append("map").append("'>").append("<").append("title").append(">").append(this.f5204a).append("</").append("title").append(">").append("<").append(Address.ELEMENT).append(">").append(this.f5205b).append("</").append(Address.ELEMENT).append(">").append("<").append("latitude").append(">").append(this.f5206c).append("</").append("latitude").append(">").append("<").append("longitude").append(">").append(this.f5207d).append("</").append("longitude").append(">").append("<").append("thumbnail").append(">").append(this.f5208e).append("</").append("thumbnail").append(">").append("</").append("media").append(">");
        return stringBuilder;
    }
}
