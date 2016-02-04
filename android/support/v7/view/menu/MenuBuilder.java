package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.C0057R;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class MenuBuilder implements SupportMenu {
    private static final int[] f1042d;
    CharSequence f1043a;
    Drawable f1044b;
    View f1045c;
    private final Context f1046e;
    private final Resources f1047f;
    private boolean f1048g;
    private boolean f1049h;
    private Callback f1050i;
    private ArrayList<MenuItemImpl> f1051j;
    private ArrayList<MenuItemImpl> f1052k;
    private boolean f1053l;
    private ArrayList<MenuItemImpl> f1054m;
    private ArrayList<MenuItemImpl> f1055n;
    private boolean f1056o;
    private int f1057p;
    private ContextMenuInfo f1058q;
    private boolean f1059r;
    private boolean f1060s;
    private boolean f1061t;
    private boolean f1062u;
    private ArrayList<MenuItemImpl> f1063v;
    private CopyOnWriteArrayList<WeakReference<MenuPresenter>> f1064w;
    private MenuItemImpl f1065x;
    private boolean f1066y;

    public interface Callback {
        void m2032a(MenuBuilder menuBuilder);

        boolean m2033a(MenuBuilder menuBuilder, MenuItem menuItem);
    }

    public interface ItemInvoker {
        boolean m2378a(MenuItemImpl menuItemImpl);
    }

    static {
        f1042d = new int[]{1, 4, 5, 3, 2, 0};
    }

    public MenuBuilder(Context context) {
        this.f1057p = 0;
        this.f1059r = false;
        this.f1060s = false;
        this.f1061t = false;
        this.f1062u = false;
        this.f1063v = new ArrayList();
        this.f1064w = new CopyOnWriteArrayList();
        this.f1046e = context;
        this.f1047f = context.getResources();
        this.f1051j = new ArrayList();
        this.f1052k = new ArrayList();
        this.f1053l = true;
        this.f1054m = new ArrayList();
        this.f1055n = new ArrayList();
        this.f1056o = true;
        m2408e(true);
    }

    public MenuBuilder m2410a(int i) {
        this.f1057p = i;
        return this;
    }

    public void m2420a(MenuPresenter menuPresenter) {
        m2421a(menuPresenter, this.f1046e);
    }

    public void m2421a(MenuPresenter menuPresenter, Context context) {
        this.f1064w.add(new WeakReference(menuPresenter));
        menuPresenter.m2349a(context, this);
        this.f1056o = true;
    }

    public void m2431b(MenuPresenter menuPresenter) {
        Iterator it = this.f1064w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter2 = (MenuPresenter) weakReference.get();
            if (menuPresenter2 == null || menuPresenter2 == menuPresenter) {
                this.f1064w.remove(weakReference);
            }
        }
    }

    private void m2407d(boolean z) {
        if (!this.f1064w.isEmpty()) {
            m2442g();
            Iterator it = this.f1064w.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f1064w.remove(weakReference);
                } else {
                    menuPresenter.m2353b(z);
                }
            }
            m2443h();
        }
    }

    private boolean m2405a(SubMenuBuilder subMenuBuilder, MenuPresenter menuPresenter) {
        boolean z = false;
        if (this.f1064w.isEmpty()) {
            return false;
        }
        if (menuPresenter != null) {
            z = menuPresenter.m2352a(subMenuBuilder);
        }
        Iterator it = this.f1064w.iterator();
        boolean z2 = z;
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter2 = (MenuPresenter) weakReference.get();
            if (menuPresenter2 == null) {
                this.f1064w.remove(weakReference);
                z = z2;
            } else if (z2) {
                z = z2;
            } else {
                z = menuPresenter2.m2352a(subMenuBuilder);
            }
            z2 = z;
        }
        return z2;
    }

    public void m2417a(Bundle bundle) {
        int size = size();
        int i = 0;
        SparseArray sparseArray = null;
        while (i < size) {
            MenuItem item = getItem(i);
            View a = MenuItemCompat.m958a(item);
            if (!(a == null || a.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                a.saveHierarchyState(sparseArray);
                if (MenuItemCompat.m962c(item)) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            SparseArray sparseArray2 = sparseArray;
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).m2417a(bundle);
            }
            i++;
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(m2416a(), sparseArray);
        }
    }

    public void m2429b(Bundle bundle) {
        if (bundle != null) {
            MenuItem item;
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(m2416a());
            int size = size();
            for (int i = 0; i < size; i++) {
                item = getItem(i);
                View a = MenuItemCompat.m958a(item);
                if (!(a == null || a.getId() == -1)) {
                    a.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((SubMenuBuilder) item.getSubMenu()).m2429b(bundle);
                }
            }
            int i2 = bundle.getInt("android:menu:expandedactionview");
            if (i2 > 0) {
                item = findItem(i2);
                if (item != null) {
                    MenuItemCompat.m961b(item);
                }
            }
        }
    }

    protected String m2416a() {
        return "android:menu:actionviewstates";
    }

    public void m2418a(Callback callback) {
        this.f1050i = callback;
    }

    protected MenuItem m2415a(int i, int i2, int i3, CharSequence charSequence) {
        int d = m2406d(i3);
        MenuItem a = m2402a(i, i2, i3, d, charSequence, this.f1057p);
        if (this.f1058q != null) {
            a.m2467a(this.f1058q);
        }
        this.f1051j.add(m2401a(this.f1051j, d), a);
        m2432b(true);
        return a;
    }

    private MenuItemImpl m2402a(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new MenuItemImpl(this, i, i2, i3, i4, charSequence, i5);
    }

    public MenuItem add(CharSequence charSequence) {
        return m2415a(0, 0, 0, charSequence);
    }

    public MenuItem add(int i) {
        return m2415a(0, 0, 0, this.f1047f.getString(i));
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return m2415a(i, i2, i3, charSequence);
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return m2415a(i, i2, i3, this.f1047f.getString(i4));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.f1047f.getString(i));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) m2415a(i, i2, i3, charSequence);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.f1046e, this, menuItemImpl);
        menuItemImpl.m2466a(subMenuBuilder);
        return subMenuBuilder;
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.f1047f.getString(i4));
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.f1046e.getPackageManager();
        List queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i5 = 0; i5 < size; i5++) {
            Intent intent2;
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivityOptions.get(i5);
            if (resolveInfo.specificIndex < 0) {
                intent2 = intent;
            } else {
                intent2 = intentArr[resolveInfo.specificIndex];
            }
            Intent intent3 = new Intent(intent2);
            intent3.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent4 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent3);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent4;
            }
        }
        return size;
    }

    public void removeItem(int i) {
        m2404a(m2428b(i), true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeGroup(int r6) {
        /*
        r5 = this;
        r1 = 0;
        r3 = r5.m2434c(r6);
        if (r3 < 0) goto L_0x002b;
    L_0x0007:
        r0 = r5.f1051j;
        r0 = r0.size();
        r4 = r0 - r3;
        r0 = r1;
    L_0x0010:
        r2 = r0 + 1;
        if (r0 >= r4) goto L_0x0027;
    L_0x0014:
        r0 = r5.f1051j;
        r0 = r0.get(r3);
        r0 = (android.support.v7.view.menu.MenuItemImpl) r0;
        r0 = r0.getGroupId();
        if (r0 != r6) goto L_0x0027;
    L_0x0022:
        r5.m2404a(r3, r1);
        r0 = r2;
        goto L_0x0010;
    L_0x0027:
        r0 = 1;
        r5.m2432b(r0);
    L_0x002b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.MenuBuilder.removeGroup(int):void");
    }

    private void m2404a(int i, boolean z) {
        if (i >= 0 && i < this.f1051j.size()) {
            this.f1051j.remove(i);
            if (z) {
                m2432b(true);
            }
        }
    }

    public void clear() {
        if (this.f1065x != null) {
            m2439d(this.f1065x);
        }
        this.f1051j.clear();
        m2432b(true);
    }

    void m2422a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f1051j.size();
        for (int i = 0; i < size; i++) {
            MenuItem menuItem2 = (MenuItemImpl) this.f1051j.get(i);
            if (menuItem2.getGroupId() == groupId && menuItem2.m2479g() && menuItem2.isCheckable()) {
                menuItem2.m2470b(menuItem2 == menuItem);
            }
        }
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.f1051j.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f1051j.get(i2);
            if (menuItemImpl.getGroupId() == i) {
                menuItemImpl.m2468a(z2);
                menuItemImpl.setCheckable(z);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int size = this.f1051j.size();
        int i2 = 0;
        boolean z2 = false;
        while (i2 < size) {
            boolean z3;
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f1051j.get(i2);
            if (menuItemImpl.getGroupId() == i && menuItemImpl.m2473c(z)) {
                z3 = true;
            } else {
                z3 = z2;
            }
            i2++;
            z2 = z3;
        }
        if (z2) {
            m2432b(true);
        }
    }

    public void setGroupEnabled(int i, boolean z) {
        int size = this.f1051j.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f1051j.get(i2);
            if (menuItemImpl.getGroupId() == i) {
                menuItemImpl.setEnabled(z);
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.f1066y) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (((MenuItemImpl) this.f1051j.get(i)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f1051j.get(i2);
            if (menuItemImpl.getItemId() == i) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu()) {
                MenuItem findItem = menuItemImpl.getSubMenu().findItem(i);
                if (findItem != null) {
                    return findItem;
                }
            }
        }
        return null;
    }

    public int m2428b(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((MenuItemImpl) this.f1051j.get(i2)).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public int m2434c(int i) {
        return m2409a(i, 0);
    }

    public int m2409a(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        for (int i3 = i2; i3 < size; i3++) {
            if (((MenuItemImpl) this.f1051j.get(i3)).getGroupId() == i) {
                return i3;
            }
        }
        return -1;
    }

    public int size() {
        return this.f1051j.size();
    }

    public MenuItem getItem(int i) {
        return (MenuItem) this.f1051j.get(i);
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return m2414a(i, keyEvent) != null;
    }

    public void setQwertyMode(boolean z) {
        this.f1048g = z;
        m2432b(false);
    }

    private static int m2406d(int i) {
        int i2 = (-65536 & i) >> 16;
        if (i2 >= 0 && i2 < f1042d.length) {
            return (f1042d[i2] << 16) | (InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i);
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    boolean m2433b() {
        return this.f1048g;
    }

    private void m2408e(boolean z) {
        boolean z2 = true;
        if (!(z && this.f1047f.getConfiguration().keyboard != 1 && this.f1047f.getBoolean(C0057R.bool.abc_config_showMenuShortcutsWhenKeyboardPresent))) {
            z2 = false;
        }
        this.f1049h = z2;
    }

    public boolean m2436c() {
        return this.f1049h;
    }

    Resources m2438d() {
        return this.f1047f;
    }

    public Context m2440e() {
        return this.f1046e;
    }

    boolean m2425a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.f1050i != null && this.f1050i.m2033a(menuBuilder, menuItem);
    }

    public void m2441f() {
        if (this.f1050i != null) {
            this.f1050i.m2032a(this);
        }
    }

    private static int m2401a(ArrayList<MenuItemImpl> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((MenuItemImpl) arrayList.get(size)).m2472c() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        MenuItem a = m2414a(i, keyEvent);
        boolean z = false;
        if (a != null) {
            z = m2426a(a, i2);
        }
        if ((i2 & 2) != 0) {
            m2424a(true);
        }
        return z;
    }

    void m2423a(List<MenuItemImpl> list, int i, KeyEvent keyEvent) {
        boolean b = m2433b();
        int metaState = keyEvent.getMetaState();
        KeyData keyData = new KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.f1051j.size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) this.f1051j.get(i2);
                if (menuItemImpl.hasSubMenu()) {
                    ((MenuBuilder) menuItemImpl.getSubMenu()).m2423a((List) list, i, keyEvent);
                }
                char alphabeticShortcut = b ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
                if ((metaState & 5) == 0 && alphabeticShortcut != '\u0000' && ((alphabeticShortcut == keyData.meta[0] || alphabeticShortcut == keyData.meta[2] || (b && alphabeticShortcut == '\b' && i == 67)) && menuItemImpl.isEnabled())) {
                    list.add(menuItemImpl);
                }
            }
        }
    }

    MenuItemImpl m2414a(int i, KeyEvent keyEvent) {
        List list = this.f1063v;
        list.clear();
        m2423a(list, i, keyEvent);
        if (list.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyData keyData = new KeyData();
        keyEvent.getKeyData(keyData);
        int size = list.size();
        if (size == 1) {
            return (MenuItemImpl) list.get(0);
        }
        boolean b = m2433b();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) list.get(i2);
            char alphabeticShortcut = b ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
            if (alphabeticShortcut == keyData.meta[0] && (metaState & 2) == 0) {
                return menuItemImpl;
            }
            if (alphabeticShortcut == keyData.meta[2] && (metaState & 2) != 0) {
                return menuItemImpl;
            }
            if (b && alphabeticShortcut == '\b' && i == 67) {
                return menuItemImpl;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int i, int i2) {
        return m2426a(findItem(i), i2);
    }

    public boolean m2426a(MenuItem menuItem, int i) {
        return m2427a(menuItem, null, i);
    }

    public boolean m2427a(MenuItem menuItem, MenuPresenter menuPresenter, int i) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
        if (menuItemImpl == null || !menuItemImpl.isEnabled()) {
            return false;
        }
        boolean z;
        boolean b = menuItemImpl.m2471b();
        ActionProvider a = menuItemImpl.m2464a();
        if (a == null || !a.m894e()) {
            z = false;
        } else {
            z = true;
        }
        boolean expandActionView;
        if (menuItemImpl.m2486n()) {
            expandActionView = menuItemImpl.expandActionView() | b;
            if (!expandActionView) {
                return expandActionView;
            }
            m2424a(true);
            return expandActionView;
        } else if (menuItemImpl.hasSubMenu() || z) {
            m2424a(false);
            if (!menuItemImpl.hasSubMenu()) {
                menuItemImpl.m2466a(new SubMenuBuilder(m2440e(), this, menuItemImpl));
            }
            SubMenuBuilder subMenuBuilder = (SubMenuBuilder) menuItemImpl.getSubMenu();
            if (z) {
                a.m889a((SubMenu) subMenuBuilder);
            }
            expandActionView = m2405a(subMenuBuilder, menuPresenter) | b;
            if (expandActionView) {
                return expandActionView;
            }
            m2424a(true);
            return expandActionView;
        } else {
            if ((i & 1) == 0) {
                m2424a(true);
            }
            return b;
        }
    }

    public final void m2424a(boolean z) {
        if (!this.f1062u) {
            this.f1062u = true;
            Iterator it = this.f1064w.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f1064w.remove(weakReference);
                } else {
                    menuPresenter.m2350a(this, z);
                }
            }
            this.f1062u = false;
        }
    }

    public void close() {
        m2424a(true);
    }

    public void m2432b(boolean z) {
        if (this.f1059r) {
            this.f1060s = true;
            return;
        }
        if (z) {
            this.f1053l = true;
            this.f1056o = true;
        }
        m2407d(z);
    }

    public void m2442g() {
        if (!this.f1059r) {
            this.f1059r = true;
            this.f1060s = false;
        }
    }

    public void m2443h() {
        this.f1059r = false;
        if (this.f1060s) {
            this.f1060s = false;
            m2432b(true);
        }
    }

    void m2419a(MenuItemImpl menuItemImpl) {
        this.f1053l = true;
        m2432b(true);
    }

    void m2430b(MenuItemImpl menuItemImpl) {
        this.f1056o = true;
        m2432b(true);
    }

    public ArrayList<MenuItemImpl> m2444i() {
        if (!this.f1053l) {
            return this.f1052k;
        }
        this.f1052k.clear();
        int size = this.f1051j.size();
        for (int i = 0; i < size; i++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f1051j.get(i);
            if (menuItemImpl.isVisible()) {
                this.f1052k.add(menuItemImpl);
            }
        }
        this.f1053l = false;
        this.f1056o = true;
        return this.f1052k;
    }

    public void m2445j() {
        ArrayList i = m2444i();
        if (this.f1056o) {
            Iterator it = this.f1064w.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                int i3;
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f1064w.remove(weakReference);
                    i3 = i2;
                } else {
                    i3 = menuPresenter.m2354b() | i2;
                }
                i2 = i3;
            }
            if (i2 != 0) {
                this.f1054m.clear();
                this.f1055n.clear();
                i2 = i.size();
                for (int i4 = 0; i4 < i2; i4++) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) i.get(i4);
                    if (menuItemImpl.m2482j()) {
                        this.f1054m.add(menuItemImpl);
                    } else {
                        this.f1055n.add(menuItemImpl);
                    }
                }
            } else {
                this.f1054m.clear();
                this.f1055n.clear();
                this.f1055n.addAll(m2444i());
            }
            this.f1056o = false;
        }
    }

    public ArrayList<MenuItemImpl> m2446k() {
        m2445j();
        return this.f1054m;
    }

    public ArrayList<MenuItemImpl> m2447l() {
        m2445j();
        return this.f1055n;
    }

    public void clearHeader() {
        this.f1044b = null;
        this.f1043a = null;
        this.f1045c = null;
        m2432b(false);
    }

    private void m2403a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources d = m2438d();
        if (view != null) {
            this.f1045c = view;
            this.f1043a = null;
            this.f1044b = null;
        } else {
            if (i > 0) {
                this.f1043a = d.getText(i);
            } else if (charSequence != null) {
                this.f1043a = charSequence;
            }
            if (i2 > 0) {
                this.f1044b = ContextCompat.m98a(m2440e(), i2);
            } else if (drawable != null) {
                this.f1044b = drawable;
            }
            this.f1045c = null;
        }
        m2432b(false);
    }

    protected MenuBuilder m2413a(CharSequence charSequence) {
        m2403a(0, charSequence, 0, null, null);
        return this;
    }

    protected MenuBuilder m2411a(Drawable drawable) {
        m2403a(0, null, 0, drawable, null);
        return this;
    }

    protected MenuBuilder m2412a(View view) {
        m2403a(0, null, 0, null, view);
        return this;
    }

    public CharSequence m2448m() {
        return this.f1043a;
    }

    public Drawable m2449n() {
        return this.f1044b;
    }

    public View m2450o() {
        return this.f1045c;
    }

    public MenuBuilder m2451p() {
        return this;
    }

    boolean m2452q() {
        return this.f1061t;
    }

    public boolean m2437c(MenuItemImpl menuItemImpl) {
        boolean z = false;
        if (!this.f1064w.isEmpty()) {
            m2442g();
            Iterator it = this.f1064w.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f1064w.remove(weakReference);
                    z = z2;
                } else {
                    z = menuPresenter.m2351a(this, menuItemImpl);
                    if (z) {
                        break;
                    }
                }
                z2 = z;
            }
            z = z2;
            m2443h();
            if (z) {
                this.f1065x = menuItemImpl;
            }
        }
        return z;
    }

    public boolean m2439d(MenuItemImpl menuItemImpl) {
        boolean z = false;
        if (!this.f1064w.isEmpty() && this.f1065x == menuItemImpl) {
            m2442g();
            Iterator it = this.f1064w.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f1064w.remove(weakReference);
                    z = z2;
                } else {
                    z = menuPresenter.m2355b(this, menuItemImpl);
                    if (z) {
                        break;
                    }
                }
                z2 = z;
            }
            z = z2;
            m2443h();
            if (z) {
                this.f1065x = null;
            }
        }
        return z;
    }

    public MenuItemImpl m2453r() {
        return this.f1065x;
    }

    public void m2435c(boolean z) {
        this.f1066y = z;
    }
}
