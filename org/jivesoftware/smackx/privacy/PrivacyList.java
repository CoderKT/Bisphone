package org.jivesoftware.smackx.privacy;

import java.util.List;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;

public class PrivacyList {
    private final boolean isActiveList;
    private final boolean isDefaultList;
    private final List<PrivacyItem> items;
    private final String listName;

    protected PrivacyList(boolean z, boolean z2, String str, List<PrivacyItem> list) {
        this.isActiveList = z;
        this.isDefaultList = z2;
        this.listName = str;
        this.items = list;
    }

    public String getName() {
        return this.listName;
    }

    public boolean isActiveList() {
        return this.isActiveList;
    }

    public boolean isDefaultList() {
        return this.isDefaultList;
    }

    public List<PrivacyItem> getItems() {
        return this.items;
    }

    public String toString() {
        return "Privacy List: " + this.listName + "(active:" + this.isActiveList + ", default:" + this.isDefaultList + ")";
    }
}
