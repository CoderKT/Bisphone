package com.google.android.gms.common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;
import android.util.Log;

public class zzd {
    private static final zzd f5962a;

    static {
        f5962a = new zzd();
    }

    private zzd() {
    }

    public static zzd m8977a() {
        return f5962a;
    }

    private boolean m8978a(PackageInfo packageInfo, boolean z) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return false;
        }
        zza com_google_android_gms_common_zzc_zzb = new zzb(packageInfo.signatures[0].toByteArray());
        if ((z ? zzc.m8973a() : zzc.m8976b()).contains(com_google_android_gms_common_zzc_zzb)) {
            return true;
        }
        if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
            Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(com_google_android_gms_common_zzc_zzb.m8773a(), 0));
        }
        return false;
    }

    zza m8979a(PackageInfo packageInfo, zza... com_google_android_gms_common_zzc_zzaArr) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zza com_google_android_gms_common_zzc_zzb = new zzb(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < com_google_android_gms_common_zzc_zzaArr.length; i++) {
            if (com_google_android_gms_common_zzc_zzaArr[i].equals(com_google_android_gms_common_zzc_zzb)) {
                return com_google_android_gms_common_zzc_zzaArr[i];
            }
        }
        if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
            Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(com_google_android_gms_common_zzc_zzb.m8773a(), 0));
        }
        return null;
    }

    public boolean m8980a(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (GooglePlayServicesUtil.m8333b(packageManager)) {
            return m8978a(packageInfo, true);
        }
        boolean a = m8978a(packageInfo, false);
        if (a || !m8978a(packageInfo, true)) {
            return a;
        }
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        return a;
    }

    public boolean m8981a(PackageManager packageManager, String str) {
        try {
            return m8980a(packageManager, packageManager.getPackageInfo(str, 64));
        } catch (NameNotFoundException e) {
            if (Log.isLoggable("GoogleSignatureVerifier", 3)) {
                Log.d("GoogleSignatureVerifier", "Package manager can't find package " + str + ", defaulting to false");
            }
            return false;
        }
    }
}
