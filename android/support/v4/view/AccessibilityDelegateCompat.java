package android.support.v4.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge;
import android.support.v4.view.AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityDelegateCompat {
    private static final AccessibilityDelegateImpl f422b;
    private static final Object f423c;
    final Object f424a;

    interface AccessibilityDelegateImpl {
        AccessibilityNodeProviderCompat m809a(Object obj, View view);

        Object m810a();

        Object m811a(AccessibilityDelegateCompat accessibilityDelegateCompat);

        void m812a(Object obj, View view, int i);

        void m813a(Object obj, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

        boolean m814a(Object obj, View view, int i, Bundle bundle);

        boolean m815a(Object obj, View view, AccessibilityEvent accessibilityEvent);

        boolean m816a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void m817b(Object obj, View view, AccessibilityEvent accessibilityEvent);

        void m818c(Object obj, View view, AccessibilityEvent accessibilityEvent);

        void m819d(Object obj, View view, AccessibilityEvent accessibilityEvent);
    }

    class AccessibilityDelegateStubImpl implements AccessibilityDelegateImpl {
        AccessibilityDelegateStubImpl() {
        }

        public Object m821a() {
            return null;
        }

        public Object m822a(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return null;
        }

        public boolean m826a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            return false;
        }

        public void m828b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        public void m824a(Object obj, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }

        public void m829c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        public boolean m827a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return true;
        }

        public void m823a(Object obj, View view, int i) {
        }

        public void m830d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        public AccessibilityNodeProviderCompat m820a(Object obj, View view) {
            return null;
        }

        public boolean m825a(Object obj, View view, int i, Bundle bundle) {
            return false;
        }
    }

    class AccessibilityDelegateIcsImpl extends AccessibilityDelegateStubImpl {

        /* renamed from: android.support.v4.view.AccessibilityDelegateCompat.AccessibilityDelegateIcsImpl.1 */
        class C00161 implements AccessibilityDelegateBridge {
            final /* synthetic */ AccessibilityDelegateCompat f418a;
            final /* synthetic */ AccessibilityDelegateIcsImpl f419b;

            C00161(AccessibilityDelegateIcsImpl accessibilityDelegateIcsImpl, AccessibilityDelegateCompat accessibilityDelegateCompat) {
                this.f419b = accessibilityDelegateIcsImpl;
                this.f418a = accessibilityDelegateCompat;
            }

            public boolean m804a(View view, AccessibilityEvent accessibilityEvent) {
                return this.f418a.m868b(view, accessibilityEvent);
            }

            public void m806b(View view, AccessibilityEvent accessibilityEvent) {
                this.f418a.m870d(view, accessibilityEvent);
            }

            public void m803a(View view, Object obj) {
                this.f418a.m864a(view, new AccessibilityNodeInfoCompat(obj));
            }

            public void m807c(View view, AccessibilityEvent accessibilityEvent) {
                this.f418a.m869c(view, accessibilityEvent);
            }

            public boolean m805a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return this.f418a.m867a(viewGroup, view, accessibilityEvent);
            }

            public void m802a(View view, int i) {
                this.f418a.m863a(view, i);
            }

            public void m808d(View view, AccessibilityEvent accessibilityEvent) {
                this.f418a.m865a(view, accessibilityEvent);
            }
        }

        AccessibilityDelegateIcsImpl() {
        }

        public Object m831a() {
            return AccessibilityDelegateCompatIcs.m871a();
        }

        public Object m832a(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return AccessibilityDelegateCompatIcs.m872a(new C00161(this, accessibilityDelegateCompat));
        }

        public boolean m835a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            return AccessibilityDelegateCompatIcs.m875a(obj, view, accessibilityEvent);
        }

        public void m837b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.m877b(obj, view, accessibilityEvent);
        }

        public void m834a(Object obj, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityDelegateCompatIcs.m874a(obj, view, accessibilityNodeInfoCompat.m1528a());
        }

        public void m838c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.m878c(obj, view, accessibilityEvent);
        }

        public boolean m836a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return AccessibilityDelegateCompatIcs.m876a(obj, viewGroup, view, accessibilityEvent);
        }

        public void m833a(Object obj, View view, int i) {
            AccessibilityDelegateCompatIcs.m873a(obj, view, i);
        }

        public void m839d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.m879d(obj, view, accessibilityEvent);
        }
    }

    class AccessibilityDelegateJellyBeanImpl extends AccessibilityDelegateIcsImpl {

        /* renamed from: android.support.v4.view.AccessibilityDelegateCompat.AccessibilityDelegateJellyBeanImpl.1 */
        class C00171 implements AccessibilityDelegateBridgeJellyBean {
            final /* synthetic */ AccessibilityDelegateCompat f420a;
            final /* synthetic */ AccessibilityDelegateJellyBeanImpl f421b;

            C00171(AccessibilityDelegateJellyBeanImpl accessibilityDelegateJellyBeanImpl, AccessibilityDelegateCompat accessibilityDelegateCompat) {
                this.f421b = accessibilityDelegateJellyBeanImpl;
                this.f420a = accessibilityDelegateCompat;
            }

            public boolean m853a(View view, AccessibilityEvent accessibilityEvent) {
                return this.f420a.m868b(view, accessibilityEvent);
            }

            public void m855b(View view, AccessibilityEvent accessibilityEvent) {
                this.f420a.m870d(view, accessibilityEvent);
            }

            public void m851a(View view, Object obj) {
                this.f420a.m864a(view, new AccessibilityNodeInfoCompat(obj));
            }

            public void m856c(View view, AccessibilityEvent accessibilityEvent) {
                this.f420a.m869c(view, accessibilityEvent);
            }

            public boolean m854a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return this.f420a.m867a(viewGroup, view, accessibilityEvent);
            }

            public void m850a(View view, int i) {
                this.f420a.m863a(view, i);
            }

            public void m857d(View view, AccessibilityEvent accessibilityEvent) {
                this.f420a.m865a(view, accessibilityEvent);
            }

            public Object m849a(View view) {
                AccessibilityNodeProviderCompat a = this.f420a.m861a(view);
                return a != null ? a.m1598a() : null;
            }

            public boolean m852a(View view, int i, Bundle bundle) {
                return this.f420a.m866a(view, i, bundle);
            }
        }

        AccessibilityDelegateJellyBeanImpl() {
        }

        public Object m859a(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return AccessibilityDelegateCompatJellyBean.m880a(new C00171(this, accessibilityDelegateCompat));
        }

        public AccessibilityNodeProviderCompat m858a(Object obj, View view) {
            Object a = AccessibilityDelegateCompatJellyBean.m881a(obj, view);
            if (a != null) {
                return new AccessibilityNodeProviderCompat(a);
            }
            return null;
        }

        public boolean m860a(Object obj, View view, int i, Bundle bundle) {
            return AccessibilityDelegateCompatJellyBean.m882a(obj, view, i, bundle);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f422b = new AccessibilityDelegateJellyBeanImpl();
        } else if (VERSION.SDK_INT >= 14) {
            f422b = new AccessibilityDelegateIcsImpl();
        } else {
            f422b = new AccessibilityDelegateStubImpl();
        }
        f423c = f422b.m810a();
    }

    public AccessibilityDelegateCompat() {
        this.f424a = f422b.m811a(this);
    }

    Object m862a() {
        return this.f424a;
    }

    public void m863a(View view, int i) {
        f422b.m812a(f423c, view, i);
    }

    public void m865a(View view, AccessibilityEvent accessibilityEvent) {
        f422b.m819d(f423c, view, accessibilityEvent);
    }

    public boolean m868b(View view, AccessibilityEvent accessibilityEvent) {
        return f422b.m815a(f423c, view, accessibilityEvent);
    }

    public void m869c(View view, AccessibilityEvent accessibilityEvent) {
        f422b.m818c(f423c, view, accessibilityEvent);
    }

    public void m870d(View view, AccessibilityEvent accessibilityEvent) {
        f422b.m817b(f423c, view, accessibilityEvent);
    }

    public void m864a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        f422b.m813a(f423c, view, accessibilityNodeInfoCompat);
    }

    public boolean m867a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return f422b.m816a(f423c, viewGroup, view, accessibilityEvent);
    }

    public AccessibilityNodeProviderCompat m861a(View view) {
        return f422b.m809a(f423c, view);
    }

    public boolean m866a(View view, int i, Bundle bundle) {
        return f422b.m814a(f423c, view, i, bundle);
    }
}
