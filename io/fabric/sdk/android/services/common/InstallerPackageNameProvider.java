package io.fabric.sdk.android.services.common;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.cache.MemoryValueCache;
import io.fabric.sdk.android.services.cache.ValueLoader;

public class InstallerPackageNameProvider {
    private final ValueLoader<String> f8261a;
    private final MemoryValueCache<String> f8262b;

    /* renamed from: io.fabric.sdk.android.services.common.InstallerPackageNameProvider.1 */
    class C09591 implements ValueLoader<String> {
        final /* synthetic */ InstallerPackageNameProvider f8260a;

        C09591(InstallerPackageNameProvider installerPackageNameProvider) {
            this.f8260a = installerPackageNameProvider;
        }

        public /* synthetic */ Object m13076c(Context context) {
            return m13075a(context);
        }

        public String m13075a(Context context) {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? "" : installerPackageName;
        }
    }

    public InstallerPackageNameProvider() {
        this.f8261a = new C09591(this);
        this.f8262b = new MemoryValueCache();
    }

    public String m13077a(Context context) {
        try {
            String str = (String) this.f8262b.m12965a(context, this.f8261a);
            if ("".equals(str)) {
                return null;
            }
            return str;
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Failed to determine installer package name", e);
            return null;
        }
    }
}
