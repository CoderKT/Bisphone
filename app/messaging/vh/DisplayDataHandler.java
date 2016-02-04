package app.messaging.vh;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.Main;
import app.account.AccountManager;
import app.common.CustomImageLoader;
import app.common.entity.ChannelEntity;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.HistoryEntity;
import app.database.datasource.ChannelDataSource;
import app.database.datasource.ContactDataSource;
import app.events.ReplyCancelerEvent;
import app.profile.ProfileModel;
import app.profile.ProfileViewer;
import app.util.PixelConverter;
import app.xmpp.JabberId;
import app.xmpp.packet.channel.CastPX.Type;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class DisplayDataHandler {
    public static HashMap<String, ContactEntity> f4039a;
    private static String f4040b;

    /* renamed from: app.messaging.vh.DisplayDataHandler.1 */
    final class C03951 implements OnClickListener {
        final /* synthetic */ HistoryEntity f4037a;
        final /* synthetic */ Context f4038b;

        C03951(HistoryEntity historyEntity, Context context) {
            this.f4037a = historyEntity;
            this.f4038b = context;
        }

        public void onClick(View view) {
            ContactEntity contactEntity = (ContactEntity) DisplayDataHandler.f4039a.get(view.getTag(2131296309).toString());
            if (!this.f4037a.m4422i() && contactEntity != null && contactEntity.m4210m() != null) {
                Parcelable profileModel = new ProfileModel(contactEntity.m4212o(), contactEntity.m4211n(), contactEntity.m4192c(), contactEntity.m4210m() == TYPE.LOCAL ? contactEntity.m4196e() : contactEntity.m4209l(), contactEntity.m4200g(), contactEntity.m4210m(), contactEntity.m4214q().longValue(), contactEntity.m4209l());
                EventBus.m12779a().m12795d(new ReplyCancelerEvent());
                Intent intent = new Intent(this.f4038b, ProfileViewer.class);
                intent.putExtra(DataPacketExtension.ELEMENT, profileModel);
                this.f4038b.startActivity(intent);
            }
        }
    }

    public static void m6537a(Context context, HistoryEntity historyEntity, String str, TextView textView, ImageView imageView, View view, LinearLayout linearLayout, int i) {
        JabberId jabberId;
        if (f4039a == null) {
            f4039a = new HashMap();
        }
        f4040b = AccountManager.m3934a().m3937c();
        m6539a(textView, false);
        if (i == 2) {
            jabberId = new JabberId(str, "bisphone.com", null);
        } else if (i == 1 || i == 4) {
            jabberId = new JabberId(str, "bisphone.com", null);
        } else {
            jabberId = JabberId.m7386a(historyEntity.m4414c());
        }
        String str2 = "";
        if (jabberId != null) {
            str2 = jabberId.m7387a();
        }
        String o;
        if (str2 == null || str2.isEmpty() || str2.equals(AccountManager.m3934a().m3937c()) || ((historyEntity.m4422i() && i == 1) || ((str2.contains("-") && i != 3) || (str2.contains("-") && i == 3 && historyEntity.m4422i())))) {
            imageView.setOnClickListener(null);
            if (f4039a.get(f4040b) != null) {
                o = ((ContactEntity) f4039a.get(f4040b)).m4212o();
            } else {
                o = null;
            }
            CustomImageLoader.m4009a().m4020a(imageView, o, null, 2130837592);
            m6536a(context, linearLayout);
            imageView.setVisibility(0);
        } else if (jabberId != null && i == 3) {
            m6536a(context, linearLayout);
            m6538a(textView, "Private Message");
            if (historyEntity.m4398D() == Type.unicast) {
                m6539a(textView, true);
            } else {
                m6539a(textView, false);
            }
            jabberId = JabberId.m7386a(historyEntity.m4414c());
            if (jabberId != null) {
                ChannelEntity a = ChannelDataSource.m4537a(jabberId.m7390d());
                if (a != null) {
                    o = a.m4154d();
                    CustomImageLoader.m4009a().m4020a(imageView, o, null, 2130837592);
                }
            }
            o = null;
            CustomImageLoader.m4009a().m4020a(imageView, o, null, 2130837592);
        } else if (f4039a.get(str2) != null) {
            ContactEntity contactEntity = (ContactEntity) f4039a.get(str2);
            imageView.setTag(2131296309, str2);
            imageView.setOnClickListener(new C03951(historyEntity, context));
            if (i == 2) {
                CustomImageLoader.m4009a().m4020a(imageView, contactEntity.m4212o(), contactEntity.m4211n(), 2130837592);
                imageView.setVisibility(0);
                m6536a(context, linearLayout);
                if (historyEntity.m4418e() != HistoryEntity.Type.STICKER) {
                    o = contactEntity.m4196e() == null ? contactEntity.m4209l() == null ? context.getResources().getString(2131296307) : contactEntity.m4209l() : contactEntity.m4196e() + ":";
                    m6538a(textView, o);
                } else {
                    o = contactEntity.m4196e() == null ? contactEntity.m4209l() == null ? context.getResources().getString(2131296307) : contactEntity.m4209l() : contactEntity.m4196e();
                    m6538a(textView, o);
                }
                m6539a(textView, true);
                return;
            }
            CustomImageLoader.m4009a().m4020a(imageView, contactEntity.m4212o(), contactEntity.m4211n(), 2130837592);
            m6536a(context, linearLayout);
            imageView.setVisibility(0);
        } else {
            imageView.setOnClickListener(null);
            CustomImageLoader.m4009a().m4020a(imageView, null, null, 2130837592);
            m6536a(context, linearLayout);
            m6538a(textView, context.getResources().getString(2131296307));
            if (2 == i) {
                m6539a(textView, true);
            } else {
                m6539a(textView, false);
            }
        }
    }

    private static void m6536a(Context context, LinearLayout linearLayout) {
        LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.setMargins(0, (int) PixelConverter.m7048a(13.0f, context), 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setPadding(linearLayout.getPaddingLeft(), (int) PixelConverter.m7048a(1.0f, context), linearLayout.getPaddingRight(), (int) PixelConverter.m7048a(1.0f, context));
    }

    private static void m6538a(TextView textView, String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    private static void m6539a(TextView textView, boolean z) {
        if (textView == null) {
            return;
        }
        if (z) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }

    public static ContactEntity m6535a(String str) {
        if (f4039a != null && f4039a.get(str) != null) {
            return (ContactEntity) f4039a.get(str);
        }
        ContactEntity b = ContactDataSource.m4553a().m4570b(str);
        if (f4039a == null) {
            f4039a = new HashMap();
        }
        f4039a.put(str, b);
        return b;
    }

    public static String m6540b(String str) {
        ContactEntity a = m6535a(str);
        if (a == null) {
            return Main.f1927b.getString(2131296769);
        }
        if (a.m4210m() == TYPE.REMOTE) {
            return a.m4209l() == null ? Main.f1927b.getString(2131296769) : a.m4209l();
        } else {
            if (a.m4210m() == TYPE.LOCAL) {
                return a.m4196e();
            }
            if (a.m4210m() == TYPE.SELF) {
                return Main.f1927b.getString(2131296787);
            }
            return Main.f1927b.getString(2131296769);
        }
    }
}
