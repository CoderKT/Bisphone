package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources.Theme;
import android.support.v7.appcompat.C0057R;
import android.view.LayoutInflater;

public class ContextThemeWrapper extends ContextWrapper {
    private int f901a;
    private Theme f902b;
    private LayoutInflater f903c;

    public ContextThemeWrapper(Context context, int i) {
        super(context);
        this.f901a = i;
    }

    public ContextThemeWrapper(Context context, Theme theme) {
        super(context);
        this.f902b = theme;
    }

    public void setTheme(int i) {
        if (this.f901a != i) {
            this.f901a = i;
            m2257b();
        }
    }

    public int m2258a() {
        return this.f901a;
    }

    public Theme getTheme() {
        if (this.f902b != null) {
            return this.f902b;
        }
        if (this.f901a == 0) {
            this.f901a = C0057R.style.Theme_AppCompat_Light;
        }
        m2257b();
        return this.f902b;
    }

    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f903c == null) {
            this.f903c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f903c;
    }

    protected void m2259a(Theme theme, int i, boolean z) {
        theme.applyStyle(i, true);
    }

    private void m2257b() {
        boolean z = this.f902b == null;
        if (z) {
            this.f902b = getResources().newTheme();
            Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f902b.setTo(theme);
            }
        }
        m2259a(this.f902b, this.f901a, z);
    }
}
