package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ItemAnimator.ItemHolderInfo;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public abstract class SimpleItemAnimator extends ItemAnimator {
    boolean f1492a;

    public abstract boolean m2961a(ViewHolder viewHolder);

    public abstract boolean m2962a(ViewHolder viewHolder, int i, int i2, int i3, int i4);

    public abstract boolean m2964a(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4);

    public abstract boolean m2967b(ViewHolder viewHolder);

    public SimpleItemAnimator() {
        this.f1492a = true;
    }

    public boolean m2972g(ViewHolder viewHolder) {
        return !this.f1492a || viewHolder.m3507n();
    }

    public boolean m2963a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
        int i = itemHolderInfo.f1595a;
        int i2 = itemHolderInfo.f1596b;
        View view = viewHolder.f1642a;
        int left = itemHolderInfo2 == null ? view.getLeft() : itemHolderInfo2.f1595a;
        int top = itemHolderInfo2 == null ? view.getTop() : itemHolderInfo2.f1596b;
        if (viewHolder.m3510q() || (i == left && i2 == top)) {
            return m2961a(viewHolder);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return m2962a(viewHolder, i, i2, left, top);
    }

    public boolean m2968b(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
        if (itemHolderInfo == null || (itemHolderInfo.f1595a == itemHolderInfo2.f1595a && itemHolderInfo.f1596b == itemHolderInfo2.f1596b)) {
            return m2967b(viewHolder);
        }
        return m2962a(viewHolder, itemHolderInfo.f1595a, itemHolderInfo.f1596b, itemHolderInfo2.f1595a, itemHolderInfo2.f1596b);
    }

    public boolean m2970c(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
        if (itemHolderInfo.f1595a == itemHolderInfo2.f1595a && itemHolderInfo.f1596b == itemHolderInfo2.f1596b) {
            m2974i(viewHolder);
            return false;
        }
        return m2962a(viewHolder, itemHolderInfo.f1595a, itemHolderInfo.f1596b, itemHolderInfo2.f1595a, itemHolderInfo2.f1596b);
    }

    public boolean m2965a(ViewHolder viewHolder, ViewHolder viewHolder2, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
        int i;
        int i2;
        int i3 = itemHolderInfo.f1595a;
        int i4 = itemHolderInfo.f1596b;
        if (viewHolder2.m3496c()) {
            i = itemHolderInfo.f1595a;
            i2 = itemHolderInfo.f1596b;
        } else {
            i = itemHolderInfo2.f1595a;
            i2 = itemHolderInfo2.f1596b;
        }
        return m2964a(viewHolder, viewHolder2, i3, i4, i, i2);
    }

    public final void m2973h(ViewHolder viewHolder) {
        m2980o(viewHolder);
        m2953e(viewHolder);
    }

    public final void m2974i(ViewHolder viewHolder) {
        m2984s(viewHolder);
        m2953e(viewHolder);
    }

    public final void m2975j(ViewHolder viewHolder) {
        m2982q(viewHolder);
        m2953e(viewHolder);
    }

    public final void m2960a(ViewHolder viewHolder, boolean z) {
        m2971d(viewHolder, z);
        m2953e(viewHolder);
    }

    public final void m2976k(ViewHolder viewHolder) {
        m2979n(viewHolder);
    }

    public final void m2977l(ViewHolder viewHolder) {
        m2983r(viewHolder);
    }

    public final void m2978m(ViewHolder viewHolder) {
        m2981p(viewHolder);
    }

    public final void m2966b(ViewHolder viewHolder, boolean z) {
        m2969c(viewHolder, z);
    }

    public void m2979n(ViewHolder viewHolder) {
    }

    public void m2980o(ViewHolder viewHolder) {
    }

    public void m2981p(ViewHolder viewHolder) {
    }

    public void m2982q(ViewHolder viewHolder) {
    }

    public void m2983r(ViewHolder viewHolder) {
    }

    public void m2984s(ViewHolder viewHolder) {
    }

    public void m2969c(ViewHolder viewHolder, boolean z) {
    }

    public void m2971d(ViewHolder viewHolder, boolean z) {
    }
}
