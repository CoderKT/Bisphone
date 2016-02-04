package app.xmpp.packet.groupv2;

import android.text.TextUtils;
import app.xmpp.packet.groupv2.GroupElement.Type;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class MigrationGroupElement extends GroupElement {
    private String f5183h;
    private String f5184i;

    public MigrationGroupElement(String str) {
        super(str);
    }

    public String m7668h() {
        return this.f5184i;
    }

    public String m7669i() {
        return this.f5183h;
    }

    public String m7670j() {
        return this.a + "@" + "group.bisphone.com";
    }

    public void m7665f(String str) {
        this.f5183h = str;
    }

    public void m7667g(String str) {
        this.f5184i = str;
    }

    public CharSequence m7666g() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(Item.GROUP).append(" ").append("id").append("='").append(this.a).append("'>");
        if (this.b != null) {
            stringBuilder.append("<").append("tp").append(">").append(this.b).append("</").append("tp").append(">");
        }
        if (this.c != null) {
            stringBuilder.append("<").append("ti").append(">").append(this.c).append("</").append("ti").append(">");
        }
        if (this.d != null) {
            stringBuilder.append("<").append("de").append(">").append(this.d).append("</").append("de").append(">");
        }
        if (this.e != null) {
            stringBuilder.append("<").append("mo").append(">").append(this.e).append("</").append("mo").append(">");
        }
        if (this.f != null) {
            stringBuilder.append("<").append("av").append(">").append(this.f).append("</").append("av").append(">");
        }
        if (this.g != null) {
            stringBuilder.append("<").append("la").append(">").append(this.g).append("</").append("la").append(">");
        }
        if (this.f5184i != null) {
            stringBuilder.append("<").append("fj").append(">").append(this.f5184i).append("</").append("fj").append(">");
        }
        if (this.f5183h != null) {
            stringBuilder.append("<").append("iu").append(">").append(this.f5183h).append("</").append("iu").append(">");
        }
        stringBuilder.append("</").append(Item.GROUP).append(">");
        return stringBuilder;
    }

    public static MigrationGroupElement m7664b(XmlPullParser xmlPullParser) {
        if (!Item.GROUP.equals(xmlPullParser.getName())) {
            return null;
        }
        MigrationGroupElement migrationGroupElement = new MigrationGroupElement(xmlPullParser.getAttributeValue("", "id"));
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
                    case 3268:
                        if (name.equals("fj")) {
                            obj2 = 6;
                            break;
                        }
                        break;
                    case 3372:
                        if (name.equals("iu")) {
                            obj2 = 7;
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
                            migrationGroupElement.m7607a(Type.m7604a(obj2));
                            break;
                        }
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        migrationGroupElement.m7608a(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        migrationGroupElement.m7610b(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        migrationGroupElement.m7612c(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        migrationGroupElement.m7614d(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        migrationGroupElement.m7616e(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        migrationGroupElement.m7667g(xmlPullParser.nextText());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        migrationGroupElement.m7665f(xmlPullParser.nextText());
                        break;
                    default:
                        break;
                }
            } else if (next == 3 && Item.GROUP.equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return migrationGroupElement;
    }
}
