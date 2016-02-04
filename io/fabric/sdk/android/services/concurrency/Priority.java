package io.fabric.sdk.android.services.concurrency;

public enum Priority {
    LOW,
    NORMAL,
    HIGH,
    IMMEDIATE;

    static <Y> int m13121a(PriorityProvider priorityProvider, Y y) {
        Priority b;
        if (y instanceof PriorityProvider) {
            b = ((PriorityProvider) y).m7832b();
        } else {
            b = NORMAL;
        }
        return b.ordinal() - priorityProvider.m7832b().ordinal();
    }
}
