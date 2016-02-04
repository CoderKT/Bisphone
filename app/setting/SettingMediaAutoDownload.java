package app.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import app.common.BaseActivity;
import app.util.SharedPreferencesUtil;
import butterknife.ButterKnife;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import se.emilsjolander.stickylistheaders.C1128R;

public class SettingMediaAutoDownload extends BaseActivity {
    TextView f4414o;
    TextView f4415p;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903096);
        setTitle(getString(2131296682));
        ButterKnife.m7741a((Activity) this);
    }

    protected void onResume() {
        super.onResume();
        m6823l();
        m6824m();
    }

    private void m6823l() {
        String b = SharedPreferencesUtil.m7058b(getResources().getString(2131296927), null);
        if (b == null) {
            SharedPreferencesUtil.m7055a(getResources().getString(2131296927), "0");
            b = "0";
        }
        m6822a(Integer.parseInt(b), this.f4415p);
    }

    private void m6824m() {
        String b = SharedPreferencesUtil.m7058b(getResources().getString(2131296938), null);
        if (b == null) {
            SharedPreferencesUtil.m7055a(getResources().getString(2131296938), "2");
            b = "2";
        }
        m6822a(Integer.parseInt(b), this.f4414o);
    }

    private void m6822a(int i, TextView textView) {
        CharSequence charSequence = "";
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                charSequence = getResources().getString(2131296688);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                charSequence = getResources().getString(2131296687);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                charSequence = getResources().getString(2131296685);
                break;
        }
        textView.setText(charSequence);
    }

    void m6825j() {
        Intent intent = new Intent(this, SettingMediaAutoDownloadDetail.class);
        intent.putExtra(DataPacketExtension.ELEMENT, true);
        startActivity(intent);
    }

    void m6826k() {
        Intent intent = new Intent(this, SettingMediaAutoDownloadDetail.class);
        intent.putExtra(DataPacketExtension.ELEMENT, false);
        startActivity(intent);
    }
}
