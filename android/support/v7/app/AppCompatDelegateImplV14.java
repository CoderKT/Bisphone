package android.support.v7.app;

import android.content.Context;
import android.support.v7.view.SupportActionModeWrapper.CallbackWrapper;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;

class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11 {
    private boolean f793q;

    class AppCompatWindowCallbackV14 extends AppCompatWindowCallbackBase {
        final /* synthetic */ AppCompatDelegateImplV14 f792b;

        AppCompatWindowCallbackV14(AppCompatDelegateImplV14 appCompatDelegateImplV14, Callback callback) {
            this.f792b = appCompatDelegateImplV14;
            super(appCompatDelegateImplV14, callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (this.f792b.m2101h()) {
                return m2099a(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        final ActionMode m2099a(ActionMode.Callback callback) {
            Object callbackWrapper = new CallbackWrapper(this.f792b.a, callback);
            android.support.v7.view.ActionMode b = this.f792b.m2085b((android.support.v7.view.ActionMode.Callback) callbackWrapper);
            if (b != null) {
                return callbackWrapper.m2280b(b);
            }
            return null;
        }
    }

    AppCompatDelegateImplV14(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.f793q = true;
    }

    Callback m2100a(Callback callback) {
        return new AppCompatWindowCallbackV14(this, callback);
    }

    public boolean m2101h() {
        return this.f793q;
    }
}
