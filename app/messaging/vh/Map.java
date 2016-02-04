package app.messaging.vh;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import app.Main;
import app.common.entity.HistoryEntity;
import app.location.MapActivity;
import app.util.BitmapUtil;
import app.util.BubbleDialogUtil;

public class Map extends MessageViewHolder {
    ImageView f4052l;
    public TextView f4053m;

    /* renamed from: app.messaging.vh.Map.1 */
    class C03991 implements OnClickListener {
        final /* synthetic */ Map f4049a;

        C03991(Map map) {
            this.f4049a = map;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.f4049a.s, MapActivity.class);
            intent.putExtra("latitude", this.f4049a.F.m4433t());
            intent.putExtra("longitude", this.f4049a.F.m4434u());
            this.f4049a.s.startActivity(intent);
        }
    }

    /* renamed from: app.messaging.vh.Map.2 */
    class C04002 implements OnClickListener {
        final /* synthetic */ Map f4050a;

        C04002(Map map) {
            this.f4050a = map;
        }

        public void onClick(View view) {
            Main.m3905a(this.f4050a.f4053m.getText().toString());
        }
    }

    /* renamed from: app.messaging.vh.Map.3 */
    class C04013 implements OnLongClickListener {
        final /* synthetic */ Map f4051a;

        C04013(Map map) {
            this.f4051a = map;
        }

        public boolean onLongClick(View view) {
            BubbleDialogUtil.m6982a().m7003b(this.f4051a.s, this.f4051a.F, this.f4051a.J, this.f4051a.G, this.f4051a.t);
            return true;
        }
    }

    public Map(View view, Context context) {
        super(view, context);
    }

    public void m6554a(Context context, HistoryEntity historyEntity, int i, int i2) {
        super.m6519a(context, historyEntity, i, i2);
        CharSequence s = historyEntity.m4432s();
        if (s == null || s.equals("")) {
            this.f4053m.setVisibility(8);
        }
        this.f4053m.setText(s);
        try {
            this.f4052l.setImageBitmap(BitmapUtil.m6976b(historyEntity.m4423j()));
        } catch (Throwable th) {
        }
    }

    public void m6555y() {
        super.m6522y();
        this.v.setOnClickListener(new C03991(this));
        this.f4053m.setOnClickListener(new C04002(this));
        this.v.setOnLongClickListener(new C04013(this));
    }
}
