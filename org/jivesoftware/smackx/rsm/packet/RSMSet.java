package org.jivesoftware.smackx.rsm.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;
import se.emilsjolander.stickylistheaders.C1128R;

public class RSMSet implements ExtensionElement {
    public static final String ELEMENT = "set";
    public static final String NAMESPACE = "http://jabber.org/protocol/rsm";
    private final String after;
    private final String before;
    private final int count;
    private final int firstIndex;
    private final String firstString;
    private final int index;
    private final String last;
    private final int max;

    /* renamed from: org.jivesoftware.smackx.rsm.packet.RSMSet.1 */
    /* synthetic */ class C11131 {
        static final /* synthetic */ int[] f8583x47d3bd1a;

        static {
            f8583x47d3bd1a = new int[PageDirection.values().length];
            try {
                f8583x47d3bd1a[PageDirection.before.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8583x47d3bd1a[PageDirection.after.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum PageDirection {
        before,
        after
    }

    public RSMSet(int i) {
        this(i, -1);
    }

    public RSMSet(int i, int i2) {
        this(null, null, -1, i2, null, i, null, -1);
    }

    public RSMSet(String str, PageDirection pageDirection) {
        this(-1, str, pageDirection);
    }

    public RSMSet(int i, String str, PageDirection pageDirection) {
        switch (C11131.f8583x47d3bd1a[pageDirection.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.before = str;
                this.after = null;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.before = null;
                this.after = str;
                break;
            default:
                throw new AssertionError();
        }
        this.count = -1;
        this.index = -1;
        this.last = null;
        this.max = i;
        this.firstString = null;
        this.firstIndex = -1;
    }

    public RSMSet(String str, String str2, int i, int i2, String str3, int i3, String str4, int i4) {
        this.after = str;
        this.before = str2;
        this.count = i;
        this.index = i2;
        this.last = str3;
        this.max = i3;
        this.firstString = str4;
        this.firstIndex = i4;
    }

    public String getAfter() {
        return this.after;
    }

    public String getBefore() {
        return this.before;
    }

    public int getCount() {
        return this.count;
    }

    public int getIndex() {
        return this.index;
    }

    public String getLast() {
        return this.last;
    }

    public int getMax() {
        return this.max;
    }

    public String getFirst() {
        return this.firstString;
    }

    public int getFirstIndex() {
        return this.firstIndex;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.optElement("after", this.after);
        xmlStringBuilder.optElement("before", this.before);
        xmlStringBuilder.optIntElement("count", this.count);
        if (this.firstString != null) {
            xmlStringBuilder.halfOpenElement("first");
            xmlStringBuilder.optIntAttribute("index", this.firstIndex);
            xmlStringBuilder.rightAngleBracket();
            xmlStringBuilder.append(this.firstString);
            xmlStringBuilder.closeElement("first");
        }
        xmlStringBuilder.optIntElement("index", this.index);
        xmlStringBuilder.optElement("last", this.last);
        xmlStringBuilder.optIntElement("max", this.max);
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }

    public static RSMSet from(Stanza stanza) {
        return (RSMSet) stanza.getExtension(ELEMENT, NAMESPACE);
    }

    public static RSMSet newAfter(String str) {
        return new RSMSet(str, PageDirection.after);
    }

    public static RSMSet newBefore(String str) {
        return new RSMSet(str, PageDirection.before);
    }
}
