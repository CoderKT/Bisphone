package com.nostra13.universalimageloader.core.listener;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import se.emilsjolander.stickylistheaders.C1128R;

public class PauseOnScrollListener implements OnScrollListener {
    private ImageLoader f7086a;
    private final boolean f7087b;
    private final boolean f7088c;
    private final OnScrollListener f7089d;

    public PauseOnScrollListener(ImageLoader imageLoader, boolean z, boolean z2) {
        this(imageLoader, z, z2, null);
    }

    public PauseOnScrollListener(ImageLoader imageLoader, boolean z, boolean z2, OnScrollListener onScrollListener) {
        this.f7086a = imageLoader;
        this.f7087b = z;
        this.f7088c = z2;
        this.f7089d = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f7086a.m11091d();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (this.f7087b) {
                    this.f7086a.m11090c();
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (this.f7088c) {
                    this.f7086a.m11090c();
                    break;
                }
                break;
        }
        if (this.f7089d != null) {
            this.f7089d.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.f7089d != null) {
            this.f7089d.onScroll(absListView, i, i2, i3);
        }
    }
}
