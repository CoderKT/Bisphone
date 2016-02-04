package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.view.View;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

public class ViewCompat {
    static final ViewCompatImpl f449a;

    interface ViewCompatImpl {
        int m1024a(int i, int i2, int i3);

        int m1025a(View view);

        WindowInsetsCompat m1026a(View view, WindowInsetsCompat windowInsetsCompat);

        void m1027a(View view, float f);

        void m1028a(View view, int i, int i2);

        void m1029a(View view, int i, Paint paint);

        void m1030a(View view, ColorStateList colorStateList);

        void m1031a(View view, Mode mode);

        void m1032a(View view, AccessibilityDelegateCompat accessibilityDelegateCompat);

        void m1033a(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener);

        void m1034a(View view, Runnable runnable);

        void m1035a(View view, Runnable runnable, long j);

        void m1036a(View view, boolean z);

        boolean m1037a(View view, int i);

        void m1038b(View view, float f);

        void m1039b(View view, boolean z);

        boolean m1040b(View view);

        boolean m1041b(View view, int i);

        void m1042c(View view, float f);

        void m1043c(View view, int i);

        boolean m1044c(View view);

        void m1045d(View view);

        void m1046d(View view, float f);

        int m1047e(View view);

        float m1048f(View view);

        int m1049g(View view);

        int m1050h(View view);

        int m1051i(View view);

        boolean m1052j(View view);

        float m1053k(View view);

        float m1054l(View view);

        int m1055m(View view);

        int m1056n(View view);

        ViewPropertyAnimatorCompat m1057o(View view);

        int m1058p(View view);

        void m1059q(View view);

        void m1060r(View view);

        void m1061s(View view);

        boolean m1062t(View view);

        boolean m1063u(View view);

        boolean m1064v(View view);
    }

    class BaseViewCompatImpl implements ViewCompatImpl {
        WeakHashMap<View, ViewPropertyAnimatorCompat> f446a;

        BaseViewCompatImpl() {
            this.f446a = null;
        }

        public boolean m1081a(View view, int i) {
            return (view instanceof ScrollingView) && m1065a((ScrollingView) view, i);
        }

        public boolean m1085b(View view, int i) {
            return (view instanceof ScrollingView) && m1066b((ScrollingView) view, i);
        }

        public int m1068a(View view) {
            return 2;
        }

        public void m1076a(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        }

        public boolean m1084b(View view) {
            return false;
        }

        public boolean m1088c(View view) {
            return false;
        }

        public void m1089d(View view) {
            view.invalidate();
        }

        public void m1078a(View view, Runnable runnable) {
            view.postDelayed(runnable, m1069a());
        }

        public void m1079a(View view, Runnable runnable, long j) {
            view.postDelayed(runnable, m1069a() + j);
        }

        long m1069a() {
            return 10;
        }

        public int m1091e(View view) {
            return 0;
        }

        public void m1087c(View view, int i) {
        }

        public float m1092f(View view) {
            return 1.0f;
        }

        public void m1073a(View view, int i, Paint paint) {
        }

        public int m1093g(View view) {
            return 0;
        }

        public int m1094h(View view) {
            return 0;
        }

        public int m1067a(int i, int i2, int i3) {
            return View.resolveSize(i, i2);
        }

        public int m1095i(View view) {
            return 0;
        }

        public boolean m1096j(View view) {
            return true;
        }

        public float m1097k(View view) {
            return 0.0f;
        }

        public float m1098l(View view) {
            return 0.0f;
        }

        public int m1099m(View view) {
            return ViewCompatBase.m1197b(view);
        }

        public int m1100n(View view) {
            return ViewCompatBase.m1198c(view);
        }

        public ViewPropertyAnimatorCompat m1101o(View view) {
            return new ViewPropertyAnimatorCompat(view);
        }

        public void m1071a(View view, float f) {
        }

        public void m1082b(View view, float f) {
        }

        public void m1086c(View view, float f) {
        }

        public int m1102p(View view) {
            return 0;
        }

        public void m1103q(View view) {
        }

        public void m1090d(View view, float f) {
        }

        public void m1104r(View view) {
        }

        public void m1077a(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        }

