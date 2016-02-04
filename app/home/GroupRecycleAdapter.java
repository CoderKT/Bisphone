package app.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.common.entity.ConversationGroupEntity;
import app.database.datasource.ContactDataSource;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.GroupUsersDatasource;
import app.database.datasource.HistoryGroupDataSource;
import app.messaging.GroupMessagingActivity;
import app.messaging.emoji.EmojiHandlerCode;
import app.util.SharedPreferencesUtil;
import app.util.TimeUtils;
import app.util.Utils;
import app.xmpp.GroupManager;
import butterknife.ButterKnife;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupRecycleAdapter extends Adapter<ViewHolder> {
    private Context f3003a;
    private List<ConversationGroupEntity> f3004b;
    private LayoutInflater f3005c;

    /* renamed from: app.home.GroupRecycleAdapter.1 */
    class C02121 implements OnClickListener {
        final /* synthetic */ GroupRecycleAdapter f2950a;

        C02121(GroupRecycleAdapter groupRecycleAdapter) {
            this.f2950a = groupRecycleAdapter;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        ImageView f2951l;
        TextView f2952m;
        TextView f2953n;
        ViewGroup f2954o;
        ViewGroup f2955p;
        protected String f2956q;
        protected String f2957r;
        protected String f2958s;
        protected String f2959t;
        protected String f2960u;
        protected String f2961v;
        protected int f2962w;
        final /* synthetic */ GroupRecycleAdapter f2963x;

        ViewHolder(GroupRecycleAdapter groupRecycleAdapter, View view) {
            this.f2963x = groupRecycleAdapter;
            super(view);
            ButterKnife.m7744a(this, view);
        }

        protected void m5397c(int i) {
            this.f2956q = ((ConversationGroupEntity) this.f2963x.f3004b.get(i)).m4274b();
            this.f2957r = ((ConversationGroupEntity) this.f2963x.f3004b.get(i)).m4283e();
            this.f2958s = ((ConversationGroupEntity) this.f2963x.f3004b.get(i)).m4286f();
            this.f2959t = ((ConversationGroupEntity) this.f2963x.f3004b.get(i)).m4291h();
            this.f2960u = ((ConversationGroupEntity) this.f2963x.f3004b.get(i)).m4299l() == null ? System.currentTimeMillis() + "" : ((ConversationGroupEntity) this.f2963x.f3004b.get(i)).m4299l();
            this.f2961v = ((ConversationGroupEntity) this.f2963x.f3004b.get(i)).m4289g();
            this.f2962w = ((ConversationGroupEntity) this.f2963x.f3004b.get(i)).m4277c();
        }
    }

    class ViewHolderCreating extends ViewHolder {
        final /* synthetic */ GroupRecycleAdapter f2964y;

        ViewHolderCreating(GroupRecycleAdapter groupRecycleAdapter, View view) {
            this.f2964y = groupRecycleAdapter;
            super(groupRecycleAdapter, view);
        }

        protected void m5398c(int i) {
            super.m5397c(i);
            this.n.setText(String.format(this.f2964y.f3003a.getString(2131296454), new Object[]{this.s}));
            this.m.clearAnimation();
            this.m.startAnimation(AnimationUtils.loadAnimation(this.f2964y.f3003a, 2130968586));
        }
    }

    class ViewHolderInvited extends ViewHolder {
        Button f2978A;
        final /* synthetic */ GroupRecycleAdapter f2979B;
        Button f2980y;
        Button f2981z;

        /* renamed from: app.home.GroupRecycleAdapter.ViewHolderInvited.1 */
        class C02131 implements AnimationListener {
            final /* synthetic */ ViewHolderInvited f2973a;

            C02131(ViewHolderInvited viewHolderInvited) {
                this.f2973a = viewHolderInvited;
            }

            public void onAnimationEnd(Animation animation) {
                this.f2973a.o.setVisibility(4);
                this.f2973a.p.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(500);
                this.f2973a.p.setAnimation(alphaAnimation);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        /* renamed from: app.home.GroupRecycleAdapter.ViewHolderInvited.2 */
        class C02142 implements AnimationListener {
            final /* synthetic */ ViewHolderInvited f2974a;

            C02142(ViewHolderInvited viewHolderInvited) {
                this.f2974a = viewHolderInvited;
            }

            public void onAnimationEnd(Animation animation) {
                GroupManager.m7323a().m7349a(this.f2974a.q, null);
                this.f2974a.p.setVisibility(4);
                this.f2974a.o.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(500);
                this.f2974a.o.setAnimation(alphaAnimation);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        /* renamed from: app.home.GroupRecycleAdapter.ViewHolderInvited.3 */
        class C02163 implements OnClickListener {
            final /* synthetic */ ViewHolderInvited f2976a;

            /* renamed from: app.home.GroupRecycleAdapter.ViewHolderInvited.3.1 */
            class C02151 implements AnimationListener {
                final /* synthetic */ C02163 f2975a;

                C02151(C02163 c02163) {
                    this.f2975a = c02163;
                }

                public void onAnimationEnd(Animation animation) {
                    this.f2975a.f2976a.p.setVisibility(4);
                    this.f2975a.f2976a.o.setVisibility(0);
                    Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration(500);
                    this.f2975a.f2976a.o.setAnimation(alphaAnimation);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            }

            C02163(ViewHolderInvited viewHolderInvited) {
                this.f2976a = viewHolderInvited;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                GroupManager.m7323a().m7361b(this.f2976a.q, null);
                this.f2976a.p.clearAnimation();
                this.f2976a.o.clearAnimation();
                Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(500);
                alphaAnimation.setAnimationListener(new C02151(this));
                this.f2976a.p.setAnimation(alphaAnimation);
            }
        }

        /* renamed from: app.home.GroupRecycleAdapter.ViewHolderInvited.4 */
        class C02174 implements AnimationListener {
            final /* synthetic */ ViewHolderInvited f2977a;

            C02174(ViewHolderInvited viewHolderInvited) {
                this.f2977a = viewHolderInvited;
            }

            public void onAnimationEnd(Animation animation) {
                this.f2977a.p.setVisibility(4);
                this.f2977a.o.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(500);
                this.f2977a.o.setAnimation(alphaAnimation);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        ViewHolderInvited(GroupRecycleAdapter groupRecycleAdapter, View view) {
            this.f2979B = groupRecycleAdapter;
            super(groupRecycleAdapter, view);
        }

        protected void m5405c(int i) {
            super.m5397c(i);
            String b = GroupUsersDatasource.m4639a().m4651b(((ConversationGroupEntity) this.f2979B.f3004b.get(i)).m4274b());
            ContactEntity b2 = ContactDataSource.m4553a().m4570b(b);
            b = b2 != null ? b2.m4183a(true) : "+" + b;
            this.n.setText(String.format(this.f2979B.f3003a.getString(2131296475), new Object[]{this.s}));
            this.m.setText(String.format(this.f2979B.f3003a.getString(2131296477), new Object[]{b, this.s}));
            this.m.clearAnimation();
            this.m.startAnimation(AnimationUtils.loadAnimation(this.f2979B.f3003a, 2130968586));
            CustomImageLoader.m4009a().m4020a(this.l, this.v, null, 2130837595);
        }

        void m5406y() {
            if (this.p.getVisibility() != 0) {
                this.p.clearAnimation();
                this.o.clearAnimation();
                Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(500);
                alphaAnimation.setAnimationListener(new C02131(this));
                this.o.setAnimation(alphaAnimation);
            }
        }

        void m5407z() {
            this.p.clearAnimation();
            this.o.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setAnimationListener(new C02142(this));
            this.p.setAnimation(alphaAnimation);
        }

        void m5403A() {
            Builder builder = new Builder(this.f2979B.f3003a, 2131558537);
            builder.m1981a(this.f2979B.f3003a.getString(2131296528), new C02163(this));
            builder.m1974a(2131296331);
            builder.m1989c(2130837730);
            builder.m1987b(this.f2979B.f3003a.getString(2131296380), null);
            builder.m1986b(this.f2979B.f3003a.getString(2131296478));
            builder.m1992c();
        }

        void m5404B() {
            this.p.clearAnimation();
            this.o.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setAnimationListener(new C02174(this));
            this.p.setAnimation(alphaAnimation);
        }
    }

    class ViewHolderJoined extends ViewHolder {
        TextView f2997A;
        Button f2998B;
        Button f2999C;
        final /* synthetic */ GroupRecycleAdapter f3000D;
        ImageView f3001y;
        TextView f3002z;

        /* renamed from: app.home.GroupRecycleAdapter.ViewHolderJoined.1 */
        class C02181 implements AnimationListener {
            final /* synthetic */ ViewHolderJoined f2990a;

            C02181(ViewHolderJoined viewHolderJoined) {
                this.f2990a = viewHolderJoined;
            }

            public void onAnimationEnd(Animation animation) {
                this.f2990a.o.setVisibility(4);
                this.f2990a.p.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(500);
                this.f2990a.p.setAnimation(alphaAnimation);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        /* renamed from: app.home.GroupRecycleAdapter.ViewHolderJoined.2 */
        class C02192 implements AnimationListener {
            final /* synthetic */ ViewHolderJoined f2991a;

            C02192(ViewHolderJoined viewHolderJoined) {
                this.f2991a = viewHolderJoined;
            }

            public void onAnimationEnd(Animation animation) {
                this.f2991a.p.setVisibility(4);
                this.f2991a.o.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(500);
                this.f2991a.o.setAnimation(alphaAnimation);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        /* renamed from: app.home.GroupRecycleAdapter.ViewHolderJoined.3 */
        class C02243 implements AnimationListener {
            final /* synthetic */ ViewHolderJoined f2996a;

            /* renamed from: app.home.GroupRecycleAdapter.ViewHolderJoined.3.1 */
            class C02201 implements OnClickListener {
                final /* synthetic */ C02243 f2992a;

                C02201(C02243 c02243) {
                    this.f2992a = c02243;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }

            /* renamed from: app.home.GroupRecycleAdapter.ViewHolderJoined.3.2 */
            class C02212 implements OnClickListener {
                final /* synthetic */ C02243 f2993a;

                C02212(C02243 c02243) {
                    this.f2993a = c02243;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    ConversationGroupDataSource.m4587a().m4613c(this.f2993a.f2996a.f3000D.f3003a, this.f2993a.f2996a.q);
                    GroupUsersDatasource.m4639a().m4655c(this.f2993a.f2996a.q);
                    HistoryGroupDataSource.m4691a().m4715e(this.f2993a.f2996a.q);
                    GroupManager.m7323a().m7348a(this.f2993a.f2996a.q);
                }
            }

            /* renamed from: app.home.GroupRecycleAdapter.ViewHolderJoined.3.3 */
            class C02223 implements OnClickListener {
                final /* synthetic */ C02243 f2994a;

                C02223(C02243 c02243) {
                    this.f2994a = c02243;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    GroupManager.m7323a().m7353a(this.f2994a.f2996a.q, true, null);
                }
            }

            /* renamed from: app.home.GroupRecycleAdapter.ViewHolderJoined.3.4 */
            class C02234 implements OnClickListener {
                final /* synthetic */ C02243 f2995a;

                C02234(C02243 c02243) {
                    this.f2995a = c02243;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    GroupManager.m7323a().m7353a(this.f2995a.f2996a.q, false, null);
                }
            }

            C02243(ViewHolderJoined viewHolderJoined) {
                this.f2996a = viewHolderJoined;
            }

            public void onAnimationEnd(Animation animation) {
                this.f2996a.p.setVisibility(4);
                this.f2996a.o.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if ("LEFT".equals(this.f2996a.r) || "DESTROYED".equals(this.f2996a.r) || "KICKED".equals(this.f2996a.r)) {
                    new Builder(this.f2996a.f3000D.f3003a, 2131558538).m1980a(this.f2996a.f3000D.f3003a.getString(2131296427)).m1986b(this.f2996a.f3000D.f3003a.getString(2131296418)).m1989c(2130837731).m1981a(this.f2996a.f3000D.f3003a.getString(2131296786), new C02212(this)).m1991c(this.f2996a.f3000D.f3003a.getString(2131296784), new C02201(this)).m1992c();
                    return;
                }
                new Builder(this.f2996a.f3000D.f3003a, 2131558538).m1980a(this.f2996a.f3000D.f3003a.getString(2131296474)).m1989c(2130837731).m1986b(String.format(this.f2996a.f3000D.f3003a.getString(2131296423), new Object[]{this.f2996a.n.getText().toString()})).m1981a(this.f2996a.f3000D.f3003a.getString(2131296473), new C02234(this)).m1991c(this.f2996a.f3000D.f3003a.getString(2131296474), new C02223(this)).m1992c();
            }
        }

        ViewHolderJoined(GroupRecycleAdapter groupRecycleAdapter, View view) {
            this.f3000D = groupRecycleAdapter;
            super(groupRecycleAdapter, view);
        }

        protected void m5413c(int i) {
            super.m5397c(i);
            if (this.s == null || this.s.equals("")) {
                this.n.setText(this.f3000D.f3003a.getString(2131296469));
            } else {
                this.n.setText(this.s);
            }
            this.m.setText("");
            if (this.t == null || !this.f3000D.m5418a(((ConversationGroupEntity) this.f3000D.f3004b.get(i)).m4280d())) {
                this.m.setText(this.f3000D.f3003a.getString(2131296579));
            } else {
                EmojiHandlerCode.m6125a().m6130a(this.t.substring(0, this.t.length() > 70 ? 70 : this.t.length()), this.m, EmojiHandlerCode.m6125a().f3543b, this.f3000D.f3003a);
            }
            this.m.clearAnimation();
            this.f3002z.setText(TimeUtils.m7070a(Long.parseLong(this.u), false, true));
            this.f3001y.setVisibility(8);
            CustomImageLoader.m4009a().m4020a(this.l, this.v, null, 2130837595);
            if (this.w == 0) {
                this.f2997A.setVisibility(4);
            } else {
                this.f2997A.setText(Utils.m7077a((double) this.w));
                this.f2997A.setVisibility(0);
            }
            String e = ((ConversationGroupEntity) this.f3000D.f3004b.get(i)).m4283e();
            boolean z = true;
            switch (e.hashCode()) {
                case -2078293659:
                    if (e.equals("KICKED")) {
                        z = true;
                        break;
                    }
                    break;
                case 2332679:
                    if (e.equals("LEFT")) {
                        z = true;
                        break;
                    }
                    break;
                case 478389753:
                    if (e.equals("DESTROYED")) {
                        z = false;
                        break;
                    }
                    break;
                case 768877972:
                    if (e.equals("LEAVING")) {
                        z = true;
                        break;
                    }
                    break;
                case 1945184680:
                    if (e.equals("DESTROYING")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.m.setText(this.f3000D.f3003a.getResources().getString(2131296460));
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.m.setText(this.f3000D.f3003a.getResources().getString(2131296463));
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.m.setText(this.f3000D.f3003a.getResources().getString(2131296462));
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.m.setText(this.f3000D.f3003a.getResources().getString(2131296464));
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.m.setText(this.f3000D.f3003a.getResources().getString(2131296461));
                default:
            }
        }

        void m5414y() {
            if (this.p.getVisibility() != 0) {
                GroupManager.m7323a().m7356a(false);
                Intent intent = new Intent(this.f3000D.f3003a, GroupMessagingActivity.class);
                intent.putExtra("extra_group_jid", this.q);
                this.f3000D.f3003a.startActivity(intent);
            }
        }

        boolean m5415z() {
            if (!("LEAVING".equals(this.r) || "DESTROYING".equals(this.r))) {
                if (ConversationGroupDataSource.m4587a().m4592a(this.f3000D.f3003a).size() >= SharedPreferencesUtil.m7057b("mgpu", 50)) {
                    this.f3000D.m5426a(false);
                } else if (this.p.getVisibility() != 0) {
                    if ("LEFT".equals(this.r) || "DESTROYED".equals(this.r) || "KICKED".equals(this.r)) {
                        ((Button) this.p.findViewById(2131755601)).setText(this.f3000D.f3003a.getResources().getString(2131296412));
                    } else {
                        ((Button) this.p.findViewById(2131755601)).setText(this.f3000D.f3003a.getResources().getString(2131296542));
                    }
                    this.p.clearAnimation();
                    this.o.clearAnimation();
                    Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                    alphaAnimation.setDuration(500);
                    alphaAnimation.setAnimationListener(new C02181(this));
                    this.o.setAnimation(alphaAnimation);
                }
            }
            return true;
        }

        void m5411A() {
            this.p.clearAnimation();
            this.o.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setAnimationListener(new C02192(this));
            this.p.setAnimation(alphaAnimation);
        }

        void m5412B() {
            this.p.clearAnimation();
            this.o.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setAnimationListener(new C02243(this));
            this.p.setAnimation(alphaAnimation);
        }
    }

    public /* synthetic */ android.support.v7.widget.RecyclerView.ViewHolder m5422a(ViewGroup viewGroup, int i) {
        return m5427c(viewGroup, i);
    }

    public GroupRecycleAdapter(Context context) {
        this.f3003a = context;
        this.f3005c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public ViewHolder m5427c(ViewGroup viewGroup, int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return new ViewHolderInvited(this, this.f3005c.inflate(2130903228, viewGroup, false));
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return new ViewHolderCreating(this, this.f3005c.inflate(2130903227, viewGroup, false));
            default:
                return new ViewHolderJoined(this, this.f3005c.inflate(2130903226, viewGroup, false));
        }
    }

    public void m5424a(ViewHolder viewHolder, int i) {
        viewHolder.m5397c(i);
    }

    public int m5421a(int i) {
        String e = ((ConversationGroupEntity) this.f3004b.get(i)).m4283e();
        if ("JOINED".equals(e) || "DESTROYED".equals(e) || "LEFT".equals(e) || "KICKED".equals(e) || "LEAVING".equals(e) || "DESTROYING".equals(e)) {
            return 0;
        }
        if ("INVITED".equals(e)) {
            return 1;
        }
        return 2;
    }

    public int m5420a() {
        return this.f3004b == null ? 0 : this.f3004b.size();
    }

    private boolean m5418a(String str) {
        try {
            return Integer.parseInt(str) > 0;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public void m5425a(List<ConversationGroupEntity> list) {
        this.f3004b = list;
    }

    public void m5426a(boolean z) {
        CharSequence string;
        CharSequence string2 = this.f3003a.getResources().getString(2131296467);
        if (z) {
            string = this.f3003a.getResources().getString(2131296465);
        } else {
            string = this.f3003a.getResources().getString(2131296466);
        }
        new Builder(this.f3003a, 2131558536).m1980a(string2).m1986b(string).m1975a(17039370, new C02121(this)).m1989c(2130837729).m1992c();
    }
}
