package app.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import app.SplashActivity;

public class Authenticator extends AbstractAccountAuthenticator {
    private final Context f1961a;

    public Authenticator(Context context) {
        super(context);
        this.f1961a = context;
    }

    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String str) {
        throw new UnsupportedOperationException();
    }

    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) {
        Parcelable intent = new Intent(this.f1961a, SplashActivity.class);
        intent.putExtra("accountAuthenticatorResponse", accountAuthenticatorResponse);
        intent.putExtra("com.bistalk.bisphone", str);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("intent", intent);
        return bundle2;
    }

    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) {
        return null;
    }

    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) {
        return null;
    }

    public String getAuthTokenLabel(String str) {
        return null;
    }

    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) {
        return null;
    }

    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strArr) {
        return null;
    }
}
