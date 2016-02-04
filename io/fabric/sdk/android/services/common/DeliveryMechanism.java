package io.fabric.sdk.android.services.common;

public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);
    
    private final int f8232e;

    private DeliveryMechanism(int i) {
        this.f8232e = i;
    }

    public int m13043a() {
        return this.f8232e;
    }

    public String toString() {
        return Integer.toString(this.f8232e);
    }

    public static DeliveryMechanism m13042a(String str) {
        if ("io.crash.air".equals(str)) {
            return TEST_DISTRIBUTION;
        }
        if (str != null) {
            return APP_STORE;
        }
        return DEVELOPER;
    }
}
