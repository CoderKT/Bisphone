package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzqx;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzf {
    private final Account f5746a;
    private final Set<Scope> f5747b;
    private final Set<Scope> f5748c;
    private final Map<Api<?>, zza> f5749d;
    private final int f5750e;
    private final View f5751f;
    private final String f5752g;
    private final String f5753h;
    private final zzqx f5754i;
    private Integer f5755j;

    public final class zza {
        public final Set<Scope> f5744a;
        public final boolean f5745b;
    }

    public zzf(Account account, Set<Scope> set, Map<Api<?>, zza> map, int i, View view, String str, String str2, zzqx com_google_android_gms_internal_zzqx) {
        Map map2;
        this.f5746a = account;
        this.f5747b = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        if (map == null) {
            map2 = Collections.EMPTY_MAP;
        }
        this.f5749d = map2;
        this.f5751f = view;
        this.f5750e = i;
        this.f5752g = str;
        this.f5753h = str2;
        this.f5754i = com_google_android_gms_internal_zzqx;
        Set hashSet = new HashSet(this.f5747b);
        for (zza com_google_android_gms_common_internal_zzf_zza : this.f5749d.values()) {
            hashSet.addAll(com_google_android_gms_common_internal_zzf_zza.f5744a);
        }
        this.f5748c = Collections.unmodifiableSet(hashSet);
    }

    public Account m8536a() {
        return this.f5746a;
    }

    public void m8537a(Integer num) {
        this.f5755j = num;
    }

    public Account m8538b() {
        return this.f5746a != null ? this.f5746a : new Account("<<default account>>", "com.google");
    }

    public Set<Scope> m8539c() {
        return this.f5747b;
    }

    public Set<Scope> m8540d() {
        return this.f5748c;
    }

    public Map<Api<?>, zza> m8541e() {
        return this.f5749d;
    }

    public String m8542f() {
        return this.f5752g;
    }

    public String m8543g() {
        return this.f5753h;
    }

    public zzqx m8544h() {
        return this.f5754i;
    }

    public Integer m8545i() {
        return this.f5755j;
    }
}
