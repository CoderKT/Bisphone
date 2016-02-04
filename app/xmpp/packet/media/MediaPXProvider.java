package app.xmpp.packet.media;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.address.packet.MultipleAddresses.Address;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class MediaPXProvider extends ExtensionElementProvider<MediaPX> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7692a(xmlPullParser, i);
    }

    public MediaPX m7692a(XmlPullParser xmlPullParser, int i) {
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        if (attributeValue == null) {
            return new MediaPX();
        }
        String toLowerCase = attributeValue.toLowerCase();
        Object obj = -1;
        switch (toLowerCase.hashCode()) {
            case -1890252483:
                if (toLowerCase.equals("sticker")) {
                    obj = 4;
                    break;
                }
                break;
            case 107868:
                if (toLowerCase.equals("map")) {
                    obj = 3;
                    break;
                }
                break;
            case 93166550:
                if (toLowerCase.equals("audio")) {
                    obj = null;
                    break;
                }
                break;
            case 106642994:
                if (toLowerCase.equals("photo")) {
                    obj = 1;
                    break;
                }
                break;
            case 112202875:
                if (toLowerCase.equals("video")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return m7687a(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return m7688b(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return m7689c(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return m7690d(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return m7691e(xmlPullParser);
            default:
                return new MediaPX();
        }
    }

    private AudioPX m7687a(XmlPullParser xmlPullParser) {
        int i = 0;
        String str = null;
        int i2 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "size".equals(xmlPullParser.getName())) {
                i2 = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "duration".equals(xmlPullParser.getName())) {
                i = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "token".equals(xmlPullParser.getName())) {
                str = xmlPullParser.nextText();
            } else if (next == 3 && "media".equals(xmlPullParser.getName())) {
                return new AudioPX(i2, i, str);
            }
        }
    }

    private PhotoPX m7688b(XmlPullParser xmlPullParser) {
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "width".equals(xmlPullParser.getName())) {
                i2 = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "height".equals(xmlPullParser.getName())) {
                i = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "token".equals(xmlPullParser.getName())) {
                str3 = xmlPullParser.nextText();
            } else if (next == 2 && "caption".equals(xmlPullParser.getName())) {
                str = xmlPullParser.nextText();
            } else if (next == 2 && "thumbnail".equals(xmlPullParser.getName())) {
                str2 = xmlPullParser.nextText();
            } else if (next == 3 && "media".equals(xmlPullParser.getName())) {
                return new PhotoPX(i2, i, str3, str2, str);
            }
        }
    }

    private VideoPX m7689c(XmlPullParser xmlPullParser) {
        String str = null;
        int i = 0;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "width".equals(xmlPullParser.getName())) {
                i4 = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "height".equals(xmlPullParser.getName())) {
                i3 = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "size".equals(xmlPullParser.getName())) {
                i2 = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "duration".equals(xmlPullParser.getName())) {
                i = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "token".equals(xmlPullParser.getName())) {
                str3 = xmlPullParser.nextText();
            } else if (next == 2 && "thumbnail".equals(xmlPullParser.getName())) {
                str2 = xmlPullParser.nextText();
            } else if (next == 2 && "caption".equals(xmlPullParser.getName())) {
                str = xmlPullParser.nextText();
            } else if (next == 3 && "media".equals(xmlPullParser.getName())) {
                return new VideoPX(i4, i3, i2, i, str3, str2, str);
            }
        }
    }

    private LocationPX m7690d(XmlPullParser xmlPullParser) {
        double d = 0.0d;
        String str = null;
        double d2 = 0.0d;
        String str2 = null;
        String str3 = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "title".equals(xmlPullParser.getName())) {
                str3 = xmlPullParser.nextText();
            } else if (next == 2 && Address.ELEMENT.equals(xmlPullParser.getName())) {
                str2 = xmlPullParser.nextText();
            } else if (next == 2 && "latitude".equals(xmlPullParser.getName())) {
                d2 = Double.parseDouble(xmlPullParser.nextText());
            } else if (next == 2 && "longitude".equals(xmlPullParser.getName())) {
                d = Double.parseDouble(xmlPullParser.nextText());
            } else if (next == 2 && "thumbnail".equals(xmlPullParser.getName())) {
                str = xmlPullParser.nextText();
            } else if (next == 3 && "media".equals(xmlPullParser.getName())) {
                return new LocationPX(str3, str2, d2, d, str);
            }
        }
    }

    private StickerPX m7691e(XmlPullParser xmlPullParser) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "sticker-id".equals(xmlPullParser.getName())) {
                i2 = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 2 && "pack-id".equals(xmlPullParser.getName())) {
                i = Integer.parseInt(xmlPullParser.nextText());
            } else if (next == 3 && "media".equals(xmlPullParser.getName())) {
                return new StickerPX(i2, i);
            }
        }
    }
}
