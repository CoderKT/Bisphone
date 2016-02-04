package app.account;

import android.accounts.Account;
import android.content.Context;
import app.Main;
import se.emilsjolander.stickylistheaders.C1128R;

public class AccountManager {
    private static AccountManager f1955a;
    private static final Object f1956b;
    private android.accounts.AccountManager f1957c;
    private Account f1958d;

    static {
        f1955a = null;
        f1956b = new Object();
    }

    public static AccountManager m3934a() {
        if (f1955a == null) {
            synchronized (f1956b) {
                if (f1955a == null) {
                    f1955a = new AccountManager(Main.f1927b);
                }
            }
        }
        return f1955a;
    }

    private AccountManager(Context context) {
        this.f1957c = (android.accounts.AccountManager) context.getSystemService("account");
        Account[] accountsByType = this.f1957c.getAccountsByType("com.bistalk.bisphone");
        switch (accountsByType.length) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f1958d = null;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f1958d = accountsByType[0];
            default:
                throw new IllegalStateException("accounts found: " + accountsByType.length + "! only a maximum of one account is supported!");
        }
    }

    public boolean m3935a(String str, String str2) {
        this.f1958d = new Account(str, "com.bistalk.bisphone");
        return this.f1957c.addAccountExplicitly(this.f1958d, str2, null);
    }

    public void m3936b() {
        if (this.f1958d != null) {
            this.f1957c.removeAccount(this.f1958d, null, null);
            this.f1958d = null;
        }
    }

    public String m3937c() {
        if (this.f1958d == null) {
            return null;
        }
        return this.f1958d.name;
    }

    public String m3938d() {
        if (this.f1958d == null) {
            return null;
        }
        return this.f1957c.getPassword(this.f1958d);
    }

    public boolean m3939e() {
        return this.f1958d != null;
    }
}
