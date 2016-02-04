package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import se.emilsjolander.stickylistheaders.C1128R;

class AppCompatViewInflater {
    private static final Class<?>[] f839a;
    private static final int[] f840b;
    private static final Map<String, Constructor<? extends View>> f841c;
    private final Object[] f842d;

    class DeclaredOnClickListener implements OnClickListener {
        private final View f835a;
        private final String f836b;
        private Method f837c;
        private Context f838d;

        public DeclaredOnClickListener(View view, String str) {
            this.f835a = view;
            this.f836b = str;
        }

        public void onClick(View view) {
            if (this.f837c == null) {
                m2136a(this.f835a.getContext(), this.f836b);
            }
            try {
                this.f837c.invoke(this.f838d, new Object[]{view});
            } catch (Throwable e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (Throwable e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }

        private void m2136a(Context context, String str) {
            Context context2 = context;
            while (context2 != null) {
                try {
                    if (!context2.isRestricted()) {
                        Method method = context2.getClass().getMethod(this.f836b, new Class[]{View.class});
                        if (method != null) {
                            this.f837c = method;
                            this.f838d = context2;
                            return;
                        }
                    }
                } catch (NoSuchMethodException e) {
                }
                if (context2 instanceof ContextWrapper) {
                    context2 = ((ContextWrapper) context2).getBaseContext();
                } else {
                    context2 = null;
                }
            }
            int id = this.f835a.getId();
            throw new IllegalStateException("Could not find method " + this.f836b + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.f835a.getClass() + (id == -1 ? "" : " with id '" + this.f835a.getContext().getResources().getResourceEntryName(id) + "'"));
        }
    }

    AppCompatViewInflater() {
        this.f842d = new Object[2];
    }

    static {
        f839a = new Class[]{Context.class, AttributeSet.class};
        f840b = new int[]{16843375};
        f841c = new ArrayMap();
    }

    public final View m2141a(View view, String str, Context context, AttributeSet attributeSet, boolean z, boolean z2, boolean z3) {
        Context context2;
        View view2;
        if (!z || view == null) {
            context2 = context;
        } else {
            context2 = view.getContext();
        }
        if (z2 || z3) {
            context2 = m2137a(context2, attributeSet, z2, z3);
        }
        View view3 = null;
        Object obj = -1;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    obj = 11;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    obj = 8;
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    obj = 10;
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    obj = null;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    obj = 5;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    obj = 12;
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    obj = 4;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    obj = 7;
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    obj = 1;
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    obj = 9;
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    obj = 6;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    obj = 3;
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                view3 = new AppCompatTextView(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                view3 = new AppCompatImageView(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                view3 = new AppCompatButton(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                view3 = new AppCompatEditText(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                view3 = new AppCompatSpinner(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                view3 = new AppCompatImageButton(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                view3 = new AppCompatCheckBox(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                view3 = new AppCompatRadioButton(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                view3 = new AppCompatCheckedTextView(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                view3 = new AppCompatAutoCompleteTextView(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                view3 = new AppCompatMultiAutoCompleteTextView(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                view3 = new AppCompatRatingBar(context2, attributeSet);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                view3 = new AppCompatSeekBar(context2, attributeSet);
                break;
        }
        if (view3 != null || context == context2) {
            view2 = view3;
        } else {
            view2 = m2138a(context2, str, attributeSet);
        }
        if (view2 != null) {
            m2140a(view2, attributeSet);
        }
        return view2;
    }

    private View m2138a(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.f842d[0] = context;
            this.f842d[1] = attributeSet;
            View a;
            if (-1 == str.indexOf(46)) {
                a = m2139a(context, str, "android.widget.");
                return a;
            }
            a = m2139a(context, str, null);
            this.f842d[0] = null;
            this.f842d[1] = null;
            return a;
        } catch (Exception e) {
            return null;
        } finally {
            this.f842d[0] = null;
            this.f842d[1] = null;
        }
    }

    private void m2140a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (ViewCompat.m1193v(view) && (context instanceof ContextWrapper)) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f840b);
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new DeclaredOnClickListener(view, string));
            }
            obtainStyledAttributes.recycle();
        }
    }

    private View m2139a(Context context, String str, String str2) {
        Constructor constructor = (Constructor) f841c.get(str);
        if (constructor == null) {
            try {
                constructor = context.getClassLoader().loadClass(str2 != null ? str2 + str : str).asSubclass(View.class).getConstructor(f839a);
                f841c.put(str, constructor);
            } catch (Exception e) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.f842d);
    }

    private static Context m2137a(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        int resourceId;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.View, 0, 0);
        if (z) {
            resourceId = obtainStyledAttributes.getResourceId(C0057R.styleable.View_android_theme, 0);
        } else {
            resourceId = 0;
        }
        if (z2 && r0 == 0) {
            resourceId = obtainStyledAttributes.getResourceId(C0057R.styleable.View_theme, 0);
            if (resourceId != 0) {
                Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }
        int i = resourceId;
        obtainStyledAttributes.recycle();
        if (i == 0) {
            return context;
        }
        if ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).m2258a() == i) {
            return context;
        }
        return new ContextThemeWrapper(context, i);
    }
}
