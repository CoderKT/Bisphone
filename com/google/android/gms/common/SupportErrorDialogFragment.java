package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzx;

public class SupportErrorDialogFragment extends DialogFragment {
    private Dialog aj;
    private OnCancelListener ak;

    public SupportErrorDialogFragment() {
        this.aj = null;
        this.ak = null;
    }

    public static SupportErrorDialogFragment m8338a(Dialog dialog, OnCancelListener onCancelListener) {
        SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        Dialog dialog2 = (Dialog) zzx.m8719a((Object) dialog, (Object) "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        supportErrorDialogFragment.aj = dialog2;
        if (onCancelListener != null) {
            supportErrorDialogFragment.ak = onCancelListener;
        }
        return supportErrorDialogFragment;
    }

    public void m8339a(FragmentManager fragmentManager, String str) {
        super.m252a(fragmentManager, str);
    }

    public Dialog m8340c(Bundle bundle) {
        if (this.aj == null) {
            m256b(false);
        }
        return this.aj;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.ak != null) {
            this.ak.onCancel(dialogInterface);
        }
    }
}
