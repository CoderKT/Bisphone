package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView.ItemView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

public class ListMenuPresenter implements MenuPresenter, OnItemClickListener {
    Context f1033a;
    LayoutInflater f1034b;
    MenuBuilder f1035c;
    ExpandedMenuView f1036d;
    int f1037e;
    int f1038f;
    MenuAdapter f1039g;
    private int f1040h;
    private Callback f1041i;

    class MenuAdapter extends BaseAdapter {
        final /* synthetic */ ListMenuPresenter f1031a;
        private int f1032b;

        public /* synthetic */ Object getItem(int i) {
            return m2388a(i);
        }

        public MenuAdapter(ListMenuPresenter listMenuPresenter) {
            this.f1031a = listMenuPresenter;
            this.f1032b = -1;
            m2389a();
        }

        public int getCount() {
            int size = this.f1031a.f1035c.m2447l().size() - this.f1031a.f1040h;
            return this.f1032b < 0 ? size : size - 1;
        }

        public MenuItemImpl m2388a(int i) {
            ArrayList l = this.f1031a.f1035c.m2447l();
            int a = this.f1031a.f1040h + i;
            if (this.f1032b >= 0 && a >= this.f1032b) {
                a++;
            }
            return (MenuItemImpl) l.get(a);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            if (view == null) {
                inflate = this.f1031a.f1034b.inflate(this.f1031a.f1038f, viewGroup, false);
            } else {
                inflate = view;
            }
            ((ItemView) inflate).m2336a(m2388a(i), 0);
            return inflate;
        }

        void m2389a() {
            MenuItemImpl r = this.f1031a.f1035c.m2453r();
            if (r != null) {
                ArrayList l = this.f1031a.f1035c.m2447l();
                int size = l.size();
                for (int i = 0; i < size; i++) {
                    if (((MenuItemImpl) l.get(i)) == r) {
                        this.f1032b = i;
                        return;
                    }
                }
            }
            this.f1032b = -1;
        }

        public void notifyDataSetChanged() {
            m2389a();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(Context context, int i) {
        this(i, 0);
        this.f1033a = context;
        this.f1034b = LayoutInflater.from(this.f1033a);
    }

    public ListMenuPresenter(int i, int i2) {
        this.f1038f = i;
        this.f1037e = i2;
    }

    public void m2393a(Context context, MenuBuilder menuBuilder) {
        if (this.f1037e != 0) {
            this.f1033a = new ContextThemeWrapper(context, this.f1037e);
            this.f1034b = LayoutInflater.from(this.f1033a);
        } else if (this.f1033a != null) {
            this.f1033a = context;
            if (this.f1034b == null) {
                this.f1034b = LayoutInflater.from(this.f1033a);
            }
        }
        this.f1035c = menuBuilder;
        if (this.f1039g != null) {
            this.f1039g.notifyDataSetChanged();
        }
    }

    public MenuView m2391a(ViewGroup viewGroup) {
        if (this.f1036d == null) {
            this.f1036d = (ExpandedMenuView) this.f1034b.inflate(C0057R.layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.f1039g == null) {
                this.f1039g = new MenuAdapter(this);
            }
            this.f1036d.setAdapter(this.f1039g);
            this.f1036d.setOnItemClickListener(this);
        }
        return this.f1036d;
    }

    public ListAdapter m2392a() {
        if (this.f1039g == null) {
            this.f1039g = new MenuAdapter(this);
        }
        return this.f1039g;
    }

    public void m2398b(boolean z) {
        if (this.f1039g != null) {
            this.f1039g.notifyDataSetChanged();
        }
    }

    public void m2395a(Callback callback) {
        this.f1041i = callback;
    }

    public boolean m2397a(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder).m2455a(null);
        if (this.f1041i != null) {
            this.f1041i.m2115a(subMenuBuilder);
        }
        return true;
    }

    public void m2394a(MenuBuilder menuBuilder, boolean z) {
        if (this.f1041i != null) {
            this.f1041i.m2114a(menuBuilder, z);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f1035c.m2427a(this.f1039g.m2388a(i), (MenuPresenter) this, 0);
    }

    public boolean m2399b() {
        return false;
    }

    public boolean m2396a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean m2400b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }
}
