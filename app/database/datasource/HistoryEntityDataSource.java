package app.database.datasource;

import android.database.Cursor;
import app.common.entity.HistoryChannelEntity.Builder;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;

public class HistoryEntityDataSource {
    public static HistoryEntity m4687a(Cursor cursor) {
        return new Builder().m4351a(cursor.getLong(cursor.getColumnIndexOrThrow("_id"))).m4359a(cursor.getString(cursor.getColumnIndexOrThrow("message_id"))).m4369c(cursor.getString(cursor.getColumnIndexOrThrow(Text.ELEMENT))).m4356a(Type.values()[cursor.getInt(cursor.getColumnIndexOrThrow("type"))]).m4365b(cursor.getString(cursor.getColumnIndex("jabber_id"))).m4372d(cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))).m4353a(DeliveryStatus.values()[cursor.getInt(cursor.getColumnIndexOrThrow(Status.ELEMENT))]).m4376e(cursor.getString(cursor.getColumnIndexOrThrow("thumbnail"))).m4378f(cursor.getString(cursor.getColumnIndexOrThrow("extra_data"))).m4366b();
    }
}
