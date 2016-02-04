package app.util;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.ClipboardManager;

public class ClipboardUtil {
    @SuppressLint({"NewApi"})
    public static void m7007a(Context context, String str) {
        if (VERSION.SDK_INT < 11) {
            ((ClipboardManager) context.getSystemService("clipboard")).setText(str);
        } else {
            ((android.content.ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("com.bistalk.bisphone", str));
        }
    }

    public static String m7006a(Context context) {
        android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) context.getSystemService("clipboard");
        if (!clipboardManager.hasPrimaryClip()) {
            return null;
        }
        if (clipboardManager.getPrimaryClipDescription().hasMimeType("text/plain")) {
            return clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
        }
        return null;
    }
}
