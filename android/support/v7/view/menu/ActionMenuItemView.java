package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.widget.ActionMenuView.ActionMenuChildView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

public class ActionMenuItemView extends AppCompatTextView implements ItemView, ActionMenuChildView, OnClickListener, OnLongClickListener {
    private MenuItemImpl f989a;
    private CharSequence f990b;
    private Drawable f991c;
    private ItemInvoker f992d;
    private ForwardingListener f993e;
    private PopupCallback f994f;
    private boolean f995g;
    private boolean f996h;
    private int f997i;
    private int f998j;
    private int f999k;

    class ActionMenuItemForwardingListener extends ForwardingListener {
        final /* synthetic */ ActionMenuItemView f985a;

        public ActionMenuItemForwardingListener(ActionMenuItemView actionMenuItemView) {
            this.f985a = actionMenuItemView;
            super(actionMenuItemView);
        }

        public ListPopupWindow m2333a() {
            if (this.f985a.f994f != null) {
                return this.f985a.f994f.m2335a();
            }
            return null;
        }

        protected boolean m2334b() {
            if (this.f985a.f992d == null || !this.f985a.f992d.m2378a(this.f985a.f989a)) {
                return false;
            }
            ListPopupWindow a = m2333a();
            if (a == null || !a.m2828k()) {
                return false;
            }
            return true;
        }
    }

    public abstract class PopupCallback {
        public abstract ListPopupWindow m2335a();
    }

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = context.getResources();
        this.f995g = resources.getBoolean(C0057R.bool.abc_config_allowActionMenuItemTextWithIcon);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.ActionMenuItemView, i, 0);
        this.f997i = obtainStyledAttributes.getDimensionPixelSize(C0057R.styleable.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.f999k = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        setOnLongClickListener(this);
        this.f998j = -1;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        this.f995g = getContext().getResources().getBoolean(C0057R.bool.abc_config_allowActionMenuItemTextWithIcon);
        m2343e();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f998j = i;
        super.setPadding(i, i2, i3, i4);
    }

    public MenuItemImpl getItemData() {
        return this.f989a;
    }

    public void m2344a(MenuItemImpl menuItemImpl, int i) {
        this.f989a = menuItemImpl;
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.m2465a((ItemView) this));
        setId(menuItemImpl.getItemId());
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setEnabled(menuItemImpl.isEnabled());
        if (menuItemImpl.hasSubMenu() && this.f993e == null) {
            this.f993e = new ActionMenuItemForwardingListener(this);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f989a.hasSubMenu() && this.f993e != null && this.f993e.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onClick(View view) {
        if (this.f992d != null) {
            this.f992d.m2378a(this.f989a);
        }
    }

    public void setItemInvoker(ItemInvoker itemInvoker) {
        this.f992d = itemInvoker;
    }

    public void setPopupCallback(PopupCallback popupCallback) {
        this.f994f = popupCallback;
    }

    public boolean m2345a() {
        return true;
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.f996h != z) {
            this.f996h = z;
            if (this.f989a != null) {
                this.f989a.m2480h();
            }
        }
    }

    private void m2343e() {
        int i = 0;
        int i2 = !TextUtils.isEmpty(this.f990b) ? 1 : 0;
        if (this.f991c == null || (this.f989a.m2485m() && (this.f995g || this.f996h))) {
            i = 1;
        }
        setText((i2 & i) != 0 ? this.f990b : null);
    }

    public void setIcon(Drawable drawable) {
        this.f991c = drawable;
        if (drawable != null) {
            float f;
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > this.f999k) {
                f = ((float) this.f999k) / ((float) intrinsicWidth);
                intrinsicWidth = this.f999k;
                intrinsicHeight = (int) (((float) intrinsicHeight) * f);
            }
            if (intrinsicHeight > this.f999k) {
                f = ((float) this.f999k) / ((float) intrinsicHeight);
                intrinsicHeight = this.f999k;
                intrinsicWidth = (int) (((float) intrinsicWidth) * f);
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, null, null, null);
        m2343e();
    }

    public boolean m2346b() {
        return !TextUtils.isEmpty(getText());
    }

    public void setTitle(CharSequence charSequence) {
        this.f990b = charSequence;
        setContentDescription(this.f990b);
        m2343e();
    }

    public boolean m2347c() {
        return m2346b() && this.f989a.getIcon() == null;
    }

    public boolean m2348d() {
        return m2346b();
    }

    public boolean onLongClick(View view) {
        if (m2346b()) {
            return false;
        }
        int[] iArr = new int[2];
        Rect rect = new Rect();
        getLocationOnScreen(iArr);
        getWindowVisibleDisplayFrame(rect);
        Context context = getContext();
        int width = getWidth();
        int height = getHeight();
        int i = iArr[1] + (height / 2);
        width = (width / 2) + iArr[0];
        if (ViewCompat.m1179h(view) == 0) {
            width = context.getResources().getDisplayMetrics().widthPixels - width;
        }
        Toast makeText = Toast.makeText(context, this.f989a.getTitle(), 0);
        if (i < rect.height()) {
            makeText.setGravity(8388661, width, (iArr[1] + height) - rect.top);
        } else {
            makeText.setGravity(81, 0, height);
        }
        makeText.show();
        return true;
    }

    protected void onMeasure(int i, int i2) {
        boolean b = m2346b();
        if (b && this.f998j >= 0) {
            super.setPadding(this.f998j, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        size = mode == Integer.MIN_VALUE ? Math.min(size, this.f997i) : this.f997i;
        if (mode != 1073741824 && this.f997i > 0 && measuredWidth < size) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), i2);
        }
        if (!b && this.f991c != null) {
            super.setPadding((getMeasuredWidth() - this.f991c.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }
}
