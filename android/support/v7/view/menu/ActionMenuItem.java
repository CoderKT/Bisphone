package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.MenuItemCompat;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class ActionMenuItem implements SupportMenuItem {
    private final int f961a;
    private final int f962b;
    private final int f963c;
    private final int f964d;
    private CharSequence f965e;
    private CharSequence f966f;
    private Intent f967g;
    private char f968h;
    private char f969i;
    private Drawable f970j;
    private int f971k;
    private Context f972l;
    private OnMenuItemClickListener f973m;
    private int f974n;

    public /* synthetic */ MenuItem setActionView(int i) {
        return m2315a(i);
    }

    public /* synthetic */ MenuItem setActionView(View view) {
        return m2318a(view);
    }

    public /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        return m2320b(i);
    }

    public ActionMenuItem(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.f971k = 0;
        this.f974n = 16;
        this.f972l = context;
        this.f961a = i2;
        this.f962b = i;
        this.f963c = i3;
        this.f964d = i4;
        this.f965e = charSequence;
    }

    public char getAlphabeticShortcut() {
        return this.f969i;
    }

    public int getGroupId() {
        return this.f962b;
    }

    public Drawable getIcon() {
        return this.f970j;
    }

    public Intent getIntent() {
        return this.f967g;
    }

    public int getItemId() {
        return this.f961a;
    }

    public ContextMenuInfo getMenuInfo() {
        return null;
    }

    public char getNumericShortcut() {
        return this.f968h;
    }

    public int getOrder() {
        return this.f964d;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public CharSequence getTitle() {
        return this.f965e;
    }

    public CharSequence getTitleCondensed() {
        return this.f966f != null ? this.f966f : this.f965e;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isCheckable() {
        return (this.f974n & 1) != 0;
    }

    public boolean isChecked() {
        return (this.f974n & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.f974n & 16) != 0;
    }

    public boolean isVisible() {
        return (this.f974n & 8) == 0;
    }

    public MenuItem setAlphabeticShortcut(char c) {
        this.f969i = c;
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        this.f974n = (z ? 1 : 0) | (this.f974n & -2);
        return this;
    }

    public MenuItem setChecked(boolean z) {
        this.f974n = (z ? 2 : 0) | (this.f974n & -3);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.f974n = (z ? 16 : 0) | (this.f974n & -17);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.f970j = drawable;
        this.f971k = 0;
        return this;
    }

    public MenuItem setIcon(int i) {
        this.f971k = i;
        this.f970j = ContextCompat.m98a(this.f972l, i);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.f967g = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        this.f968h = c;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.f973m = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.f968h = c;
        this.f969i = c2;
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.f965e = charSequence;
        return this;
    }

    public MenuItem setTitle(int i) {
        this.f965e = this.f972l.getResources().getString(i);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f966f = charSequence;
        return this;
    }

    public MenuItem setVisible(boolean z) {
        this.f974n = (z ? 0 : 8) | (this.f974n & 8);
        return this;
    }

    public void setShowAsAction(int i) {
    }

    public SupportMenuItem m2318a(View view) {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem m2315a(int i) {
        throw new UnsupportedOperationException();
    }

    public android.support.v4.view.ActionProvider m2319a() {
        return null;
    }

    public SupportMenuItem m2316a(android.support.v4.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem m2320b(int i) {
        setShowAsAction(i);
        return this;
    }

    public boolean expandActionView() {
        return false;
    }

    public boolean collapseActionView() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem m2317a(MenuItemCompat.OnActionExpandListener onActionExpandListener) {
        return this;
    }
}
