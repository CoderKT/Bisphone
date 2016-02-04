package app.messaging.emoji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class EmojiAdapter extends BaseAdapter {
    Context f3536a;
    LayoutInflater f3537b;

    class ViewHolder {
        ImageView f3534a;
        final /* synthetic */ EmojiAdapter f3535b;

        ViewHolder(EmojiAdapter emojiAdapter) {
            this.f3535b = emojiAdapter;
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return m6121a(i);
    }

    public EmojiAdapter(Context context) {
        this.f3536a = context;
        this.f3537b = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return SmilyMaper.f3546b.size();
    }

    public SmilyMaper m6121a(int i) {
        return (SmilyMaper) SmilyMaper.f3546b.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.f3537b.inflate(2130903218, viewGroup, false);
            ViewHolder viewHolder2 = new ViewHolder(this);
            viewHolder2.f3534a = (ImageView) view.findViewById(2131755574);
            view.setTag(viewHolder2);
            viewHolder = viewHolder2;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f3534a.setImageDrawable(((SmilyMaper) SmilyMaper.f3546b.get(i)).m6134a(this.f3536a));
        return view;
    }
}
