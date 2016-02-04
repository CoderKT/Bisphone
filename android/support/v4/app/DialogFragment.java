package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import se.emilsjolander.stickylistheaders.C1128R;

public class DialogFragment extends Fragment implements OnCancelListener, OnDismissListener {
    int f125a;
    int f126b;
    boolean f127c;
    boolean f128d;
    int f129e;
    Dialog f130f;
    boolean f131g;
    boolean f132h;
    boolean f133i;

    public DialogFragment() {
        this.f125a = 0;
        this.f126b = 0;
        this.f127c = true;
        this.f128d = true;
        this.f129e = -1;
    }

    public void m252a(FragmentManager fragmentManager, String str) {
        this.f132h = false;
        this.f133i = true;
        FragmentTransaction a = fragmentManager.m353a();
        a.m113a((Fragment) this, str);
        a.m109a();
    }

    void m253a(boolean z) {
        if (!this.f132h) {
            this.f132h = true;
            this.f133i = false;
            if (this.f130f != null) {
                this.f130f.dismiss();
                this.f130f = null;
            }
            this.f131g = true;
            if (this.f129e >= 0) {
                m231k().m354a(this.f129e, 1);
                this.f129e = -1;
                return;
            }
            FragmentTransaction a = m231k().m353a();
            a.m112a(this);
            if (z) {
                a.m114b();
            } else {
                a.m109a();
            }
        }
    }

    public int m248a() {
        return this.f126b;
    }

    public void m256b(boolean z) {
        this.f128d = z;
    }

    public void m249a(Activity activity) {
        super.m188a(activity);
        if (!this.f133i) {
            this.f132h = false;
        }
    }

    public void m255b() {
        super.m204b();
        if (!this.f133i && !this.f132h) {
            this.f132h = true;
        }
    }

    public void m251a(Bundle bundle) {
        super.m195a(bundle);
        this.f128d = this.H == 0;
        if (bundle != null) {
            this.f125a = bundle.getInt("android:style", 0);
            this.f126b = bundle.getInt("android:theme", 0);
            this.f127c = bundle.getBoolean("android:cancelable", true);
            this.f128d = bundle.getBoolean("android:showsDialog", this.f128d);
            this.f129e = bundle.getInt("android:backStackId", -1);
        }
    }

    public LayoutInflater m254b(Bundle bundle) {
        if (!this.f128d) {
            return super.m202b(bundle);
        }
        this.f130f = m257c(bundle);
        if (this.f130f == null) {
            return (LayoutInflater) this.D.m285g().getSystemService("layout_inflater");
        }
        m250a(this.f130f, this.f125a);
        return (LayoutInflater) this.f130f.getContext().getSystemService("layout_inflater");
    }

    public void m250a(Dialog dialog, int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                dialog.getWindow().addFlags(24);
                break;
            default:
                return;
        }
        dialog.requestWindowFeature(1);
    }

    public Dialog m257c(Bundle bundle) {
        return new Dialog(m227i(), m248a());
    }

    public void onCancel(DialogInterface dialogInterface) {
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.f131g) {
            m253a(true);
        }
    }

    public void m260d(Bundle bundle) {
        super.m213d(bundle);
        if (this.f128d) {
            View p = m237p();
            if (p != null) {
                if (p.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.f130f.setContentView(p);
            }
            this.f130f.setOwnerActivity(m227i());
            this.f130f.setCancelable(this.f127c);
            this.f130f.setOnCancelListener(this);
            this.f130f.setOnDismissListener(this);
            if (bundle != null) {
                Bundle bundle2 = bundle.getBundle("android:savedDialogState");
                if (bundle2 != null) {
                    this.f130f.onRestoreInstanceState(bundle2);
                }
            }
        }
    }

    public void m258c() {
        super.m208c();
        if (this.f130f != null) {
            this.f131g = false;
            this.f130f.show();
        }
    }

    public void m262e(Bundle bundle) {
        super.m218e(bundle);
        if (this.f130f != null) {
            Bundle onSaveInstanceState = this.f130f.onSaveInstanceState();
            if (onSaveInstanceState != null) {
                bundle.putBundle("android:savedDialogState", onSaveInstanceState);
            }
        }
        if (this.f125a != 0) {
            bundle.putInt("android:style", this.f125a);
        }
        if (this.f126b != 0) {
            bundle.putInt("android:theme", this.f126b);
        }
        if (!this.f127c) {
            bundle.putBoolean("android:cancelable", this.f127c);
        }
        if (!this.f128d) {
            bundle.putBoolean("android:showsDialog", this.f128d);
        }
        if (this.f129e != -1) {
            bundle.putInt("android:backStackId", this.f129e);
        }
    }

    public void m259d() {
        super.m212d();
        if (this.f130f != null) {
            this.f130f.hide();
        }
    }

    public void m261e() {
        super.m217e();
        if (this.f130f != null) {
            this.f131g = true;
            this.f130f.dismiss();
            this.f130f = null;
        }
    }
}
