package app.messaging.vh;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.Main;
import app.account.AccountManager;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.GroupStatus;
import app.common.entity.HistoryGroupEntity;
import app.events.view_holder.ViewHolderTouchEvent;
import app.localization.Language;
import app.localization.Localize;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupStatusHolder extends NonMessageViewHolder {
    LinearLayout f4044l;
    TextView f4045m;
    private HistoryGroupEntity f4046n;
    private String f4047o;
    private String f4048p;

    /* renamed from: app.messaging.vh.GroupStatusHolder.1 */
    class C03961 implements OnClickListener {
        final /* synthetic */ GroupStatusHolder f4041a;

        C03961(GroupStatusHolder groupStatusHolder) {
            this.f4041a = groupStatusHolder;
        }

        public void onClick(View view) {
            EventBus.m12779a().m12795d(new ViewHolderTouchEvent(this.f4041a.t, false));
        }
    }

    /* renamed from: app.messaging.vh.GroupStatusHolder.2 */
    class C03972 implements OnLongClickListener {
        final /* synthetic */ GroupStatusHolder f4042a;

        C03972(GroupStatusHolder groupStatusHolder) {
            this.f4042a = groupStatusHolder;
        }

        public boolean onLongClick(View view) {
            EventBus.m12779a().m12795d(new ViewHolderTouchEvent(this.f4042a.t, true));
            return true;
        }
    }

    /* renamed from: app.messaging.vh.GroupStatusHolder.3 */
    /* synthetic */ class C03983 {
        static final /* synthetic */ int[] f4043a;

        static {
            f4043a = new int[GroupStatus.values().length];
            try {
                f4043a[GroupStatus.JOIN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4043a[GroupStatus.KICK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4043a[GroupStatus.LEAVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4043a[GroupStatus.CHANGE_INFO.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public GroupStatusHolder(View view) {
        super(view);
        ButterKnife.m7744a(this, view);
        this.f4047o = Main.f1927b.getString(2131296783);
        this.f4048p = " " + Main.f1927b.getString(2131296782);
    }

    public void m6551a(Context context, HistoryEntity historyEntity, int i, int i2) {
        this.s = context;
        this.t = i;
        m6552y();
        if (historyEntity == null || !(historyEntity instanceof HistoryGroupEntity)) {
            this.f4045m.setText("");
            return;
        }
        this.f4046n = (HistoryGroupEntity) historyEntity;
        String str = "";
        String string = context.getString(2131296769);
        String M = this.f4046n.m4449M();
        switch (C03983.f4043a[this.f4046n.m4436w().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                string = this.f4046n.m4438y();
                if (!AccountManager.m3934a().m3937c().equals(M)) {
                    str = context.getString(2131296513);
                    break;
                } else {
                    str = context.getString(2131296522);
                    break;
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                string = this.f4046n.m4437x();
                str = context.getString(2131296514);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f4045m.setText(context.getString(2131296515));
                return;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f4045m.setText(m6546a(this.f4046n.m4395A(), DisplayDataHandler.m6540b(this.f4046n.m4439z())));
                return;
        }
        this.f4045m.setText(String.format(str, new Object[]{DisplayDataHandler.m6540b(string), DisplayDataHandler.m6540b(M)}));
    }

    private String m6546a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder(str2 + " ");
        if (str.isEmpty()) {
            stringBuilder.append(Main.f1927b.getString(2131296512)).append(this.f4047o).append(" ");
            return stringBuilder.toString();
        }
        if (str.contains("av")) {
            stringBuilder.append(m6545a(str2));
        }
        if (str.contains("ti")) {
            stringBuilder.append(m6549b(str2));
        }
        if (str.contains("de")) {
            stringBuilder.append(m6547a(str2, this.f4046n.m4400F().isEmpty()));
        }
        while (Character.isWhitespace(stringBuilder.charAt(stringBuilder.length() - 1))) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (stringBuilder.lastIndexOf(this.f4047o) == stringBuilder.length() - this.f4047o.length()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        int lastIndexOf = stringBuilder.lastIndexOf(this.f4047o);
        if (lastIndexOf > -1) {
            stringBuilder.replace(lastIndexOf, this.f4047o.length() + lastIndexOf, this.f4048p);
        }
        stringBuilder.append(".");
        return stringBuilder.toString();
    }

    private String m6545a(String str) {
        String string;
        StringBuilder stringBuilder = new StringBuilder("");
        String string2 = Main.f1927b.getString(2131296504);
        if (str.equals(Main.f1927b.getString(2131296787))) {
            string = Main.f1927b.getString(2131296520);
        } else {
            string = Main.f1927b.getString(2131296516);
        }
        m6548a(stringBuilder, string2, string);
        return stringBuilder.toString();
    }

    private String m6549b(String str) {
        String string;
        StringBuilder stringBuilder = new StringBuilder("");
        String str2 = Main.f1927b.getString(2131296519) + " " + Main.f1927b.getString(2131296809) + " '" + this.f4046n.m4399E() + "'";
        if (str.equals(Main.f1927b.getString(2131296787))) {
            string = Main.f1927b.getString(2131296521);
        } else {
            string = Main.f1927b.getString(2131296517);
        }
        m6548a(stringBuilder, str2, string);
        return stringBuilder.toString();
    }

    private String m6547a(String str, boolean z) {
        String string;
        String string2;
        StringBuilder stringBuilder = new StringBuilder("");
        if (z) {
            string = Main.f1927b.getString(2131296506);
        } else {
            string = Main.f1927b.getString(2131296505) + " " + Main.f1927b.getString(2131296809) + " '" + this.f4046n.m4400F() + "'";
        }
        if (z) {
            if (str.equals(Main.f1927b.getString(2131296787))) {
                string2 = Main.f1927b.getString(2131296523);
            } else {
                string2 = Main.f1927b.getString(2131296518);
            }
        } else if (str.equals(Main.f1927b.getString(2131296787))) {
            string2 = Main.f1927b.getString(2131296521);
        } else {
            string2 = Main.f1927b.getString(2131296517);
        }
        m6548a(stringBuilder, string, string2);
        return stringBuilder.toString();
    }

    private void m6548a(StringBuilder stringBuilder, String str, String str2) {
        if (Localize.m5603d().equals(Language.PERSIAN)) {
            stringBuilder.append(str).append(" ").append(str2);
        } else {
            stringBuilder.append(str2).append(" ").append(str);
        }
        stringBuilder.append(this.f4047o).append(" ");
    }

    public void m6553z() {
        this.f4044l.setBackgroundResource(2130837874);
    }

    public void m6550A() {
        this.f4044l.setBackgroundResource(2130837873);
    }

    public void m6552y() {
        this.f4044l.setOnClickListener(new C03961(this));
        this.f4044l.setOnLongClickListener(new C03972(this));
    }
}
