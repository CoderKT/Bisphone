package app.xmpp.packet.channel;

import org.jivesoftware.smackx.time.packet.Time;
import org.xmlpull.v1.XmlPullParser;

public class TimeElement {
    private long f5085a;

    public TimeElement(long j) {
        this.f5085a = j;
    }

    public long m7568a() {
        return this.f5085a;
    }

    public CharSequence m7569b() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(Time.ELEMENT).append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/time").append("'>").append(this.f5085a).append("</").append(Time.ELEMENT).append(">");
        return stringBuilder;
    }

    public static TimeElement m7567a(XmlPullParser xmlPullParser) {
        if (!Time.ELEMENT.equals(xmlPullParser.getName())) {
            return null;
        }
        String nextText = xmlPullParser.nextText();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && Time.ELEMENT.equals(xmlPullParser.getName())) {
                return new TimeElement(Long.valueOf(nextText).longValue());
            }
            eventType = xmlPullParser.next();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.f5085a != ((TimeElement) obj).f5085a) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.f5085a ^ (this.f5085a >>> 32));
    }
}
