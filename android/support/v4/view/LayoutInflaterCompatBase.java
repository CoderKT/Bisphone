package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;

class LayoutInflaterCompatBase {

    class FactoryWrapper implements Factory {
        final LayoutInflaterFactory f433a;

        FactoryWrapper(LayoutInflaterFactory layoutInflaterFactory) {
            this.f433a = layoutInflaterFactory;
        }

        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return this.f433a.m362a(null, str, context, attributeSet);
        }

        public String toString() {
            return getClass().getName() + "{" + this.f433a + "}";
        }
    }

    static void m920a(LayoutInflater layoutInflater, LayoutInflaterFactory layoutInflaterFactory) {
        layoutInflater.setFactory(layoutInflaterFactory != null ? new FactoryWrapper(layoutInflaterFactory) : null);
    }
}
