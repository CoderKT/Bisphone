package android.support.v7.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.view.ActionProvider.VisibilityListener;
import android.view.MenuItem;
import android.view.View;

@TargetApi(16)
class MenuItemWrapperJB extends MenuItemWrapperICS {

    class ActionProviderWrapperJB extends ActionProviderWrapper implements VisibilityListener {
        ActionProvider.VisibilityListener f1104c;
        final /* synthetic */ MenuItemWrapperJB f1105d;

        public ActionProviderWrapperJB(MenuItemWrapperJB menuItemWrapperJB, Context context, android.view.ActionProvider actionProvider) {
            this.f1105d = menuItemWrapperJB;
            super(menuItemWrapperJB, context, actionProvider);
        }

        public View m2498a(MenuItem menuItem) {
            return this.a.onCreateActionView(menuItem);
        }

        public boolean m2500b() {
            return this.a.overridesItemVisibility();
        }

        public boolean m2501c() {
            return this.a.isVisible();
        }

        public void m2499a(ActionProvider.VisibilityListener visibilityListener) {
            VisibilityListener visibilityListener2;
            this.f1104c = visibilityListener;
            android.view.ActionProvider actionProvider = this.a;
            if (visibilityListener == null) {
                visibilityListener2 = null;
            }
            actionProvider.setVisibilityListener(visibilityListener2);
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            if (this.f1104c != null) {
                this.f1104c.m884a(z);
            }
        }
    }

    MenuItemWrapperJB(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    ActionProviderWrapper m2502a(android.view.ActionProvider actionProvider) {
        return new ActionProviderWrapperJB(this, this.a, actionProvider);
    }
}
