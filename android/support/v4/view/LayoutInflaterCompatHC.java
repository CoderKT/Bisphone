package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import java.lang.reflect.Field;

class LayoutInflaterCompatHC {
    private static Field f434a;
    private static boolean f435b;

    class FactoryWrapperHC extends FactoryWrapper implements Factory2 {
        FactoryWrapperHC(LayoutInflaterFactory layoutInflaterFactory) {
            super(layoutInflaterFactory);
        }

        public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            return this.a.m362a(view, str, context, attributeSet);
        }
    }

    static void m921a(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
        Factory2 factoryWrapperHC;
        if (layoutInflaterFactory != null) {
            factoryWrapperHC = new FactoryWrapperHC(layoutInflaterFactory);
        } else {
            factoryWrapperHC = null;
        }
        layoutInflater.setFactory2(factoryWrapperHC);
        Factory factory = layoutInflater.getFactory();
        if (factory instanceof Factory2) {
            m922a(layoutInflater, (Factory2) factory);
        } else {
            m922a(layoutInflater, factoryWrapperHC);
        }
    }

    static void m922a(LayoutInflater layoutInflater, Factory2 factory2) {
        if (!f435b) {
            try {
                f434a = LayoutInflater.class.getDeclaredField("mFactory2");
                f434a.setAccessible(true);
            } catch (Throwable e) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", e);
            }
            f435b = true;
        }
        if (f434a != null) {
            try {
                f434a.set(layoutInflater, factory2);
            } catch (Throwable e2) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + layoutInflater + "; inflation may have unexpected results.", e2);
            }
        }
    }
}
