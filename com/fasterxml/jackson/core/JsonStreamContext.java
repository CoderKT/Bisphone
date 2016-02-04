package com.fasterxml.jackson.core;

import se.emilsjolander.stickylistheaders.C1128R;

public abstract class JsonStreamContext {
    protected int _index;
    protected int _type;

    protected JsonStreamContext() {
    }

    public final boolean inArray() {
        return this._type == 1;
    }

    public final boolean inRoot() {
        return this._type == 0;
    }

    public final boolean inObject() {
        return this._type == 2;
    }

    public final String getTypeDesc() {
        switch (this._type) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return "ROOT";
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return "ARRAY";
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return "OBJECT";
            default:
                return "?";
        }
    }

    public final int getCurrentIndex() {
        return this._index < 0 ? 0 : this._index;
    }
}
