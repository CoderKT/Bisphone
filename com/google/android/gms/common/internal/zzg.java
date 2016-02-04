package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import app.C0110R;
import com.google.android.gms.C0648R;
import com.google.android.gms.internal.zzmq;
import se.emilsjolander.stickylistheaders.C1128R;

public final class zzg {
    public static final String m8546a(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return resources.getString(C0648R.string.common_google_play_services_install_title);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return resources.getString(C0648R.string.common_google_play_services_update_title);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return resources.getString(C0648R.string.common_google_play_services_enable_title);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return resources.getString(C0648R.string.common_google_play_services_invalid_account_title);
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return resources.getString(C0648R.string.common_google_play_services_network_error_title);
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return resources.getString(C0648R.string.common_google_play_services_unsupported_title);
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return resources.getString(C0648R.string.common_google_play_services_sign_in_failed_title);
            case C1128R.styleable.StickyListHeadersListView_android_fastScrollEnabled /*18*/:
                return resources.getString(C0648R.string.common_google_play_services_updating_title);
            case C0110R.styleable.Theme_dialogTheme /*42*/:
                return resources.getString(C0648R.string.common_android_wear_update_title);
            default:
                Log.e("GoogleApiAvailability", "Unexpected error code " + i);
                return null;
        }
    }

    public static String m8547a(Context context, int i, String str) {
        Resources resources = context.getResources();
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (zzmq.m9302a(resources)) {
                    return resources.getString(C0648R.string.common_google_play_services_install_text_tablet, new Object[]{str});
                }
                return resources.getString(C0648R.string.common_google_play_services_install_text_phone, new Object[]{str});
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return resources.getString(C0648R.string.common_google_play_services_update_text, new Object[]{str});
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return resources.getString(C0648R.string.common_google_play_services_enable_text, new Object[]{str});
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return resources.getString(C0648R.string.common_google_play_services_invalid_account_text);
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return resources.getString(C0648R.string.common_google_play_services_network_error_text);
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return resources.getString(C0648R.string.common_google_play_services_unsupported_text, new Object[]{str});
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                return resources.getString(C0648R.string.common_google_play_services_api_unavailable_text, new Object[]{str});
            case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                return resources.getString(C0648R.string.common_google_play_services_sign_in_failed_text);
            case C1128R.styleable.StickyListHeadersListView_android_fastScrollEnabled /*18*/:
                return resources.getString(C0648R.string.common_google_play_services_updating_text, new Object[]{str});
            case C0110R.styleable.Theme_dialogTheme /*42*/:
                return resources.getString(C0648R.string.common_android_wear_update_text, new Object[]{str});
            default:
                return resources.getString(C0648R.string.common_google_play_services_unknown_issue);
        }
    }

    public static String m8548b(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return resources.getString(C0648R.string.common_google_play_services_install_button);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C0110R.styleable.Theme_dialogTheme /*42*/:
                return resources.getString(C0648R.string.common_google_play_services_update_button);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return resources.getString(C0648R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }
}
