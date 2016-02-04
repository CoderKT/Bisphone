package app.common;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

@SuppressLint({"ValidFragment"})
public class CustomDialogFragment extends DialogFragment {
    private String f1985a;
    private String f1986b;
    private String f1987c;
    private String f1988d;
    private OnClickListener f1989e;
    private OnClickListener f1990f;
    private boolean f1991g;

    public class Builder {
        private String f1978a;
        private String f1979b;
        private String f1980c;
        private String f1981d;
        private OnClickListener f1982e;
        private OnClickListener f1983f;
        private boolean f1984g;

        public Builder() {
            this.f1984g = false;
        }

        public Builder m3969a(String str) {
            this.f1978a = str;
            return this;
        }

        public Builder m3973b(String str) {
            this.f1979b = str;
            return this;
        }

        public Builder m3970a(String str, OnClickListener onClickListener) {
            this.f1982e = onClickListener;
            this.f1980c = str;
            return this;
        }

        public Builder m3974b(String str, OnClickListener onClickListener) {
            this.f1983f = onClickListener;
            this.f1981d = str;
            return this;
        }

        public Builder m3971a(boolean z) {
            this.f1984g = z;
            return this;
        }

        public CustomDialogFragment m3972a() {
            return new CustomDialogFragment();
        }
    }

    private CustomDialogFragment(Builder builder) {
        this.f1985a = builder.f1978a;
        this.f1986b = builder.f1979b;
        this.f1987c = builder.f1980c;
        this.f1988d = builder.f1981d;
        this.f1989e = builder.f1982e;
        this.f1990f = builder.f1983f;
        this.f1991g = builder.f1984g;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity(), 2131558535);
        builder.m1980a(this.f1985a);
        builder.m1986b(this.f1986b);
        if (this.f1987c != null) {
            builder.m1981a(this.f1987c, this.f1989e);
        }
        if (this.f1988d != null) {
            builder.m1987b(this.f1988d, this.f1990f);
        }
        Dialog b = builder.m1988b();
        b.setCanceledOnTouchOutside(this.f1991g);
        return b;
    }
}
