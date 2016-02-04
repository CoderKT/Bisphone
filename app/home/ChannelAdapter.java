package app.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.ChannelEntity;
import app.database.datasource.ChannelDataSource;
import app.database.datasource.HistoryChannelDataSource;
import app.events.channel.ChannelEvent;
import app.events.channel.ChannelEvent.Action;
import app.events.channel.ChannelEvent.ChannelEventListener;
import app.messaging.ChannelMessagingActivity;
import app.messaging.channel.ChannelCommunication;
import app.util.TimeUtils;
import app.util.Utils;
import app.xmpp.packet.channel.ChannelElement;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class ChannelAdapter extends CursorAdapter implements OnClickListener, OnLongClickListener {
    Context f2838a;
    LayoutInflater f2839b;
    private boolean f2840c;

    /* renamed from: app.home.ChannelAdapter.1 */
    class C01891 implements AnimationListener {
        final /* synthetic */ ViewHolder f2810a;
        final /* synthetic */ ChannelAdapter f2811b;

        C01891(ChannelAdapter channelAdapter, ViewHolder viewHolder) {
            this.f2811b = channelAdapter;
            this.f2810a = viewHolder;
        }

        public void onAnimationEnd(Animation animation) {
            this.f2810a.f2832e.setVisibility(4);
            this.f2810a.f2833f.setVisibility(0);
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(500);
            this.f2810a.f2833f.setAnimation(alphaAnimation);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: app.home.ChannelAdapter.2 */
    class C01902 implements ChannelEventListener {
        final /* synthetic */ String f2812a;
        final /* synthetic */ ChannelEvent f2813b;
        final /* synthetic */ ChannelAdapter f2814c;

        C01902(ChannelAdapter channelAdapter, String str, ChannelEvent channelEvent) {
            this.f2814c = channelAdapter;
            this.f2812a = str;
            this.f2813b = channelEvent;
        }

        public void m5274a(ChannelElement channelElement, Long l) {
            ChannelCommunication.getInstance().m6096a(this.f2812a, Boolean.valueOf(false));
            Main.f1926a.m5683d("Unfollow Success");
            if (this.f2813b.m4855d()) {
                ChannelDataSource.m4544b(channelElement.m7545a());
                HistoryChannelDataSource.m4674b(channelElement.m7545a());
                return;
            }
            ChannelDataSource.m4548c(channelElement.m7545a());
        }

        public void m5273a() {
            Main.f1926a.m5683d("Unfollow Failed");
        }
    }

    class ViewHolder {
        ImageView f2828a;
        TextView f2829b;
        TextView f2830c;
        TextView f2831d;
        ViewGroup f2832e;
        ViewGroup f2833f;
        Button f2834g;
        ChannelEntity f2835h;
        int f2836i;
        final /* synthetic */ ChannelAdapter f2837j;

        /* renamed from: app.home.ChannelAdapter.ViewHolder.1 */
        class C01911 implements AnimationListener {
            final /* synthetic */ ViewHolder f2821a;

            C01911(ViewHolder viewHolder) {
                this.f2821a = viewHolder;
            }

            public void onAnimationEnd(Animation animation) {
                this.f2821a.f2833f.setVisibility(4);
                this.f2821a.f2832e.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(500);
                this.f2821a.f2832e.setAnimation(alphaAnimation);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        /* renamed from: app.home.ChannelAdapter.ViewHolder.2 */
        class C01922 implements DialogInterface.OnClickListener {
            final /* synthetic */ ViewHolder f2822a;

            C01922(ViewHolder viewHolder) {
                this.f2822a = viewHolder;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        /* renamed from: app.home.ChannelAdapter.ViewHolder.3 */
        class C01933 implements DialogInterface.OnClickListener {
            final /* synthetic */ ViewHolder f2823a;

            C01933(ViewHolder viewHolder) {
                this.f2823a = viewHolder;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f2823a.f2837j.m5282a(this.f2823a.f2835h.m4150a(), false);
            }
        }

        /* renamed from: app.home.ChannelAdapter.ViewHolder.4 */
        class C01944 implements AnimationListener {
            final /* synthetic */ ViewHolder f2824a;

            C01944(ViewHolder viewHolder) {
                this.f2824a = viewHolder;
            }

            public void onAnimationEnd(Animation animation) {
                this.f2824a.f2833f.setVisibility(4);
                this.f2824a.f2832e.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        /* renamed from: app.home.ChannelAdapter.ViewHolder.5 */
        class C01955 implements DialogInterface.OnClickListener {
            final /* synthetic */ ViewHolder f2825a;

            C01955(ViewHolder viewHolder) {
                this.f2825a = viewHolder;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        /* renamed from: app.home.ChannelAdapter.ViewHolder.6 */
        class C01966 implements DialogInterface.OnClickListener {
            final /* synthetic */ ViewHolder f2826a;

            C01966(ViewHolder viewHolder) {
                this.f2826a = viewHolder;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (this.f2826a.f2835h.m4157g()) {
                    this.f2826a.f2837j.m5282a(this.f2826a.f2835h.m4150a(), true);
                    return;
                }
                ChannelDataSource.m4544b(this.f2826a.f2835h.m4150a());
                HistoryChannelDataSource.m4674b(this.f2826a.f2835h.m4150a());
            }
        }

        /* renamed from: app.home.ChannelAdapter.ViewHolder.7 */
        class C01977 implements AnimationListener {
            final /* synthetic */ ViewHolder f2827a;

            C01977(ViewHolder viewHolder) {
                this.f2827a = viewHolder;
            }

            public void onAnimationEnd(Animation animation) {
                this.f2827a.f2833f.setVisibility(4);
                this.f2827a.f2832e.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        public ViewHolder(ChannelAdapter channelAdapter, View view) {
            this.f2837j = channelAdapter;
            ButterKnife.m7744a(this, view);
        }

        void m5278a() {
            this.f2837j.f2840c = false;
            this.f2833f.clearAnimation();
            this.f2832e.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setAnimationListener(new C01911(this));
            this.f2833f.setAnimation(alphaAnimation);
        }

        void m5279b() {
            if (this.f2835h.m4157g()) {
                new Builder(this.f2837j.f2838a, 2131558538).m1980a(this.f2837j.f2838a.getString(2131296389)).m1986b(String.format(this.f2837j.f2838a.getString(2131296425), new Object[]{this.f2835h.m4152b()})).m1975a(17039379, new C01933(this)).m1985b(17039369, new C01922(this)).m1989c(2130837731).m1992c();
            } else {
                ChannelCommunication.getInstance().m6094a(ChannelDataSource.m4539a(this.f2835h), this.f2835h.m4150a(), null);
            }
            this.f2837j.f2840c = false;
            this.f2833f.clearAnimation();
            this.f2832e.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setAnimationListener(new C01944(this));
            this.f2833f.setAnimation(alphaAnimation);
        }

        void m5280c() {
            new Builder(this.f2837j.f2838a, 2131558538).m1980a(this.f2837j.f2838a.getString(2131296390)).m1986b(String.format(this.f2835h.m4157g() ? this.f2837j.f2838a.getString(2131296419) : this.f2837j.f2838a.getString(2131296420), new Object[]{this.f2835h.m4152b()})).m1975a(17039379, new C01966(this)).m1985b(17039369, new C01955(this)).m1989c(2130837731).m1992c();
            this.f2837j.f2840c = false;
            this.f2833f.clearAnimation();
            this.f2832e.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setAnimationListener(new C01977(this));
            this.f2833f.setAnimation(alphaAnimation);
        }
    }

    public ChannelAdapter(Context context, Cursor cursor) {
        super(context, cursor, 2);
        this.f2838a = context;
        this.f2839b = LayoutInflater.from(context);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = this.f2839b.inflate(2130903213, viewGroup, false);
        inflate.setTag(new ViewHolder(this, inflate));
        return inflate;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        ChannelEntity a = ChannelDataSource.m4536a(cursor);
        viewHolder.f2829b.setText(a.m4152b());
        viewHolder.f2830c.setText(TimeUtils.m7070a(a.m4159i(), false, true));
        viewHolder.f2831d.setText(Utils.m7077a((double) a.m4160j()));
        viewHolder.f2835h = a;
        if (a.m4160j() == 0) {
            viewHolder.f2831d.setVisibility(4);
        } else {
            viewHolder.f2831d.setVisibility(0);
        }
        viewHolder.f2836i = cursor.getPosition();
        CustomImageLoader.m4009a().m4020a(viewHolder.f2828a, a.m4154d(), null, 2130837592);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
    }

    public void onClick(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        Intent intent = new Intent(this.f2838a, ChannelMessagingActivity.class);
        intent.putExtra("extra_channel_jid", viewHolder.f2835h.m4150a());
        String str = "extra_channel_read_only";
        boolean z = viewHolder.f2835h.m4158h() || !viewHolder.f2835h.m4157g();
        intent.putExtra(str, z);
        this.f2838a.startActivity(intent);
    }

    public boolean onLongClick(View view) {
        if (!this.f2840c) {
            this.f2840c = true;
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (viewHolder.f2835h.m4157g()) {
                viewHolder.f2834g.setText(this.f2838a.getString(2131296389));
            } else {
                viewHolder.f2834g.setText(this.f2838a.getString(2131296384));
            }
            if (viewHolder.f2833f.getVisibility() != 0) {
                viewHolder.f2833f.clearAnimation();
                viewHolder.f2832e.clearAnimation();
                Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(500);
                alphaAnimation.setAnimationListener(new C01891(this, viewHolder));
                viewHolder.f2832e.setAnimation(alphaAnimation);
            }
        }
        return true;
    }

    private void m5282a(String str, boolean z) {
        ChannelCommunication.getInstance().m6095a(str, null, z);
        ChannelEvent channelEvent = new ChannelEvent(str, Action.unfollow, false);
        channelEvent.m4852a(new C01902(this, str, channelEvent));
        EventBus.m12779a().m12795d(channelEvent);
    }
}
