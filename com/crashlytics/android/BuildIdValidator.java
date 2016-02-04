package com.crashlytics.android;

import android.util.Log;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;

class BuildIdValidator {
    private final String f5286a;
    private final boolean f5287b;

    public BuildIdValidator(String str, boolean z) {
        this.f5286a = str;
        this.f5287b = z;
    }

    public void m7765a(String str, String str2) {
        if (CommonUtils.m13028c(this.f5286a) && this.f5287b) {
            String b = m7766b(str, str2);
            Log.e("Fabric", ".");
            Log.e("Fabric", ".     |  | ");
            Log.e("Fabric", ".     |  |");
            Log.e("Fabric", ".     |  |");
            Log.e("Fabric", ".   \\ |  | /");
            Log.e("Fabric", ".    \\    /");
            Log.e("Fabric", ".     \\  /");
            Log.e("Fabric", ".      \\/");
            Log.e("Fabric", ".");
            Log.e("Fabric", b);
            Log.e("Fabric", ".");
            Log.e("Fabric", ".      /\\");
            Log.e("Fabric", ".     /  \\");
            Log.e("Fabric", ".    /    \\");
            Log.e("Fabric", ".   / |  | \\");
            Log.e("Fabric", ".     |  |");
            Log.e("Fabric", ".     |  |");
            Log.e("Fabric", ".     |  |");
            Log.e("Fabric", ".");
            throw new CrashlyticsMissingDependencyException(b);
        } else if (!this.f5287b) {
            Fabric.m12905g().m12867a("Fabric", "Configured not to require a build ID.");
        }
    }

    protected String m7766b(String str, String str2) {
        return "This app relies on Crashlytics. Please sign up for access at https://fabric.io/sign_up,\ninstall an Android build tool and ask a team member to invite you to this app's organization.";
    }
}
