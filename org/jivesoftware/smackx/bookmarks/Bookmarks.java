package org.jivesoftware.smackx.bookmarks;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.iqprivate.packet.PrivateData;
import org.jivesoftware.smackx.iqprivate.provider.PrivateDataProvider;
import org.jivesoftware.smackx.nick.packet.Nick;
import org.xmlpull.v1.XmlPullParser;

public class Bookmarks implements PrivateData {
    public static final String ELEMENT = "storage";
    public static final String NAMESPACE = "storage:bookmarks";
    private List<BookmarkedConference> bookmarkedConferences;
    private List<BookmarkedURL> bookmarkedURLS;

    public class Provider implements PrivateDataProvider {
        public PrivateData parsePrivateData(XmlPullParser xmlPullParser) {
            PrivateData bookmarks = new Bookmarks();
            Object obj = null;
            while (obj == null) {
                int next = xmlPullParser.next();
                if (next == 2 && "url".equals(xmlPullParser.getName())) {
                    BookmarkedURL access$000 = Bookmarks.getURLStorage(xmlPullParser);
                    if (access$000 != null) {
                        bookmarks.addBookmarkedURL(access$000);
                    }
                } else if (next == 2 && "conference".equals(xmlPullParser.getName())) {
                    bookmarks.addBookmarkedConference(Bookmarks.getConferenceStorage(xmlPullParser));
                } else if (next == 3 && Bookmarks.ELEMENT.equals(xmlPullParser.getName())) {
                    obj = 1;
                }
            }
            return bookmarks;
        }
    }

    public Bookmarks() {
        this.bookmarkedURLS = new ArrayList();
        this.bookmarkedConferences = new ArrayList();
    }

    public void addBookmarkedURL(BookmarkedURL bookmarkedURL) {
        this.bookmarkedURLS.add(bookmarkedURL);
    }

    public void removeBookmarkedURL(BookmarkedURL bookmarkedURL) {
        this.bookmarkedURLS.remove(bookmarkedURL);
    }

    public void clearBookmarkedURLS() {
        this.bookmarkedURLS.clear();
    }

    public void addBookmarkedConference(BookmarkedConference bookmarkedConference) {
        this.bookmarkedConferences.add(bookmarkedConference);
    }

    public void removeBookmarkedConference(BookmarkedConference bookmarkedConference) {
        this.bookmarkedConferences.remove(bookmarkedConference);
    }

    public void clearBookmarkedConferences() {
        this.bookmarkedConferences.clear();
    }

    public List<BookmarkedURL> getBookmarkedURLS() {
        return this.bookmarkedURLS;
    }

    public List<BookmarkedConference> getBookmarkedConferences() {
        return this.bookmarkedConferences;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement(ELEMENT).xmlnsAttribute(NAMESPACE).rightAngleBracket();
        for (BookmarkedURL bookmarkedURL : getBookmarkedURLS()) {
            if (!bookmarkedURL.isShared()) {
                xmlStringBuilder.halfOpenElement("url").attribute("name", bookmarkedURL.getName()).attribute("url", bookmarkedURL.getURL());
                xmlStringBuilder.condAttribute(bookmarkedURL.isRss(), "rss", "true");
                xmlStringBuilder.closeEmptyElement();
            }
        }
        for (BookmarkedConference bookmarkedConference : getBookmarkedConferences()) {
            if (!bookmarkedConference.isShared()) {
                xmlStringBuilder.halfOpenElement("conference");
                xmlStringBuilder.attribute("name", bookmarkedConference.getName());
                xmlStringBuilder.attribute("autojoin", Boolean.toString(bookmarkedConference.isAutoJoin()));
                xmlStringBuilder.attribute("jid", bookmarkedConference.getJid());
                xmlStringBuilder.rightAngleBracket();
                xmlStringBuilder.optElement(Nick.ELEMENT_NAME, bookmarkedConference.getNickname());
                xmlStringBuilder.optElement("password", bookmarkedConference.getPassword());
                xmlStringBuilder.closeElement("conference");
            }
        }
        xmlStringBuilder.closeElement(ELEMENT);
        return xmlStringBuilder;
    }

    private static BookmarkedURL getURLStorage(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue("", "name");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "url");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "rss");
        boolean z = attributeValue3 != null && "true".equals(attributeValue3);
        BookmarkedURL bookmarkedURL = new BookmarkedURL(attributeValue2, attributeValue, z);
        z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2 && "shared_bookmark".equals(xmlPullParser.getName())) {
                bookmarkedURL.setShared(true);
            } else if (next == 3 && "url".equals(xmlPullParser.getName())) {
                z = true;
            }
        }
        return bookmarkedURL;
    }

    private static BookmarkedConference getConferenceStorage(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue("", "name");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "autojoin");
        BookmarkedConference bookmarkedConference = new BookmarkedConference(xmlPullParser.getAttributeValue("", "jid"));
        bookmarkedConference.setName(attributeValue);
        bookmarkedConference.setAutoJoin(Boolean.valueOf(attributeValue2).booleanValue());
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2 && Nick.ELEMENT_NAME.equals(xmlPullParser.getName())) {
                bookmarkedConference.setNickname(xmlPullParser.nextText());
            } else if (next == 2 && "password".equals(xmlPullParser.getName())) {
                bookmarkedConference.setPassword(xmlPullParser.nextText());
            } else if (next == 2 && "shared_bookmark".equals(xmlPullParser.getName())) {
                bookmarkedConference.setShared(true);
            } else if (next == 3 && "conference".equals(xmlPullParser.getName())) {
                z = true;
            }
        }
        return bookmarkedConference;
    }
}
