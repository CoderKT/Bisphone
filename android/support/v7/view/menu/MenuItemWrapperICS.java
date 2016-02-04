package android.support.v7.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.CollapsibleActionView;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

@TargetApi(14)
public class MenuItemWrapperICS extends BaseMenuWrapper<SupportMenuItem> implements MenuItem {
    private Method f1103c;

    class ActionProviderWrapper extends ActionProvider {
        final android.view.ActionProvider f1098a;
        final /* synthetic */ MenuItemWrapperICS f1099b;

        public ActionProviderWrapper(MenuItemWrapperICS menuItemWrapperICS, Context context, android.view.ActionProvider actionProvider) {
            this.f1099b = menuItemWrapperICS;
            super(context);
            this.f1098a = actionProvider;
        }

        public View m2487a() {
            return this.f1098a.onCreateActionView();
        }

        public boolean m2489d() {
            return this.f1098a.onPerformDefaultAction();
        }

        public boolean m2490e() {
            return this.f1098a.hasSubMenu();
        }

        public void m2488a(SubMenu subMenu) {
            this.f1098a.onPrepareSubMenu(this.f1099b.m2374a(subMenu));
        }
    }

    class CollapsibleActionViewWrapper extends FrameLayout implements CollapsibleActionView {
        final android.view.CollapsibleActionView f1100a;

        CollapsibleActionViewWrapper(View view) {
            super(view.getContext());
            this.f1100a = (android.view.CollapsibleActionView) view;
            addView(view);
        }

        public void m2491a() {
            this.f1100a.onActionViewExpanded();
        }

        public void m2492b() {
            this.f1100a.onActionViewCollapsed();
        }

        View m2493c() {
            return (View) this.f1100a;
        }
    }

    class OnActionExpandListenerWrapper extends BaseWrapper<OnActionExpandListener> implements MenuItemCompat.OnActionExpandListener {
        final /* synthetic */ MenuItemWrapperICS f1101a;

        OnActionExpandListenerWrapper(MenuItemWrapperICS menuItemWrapperICS, OnActionExpandListener onActionExpandListener) {
            this.f1101a = menuItemWrapperICS;
            super(onActionExpandListener);
        }

        public boolean m2494a(MenuItem menuItem) {
            return ((OnActionExpandListener) this.b).onMenuItemActionExpand(this.f1101a.m2373a(menuItem));
        }

        public boolean m2495b(MenuItem menuItem) {
            return ((OnActionExpandListener) this.b).onMenuItemActionCollapse(this.f1101a.m2373a(menuItem));
        }
    }

    class OnMenuItemClickListenerWrapper extends BaseWrapper<OnMenuItemClickListener> implements OnMenuItemClickListener {
        final /* synthetic */ MenuItemWrapperICS f1102a;

        OnMenuItemClickListenerWrapper(MenuItemWrapperICS menuItemWrapperICS, OnMenuItemClickListener onMenuItemClickListener) {
            this.f1102a = menuItemWrapperICS;
            super(onMenuItemClickListener);
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            return ((OnMenuItemClickListener) this.b).onMenuItemClick(this.f1102a.m2373a(menuItem));
        }
    }

