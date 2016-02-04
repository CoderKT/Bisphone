package cz.msebera.android.httpclient.client.utils;

import java.lang.reflect.InvocationTargetException;

public class CloneUtils {
    public static <T> T m11565a(T t) {
        T t2 = null;
        if (t != null) {
            if (t instanceof Cloneable) {
                try {
                    try {
                        t2 = t.getClass().getMethod("clone", (Class[]) null).invoke(t, (Object[]) null);
                    } catch (InvocationTargetException e) {
                        Throwable cause = e.getCause();
                        if (cause instanceof CloneNotSupportedException) {
                            throw ((CloneNotSupportedException) cause);
                        }
                        throw new Error("Unexpected exception", cause);
                    } catch (IllegalAccessException e2) {
                        throw new IllegalAccessError(e2.getMessage());
                    }
                } catch (NoSuchMethodException e3) {
                    throw new NoSuchMethodError(e3.getMessage());
                }
            }
            throw new CloneNotSupportedException();
        }
        return t2;
    }
}
