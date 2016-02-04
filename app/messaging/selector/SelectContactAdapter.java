package app.messaging.selector;

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import app.common.CustomImageLoader;
import butterknife.ButterKnife;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SelectContactAdapter extends CursorAdapter implements SectionIndexer {
    private LayoutInflater f3784a;
    private Context f3785b;
    private AlphabetIndexer f3786c;
    private Set<String> f3787d;
    private HashMap<String, SelectContactModel> f3788e;

    public class SelectContactModel {
        String f3769a;
        String f3770b;
        String f3771c;
        String f3772d;
        final /* synthetic */ SelectContactAdapter f3773e;

        public SelectContactModel(SelectContactAdapter selectContactAdapter, String str, String str2, String str3, String str4) {
            this.f3773e = selectContactAdapter;
            this.f3770b = str2;
            this.f3769a = str;
            this.f3771c = str3;
            this.f3772d = str4;
        }
    }

    public class ViewHolder {
        TextView f3774a;
        TextView f3775b;
        ImageView f3776c;
        CheckBox f3777d;
        String f3778e;
        String f3779f;
        String f3780g;
        String f3781h;
        public String f3782i;
        final /* synthetic */ SelectContactAdapter f3783j;

        ViewHolder(SelectContactAdapter selectContactAdapter, View view) {
            this.f3783j = selectContactAdapter;
            ButterKnife.m7744a(this, view);
        }
    }

    public SelectContactAdapter(Context context, Cursor cursor, FragmentManager fragmentManager) {
        super(context, cursor, 2);
        this.f3787d = new HashSet(4);
        this.f3788e = new HashMap();
        this.f3785b = context;
        this.f3784a = LayoutInflater.from(context);
        if (cursor != null) {
            m6351a(cursor);
        }
    }

    public Set<String> m6350a() {
        return this.f3787d == null ? new HashSet() : this.f3787d;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.f3784a.inflate(2130903231, viewGroup, false);
        inflate.setTag(new ViewHolder(this, inflate));
        return inflate;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.f3782i = cursor.getString(1);
        viewHolder.f3778e = cursor.getString(cursor.getColumnIndexOrThrow("username"));
        viewHolder.f3775b.setText(String.format("+%s", new Object[]{viewHolder.f3778e}));
        viewHolder.f3781h = cursor.getString(3);
        viewHolder.f3774a.setText(cursor.getString(cursor.getColumnIndex("name")));
        if (m6353a(viewHolder.f3778e)) {
            viewHolder.f3777d.setChecked(true);
        } else {
            viewHolder.f3777d.setChecked(false);
        }
        String string = cursor.getString(cursor.getColumnIndexOrThrow("vcard_avatar"));
        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("photo_uri"));
        viewHolder.f3779f = string2;
        viewHolder.f3780g = string;
        CustomImageLoader.m4009a().m4020a(viewHolder.f3776c, string, string2, 2130837592);
    }

    public Cursor swapCursor(Cursor cursor) {
        m6351a(cursor);
        return super.swapCursor(cursor);
    }

    public void m6351a(Cursor cursor) {
        if (this.f3786c == null) {
            this.f3786c = new AlphabetIndexer(cursor, 3, this.f3785b.getString(2131296333));
        } else {
            this.f3786c.setCursor(cursor);
        }
    }

    public int getSectionForPosition(int i) {
        if (this.f3786c != null) {
            try {
                return this.f3786c.getSectionForPosition(i);
            } catch (CursorIndexOutOfBoundsException e) {
            }
        }
        return 0;
    }

    public int getPositionForSection(int i) {
        return this.f3786c.getPositionForSection(i);
    }

    public Object[] getSections() {
        return this.f3786c == null ? null : this.f3786c.getSections();
    }

    public Collection<String> m6354b() {
        return this.f3787d;
    }

    public boolean m6353a(String str) {
        return this.f3787d.contains(str);
    }

    public void m6352a(String str, String str2, String str3, String str4) {
        this.f3787d.add(str);
        this.f3788e.put(str, new SelectContactModel(this, str, str2, str3, str4));
    }

    public void m6355b(String str) {
        this.f3787d.remove(str);
        this.f3788e.remove(str);
    }

    public void m6356c() {
        this.f3787d = new HashSet(4);
        this.f3788e = new HashMap(4);
        notifyDataSetChanged();
    }
}
