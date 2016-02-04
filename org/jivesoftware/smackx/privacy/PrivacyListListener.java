package org.jivesoftware.smackx.privacy;

import java.util.List;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;

public interface PrivacyListListener {
    void setPrivacyList(String str, List<PrivacyItem> list);

    void updatedPrivacyList(String str);
}
