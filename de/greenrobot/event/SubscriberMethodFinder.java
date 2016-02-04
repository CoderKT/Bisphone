package de.greenrobot.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SubscriberMethodFinder {
    private static final Map<String, List<SubscriberMethod>> f7990a;
    private final Map<Class<?>, Class<?>> f7991b;

    static {
        f7990a = new HashMap();
    }

    SubscriberMethodFinder(List<Class<?>> list) {
        this.f7991b = new ConcurrentHashMap();
        if (list != null) {
            for (Class cls : list) {
                this.f7991b.put(cls, cls);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    java.util.List<de.greenrobot.event.SubscriberMethod> m12805a(java.lang.Class<?> r15) {
        /*
        r14 = this;
        r3 = 0;
        r5 = r15.getName();
        r1 = f7990a;
        monitor-enter(r1);
        r0 = f7990a;	 Catch:{ all -> 0x0014 }
        r0 = r0.get(r5);	 Catch:{ all -> 0x0014 }
        r0 = (java.util.List) r0;	 Catch:{ all -> 0x0014 }
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        if (r0 == 0) goto L_0x0017;
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0014 }
        throw r0;
    L_0x0017:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r6 = new java.util.HashSet;
        r6.<init>();
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r4 = r15;
    L_0x0027:
        if (r4 == 0) goto L_0x0045;
    L_0x0029:
        r0 = r4.getName();
        r2 = "java.";
        r2 = r0.startsWith(r2);
        if (r2 != 0) goto L_0x0045;
    L_0x0035:
        r2 = "javax.";
        r2 = r0.startsWith(r2);
        if (r2 != 0) goto L_0x0045;
    L_0x003d:
        r2 = "android.";
        r0 = r0.startsWith(r2);
        if (r0 == 0) goto L_0x0070;
    L_0x0045:
        r0 = r1.isEmpty();
        if (r0 == 0) goto L_0x014b;
    L_0x004b:
        r0 = new de.greenrobot.event.EventBusException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Subscriber ";
        r1 = r1.append(r2);
        r1 = r1.append(r15);
        r2 = " has no public methods called ";
        r1 = r1.append(r2);
        r2 = "onEvent";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0070:
        r8 = r4.getDeclaredMethods();
        r9 = r8.length;
        r2 = r3;
    L_0x0076:
        if (r2 >= r9) goto L_0x0144;
    L_0x0078:
        r10 = r8[r2];
        r11 = r10.getName();
        r0 = "onEvent";
        r0 = r11.startsWith(r0);
        if (r0 == 0) goto L_0x00d3;
    L_0x0086:
        r0 = r10.getModifiers();
        r12 = r0 & 1;
        if (r12 == 0) goto L_0x0119;
    L_0x008e:
        r0 = r0 & 5192;
        if (r0 != 0) goto L_0x0119;
    L_0x0092:
        r12 = r10.getParameterTypes();
        r0 = r12.length;
        r13 = 1;
        if (r0 != r13) goto L_0x00d3;
    L_0x009a:
        r0 = "onEvent";
        r0 = r0.length();
        r0 = r11.substring(r0);
        r13 = r0.length();
        if (r13 != 0) goto L_0x00d7;
    L_0x00aa:
        r0 = de.greenrobot.event.ThreadMode.PostThread;
    L_0x00ac:
        r12 = r12[r3];
        r7.setLength(r3);
        r7.append(r11);
        r11 = 62;
        r11 = r7.append(r11);
        r13 = r12.getName();
        r11.append(r13);
        r11 = r7.toString();
        r11 = r6.add(r11);
        if (r11 == 0) goto L_0x00d3;
    L_0x00cb:
        r11 = new de.greenrobot.event.SubscriberMethod;
        r11.<init>(r10, r0, r12);
        r1.add(r11);
    L_0x00d3:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0076;
    L_0x00d7:
        r13 = "MainThread";
        r13 = r0.equals(r13);
        if (r13 == 0) goto L_0x00e2;
    L_0x00df:
        r0 = de.greenrobot.event.ThreadMode.MainThread;
        goto L_0x00ac;
    L_0x00e2:
        r13 = "BackgroundThread";
        r13 = r0.equals(r13);
        if (r13 == 0) goto L_0x00ed;
    L_0x00ea:
        r0 = de.greenrobot.event.ThreadMode.BackgroundThread;
        goto L_0x00ac;
    L_0x00ed:
        r13 = "Async";
        r0 = r0.equals(r13);
        if (r0 == 0) goto L_0x00f8;
    L_0x00f5:
        r0 = de.greenrobot.event.ThreadMode.Async;
        goto L_0x00ac;
    L_0x00f8:
        r0 = r14.f7991b;
        r0 = r0.containsKey(r4);
        if (r0 != 0) goto L_0x00d3;
    L_0x0100:
        r0 = new de.greenrobot.event.EventBusException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Illegal onEvent method, check for typos: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0119:
        r0 = r14.f7991b;
        r0 = r0.containsKey(r4);
        if (r0 != 0) goto L_0x00d3;
    L_0x0121:
        r0 = de.greenrobot.event.EventBus.f7942a;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r12 = "Skipping method (not public, static or abstract): ";
        r10 = r10.append(r12);
        r10 = r10.append(r4);
        r12 = ".";
        r10 = r10.append(r12);
        r10 = r10.append(r11);
        r10 = r10.toString();
        android.util.Log.d(r0, r10);
        goto L_0x00d3;
    L_0x0144:
        r0 = r4.getSuperclass();
        r4 = r0;
        goto L_0x0027;
    L_0x014b:
        r2 = f7990a;
        monitor-enter(r2);
        r0 = f7990a;	 Catch:{ all -> 0x0157 }
        r0.put(r5, r1);	 Catch:{ all -> 0x0157 }
        monitor-exit(r2);	 Catch:{ all -> 0x0157 }
        r0 = r1;
        goto L_0x0013;
    L_0x0157:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0157 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.greenrobot.event.SubscriberMethodFinder.a(java.lang.Class):java.util.List<de.greenrobot.event.SubscriberMethod>");
    }
}
