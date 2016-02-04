package io.fabric.sdk.android.services.concurrency;

public class UnmetDependencyException extends RuntimeException {
    public UnmetDependencyException(String str) {
        super(str);
    }

    public UnmetDependencyException(Throwable th) {
        super(th);
    }
}
