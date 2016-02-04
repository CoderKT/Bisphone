package android.support.v7.view.menu;

class BaseWrapper<T> {
    final T f1010b;

    BaseWrapper(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f1010b = t;
    }
}
