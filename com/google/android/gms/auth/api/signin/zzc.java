package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzc implements Creator<GoogleSignInAccount> {
    static void m8297a(GoogleSignInAccount googleSignInAccount, Parcel parcel, int i) {
        int a = zzb.m8455a(parcel);
        zzb.m8460a(parcel, 1, googleSignInAccount.f5626b);
        zzb.m8466a(parcel, 2, googleSignInAccount.m8289a(), false);
        zzb.m8466a(parcel, 3, googleSignInAccount.m8290b(), false);
        zzb.m8466a(parcel, 4, googleSignInAccount.m8291c(), false);
        zzb.m8466a(parcel, 5, googleSignInAccount.m8292d(), false);
        zzb.m8464a(parcel, 6, googleSignInAccount.m8293e(), i, false);
        zzb.m8466a(parcel, 7, googleSignInAccount.m8294f(), false);
        zzb.m8461a(parcel, 8, googleSignInAccount.m8295g());
        zzb.m8456a(parcel, a);
    }

    public GoogleSignInAccount m8298a(Parcel parcel) {
        String str = null;
        int b = zza.m8438b(parcel);
        int i = 0;
        long j = 0;
        Uri uri = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        while (parcel.dataPosition() < b) {
            int a = zza.m8432a(parcel);
            switch (zza.m8431a(a)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    i = zza.m8445f(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    str5 = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    str4 = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    str3 = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    str2 = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    uri = (Uri) zza.m8434a(parcel, a, Uri.CREATOR);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    str = zza.m8450k(parcel, a);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    j = zza.m8447h(parcel, a);
                    break;
                default:
                    zza.m8439b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() == b) {
            return new GoogleSignInAccount(i, str5, str4, str3, str2, uri, str, j);
        }
        throw new zza.zza("Overread allowed size end=" + b, parcel);
    }

    public GoogleSignInAccount[] m8299a(int i) {
        return new GoogleSignInAccount[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8298a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m8299a(i);
    }
}
