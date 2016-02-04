package com.crashlytics.android.answers;

final class SessionEventMetadata {
    public final String f5502a;
    public final String f5503b;
    public final String f5504c;
    public final String f5505d;
    public final String f5506e;
    public final String f5507f;
    public final String f5508g;
    public final String f5509h;
    public final String f5510i;
    public final String f5511j;
    public final String f5512k;
    private String f5513l;

    public SessionEventMetadata(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.f5502a = str;
        this.f5503b = str2;
        this.f5504c = str3;
        this.f5505d = str4;
        this.f5506e = str5;
        this.f5507f = str6;
        this.f5508g = str7;
        this.f5509h = str8;
        this.f5510i = str9;
        this.f5511j = str10;
        this.f5512k = str11;
    }

    public String toString() {
        if (this.f5513l == null) {
            this.f5513l = "appBundleId=" + this.f5502a + ", executionId=" + this.f5503b + ", installationId=" + this.f5504c + ", androidId=" + this.f5505d + ", advertisingId=" + this.f5506e + ", betaDeviceToken=" + this.f5507f + ", buildId=" + this.f5508g + ", osVersion=" + this.f5509h + ", deviceModel=" + this.f5510i + ", appVersionCode=" + this.f5511j + ", appVersionName=" + this.f5512k;
        }
        return this.f5513l;
    }
}
