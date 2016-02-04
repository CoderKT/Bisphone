package android.support.v7.view;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window.Callback;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class WindowCallbackWrapper implements Callback {
    final Callback f751d;

    public WindowCallbackWrapper(Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.f751d = callback;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.f751d.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return this.f751d.dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.f751d.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.f751d.dispatchTrackballEvent(motionEvent);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.f751d.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.f751d.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public View onCreatePanelView(int i) {
        return this.f751d.onCreatePanelView(i);
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return this.f751d.onCreatePanelMenu(i, menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        return this.f751d.onPreparePanel(i, view, menu);
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return this.f751d.onMenuOpened(i, menu);
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.f751d.onMenuItemSelected(i, menuItem);
    }

    public void onWindowAttributesChanged(LayoutParams layoutParams) {
        this.f751d.onWindowAttributesChanged(layoutParams);
    }

    public void onContentChanged() {
        this.f751d.onContentChanged();
    }

    public void onWindowFocusChanged(boolean z) {
        this.f751d.onWindowFocusChanged(z);
    }

    public void onAttachedToWindow() {
        this.f751d.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        this.f751d.onDetachedFromWindow();
    }

    public void onPanelClosed(int i, Menu menu) {
        this.f751d.onPanelClosed(i, menu);
    }

    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.f751d.onSearchRequested(searchEvent);
    }

    public boolean onSearchRequested() {
        return this.f751d.onSearchRequested();
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.f751d.onWindowStartingActionMode(callback);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return this.f751d.onWindowStartingActionMode(callback, i);
    }

    public void onActionModeStarted(ActionMode actionMode) {
        this.f751d.onActionModeStarted(actionMode);
    }

    public void onActionModeFinished(ActionMode actionMode) {
        this.f751d.onActionModeFinished(actionMode);
    }
}
