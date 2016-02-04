package app.util;

import app.Main;
import app.localization.BisPhoneCalender;
import app.localization.Localize;
import com.sahandrc.calendar.PersianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
    public static String m7071a(Long l) {
        String string;
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date(l.longValue() / 1000));
        int i = gregorianCalendar.get(11);
        int i2 = gregorianCalendar.get(12);
        if (i <= 12) {
            string = Main.f1927b.getString(2131296789);
        } else {
            i -= 12;
            string = Main.f1927b.getString(2131296790);
        }
        if (i2 < 10) {
            return i + ":0" + i2 + " " + string;
        }
        return i + ":" + i2 + " " + string;
    }

    public static String m7074b(Long l) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(l.longValue()));
    }

    public static String m7068a(int i) {
        if (i >= 0) {
            int i2 = i / 3600;
            int i3 = (i % 3600) / 60;
            int i4 = i % 60;
            String str = "";
            if (i2 > 0) {
                str = i2 + ":";
            }
            return str + i3 + ":" + i4;
        }
        Main.f1926a.m5677a("second value must be equals or grater than 0");
        return "";
    }

    public static int m7075c(Long l) {
        if (l == null) {
            return 10;
        }
        return (int) (((((new Date(System.currentTimeMillis()).getTime() - new Date(l.longValue()).getTime()) / 1000) / 60) / 60) / 24);
    }

    public static String m7069a(long j) {
        if (String.valueOf(j).length() >= 16) {
            j = TimeUnit.MILLISECONDS.convert(j, TimeUnit.MICROSECONDS);
        }
        if (System.currentTimeMillis() - j < 60000) {
            return Main.f1927b.getString(2131296791);
        }
        long offset = ((long) TimeZone.getDefault().getOffset(j)) + j;
        long j2 = (offset / 3600000) % 24;
        offset = (offset / 60000) % 60;
        long j3 = offset / 10;
        long j4 = offset % 10;
        Object obj = 1;
        if (j2 > 12) {
            obj = null;
            j2 %= 12;
        } else if (j2 == 12) {
            obj = null;
        }
        String str = "%d:%d%d %s";
        if (obj != null) {
            return String.format(str, new Object[]{Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4), Main.f1927b.getString(2131296789)});
        }
        return String.format(str, new Object[]{Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4), Main.f1927b.getString(2131296790)});
    }

    public static String m7070a(long j, boolean z, boolean z2) {
        if (String.valueOf(j).length() >= 16) {
            j = TimeUnit.MILLISECONDS.convert(j, TimeUnit.MICROSECONDS);
        }
        if (z2) {
            int currentTimeMillis = (int) (System.currentTimeMillis() / 86400000);
            int i = (int) (j / 86400000);
            if (currentTimeMillis == i) {
                return Main.f1927b.getString(2131296792);
            }
            if (currentTimeMillis - i == 1) {
                return Main.f1927b.getString(2131296793);
            }
        }
        if (BisPhoneCalender.HEJRI_SHAMSI.equals(Localize.m5602c())) {
            PersianCalendar persianCalendar = new PersianCalendar();
            persianCalendar.setTimeInMillis(j);
            if (z) {
                return persianCalendar.m11347e() + " " + persianCalendar.m11346d() + " " + persianCalendar.m11344b();
            }
            return persianCalendar.m11347e() + " " + persianCalendar.m11346d();
        }
        String b = m7074b(Long.valueOf(j));
        String substring = b.substring(0, 4);
        String substring2 = b.substring(4, 6);
        b = b.substring(6, 8);
        if (z) {
            return m7072b(Integer.valueOf(substring2).intValue()) + " " + b + ", " + substring;
        }
        return m7072b(Integer.valueOf(substring2).intValue()).substring(0, 3) + " " + b;
    }

    public static String m7073b(long j) {
        if (j == 0) {
            return Main.f1927b.getString(2131296630);
        }
        if (j < 60) {
            return Main.f1927b.getString(2131296628);
        }
        if (j < 600) {
            return Main.f1927b.getString(2131296627);
        }
        if (j < 3600) {
            return String.format(Main.f1927b.getString(2131296633), new Object[]{Long.valueOf(j / 60)});
        } else if (j < 7200) {
            return Main.f1927b.getString(2131296625);
        } else {
            if (j < 86400) {
                return String.format(Main.f1927b.getString(2131296632), new Object[]{Long.valueOf(j / 3600)});
            } else if (j < 172800) {
                return Main.f1927b.getString(2131296626);
            } else {
                if (j < 604800) {
                    return String.format(Main.f1927b.getString(2131296631), new Object[]{Long.valueOf(j / 86400)});
                } else if (j < 1209600) {
                    return Main.f1927b.getString(2131296637);
                } else {
                    if (j < 2592000) {
                        return String.format(Main.f1927b.getString(2131296635), new Object[]{Long.valueOf(j / 604800)});
                    } else if (j < 5184000) {
                        return Main.f1927b.getString(2131296629);
                    } else {
                        if (j < 31536000) {
                            return String.format(Main.f1927b.getString(2131296634), new Object[]{Long.valueOf(j / 2592000)});
                        } else if (j < 63072000) {
                            return Main.f1927b.getString(2131296638);
                        } else {
                            return String.format(Main.f1927b.getString(2131296636), new Object[]{Long.valueOf(j / 31536000)});
                        }
                    }
                }
            }
        }
    }

    public static String m7072b(int i) {
        try {
            return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}[i - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return i + "";
        }
    }
}
