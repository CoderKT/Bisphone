package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzx;

public class ErrorDialogFragment extends DialogFragment {
    private Dialog f5639a;
    private OnCancelListener f5640b;

    public ErrorDialogFragment() {
        this.f5639a = null;
        this.f5640b = null;
    }

    public static ErrorDialogFragment m8307a(Dialog dialog, OnCancelListener onCancelListener) {
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        Dialog dialog2 = (Dialog) zzx.m8719a((Object) dialog, (Object) "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        errorDialogFragment.f5639a = dialog2;
        if (onCancelListener != null) {
            errorDialogFragment.f5640b = onCancelListener;
        }
        return errorDialogFragment;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f5640b != null) {
            this.f5640b.onCancel(dialogInterface);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (this.f5639a == null) {
            setShowsDialog(false);
        }
        return this.f5639a;
    }

    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
