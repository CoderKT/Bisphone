package app.galley;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class ActionEditText extends EditText {
    public ActionEditText(Context context) {
        super(context);
    }

    public ActionEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActionEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        editorInfo.imeOptions &= -1073741825;
        return onCreateInputConnection;
    }
}
