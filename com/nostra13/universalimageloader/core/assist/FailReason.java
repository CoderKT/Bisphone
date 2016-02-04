package com.nostra13.universalimageloader.core.assist;

public class FailReason {
    private final FailType f7016a;
    private final Throwable f7017b;

    public enum FailType {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN
    }

    public FailReason(FailType failType, Throwable th) {
        this.f7016a = failType;
        this.f7017b = th;
    }

    public FailType m11166a() {
        return this.f7016a;
    }
}