        public WindowInsetsCompat m1070a(View view, WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        public void m1080a(View view, boolean z) {
        }

        public void m1083b(View view, boolean z) {
        }

        public void m1074a(View view, ColorStateList colorStateList) {
            ViewCompatBase.m1194a(view, colorStateList);
        }

        public void m1075a(View view, Mode mode) {
            ViewCompatBase.m1195a(view, mode);
        }

        private boolean m1065a(ScrollingView scrollingView, int i) {
            int computeHorizontalScrollOffset = scrollingView.computeHorizontalScrollOffset();
            int computeHorizontalScrollRange = scrollingView.computeHorizontalScrollRange() - scrollingView.computeHorizontalScrollExtent();
            if (computeHorizontalScrollRange == 0) {
                return false;
            }
            if (i < 0) {
                if (computeHorizontalScrollOffset <= 0) {
                    return false;
                }
                return true;
            } else if (computeHorizontalScrollOffset >= computeHorizontalScrollRange - 1) {
                return false;
            } else {
                return true;
            }
        }

        private boolean m1066b(ScrollingView scrollingView, int i) {
            int computeVerticalScrollOffset = scrollingView.computeVerticalScrollOffset();
            int computeVerticalScrollRange = scrollingView.computeVerticalScrollRange() - scrollingView.computeVerticalScrollExtent();
            if (computeVerticalScrollRange == 0) {
                return false;
            }
            if (i < 0) {
                if (computeVerticalScrollOffset <= 0) {
                    return false;
                }
                return true;
            } else if (computeVerticalScrollOffset >= computeVerticalScrollRange - 1) {
                return false;
            } else {
                return true;
            }
        }

        public void m1105s(View view) {
            if (view instanceof NestedScrollingChild) {
                ((NestedScrollingChild) view).stopNestedScroll();
            }
        }

        public boolean m1106t(View view) {
            return ViewCompatBase.m1196a(view);
        }

        public boolean m1107u(View view) {
            return ViewCompatBase.m1199d(view);
        }

        public boolean m1108v(View view) {
            return false;
        }

        public void m1072a(View view, int i, int i2) {
        }
    }

    class EclairMr1ViewCompatImpl extends BaseViewCompatImpl {
        EclairMr1ViewCompatImpl() {
        }
    }

    class GBViewCompatImpl extends EclairMr1ViewCompatImpl {
        GBViewCompatImpl() {
        }

        public int m1109a(View view) {
            return ViewCompatGingerbread.m1200a(view);
        }
    }

    class HCViewCompatImpl extends GBViewCompatImpl {
        HCViewCompatImpl() {
        }

        long m1111a() {
            return ViewCompatHC.m1203a();
        }

        public float m1118f(View view) {
            return ViewCompatHC.m1201a(view);
        }

        public void m1113a(View view, int i, Paint paint) {
            ViewCompatHC.m1205a(view, i, paint);
        }

        public int m1119g(View view) {
            return ViewCompatHC.m1207b(view);
        }

        public int m1110a(int i, int i2, int i3) {
            return ViewCompatHC.m1202a(i, i2, i3);
        }

        public int m1120i(View view) {
            return ViewCompatHC.m1210c(view);
        }

        public float m1121k(View view) {
            return ViewCompatHC.m1212d(view);
        }

        public float m1122l(View view) {
            return ViewCompatHC.m1213e(view);
        }

        public void m1112a(View view, float f) {
            ViewCompatHC.m1204a(view, f);
        }

        public void m1115b(View view, float f) {
            ViewCompatHC.m1208b(view, f);
        }

        public void m1117c(View view, float f) {
            ViewCompatHC.m1211c(view, f);
        }

        public void m1123r(View view) {
            ViewCompatHC.m1214f(view);
        }

        public void m1114a(View view, boolean z) {
            ViewCompatHC.m1206a(view, z);
        }

        public void m1116b(View view, boolean z) {
            ViewCompatHC.m1209b(view, z);
        }
    }

    class ICSViewCompatImpl extends HCViewCompatImpl {
        static Field f447b;
        static boolean f448c;

        ICSViewCompatImpl() {
        }

        static {
            f448c = false;
        }

        public boolean m1125a(View view, int i) {
            return ViewCompatICS.m1216a(view, i);
        }

        public boolean m1127b(View view, int i) {
            return ViewCompatICS.m1217b(view, i);
        }

        public void m1124a(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
            ViewCompatICS.m1215a(view, accessibilityDelegateCompat == null ? null : accessibilityDelegateCompat.m862a());
        }

