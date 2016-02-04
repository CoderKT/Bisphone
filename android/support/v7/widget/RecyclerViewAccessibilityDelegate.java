package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat {
    final RecyclerView f1708b;
    final AccessibilityDelegateCompat f1709c;

    /* renamed from: android.support.v7.widget.RecyclerViewAccessibilityDelegate.1 */
    class C00961 extends AccessibilityDelegateCompat {
        final /* synthetic */ RecyclerViewAccessibilityDelegate f1707b;

        C00961(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
            this.f1707b = recyclerViewAccessibilityDelegate;
        }

        public void m3640a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.m864a(view, accessibilityNodeInfoCompat);
            if (!this.f1707b.m3643c() && this.f1707b.f1708b.getLayoutManager() != null) {
                this.f1707b.f1708b.getLayoutManager().m3100a(view, accessibilityNodeInfoCompat);
            }
        }

        public boolean m3641a(View view, int i, Bundle bundle) {
            if (super.m866a(view, i, bundle)) {
                return true;
            }
            if (this.f1707b.m3643c() || this.f1707b.f1708b.getLayoutManager() == null) {
                return false;
            }
            return this.f1707b.f1708b.getLayoutManager().m3112a(view, i, bundle);
        }
    }

    public RecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        this.f1709c = new C00961(this);
        this.f1708b = recyclerView;
    }

    private boolean m3643c() {
        return this.f1708b.m3639p();
    }

    public boolean m3645a(View view, int i, Bundle bundle) {
        if (super.m866a(view, i, bundle)) {
            return true;
        }
        if (m3643c() || this.f1708b.getLayoutManager() == null) {
            return false;
        }
        return this.f1708b.getLayoutManager().m3104a(i, bundle);
    }

    public void m3644a(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.m864a(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.m1531a(RecyclerView.class.getName());
        if (!m3643c() && this.f1708b.getLayoutManager() != null) {
            this.f1708b.getLayoutManager().m3080a(accessibilityNodeInfoCompat);
        }
    }

    public void m3647d(View view, AccessibilityEvent accessibilityEvent) {
        super.m870d(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if ((view instanceof RecyclerView) && !m3643c()) {
            RecyclerView recyclerView = (RecyclerView) view;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().m3102a(accessibilityEvent);
            }
        }
    }

    AccessibilityDelegateCompat m3646b() {
        return this.f1709c;
    }
}
