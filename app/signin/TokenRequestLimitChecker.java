package app.signin;

import android.content.Context;
import app.Main;
import app.util.SharedPreferencesUtil;

public class TokenRequestLimitChecker {
    private Context f4496a;

    public TokenRequestLimitChecker(Context context) {
        this.f4496a = context;
    }

    private int m6892e() {
        String a = SharedPreferencesUtil.m7052a(this.f4496a.getString(2131296937));
        if (a == null) {
            a = "0";
        }
        Main.f1926a.m5685e("Number of tries: " + a);
        return Integer.valueOf(a).intValue();
    }

    private void m6891a(int i) {
        SharedPreferencesUtil.m7055a(this.f4496a.getString(2131296937), i + "");
    }

    private void m6893f() {
        m6891a(m6892e() + 1);
    }

    private void m6894g() {
        m6891a(0);
    }

    public long m6896a() {
        String a = SharedPreferencesUtil.m7052a(this.f4496a.getString(2131296936));
        if (a == null) {
            return 0;
        }
        return Long.valueOf(a).longValue();
    }

    private void m6895h() {
        SharedPreferencesUtil.m7055a(this.f4496a.getString(2131296936), Long.toString(System.currentTimeMillis()));
    }

    public boolean m6897b() {
        if (System.currentTimeMillis() - m6896a() > 900000) {
            m6894g();
        }
        return m6892e() < 5;
    }

    public boolean m6898c() {
        boolean b = m6897b();
        if (b) {
            m6893f();
            m6895h();
        }
        return b;
    }

    public String m6899d() {
        return String.format(this.f4496a.getString(2131296858), new Object[]{Long.valueOf(15 - ((System.currentTimeMillis() - m6896a()) / 60000))});
    }
}