        public boolean m1126b(View view) {
            boolean z = true;
            if (f448c) {
                return false;
            }
            if (f447b == null) {
                try {
                    f447b = View.class.getDeclaredField("mAccessibilityDelegate");
                    f447b.setAccessible(true);
                } catch (Throwable th) {
                    f448c = true;
                    return false;
                }
            }
            try {
                if (f447b.get(view) == null) {
                    z = false;
                }
                return z;
            } catch (Throwable th2) {
                f448c = true;
                return false;
            }
        }

        public ViewPropertyAnimatorCompat m1128o(View view) {
            if (this.a == null) {
                this.a = new WeakHashMap();
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat) this.a.get(view);
            if (viewPropertyAnimatorCompat != null) {
                return viewPropertyAnimatorCompat;
            }
            viewPropertyAnimatorCompat = new ViewPropertyAnimatorCompat(view);
            this.a.put(view, viewPropertyAnimatorCompat);
            return viewPropertyAnimatorCompat;
        }
    }

    class ICSMr1ViewCompatImpl extends ICSViewCompatImpl {
        ICSMr1ViewCompatImpl() {
        }

        public boolean m1129v(View view) {
            return ViewCompatICSMr1.m1218a(view);
        }
    }

    class JBViewCompatImpl extends ICSMr1ViewCompatImpl {
        JBViewCompatImpl() {
        }

        public boolean m1133c(View view) {
            return ViewCompatJB.m1222a(view);
        }

        public void m1134d(View view) {
            ViewCompatJB.m1223b(view);
        }

        public void m1130a(View view, Runnable runnable) {
            ViewCompatJB.m1220a(view, runnable);
        }

        public void m1131a(View view, Runnable runnable, long j) {
            ViewCompatJB.m1221a(view, runnable, j);
        }

        public int m1135e(View view) {
            return ViewCompatJB.m1224c(view);
        }

        public void m1132c(View view, int i) {
            if (i == 4) {
                i = 2;
            }
            ViewCompatJB.m1219a(view, i);
        }

        public int m1137m(View view) {
            return ViewCompatJB.m1225d(view);
        }

        public int m1138n(View view) {
            return ViewCompatJB.m1226e(view);
        }

        public void m1139q(View view) {
            ViewCompatJB.m1227f(view);
        }

        public boolean m1136j(View view) {
            return ViewCompatJB.m1228g(view);
        }
    }

    class JbMr1ViewCompatImpl extends JBViewCompatImpl {
        JbMr1ViewCompatImpl() {
        }

        public int m1140h(View view) {
            return ViewCompatJellybeanMr1.m1229a(view);
        }

        public int m1141p(View view) {
            return ViewCompatJellybeanMr1.m1230b(view);
        }
    }

    class JbMr2ViewCompatImpl extends JbMr1ViewCompatImpl {
        JbMr2ViewCompatImpl() {
        }
    }

    class KitKatViewCompatImpl extends JbMr2ViewCompatImpl {
        KitKatViewCompatImpl() {
        }

        public void m1142c(View view, int i) {
            ViewCompatJB.m1219a(view, i);
        }

        public boolean m1143t(View view) {
            return ViewCompatKitKat.m1231a(view);
        }

        public boolean m1144u(View view) {
            return ViewCompatKitKat.m1232b(view);
        }
    }

    class LollipopViewCompatImpl extends KitKatViewCompatImpl {
        LollipopViewCompatImpl() {
        }

        public void m1150q(View view) {
            ViewCompatLollipop.m1234a(view);
        }

        public void m1149d(View view, float f) {
            ViewCompatLollipop.m1235a(view, f);
        }

        public void m1148a(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            ViewCompatLollipop.m1238a(view, onApplyWindowInsetsListener);
        }

        public void m1151s(View view) {
            ViewCompatLollipop.m1239b(view);
        }

        public void m1146a(View view, ColorStateList colorStateList) {
            ViewCompatLollipop.m1236a(view, colorStateList);
        }

        public void m1147a(View view, Mode mode) {
            ViewCompatLollipop.m1237a(view, mode);
        }

        public WindowInsetsCompat m1145a(View view, WindowInsetsCompat windowInsetsCompat) {
            return ViewCompatLollipop.m1233a(view, windowInsetsCompat);
        }
    }

    class MarshmallowViewCompatImpl extends LollipopViewCompatImpl {
        MarshmallowViewCompatImpl() {
        }

