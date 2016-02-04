package io.fabric.sdk.android.services.persistence;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import io.fabric.sdk.android.Kit;

public class PreferenceStoreImpl implements PreferenceStore {
    private final SharedPreferences f8371a;
    private final String f8372b;
    private final Context f8373c;

    public PreferenceStoreImpl(Context context, String str) {
        if (context == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.f8373c = context;
        this.f8372b = str;
        this.f8371a = this.f8373c.getSharedPreferences(this.f8372b, 0);
    }

    public PreferenceStoreImpl(Kit kit) {
        this(kit.m7860C(), kit.getClass().getName());
    }

    public SharedPreferences m13250a() {
        return this.f8371a;
    }

    public Editor m13252b() {
        return this.f8371a.edit();
    }

    @TargetApi(9)
    public boolean m13251a(Editor editor) {
        if (VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}
