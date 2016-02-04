package app.signin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import app.Main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSReceiver {
    private VerificationFragment f4474a;
    private BroadcastReceiver f4475b;
    private IntentFilter f4476c;

    /* renamed from: app.signin.SMSReceiver.1 */
    class C04741 extends BroadcastReceiver {
        final /* synthetic */ SMSReceiver f4473a;

        C04741(SMSReceiver sMSReceiver) {
            this.f4473a = sMSReceiver;
        }

        public void onReceive(Context context, Intent intent) {
            Main.f1926a.m5681c("onReceive SMS");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Object[] objArr = (Object[]) extras.get("pdus");
                SmsMessage[] smsMessageArr = new SmsMessage[objArr.length];
                for (int i = 0; i < smsMessageArr.length; i++) {
                    smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
                    String messageBody = smsMessageArr[i].getMessageBody();
                    if (this.f4473a.m6859a(messageBody)) {
                        messageBody = this.f4473a.m6861b(messageBody);
                        if (messageBody != null) {
                            this.f4473a.f4474a.m6931a(messageBody);
                        }
                    }
                }
            }
        }
    }

    public SMSReceiver(VerificationFragment verificationFragment) {
        this.f4474a = verificationFragment;
        this.f4476c = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        this.f4476c.setPriority(999);
        this.f4475b = new C04741(this);
    }

    public void m6862a() {
        Main.f1927b.registerReceiver(this.f4475b, this.f4476c);
    }

    public void m6863b() {
        try {
            Main.f1927b.unregisterReceiver(this.f4475b);
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
    }

    private boolean m6859a(String str) {
        return str.startsWith(Main.f1927b.getString(2131296882)) || str.startsWith(Main.f1927b.getString(2131296883));
    }

    private String m6861b(String str) {
        Matcher matcher = Pattern.compile("\\d{4}").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
