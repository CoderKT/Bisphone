package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.menu.MenuView.ItemView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListMenuItemView extends LinearLayout implements ItemView {
    private MenuItemImpl f1017a;
    private ImageView f1018b;
    private RadioButton f1019c;
    private TextView f1020d;
    private CheckBox f1021e;
    private TextView f1022f;
    private Drawable f1023g;
    private int f1024h;
    private Context f1025i;
    private boolean f1026j;
    private int f1027k;
    private Context f1028l;
    private LayoutInflater f1029m;
    private boolean f1030n;

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.f1028l = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.MenuView, i, 0);
        this.f1023g = obtainStyledAttributes.getDrawable(C0057R.styleable.MenuView_android_itemBackground);
        this.f1024h = obtainStyledAttributes.getResourceId(C0057R.styleable.MenuView_android_itemTextAppearance, -1);
        this.f1026j = obtainStyledAttributes.getBoolean(C0057R.styleable.MenuView_preserveIconSpacing, false);
        this.f1025i = context;
        obtainStyledAttributes.recycle();
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundDrawable(this.f1023g);
        this.f1020d = (TextView) findViewById(C0057R.id.title);
        if (this.f1024h != -1) {
            this.f1020d.setTextAppearance(this.f1025i, this.f1024h);
        }
        this.f1022f = (TextView) findViewById(C0057R.id.shortcut);
    }

    public void m2385a(MenuItemImpl menuItemImpl, int i) {
        this.f1017a = menuItemImpl;
        this.f1027k = i;
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setTitle(menuItemImpl.m2465a((ItemView) this));
        setCheckable(menuItemImpl.isCheckable());
        m2386a(menuItemImpl.m2478f(), menuItemImpl.m2474d());
        setIcon(menuItemImpl.getIcon());
        setEnabled(menuItemImpl.isEnabled());
    }

    public void setForceShowIcon(boolean z) {
        this.f1030n = z;
        this.f1026j = z;
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.f1020d.setText(charSequence);
            if (this.f1020d.getVisibility() != 0) {
                this.f1020d.setVisibility(0);
            }
        } else if (this.f1020d.getVisibility() != 8) {
            this.f1020d.setVisibility(8);
        }
    }

    public MenuItemImpl getItemData() {
        return this.f1017a;
    }

    public void setCheckable(boolean z) {
        if (z || this.f1019c != null || this.f1021e != null) {
            CompoundButton compoundButton;
            CompoundButton compoundButton2;
            if (this.f1017a.m2479g()) {
                if (this.f1019c == null) {
                    m2383c();
                }
                compoundButton = this.f1019c;
                compoundButton2 = this.f1021e;
            } else {
                if (this.f1021e == null) {
                    m2384d();
                }
                compoundButton = this.f1021e;
                compoundButton2 = this.f1019c;
            }
            if (z) {
                int i;
                compoundButton.setChecked(this.f1017a.isChecked());
                if (z) {
                    i = 0;
                } else {
                    i = 8;
                }
                if (compoundButton.getVisibility() != i) {
                    compoundButton.setVisibility(i);
                }
                if (compoundButton2 != null && compoundButton2.getVisibility() != 8) {
                    compoundButton2.setVisibility(8);
                    return;
                }
                return;
            }
            if (this.f1021e != null) {
                this.f1021e.setVisibility(8);
            }
            if (this.f1019c != null) {
                this.f1019c.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f1017a.m2479g()) {
            if (this.f1019c == null) {
                m2383c();
            }
            compoundButton = this.f1019c;
        } else {
            if (this.f1021e == null) {
                m2384d();
            }
            compoundButton = this.f1021e;
        }
        compoundButton.setChecked(z);
    }

    public void m2386a(boolean z, char c) {
        int i = (z && this.f1017a.m2478f()) ? 0 : 8;
        if (i == 0) {
            this.f1022f.setText(this.f1017a.m2476e());
        }
        if (this.f1022f.getVisibility() != i) {
            this.f1022f.setVisibility(i);
        }
    }

    public void setIcon(Drawable drawable) {
        int i = (this.f1017a.m2481i() || this.f1030n) ? 1 : 0;
        if (i == 0 && !this.f1026j) {
            return;
        }
        if (this.f1018b != null || drawable != null || this.f1026j) {
            if (this.f1018b == null) {
                m2382b();
            }
            if (drawable != null || this.f1026j) {
                ImageView imageView = this.f1018b;
                if (i == 0) {
                    drawable = null;
                }
                imageView.setImageDrawable(drawable);
                if (this.f1018b.getVisibility() != 0) {
                    this.f1018b.setVisibility(0);
                    return;
                }
                return;
            }
            this.f1018b.setVisibility(8);
        }
    }

    protected void onMeasure(int i, int i2) {
        if (this.f1018b != null && this.f1026j) {
            LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f1018b.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    private void m2382b() {
        this.f1018b = (ImageView) getInflater().inflate(C0057R.layout.abc_list_menu_item_icon, this, false);
        addView(this.f1018b, 0);
    }

    private void m2383c() {
        this.f1019c = (RadioButton) getInflater().inflate(C0057R.layout.abc_list_menu_item_radio, this, false);
        addView(this.f1019c);
    }

    private void m2384d() {
        this.f1021e = (CheckBox) getInflater().inflate(C0057R.layout.abc_list_menu_item_checkbox, this, false);
        addView(this.f1021e);
    }

    public boolean m2387a() {
        return false;
    }

    private LayoutInflater getInflater() {
        if (this.f1029m == null) {
            this.f1029m = LayoutInflater.from(this.f1028l);
        }
        return this.f1029m;
    }
}
