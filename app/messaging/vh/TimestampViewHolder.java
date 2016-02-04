package app.messaging.vh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import app.common.entity.HistoryEntity;
import butterknife.ButterKnife;

public class TimestampViewHolder extends NonMessageViewHolder {
    public TextView f4097l;

    public TimestampViewHolder(View view) {
        super(view);
        ButterKnife.m7744a(this, view);
    }

    public void m6582a(Context context, HistoryEntity historyEntity, int i, int i2) {
        this.f4097l.setText(historyEntity.m4416d());
    }
}
