package com.nispok.snackbar.enums;

public enum SnackbarType {
    SINGLE_LINE(48, 48, 1),
    MULTI_LINE(48, 80, 2);
    
    private int f6771c;
    private int f6772d;
    private int f6773e;

    private SnackbarType(int i, int i2, int i3) {
        this.f6771c = i;
        this.f6772d = i2;
        this.f6773e = i3;
    }

    public int m10888a() {
        return this.f6771c;
    }

    public int m10889b() {
        return this.f6772d;
    }

    public int m10890c() {
        return this.f6773e;
    }
}
