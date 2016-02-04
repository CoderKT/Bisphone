package com.google.android.gms.common.api;

import se.emilsjolander.stickylistheaders.C1128R;

public class CommonStatusCodes {
    public static String m8363a(int i) {
        switch (i) {
            case -1:
                return "SUCCESS_CACHE";
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return "SUCCESS";
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return "SERVICE_MISSING";
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return "SERVICE_DISABLED";
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return "SIGN_IN_REQUIRED";
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return "INVALID_ACCOUNT";
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return "RESOLUTION_REQUIRED";
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return "NETWORK_ERROR";
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return "INTERNAL_ERROR";
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return "SERVICE_INVALID";
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return "DEVELOPER_ERROR";
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                return "LICENSE_CHECK_FAILED";
            case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                return "ERROR";
            case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                return "INTERRUPTED";
            case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                return "TIMEOUT";
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                return "CANCELED";
            case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                return "API_NOT_CONNECTED";
            case 3000:
                return "AUTH_API_INVALID_CREDENTIALS";
            case 3001:
                return "AUTH_API_ACCESS_FORBIDDEN";
            case 3002:
                return "AUTH_API_CLIENT_ERROR";
            case 3003:
                return "AUTH_API_SERVER_ERROR";
            case 3004:
                return "AUTH_TOKEN_ERROR";
            case 3005:
                return "AUTH_URL_RESOLUTION";
            default:
                return "unknown status code: " + i;
        }
    }
}
