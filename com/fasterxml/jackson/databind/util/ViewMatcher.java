package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import se.emilsjolander.stickylistheaders.C1128R;

public class ViewMatcher implements Serializable {
    protected static final ViewMatcher EMPTY;

    final class Multi extends ViewMatcher implements Serializable {
        private final Class<?>[] _views;

        public Multi(Class<?>[] clsArr) {
            this._views = clsArr;
        }

        public boolean isVisibleForView(Class<?> cls) {
            for (Class<?> cls2 : this._views) {
                if (cls == cls2 || cls2.isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    final class Single extends ViewMatcher {
        private final Class<?> _view;

        public Single(Class<?> cls) {
            this._view = cls;
        }

        public boolean isVisibleForView(Class<?> cls) {
            return cls == this._view || this._view.isAssignableFrom(cls);
        }
    }

    static {
        EMPTY = new ViewMatcher();
    }

    public boolean isVisibleForView(Class<?> cls) {
        return false;
    }

    public static ViewMatcher construct(Class<?>[] clsArr) {
        if (clsArr == null) {
            return EMPTY;
        }
        switch (clsArr.length) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return EMPTY;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return new Single(clsArr[0]);
            default:
                return new Multi(clsArr);
        }
    }
}
