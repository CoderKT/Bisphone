package org.jivesoftware.smack.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.util.Objects;

public abstract class AbstractListFilter implements StanzaFilter {
    protected final List<StanzaFilter> filters;

    protected AbstractListFilter() {
        this.filters = new ArrayList();
    }

    protected AbstractListFilter(StanzaFilter... stanzaFilterArr) {
        Objects.requireNonNull(stanzaFilterArr, "Parameter must not be null.");
        for (Object requireNonNull : stanzaFilterArr) {
            Objects.requireNonNull(requireNonNull, "Parameter must not be null.");
        }
        this.filters = new ArrayList(Arrays.asList(stanzaFilterArr));
    }

    public void addFilter(StanzaFilter stanzaFilter) {
        Objects.requireNonNull(stanzaFilter, "Parameter must not be null.");
        this.filters.add(stanzaFilter);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(": (");
        Iterator it = this.filters.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((StanzaFilter) it.next()).toString());
            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
