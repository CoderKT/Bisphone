package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.LruCache;
import android.support.v7.appcompat.C0057R;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class TintManager {
    public static final boolean f1820a;
    private static final Mode f1821b;
    private static final WeakHashMap<Context, TintManager> f1822c;
    private static final ColorFilterLruCache f1823d;
    private static final int[] f1824e;
    private static final int[] f1825f;
    private static final int[] f1826g;
    private static final int[] f1827h;
    private static final int[] f1828i;
    private static final int[] f1829j;
    private final WeakReference<Context> f1830k;
    private SparseArray<ColorStateList> f1831l;
    private ColorStateList f1832m;

    class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int i) {
            super(i);
        }

        PorterDuffColorFilter m3732a(int i, Mode mode) {
            return (PorterDuffColorFilter) m776a((Object) Integer.valueOf(m3731b(i, mode)));
        }

        PorterDuffColorFilter m3733a(int i, Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) m777a(Integer.valueOf(m3731b(i, mode)), porterDuffColorFilter);
        }

        private static int m3731b(int i, Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }
    }

    static {
        f1820a = VERSION.SDK_INT < 21;
        f1821b = Mode.SRC_IN;
        f1822c = new WeakHashMap();
        f1823d = new ColorFilterLruCache(6);
        f1824e = new int[]{C0057R.drawable.abc_textfield_search_default_mtrl_alpha, C0057R.drawable.abc_textfield_default_mtrl_alpha, C0057R.drawable.abc_ab_share_pack_mtrl_alpha};
        f1825f = new int[]{C0057R.drawable.abc_ic_ab_back_mtrl_am_alpha, C0057R.drawable.abc_ic_go_search_api_mtrl_alpha, C0057R.drawable.abc_ic_search_api_mtrl_alpha, C0057R.drawable.abc_ic_commit_search_api_mtrl_alpha, C0057R.drawable.abc_ic_clear_mtrl_alpha, C0057R.drawable.abc_ic_menu_share_mtrl_alpha, C0057R.drawable.abc_ic_menu_copy_mtrl_am_alpha, C0057R.drawable.abc_ic_menu_cut_mtrl_alpha, C0057R.drawable.abc_ic_menu_selectall_mtrl_alpha, C0057R.drawable.abc_ic_menu_paste_mtrl_am_alpha, C0057R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha, C0057R.drawable.abc_ic_voice_search_api_mtrl_alpha};
        f1826g = new int[]{C0057R.drawable.abc_textfield_activated_mtrl_alpha, C0057R.drawable.abc_textfield_search_activated_mtrl_alpha, C0057R.drawable.abc_cab_background_top_mtrl_alpha, C0057R.drawable.abc_text_cursor_material};
        f1827h = new int[]{C0057R.drawable.abc_popup_background_mtrl_mult, C0057R.drawable.abc_cab_background_internal_bg, C0057R.drawable.abc_menu_hardkey_panel_mtrl_mult};
        f1828i = new int[]{C0057R.drawable.abc_edit_text_material, C0057R.drawable.abc_tab_indicator_material, C0057R.drawable.abc_textfield_search_material, C0057R.drawable.abc_spinner_mtrl_am_alpha, C0057R.drawable.abc_spinner_textfield_background_material, C0057R.drawable.abc_ratingbar_full_material, C0057R.drawable.abc_switch_track_mtrl_alpha, C0057R.drawable.abc_switch_thumb_material, C0057R.drawable.abc_btn_default_mtrl_shape, C0057R.drawable.abc_btn_borderless_material};
        f1829j = new int[]{C0057R.drawable.abc_btn_check_material, C0057R.drawable.abc_btn_radio_material};
    }

    public static Drawable m3736a(Context context, int i) {
        if (m3746d(i)) {
            return m3737a(context).m3753a(i);
        }
        return ContextCompat.m98a(context, i);
    }

    public static TintManager m3737a(Context context) {
        TintManager tintManager = (TintManager) f1822c.get(context);
        if (tintManager != null) {
            return tintManager;
        }
        tintManager = new TintManager(context);
        f1822c.put(context, tintManager);
        return tintManager;
    }

    private TintManager(Context context) {
        this.f1830k = new WeakReference(context);
    }

    public Drawable m3753a(int i) {
        return m3754a(i, false);
    }

    public Drawable m3754a(int i, boolean z) {
        Context context = (Context) this.f1830k.get();
        if (context == null) {
            return null;
        }
        Drawable a = ContextCompat.m98a(context, i);
        if (a != null) {
            if (VERSION.SDK_INT >= 8) {
                a = a.mutate();
            }
            ColorStateList c = m3757c(i);
            if (c != null) {
                a = DrawableCompat.m664c(a);
                DrawableCompat.m660a(a, c);
                Mode b = m3756b(i);
                if (b != null) {
                    DrawableCompat.m661a(a, b);
                }
            } else if (i == C0057R.drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{m3753a(C0057R.drawable.abc_cab_background_internal_bg), m3753a(C0057R.drawable.abc_cab_background_top_mtrl_alpha)});
            } else if (i == C0057R.drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) a;
                m3738a(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.m3723a(context, C0057R.attr.colorControlNormal), f1821b);
                m3738a(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.m3723a(context, C0057R.attr.colorControlNormal), f1821b);
                m3738a(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.m3723a(context, C0057R.attr.colorControlActivated), f1821b);
            } else if (!m3755a(i, a) && z) {
                a = null;
            }
        }
        return a;
    }

    public final boolean m3755a(int i, Drawable drawable) {
        Context context = (Context) this.f1830k.get();
        if (context == null) {
            return false;
        }
        int i2;
        Mode mode;
        Object obj;
        int i3;
        Mode mode2 = f1821b;
        if (m3741a(f1824e, i)) {
            i2 = C0057R.attr.colorControlNormal;
            mode = mode2;
            obj = 1;
            i3 = -1;
        } else if (m3741a(f1826g, i)) {
            i2 = C0057R.attr.colorControlActivated;
            mode = mode2;
            r6 = 1;
            i3 = -1;
        } else if (m3741a(f1827h, i)) {
            r6 = 1;
            mode = Mode.MULTIPLY;
            i2 = 16842801;
            i3 = -1;
        } else if (i == C0057R.drawable.abc_list_divider_mtrl_alpha) {
            i2 = 16842800;
            i3 = Math.round(40.8f);
            mode = mode2;
            r6 = 1;
        } else {
            i3 = -1;
            i2 = 0;
            mode = mode2;
            obj = null;
        }
        if (obj == null) {
            return false;
        }
        drawable.setColorFilter(m3734a(ThemeUtils.m3723a(context, i2), mode));
        if (i3 != -1) {
            drawable.setAlpha(i3);
        }
        return true;
    }

    private static boolean m3741a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean m3746d(int i) {
        return m3741a(f1825f, i) || m3741a(f1824e, i) || m3741a(f1826g, i) || m3741a(f1828i, i) || m3741a(f1827h, i) || m3741a(f1829j, i) || i == C0057R.drawable.abc_cab_background_top_material;
    }

    final Mode m3756b(int i) {
        if (i == C0057R.drawable.abc_switch_thumb_material) {
            return Mode.MULTIPLY;
        }
        return null;
    }

    public final ColorStateList m3757c(int i) {
        ColorStateList colorStateList = null;
        Context context = (Context) this.f1830k.get();
        if (context == null) {
            return null;
        }
        if (this.f1831l != null) {
            colorStateList = (ColorStateList) this.f1831l.get(i);
        }
        if (colorStateList != null) {
            return colorStateList;
        }
        ColorStateList f = i == C0057R.drawable.abc_edit_text_material ? m3748f(context) : i == C0057R.drawable.abc_switch_track_mtrl_alpha ? m3745d(context) : i == C0057R.drawable.abc_switch_thumb_material ? m3747e(context) : (i == C0057R.drawable.abc_btn_default_mtrl_shape || i == C0057R.drawable.abc_btn_borderless_material) ? m3749g(context) : i == C0057R.drawable.abc_btn_colored_material ? m3750h(context) : (i == C0057R.drawable.abc_spinner_mtrl_am_alpha || i == C0057R.drawable.abc_spinner_textfield_background_material) ? m3751i(context) : m3741a(f1825f, i) ? ThemeUtils.m3726b(context, C0057R.attr.colorControlNormal) : m3741a(f1828i, i) ? m3742b(context) : m3741a(f1829j, i) ? m3744c(context) : i == C0057R.drawable.abc_seekbar_thumb_material ? m3752j(context) : colorStateList;
        if (f == null) {
            return f;
        }
        if (this.f1831l == null) {
            this.f1831l = new SparseArray();
        }
        this.f1831l.append(i, f);
        return f;
    }

    private ColorStateList m3742b(Context context) {
        if (this.f1832m == null) {
            int a = ThemeUtils.m3723a(context, C0057R.attr.colorControlNormal);
            int a2 = ThemeUtils.m3723a(context, C0057R.attr.colorControlActivated);
            r2 = new int[7][];
            int[] iArr = new int[]{ThemeUtils.f1804a, ThemeUtils.m3727c(context, C0057R.attr.colorControlNormal), ThemeUtils.f1805b, a2, ThemeUtils.f1806c, a2, ThemeUtils.f1807d};
            iArr[3] = a2;
            r2[4] = ThemeUtils.f1808e;
            iArr[4] = a2;
            r2[5] = ThemeUtils.f1809f;
            iArr[5] = a2;
            r2[6] = ThemeUtils.f1811h;
            iArr[6] = a;
            this.f1832m = new ColorStateList(r2, iArr);
        }
        return this.f1832m;
    }

    private ColorStateList m3744c(Context context) {
        r0 = new int[3][];
        int[] iArr = new int[]{ThemeUtils.f1804a, ThemeUtils.m3727c(context, C0057R.attr.colorControlNormal), ThemeUtils.f1808e};
        iArr[1] = ThemeUtils.m3723a(context, C0057R.attr.colorControlActivated);
        r0[2] = ThemeUtils.f1811h;
        iArr[2] = ThemeUtils.m3723a(context, C0057R.attr.colorControlNormal);
        return new ColorStateList(r0, iArr);
    }

    private ColorStateList m3745d(Context context) {
        r0 = new int[3][];
        int[] iArr = new int[]{ThemeUtils.f1804a, ThemeUtils.m3724a(context, 16842800, 0.1f), ThemeUtils.f1808e};
        iArr[1] = ThemeUtils.m3724a(context, C0057R.attr.colorControlActivated, 0.3f);
        r0[2] = ThemeUtils.f1811h;
        iArr[2] = ThemeUtils.m3724a(context, 16842800, 0.3f);
        return new ColorStateList(r0, iArr);
    }

    private ColorStateList m3747e(Context context) {
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList b = ThemeUtils.m3726b(context, C0057R.attr.colorSwitchThumbNormal);
        if (b == null || !b.isStateful()) {
            iArr[0] = ThemeUtils.f1804a;
            iArr2[0] = ThemeUtils.m3727c(context, C0057R.attr.colorSwitchThumbNormal);
            iArr[1] = ThemeUtils.f1808e;
            iArr2[1] = ThemeUtils.m3723a(context, C0057R.attr.colorControlActivated);
            iArr[2] = ThemeUtils.f1811h;
            iArr2[2] = ThemeUtils.m3723a(context, C0057R.attr.colorSwitchThumbNormal);
        } else {
            iArr[0] = ThemeUtils.f1804a;
            iArr2[0] = b.getColorForState(iArr[0], 0);
            iArr[1] = ThemeUtils.f1808e;
            iArr2[1] = ThemeUtils.m3723a(context, C0057R.attr.colorControlActivated);
            iArr[2] = ThemeUtils.f1811h;
            iArr2[2] = b.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    private ColorStateList m3748f(Context context) {
        r0 = new int[3][];
        int[] iArr = new int[]{ThemeUtils.f1804a, ThemeUtils.m3727c(context, C0057R.attr.colorControlNormal), ThemeUtils.f1810g};
        iArr[1] = ThemeUtils.m3723a(context, C0057R.attr.colorControlNormal);
        r0[2] = ThemeUtils.f1811h;
        iArr[2] = ThemeUtils.m3723a(context, C0057R.attr.colorControlActivated);
        return new ColorStateList(r0, iArr);
    }

    private ColorStateList m3749g(Context context) {
        return m3743b(context, C0057R.attr.colorButtonNormal);
    }

    private ColorStateList m3750h(Context context) {
        return m3743b(context, C0057R.attr.colorAccent);
    }

    private ColorStateList m3743b(Context context, int i) {
        r0 = new int[4][];
        r1 = new int[4];
        int a = ThemeUtils.m3723a(context, i);
        int a2 = ThemeUtils.m3723a(context, C0057R.attr.colorControlHighlight);
        r0[0] = ThemeUtils.f1804a;
        r1[0] = ThemeUtils.m3727c(context, C0057R.attr.colorButtonNormal);
        r0[1] = ThemeUtils.f1807d;
        r1[1] = ColorUtils.m622a(a2, a);
        r0[2] = ThemeUtils.f1805b;
        r1[2] = ColorUtils.m622a(a2, a);
        r0[3] = ThemeUtils.f1811h;
        r1[3] = a;
        return new ColorStateList(r0, r1);
    }

    private ColorStateList m3751i(Context context) {
        r0 = new int[3][];
        int[] iArr = new int[]{ThemeUtils.f1804a, ThemeUtils.m3727c(context, C0057R.attr.colorControlNormal), ThemeUtils.f1810g};
        iArr[1] = ThemeUtils.m3723a(context, C0057R.attr.colorControlNormal);
        r0[2] = ThemeUtils.f1811h;
        iArr[2] = ThemeUtils.m3723a(context, C0057R.attr.colorControlActivated);
        return new ColorStateList(r0, iArr);
    }

    private ColorStateList m3752j(Context context) {
        r0 = new int[2][];
        int[] iArr = new int[]{ThemeUtils.f1804a, ThemeUtils.m3727c(context, C0057R.attr.colorControlActivated)};
        r0[1] = ThemeUtils.f1811h;
        iArr[1] = ThemeUtils.m3723a(context, C0057R.attr.colorControlActivated);
        return new ColorStateList(r0, iArr);
    }

    public static void m3739a(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        if (!m3740a(drawable) || drawable.mutate() == drawable) {
            if (tintInfo.f1819d || tintInfo.f1818c) {
                drawable.setColorFilter(m3735a(tintInfo.f1819d ? tintInfo.f1816a : null, tintInfo.f1818c ? tintInfo.f1817b : f1821b, iArr));
            } else {
                drawable.clearColorFilter();
            }
            if (VERSION.SDK_INT <= 10) {
                drawable.invalidateSelf();
                return;
            }
            return;
        }
        Log.d("TintManager", "Mutated drawable is not the same instance as the input.");
    }

    private static boolean m3740a(Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            return true;
        }
        if (drawable instanceof LayerDrawable) {
            return VERSION.SDK_INT >= 16;
        } else if (drawable instanceof InsetDrawable) {
            if (VERSION.SDK_INT < 14) {
                return false;
            }
            return true;
        } else if (!(drawable instanceof DrawableContainer)) {
            return true;
        } else {
            ConstantState constantState = drawable.getConstantState();
            if (!(constantState instanceof DrawableContainerState)) {
                return true;
            }
            for (Drawable a : ((DrawableContainerState) constantState).getChildren()) {
                if (!m3740a(a)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static PorterDuffColorFilter m3735a(ColorStateList colorStateList, Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return m3734a(colorStateList.getColorForState(iArr, 0), mode);
    }

    private static PorterDuffColorFilter m3734a(int i, Mode mode) {
        PorterDuffColorFilter a = f1823d.m3732a(i, mode);
        if (a != null) {
            return a;
        }
        a = new PorterDuffColorFilter(i, mode);
        f1823d.m3733a(i, mode, a);
        return a;
    }

    private static void m3738a(Drawable drawable, int i, Mode mode) {
        if (mode == null) {
            mode = f1821b;
        }
        drawable.setColorFilter(m3734a(i, mode));
    }
}
