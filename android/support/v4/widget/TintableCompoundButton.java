package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

public interface TintableCompoundButton {
    void setSupportButtonTintList(ColorStateList colorStateList);

    void setSupportButtonTintMode(Mode mode);
}
