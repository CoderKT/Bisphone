package io.fabric.sdk.android.services.common;

class AdvertisingInfo {
    public final String f8198a;
    public final boolean f8199b;

    AdvertisingInfo(String str, boolean z) {
        this.f8198a = str;
        this.f8199b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvertisingInfo advertisingInfo = (AdvertisingInfo) obj;
        if (this.f8199b != advertisingInfo.f8199b) {
            return false;
        }
        if (this.f8198a != null) {
            if (this.f8198a.equals(advertisingInfo.f8198a)) {
                return true;
            }
        } else if (advertisingInfo.f8198a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f8198a != null) {
            hashCode = this.f8198a.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.f8199b) {
            i = 1;
        }
        return hashCode + i;
    }
}
