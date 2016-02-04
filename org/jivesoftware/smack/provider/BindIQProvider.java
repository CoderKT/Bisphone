package org.jivesoftware.smack.provider;

import org.jivesoftware.smack.packet.Bind;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class BindIQProvider extends IQProvider<Bind> {
    public Bind parse(XmlPullParser xmlPullParser, int i) {
        Bind bind = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -341064690:
                            if (name.equals("resource")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 105221:
                            if (name.equals("jid")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            bind = Bind.newSet(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            bind = Bind.newResult(xmlPullParser.nextText());
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    return bind;
                default:
                    break;
            }
        }
    }
}
