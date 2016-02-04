package android.support.v7.app;

import android.content.Context;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;
import se.emilsjolander.stickylistheaders.C1128R;

class AppCompatDelegateImplV23 extends AppCompatDelegateImplV14 {

    class AppCompatWindowCallbackV23 extends AppCompatWindowCallbackV14 {
        final /* synthetic */ AppCompatDelegateImplV23 f794c;

        AppCompatWindowCallbackV23(AppCompatDelegateImplV23 appCompatDelegateImplV23, Callback callback) {
            this.f794c = appCompatDelegateImplV23;
            super(appCompatDelegateImplV23, callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (this.f794c.m2101h()) {
                switch (i) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        return m2099a(callback);
                }
            }
            return super.onWindowStartingActionMode(callback, i);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }
    }

    AppCompatDelegateImplV23(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }

    Callback m2102a(Callback callback) {
        return new AppCompatWindowCallbackV23(this, callback);
    }
}
