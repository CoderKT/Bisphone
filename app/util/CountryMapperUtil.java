package app.util;

import android.content.Context;
import java.util.HashMap;

public class CountryMapperUtil {
    public static String m7008a(Context context, String str) {
        HashMap hashMap = new HashMap();
        String[] stringArray = context.getResources().getStringArray(2131361794);
        String[] stringArray2 = context.getResources().getStringArray(2131361796);
        for (int i = 0; i < stringArray.length; i++) {
            hashMap.put(stringArray[i], stringArray2[i]);
        }
        return (String) hashMap.get(str);
    }

    public static String m7009b(Context context, String str) {
        HashMap hashMap = new HashMap();
        String[] stringArray = context.getResources().getStringArray(2131361796);
        String[] stringArray2 = context.getResources().getStringArray(2131361793);
        for (int i = 0; i < stringArray.length; i++) {
            hashMap.put(stringArray[i], stringArray2[i]);
        }
        return (String) hashMap.get(str);
    }
}
