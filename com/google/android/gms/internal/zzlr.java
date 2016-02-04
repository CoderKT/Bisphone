package com.google.android.gms.internal;

public abstract class zzlr<T> {
    private static final Object f6112c;
    private static zza f6113d;
    private static int f6114e;
    private static String f6115f;
    protected final String f6116a;
    protected final T f6117b;
    private T f6118g;

    /* renamed from: com.google.android.gms.internal.zzlr.2 */
    final class C08652 extends zzlr<Long> {
        C08652(String str, Long l) {
            super(str, l);
        }

        protected /* synthetic */ Object m9245a(String str) {
            return m9246b(str);
        }

        protected Long m9246b(String str) {
            return zzlr.f6113d.m9252a(this.a, (Long) this.b);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzlr.3 */
    final class C08663 extends zzlr<Integer> {
        C08663(String str, Integer num) {
            super(str, num);
        }

        protected /* synthetic */ Object m9247a(String str) {
            return m9248b(str);
        }

        protected Integer m9248b(String str) {
            return zzlr.f6113d.m9251a(this.a, (Integer) this.b);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzlr.5 */
    final class C08675 extends zzlr<String> {
        C08675(String str, String str2) {
            super(str, str2);
        }

        protected /* synthetic */ Object m9249a(String str) {
            return m9250b(str);
        }

        protected String m9250b(String str) {
            return zzlr.f6113d.m9253a(this.a, (String) this.b);
        }
    }

    interface zza {
        Integer m9251a(String str, Integer num);

        Long m9252a(String str, Long l);

        String m9253a(String str, String str2);
    }

    static {
        f6112c = new Object();
        f6113d = null;
        f6114e = 0;
        f6115f = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    }

    protected zzlr(String str, T t) {
        this.f6118g = null;
        this.f6116a = str;
        this.f6117b = t;
    }

    public static int m9237a() {
        return f6114e;
    }

    public static zzlr<Integer> m9238a(String str, Integer num) {
        return new C08663(str, num);
    }

    public static zzlr<Long> m9239a(String str, Long l) {
        return new C08652(str, l);
    }

    public static zzlr<String> m9240a(String str, String str2) {
        return new C08675(str, str2);
    }

    public static boolean m9241b() {
        return f6113d != null;
    }

    protected abstract T m9243a(String str);

    public final T m9244c() {
        return this.f6118g != null ? this.f6118g : m9243a(this.f6116a);
    }
}
