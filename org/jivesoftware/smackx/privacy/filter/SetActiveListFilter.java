package org.jivesoftware.smackx.privacy.filter;

import org.jivesoftware.smack.filter.FlexibleStanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.privacy.packet.Privacy;

public class SetActiveListFilter extends FlexibleStanzaTypeFilter<Privacy> {
    public static final SetActiveListFilter INSTANCE;

    static {
        INSTANCE = new SetActiveListFilter();
    }

    private SetActiveListFilter() {
    }

    protected boolean acceptSpecific(Privacy privacy) {
        if (privacy.getType() != Type.set) {
            return false;
        }
        if (privacy.getActiveName() != null || privacy.isDeclineActiveList()) {
            return true;
        }
        return false;
    }
}
