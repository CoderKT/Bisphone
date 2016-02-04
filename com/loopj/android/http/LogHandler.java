package com.loopj.android.http;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.util.Log;
import se.emilsjolander.stickylistheaders.C1128R;

public class LogHandler implements LogInterface {
    boolean f6598a;
    int f6599b;

    public LogHandler() {
        this.f6598a = true;
        this.f6599b = 2;
    }

    public boolean m10691a() {
        return this.f6598a;
    }

    public boolean m10692a(int i) {
        return i >= this.f6599b;
    }

    public void m10687a(int i, String str, String str2) {
        m10688a(i, str, str2, null);
    }

    public void m10688a(int i, String str, String str2, Throwable th) {
        if (m10691a() && m10692a(i)) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    Log.v(str, str2, th);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    Log.d(str, str2, th);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    Log.i(str, str2, th);
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    Log.w(str, str2, th);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    Log.e(str, str2, th);
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    if (Integer.valueOf(VERSION.SDK).intValue() > 8) {
                        m10686c(str, str2, th);
                    } else {
                        Log.e(str, str2, th);
                    }
                default:
            }
        }
    }

    @TargetApi(8)
    private void m10686c(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }

    public void m10689a(String str, String str2) {
        m10687a(2, str, str2);
    }

    public void m10693b(String str, String str2) {
        m10687a(2, str, str2);
    }

    public void m10695c(String str, String str2) {
        m10687a(4, str, str2);
    }

    public void m10696d(String str, String str2) {
        m10687a(5, str, str2);
    }

    public void m10690a(String str, String str2, Throwable th) {
        m10688a(5, str, str2, th);
    }

    public void m10697e(String str, String str2) {
        m10687a(6, str, str2);
    }

    public void m10694b(String str, String str2, Throwable th) {
        m10688a(6, str, str2, th);
    }
}
