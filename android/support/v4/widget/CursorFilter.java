package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;

class CursorFilter extends Filter {
    CursorFilterClient f600a;

    interface CursorFilterClient {
        Cursor m1719a();

        Cursor m1720a(CharSequence charSequence);

        void m1721a(Cursor cursor);

        CharSequence m1722c(Cursor cursor);
    }

    CursorFilter(CursorFilterClient cursorFilterClient) {
        this.f600a = cursorFilterClient;
    }

    public CharSequence convertResultToString(Object obj) {
        return this.f600a.m1722c((Cursor) obj);
    }

    protected FilterResults performFiltering(CharSequence charSequence) {
        Cursor a = this.f600a.m1720a(charSequence);
        FilterResults filterResults = new FilterResults();
        if (a != null) {
            filterResults.count = a.getCount();
            filterResults.values = a;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        Cursor a = this.f600a.m1719a();
        if (filterResults.values != null && filterResults.values != a) {
            this.f600a.m1721a((Cursor) filterResults.values);
        }
    }
}
