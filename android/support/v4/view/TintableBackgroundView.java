package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

public interface TintableBackgroundView {
    void setSupportBackgroundTintList(ColorStateList colorStateList);

    void setSupportBackgroundTintMode(Mode mode);
}