    MenuItemWrapperICS(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    public int getItemId() {
        return ((SupportMenuItem) this.b).getItemId();
    }

    public int getGroupId() {
        return ((SupportMenuItem) this.b).getGroupId();
    }

    public int getOrder() {
        return ((SupportMenuItem) this.b).getOrder();
    }

    public MenuItem setTitle(CharSequence charSequence) {
        ((SupportMenuItem) this.b).setTitle(charSequence);
        return this;
    }

    public MenuItem setTitle(int i) {
        ((SupportMenuItem) this.b).setTitle(i);
        return this;
    }

    public CharSequence getTitle() {
        return ((SupportMenuItem) this.b).getTitle();
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((SupportMenuItem) this.b).setTitleCondensed(charSequence);
        return this;
    }

    public CharSequence getTitleCondensed() {
        return ((SupportMenuItem) this.b).getTitleCondensed();
    }

    public MenuItem setIcon(Drawable drawable) {
        ((SupportMenuItem) this.b).setIcon(drawable);
        return this;
    }

    public MenuItem setIcon(int i) {
        ((SupportMenuItem) this.b).setIcon(i);
        return this;
    }

    public Drawable getIcon() {
        return ((SupportMenuItem) this.b).getIcon();
    }

    public MenuItem setIntent(Intent intent) {
        ((SupportMenuItem) this.b).setIntent(intent);
        return this;
    }

    public Intent getIntent() {
        return ((SupportMenuItem) this.b).getIntent();
    }

    public MenuItem setShortcut(char c, char c2) {
        ((SupportMenuItem) this.b).setShortcut(c, c2);
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        ((SupportMenuItem) this.b).setNumericShortcut(c);
        return this;
    }

    public char getNumericShortcut() {
        return ((SupportMenuItem) this.b).getNumericShortcut();
    }

    public MenuItem setAlphabeticShortcut(char c) {
        ((SupportMenuItem) this.b).setAlphabeticShortcut(c);
        return this;
    }

    public char getAlphabeticShortcut() {
        return ((SupportMenuItem) this.b).getAlphabeticShortcut();
    }

    public MenuItem setCheckable(boolean z) {
        ((SupportMenuItem) this.b).setCheckable(z);
        return this;
    }

    public boolean isCheckable() {
        return ((SupportMenuItem) this.b).isCheckable();
    }

    public MenuItem setChecked(boolean z) {
        ((SupportMenuItem) this.b).setChecked(z);
        return this;
    }

    public boolean isChecked() {
        return ((SupportMenuItem) this.b).isChecked();
    }

    public MenuItem setVisible(boolean z) {
        return ((SupportMenuItem) this.b).setVisible(z);
    }

    public boolean isVisible() {
        return ((SupportMenuItem) this.b).isVisible();
    }

    public MenuItem setEnabled(boolean z) {
        ((SupportMenuItem) this.b).setEnabled(z);
        return this;
    }

    public boolean isEnabled() {
        return ((SupportMenuItem) this.b).isEnabled();
    }

    public boolean hasSubMenu() {
        return ((SupportMenuItem) this.b).hasSubMenu();
    }

    public SubMenu getSubMenu() {
        return m2374a(((SupportMenuItem) this.b).getSubMenu());
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        ((SupportMenuItem) this.b).setOnMenuItemClickListener(onMenuItemClickListener != null ? new OnMenuItemClickListenerWrapper(this, onMenuItemClickListener) : null);
        return this;
    }

    public ContextMenuInfo getMenuInfo() {
        return ((SupportMenuItem) this.b).getMenuInfo();
    }

    public void setShowAsAction(int i) {
        ((SupportMenuItem) this.b).setShowAsAction(i);
    }

    public MenuItem setShowAsActionFlags(int i) {
        ((SupportMenuItem) this.b).setShowAsActionFlags(i);
        return this;
    }

    public MenuItem setActionView(View view) {
        if (view instanceof android.view.CollapsibleActionView) {
            view = new CollapsibleActionViewWrapper(view);
        }
        ((SupportMenuItem) this.b).setActionView(view);
        return this;
    }

    public MenuItem setActionView(int i) {
        ((SupportMenuItem) this.b).setActionView(i);
        View actionView = ((SupportMenuItem) this.b).getActionView();
        if (actionView instanceof android.view.CollapsibleActionView) {
            ((SupportMenuItem) this.b).setActionView(new CollapsibleActionViewWrapper(actionView));
        }
        return this;
    }

    public View getActionView() {
        View actionView = ((SupportMenuItem) this.b).getActionView();
        if (actionView instanceof CollapsibleActionViewWrapper) {
            return ((CollapsibleActionViewWrapper) actionView).m2493c();
        }
        return actionView;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        ((SupportMenuItem) this.b).m686a(actionProvider != null ? m2496a(actionProvider) : null);
        return this;
    }

    public android.view.ActionProvider getActionProvider() {
        ActionProvider a = ((SupportMenuItem) this.b).m688a();
        if (a instanceof ActionProviderWrapper) {
            return ((ActionProviderWrapper) a).f1098a;
        }
        return null;
    }

    public boolean expandActionView() {
        return ((SupportMenuItem) this.b).expandActionView();
    }

    public boolean collapseActionView() {
        return ((SupportMenuItem) this.b).collapseActionView();
    }

    public boolean isActionViewExpanded() {
        return ((SupportMenuItem) this.b).isActionViewExpanded();
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        ((SupportMenuItem) this.b).m687a(onActionExpandListener != null ? new OnActionExpandListenerWrapper(this, onActionExpandListener) : null);
        return this;
    }

    public void m2497a(boolean z) {
        try {
            if (this.f1103c == null) {
                this.f1103c = ((SupportMenuItem) this.b).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
            }
            this.f1103c.invoke(this.b, new Object[]{Boolean.valueOf(z)});
        } catch (Throwable e) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e);
        }
    }

    ActionProviderWrapper m2496a(android.view.ActionProvider actionProvider) {
        return new ActionProviderWrapper(this, this.a, actionProvider);
    }
}
