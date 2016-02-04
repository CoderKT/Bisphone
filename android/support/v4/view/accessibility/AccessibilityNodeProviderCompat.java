package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class AccessibilityNodeProviderCompat {
    private static final AccessibilityNodeProviderImpl f550a;
    private final Object f551b;

    interface AccessibilityNodeProviderImpl {
        Object m1579a(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat);
    }

    class AccessibilityNodeProviderStubImpl implements AccessibilityNodeProviderImpl {
        AccessibilityNodeProviderStubImpl() {
        }

        public Object m1586a(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            return null;
        }
    }

    class AccessibilityNodeProviderJellyBeanImpl extends AccessibilityNodeProviderStubImpl {

        /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeProviderCompat.AccessibilityNodeProviderJellyBeanImpl.1 */
        class C00291 implements AccessibilityNodeInfoBridge {
            final /* synthetic */ AccessibilityNodeProviderCompat f546a;
            final /* synthetic */ AccessibilityNodeProviderJellyBeanImpl f547b;

            C00291(AccessibilityNodeProviderJellyBeanImpl accessibilityNodeProviderJellyBeanImpl, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
                this.f547b = accessibilityNodeProviderJellyBeanImpl;
                this.f546a = accessibilityNodeProviderCompat;
            }

            public boolean m1585a(int i, int i2, Bundle bundle) {
                return this.f546a.m1600a(i, i2, bundle);
            }

            public List<Object> m1584a(String str, int i) {
                List a = this.f546a.m1599a(str, i);
                List<Object> arrayList = new ArrayList();
                int size = a.size();
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(((AccessibilityNodeInfoCompat) a.get(i2)).m1528a());
                }
                return arrayList;
            }

            public Object m1583a(int i) {
                AccessibilityNodeInfoCompat a = this.f546a.m1597a(i);
                if (a == null) {
                    return null;
                }
                return a.m1528a();
            }
        }

        AccessibilityNodeProviderJellyBeanImpl() {
        }

        public Object m1587a(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            return AccessibilityNodeProviderCompatJellyBean.m1602a(new C00291(this, accessibilityNodeProviderCompat));
        }
    }

    class AccessibilityNodeProviderKitKatImpl extends AccessibilityNodeProviderStubImpl {

        /* renamed from: android.support.v4.view.accessibility.AccessibilityNodeProviderCompat.AccessibilityNodeProviderKitKatImpl.1 */
        class C00301 implements AccessibilityNodeInfoBridge {
            final /* synthetic */ AccessibilityNodeProviderCompat f548a;
            final /* synthetic */ AccessibilityNodeProviderKitKatImpl f549b;

            C00301(AccessibilityNodeProviderKitKatImpl accessibilityNodeProviderKitKatImpl, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
                this.f549b = accessibilityNodeProviderKitKatImpl;
                this.f548a = accessibilityNodeProviderCompat;
            }

            public boolean m1594a(int i, int i2, Bundle bundle) {
                return this.f548a.m1600a(i, i2, bundle);
            }

            public List<Object> m1593a(String str, int i) {
                List a = this.f548a.m1599a(str, i);
                List<Object> arrayList = new ArrayList();
                int size = a.size();
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(((AccessibilityNodeInfoCompat) a.get(i2)).m1528a());
                }
                return arrayList;
            }

            public Object m1592a(int i) {
                AccessibilityNodeInfoCompat a = this.f548a.m1597a(i);
                if (a == null) {
                    return null;
                }
                return a.m1528a();
            }

            public Object m1595b(int i) {
                AccessibilityNodeInfoCompat b = this.f548a.m1601b(i);
                if (b == null) {
                    return null;
                }
                return b.m1528a();
            }
        }

        AccessibilityNodeProviderKitKatImpl() {
        }

        public Object m1596a(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
            return AccessibilityNodeProviderCompatKitKat.m1603a(new C00301(this, accessibilityNodeProviderCompat));
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            f550a = new AccessibilityNodeProviderKitKatImpl();
        } else if (VERSION.SDK_INT >= 16) {
            f550a = new AccessibilityNodeProviderJellyBeanImpl();
        } else {
            f550a = new AccessibilityNodeProviderStubImpl();
        }
    }

    public AccessibilityNodeProviderCompat() {
        this.f551b = f550a.m1579a(this);
    }

    public AccessibilityNodeProviderCompat(Object obj) {
        this.f551b = obj;
    }

    public Object m1598a() {
        return this.f551b;
    }

    public AccessibilityNodeInfoCompat m1597a(int i) {
        return null;
    }

    public boolean m1600a(int i, int i2, Bundle bundle) {
        return false;
    }

    public List<AccessibilityNodeInfoCompat> m1599a(String str, int i) {
        return null;
    }

    public AccessibilityNodeInfoCompat m1601b(int i) {
        return null;
    }
}
