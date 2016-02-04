package app.messaging.broadcast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import java.util.List;

public class BroadcastMessageInfoExpandableAdapter extends BaseExpandableListAdapter {
    private Context f3331a;
    private LayoutInflater f3332b;
    private List<BroadcastEntityStatusModel> f3333c;
    private List<BroadcastEntityStatusModel> f3334d;
    private int f3335e;
    private int f3336f;

    class childViewHolder {
        TextView f3326a;
        ImageView f3327b;
        int f3328c;
        int f3329d;
        final /* synthetic */ BroadcastMessageInfoExpandableAdapter f3330e;

        childViewHolder(BroadcastMessageInfoExpandableAdapter broadcastMessageInfoExpandableAdapter) {
            this.f3330e = broadcastMessageInfoExpandableAdapter;
        }
    }

    public BroadcastMessageInfoExpandableAdapter(Context context, List<BroadcastEntityStatusModel> list, List<BroadcastEntityStatusModel> list2) {
        this.f3335e = 0;
        this.f3336f = 1;
        this.f3331a = context;
        this.f3333c = list;
        this.f3334d = list2;
        this.f3332b = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getGroupCount() {
        if (this.f3334d.size() == 0) {
            return 2;
        }
        if (this.f3334d.size() > 0 && this.f3333c.size() == 0) {
            return 1;
        }
        if (this.f3334d.size() <= 0 || this.f3333c.size() <= 0) {
            return 0;
        }
        return 2;
    }

    public int getChildrenCount(int i) {
        if (getGroupCount() == 2) {
            if (i == this.f3335e) {
                if (this.f3333c.size() == 0) {
                    return 1;
                }
                return this.f3333c.size();
            } else if (this.f3334d.size() != 0) {
                return this.f3334d.size();
            } else {
                return 1;
            }
        } else if (i == 0) {
            return this.f3334d.size();
        } else {
            return 0;
        }
    }

    public Object getGroup(int i) {
        return null;
    }

    public Object getChild(int i, int i2) {
        return null;
    }

    public long getGroupId(int i) {
        return 0;
    }

    public long getChildId(int i, int i2) {
        return 0;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f3332b.inflate(2130903208, viewGroup, false);
        }
        ImageView imageView = (ImageView) view.findViewById(2131755536);
        TextView textView = (TextView) view.findViewById(2131755537);
        if (i == this.f3335e && getGroupCount() == 2) {
            imageView.setImageResource(2130837929);
            textView.setText(2131296347);
        } else if (i == this.f3336f || getGroupCount() == 1) {
            imageView.setImageResource(2130837926);
            textView.setText(2131296346);
        }
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if ((getGroupCount() == 2 && i == 0 && this.f3333c.size() == 0) || (i == 1 && this.f3334d.size() == 0)) {
            return this.f3332b.inflate(2130903207, viewGroup, false);
        }
        childViewHolder app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder;
        if (view == null) {
            view = this.f3332b.inflate(2130903209, viewGroup, false);
            app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder = new childViewHolder(this);
            app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder.f3327b = (ImageView) view.findViewById(2131755538);
            app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder.f3326a = (TextView) view.findViewById(2131755539);
            view.setTag(app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder);
        } else {
            app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder = (childViewHolder) view.getTag();
        }
        app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder.f3329d = i2;
        app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder.f3328c = i;
        app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder.f3326a.setText(((BroadcastEntityStatusModel) m6003a(i).get(i2)).m5987a().m4238e());
        CustomImageLoader.m4009a().m4020a(app_messaging_broadcast_BroadcastMessageInfoExpandableAdapter_childViewHolder.f3327b, ((BroadcastEntityStatusModel) m6003a(i).get(i2)).m5987a().m4241h(), null, 2130837592);
        return view;
    }

    private List<BroadcastEntityStatusModel> m6003a(int i) {
        if (getGroupCount() == 1) {
            return this.f3334d;
        }
        if (i == 0) {
            return this.f3333c;
        }
        return this.f3334d;
    }

    public int getChildType(int i, int i2) {
        if (getGroupCount() == 2) {
            if (i == 1 && this.f3334d.size() == 0) {
                return 1;
            }
            if (i == 0 && this.f3333c.size() == 0) {
                return 1;
            }
        }
        return 0;
    }

    public int getChildTypeCount() {
        if (getGroupCount() == 2 && (this.f3334d.size() == 0 || this.f3333c.size() == 0)) {
            return 2;
        }
        return 1;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
