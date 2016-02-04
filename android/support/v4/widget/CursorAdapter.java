package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

public abstract class CursorAdapter extends BaseAdapter implements CursorFilterClient, Filterable {
    protected boolean f591a;
    protected boolean f592b;
    protected Cursor f593c;
    protected Context f594d;
    protected int f595e;
    protected ChangeObserver f596f;
    protected DataSetObserver f597g;
    protected CursorFilter f598h;
    protected FilterQueryProvider f599i;

    class ChangeObserver extends ContentObserver {
        final /* synthetic */ CursorAdapter f589a;

        public ChangeObserver(CursorAdapter cursorAdapter) {
            this.f589a = cursorAdapter;
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            this.f589a.m1731b();
        }
    }

    class MyDataSetObserver extends DataSetObserver {
        final /* synthetic */ CursorAdapter f590a;

        private MyDataSetObserver(CursorAdapter cursorAdapter) {
            this.f590a = cursorAdapter;
        }

        public void onChanged() {
            this.f590a.f591a = true;
            this.f590a.notifyDataSetChanged();
        }

        public void onInvalidated() {
            this.f590a.f591a = false;
            this.f590a.notifyDataSetInvalidated();
        }
    }

    public abstract View m1725a(Context context, Cursor cursor, ViewGroup viewGroup);

    public abstract void m1728a(View view, Context context, Cursor cursor);

    public CursorAdapter(Context context, Cursor cursor, boolean z) {
        m1726a(context, cursor, z ? 1 : 2);
    }

    void m1726a(Context context, Cursor cursor, int i) {
        boolean z = true;
        if ((i & 1) == 1) {
            i |= 2;
            this.f592b = true;
        } else {
            this.f592b = false;
        }
        if (cursor == null) {
            z = false;
        }
        this.f593c = cursor;
        this.f591a = z;
        this.f594d = context;
        this.f595e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f596f = new ChangeObserver(this);
            this.f597g = new MyDataSetObserver();
        } else {
            this.f596f = null;
            this.f597g = null;
        }
        if (z) {
            if (this.f596f != null) {
                cursor.registerContentObserver(this.f596f);
            }
            if (this.f597g != null) {
                cursor.registerDataSetObserver(this.f597g);
            }
        }
    }

    public Cursor m1723a() {
        return this.f593c;
    }

    public int getCount() {
        if (!this.f591a || this.f593c == null) {
            return 0;
        }
        return this.f593c.getCount();
    }

    public Object getItem(int i) {
        if (!this.f591a || this.f593c == null) {
            return null;
        }
        this.f593c.moveToPosition(i);
        return this.f593c;
    }

    public long getItemId(int i) {
        if (this.f591a && this.f593c != null && this.f593c.moveToPosition(i)) {
            return this.f593c.getLong(this.f595e);
        }
        return 0;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f591a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.f593c.moveToPosition(i)) {
            if (view == null) {
                view = m1725a(this.f594d, this.f593c, viewGroup);
            }
            m1728a(view, this.f594d, this.f593c);
            return view;
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f591a) {
            return null;
        }
        this.f593c.moveToPosition(i);
        if (view == null) {
            view = m1730b(this.f594d, this.f593c, viewGroup);
        }
        m1728a(view, this.f594d, this.f593c);
        return view;
    }

    public View m1730b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return m1725a(context, cursor, viewGroup);
    }

    public void m1727a(Cursor cursor) {
        Cursor b = m1729b(cursor);
        if (b != null) {
            b.close();
        }
    }

    public Cursor m1729b(Cursor cursor) {
        if (cursor == this.f593c) {
            return null;
        }
        Cursor cursor2 = this.f593c;
        if (cursor2 != null) {
            if (this.f596f != null) {
                cursor2.unregisterContentObserver(this.f596f);
            }
            if (this.f597g != null) {
                cursor2.unregisterDataSetObserver(this.f597g);
            }
        }
        this.f593c = cursor;
        if (cursor != null) {
            if (this.f596f != null) {
                cursor.registerContentObserver(this.f596f);
            }
            if (this.f597g != null) {
                cursor.registerDataSetObserver(this.f597g);
            }
            this.f595e = cursor.getColumnIndexOrThrow("_id");
            this.f591a = true;
            notifyDataSetChanged();
            return cursor2;
        }
        this.f595e = -1;
        this.f591a = false;
        notifyDataSetInvalidated();
        return cursor2;
    }

    public CharSequence m1732c(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    public Cursor m1724a(CharSequence charSequence) {
        if (this.f599i != null) {
            return this.f599i.runQuery(charSequence);
        }
        return this.f593c;
    }

    public Filter getFilter() {
        if (this.f598h == null) {
            this.f598h = new CursorFilter(this);
        }
        return this.f598h;
    }

    protected void m1731b() {
        if (this.f592b && this.f593c != null && !this.f593c.isClosed()) {
            this.f591a = this.f593c.requery();
        }
    }
}
