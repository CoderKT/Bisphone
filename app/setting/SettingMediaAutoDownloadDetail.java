package app.setting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import app.common.BaseActivity;
import app.util.SharedPreferencesUtil;
import butterknife.ButterKnife;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class SettingMediaAutoDownloadDetail extends BaseActivity {
    ImageView f4422o;
    ImageView f4423p;
    ImageView f4424q;
    String f4425r;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903097);
        ButterKnife.m7741a((Activity) this);
        this.f4425r = getResources().getString(getIntent().getBooleanExtra(DataPacketExtension.ELEMENT, false) ? 2131296927 : 2131296938);
        m6830m();
    }

    void m6831j() {
        SharedPreferencesUtil.m7055a(this.f4425r, "0");
        m6830m();
    }

    void m6832k() {
        SharedPreferencesUtil.m7055a(this.f4425r, "2");
        m6830m();
    }

    void m6833l() {
        SharedPreferencesUtil.m7055a(this.f4425r, "1");
        m6830m();
    }

    private void m6830m() {
        String b = SharedPreferencesUtil.m7058b(this.f4425r, null);
        this.f4422o.setImageResource(2130837677);
        this.f4424q.setImageResource(2130837677);
        this.f4423p.setImageResource(2130837677);
        if (b.equals("0")) {
            this.f4423p.setImageResource(2130837676);
        } else if (b.equals("1")) {
            this.f4424q.setImageResource(2130837676);
        } else {
            this.f4422o.setImageResource(2130837676);
        }
    }
}
