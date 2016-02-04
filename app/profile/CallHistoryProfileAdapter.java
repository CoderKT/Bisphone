package app.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.entity.CallLogEntity.Type;
import app.common.entity.CallLogHistoryEntity;
import app.util.TimeUtils;
import java.util.ArrayList;

public class CallHistoryProfileAdapter extends BaseAdapter {
    private Context f4179a;
    private ArrayList<CallLogHistoryEntity> f4180b;
    private LayoutInflater f4181c;
    private String f4182d;

    class ViewHolder {
        TextView f4172a;
        TextView f4173b;
        TextView f4174c;
        TextView f4175d;
        ImageView f4176e;
        View f4177f;
        final /* synthetic */ CallHistoryProfileAdapter f4178g;

        ViewHolder(CallHistoryProfileAdapter callHistoryProfileAdapter) {
            this.f4178g = callHistoryProfileAdapter;
        }
    }

    public CallHistoryProfileAdapter(Context context) {
        this.f4182d = "";
        this.f4179a = context;
        this.f4181c = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f4180b = new ArrayList();
    }

    public void m6667a(ArrayList<CallLogHistoryEntity> arrayList) {
        this.f4180b = arrayList;
    }

    public int getCount() {
        return this.f4180b.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        CharSequence string;
        if (view == null) {
            viewHolder = new ViewHolder(this);
            view = this.f4181c.inflate(2130903211, viewGroup, false);
            viewHolder.f4177f = view.findViewById(2131755542);
            viewHolder.f4173b = (TextView) view.findViewById(2131755545);
            viewHolder.f4174c = (TextView) view.findViewById(2131755544);
            viewHolder.f4176e = (ImageView) view.findViewById(2131755543);
            viewHolder.f4172a = (TextView) view.findViewById(2131755546);
            viewHolder.f4175d = (TextView) view.findViewById(2131755541);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i == 0) {
            viewHolder.f4177f.setVisibility(0);
            this.f4182d = TimeUtils.m7070a(((CallLogHistoryEntity) this.f4180b.get(i)).m4120e().m4112f(), false, true);
            viewHolder.f4175d.setText(this.f4182d);
        } else if (this.f4182d.equals(TimeUtils.m7070a(((CallLogHistoryEntity) this.f4180b.get(i)).m4120e().m4112f(), false, true))) {
            viewHolder.f4177f.setVisibility(8);
        } else {
            this.f4182d = TimeUtils.m7070a(((CallLogHistoryEntity) this.f4180b.get(i)).m4120e().m4112f(), false, true);
            viewHolder.f4175d.setText(this.f4182d);
            viewHolder.f4177f.setVisibility(0);
        }
        viewHolder.f4173b.setText(TimeUtils.m7068a(((CallLogHistoryEntity) this.f4180b.get(i)).m4120e().m4111e()));
        viewHolder.f4172a.setText(TimeUtils.m7071a(Long.valueOf(((CallLogHistoryEntity) this.f4180b.get(i)).m4120e().m4112f())));
        if (((CallLogHistoryEntity) this.f4180b.get(i)).m4120e().m4109c().equals(Type.incoming)) {
            viewHolder.f4174c.setTextColor(this.f4179a.getResources().getColor(2131689543));
            viewHolder.f4176e.setImageResource(2130837737);
            string = this.f4179a.getResources().getString(2131296363);
        } else if (((CallLogHistoryEntity) this.f4180b.get(i)).m4120e().m4109c().equals(Type.missed)) {
            viewHolder.f4174c.setTextColor(this.f4179a.getResources().getColor(2131689529));
            viewHolder.f4176e.setImageResource(2130837738);
            string = this.f4179a.getResources().getString(2131296365);
        } else {
            viewHolder.f4176e.setImageResource(2130837739);
            viewHolder.f4174c.setTextColor(this.f4179a.getResources().getColor(2131689543));
            if (((CallLogHistoryEntity) this.f4180b.get(i)).m4120e().m4110d()) {
                string = view.getResources().getString(2131296370);
            } else {
                string = this.f4179a.getResources().getString(2131296371);
            }
        }
        viewHolder.f4174c.setText(string);
        return view;
    }
}
