package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class SupportMenuInflater extends MenuInflater {
    private static final Class<?>[] f946a;
    private static final Class<?>[] f947b;
    private final Object[] f948c;
    private final Object[] f949d;
    private Context f950e;
    private Object f951f;

    class InflatedOnMenuItemClickListener implements OnMenuItemClickListener {
        private static final Class<?>[] f917a;
        private Object f918b;
        private Method f919c;

        static {
            f917a = new Class[]{MenuItem.class};
        }

        public InflatedOnMenuItemClickListener(Object obj, String str) {
            this.f918b = obj;
            Class cls = obj.getClass();
            try {
                this.f919c = cls.getMethod(str, f917a);
            } catch (Throwable e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f919c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f919c.invoke(this.f918b, new Object[]{menuItem})).booleanValue();
                }
                this.f919c.invoke(this.f918b, new Object[]{menuItem});
                return true;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    class MenuState {
        final /* synthetic */ SupportMenuInflater f920a;
        private Menu f921b;
        private int f922c;
        private int f923d;
        private int f924e;
        private int f925f;
        private boolean f926g;
        private boolean f927h;
        private boolean f928i;
        private int f929j;
        private int f930k;
        private CharSequence f931l;
        private CharSequence f932m;
        private int f933n;
        private char f934o;
        private char f935p;
        private int f936q;
        private boolean f937r;
        private boolean f938s;
        private boolean f939t;
        private int f940u;
        private int f941v;
        private String f942w;
        private String f943x;
        private String f944y;
        private ActionProvider f945z;

        public MenuState(SupportMenuInflater supportMenuInflater, Menu menu) {
            this.f920a = supportMenuInflater;
            this.f921b = menu;
            m2286a();
        }

        public void m2286a() {
            this.f922c = 0;
            this.f923d = 0;
            this.f924e = 0;
            this.f925f = 0;
            this.f926g = true;
            this.f927h = true;
        }

        public void m2287a(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = this.f920a.f950e.obtainStyledAttributes(attributeSet, C0057R.styleable.MenuGroup);
            this.f922c = obtainStyledAttributes.getResourceId(C0057R.styleable.MenuGroup_android_id, 0);
            this.f923d = obtainStyledAttributes.getInt(C0057R.styleable.MenuGroup_android_menuCategory, 0);
            this.f924e = obtainStyledAttributes.getInt(C0057R.styleable.MenuGroup_android_orderInCategory, 0);
            this.f925f = obtainStyledAttributes.getInt(C0057R.styleable.MenuGroup_android_checkableBehavior, 0);
            this.f926g = obtainStyledAttributes.getBoolean(C0057R.styleable.MenuGroup_android_visible, true);
            this.f927h = obtainStyledAttributes.getBoolean(C0057R.styleable.MenuGroup_android_enabled, true);
            obtainStyledAttributes.recycle();
        }

        public void m2289b(AttributeSet attributeSet) {
            boolean z = true;
            TypedArray obtainStyledAttributes = this.f920a.f950e.obtainStyledAttributes(attributeSet, C0057R.styleable.MenuItem);
            this.f929j = obtainStyledAttributes.getResourceId(C0057R.styleable.MenuItem_android_id, 0);
            this.f930k = (obtainStyledAttributes.getInt(C0057R.styleable.MenuItem_android_menuCategory, this.f923d) & -65536) | (obtainStyledAttributes.getInt(C0057R.styleable.MenuItem_android_orderInCategory, this.f924e) & InBandBytestreamManager.MAXIMUM_BLOCK_SIZE);
            this.f931l = obtainStyledAttributes.getText(C0057R.styleable.MenuItem_android_title);
            this.f932m = obtainStyledAttributes.getText(C0057R.styleable.MenuItem_android_titleCondensed);
            this.f933n = obtainStyledAttributes.getResourceId(C0057R.styleable.MenuItem_android_icon, 0);
            this.f934o = m2282a(obtainStyledAttributes.getString(C0057R.styleable.MenuItem_android_alphabeticShortcut));
            this.f935p = m2282a(obtainStyledAttributes.getString(C0057R.styleable.MenuItem_android_numericShortcut));
            if (obtainStyledAttributes.hasValue(C0057R.styleable.MenuItem_android_checkable)) {
                this.f936q = obtainStyledAttributes.getBoolean(C0057R.styleable.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.f936q = this.f925f;
            }
            this.f937r = obtainStyledAttributes.getBoolean(C0057R.styleable.MenuItem_android_checked, false);
            this.f938s = obtainStyledAttributes.getBoolean(C0057R.styleable.MenuItem_android_visible, this.f926g);
            this.f939t = obtainStyledAttributes.getBoolean(C0057R.styleable.MenuItem_android_enabled, this.f927h);
            this.f940u = obtainStyledAttributes.getInt(C0057R.styleable.MenuItem_showAsAction, -1);
            this.f944y = obtainStyledAttributes.getString(C0057R.styleable.MenuItem_android_onClick);
            this.f941v = obtainStyledAttributes.getResourceId(C0057R.styleable.MenuItem_actionLayout, 0);
            this.f942w = obtainStyledAttributes.getString(C0057R.styleable.MenuItem_actionViewClass);
            this.f943x = obtainStyledAttributes.getString(C0057R.styleable.MenuItem_actionProviderClass);
            if (this.f943x == null) {
                z = false;
            }
            if (z && this.f941v == 0 && this.f942w == null) {
                this.f945z = (ActionProvider) m2284a(this.f943x, SupportMenuInflater.f947b, this.f920a.f949d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.f945z = null;
            }
            obtainStyledAttributes.recycle();
            this.f928i = false;
        }

        private char m2282a(String str) {
            if (str == null) {
                return '\u0000';
            }
            return str.charAt(0);
        }

        private void m2285a(MenuItem menuItem) {
            boolean z = true;
            menuItem.setChecked(this.f937r).setVisible(this.f938s).setEnabled(this.f939t).setCheckable(this.f936q >= 1).setTitleCondensed(this.f932m).setIcon(this.f933n).setAlphabeticShortcut(this.f934o).setNumericShortcut(this.f935p);
            if (this.f940u >= 0) {
                MenuItemCompat.m959a(menuItem, this.f940u);
            }
            if (this.f944y != null) {
                if (this.f920a.f950e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(this.f920a.m2298c(), this.f944y));
            }
            if (menuItem instanceof MenuItemImpl) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
            }
            if (this.f936q >= 2) {
                if (menuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl) menuItem).m2468a(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) menuItem).m2497a(true);
                }
            }
            if (this.f942w != null) {
                MenuItemCompat.m957a(menuItem, (View) m2284a(this.f942w, SupportMenuInflater.f946a, this.f920a.f948c));
            } else {
                z = false;
            }
            if (this.f941v > 0) {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    MenuItemCompat.m960b(menuItem, this.f941v);
                }
            }
            if (this.f945z != null) {
                MenuItemCompat.m956a(menuItem, this.f945z);
            }
        }

        public void m2288b() {
            this.f928i = true;
            m2285a(this.f921b.add(this.f922c, this.f929j, this.f930k, this.f931l));
        }

        public SubMenu m2290c() {
            this.f928i = true;
            SubMenu addSubMenu = this.f921b.addSubMenu(this.f922c, this.f929j, this.f930k, this.f931l);
            m2285a(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean m2291d() {
            return this.f928i;
        }

        private <T> T m2284a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor constructor = this.f920a.f950e.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return constructor.newInstance(objArr);
            } catch (Throwable e) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e);
                return null;
            }
        }
    }

    static {
        f946a = new Class[]{Context.class};
        f947b = f946a;
    }

    public SupportMenuInflater(Context context) {
        super(context);
        this.f950e = context;
        this.f948c = new Object[]{context};
        this.f949d = this.f948c;
    }

    public void inflate(int i, Menu menu) {
        if (menu instanceof SupportMenu) {
            XmlResourceParser xmlResourceParser = null;
            try {
                xmlResourceParser = this.f950e.getResources().getLayout(i);
                m2294a(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            } catch (Throwable e) {
                throw new InflateException("Error inflating menu XML", e);
            } catch (Throwable e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (Throwable th) {
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            }
        } else {
            super.inflate(i, menu);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2294a(org.xmlpull.v1.XmlPullParser r11, android.util.AttributeSet r12, android.view.Menu r13) {
        /*
        r10 = this;
        r4 = 0;
        r1 = 1;
        r6 = 0;
        r7 = new android.support.v7.view.SupportMenuInflater$MenuState;
        r7.<init>(r10, r13);
        r0 = r11.getEventType();
    L_0x000c:
        r2 = 2;
        if (r0 != r2) goto L_0x004a;
    L_0x000f:
        r0 = r11.getName();
        r2 = "menu";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0031;
    L_0x001b:
        r0 = r11.next();
    L_0x001f:
        r2 = r4;
        r5 = r6;
        r3 = r0;
        r0 = r6;
    L_0x0023:
        if (r0 != 0) goto L_0x00e1;
    L_0x0025:
        switch(r3) {
            case 1: goto L_0x00d9;
            case 2: goto L_0x0051;
            case 3: goto L_0x0087;
            default: goto L_0x0028;
        };
    L_0x0028:
        r3 = r5;
    L_0x0029:
        r5 = r11.next();
        r9 = r3;
        r3 = r5;
        r5 = r9;
        goto L_0x0023;
    L_0x0031:
        r1 = new java.lang.RuntimeException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Expecting menu, got ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
    L_0x004a:
        r0 = r11.next();
        if (r0 != r1) goto L_0x000c;
    L_0x0050:
        goto L_0x001f;
    L_0x0051:
        if (r5 == 0) goto L_0x0055;
    L_0x0053:
        r3 = r5;
        goto L_0x0029;
    L_0x0055:
        r3 = r11.getName();
        r8 = "group";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0066;
    L_0x0061:
        r7.m2287a(r12);
        r3 = r5;
        goto L_0x0029;
    L_0x0066:
        r8 = "item";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0073;
    L_0x006e:
        r7.m2289b(r12);
        r3 = r5;
        goto L_0x0029;
    L_0x0073:
        r8 = "menu";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0084;
    L_0x007b:
        r3 = r7.m2290c();
        r10.m2294a(r11, r12, r3);
        r3 = r5;
        goto L_0x0029;
    L_0x0084:
        r2 = r3;
        r3 = r1;
        goto L_0x0029;
    L_0x0087:
        r3 = r11.getName();
        if (r5 == 0) goto L_0x0096;
    L_0x008d:
        r8 = r3.equals(r2);
        if (r8 == 0) goto L_0x0096;
    L_0x0093:
        r2 = r4;
        r3 = r6;
        goto L_0x0029;
    L_0x0096:
        r8 = "group";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x00a3;
    L_0x009e:
        r7.m2286a();
        r3 = r5;
        goto L_0x0029;
    L_0x00a3:
        r8 = "item";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x00cd;
    L_0x00ab:
        r3 = r7.m2291d();
        if (r3 != 0) goto L_0x0028;
    L_0x00b1:
        r3 = r7.f945z;
        if (r3 == 0) goto L_0x00c7;
    L_0x00b7:
        r3 = r7.f945z;
        r3 = r3.m894e();
        if (r3 == 0) goto L_0x00c7;
    L_0x00c1:
        r7.m2290c();
        r3 = r5;
        goto L_0x0029;
    L_0x00c7:
        r7.m2288b();
        r3 = r5;
        goto L_0x0029;
    L_0x00cd:
        r8 = "menu";
        r3 = r3.equals(r8);
        if (r3 == 0) goto L_0x0028;
    L_0x00d5:
        r0 = r1;
        r3 = r5;
        goto L_0x0029;
    L_0x00d9:
        r0 = new java.lang.RuntimeException;
        r1 = "Unexpected end of document";
        r0.<init>(r1);
        throw r0;
    L_0x00e1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.SupportMenuInflater.a(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    private Object m2298c() {
        if (this.f951f == null) {
            this.f951f = m2293a(this.f950e);
        }
        return this.f951f;
    }

    private Object m2293a(Object obj) {
        if (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
            return m2293a(((ContextWrapper) obj).getBaseContext());
        }
        return obj;
    }
}
