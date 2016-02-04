package io.fabric.sdk.android;

public interface InitializationCallback<T> {
    public static final InitializationCallback f8141d;

    public class Empty implements InitializationCallback<Object> {
        private Empty() {
        }

        public void m12918a(Object obj) {
        }

        public void m12917a(Exception exception) {
        }
    }

    void m12889a(Exception exception);

    void m12890a(T t);

    static {
        f8141d = new Empty();
    }
}
