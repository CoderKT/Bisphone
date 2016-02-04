package com.google.android.gms.maps.internal;

import se.emilsjolander.stickylistheaders.C1128R;

public final class zza {
    public static byte m9995a(Boolean bool) {
        return bool != null ? bool.booleanValue() ? (byte) 1 : (byte) 0 : (byte) -1;
    }

    public static Boolean m9996a(byte b) {
        switch (b) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return Boolean.FALSE;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return Boolean.TRUE;
            default:
                return null;
        }
    }
}
