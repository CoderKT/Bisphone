package app.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class MessagingEditText extends EditText {
    private handleDismissingKeyboard f4660a;

    public interface handleDismissingKeyboard {
        void m5709L();

        void m5710M();
    }

    @SuppressLint({"NewApi"})
    public MessagingEditText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public MessagingEditText(Context context) {
        super(context);
    }

    public MessagingEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MessagingEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || keyEvent.getAction() != 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        this.f4660a.m5709L();
        return true;
    }

    public void setHandleEditTextKeyboard(handleDismissingKeyboard app_view_MessagingEditText_handleDismissingKeyboard) {
        this.f4660a = app_view_MessagingEditText_handleDismissingKeyboard;
    }

    public boolean onTextContextMenuItem(int i) {
        boolean onTextContextMenuItem = super.onTextContextMenuItem(i);
        switch (i) {
            case 16908320:
                m7122a();
                break;
            case 16908321:
                m7123b();
                break;
            case 16908322:
                m7124c();
                break;
        }
        return onTextContextMenuItem;
    }

    public void m7122a() {
    }

    public void m7123b() {
    }

    public void m7124c() {
        this.f4660a.m5710M();
    }
}
