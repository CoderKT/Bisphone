package app.messaging.channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import java.util.List;

public class ChannelCategoryListAdapter extends BaseAdapter {
    Context f3446a;
    List<ChannelCategoryModel> f3447b;
    LayoutInflater f3448c;

    class ViewHolder {
        TextView f3442a;
        ImageView f3443b;
        ImageView f3444c;
        final /* synthetic */ ChannelCategoryListAdapter f3445d;

        ViewHolder(ChannelCategoryListAdapter channelCategoryListAdapter) {
            this.f3445d = channelCategoryListAdapter;
        }
    }

    public ChannelCategoryListAdapter(Context context, List<ChannelCategoryModel> list) {
        this.f3446a = context;
        this.f3447b = list;
        this.f3448c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.f3447b == null ? 0 : this.f3447b.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.f3448c.inflate(2130903214, viewGroup, false);
            viewHolder = new ViewHolder(this);
            viewHolder.f3443b = (ImageView) view.findViewById(2131755560);
            viewHolder.f3444c = (ImageView) view.findViewById(2131755562);
            viewHolder.f3442a = (TextView) view.findViewById(2131755561);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f3442a.setText(((ChannelCategoryModel) this.f3447b.get(i)).getTitle());
        CustomImageLoader.m4009a().m4020a(viewHolder.f3443b, ((ChannelCategoryModel) this.f3447b.get(i)).getIcon(), null, 2130837647);
        return view;
    }
}
