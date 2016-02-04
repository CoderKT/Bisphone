package org.jivesoftware.smackx.xdatalayout.provider;

import java.util.List;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.DataFormLayoutElement;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Fieldref;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Reportedref;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Section;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class DataLayoutProvider {
    public static DataLayout parse(XmlPullParser xmlPullParser) {
        DataLayout dataLayout = new DataLayout(xmlPullParser.getAttributeValue("", "label"));
        parseLayout(dataLayout.getPageLayout(), xmlPullParser);
        return dataLayout;
    }

    private static Section parseSection(XmlPullParser xmlPullParser) {
        Section section = new Section(xmlPullParser.getAttributeValue("", "label"));
        parseLayout(section.getSectionLayout(), xmlPullParser);
        return section;
    }

    private static void parseLayout(List<DataFormLayoutElement> list, XmlPullParser xmlPullParser) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -928989863:
                            if (name.equals(Fieldref.ELEMENT)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -241484064:
                            if (name.equals(Reportedref.ELEMENT)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3556653:
                            if (name.equals(Text.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1970241253:
                            if (name.equals(Section.ELEMENT)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            list.add(new Text(xmlPullParser.nextText()));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            list.add(parseSection(xmlPullParser));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            list.add(parseFieldref(xmlPullParser));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            list.add(new Reportedref());
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return;
                default:
                    break;
            }
        }
    }

    private static Fieldref parseFieldref(XmlPullParser xmlPullParser) {
        int depth = xmlPullParser.getDepth();
        Fieldref fieldref = new Fieldref(xmlPullParser.getAttributeValue("", "var"));
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return fieldref;
            }
        }
    }
}
