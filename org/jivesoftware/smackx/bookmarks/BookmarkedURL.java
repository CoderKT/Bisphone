package org.jivesoftware.smackx.bookmarks;

public class BookmarkedURL implements SharedBookmark {
    private final String URL;
    private boolean isRss;
    private boolean isShared;
    private String name;

    protected BookmarkedURL(String str) {
        this.URL = str;
    }

    protected BookmarkedURL(String str, String str2, boolean z) {
        this.URL = str;
        this.name = str2;
        this.isRss = z;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String str) {
        this.name = str;
    }

    public String getURL() {
        return this.URL;
    }

    protected void setRss(boolean z) {
        this.isRss = z;
    }

    public boolean isRss() {
        return this.isRss;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BookmarkedURL) {
            return ((BookmarkedURL) obj).getURL().equalsIgnoreCase(this.URL);
        }
        return false;
    }

    protected void setShared(boolean z) {
        this.isShared = z;
    }

    public boolean isShared() {
        return this.isShared;
    }
}
