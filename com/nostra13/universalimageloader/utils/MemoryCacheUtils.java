package com.nostra13.universalimageloader.utils;

import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.util.Comparator;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public final class MemoryCacheUtils {

    /* renamed from: com.nostra13.universalimageloader.utils.MemoryCacheUtils.1 */
    final class C09271 implements Comparator<String> {
        C09271() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m11278a((String) obj, (String) obj2);
        }

        public int m11278a(String str, String str2) {
            return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
        }
    }

    public static String m11279a(String str, ImageSize imageSize) {
        return "_" + imageSize.m11167a() + DataForm.ELEMENT + imageSize.m11170b();
    }

    public static Comparator<String> m11280a() {
        return new C09271();
    }
}
