package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzg;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    private T f5978a;
    private Bundle f5979b;
    private LinkedList<zza> f5980c;
    private final zzf<T> f5981d;

    /* renamed from: com.google.android.gms.dynamic.zza.1 */
    class C08481 implements zzf<T> {
        final /* synthetic */ zza f5963a;

        C08481(zza com_google_android_gms_dynamic_zza) {
            this.f5963a = com_google_android_gms_dynamic_zza;
        }

        public void m8992a(T t) {
            this.f5963a.f5978a = t;
            Iterator it = this.f5963a.f5980c.iterator();
            while (it.hasNext()) {
                ((zza) it.next()).m8994a(this.f5963a.f5978a);
            }
            this.f5963a.f5980c.clear();
            this.f5963a.f5979b = null;
        }
    }

    interface zza {
        int m8993a();

        void m8994a(LifecycleDelegate lifecycleDelegate);
    }

    /* renamed from: com.google.android.gms.dynamic.zza.2 */
    class C08492 implements zza {
        final /* synthetic */ Activity f5964a;
        final /* synthetic */ Bundle f5965b;
        final /* synthetic */ Bundle f5966c;
        final /* synthetic */ zza f5967d;

        C08492(zza com_google_android_gms_dynamic_zza, Activity activity, Bundle bundle, Bundle bundle2) {
            this.f5967d = com_google_android_gms_dynamic_zza;
            this.f5964a = activity;
            this.f5965b = bundle;
            this.f5966c = bundle2;
        }

        public int m8995a() {
            return 0;
        }

        public void m8996a(LifecycleDelegate lifecycleDelegate) {
            this.f5967d.f5978a.m8984a(this.f5964a, this.f5965b, this.f5966c);
        }
    }

    /* renamed from: com.google.android.gms.dynamic.zza.3 */
    class C08503 implements zza {
        final /* synthetic */ Bundle f5968a;
        final /* synthetic */ zza f5969b;

        C08503(zza com_google_android_gms_dynamic_zza, Bundle bundle) {
            this.f5969b = com_google_android_gms_dynamic_zza;
            this.f5968a = bundle;
        }

        public int m8997a() {
            return 1;
        }

        public void m8998a(LifecycleDelegate lifecycleDelegate) {
            this.f5969b.f5978a.m8985a(this.f5968a);
        }
    }

    /* renamed from: com.google.android.gms.dynamic.zza.4 */
    class C08514 implements zza {
        final /* synthetic */ FrameLayout f5970a;
        final /* synthetic */ LayoutInflater f5971b;
        final /* synthetic */ ViewGroup f5972c;
        final /* synthetic */ Bundle f5973d;
        final /* synthetic */ zza f5974e;

        C08514(zza com_google_android_gms_dynamic_zza, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            this.f5974e = com_google_android_gms_dynamic_zza;
            this.f5970a = frameLayout;
            this.f5971b = layoutInflater;
            this.f5972c = viewGroup;
            this.f5973d = bundle;
        }

        public int m8999a() {
            return 2;
        }

        public void m9000a(LifecycleDelegate lifecycleDelegate) {
            this.f5970a.removeAllViews();
            this.f5970a.addView(this.f5974e.f5978a.m8982a(this.f5971b, this.f5972c, this.f5973d));
        }
    }

    /* renamed from: com.google.android.gms.dynamic.zza.5 */
    final class C08525 implements OnClickListener {
        final /* synthetic */ Context f5975a;
        final /* synthetic */ int f5976b;

        C08525(Context context, int i) {
            this.f5975a = context;
            this.f5976b = i;
        }

        public void onClick(View view) {
            this.f5975a.startActivity(GooglePlayServicesUtil.m8319a(this.f5976b));
        }
    }

    /* renamed from: com.google.android.gms.dynamic.zza.7 */
    class C08537 implements zza {
        final /* synthetic */ zza f5977a;

        C08537(zza com_google_android_gms_dynamic_zza) {
            this.f5977a = com_google_android_gms_dynamic_zza;
        }

        public int m9001a() {
            return 5;
        }

        public void m9002a(LifecycleDelegate lifecycleDelegate) {
            this.f5977a.f5978a.m8983a();
        }
    }

    public zza() {
        this.f5981d = new C08481(this);
    }

    private void m9006a(int i) {
        while (!this.f5980c.isEmpty() && ((zza) this.f5980c.getLast()).m8993a() >= i) {
            this.f5980c.removeLast();
        }
    }

    private void m9007a(Bundle bundle, zza com_google_android_gms_dynamic_zza_zza) {
        if (this.f5978a != null) {
            com_google_android_gms_dynamic_zza_zza.m8994a(this.f5978a);
            return;
        }
        if (this.f5980c == null) {
            this.f5980c = new LinkedList();
        }
        this.f5980c.add(com_google_android_gms_dynamic_zza_zza);
        if (bundle != null) {
            if (this.f5979b == null) {
                this.f5979b = (Bundle) bundle.clone();
            } else {
                this.f5979b.putAll(bundle);
            }
        }
        m9015a(this.f5981d);
    }

    public static void m9009b(FrameLayout frameLayout) {
        Context context = frameLayout.getContext();
        int a = GooglePlayServicesUtil.m8318a(context);
        CharSequence a2 = zzg.m8547a(context, a, GooglePlayServicesUtil.m8335d(context));
        CharSequence b = zzg.m8548b(context, a);
        View linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        View textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(a2);
        linearLayout.addView(textView);
        if (b != null) {
            View button = new Button(context);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(b);
            linearLayout.addView(button);
            button.setOnClickListener(new C08525(context, a));
        }
    }

    public View m9010a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        m9007a(bundle, new C08514(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.f5978a == null) {
            m9014a(frameLayout);
        }
        return frameLayout;
    }

    public T m9011a() {
        return this.f5978a;
    }

    public void m9012a(Activity activity, Bundle bundle, Bundle bundle2) {
        m9007a(bundle2, new C08492(this, activity, bundle, bundle2));
    }

    public void m9013a(Bundle bundle) {
        m9007a(bundle, new C08503(this, bundle));
    }

    protected void m9014a(FrameLayout frameLayout) {
        m9009b(frameLayout);
    }

    protected abstract void m9015a(zzf<T> com_google_android_gms_dynamic_zzf_T);

    public void m9016b() {
        m9007a(null, new C08537(this));
    }

    public void m9017b(Bundle bundle) {
        if (this.f5978a != null) {
            this.f5978a.m8987b(bundle);
        } else if (this.f5979b != null) {
            bundle.putAll(this.f5979b);
        }
    }

    public void m9018c() {
        if (this.f5978a != null) {
            this.f5978a.m8986b();
        } else {
            m9006a(5);
        }
    }

    public void m9019d() {
        if (this.f5978a != null) {
            this.f5978a.m8988c();
        } else {
            m9006a(2);
        }
    }

    public void m9020e() {
        if (this.f5978a != null) {
            this.f5978a.m8989d();
        } else {
            m9006a(1);
        }
    }

    public void m9021f() {
        if (this.f5978a != null) {
            this.f5978a.m8990e();
        }
    }
}
