package org.jivesoftware.smack.sm;

import java.math.BigInteger;

public class SMUtils {
    private static long MASK_32_BIT;

    static {
        MASK_32_BIT = BigInteger.ONE.shiftLeft(32).subtract(BigInteger.ONE).longValue();
    }

    public static long incrementHeight(long j) {
        return (1 + j) & MASK_32_BIT;
    }

    public static long calculateDelta(long j, long j2) {
        return (j - j2) & MASK_32_BIT;
    }
}
