package app.messaging.selector;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Spannable.Factory;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.HistoryEntity.Type;
import app.messaging.emoji.EmojiHandlerCode;
import app.util.TimeUtils;
import java.util.ArrayList;
import java.util.List;

public class SelectConversationAdapter extends Adapter<ChatViewHolder> {
    private Context f3834a;
    private List<ChatHistoryEntity> f3835b;
    private LayoutInflater f3836c;
    private ArrayList<Integer> f3837d;
    private ChatAdapterListener f3838e;
    private String f3839f;

    public interface ChatAdapterListener {
        void m6381a(int i);
    }

    class ChatViewHolder extends ViewHolder implements OnClickListener {
        View f3824l;
        ImageView f3825m;
        TextView f3826n;
        TextView f3827o;
        TextView f3828p;
        TextView f3829q;
        final /* synthetic */ SelectConversationAdapter f3830r;
        private String f3831s;
        private String f3832t;
        private int f3833u;

        public ChatViewHolder(SelectConversationAdapter selectConversationAdapter, View view) {
            this.f3830r = selectConversationAdapter;
            super(view);
        }

        private void m6383c(int i) {
            this.f3824l = this.a.findViewById(2131755540);
            this.f3825m = (ImageView) this.a.findViewById(2131755116);
            this.f3826n = (TextView) this.a.findViewById(2131755548);
            this.f3827o = (TextView) this.a.findViewById(2131755547);
            this.f3828p = (TextView) this.a.findViewById(2131755549);
            this.f3829q = (TextView) this.a.findViewById(2131755550);
            this.f3824l.setOnClickListener(this);
            this.f3833u = i;
            ChatHistoryEntity chatHistoryEntity = (ChatHistoryEntity) this.f3830r.f3835b.get(i);
            if (chatHistoryEntity.m4167b()) {
                this.f3827o.setText(chatHistoryEntity.m4173g());
                this.f3829q.setVisibility(8);
                this.f3826n.setText(chatHistoryEntity.m4170d().m4335e());
                this.f3828p.setText(TimeUtils.m7070a(Long.parseLong(chatHistoryEntity.m4170d().m4336f()), false, true));
                CustomImageLoader.m4009a().m4020a(this.f3825m, chatHistoryEntity.m4174h(), null, 2130837593);
                return;
            }
            CharSequence charSequence;
            if (chatHistoryEntity.m4171e() == null) {
                this.f3827o.setText("+" + chatHistoryEntity.m4170d().m4333d());
                CustomImageLoader.m4009a().m4020a(this.f3825m, null, null, 2130837592);
            } else {
                CharSequence e = chatHistoryEntity.m4171e().m4238e();
                this.f3831s = chatHistoryEntity.m4170d().m4333d();
                if (chatHistoryEntity.m4171e().m4232a() == TYPE.LOCAL) {
                    this.f3832t = e;
                    this.f3827o.setText(e);
                } else if (chatHistoryEntity.m4171e().m4232a() == TYPE.REMOTE) {
                    this.f3832t = chatHistoryEntity.m4171e().m4240g();
                    if (this.f3832t == null || this.f3832t.equals("")) {
                        this.f3832t = this.f3831s;
                    }
                    this.f3827o.setText("+" + this.f3831s);
                } else {
                    this.f3832t = "+" + chatHistoryEntity.m4170d().m4333d();
                    this.f3827o.setText("+" + chatHistoryEntity.m4170d().m4333d());
                }
                if (chatHistoryEntity.m4171e().m4232a() != null) {
                    CustomImageLoader.m4009a().m4020a(this.f3825m, chatHistoryEntity.m4171e().m4241h(), chatHistoryEntity.m4171e().m4239f(), 2130837592);
                } else {
                    CustomImageLoader.m4009a().m4020a(this.f3825m, null, null, 2130837592);
                    this.f3825m.setOnClickListener(null);
                }
            }
            String e2 = chatHistoryEntity.m4170d().m4335e();
            if (e2 == null) {
                e2 = "";
            }
            if (chatHistoryEntity.m4170d().m4337g().toString().equals(Type.CALL.toString())) {
                if (this.f3830r.f3834a.getResources().getString(2131296943).equals(e2)) {
                    this.f3826n.setCompoundDrawablesWithIntrinsicBounds(this.f3830r.f3834a.getResources().getDrawable(2130837739), null, null, null);
                    e2 = this.f3830r.f3834a.getResources().getString(2131296371);
                } else if (this.f3830r.f3834a.getResources().getString(2131296941).equals(e2)) {
                    this.f3826n.setCompoundDrawablesWithIntrinsicBounds(this.f3830r.f3834a.getResources().getDrawable(2130837737), null, null, null);
                    e2 = this.f3830r.f3834a.getResources().getString(2131296363);
                } else if (this.f3830r.f3834a.getResources().getString(2131296942).equals(e2)) {
                    this.f3826n.setCompoundDrawablesWithIntrinsicBounds(this.f3830r.f3834a.getResources().getDrawable(2130837738), null, null, null);
                    e2 = this.f3830r.f3834a.getResources().getString(2131296365);
                }
                this.f3826n.setCompoundDrawablePadding(4);
            } else {
                this.f3826n.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
            this.f3826n.setText("");
            EmojiHandlerCode.m6125a().m6130a(e2.substring(0, e2.length() > 70 ? 70 : e2.length()), this.f3826n, EmojiHandlerCode.m6125a().f3543b, this.f3830r.f3834a);
            this.f3828p.setText(TimeUtils.m7070a(Long.parseLong(chatHistoryEntity.m4170d().m4336f()), false, true));
            int f = chatHistoryEntity.m4172f();
            if (f == 0) {
                this.f3829q.setVisibility(4);
            } else {
                TextView textView = this.f3829q;
                if (f > 99) {
                    charSequence = "99";
                } else {
                    charSequence = f + "";
                }
                textView.setText(charSequence);
                this.f3829q.setVisibility(0);
            }
            if (!this.f3830r.f3839f.isEmpty()) {
                charSequence = Factory.getInstance().newSpannable(e2);
                int indexOf = e2.toLowerCase().indexOf(this.f3830r.f3839f.toLowerCase());
                if (indexOf > -1) {
                    charSequence.setSpan(new BackgroundColorSpan(-256), indexOf, this.f3830r.f3839f.length() + indexOf, 17);
                    this.f3826n.setText(charSequence);
                }
                charSequence = Factory.getInstance().newSpannable(this.f3827o.getText());
                indexOf = this.f3827o.getText().toString().toLowerCase().indexOf(this.f3830r.f3839f.toLowerCase());
                if (indexOf > -1) {
                    charSequence.setSpan(new BackgroundColorSpan(-256), indexOf, this.f3830r.f3839f.length() + indexOf, 17);
                    this.f3827o.setText(charSequence);
                }
            }
        }

        public void onClick(View view) {
            this.f3830r.f3838e.m6381a(this.f3833u);
        }
    }

    public /* synthetic */ ViewHolder m6389a(ViewGroup viewGroup, int i) {
        return m6394c(viewGroup, i);
    }

    public SelectConversationAdapter(Context context) {
        this.f3839f = "";
        this.f3834a = context;
        this.f3836c = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f3837d = new ArrayList();
    }

    public ChatViewHolder m6394c(ViewGroup viewGroup, int i) {
        return new ChatViewHolder(this, this.f3836c.inflate(2130903225, viewGroup, false));
    }

    public void m6392a(ChatViewHolder chatViewHolder, int i) {
        chatViewHolder.m6383c(i);
    }

    public int m6388a() {
        return this.f3835b == null ? 0 : this.f3835b.size();
    }

    public void m6393a(List<ChatHistoryEntity> list) {
        this.f3835b = list;
    }

    public void m6391a(ChatAdapterListener chatAdapterListener) {
        this.f3838e = chatAdapterListener;
    }
}
