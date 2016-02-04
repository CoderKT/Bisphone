package app.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    public static NetworkInfo m7036a(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean m7038b(Context context) {
        NetworkInfo a = m7036a(context);
        return a != null && a.isConnected();
    }

    public static boolean m7039c(Context context) {
        NetworkInfo a = m7036a(context);
        if (a != null && a.isConnected() && a.getType() == 1) {
            return true;
        }
        return false;
    }

    public static boolean m7040d(Context context) {
        NetworkInfo a = m7036a(context);
        return a != null && a.isConnected() && a.getType() == 0;
    }

    public static boolean m7037a(Context context, boolean z) {
        if (context == null) {
            return false;
        }
        String string;
        if (z) {
            string = context.getResources().getString(2131296927);
        } else {
            string = context.getResources().getString(2131296938);
        }
        string = SharedPreferencesUtil.m7052a(string);
        if (string == null) {
            return false;
        }
        boolean z2;
        if (!(m7040d(context) && string.equals("0")) && ((!m7039c(context) || string.equals("2")) && m7038b(context))) {
            z2 = false;
        } else {
            z2 = true;
        }
        return z2;
    }
}
