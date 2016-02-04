package io.fabric.sdk.android.services.common;

import android.content.Context;
import io.fabric.sdk.android.Fabric;

class AdvertisingInfoReflectionStrategy implements AdvertisingInfoStrategy {
    private final Context f8204a;

    public AdvertisingInfoReflectionStrategy(Context context) {
        this.f8204a = context.getApplicationContext();
    }

    boolean m12985a(Context context) {
        try {
            if (((Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public AdvertisingInfo m12984a() {
        if (m12985a(this.f8204a)) {
            return new AdvertisingInfo(m12981b(), m12982c());
        }
        return null;
    }

    private String m12981b() {
        try {
            return (String) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId", new Class[0]).invoke(m12983d(), new Object[0]);
        } catch (Exception e) {
            Fabric.m12905g().m12871c("Fabric", "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    private boolean m12982c() {
        try {
            return ((Boolean) Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(m12983d(), new Object[0])).booleanValue();
        } catch (Exception e) {
            Fabric.m12905g().m12871c("Fabric", "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return false;
        }
    }

    private Object m12983d() {
        Object obj = null;
        try {
            obj = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.f8204a});
        } catch (Exception e) {
            Fabric.m12905g().m12871c("Fabric", "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
        }
        return obj;
    }
}
