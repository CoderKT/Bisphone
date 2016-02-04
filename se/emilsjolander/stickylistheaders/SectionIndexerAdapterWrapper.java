package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.widget.SectionIndexer;

class SectionIndexerAdapterWrapper extends AdapterWrapper implements SectionIndexer {
    SectionIndexer f8661b;

    SectionIndexerAdapterWrapper(Context context, StickyListHeadersAdapter stickyListHeadersAdapter) {
        super(context, stickyListHeadersAdapter);
        this.f8661b = (SectionIndexer) stickyListHeadersAdapter;
    }

    public int getPositionForSection(int i) {
        return this.f8661b.getPositionForSection(i);
    }

    public int getSectionForPosition(int i) {
        return this.f8661b.getSectionForPosition(i);
    }

    public Object[] getSections() {
        return this.f8661b.getSections();
    }
}
