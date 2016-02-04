package app.voip;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ContactKeyPadMatchAdapter extends CursorAdapter {
    Context f4782a;
    LayoutInflater f4783b;

    class ViewHolder {
        TextView f4777a;
        TextView f4778b;
        String f4779c;
        String f4780d;
        final /* synthetic */ ContactKeyPadMatchAdapter f4781e;

        ViewHolder(ContactKeyPadMatchAdapter contactKeyPadMatchAdapter) {
            this.f4781e = contactKeyPadMatchAdapter;
        }
    }

    public ContactKeyPadMatchAdapter(Context context, Cursor cursor) {
        super(context, cursor, 2);
        this.f4782a = context;
        this.f4783b = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.f4783b.inflate(2130903105, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(this);
        viewHolder.f4777a = (TextView) inflate.findViewById(2131755299);
        viewHolder.f4778b = (TextView) inflate.findViewById(2131755298);
        inflate.setTag(viewHolder);
        viewHolder.f4779c = cursor.getString(cursor.getColumnIndex("name"));
        viewHolder.f4780d = cursor.getString(cursor.getColumnIndex("username"));
        viewHolder.f4778b.setText("+" + viewHolder.f4780d);
        viewHolder.f4777a.setText(viewHolder.f4779c);
        return inflate;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.f4779c = cursor.getString(cursor.getColumnIndex("name"));
        viewHolder.f4780d = cursor.getString(cursor.getColumnIndex("username"));
        viewHolder.f4778b.setText("+" + viewHolder.f4780d);
        viewHolder.f4777a.setText(viewHolder.f4779c);
    }
}
