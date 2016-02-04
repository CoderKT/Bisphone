package android.support.v4.internal.view;

import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.view.MenuItem;
import android.view.View;

public interface SupportMenuItem extends MenuItem {
    SupportMenuItem m686a(ActionProvider actionProvider);

    SupportMenuItem m687a(OnActionExpandListener onActionExpandListener);

    ActionProvider m688a();

    boolean collapseActionView();

    boolean expandActionView();

    View getActionView();

    boolean isActionViewExpanded();

    MenuItem setActionView(int i);

    MenuItem setActionView(View view);

    void setShowAsAction(int i);

    MenuItem setShowAsActionFlags(int i);
}
