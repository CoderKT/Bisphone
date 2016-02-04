package app.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import app.common.entity.CallLogEntity.Type;
import app.common.entity.CallLogHistoryEntity;
import app.common.entity.ContactEntity.TYPE;
import app.util.TimeUtils;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class CallRecycleAdapter extends Adapter<CallViewHolder> {
    private Context f2804a;
    private List<CallLogHistoryEntity> f2805b;
    private LayoutInflater f2806c;
    private ArrayList<Integer> f2807d;
    private CallAdapterListener f2808e;
    private boolean f2809f;

    public interface CallAdapterListener {
        void m5216a(int i);

        void m5217a(boolean z);

        void m5218b(int i);

        void m5219c(int i);
    }

    class CallViewHolder extends ViewHolder {
        View f2796l;
        ImageView f2797m;
        TextView f2798n;
        TextView f2799o;
        TextView f2800p;
        TextView f2801q;
        final /* synthetic */ CallRecycleAdapter f2802r;
        private int f2803s;

        public CallViewHolder(CallRecycleAdapter callRecycleAdapter, View view) {
            this.f2802r = callRecycleAdapter;
            super(view);
            ButterKnife.m7744a(this, view);
            this.f2796l = view;
        }

        private void m5249c(int i) {
            CharSequence string;
            this.f2803s = i;
            CallLogHistoryEntity callLogHistoryEntity = (CallLogHistoryEntity) this.f2802r.f2805b.get(i);
            if (callLogHistoryEntity.m4120e().m4109c().equals(Type.incoming)) {
                string = this.f2802r.f2804a.getResources().getString(2131296363);
            } else if (callLogHistoryEntity.m4120e().m4109c().equals(Type.missed)) {
                string = this.f2802r.f2804a.getResources().getString(2131296365);
            } else if (callLogHistoryEntity.m4120e().m4110d()) {
                string = this.f2802r.f2804a.getResources().getString(2131296370);
            } else {
                string = this.f2802r.f2804a.getResources().getString(2131296371);
            }
            if (callLogHistoryEntity.m4117b() == null) {
                this.f2799o.setText("+" + callLogHistoryEntity.m4120e().m4108b());
                CustomImageLoader.m4009a().m4020a(this.f2797m, null, null, 2130837592);
            } else if (callLogHistoryEntity.m4117b().m4232a() == TYPE.LOCAL) {
                this.f2799o.setText(callLogHistoryEntity.m4117b().m4238e());
            } else {
                this.f2799o.setText("+" + callLogHistoryEntity.m4120e().m4108b());
            }
            this.f2801q.setVisibility(8);
            this.f2798n.setText(string);
            this.f2800p.setText(TimeUtils.m7070a(callLogHistoryEntity.m4120e().m4112f(), false, true));
            if (this.f2802r.f2804a.getResources().getString(2131296371).equals(string) || this.f2802r.f2804a.getResources().getString(2131296370).equals(string)) {
                this.f2798n.setCompoundDrawablesWithIntrinsicBounds(this.f2802r.f2804a.getResources().getDrawable(2130837739), null, null, null);
            } else if (this.f2802r.f2804a.getResources().getString(2131296363).equals(string)) {
                this.f2798n.setCompoundDrawablesWithIntrinsicBounds(this.f2802r.f2804a.getResources().getDrawable(2130837737), null, null, null);
            } else if (this.f2802r.f2804a.getResources().getString(2131296365).equals(string)) {
                this.f2798n.setCompoundDrawablesWithIntrinsicBounds(this.f2802r.f2804a.getResources().getDrawable(2130837738), null, null, null);
            }
            this.f2798n.setCompoundDrawablePadding(4);
            if (callLogHistoryEntity.m4119d() > 1) {
                this.f2798n.append(" (" + callLogHistoryEntity.m4119d() + ")");
            } else {
                this.f2798n.append("");
            }
            if (this.f2802r.m5272g(i)) {
                this.f2796l.setBackgroundResource(2130837874);
            } else {
                this.f2796l.setBackgroundResource(2130837873);
            }
            if (callLogHistoryEntity.m4117b() != null) {
                CustomImageLoader.m4009a().m4020a(this.f2797m, callLogHistoryEntity.m4117b().m4241h(), callLogHistoryEntity.m4117b().m4239f(), 2130837592);
            }
        }

        public void m5252y() {
            this.f2796l.setBackgroundResource(2130837874);
        }

        public void m5253z() {
            this.f2796l.setBackgroundResource(2130837873);
        }

        void m5250A() {
            this.f2802r.f2808e.m5216a(this.f2803s);
        }

        boolean m5251B() {
            if (this.f2802r.f2809f) {
                return false;
            }
            this.f2802r.f2809f = true;
            this.f2802r.m5271f(this.f2803s);
            this.f2802r.f2808e.m5217a(this.f2802r.f2809f);
            this.f2802r.f2808e.m5219c(this.f2802r.f2807d.size());
            this.f2802r.f2808e.m5218b(this.f2803s);
            return true;
        }
    }

    public /* synthetic */ ViewHolder m5261a(ViewGroup viewGroup, int i) {
        return m5267c(viewGroup, i);
    }

    public CallRecycleAdapter(Context context) {
        this.f2809f = false;
        this.f2804a = context;
        this.f2806c = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f2807d = new ArrayList();
    }

    public CallViewHolder m5267c(ViewGroup viewGroup, int i) {
        return new CallViewHolder(this, this.f2806c.inflate(2130903225, viewGroup, false));
    }

    public void m5264a(CallViewHolder callViewHolder, int i) {
        callViewHolder.m5249c(i);
        if (m5272g(i)) {
            callViewHolder.m5252y();
        } else {
            callViewHolder.m5253z();
        }
    }

    public int m5260a() {
        return this.f2805b == null ? 0 : this.f2805b.size();
    }

    public void m5271f(int i) {
        if (m5272g(i)) {
            this.f2807d.remove(this.f2807d.indexOf(Integer.valueOf(i)));
            if (this.f2807d.isEmpty()) {
                m5269e();
            }
        } else {
            this.f2807d.add(Integer.valueOf(i));
        }
        m3346c(i);
        this.f2808e.m5219c(this.f2807d.size());
    }

    public int m5268d() {
        return this.f2807d.size();
    }

    public boolean m5272g(int i) {
        return this.f2807d.indexOf(Integer.valueOf(i)) > -1;
    }

    public void m5269e() {
        this.f2807d.clear();
        this.f2809f = false;
        m3345c();
        this.f2808e.m5217a(this.f2809f);
        this.f2808e.m5219c(this.f2807d.size());
    }

    public void m5266a(boolean z) {
        this.f2809f = z;
        this.f2808e.m5217a(z);
    }

    public ArrayList<Integer> m5270f() {
        return this.f2807d;
    }

    public void m5265a(List<CallLogHistoryEntity> list) {
        this.f2805b = list;
    }

    public void m5263a(CallAdapterListener callAdapterListener) {
        this.f2808e = callAdapterListener;
    }
}
