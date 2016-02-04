package org.jxmpp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmppDateTime {
    private static final DateFormatType f8604a;
    private static final Pattern f8605b;
    private static final DateFormatType f8606c;
    private static final Pattern f8607d;
    private static final DateFormatType f8608e;
    private static final Pattern f8609f;
    private static final DateFormatType f8610g;
    private static final Pattern f8611h;
    private static final DateFormatType f8612i;
    private static final Pattern f8613j;
    private static final DateFormatType f8614k;
    private static final Pattern f8615l;
    private static final DateFormatType f8616m;
    private static final Pattern f8617n;
    private static final DateFormat f8618o;
    private static final DateFormat f8619p;
    private static final DateFormat f8620q;
    private static final DateFormat f8621r;
    private static final Pattern f8622s;
    private static final List<PatternCouplings> f8623t;
    private static final Pattern f8624u;

    /* renamed from: org.jxmpp.util.XmppDateTime.1 */
    final class C11251 implements Comparator<Calendar> {
        final /* synthetic */ Calendar f8588a;

        C11251(Calendar calendar) {
            this.f8588a = calendar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m13425a((Calendar) obj, (Calendar) obj2);
        }

        public int m13425a(Calendar calendar, Calendar calendar2) {
            return new Long(this.f8588a.getTimeInMillis() - calendar.getTimeInMillis()).compareTo(new Long(this.f8588a.getTimeInMillis() - calendar2.getTimeInMillis()));
        }
    }

    public enum DateFormatType {
        XEP_0082_DATE_PROFILE("yyyy-MM-dd"),
        XEP_0082_DATETIME_PROFILE("yyyy-MM-dd'T'HH:mm:ssZ"),
        XEP_0082_DATETIME_MILLIS_PROFILE("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
        XEP_0082_TIME_PROFILE("hh:mm:ss"),
        XEP_0082_TIME_ZONE_PROFILE("hh:mm:ssZ"),
        XEP_0082_TIME_MILLIS_PROFILE("hh:mm:ss.SSS"),
        XEP_0082_TIME_MILLIS_ZONE_PROFILE("hh:mm:ss.SSSZ"),
        XEP_0091_DATETIME("yyyyMMdd'T'HH:mm:ss");
        
        private final String f8598i;
        private final DateFormat f8599j;
        private final boolean f8600k;
        private final boolean f8601l;

        private DateFormatType(String str) {
            this.f8598i = str;
            this.f8599j = new SimpleDateFormat(this.f8598i);
            this.f8599j.setTimeZone(TimeZone.getTimeZone("UTC"));
            this.f8600k = str.charAt(str.length() + -1) == 'Z';
            this.f8601l = str.contains("SSS");
        }

        public String m13426a(Date date) {
            synchronized (this.f8599j) {
                String format = this.f8599j.format(date);
            }
            if (this.f8600k) {
                return XmppDateTime.m13437d(format);
            }
            return format;
        }

        public Date m13427a(String str) {
            Date parse;
            if (this.f8600k) {
                str = XmppDateTime.m13436c(str);
            }
            if (this.f8601l) {
                str = XmppDateTime.m13439f(str);
            }
            synchronized (this.f8599j) {
                parse = this.f8599j.parse(str);
            }
            return parse;
        }
    }

    class PatternCouplings {
        final Pattern f8602a;
        final DateFormatType f8603b;

        public PatternCouplings(Pattern pattern, DateFormatType dateFormatType) {
            this.f8602a = pattern;
            this.f8603b = dateFormatType;
        }
    }

    static {
        f8604a = DateFormatType.XEP_0082_DATE_PROFILE;
        f8605b = Pattern.compile("^\\d+-\\d+-\\d+$");
        f8606c = DateFormatType.XEP_0082_TIME_MILLIS_ZONE_PROFILE;
        f8607d = Pattern.compile("^(\\d+:){2}\\d+.\\d+(Z|([+-](\\d+:\\d+)))$");
        f8608e = DateFormatType.XEP_0082_TIME_MILLIS_PROFILE;
        f8609f = Pattern.compile("^(\\d+:){2}\\d+.\\d+$");
        f8610g = DateFormatType.XEP_0082_TIME_ZONE_PROFILE;
        f8611h = Pattern.compile("^(\\d+:){2}\\d+(Z|([+-](\\d+:\\d+)))$");
        f8612i = DateFormatType.XEP_0082_TIME_PROFILE;
        f8613j = Pattern.compile("^(\\d+:){2}\\d+$");
        f8614k = DateFormatType.XEP_0082_DATETIME_MILLIS_PROFILE;
        f8615l = Pattern.compile("^\\d+(-\\d+){2}+T(\\d+:){2}\\d+.\\d+(Z|([+-](\\d+:\\d+)))?$");
        f8616m = DateFormatType.XEP_0082_DATETIME_PROFILE;
        f8617n = Pattern.compile("^\\d+(-\\d+){2}+T(\\d+:){2}\\d+(Z|([+-](\\d+:\\d+)))?$");
        f8618o = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        f8619p = new SimpleDateFormat("yyyyMd'T'HH:mm:ss");
        f8620q = new SimpleDateFormat("yyyyMdd'T'HH:mm:ss");
        f8621r = new SimpleDateFormat("yyyyMMd'T'HH:mm:ss");
        f8622s = Pattern.compile("^\\d+T\\d+:\\d+:\\d+$");
        f8623t = new ArrayList();
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        f8618o.setTimeZone(timeZone);
        f8619p.setTimeZone(timeZone);
        f8620q.setTimeZone(timeZone);
        f8620q.setLenient(false);
        f8621r.setTimeZone(timeZone);
        f8621r.setLenient(false);
        f8623t.add(new PatternCouplings(f8605b, f8604a));
        f8623t.add(new PatternCouplings(f8615l, f8614k));
        f8623t.add(new PatternCouplings(f8617n, f8616m));
        f8623t.add(new PatternCouplings(f8607d, f8606c));
        f8623t.add(new PatternCouplings(f8609f, f8608e));
        f8623t.add(new PatternCouplings(f8611h, f8610g));
        f8623t.add(new PatternCouplings(f8613j, f8612i));
        f8624u = Pattern.compile(".*\\.(\\d{1,})(Z|((\\+|-)\\d{4}))");
    }

    public static Date m13432a(String str) {
        Date a;
        for (PatternCouplings patternCouplings : f8623t) {
            if (patternCouplings.f8602a.matcher(str).matches()) {
                return patternCouplings.f8603b.m13427a(str);
            }
        }
        synchronized (f8616m) {
            a = f8616m.m13427a(str);
        }
        return a;
    }

    public static Date m13435b(String str) {
        if (f8622s.matcher(str).matches()) {
            int length = str.split("T")[0].length();
            Date a;
            if (length < 8) {
                a = m13433a(str, length);
                if (a != null) {
                    return a;
                }
            }
            synchronized (f8618o) {
                a = f8618o.parse(str);
            }
            return a;
        }
        return m13432a(str);
    }

    public static String m13428a(Date date) {
        String a;
        synchronized (f8614k) {
            a = f8614k.m13426a(date);
        }
        return a;
    }

    public static String m13436c(String str) {
        if (str.charAt(str.length() - 1) == 'Z') {
            return str.replace("Z", "+0000");
        }
        return str.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
    }

    public static String m13437d(String str) {
        int length = str.length();
        return (str.substring(0, length - 2) + ':') + str.substring(length - 2, length);
    }

    public static String m13429a(TimeZone timeZone) {
        int rawOffset = timeZone.getRawOffset();
        rawOffset = Math.abs((rawOffset / 60000) - ((rawOffset / 3600000) * 60));
        return String.format("%+d:%02d", new Object[]{Integer.valueOf(r1), Integer.valueOf(rawOffset)});
    }

    private static Date m13433a(String str, int i) {
        if (i == 6) {
            Date parse;
            synchronized (f8619p) {
                parse = f8619p.parse(str);
            }
            return parse;
        }
        Calendar instance = Calendar.getInstance();
        Calendar a = m13430a(str, f8620q);
        Calendar a2 = m13430a(str, f8621r);
        List a3 = m13434a(instance, a, a2);
        if (a3.isEmpty()) {
            return null;
        }
        return m13431a(instance, a3).getTime();
    }

    private static Calendar m13430a(String str, DateFormat dateFormat) {
        try {
            Calendar calendar;
            synchronized (dateFormat) {
                dateFormat.parse(str);
                calendar = dateFormat.getCalendar();
            }
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    private static List<Calendar> m13434a(Calendar calendar, Calendar... calendarArr) {
        List<Calendar> arrayList = new ArrayList();
        for (Calendar calendar2 : calendarArr) {
            if (calendar2 != null && calendar2.before(calendar)) {
                arrayList.add(calendar2);
            }
        }
        return arrayList;
    }

    private static String m13439f(String str) {
        Matcher matcher = f8624u.matcher(str);
        if (!matcher.matches()) {
            return str;
        }
        int length = matcher.group(1).length();
        if (length == 3) {
            return str;
        }
        int indexOf = str.indexOf(".");
        StringBuilder stringBuilder = new StringBuilder((str.length() - length) + 3);
        if (length > 3) {
            stringBuilder.append(str.substring(0, indexOf + 4));
        } else {
            stringBuilder.append(str.substring(0, (indexOf + length) + 1));
            for (int i = length; i < 3; i++) {
                stringBuilder.append('0');
            }
        }
        stringBuilder.append(str.substring((indexOf + length) + 1));
        return stringBuilder.toString();
    }

    private static Calendar m13431a(Calendar calendar, List<Calendar> list) {
        Collections.sort(list, new C11251(calendar));
        return (Calendar) list.get(0);
    }
}
