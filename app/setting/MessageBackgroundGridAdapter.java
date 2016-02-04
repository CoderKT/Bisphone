package app.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import app.util.SharedPreferencesUtil;

public class MessageBackgroundGridAdapter extends BaseAdapter {
    private static final int[] f4329g;
    private Context f4330a;
    private LayoutInflater f4331b;
    private LayoutParams f4332c;
    private int f4333d;
    private int f4334e;
    private int f4335f;

    class ViewHolder {
        ImageView f4326a;
        CheckBox f4327b;
        final /* synthetic */ MessageBackgroundGridAdapter f4328c;

        private ViewHolder(MessageBackgroundGridAdapter messageBackgroundGridAdapter) {
            this.f4328c = messageBackgroundGridAdapter;
        }
    }

    static {
        f4329g = new int[]{2130837705, 2130837844, 2130837846, 2130837847, 2130837848, 2130837849, 2130837850, 2130837851, 2130837852, 2130837845};
    }

    public MessageBackgroundGridAdapter(Context context, int i, int i2) {
        this.f4333d = 150;
        this.f4334e = 100;
        this.f4335f = 0;
        this.f4330a = context;
        this.f4333d = i2;
        this.f4331b = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f4332c = new LayoutParams(i, i2);
        m6774a();
    }

    public void m6774a() {
        this.f4335f = SharedPreferencesUtil.m7057b(this.f4330a.getString(2131296920), 1);
    }

    public int getCount() {
        return f4329g.length;
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
            ViewHolder viewHolder2 = new ViewHolder();
            view = this.f4331b.inflate(2130903233, viewGroup, false);
            viewHolder2.f4326a = (ImageView) view.findViewById(2131755615);
            viewHolder2.f4327b = (CheckBox) view.findViewById(2131755616);
            viewHolder2.f4326a.setLayoutParams(this.f4332c);
            view.setTag(viewHolder2);
            viewHolder = viewHolder2;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f4326a.setImageResource(f4329g[i]);
        if (i > 8) {
            viewHolder.f4326a.setBackgroundResource(f4329g[i]);
        }
        viewHolder.f4327b.setVisibility(this.f4335f == i ? 0 : 4);
        return view;
    }

    public static int m6773a(int i) {
        if (i > f4329g.length - 1) {
            return 1;
        }
        return f4329g[i];
    }

    public void m6775a(int i, int i2) {
        this.f4333d = i2;
        this.f4334e = i;
        this.f4332c.height = i2;
        this.f4332c.width = i;
        notifyDataSetChanged();
    }
}
