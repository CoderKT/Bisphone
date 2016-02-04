package org.jivesoftware.smack.filter;

import java.lang.reflect.ParameterizedType;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.Objects;

public abstract class FlexibleStanzaTypeFilter<S extends Stanza> implements StanzaFilter {
    protected final Class<S> stanzaType;

    protected abstract boolean acceptSpecific(S s);

    public FlexibleStanzaTypeFilter(Class<S> cls) {
        this.stanzaType = (Class) Objects.requireNonNull(cls, "Type must not be null");
    }

    public FlexibleStanzaTypeFilter() {
        this.stanzaType = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public final boolean accept(Stanza stanza) {
        if (this.stanzaType.isInstance(stanza)) {
            return acceptSpecific(stanza);
        }
        return false;
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + this.stanzaType.toString();
    }
}
