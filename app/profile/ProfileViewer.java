package app.profile;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import app.Main;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryNormalMessageEntity.Builder;
import app.database.datasource.ContactDataSource;
import app.events.vcard.VCardRefreshEvent;
import app.events.xmpp.LastActivityEvent;
import app.events.xmpp.LastActivityEvent.LastActivityListener;
import app.events.xmpp.SendPacketEvent;
import app.messaging.NormalMessagingActivity;
import app.util.CallUtil;
import app.util.DialogsUtil;
import app.util.PixelConverter;
import app.util.StringUtil;
import app.util.TimeUtils;
import app.util.Utils;
import app.util.XMPPUtils;
import app.xmpp.JabberId;
import app.xmpp.NetworkConnectivityChangeReceiver;
import app.xmpp.VCardHandler;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class ProfileViewer extends BaseActivity implements OnClickListener {
    Handler f4275A;
    List<ContactEntity> f4276B;
    int f4277C;
    ProfileModel f4278D;
    boolean f4279E;
    ImageView f4280o;
    TextView f4281p;
    TextView f4282q;
    Button f4283r;
    Button f4284s;
    Button f4285t;
    Button f4286u;
    TextView f4287v;
    LinearLayout f4288w;
    LinearLayout f4289x;
    LinearLayout f4290y;
    ImageView f4291z;

    /* renamed from: app.profile.ProfileViewer.1 */
    class C04431 implements Runnable {
        final /* synthetic */ ProfileViewer f4268a;

        C04431(ProfileViewer profileViewer) {
            this.f4268a = profileViewer;
        }

        public void run() {
            this.f4268a.m6738s();
        }
    }

    /* renamed from: app.profile.ProfileViewer.2 */
    class C04442 implements DialogInterface.OnClickListener {
        final /* synthetic */ ProfileViewer f4269a;

        C04442(ProfileViewer profileViewer) {
            this.f4269a = profileViewer;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: app.profile.ProfileViewer.3 */
    class C04453 implements DialogInterface.OnClickListener {
        final /* synthetic */ ProfileViewer f4270a;

        C04453(ProfileViewer profileViewer) {
            this.f4270a = profileViewer;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Builder builder = new Builder();
            builder.m4356a(Type.TEXT).m4369c(this.f4270a.getResources().getString(2131296656)).m4353a(DeliveryStatus.SENDING).m4372d(System.currentTimeMillis() + "");
            builder.m4455t(this.f4270a.f4278D.m6722d());
            builder.m4359a(StringUtil.m7065b());
            builder.m4365b(new JabberId(this.f4270a.f4278D.m6722d(), "bisphone.com", null).m7391e());
            builder.m4455t(this.f4270a.f4278D.m6722d());
            EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7091a(builder.m4453a(), 1)));
        }
    }

    /* renamed from: app.profile.ProfileViewer.4 */
    class C04474 implements LastActivityListener {
        final /* synthetic */ String f4273a;
        final /* synthetic */ ProfileViewer f4274b;

        /* renamed from: app.profile.ProfileViewer.4.1 */
        class C04461 implements Runnable {
            final /* synthetic */ String f4271a;
            final /* synthetic */ C04474 f4272b;

            C04461(C04474 c04474, String str) {
                this.f4272b = c04474;
                this.f4271a = str;
            }

            public void run() {
                this.f4272b.f4274b.f4282q.setText(this.f4271a);
            }
        }

        C04474(ProfileViewer profileViewer, String str) {
            this.f4274b = profileViewer;
            this.f4273a = str;
        }

        public void m6733a(long j) {
            if (!this.f4274b.isFinishing()) {
                if (this.f4274b.f4276B != null || this.f4274b.f4278D != null) {
                    String d = (this.f4274b.f4276B == null || this.f4274b.f4276B.size() <= 0) ? this.f4274b.f4278D == null ? null : this.f4274b.f4278D.m6722d() : ((ContactEntity) this.f4274b.f4276B.get(this.f4274b.f4277C)).m4200g();
                    if (d != null && d.equals(this.f4273a)) {
                        this.f4274b.f4275A.post(new C04461(this, TimeUtils.m7073b(j)));
                    }
                }
            }
        }

        public void m6732a() {
        }
    }

    public ProfileViewer() {
        this.f4277C = 0;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m6736q();
        this.f4275A = new Handler();
        m6737r();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6736q();
        m6738s();
    }

    private void m6736q() {
        if (getResources().getConfiguration().orientation == 1) {
            setContentView(2130903198);
        } else {
            setContentView(2130903199);
        }
        ButterKnife.m7741a((Activity) this);
    }

    private void m6737r() {
        this.f4279E = false;
        this.f4278D = (ProfileModel) getIntent().getParcelableExtra(DataPacketExtension.ELEMENT);
        m6735b(this.f4278D.m6722d());
        if (this.f4278D.m6718b() == null || Integer.parseInt(this.f4278D.m6718b()) <= 0) {
            this.f4276B = new ArrayList();
        } else {
            this.f4276B = ContactDataSource.m4553a().m4563a(this.f4278D.m6718b());
            for (int i = 0; i < this.f4276B.size(); i++) {
                if (this.f4278D.m6722d().equals(((ContactEntity) this.f4276B.get(i)).m4200g())) {
                    ContactEntity contactEntity = (ContactEntity) this.f4276B.get(i);
                    this.f4276B.remove(i);
                    this.f4276B.add(0, contactEntity);
                    break;
                }
            }
            if (this.f4276B.size() > 0 && ((ContactEntity) this.f4276B.get(0)).m4214q().longValue() > 0) {
                ContactDataSource.m4553a().m4576d(this.f4278D.m6722d());
            }
        }
        VCardHandler.m7499a().m7507d(new JabberId(this.f4278D.m6722d(), "bisphone.com", null).m7391e());
        EventBus.m12779a().m12791a((Object) this);
        m6738s();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.m12779a().m12794c(this);
    }

    private void m6738s() {
        this.f4282q.setText("");
        this.f4286u.setVisibility(8);
        this.f4285t.setVisibility(8);
        this.f4288w.setVisibility(8);
        this.f4289x.setVisibility(8);
        this.f4283r.setVisibility(8);
        this.f4284s.setVisibility(8);
        String d;
        if (this.f4278D.m6723e() == TYPE.LOCAL) {
            this.f4288w.setVisibility(0);
            if (this.f4276B.size() == 0) {
                this.f4287v.setText(String.format("+%s", new Object[]{this.f4278D.m6722d()}));
                this.f4281p.setText(this.f4278D.m6720c());
                d = this.f4278D.m6722d();
            } else {
                String g = ((ContactEntity) this.f4276B.get(this.f4277C)).m4200g();
                while (true) {
                    if (!((ContactEntity) this.f4276B.get(this.f4277C)).m4200g().startsWith("0") && !((ContactEntity) this.f4276B.get(this.f4277C)).m4200g().startsWith("+")) {
                        break;
                    }
                    ((ContactEntity) this.f4276B.get(this.f4277C)).m4197f(((ContactEntity) this.f4276B.get(this.f4277C)).m4200g().substring(1));
                }
                this.f4281p.setText(((ContactEntity) this.f4276B.get(this.f4277C)).m4196e());
                String n = ((ContactEntity) this.f4276B.get(this.f4277C)).m4211n();
                d = ((ContactEntity) this.f4276B.get(this.f4277C)).m4212o();
                CustomImageLoader.m4009a().m4020a(this.f4280o, d, n, 2130837592);
                CustomImageLoader.m4009a().m4020a(this.f4291z, d, n, 2130837592);
                if (((ContactEntity) this.f4276B.get(this.f4277C)).m4206j()) {
                    this.f4284s.setVisibility(0);
                    this.f4283r.setVisibility(0);
                } else {
                    this.f4286u.setVisibility(0);
                }
                if (this.f4276B.size() > 1) {
                    this.f4289x.setVisibility(0);
                    m6739t();
                }
                this.f4287v.setText(String.format("+%s", new Object[]{g}));
                d = g;
            }
            m6735b(d);
        } else if (this.f4278D.m6723e() == TYPE.REMOTE) {
            CharSequence string;
            if (this.f4278D.m6722d() != null) {
                d = this.f4278D.m6722d();
                while (true) {
                    if (!this.f4278D.m6722d().startsWith("0") && !this.f4278D.m6722d().startsWith("+")) {
                        break;
                    }
                    this.f4278D.m6719b(this.f4278D.m6722d().substring(1));
                }
            } else {
                d = null;
            }
            TextView textView = this.f4281p;
            if (this.f4278D.m6724f() == null) {
                string = getString(2131296307);
            } else {
                string = this.f4278D.m6724f();
            }
            textView.setText(string);
            CustomImageLoader.m4009a().m4020a(this.f4280o, this.f4278D.m6716a(), null, 2130837592);
            CustomImageLoader.m4009a().m4020a(this.f4291z, this.f4278D.m6716a(), null, 2130837592);
            this.f4284s.setVisibility(8);
            this.f4283r.setVisibility(8);
            this.f4285t.setVisibility(0);
            m6735b(d);
        } else {
            this.f4284s.setVisibility(8);
            this.f4283r.setVisibility(8);
            this.f4285t.setVisibility(8);
            this.f4286u.setVisibility(0);
        }
    }

    private void m6739t() {
        if (this.f4276B != null && this.f4276B.size() != 0) {
            this.f4290y.removeAllViews();
            for (int i = 0; i < this.f4276B.size(); i++) {
                if (i != this.f4277C) {
                    View inflate = getLayoutInflater().inflate(2130903200, this.f4289x, false);
                    inflate.setTag(Integer.valueOf(i));
                    inflate.setOnClickListener(this);
                    ((TextView) inflate.findViewById(2131755528)).setText(String.format("+%s", new Object[]{((ContactEntity) this.f4276B.get(i)).m4200g()}));
                    View view = new View(this);
                    view.setLayoutParams(new LayoutParams(-1, (int) PixelConverter.m7048a(1.0f, this)));
                    view.setBackgroundColor(getResources().getColor(2131689607));
                    this.f4290y.addView(inflate);
                    this.f4290y.addView(view);
                }
            }
        }
    }

    public void onEvent(VCardRefreshEvent vCardRefreshEvent) {
        int i = 1;
        int i2 = 0;
        ContactEntity b;
        if (this.f4278D.m6718b() == null || Integer.parseInt(this.f4278D.m6718b()) <= 0) {
            this.f4276B = new ArrayList();
            b = ContactDataSource.m4553a().m4570b(this.f4278D.m6722d());
            if (((this.f4278D.m6716a() == null && b.m4212o() == null) || (this.f4278D.m6716a() != null && this.f4278D.m6716a().equals(b.m4212o()))) && ((this.f4278D.m6724f() == null && b.m4209l() == null) || (this.f4278D.m6724f() != null && this.f4278D.m6724f().equals(b.m4209l())))) {
                i = 0;
            }
            this.f4278D.m6717a(b.m4212o());
            this.f4278D.m6721c(b.m4209l());
            i2 = i;
        } else {
            this.f4276B = ContactDataSource.m4553a().m4563a(this.f4278D.m6718b());
            int i3 = 0;
            while (i3 < this.f4276B.size()) {
                if (this.f4278D.m6722d().equals(((ContactEntity) this.f4276B.get(i3)).m4200g())) {
                    b = (ContactEntity) this.f4276B.get(i3);
                    this.f4276B.remove(i3);
                    this.f4276B.add(0, b);
                    if (this.f4278D.m6716a() == null) {
                        if (((ContactEntity) this.f4276B.get(i3)).m4212o() != null) {
                            i2 = 1;
                        }
                    } else if (!this.f4278D.m6716a().equals(((ContactEntity) this.f4276B.get(i3)).m4212o())) {
                        i2 = 1;
                    }
                } else {
                    i3++;
                }
            }
        }
        if (i2 != 0) {
            this.f4275A.post(new C04431(this));
        }
    }

    void m6740j() {
        this.f4291z.setVisibility(0);
        CustomImageLoader.m4009a().m4020a(this.f4291z, this.f4278D.m6716a(), null, 2130837592);
    }

    void m6741k() {
        this.f4291z.setVisibility(8);
    }

    public void m6742l() {
        String d;
        if (this.f4276B == null || this.f4276B.size() <= this.f4277C) {
            d = this.f4278D.m6722d();
        } else {
            d = ((ContactEntity) this.f4276B.get(this.f4277C)).m4200g();
        }
        if (NetworkConnectivityChangeReceiver.m7394a(this)) {
            CallUtil.m7005a(d, this, false);
        } else {
            DialogsUtil.m7014a(this);
        }
    }

    public void m6743m() {
        String d;
        if (this.f4276B == null || this.f4276B.size() <= this.f4277C) {
            d = this.f4278D.m6722d();
        } else {
            d = ((ContactEntity) this.f4276B.get(this.f4277C)).m4200g();
        }
        Intent intent = new Intent(this, NormalMessagingActivity.class);
        intent.putExtra("extra_jabber_id", Utils.m7078a(d));
        startActivity(intent);
    }

    public void m6744n() {
        if (NetworkConnectivityChangeReceiver.m7394a(this)) {
            CallUtil.m7005a(this.f4276B.size() > this.f4277C ? ((ContactEntity) this.f4276B.get(this.f4277C)).m4200g() : this.f4278D.m6722d(), this, true);
        } else {
            DialogsUtil.m7014a(this);
        }
    }

    public void m6745o() {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + (this.f4277C >= this.f4276B.size() ? this.f4278D.m6722d() : ((ContactEntity) this.f4276B.get(this.f4277C)).m4200g())));
        intent.putExtra("sms_body", getString(2131296653));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Main.m3905a(getResources().getString(2131296327));
        }
    }

    public void m6746p() {
        String f = this.f4278D.m6724f() == null ? "unknown" : this.f4278D.m6724f();
        new AlertDialog.Builder(this, 2131558537).m1980a(getResources().getString(2131296647)).m1986b(String.format(getResources().getString(2131296646), new Object[]{f, f})).m1975a(2131296645, new C04453(this)).m1985b(17039360, new C04442(this)).m1989c(2130837730).m1992c();
    }

    public void onBackPressed() {
        if (!isFinishing()) {
            if (this.f4291z.getVisibility() == 0) {
                this.f4291z.setVisibility(8);
            } else {
                super.onBackPressed();
            }
        }
    }

    private void m6735b(String str) {
        if (str != null) {
            LastActivityEvent lastActivityEvent = new LastActivityEvent(new JabberId(str, "bisphone.com", null));
            lastActivityEvent.m4983a(new C04474(this, str));
            EventBus.m12779a().m12795d(lastActivityEvent);
        }
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue >= 0 && intValue < this.f4276B.size()) {
            this.f4277C = intValue;
            m6738s();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return false;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }
}
