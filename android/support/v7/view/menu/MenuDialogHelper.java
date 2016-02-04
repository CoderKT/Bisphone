package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

class MenuDialogHelper implements OnClickListener, OnDismissListener, OnKeyListener, Callback {
    ListMenuPresenter f1067a;
    private MenuBuilder f1068b;
    private AlertDialog f1069c;
    private Callback f1070d;

    public MenuDialogHelper(MenuBuilder menuBuilder) {
        this.f1068b = menuBuilder;
    }

    public void m2455a(IBinder iBinder) {
        MenuBuilder menuBuilder = this.f1068b;
        Builder builder = new Builder(menuBuilder.m2440e());
        this.f1067a = new ListMenuPresenter(builder.m1973a(), C0057R.layout.abc_list_menu_item_layout);
        this.f1067a.m2395a((Callback) this);
        this.f1068b.m2420a(this.f1067a);
        builder.m1979a(this.f1067a.m2392a(), (OnClickListener) this);
        View o = menuBuilder.m2450o();
        if (o != null) {
            builder.m1978a(o);
        } else {
            builder.m1977a(menuBuilder.m2449n()).m1980a(menuBuilder.m2448m());
        }
        builder.m1976a((OnKeyListener) this);
        this.f1069c = builder.m1988b();
        this.f1069c.setOnDismissListener(this);
        LayoutParams attributes = this.f1069c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f1069c.show();
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i == 82 || i == 4) {
            Window window;
            View decorView;
            DispatcherState keyDispatcherState;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                window = this.f1069c.getWindow();
                if (window != null) {
                    decorView = window.getDecorView();
                    if (decorView != null) {
                        keyDispatcherState = decorView.getKeyDispatcherState();
                        if (keyDispatcherState != null) {
                            keyDispatcherState.startTracking(keyEvent, this);
                            return true;
                        }
                    }
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled()) {
                window = this.f1069c.getWindow();
                if (window != null) {
                    decorView = window.getDecorView();
                    if (decorView != null) {
                        keyDispatcherState = decorView.getKeyDispatcherState();
                        if (keyDispatcherState != null && keyDispatcherState.isTracking(keyEvent)) {
                            this.f1068b.m2424a(true);
                            dialogInterface.dismiss();
                            return true;
                        }
                    }
                }
            }
        }
        return this.f1068b.performShortcut(i, keyEvent, 0);
    }

    public void m2454a() {
        if (this.f1069c != null) {
            this.f1069c.dismiss();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.f1067a.m2394a(this.f1068b, true);
    }

    public void m2456a(MenuBuilder menuBuilder, boolean z) {
        if (z || menuBuilder == this.f1068b) {
            m2454a();
        }
        if (this.f1070d != null) {
            this.f1070d.m2114a(menuBuilder, z);
        }
    }

    public boolean m2457a(MenuBuilder menuBuilder) {
        if (this.f1070d != null) {
            return this.f1070d.m2115a(menuBuilder);
        }
        return false;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f1068b.m2426a((MenuItemImpl) this.f1067a.m2392a().getItem(i), 0);
    }
}
