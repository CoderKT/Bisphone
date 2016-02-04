package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;

class CloseableHttpResponseProxy implements InvocationHandler {
    private static final Constructor<?> f7575a;
    private final HttpResponse f7576b;

    static {
        try {
            f7575a = Proxy.getProxyClass(CloseableHttpResponseProxy.class.getClassLoader(), new Class[]{CloseableHttpResponse.class}).getConstructor(new Class[]{InvocationHandler.class});
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    CloseableHttpResponseProxy(HttpResponse httpResponse) {
        this.f7576b = httpResponse;
    }

    public void m12098a() {
        EntityUtils.m12764a(this.f7576b.m11393b());
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (method.getName().equals(Close.ELEMENT)) {
            m12098a();
            return null;
        }
        try {
            return method.invoke(this.f7576b, objArr);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                throw cause;
            }
            throw e;
        }
    }

    public static CloseableHttpResponse m12097a(HttpResponse httpResponse) {
        try {
            return (CloseableHttpResponse) f7575a.newInstance(new Object[]{new CloseableHttpResponseProxy(httpResponse)});
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        } catch (Throwable e2) {
            throw new IllegalStateException(e2);
        } catch (Throwable e22) {
            throw new IllegalStateException(e22);
        }
    }
}
