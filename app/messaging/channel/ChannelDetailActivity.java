package app.messaging.channel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog.Builder;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.messaging.ChannelMessagingActivity;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.text.DecimalFormat;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class ChannelDetailActivity extends BaseActivity {
    int f3487A;
    Handler f3488B;
    ImageView f3489o;
    ImageView f3490p;
    TextView f3491q;
    View f3492r;
    ProgressBar f3493s;
    TextView f3494t;
    TextView f3495u;
    TextView f3496v;
    View f3497w;
    boolean f3498x;
    boolean f3499y;
    ChannelDetailAdapterModel f3500z;

    /* renamed from: app.messaging.channel.ChannelDetailActivity.1 */
    class C02991 implements OnClickListener {
        final /* synthetic */ ChannelDetailActivity f3478a;

        C02991(ChannelDetailActivity channelDetailActivity) {
            this.f3478a = channelDetailActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: app.messaging.channel.ChannelDetailActivity.2 */
    class C03002 implements OnClickListener {
        final /* synthetic */ ChannelDetailActivity f3479a;

        C03002(ChannelDetailActivity channelDetailActivity) {
            this.f3479a = channelDetailActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f3479a.m6105b(this.f3479a.f3500z.f3501a.getId());
        }
    }

    /* renamed from: app.messaging.channel.ChannelDetailActivity.3 */
    class C03023 implements Runnable {
        final /* synthetic */ ChannelDetailActivity f3481a;

        /* renamed from: app.messaging.channel.ChannelDetailActivity.3.1 */
        class C03011 implements AnimationListener {
            final /* synthetic */ C03023 f3480a;

            C03011(C03023 c03023) {
                this.f3480a = c03023;
            }

            @SuppressLint({"NewApi"})
            public void onAnimationEnd(Animation animation) {
                this.f3480a.f3481a.f3493s.setVisibility(4);
                this.f3480a.f3481a.f3494t.setVisibility(0);
                this.f3480a.f3481a.f3500z.f3502b = Boolean.valueOf(true);
                this.f3480a.f3481a.f3494t.setText(this.f3480a.f3481a.getResources().getString(2131296387));
                this.f3480a.f3481a.f3494t.setTextColor(this.f3480a.f3481a.getResources().getColor(2131689502));
                if (this.f3480a.f3481a.f3487A < 16) {
                    this.f3480a.f3481a.f3492r.setBackgroundDrawable(this.f3480a.f3481a.getResources().getDrawable(2130837645));
                } else {
                    this.f3480a.f3481a.f3492r.setBackground(this.f3480a.f3481a.getResources().getDrawable(2130837645));
                }
                this.f3480a.f3481a.f3500z.f3501a.setFollowers(this.f3480a.f3481a.f3500z.f3501a.getFollowers() + 1);
                this.f3480a.f3481a.f3496v.setText(this.f3480a.f3481a.m6102a((double) this.f3480a.f3481a.f3500z.f3501a.getFollowers()));
                this.f3480a.f3481a.f3492r.clearAnimation();
                this.f3480a.f3481a.f3497w.clearAnimation();
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(300);
                this.f3480a.f3481a.f3492r.startAnimation(alphaAnimation);
                this.f3480a.f3481a.f3497w.startAnimation(alphaAnimation);
                this.f3480a.f3481a.f3497w.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        C03023(ChannelDetailActivity channelDetailActivity) {
            this.f3481a = channelDetailActivity;
        }

        public void run() {
            this.f3481a.f3492r.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(300);
            alphaAnimation.setAnimationListener(new C03011(this));
            this.f3481a.f3492r.startAnimation(alphaAnimation);
        }
    }

    /* renamed from: app.messaging.channel.ChannelDetailActivity.4 */
    class C03034 implements Runnable {
        final /* synthetic */ ChannelDetailActivity f3482a;

        C03034(ChannelDetailActivity channelDetailActivity) {
            this.f3482a = channelDetailActivity;
        }

        public void run() {
            this.f3482a.f3493s.setVisibility(4);
            this.f3482a.f3492r.setVisibility(0);
        }
    }

    /* renamed from: app.messaging.channel.ChannelDetailActivity.5 */
    class C03055 implements Runnable {
        final /* synthetic */ ChannelDetailActivity f3484a;

        /* renamed from: app.messaging.channel.ChannelDetailActivity.5.1 */
        class C03041 implements AnimationListener {
            final /* synthetic */ C03055 f3483a;

            C03041(C03055 c03055) {
                this.f3483a = c03055;
            }

            @SuppressLint({"NewApi"})
            public void onAnimationEnd(Animation animation) {
                this.f3483a.f3484a.f3497w.setVisibility(8);
                this.f3483a.f3484a.f3493s.setVisibility(4);
                this.f3483a.f3484a.f3494t.setVisibility(0);
                this.f3483a.f3484a.f3494t.setText(this.f3483a.f3484a.getResources().getString(2131296384));
                this.f3483a.f3484a.f3494t.setTextColor(this.f3483a.f3484a.getResources().getColor(2131689503));
                if (this.f3483a.f3484a.f3487A < 16) {
                    this.f3483a.f3484a.f3492r.setBackgroundDrawable(this.f3483a.f3484a.getResources().getDrawable(2130837643));
                } else {
                    this.f3483a.f3484a.f3492r.setBackground(this.f3483a.f3484a.getResources().getDrawable(2130837643));
                }
                if (this.f3483a.f3484a.f3500z.f3501a.getFollowers() > 0) {
                    this.f3483a.f3484a.f3500z.f3501a.setFollowers(this.f3483a.f3484a.f3500z.f3501a.getFollowers() - 1);
                }
                this.f3483a.f3484a.f3496v.setText(this.f3483a.f3484a.m6102a((double) this.f3483a.f3484a.f3500z.f3501a.getFollowers()));
                this.f3483a.f3484a.f3492r.clearAnimation();
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(300);
                this.f3483a.f3484a.f3492r.startAnimation(alphaAnimation);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        C03055(ChannelDetailActivity channelDetailActivity) {
            this.f3484a = channelDetailActivity;
        }

        public void run() {
            this.f3484a.f3500z.f3502b = Boolean.valueOf(false);
            this.f3484a.f3492r.clearAnimation();
            this.f3484a.f3497w.clearAnimation();
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(300);
            alphaAnimation.setAnimationListener(new C03041(this));
            this.f3484a.f3492r.startAnimation(alphaAnimation);
            this.f3484a.f3497w.startAnimation(alphaAnimation);
        }
    }

    /* renamed from: app.messaging.channel.ChannelDetailActivity.6 */
    class C03066 implements Runnable {
        final /* synthetic */ ChannelCommunicationUnfollowEntity f3485a;
        final /* synthetic */ ChannelDetailActivity f3486b;

        C03066(ChannelDetailActivity channelDetailActivity, ChannelCommunicationUnfollowEntity channelCommunicationUnfollowEntity) {
            this.f3486b = channelDetailActivity;
            this.f3485a = channelCommunicationUnfollowEntity;
        }

        public void run() {
            this.f3485a.getHolder().f3435f.setVisibility(4);
            this.f3485a.getHolder().f3433d.setVisibility(0);
        }
    }

    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903072);
        ButterKnife.m7741a((Activity) this);
        EventBus.m12779a().m12791a((Object) this);
        this.f3488B = new Handler();
        this.f3500z = (ChannelDetailAdapterModel) getIntent().getParcelableExtra(DataPacketExtension.ELEMENT);
        this.f3499y = getIntent().getBooleanExtra("messaging", false);
        setTitle(this.f3500z.f3501a.getTitle());
        this.f3495u.setText(this.f3500z.f3501a.getTitle());
        this.f3491q.setText(this.f3500z.f3501a.getDescription());
        CustomImageLoader.m4009a().m4020a(this.f3489o, this.f3500z.f3501a.getAvatar(), null, 2130837647);
        CustomImageLoader.m4009a().m4020a(this.f3490p, this.f3500z.f3501a.getCover(), null, 2130837647);
        this.f3487A = VERSION.SDK_INT;
        this.f3496v.setText(m6102a((double) this.f3500z.f3501a.getFollowers()));
        m6109n();
        m6107l();
        if (this.f3500z.f3502b != null) {
            if (this.f3500z.f3502b.booleanValue()) {
                this.f3497w.setVisibility(0);
            } else {
                this.f3497w.setVisibility(8);
            }
        }
        this.f3492r.setVisibility(0);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (this.f3499y) {
                    Intent intent = new Intent();
                    String str = "extra_channel_read_only";
                    boolean z = !(this.f3500z.f3502b == null || this.f3500z.f3502b.booleanValue()) || this.f3500z.f3501a.m6117a();
                    intent.putExtra(str, z);
                    setResult(-1, intent);
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onBackPressed() {
        if (this.f3499y) {
            Intent intent = new Intent();
            String str = "extra_channel_read_only";
            boolean z = !(this.f3500z.f3502b == null || this.f3500z.f3502b.booleanValue()) || this.f3500z.f3501a.m6117a();
            intent.putExtra(str, z);
            setResult(-1, intent);
        }
        finish();
    }

    @SuppressLint({"NewApi"})
    private void m6107l() {
        if (this.f3500z.f3502b == null) {
            this.f3494t.setVisibility(4);
            this.f3493s.setVisibility(0);
        } else if (this.f3500z.f3502b.booleanValue()) {
            this.f3494t.setVisibility(0);
            this.f3493s.setVisibility(4);
            this.f3494t.setText(getResources().getString(2131296387));
            this.f3494t.setTextColor(getResources().getColor(2131689507));
            if (this.f3487A < 16) {
                this.f3492r.setBackgroundDrawable(getResources().getDrawable(2130837645));
            } else {
                this.f3492r.setBackground(getResources().getDrawable(2130837645));
            }
        } else {
            this.f3494t.setVisibility(0);
            this.f3493s.setVisibility(4);
            this.f3494t.setText(getResources().getString(2131296384));
            this.f3494t.setTextColor(getResources().getColor(2131689503));
            if (this.f3487A < 16) {
                this.f3492r.setBackgroundDrawable(getResources().getDrawable(2130837643));
            } else {
                this.f3492r.setBackground(getResources().getDrawable(2130837643));
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.m12779a().m12794c(this);
    }

    private void m6108m() {
        new Builder(this, 2131558538).m1980a(getString(2131296389)).m1986b(String.format(getString(2131296425), new Object[]{this.f3500z.f3501a.getTitle()})).m1975a(17039379, new C03002(this)).m1985b(17039369, new C02991(this)).m1989c(2130837731).m1992c();
    }

    private void m6105b(String str) {
        this.f3493s.setVisibility(0);
        this.f3494t.setVisibility(4);
        this.f3500z.f3502b = null;
        ChannelCommunication.getInstance().m6095a(str, null, false);
    }

    private void m6106c(String str) {
        this.f3500z.f3502b = null;
        this.f3493s.setVisibility(0);
        this.f3494t.setVisibility(4);
        ChannelCommunication.getInstance().m6094a(this.f3500z.f3501a, str, null);
    }

    private String m6102a(double d) {
        if (d < 1000.0d) {
            return String.format(getResources().getString(2131296385), new Object[]{new DecimalFormat("##").format(d)});
        }
        double d2 = d / 1000.0d;
        return String.format(getResources().getString(2131296386), new Object[]{new DecimalFormat("##.#").format(d2)});
    }

    public void m6110j() {
        if (this.f3500z.f3502b != null) {
            if (this.f3500z.f3502b.booleanValue()) {
                this.f3498x = true;
                m6108m();
                return;
            }
            this.f3498x = false;
            m6106c(this.f3500z.f3501a.getId());
        }
    }

    public void onEvent(ChannelCommunicationFollowEntity channelCommunicationFollowEntity) {
        if (!isFinishing() && channelCommunicationFollowEntity.getJabberId().equals(this.f3500z.f3501a.getId())) {
            if (channelCommunicationFollowEntity.m6098a()) {
                this.f3488B.post(new C03023(this));
            } else {
                this.f3488B.post(new C03034(this));
            }
        }
    }

    public void onEvent(ChannelCommunicationUnfollowEntity channelCommunicationUnfollowEntity) {
        if (!isFinishing() && channelCommunicationUnfollowEntity.getJabberId().equals(this.f3500z.f3501a.getId())) {
            if (channelCommunicationUnfollowEntity.m6099a()) {
                this.f3488B.post(new C03055(this));
            } else {
                this.f3488B.post(new C03066(this, channelCommunicationUnfollowEntity));
            }
        }
    }

    private void m6109n() {
        int a = ChannelCommunication.getInstance().m6092a(this.f3500z.f3501a.getId());
        if (a == -1) {
            this.f3500z.f3502b = null;
        } else if (a == 0) {
            this.f3500z.f3502b = Boolean.valueOf(false);
        } else if (a == 1) {
            this.f3500z.f3502b = Boolean.valueOf(true);
        }
    }

    public void m6111k() {
        boolean z = true;
        if (this.f3499y) {
            Intent intent = new Intent();
            String str = "extra_channel_read_only";
            if ((this.f3500z.f3502b == null || this.f3500z.f3502b.booleanValue()) && !this.f3500z.f3501a.m6117a()) {
                z = false;
            }
            intent.putExtra(str, z);
            setResult(-1, intent);
            finish();
            return;
        }
        intent = new Intent(this, ChannelMessagingActivity.class);
        intent.putExtra("extra_channel_jid", this.f3500z.f3501a.getId());
        intent.putExtra("extra_channel_read_only", this.f3500z.f3501a.m6117a());
        intent.putExtra("extra_channel_comming_details", true);
        startActivity(intent);
    }
}
