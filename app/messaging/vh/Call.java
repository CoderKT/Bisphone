package app.messaging.vh;

import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.CallStatus;
import app.util.BubbleDialogUtil;
import java.text.DecimalFormat;

public class Call extends MessageViewHolder {
    public TextView f4036l;

    /* renamed from: app.messaging.vh.Call.1 */
    class C03941 implements OnLongClickListener {
        final /* synthetic */ Call f4035a;

        C03941(Call call) {
            this.f4035a = call;
        }

        public boolean onLongClick(View view) {
            BubbleDialogUtil.m6982a().m7002a(this.f4035a.s, this.f4035a.F, this.f4035a.J, this.f4035a.G, this.f4035a.t);
            return true;
        }
    }

    public Call(View view, Context context) {
        super(view, context);
    }

    public void m6533a(Context context, HistoryEntity historyEntity, int i, int i2) {
        super.m6519a(context, historyEntity, i, i2);
        int o;
        int o2;
        DecimalFormat decimalFormat;
        if (CallStatus.INCOMING == CallStatus.m4441a(historyEntity.m4435v())) {
            o = historyEntity.m4428o() / 60;
            o2 = historyEntity.m4428o() % 60;
            decimalFormat = new DecimalFormat("00");
            this.f4036l.setText(decimalFormat.format((long) o) + ":" + decimalFormat.format((long) o2));
        } else if (CallStatus.MISSED == CallStatus.m4441a(historyEntity.m4435v())) {
            this.f4036l.setText(context.getResources().getString(2131296365));
        } else if (CallStatus.OUTGOING == CallStatus.m4441a(historyEntity.m4435v())) {
            o = historyEntity.m4428o() / 60;
            o2 = historyEntity.m4428o() % 60;
            decimalFormat = new DecimalFormat("00");
            this.f4036l.setText(decimalFormat.format((long) o) + ":" + decimalFormat.format((long) o2));
        }
    }

    public void m6534y() {
        super.m6522y();
        this.v.setOnLongClickListener(new C03941(this));
    }
}
