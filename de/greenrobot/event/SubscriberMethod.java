package de.greenrobot.event;

import java.lang.reflect.Method;

final class SubscriberMethod {
    final Method f7986a;
    final ThreadMode f7987b;
    final Class<?> f7988c;
    String f7989d;

    SubscriberMethod(Method method, ThreadMode threadMode, Class<?> cls) {
        this.f7986a = method;
        this.f7987b = threadMode;
        this.f7988c = cls;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SubscriberMethod)) {
            return false;
        }
        m12804a();
        SubscriberMethod subscriberMethod = (SubscriberMethod) obj;
        subscriberMethod.m12804a();
        return this.f7989d.equals(subscriberMethod.f7989d);
    }

    private synchronized void m12804a() {
        if (this.f7989d == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(this.f7986a.getDeclaringClass().getName());
            stringBuilder.append('#').append(this.f7986a.getName());
            stringBuilder.append('(').append(this.f7988c.getName());
            this.f7989d = stringBuilder.toString();
        }
    }

    public int hashCode() {
        return this.f7986a.hashCode();
    }
}
