package app.setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import app.common.BaseActivity;
import app.messaging.RecycleMessagingAdapter;
import app.util.SharedPreferencesUtil;
import butterknife.ButterKnife;
import net.simonvt.numberpicker.NumberPicker;

public class SettingMessageActivity extends BaseActivity {
    private Dialog f4434o;

    /* renamed from: app.setting.SettingMessageActivity.1 */
    class C04641 implements OnClickListener {
        final /* synthetic */ NumberPicker f4432a;
        final /* synthetic */ SettingMessageActivity f4433b;

        C04641(SettingMessageActivity settingMessageActivity, NumberPicker numberPicker) {
            this.f4433b = settingMessageActivity;
            this.f4432a = numberPicker;
        }

        public void onClick(View view) {
            RecycleMessagingAdapter.f3297b = this.f4432a.getValue();
            SharedPreferencesUtil.m7053a(this.f4433b.getString(2131296926), this.f4432a.getValue());
            this.f4433b.f4434o.dismiss();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903098);
        setTitle(getString(2131296726));
        ButterKnife.m7741a((Activity) this);
    }

    void m6839j() {
        startActivity(new Intent(this, SettingBackgroundActivity.class));
    }

    void m6840k() {
        startActivity(new Intent(this, CleanMessageActivity.class));
    }

    void m6841l() {
        m6838m();
    }

    private void m6838m() {
        this.f4434o = new Dialog(this);
        setTheme(2131558590);
        this.f4434o.requestWindowFeature(1);
        this.f4434o.setContentView(2130903123);
        this.f4434o.getWindow().setBackgroundDrawableResource(17170445);
        Button button = (Button) this.f4434o.findViewById(2131755321);
        NumberPicker numberPicker = (NumberPicker) this.f4434o.findViewById(2131755320);
        numberPicker.setMinValue(14);
        numberPicker.setMaxValue(30);
        numberPicker.setValue(SharedPreferencesUtil.m7057b(getString(2131296926), 16));
        numberPicker.setFocusable(true);
        numberPicker.setFocusableInTouchMode(true);
        button.setOnClickListener(new C04641(this, numberPicker));
        this.f4434o.show();
    }
}
