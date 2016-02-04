package app.xmpp.packet.channel;

import android.text.TextUtils;
import java.util.Comparator;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class ChannelElement implements Comparator<ChannelElement> {
    private String f5069a;
    private String f5070b;
    private String f5071c;
    private State f5072d;
    private Boolean f5073e;
    private Float f5074f;
    private String f5075g;
    private String f5076h;
    private Long f5077i;

    enum State {
        enabled,
        disabled;

        public static State m7542a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return enabled;
            }
        }
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m7544a((ChannelElement) obj, (ChannelElement) obj2);
    }

    public String m7545a() {
        return this.f5069a;
    }

    public String m7551b() {
        return this.f5070b;
    }

    public String m7553c() {
        return this.f5071c;
    }

    public boolean m7556d() {
        return this.f5073e.booleanValue();
    }

    public String m7557e() {
        return this.f5075g;
    }

    public String m7559f() {
        return this.f5076h;
    }

    public long m7560g() {
        return this.f5077i.longValue();
    }

    public void m7549a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id can't be null!");
        }
        this.f5069a = str;
    }

    public void m7552b(String str) {
        this.f5070b = str;
    }

    public void m7554c(String str) {
        this.f5071c = str;
    }

    public void m7548a(State state) {
        this.f5072d = state;
    }

    public void m7550a(boolean z) {
        this.f5073e = Boolean.valueOf(z);
    }

    public void m7546a(float f) {
        this.f5074f = Float.valueOf(f);
    }

    public void m7555d(String str) {
        this.f5075g = str;
    }

    public void m7558e(String str) {
        this.f5076h = str;
    }

    public void m7547a(long j) {
        this.f5077i = Long.valueOf(j);
    }

    public CharSequence m7561h() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("channel").append(" ").append("jid").append("='").append(this.f5069a).append("'>");
        if (this.f5070b != null) {
            stringBuilder.append("<").append("ti").append(">").append(this.f5070b).append("</").append("ti").append(">");
        }
        if (this.f5071c != null) {
            stringBuilder.append("<").append("de").append(">").append(this.f5071c).append("</").append("de").append(">");
        }
        if (this.f5072d != null) {
            stringBuilder.append("<").append("st").append(">").append(this.f5072d).append("</").append("st").append(">");
        }
        if (this.f5073e != null) {
            stringBuilder.append("<").append("ro").append(">").append(this.f5073e).append("</").append("ro").append(">");
        }
        if (this.f5074f != null) {
            stringBuilder.append("<").append("co").append(">").append(this.f5074f).append("</").append("co").append(">");
        }
        if (this.f5075g != null) {
            stringBuilder.append("<").append("at").append(">").append(this.f5075g).append("</").append("at").append(">");
        }
        if (this.f5076h != null) {
            stringBuilder.append("<").append("ct").append(">").append(this.f5076h).append("</").append("ct").append(">");
        }
        if (this.f5077i != null) {
            stringBuilder.append("<").append("fc").append(">").append(this.f5077i).append("</").append("fc").append(">");
        }
        stringBuilder.append("</").append("channel").append(">");
        return stringBuilder;
    }

    public int m7544a(ChannelElement channelElement, ChannelElement channelElement2) {
        return (int) (channelElement.m7560g() - channelElement2.m7560g());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ChannelElement channelElement = (ChannelElement) obj;
        if (this.f5069a == null) {
            if (channelElement.f5069a != null) {
                return false;
            }
            return true;
        } else if (this.f5069a.equals(channelElement.f5069a)) {
            return true;
        } else {
            return false;
        }
    }

    public static ChannelElement m7543a(XmlPullParser xmlPullParser) {
        if (!"channel".equals(xmlPullParser.getName())) {
            return null;
        }
        ChannelElement channelElement = new ChannelElement();
        channelElement.m7549a(xmlPullParser.getAttributeValue("", "jid"));
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                Object obj2 = -1;
                switch (name.hashCode()) {
                    case 3123:
                        if (name.equals("at")) {
                            obj2 = 5;
                            break;
                        }
                        break;
                    case 3180:
                        if (name.equals("co")) {
                            obj2 = 4;
                            break;
                        }
                        break;
                    case 3185:
                        if (name.equals("ct")) {
                            obj2 = 6;
                            break;
                        }
                        break;
                    case 3201:
                        if (name.equals("de")) {
                            next = 1;
                            break;
                        }
                        break;
                    case 3261:
                        if (name.equals("fc")) {
                            obj2 = 7;
                            break;
                        }
                        break;
                    case 3645:
                        if (name.equals("ro")) {
                            next = 3;
                            break;
                        }
                        break;
                    case 3681:
                        if (name.equals("st")) {
                            next = 2;
                            break;
                        }
                        break;
                    case 3701:
                        if (name.equals("ti")) {
                            obj2 = null;
                            break;
                        }
                        break;
                }
                switch (obj2) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        channelElement.m7552b(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        channelElement.m7554c(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        obj2 = xmlPullParser.nextText();
                        if (!TextUtils.isEmpty(obj2)) {
                            channelElement.m7548a(State.m7542a(obj2));
                            break;
                        }
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        obj2 = xmlPullParser.nextText();
                        if (!TextUtils.isEmpty(obj2)) {
                            channelElement.m7550a(Boolean.parseBoolean(obj2));
                            break;
                        }
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        obj2 = xmlPullParser.nextText();
                        if (!TextUtils.isEmpty(obj2)) {
                            channelElement.m7546a(Float.parseFloat(obj2));
                            break;
                        }
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        channelElement.m7555d(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        channelElement.m7558e(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        obj2 = xmlPullParser.nextText();
                        if (!TextUtils.isEmpty(obj2)) {
                            channelElement.m7547a((long) Integer.parseInt(obj2));
                            break;
                        }
                        break;
                    default:
                        break;
                }
            } else if (next == 3 && "channel".equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return channelElement;
    }
}