        public void m1152a(View view, int i, int i2) {
            ViewCompatMarshmallow.m1240a(view, i, i2);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 23) {
            f449a = new MarshmallowViewCompatImpl();
        } else if (i >= 21) {
            f449a = new LollipopViewCompatImpl();
        } else if (i >= 19) {
            f449a = new KitKatViewCompatImpl();
        } else if (i >= 17) {
            f449a = new JbMr1ViewCompatImpl();
        } else if (i >= 16) {
            f449a = new JBViewCompatImpl();
        } else if (i >= 15) {
            f449a = new ICSMr1ViewCompatImpl();
        } else if (i >= 14) {
            f449a = new ICSViewCompatImpl();
        } else if (i >= 11) {
            f449a = new HCViewCompatImpl();
        } else if (i >= 9) {
            f449a = new GBViewCompatImpl();
        } else if (i >= 7) {
            f449a = new EclairMr1ViewCompatImpl();
        } else {
            f449a = new BaseViewCompatImpl();
        }
    }

    public static boolean m1166a(View view, int i) {
        return f449a.m1037a(view, i);
    }

    public static boolean m1170b(View view, int i) {
        return f449a.m1041b(view, i);
    }

    public static int m1154a(View view) {
        return f449a.m1025a(view);
    }

    public static void m1161a(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        f449a.m1032a(view, accessibilityDelegateCompat);
    }

    public static boolean m1169b(View view) {
        return f449a.m1040b(view);
    }

    public static boolean m1173c(View view) {
        return f449a.m1044c(view);
    }

    public static void m1174d(View view) {
        f449a.m1045d(view);
    }

    public static void m1163a(View view, Runnable runnable) {
        f449a.m1034a(view, runnable);
    }

    public static void m1164a(View view, Runnable runnable, long j) {
        f449a.m1035a(view, runnable, j);
    }

    public static int m1176e(View view) {
        return f449a.m1047e(view);
    }

    public static void m1172c(View view, int i) {
        f449a.m1043c(view, i);
    }

    public static float m1177f(View view) {
        return f449a.m1048f(view);
    }

    public static void m1158a(View view, int i, Paint paint) {
        f449a.m1029a(view, i, paint);
    }

    public static int m1178g(View view) {
        return f449a.m1049g(view);
    }

    public static int m1179h(View view) {
        return f449a.m1050h(view);
    }

    public static int m1153a(int i, int i2, int i3) {
        return f449a.m1024a(i, i2, i3);
    }

    public static int m1180i(View view) {
        return f449a.m1051i(view);
    }

    public static float m1181j(View view) {
        return f449a.m1053k(view);
    }

    public static float m1182k(View view) {
        return f449a.m1054l(view);
    }

    public static int m1183l(View view) {
        return f449a.m1055m(view);
    }

    public static int m1184m(View view) {
        return f449a.m1056n(view);
    }

    public static ViewPropertyAnimatorCompat m1185n(View view) {
        return f449a.m1057o(view);
    }

    public static void m1156a(View view, float f) {
        f449a.m1027a(view, f);
    }

    public static void m1167b(View view, float f) {
        f449a.m1038b(view, f);
    }

    public static void m1171c(View view, float f) {
        f449a.m1042c(view, f);
    }

    public static void m1175d(View view, float f) {
        f449a.m1046d(view, f);
    }

    public static int m1186o(View view) {
        return f449a.m1058p(view);
    }

    public static void m1187p(View view) {
        f449a.m1059q(view);
    }

    public static void m1188q(View view) {
        f449a.m1060r(view);
    }

    public static void m1162a(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        f449a.m1033a(view, onApplyWindowInsetsListener);
    }

    public static WindowInsetsCompat m1155a(View view, WindowInsetsCompat windowInsetsCompat) {
        return f449a.m1026a(view, windowInsetsCompat);
    }

    public static void m1165a(View view, boolean z) {
        f449a.m1036a(view, z);
    }

    public static void m1168b(View view, boolean z) {
        f449a.m1039b(view, z);
    }

    public static boolean m1189r(View view) {
        return f449a.m1052j(view);
    }

    public static void m1159a(View view, ColorStateList colorStateList) {
        f449a.m1030a(view, colorStateList);
    }

    public static void m1160a(View view, Mode mode) {
        f449a.m1031a(view, mode);
    }

    public static void m1190s(View view) {
        f449a.m1061s(view);
    }

    public static boolean m1191t(View view) {
        return f449a.m1062t(view);
    }

    public static boolean m1192u(View view) {
        return f449a.m1063u(view);
    }

    public static boolean m1193v(View view) {
        return f449a.m1064v(view);
    }

    public static void m1157a(View view, int i, int i2) {
        f449a.m1028a(view, i, i2);
    }
}
