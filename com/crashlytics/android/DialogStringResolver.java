package com.crashlytics.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.PromptSettingsData;

class DialogStringResolver {
    private final Context f5399a;
    private final PromptSettingsData f5400b;

    public DialogStringResolver(Context context, PromptSettingsData promptSettingsData) {
        this.f5399a = context;
        this.f5400b = promptSettingsData;
    }

    public String m7999a() {
        return m7996a("com.crashlytics.CrashSubmissionPromptTitle", this.f5400b.f8417a);
    }

    public String m8000b() {
        return m7996a("com.crashlytics.CrashSubmissionPromptMessage", this.f5400b.f8418b);
    }

    public String m8001c() {
        return m7996a("com.crashlytics.CrashSubmissionSendTitle", this.f5400b.f8419c);
    }

    public String m8002d() {
        return m7996a("com.crashlytics.CrashSubmissionAlwaysSendTitle", this.f5400b.f8423g);
    }

    public String m8003e() {
        return m7996a("com.crashlytics.CrashSubmissionCancelTitle", this.f5400b.f8421e);
    }

    private String m7996a(String str, String str2) {
        return m7998b(CommonUtils.m13023b(this.f5399a, str), str2);
    }

    private String m7998b(String str, String str2) {
        return m7997a(str) ? str2 : str;
    }

    private boolean m7997a(String str) {
        return str == null || str.length() == 0;
    }
}
