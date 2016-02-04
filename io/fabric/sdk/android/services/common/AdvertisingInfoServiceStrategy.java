package io.fabric.sdk.android.services.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import io.fabric.sdk.android.Fabric;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class AdvertisingInfoServiceStrategy implements AdvertisingInfoStrategy {
    private final Context f8208a;

    final class AdvertisingConnection implements ServiceConnection {
        private boolean f8205a;
        private final LinkedBlockingQueue<IBinder> f8206b;

        private AdvertisingConnection() {
            this.f8205a = false;
            this.f8206b = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f8206b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f8206b.clear();
        }

        public IBinder m12986a() {
            if (this.f8205a) {
                Fabric.m12905g().m12873d("Fabric", "getBinder already called");
            }
            this.f8205a = true;
            try {
                return (IBinder) this.f8206b.poll(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return null;
            }
        }
    }

    final class AdvertisingInterface implements IInterface {
        private final IBinder f8207a;

        public boolean m12988b() {
            /* JADX: method processing error */
/*
            Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r7 = this;
            r0 = 1;
            r1 = 0;
            r2 = android.os.Parcel.obtain();
            r3 = android.os.Parcel.obtain();
            r4 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r2.writeInterfaceToken(r4);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = 1;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r2.writeInt(r4);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = r7.f8207a;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r5 = 2;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r6 = 0;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4.transact(r5, r2, r3, r6);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r3.readException();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = r3.readInt();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            if (r4 == 0) goto L_0x002a;
        L_0x0023:
            r3.recycle();
            r2.recycle();
        L_0x0029:
            return r0;
        L_0x002a:
            r0 = r1;
            goto L_0x0023;
        L_0x002c:
            r0 = move-exception;
            r0 = io.fabric.sdk.android.Fabric.m12905g();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = "Fabric";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r5 = "Could not get parcel from Google Play Service to capture Advertising limitAdTracking";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r0.m12867a(r4, r5);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r3.recycle();
            r2.recycle();
            r0 = r1;
            goto L_0x0029;
        L_0x0040:
            r0 = move-exception;
            r3.recycle();
            r2.recycle();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy.AdvertisingInterface.b():boolean");
        }

        public AdvertisingInterface(IBinder iBinder) {
            this.f8207a = iBinder;
        }

        public IBinder asBinder() {
            return this.f8207a;
        }

        public String m12987a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            String str = null;
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f8207a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str = obtain2.readString();
            } catch (Exception e) {
                Fabric.m12905g().m12867a("Fabric", "Could not get parcel from Google Play Service to capture AdvertisingId");
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return str;
        }
    }

    public AdvertisingInfoServiceStrategy(Context context) {
        this.f8208a = context.getApplicationContext();
    }

    public AdvertisingInfo m12989a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Fabric.m12905g().m12867a("Fabric", "AdvertisingInfoServiceStrategy cannot be called on the main thread");
            return null;
        }
        try {
            this.f8208a.getPackageManager().getPackageInfo("com.android.vending", 0);
            ServiceConnection advertisingConnection = new AdvertisingConnection();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (this.f8208a.bindService(intent, advertisingConnection, 1)) {
                    AdvertisingInterface advertisingInterface = new AdvertisingInterface(advertisingConnection.m12986a());
                    AdvertisingInfo advertisingInfo = new AdvertisingInfo(advertisingInterface.m12987a(), advertisingInterface.m12988b());
                    this.f8208a.unbindService(advertisingConnection);
                    return advertisingInfo;
                }
                Fabric.m12905g().m12867a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId");
                return null;
            } catch (Throwable e) {
                Fabric.m12905g().m12872c("Fabric", "Exception in binding to Google Play Service to capture AdvertisingId", e);
                this.f8208a.unbindService(advertisingConnection);
                return null;
            } catch (Throwable e2) {
                Fabric.m12905g().m12868a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId", e2);
                return null;
            }
        } catch (Throwable e22) {
            Fabric.m12905g().m12868a("Fabric", "Unable to determine if Google Play Services is available", e22);
            return null;
        }
    }
}
