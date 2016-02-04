package com.crashlytics.android;

public class CrashlyticsMissingDependencyException extends RuntimeException {
    CrashlyticsMissingDependencyException(String str) {
        super(m7925a(str));
    }

    private static String m7925a(String str) {
        return "\n" + str + "\n";
    }
}
