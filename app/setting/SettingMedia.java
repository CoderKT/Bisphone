package app.setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import app.Main;
import app.common.BaseActivity;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.SharedPreferencesUtil;
import butterknife.ButterKnife;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.Calendar;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import se.emilsjolander.stickylistheaders.C1128R;

public class SettingMedia extends BaseActivity implements OnClickListener {
    View f4401o;
    ImageView f4402p;
    View f4403q;
    ImageView f4404r;
    View f4405s;
    Dialog f4406t;
    PermissionMediaClick f4407u;
    private boolean f4408v;
    private boolean f4409w;

    /* renamed from: app.setting.SettingMedia.1 */
    class C04621 implements Runnable {
        final /* synthetic */ Calendar f4394a;
        final /* synthetic */ SettingMedia f4395b;

        C04621(SettingMedia settingMedia, Calendar calendar) {
            this.f4395b = settingMedia;
            this.f4394a = calendar;
        }

        public void run() {
            ImageLoader.m11076a().m11089b();
            HistoryNormalMessageDataSource.m4731d(this.f4394a.getTimeInMillis() * 1000);
            HistoryGroupDataSource.m4699b(this.f4394a.getTimeInMillis() * 1000);
            HistoryChannelDataSource.m4675b(this.f4394a.getTimeInMillis() * 1000);
        }
    }

    /* renamed from: app.setting.SettingMedia.2 */
    /* synthetic */ class C04632 {
        static final /* synthetic */ int[] f4396a;

        static {
            f4396a = new int[PermissionMediaClick.values().length];
            try {
                f4396a[PermissionMediaClick.autoDownloadPhoto.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4396a[PermissionMediaClick.autoDownloadVideo.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4396a[PermissionMediaClick.cleanMedia.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    enum PermissionMediaClick {
        autoDownloadPhoto,
        autoDownloadVideo,
        cleanMedia
    }

    public SettingMedia() {
        this.f4408v = false;
        this.f4409w = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903095);
        setTitle(getString(2131296713));
        ButterKnife.m7741a((Activity) this);
        this.f4408v = SharedPreferencesUtil.m7060b(getResources().getString(2131296917), Boolean.valueOf(false));
        this.f4409w = SharedPreferencesUtil.m7060b(getResources().getString(2131296918), Boolean.valueOf(false));
        m6815o();
        m6814n();
    }

    private void m6814n() {
        this.f4404r.setImageResource(this.f4409w ? 2130837676 : 2130837677);
    }

    private void m6815o() {
        this.f4402p.setImageResource(this.f4408v ? 2130837676 : 2130837677);
    }

    void m6816j() {
        if (PermissionUtil.m7044a(PermissionType.storage) || this.f4408v) {
            this.f4408v = !this.f4408v;
            SharedPreferencesUtil.m7054a(getResources().getString(2131296917), Boolean.valueOf(this.f4408v));
            m6815o();
            return;
        }
        this.f4407u = PermissionMediaClick.autoDownloadPhoto;
        PermissionUtil.m7042a((Activity) this, PermissionType.storage, 2);
    }

    void m6817k() {
        if (PermissionUtil.m7044a(PermissionType.storage) || this.f4409w) {
            this.f4409w = !this.f4409w;
            SharedPreferencesUtil.m7054a(getResources().getString(2131296918), Boolean.valueOf(this.f4409w));
            m6814n();
            return;
        }
        this.f4407u = PermissionMediaClick.autoDownloadVideo;
        PermissionUtil.m7042a((Activity) this, PermissionType.storage, 2);
    }

    void m6818l() {
        if (PermissionUtil.m7044a(PermissionType.storage)) {
            this.f4406t = new Dialog(this);
            this.f4406t.requestWindowFeature(1);
            this.f4406t.setContentView(2130903120);
            this.f4406t.getWindow().setBackgroundDrawableResource(17170445);
            this.f4406t.findViewById(2131755315).setOnClickListener(this);
            this.f4406t.findViewById(2131755312).setOnClickListener(this);
            this.f4406t.findViewById(2131755313).setOnClickListener(this);
            this.f4406t.findViewById(2131755314).setOnClickListener(this);
            this.f4406t.show();
            return;
        }
        this.f4407u = PermissionMediaClick.cleanMedia;
        PermissionUtil.m7042a((Activity) this, PermissionType.storage, 2);
    }

    void m6819m() {
        Intent intent = new Intent(this, SettingMediaAutoDownload.class);
        intent.putExtra(DataPacketExtension.ELEMENT, true);
        startActivity(intent);
    }

    public void onClick(View view) {
        Calendar instance = Calendar.getInstance();
        Main.f1926a.m5683d(instance.getTimeInMillis() + "");
        switch (view.getId()) {
            case 2131755312:
                instance.add(6, -14);
                break;
            case 2131755313:
                instance.add(2, -1);
                break;
            case 2131755314:
                instance.add(2, -6);
                break;
        }
        this.f4406t.dismiss();
        Main.f1926a.m5683d(instance.getTimeInMillis() + "");
        new Thread(new C04621(this, instance)).start();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length != 0 && iArr[0] == 0) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    switch (C04632.f4396a[this.f4407u.ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            m6816j();
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            m6817k();
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            m6818l();
                        default:
                    }
                default:
            }
        }
    }
}
