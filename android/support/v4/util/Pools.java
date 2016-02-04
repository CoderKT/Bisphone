package android.support.v4.util;

public final class Pools {

    public interface Pool<T> {
        T m784a();

        boolean m785a(T t);
    }

    public class SimplePool<T> implements Pool<T> {
        private final Object[] f411a;
        private int f412b;

        public SimplePool(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.f411a = new Object[i];
        }

        public T m787a() {
            if (this.f412b <= 0) {
                return null;
            }
            int i = this.f412b - 1;
            T t = this.f411a[i];
            this.f411a[i] = null;
            this.f412b--;
            return t;
        }

        public boolean m788a(T t) {
            if (m786b(t)) {
                throw new IllegalStateException("Already in the pool!");
            } else if (this.f412b >= this.f411a.length) {
                return false;
            } else {
                this.f411a[this.f412b] = t;
                this.f412b++;
                return true;
            }
        }

        private boolean m786b(T t) {
            for (int i = 0; i < this.f412b; i++) {
                if (this.f411a[i] == t) {
                    return true;
                }
            }
            return false;
        }
    }
}
