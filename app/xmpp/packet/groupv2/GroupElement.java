package app.xmpp.packet.groupv2;

import android.text.TextUtils;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupElement {
    protected String f5146a;
    protected Type f5147b;
    protected String f5148c;
    protected String f5149d;
    protected String f5150e;
    protected String f5151f;
    protected String f5152g;

    public enum Type {
        moderated,
        semimoderated,
        unmoderated;

        public static Type m7604a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return unmoderated;
            }
        }
    }

    public GroupElement(String str) {
        this.f5146a = str;
    }

    public String m7606a() {
        return this.f5146a;
    }

    public Type m7609b() {
        return this.f5147b;
    }

    public String m7611c() {
        return this.f5148c;
    }

    public String m7613d() {
        return this.f5149d;
    }

    public String m7615e() {
        return this.f5150e;
    }

    public String m7617f() {
        return this.f5151f;
    }

    public void m7607a(Type type) {
        this.f5147b = type;
    }

    public void m7608a(String str) {
        this.f5148c = str;
    }

    public void m7610b(String str) {
        this.f5149d = str;
    }

    public void m7612c(String str) {
        this.f5150e = str;
    }

    public void m7614d(String str) {
        this.f5151f = str;
    }

    public void m7616e(String str) {
        this.f5152g = str;
    }

    public CharSequence m7618g() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(Item.GROUP).append(" ").append("id").append("='").append(this.f5146a).append("'>");
        if (this.f5147b != null) {
            stringBuilder.append("<").append("tp").append(">").append(this.f5147b).append("</").append("tp").append(">");
        }
        if (this.f5148c != null) {
            stringBuilder.append("<").append("ti").append(">").append(this.f5148c).append("</").append("ti").append(">");
        }
        if (this.f5149d != null) {
            stringBuilder.append("<").append("de").append(">").append(this.f5149d).append("</").append("de").append(">");
        }
        if (this.f5150e != null) {
            stringBuilder.append("<").append("mo").append(">").append(this.f5150e).append("</").append("mo").append(">");
        }
        if (this.f5151f != null) {
            stringBuilder.append("<").append("av").append(">").append(this.f5151f).append("</").append("av").append(">");
        }
        if (this.f5152g != null) {
            stringBuilder.append("<").append("la").append(">").append(this.f5152g).append("</").append("la").append(">");
        }
        stringBuilder.append("</").append(Item.GROUP).append(">");
        return stringBuilder;
    }

    public static GroupElement m7605a(XmlPullParser xmlPullParser) {
        if (!Item.GROUP.equals(xmlPullParser.getName())) {
            return null;
        }
        GroupElement groupElement = new GroupElement(xmlPullParser.getAttributeValue("", "id"));
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                Object obj2 = -1;
                switch (name.hashCode()) {
                    case 3125:
                        if (name.equals("av")) {
                            obj2 = 4;
                            break;
                        }
                        break;
                    case 3201:
                        if (name.equals("de")) {
                            next = 2;
                            break;
                        }
                        break;
                    case 3445:
                        if (name.equals("la")) {
                            obj2 = 5;
                            break;
                        }
                        break;
                    case 3490:
                        if (name.equals("mo")) {
                            next = 3;
                            break;
                        }
                        break;
                    case 3701:
                        if (name.equals("ti")) {
                            next = 1;
                            break;
                        }
                        break;
                    case 3708:
                        if (name.equals("tp")) {
                            obj2 = null;
                            break;
                        }
                        break;
                }
                switch (obj2) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        obj2 = xmlPullParser.nextText();
                        if (!TextUtils.isEmpty(obj2)) {
                            groupElement.m7607a(Type.m7604a(obj2));
                            break;
                        }
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        groupElement.m7608a(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        groupElement.m7610b(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        groupElement.m7612c(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        groupElement.m7614d(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        groupElement.m7616e(xmlPullParser.nextText());
                        break;
                    default:
                        break;
                }
            } else if (next == 3 && Item.GROUP.equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return groupElement;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f5146a.equals(((GroupElement) obj).f5146a);
    }
}
