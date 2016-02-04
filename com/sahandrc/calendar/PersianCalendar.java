package com.sahandrc.calendar;

import com.sahandrc.calendar.utils.PersianCalendarConstants;
import com.sahandrc.calendar.utils.PersianCalendarUtils;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class PersianCalendar extends GregorianCalendar {
    private int f7156a;
    private int f7157b;
    private int f7158c;
    private String f7159d;

    public PersianCalendar() {
        this.f7159d = "/";
        setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    protected void m11343a() {
        long timeInMillis;
        try {
            timeInMillis = getTimeInMillis();
        } catch (Exception e) {
            timeInMillis = 0;
        }
        long a = PersianCalendarUtils.m11350a(((long) Math.floor((double) (timeInMillis - -210866803200000L))) / 86400000);
        timeInMillis = a >> 16;
        int i = ((int) (65280 & a)) >> 8;
        int i2 = (int) (a & 255);
        if (timeInMillis <= 0) {
            timeInMillis--;
        }
        this.f7156a = (int) timeInMillis;
        this.f7157b = i;
        this.f7158c = i2;
    }

    public int m11344b() {
        return this.f7156a;
    }

    public int m11345c() {
        return this.f7157b + 1;
    }

    public String m11346d() {
        return PersianCalendarConstants.f7160a[this.f7157b];
    }

    public int m11347e() {
        return this.f7158c;
    }

    public String m11348f() {
        return "" + m11342a(this.f7156a) + this.f7159d + m11342a(m11345c()) + this.f7159d + m11342a(this.f7158c);
    }

    private String m11342a(int i) {
        return i < 9 ? "0" + i : String.valueOf(i);
    }

    public String toString() {
        String gregorianCalendar = super.toString();
        return gregorianCalendar.substring(0, gregorianCalendar.length() - 1) + ",PersianDate=" + m11348f() + "]";
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void set(int i, int i2) {
        super.set(i, i2);
        m11343a();
    }

    public void setTimeInMillis(long j) {
        super.setTimeInMillis(j);
        m11343a();
    }

    public void setTimeZone(TimeZone timeZone) {
        super.setTimeZone(timeZone);
        m11343a();
    }
}
