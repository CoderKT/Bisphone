package app.messaging.selector;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import app.util.TimeUtils;
import butterknife.ButterKnife;

public class SelectGroupAdapter extends CursorAdapter {
    private Context f3866a;
    private LayoutInflater f3867b;

    class ViewHolder {
        TextView f3856a;
        TextView f3857b;
        String f3858c;
        String f3859d;
        final /* synthetic */ SelectGroupAdapter f3860e;

        ViewHolder(SelectGroupAdapter selectGroupAdapter, View view) {
            this.f3860e = selectGroupAdapter;
            ButterKnife.m7744a(this, view);
        }
    }

    class ViewHolderJoined extends ViewHolder {
        ImageView f3861f;
        TextView f3862g;
        TextView f3863h;
        ImageView f3864i;
        final /* synthetic */ SelectGroupAdapter f3865j;

        ViewHolderJoined(SelectGroupAdapter selectGroupAdapter, View view) {
            this.f3865j = selectGroupAdapter;
            super(selectGroupAdapter, view);
        }
    }

    public SelectGroupAdapter(Context context, Cursor cursor) {
        super(context, cursor, 2);
        this.f3866a = context;
        this.f3867b = LayoutInflater.from(this.f3866a);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.f3867b.inflate(2130903226, viewGroup, false);
        inflate.setTag(new ViewHolderJoined(this, inflate));
        return inflate;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.f3858c = cursor.getString(cursor.getColumnIndexOrThrow("group_jid"));
        viewHolder.f3859d = cursor.getString(cursor.getColumnIndexOrThrow("group_state"));
        CharSequence string = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        CharSequence string2 = cursor.getString(cursor.getColumnIndexOrThrow("message_body"));
        long j = cursor.getLong(cursor.getColumnIndexOrThrow("time_stamp"));
        int i = cursor.getInt(cursor.getColumnIndexOrThrow("unread_count"));
        if (string == null || string.equals("")) {
            viewHolder.f3857b.setText(this.f3866a.getString(2131296469));
        } else {
            viewHolder.f3857b.setText(string);
        }
        viewHolder.f3856a.setText(string2);
        viewHolder.f3856a.clearAnimation();
        ViewHolderJoined viewHolderJoined = (ViewHolderJoined) viewHolder;
        viewHolderJoined.f3862g.setText(TimeUtils.m7070a(j, false, true));
        if (string2 == null) {
            viewHolderJoined.a.setText(context.getString(2131296579));
        }
        CustomImageLoader.m4009a().m4020a(viewHolderJoined.f3864i, cursor.getString(cursor.getColumnIndex("avatar_token")), null, 2130837595);
        viewHolderJoined.f3861f.setVisibility(8);
        if (i == 0) {
            viewHolderJoined.f3863h.setVisibility(4);
            return;
        }
        viewHolderJoined.f3863h.setText(i + "");
        viewHolderJoined.f3863h.setVisibility(0);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return super.getView(i, view, viewGroup);
    }
}
