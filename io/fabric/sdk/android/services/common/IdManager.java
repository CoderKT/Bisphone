package io.fabric.sdk.android.services.common;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import app.C0110R;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import org.json.JSONObject;

public class IdManager {
    private static final Pattern f8250a;
    private static final String f8251b;
    private final ReentrantLock f8252c;
    private final InstallerPackageNameProvider f8253d;
    private final boolean f8254e;
    private final boolean f8255f;
    private final Context f8256g;
    private final String f8257h;
    private final String f8258i;
    private final Collection<Kit> f8259j;

    public enum DeviceIdentifierType {
        WIFI_MAC_ADDRESS(1),
        BLUETOOTH_MAC_ADDRESS(2),
        FONT_TOKEN(53),
        ANDROID_ID(100),
        ANDROID_DEVICE_ID(C0110R.styleable.Theme_buttonStyleSmall),
        ANDROID_SERIAL(C0110R.styleable.Theme_checkboxStyle),
        ANDROID_ADVERTISING_ID(C0110R.styleable.Theme_checkedTextViewStyle);
        
        public final int f8249h;

        private DeviceIdentifierType(int i) {
            this.f8249h = i;
        }
    }

    static {
        f8250a = Pattern.compile("[^\\p{Alnum}]");
        f8251b = Pattern.quote("/");
    }

