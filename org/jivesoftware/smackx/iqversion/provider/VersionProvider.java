package org.jivesoftware.smackx.iqversion.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.iqversion.packet.Version;
import org.jivesoftware.smackx.search.UserSearch;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class VersionProvider extends IQProvider<Version> {
    public Version parse(XmlPullParser xmlPullParser, int i) {
        String str = null;
        String str2 = null;
        String str3 = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case 3556:
                            if (name.equals("os")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 3373707:
                            if (name.equals("name")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 351608024:
                            if (name.equals("version")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            str3 = xmlPullParser.nextText();
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            str2 = xmlPullParser.nextText();
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            str = xmlPullParser.nextText();
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() == i && xmlPullParser.getName().equals(UserSearch.ELEMENT)) {
                        if (str3 == null && str2 == null && str == null) {
                            return new Version();
                        }
                        return new Version(str3, str2, str);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
