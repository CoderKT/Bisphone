package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertController.AlertParams;
import android.support.v7.appcompat.C0057R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
    private AlertController f750a;

    public class Builder {
        private final AlertParams f747a;
        private int f748b;

        public Builder(Context context) {
            this(context, AlertDialog.m2002a(context, 0));
        }

        public Builder(Context context, int i) {
            this.f747a = new AlertParams(new ContextThemeWrapper(context, AlertDialog.m2002a(context, i)));
            this.f748b = i;
        }

        public Context m1973a() {
            return this.f747a.f680a;
        }

        public Builder m1974a(int i) {
            this.f747a.f685f = this.f747a.f680a.getText(i);
            return this;
        }

        public Builder m1980a(CharSequence charSequence) {
            this.f747a.f685f = charSequence;
            return this;
        }

        public Builder m1978a(View view) {
            this.f747a.f686g = view;
            return this;
        }

        public Builder m1984b(int i) {
            this.f747a.f687h = this.f747a.f680a.getText(i);
            return this;
        }

        public Builder m1986b(CharSequence charSequence) {
            this.f747a.f687h = charSequence;
            return this;
        }

        public Builder m1989c(int i) {
            this.f747a.f682c = i;
            return this;
        }

        public Builder m1977a(Drawable drawable) {
            this.f747a.f683d = drawable;
            return this;
        }

        public Builder m1975a(int i, OnClickListener onClickListener) {
            this.f747a.f688i = this.f747a.f680a.getText(i);
            this.f747a.f689j = onClickListener;
            return this;
        }

        public Builder m1981a(CharSequence charSequence, OnClickListener onClickListener) {
            this.f747a.f688i = charSequence;
            this.f747a.f689j = onClickListener;
            return this;
        }

        public Builder m1985b(int i, OnClickListener onClickListener) {
            this.f747a.f690k = this.f747a.f680a.getText(i);
            this.f747a.f691l = onClickListener;
            return this;
        }

        public Builder m1987b(CharSequence charSequence, OnClickListener onClickListener) {
            this.f747a.f690k = charSequence;
            this.f747a.f691l = onClickListener;
            return this;
        }

        public Builder m1990c(int i, OnClickListener onClickListener) {
            this.f747a.f692m = this.f747a.f680a.getText(i);
            this.f747a.f693n = onClickListener;
            return this;
        }

        public Builder m1991c(CharSequence charSequence, OnClickListener onClickListener) {
            this.f747a.f692m = charSequence;
            this.f747a.f693n = onClickListener;
            return this;
        }

        public Builder m1982a(boolean z) {
            this.f747a.f694o = z;
            return this;
        }

        public Builder m1976a(OnKeyListener onKeyListener) {
            this.f747a.f697r = onKeyListener;
            return this;
        }

        public Builder m1983a(CharSequence[] charSequenceArr, OnClickListener onClickListener) {
            this.f747a.f698s = charSequenceArr;
            this.f747a.f700u = onClickListener;
            return this;
        }

        public Builder m1979a(ListAdapter listAdapter, OnClickListener onClickListener) {
            this.f747a.f699t = listAdapter;
            this.f747a.f700u = onClickListener;
            return this;
        }

        public AlertDialog m1988b() {
            AlertDialog alertDialog = new AlertDialog(this.f747a.f680a, this.f748b, false);
            this.f747a.m1931a(alertDialog.f750a);
            alertDialog.setCancelable(this.f747a.f694o);
            if (this.f747a.f694o) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f747a.f695p);
            alertDialog.setOnDismissListener(this.f747a.f696q);
            if (this.f747a.f697r != null) {
                alertDialog.setOnKeyListener(this.f747a.f697r);
            }
            return alertDialog;
        }

        public AlertDialog m1992c() {
            AlertDialog b = m1988b();
            b.show();
            return b;
        }
    }

    AlertDialog(Context context, int i, boolean z) {
        super(context, m2002a(context, i));
        this.f750a = new AlertController(getContext(), this, getWindow());
    }

    static int m2002a(Context context, int i) {
        if (i >= 16777216) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0057R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f750a.m1965a(charSequence);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f750a.m1960a();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f750a.m1966a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f750a.m1970b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }
}