    public IdManager(Context context, String str, String str2, Collection<Kit> collection) {
        this.f8252c = new ReentrantLock();
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        } else if (collection == null) {
            throw new IllegalArgumentException("kits must not be null");
        } else {
            this.f8256g = context;
            this.f8257h = str;
            this.f8258i = str2;
            this.f8259j = collection;
            this.f8253d = new InstallerPackageNameProvider();
            this.f8254e = CommonUtils.m13019a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.f8254e) {
                Fabric.m12905g().m12867a("Fabric", "Device ID collection disabled for " + context.getPackageName());
            }
            this.f8255f = CommonUtils.m13019a(context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.f8255f) {
                Fabric.m12905g().m12867a("Fabric", "User information collection disabled for " + context.getPackageName());
            }
        }
    }

    public String m13060a(String str, String str2) {
        try {
            Cipher a = CommonUtils.m13012a(1, CommonUtils.m13007a(str + str2.replaceAll("\\.", new StringBuilder(new String(new char[]{'s', 'l', 'c'})).reverse().toString())));
            JSONObject jSONObject = new JSONObject();
            m13053a(jSONObject);
            m13056b(jSONObject);
            m13058c(jSONObject);
            m13059d(jSONObject);
            String str3 = "";
            if (jSONObject.length() <= 0) {
                return str3;
            }
            try {
                return CommonUtils.m13009a(a.doFinal(jSONObject.toString().getBytes()));
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Could not encrypt IDs", e);
                return str3;
            }
        } catch (Throwable e2) {
            Fabric.m12905g().m12874d("Fabric", "Could not create cipher to encrypt headers.", e2);
            return "";
        }
    }

    private void m13053a(JSONObject jSONObject) {
        try {
            jSONObject.put("APPLICATION_INSTALLATION_UUID".toLowerCase(Locale.US), m13062b());
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Could not write application id to JSON", e);
        }
    }

    private void m13056b(JSONObject jSONObject) {
        for (Entry entry : m13067g().entrySet()) {
            try {
                jSONObject.put(((DeviceIdentifierType) entry.getKey()).name().toLowerCase(Locale.US), entry.getValue());
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Could not write value to JSON: " + ((DeviceIdentifierType) entry.getKey()).name(), e);
            }
        }
    }

    private void m13058c(JSONObject jSONObject) {
        try {
            jSONObject.put("os_version", m13064d());
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Could not write OS version to JSON", e);
        }
    }

    private void m13059d(JSONObject jSONObject) {
        try {
            jSONObject.put("model", m13065e());
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Could not write model to JSON", e);
        }
    }

    public boolean m13061a() {
        return this.f8255f;
    }

    private boolean m13054a(String str) {
        return this.f8256g.checkCallingPermission(str) == 0;
    }

    private String m13055b(String str) {
        return str == null ? null : f8250a.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public String m13062b() {
        String str = this.f8258i;
        if (str != null) {
            return str;
        }
        SharedPreferences a = CommonUtils.m13002a(this.f8256g);
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return m13051a(a);
        }
        return str;
    }

    public String m13063c() {
        return this.f8257h;
    }

    public String m13064d() {
        return String.format(Locale.US, "%s/%s", new Object[]{m13057c(VERSION.RELEASE), m13057c(VERSION.INCREMENTAL)});
    }

    public String m13065e() {
        return String.format(Locale.US, "%s/%s", new Object[]{m13057c(Build.MANUFACTURER), m13057c(Build.MODEL)});
    }

    private String m13057c(String str) {
        return str.replaceAll(f8251b, "");
    }

    public String m13066f() {
        String str = "";
        if (!this.f8254e) {
            return str;
        }
        str = m13070j();
        if (str != null) {
            return str;
        }
        SharedPreferences a = CommonUtils.m13002a(this.f8256g);
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return m13051a(a);
        }
        return str;
    }

    private String m13051a(SharedPreferences sharedPreferences) {
        this.f8252c.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = m13055b(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            this.f8252c.unlock();
            return string;
        } catch (Throwable th) {
            this.f8252c.unlock();
        }
    }

    public Map<DeviceIdentifierType, String> m13067g() {
        Map hashMap = new HashMap();
        for (Kit kit : this.f8259j) {
            if (kit instanceof DeviceIdentifierProvider) {
                for (Entry entry : ((DeviceIdentifierProvider) kit).m8195e().entrySet()) {
                    m13052a(hashMap, (DeviceIdentifierType) entry.getKey(), (String) entry.getValue());
                }
            }
        }
        m13052a(hashMap, DeviceIdentifierType.ANDROID_ID, m13070j());
        m13052a(hashMap, DeviceIdentifierType.ANDROID_DEVICE_ID, m13071k());
        m13052a(hashMap, DeviceIdentifierType.ANDROID_SERIAL, m13074n());
        m13052a(hashMap, DeviceIdentifierType.WIFI_MAC_ADDRESS, m13072l());
        m13052a(hashMap, DeviceIdentifierType.BLUETOOTH_MAC_ADDRESS, m13073m());
        m13052a(hashMap, DeviceIdentifierType.ANDROID_ADVERTISING_ID, m13069i());
        return Collections.unmodifiableMap(hashMap);
    }

    public String m13068h() {
        return this.f8253d.m13077a(this.f8256g);
    }

    public String m13069i() {
        if (!this.f8254e) {
            return null;
        }
        AdvertisingInfo a = new AdvertisingInfoProvider(this.f8256g).m12976a();
        if (a != null) {
            return a.f8198a;
        }
        return null;
    }

    private void m13052a(Map<DeviceIdentifierType, String> map, DeviceIdentifierType deviceIdentifierType, String str) {
        if (str != null) {
            map.put(deviceIdentifierType, str);
        }
    }

    public String m13070j() {
        if (!this.f8254e) {
            return null;
        }
        String string = Secure.getString(this.f8256g.getContentResolver(), "android_id");
        if ("9774d56d682e549c".equals(string)) {
            return null;
        }
        return m13055b(string);
    }

    public String m13071k() {
        if (this.f8254e && m13054a("android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = (TelephonyManager) this.f8256g.getSystemService("phone");
            if (telephonyManager != null) {
                return m13055b(telephonyManager.getDeviceId());
            }
        }
        return null;
    }

    public String m13072l() {
        if (this.f8254e && m13054a("android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) this.f8256g.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    return m13055b(connectionInfo.getMacAddress());
                }
            }
        }
        return null;
    }

    public String m13073m() {
        if (this.f8254e && m13054a("android.permission.BLUETOOTH")) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    m13055b(defaultAdapter.getAddress());
                }
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Utils#getBluetoothMacAddress failed, returning null. Requires prior call to BluetoothAdatpter.getDefaultAdapter() on thread that has called Looper.prepare()", e);
            }
        }
        return null;
    }

    public String m13074n() {
        if (this.f8254e && VERSION.SDK_INT >= 9) {
            try {
                return m13055b((String) Build.class.getField("SERIAL").get(null));
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Could not retrieve android.os.Build.SERIAL value", e);
            }
        }
        return null;
    }
}
