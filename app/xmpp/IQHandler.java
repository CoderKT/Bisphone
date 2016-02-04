package app.xmpp;

import android.content.Context;
import app.Main;

public class IQHandler {
    private static IQHandler f4932a;
    private Context f4933b;

    static {
        f4932a = null;
    }

    private IQHandler(Context context) {
        this.f4933b = context;
    }

    public static IQHandler m7384a() {
        if (f4932a == null) {
            f4932a = new IQHandler(Main.f1927b);
        }
        return f4932a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m7385a(app.xmpp.packet.sublist.Sublist r12) {
        /*
        r11 = this;
        r10 = 1;
        r2 = 0;
        r0 = org.jivesoftware.smack.packet.IQ.Type.result;
        r0 = r0.toString();
        r1 = r12.getType();
        r1 = r1.toString();
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x001e;
    L_0x0016:
        r0 = app.Main.f1926a;
        r1 = "handleIncomingSubList: IQ is not of type RESULT";
        r0.m5679b(r1);
    L_0x001d:
        return;
    L_0x001e:
        r0 = app.Main.f1926a;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "sublist is ";
        r1 = r1.append(r3);
        r3 = r12.toString();
        r1 = r1.append(r3);
        r1 = r1.toString();
        r0.m5683d(r1);
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = r12.m7717a();
        r5 = r0.iterator();
    L_0x004c:
        r0 = r5.hasNext();
        if (r0 == 0) goto L_0x0064;
    L_0x0052:
        r0 = r5.next();
        r0 = (app.xmpp.packet.sublist.Sublist.Item) r0;
        r1 = r0.m7711a();
        if (r1 == 0) goto L_0x0064;
    L_0x005e:
        r6 = r0.m7713b();
        if (r6 != 0) goto L_0x00d0;
    L_0x0064:
        r0 = app.database.provider.ContactProvider.f2364a;
        r0 = android.content.ContentProviderOperation.newDelete(r0);
        r1 = "status=?";
        r5 = new java.lang.String[r10];
        r6 = app.common.entity.ContactEntity.STATUS.REMOVED;
        r6 = r6.toString();
        r5[r2] = r6;
        r0 = r0.withSelection(r1, r5);
        r0 = r0.build();
        r4.add(r0);
        r0 = new android.content.ContentValues;
        r0.<init>(r10);
        r1 = "status";
        r5 = app.common.entity.ContactEntity.STATUS.SYNCED;
        r5 = r5.toString();
        r0.put(r1, r5);
        r1 = app.database.provider.ContactProvider.f2364a;
        r1 = android.content.ContentProviderOperation.newUpdate(r1);
        r5 = "status!=?";
        r6 = new java.lang.String[r10];
        r7 = app.common.entity.ContactEntity.STATUS.SYNCED;
        r7 = r7.toString();
        r6[r2] = r7;
        r1 = r1.withSelection(r5, r6);
        r0 = r1.withValues(r0);
        r0 = r0.build();
        r4.add(r0);
        r0 = r11.f4933b;	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r0 = r0.getContentResolver();	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r1 = "com.bistalk.bisphone.provider.contacts";
        r1 = r0.applyBatch(r1, r4);	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        if (r1 == 0) goto L_0x0191;
    L_0x00c0:
        r5 = r1.length;	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r0 = r2;
    L_0x00c2:
        if (r0 >= r5) goto L_0x0179;
    L_0x00c4:
        r6 = r1[r0];	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r6 = r6.count;	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r6 = r6.intValue();	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r2 = r2 + r6;
        r0 = r0 + 1;
        goto L_0x00c2;
    L_0x00d0:
        r6 = "@";
        r6 = r1.contains(r6);
        if (r6 == 0) goto L_0x00e2;
    L_0x00d8:
        r6 = "@";
        r6 = r1.indexOf(r6);
        r1 = r1.substring(r2, r6);
    L_0x00e2:
        r6 = "a";
        r7 = r0.m7713b();
        r6 = r6.equals(r7);
        if (r6 == 0) goto L_0x0156;
    L_0x00ee:
        r0 = new android.content.ContentValues;
        r0.<init>(r10);
        r6 = "status";
        r7 = app.common.entity.ContactEntity.STATUS.SYNCED;
        r7 = r7.toString();
        r0.put(r6, r7);
        r6 = "is_registered";
        r7 = java.lang.Boolean.valueOf(r10);
        r0.put(r6, r7);
        r6 = "type";
        r7 = app.common.entity.ContactEntity.TYPE.LOCAL;
        r7 = r7.name();
        r0.put(r6, r7);
        r6 = app.database.provider.ContactProvider.f2364a;
        r6 = android.content.ContentProviderOperation.newDelete(r6);
        r7 = "username=? AND type=?";
        r8 = 2;
        r8 = new java.lang.String[r8];
        r8[r2] = r1;
        r9 = app.common.entity.ContactEntity.TYPE.REMOTE;
        r9 = r9.name();
        r8[r10] = r9;
        r6 = r6.withSelection(r7, r8);
        r6 = r6.build();
        r4.add(r6);
        r6 = app.database.provider.ContactProvider.f2364a;
        r6 = android.content.ContentProviderOperation.newUpdate(r6);
        r7 = "username=?";
        r8 = new java.lang.String[r10];
        r8[r2] = r1;
        r6 = r6.withSelection(r7, r8);
        r0 = r6.withValues(r0);
        r0 = r0.build();
        r4.add(r0);
        r0 = app.util.Utils.m7078a(r1);
        r3.add(r0);
        goto L_0x004c;
    L_0x0156:
        r6 = "m";
        r7 = r0.m7713b();
        r6 = r6.equals(r7);
        if (r6 == 0) goto L_0x016b;
    L_0x0162:
        r0 = app.util.Utils.m7078a(r1);
        r3.add(r0);
        goto L_0x004c;
    L_0x016b:
        r1 = "r";
        r0 = r0.m7713b();
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x004c;
    L_0x0177:
        goto L_0x004c;
    L_0x0179:
        r0 = app.Main.f1926a;	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r1 = new java.lang.StringBuilder;	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r1.<init>();	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r5 = "Sublist batch update count: ";
        r1 = r1.append(r5);	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r1 = r1.append(r2);	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r1 = r1.toString();	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
        r0.m5681c(r1);	 Catch:{ RemoteException -> 0x01a1, OperationApplicationException -> 0x01c2 }
    L_0x0191:
        r0 = app.xmpp.VCardHandler.m7499a();
        r0.m7503a(r3);
        r0 = app.database.datasource.ContactDataSource.m4553a();
        r0.m4583h();
        goto L_0x001d;
    L_0x01a1:
        r0 = move-exception;
        r1 = app.Main.f1926a;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r5 = "RemoteException when handling sublist result, operations: ";
        r2 = r2.append(r5);
        r4 = r4.toString();
        r2 = r2.append(r4);
        r2 = r2.toString();
        r1.m5683d(r2);
        r0.printStackTrace();
        goto L_0x0191;
    L_0x01c2:
        r0 = move-exception;
        r1 = app.Main.f1926a;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r5 = "OperationApplicationException when handling sublist result, operations: ";
        r2 = r2.append(r5);
        r4 = r4.toString();
        r2 = r2.append(r4);
        r2 = r2.toString();
        r1.m5683d(r2);
        r0.printStackTrace();
        goto L_0x0191;
        */
        throw new UnsupportedOperationException("Method not decompiled: app.xmpp.IQHandler.a(app.xmpp.packet.sublist.Sublist):void");
    }
}
