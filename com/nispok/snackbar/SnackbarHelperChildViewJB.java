package com.nispok.snackbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.View;
import android.view.ViewParent;

@TargetApi(16)
class SnackbarHelperChildViewJB extends View {
    public SnackbarHelperChildViewJB(Context context) {
        super(context);
        setSaveEnabled(false);
        setWillNotDraw(true);
        setVisibility(8);
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        ViewParent parent = getParent();
        if (parent instanceof Snackbar) {
            ((Snackbar) parent).m10876b(i);
        }
    }
}
