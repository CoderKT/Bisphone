package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public final class ExpandedMenuView extends ListView implements ItemInvoker, MenuView, OnItemClickListener {
    private static final int[] f1014a;
    private MenuBuilder f1015b;
    private int f1016c;

    static {
        f1014a = new int[]{16842964, 16843049};
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        TintTypedArray a = TintTypedArray.m3759a(context, attributeSet, f1014a, i, 0);
        if (a.m3773e(0)) {
            setBackgroundDrawable(a.m3762a(0));
        }
        if (a.m3773e(1)) {
            setDivider(a.m3762a(1));
        }
        a.m3763a();
    }

    public void m2380a(MenuBuilder menuBuilder) {
        this.f1015b = menuBuilder;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public boolean m2381a(MenuItemImpl menuItemImpl) {
        return this.f1015b.m2426a((MenuItem) menuItemImpl, 0);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        m2381a((MenuItemImpl) getAdapter().getItem(i));
    }

    public int getWindowAnimations() {
        return this.f1016c;
    }
}
