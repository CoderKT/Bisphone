package org.jivesoftware.smackx.privacy.filter;

import org.jivesoftware.smack.filter.FlexibleStanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.privacy.packet.Privacy;

public class SetDefaultListFilter extends FlexibleStanzaTypeFilter<Privacy> {
    public static final SetDefaultListFilter INSTANCE;

    static {
        INSTANCE = new SetDefaultListFilter();
    }

    private SetDefaultListFilter() {
    }

    protected boolean acceptSpecific(Privacy privacy) {
        if (privacy.getType() != Type.set) {
            return false;
        }
        if (privacy.getDefaultName() != null || privacy.isDeclineDefaultList()) {
            return true;
        }
        return false;
    }
}
