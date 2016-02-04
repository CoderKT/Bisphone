package app.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import app.common.BaseActivity;
import app.messaging.channel.ChannelCategoryActivity;
import app.messaging.stickers.OnlineStickersActivity;
import butterknife.ButterKnife;

public class SettingMain extends BaseActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903094);
        ButterKnife.m7741a((Activity) this);
        setTitle(getResources().getString(2131296808));
    }

    void m6805j() {
        startActivity(new Intent(this, ChannelCategoryActivity.class));
    }

    void m6806k() {
        startActivity(new Intent(this, OnlineStickersActivity.class));
    }

    void m6807l() {
        startActivity(new Intent(this, SettingGeneral.class));
    }

    void m6808m() {
        startActivity(new Intent(this, SettingMedia.class));
    }

    void m6809n() {
        startActivity(new Intent(this, SettingMessageActivity.class));
    }
}
