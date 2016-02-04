package app.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Spannable.Factory;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.HistoryEntity.Type;
import app.messaging.emoji.EmojiHandlerCode;
import app.util.TimeUtils;
import app.util.Utils;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class ChatRecycleAdapter extends Adapter<ChatViewHolder> {
    private Context f2896a;
    private List<ChatHistoryEntity> f2897b;
    private LayoutInflater f2898c;
    private ArrayList<Integer> f2899d;
    private ChatAdapterListener f2900e;
    private String f2901f;
    private boolean f2902g;

    public interface ChatAdapterListener {
        void m5296a(int i);

        void m5297a(boolean z);

        void m5298b(int i);

        void m5299c(int i);
    }

    class ChatViewHolder extends ViewHolder {
        View f2886l;
        ImageView f2887m;
        TextView f2888n;
        TextView f2889o;
        TextView f2890p;
        TextView f2891q;
        final /* synthetic */ ChatRecycleAdapter f2892r;
        private String f2893s;
        private String f2894t;
        private int f2895u;

        public ChatViewHolder(ChatRecycleAdapter chatRecycleAdapter, View view) {
            this.f2892r = chatRecycleAdapter;
            super(view);
            ButterKnife.m7744a(this, view);
        }

        private void m5340c(int i) {
            int i2 = 70;
            this.f2895u = i;
            ChatHistoryEntity chatHistoryEntity = (ChatHistoryEntity) this.f2892r.f2897b.get(i);
            String e;
            if (chatHistoryEntity.m4167b()) {
                this.f2889o.setText(chatHistoryEntity.m4173g());
                this.f2891q.setVisibility(8);
                e = chatHistoryEntity.m4170d().m4335e();
                this.f2888n.setText("");
                if (e == null || e.length() == 0) {
                    this.f2888n.setText(this.f2892r.f2896a.getResources().getString(2131296599));
                } else {
                    EmojiHandlerCode a = EmojiHandlerCode.m6125a();
                    if (e.length() <= 70) {
                        i2 = e.length();
                    }
                    a.m6130a(e.substring(0, i2), this.f2888n, EmojiHandlerCode.m6125a().f3543b, this.f2892r.f2896a);
                }
                this.f2890p.setText(TimeUtils.m7070a(Long.parseLong(chatHistoryEntity.m4170d().m4336f()), false, true));
                CustomImageLoader.m4009a().m4020a(this.f2887m, chatHistoryEntity.m4174h(), null, 2130837593);
                return;
            }
            if (chatHistoryEntity.m4171e() == null) {
                this.f2889o.setText(String.format("+%s", new Object[]{chatHistoryEntity.m4170d().m4333d()}));
                CustomImageLoader.m4009a().m4020a(this.f2887m, null, null, 2130837592);
            } else {
                CharSequence e2 = chatHistoryEntity.m4171e().m4238e();
                this.f2893s = chatHistoryEntity.m4170d().m4333d();
                if (chatHistoryEntity.m4171e().m4232a() == TYPE.LOCAL) {
                    this.f2894t = e2;
                    this.f2889o.setText(e2);
                } else if (chatHistoryEntity.m4171e().m4232a() == TYPE.REMOTE) {
                    this.f2894t = chatHistoryEntity.m4171e().m4240g();
                    if (this.f2894t == null || this.f2894t.equals("")) {
                        this.f2894t = this.f2893s;
                    }
                    this.f2889o.setText("+" + this.f2893s);
                } else {
                    this.f2894t = "+" + chatHistoryEntity.m4170d().m4333d();
                    this.f2889o.setText("+" + chatHistoryEntity.m4170d().m4333d());
                }
                if (chatHistoryEntity.m4171e().m4232a() != null) {
                    CustomImageLoader.m4009a().m4020a(this.f2887m, chatHistoryEntity.m4171e().m4241h(), chatHistoryEntity.m4171e().m4239f(), 2130837592);
                } else {
                    CustomImageLoader.m4009a().m4020a(this.f2887m, null, null, 2130837592);
                    this.f2887m.setOnClickListener(null);
                }
            }
            e = chatHistoryEntity.m4170d().m4335e();
            if (e == null) {
                e = "";
            }
            if (chatHistoryEntity.m4170d().m4337g().toString().equals(Type.CALL.toString())) {
                if (this.f2892r.f2896a.getResources().getString(2131296943).equals(e)) {
                    this.f2888n.setCompoundDrawablesWithIntrinsicBounds(this.f2892r.f2896a.getResources().getDrawable(2130837739), null, null, null);
                    e = this.f2892r.f2896a.getResources().getString(2131296371);
                } else if (this.f2892r.f2896a.getResources().getString(2131296941).equals(e)) {
                    this.f2888n.setCompoundDrawablesWithIntrinsicBounds(this.f2892r.f2896a.getResources().getDrawable(2130837737), null, null, null);
                    e = this.f2892r.f2896a.getResources().getString(2131296363);
                } else if (this.f2892r.f2896a.getResources().getString(2131296942).equals(e)) {
                    this.f2888n.setCompoundDrawablesWithIntrinsicBounds(this.f2892r.f2896a.getResources().getDrawable(2130837738), null, null, null);
                    e = this.f2892r.f2896a.getResources().getString(2131296365);
                }
                this.f2888n.setCompoundDrawablePadding(4);
            } else {
                this.f2888n.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
            this.f2888n.setText("");
            a = EmojiHandlerCode.m6125a();
            if (e.length() <= 70) {
                i2 = e.length();
            }
            a.m6130a(e.substring(0, i2), this.f2888n, EmojiHandlerCode.m6125a().f3543b, this.f2892r.f2896a);
            this.f2890p.setText(TimeUtils.m7070a(Long.parseLong(chatHistoryEntity.m4170d().m4336f()), false, true));
            int f = chatHistoryEntity.m4172f();
            if (f == 0) {
                this.f2891q.setVisibility(4);
            } else {
                this.f2891q.setText(Utils.m7077a((double) f));
                this.f2891q.setVisibility(0);
            }
            if (!this.f2892r.f2901f.isEmpty()) {
                CharSequence newSpannable = Factory.getInstance().newSpannable(e);
                int indexOf = e.toLowerCase().indexOf(this.f2892r.f2901f.toLowerCase());
                if (indexOf > -1) {
                    newSpannable.setSpan(new BackgroundColorSpan(-256), indexOf, this.f2892r.f2901f.length() + indexOf, 17);
                    this.f2888n.setText(newSpannable);
                }
                newSpannable = Factory.getInstance().newSpannable(this.f2889o.getText());
                indexOf = this.f2889o.getText().toString().toLowerCase().indexOf(this.f2892r.f2901f.toLowerCase());
                if (indexOf > -1) {
                    newSpannable.setSpan(new BackgroundColorSpan(-256), indexOf, this.f2892r.f2901f.length() + indexOf, 17);
                    this.f2889o.setText(newSpannable);
                }
            }
        }

        public void m5343y() {
            this.f2886l.setBackgroundResource(2130837874);
        }

        public void m5344z() {
            this.f2886l.setBackgroundResource(2130837873);
        }

        void m5341A() {
            this.f2892r.f2900e.m5296a(this.f2895u);
        }

        boolean m5342B() {
            if (this.f2892r.f2902g) {
                return false;
            }
            this.f2892r.f2902g = true;
            this.f2892r.m5364f(this.f2895u);
            this.f2892r.f2900e.m5297a(this.f2892r.f2902g);
            this.f2892r.f2900e.m5299c(this.f2892r.f2899d.size());
            this.f2892r.f2900e.m5298b(this.f2895u);
            return true;
        }
    }

    public /* synthetic */ ViewHolder m5353a(ViewGroup viewGroup, int i) {
        return m5360c(viewGroup, i);
    }

    public ChatRecycleAdapter(Context context) {
        this.f2901f = "";
        this.f2902g = false;
        this.f2896a = context;
        this.f2898c = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f2899d = new ArrayList();
    }

    public ChatViewHolder m5360c(ViewGroup viewGroup, int i) {
        return new ChatViewHolder(this, this.f2898c.inflate(2130903225, viewGroup, false));
    }

    public void m5356a(ChatViewHolder chatViewHolder, int i) {
        chatViewHolder.m5340c(i);
        if (m5366g(i)) {
            chatViewHolder.m5343y();
        } else {
            chatViewHolder.m5344z();
        }
    }

    public int m5352a() {
        return this.f2897b == null ? 0 : this.f2897b.size();
    }

    public void m5364f(int i) {
        if (m5366g(i)) {
            this.f2899d.remove(this.f2899d.indexOf(Integer.valueOf(i)));
            if (this.f2899d.isEmpty()) {
                m5362e();
            }
        } else {
            this.f2899d.add(Integer.valueOf(i));
        }
        m3346c(i);
        this.f2900e.m5299c(this.f2899d.size());
    }

    public int m5361d() {
        return this.f2899d.size();
    }

    public boolean m5366g(int i) {
        return this.f2899d.indexOf(Integer.valueOf(i)) > -1;
    }

    public void m5362e() {
        this.f2899d.clear();
        this.f2902g = false;
        m3345c();
        this.f2900e.m5297a(this.f2902g);
        this.f2900e.m5299c(this.f2899d.size());
    }

    public void m5358a(List<ChatHistoryEntity> list) {
        this.f2897b = list;
    }

    public void m5357a(String str) {
        if (str == null) {
            str = "";
        }
        this.f2901f = str;
    }

    public void m5363f() {
        this.f2901f = "";
    }

    public void m5359a(boolean z) {
        this.f2902g = z;
        this.f2900e.m5297a(z);
    }

    public ArrayList<Integer> m5365g() {
        return this.f2899d;
    }

    public String m5367h() {
        return this.f2901f;
    }

    public void m5355a(ChatAdapterListener chatAdapterListener) {
        this.f2900e = chatAdapterListener;
    }
}
