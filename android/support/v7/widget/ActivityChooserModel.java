package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.time.packet.Time;
import org.xmlpull.v1.XmlSerializer;

class ActivityChooserModel extends DataSetObservable {
    private static final String f1273a;
    private static final Object f1274b;
    private static final Map<String, ActivityChooserModel> f1275c;
    private final Object f1276d;
    private final List<ActivityResolveInfo> f1277e;
    private final List<HistoricalRecord> f1278f;
    private final Context f1279g;
    private final String f1280h;
    private Intent f1281i;
    private ActivitySorter f1282j;
    private int f1283k;
    private boolean f1284l;
    private boolean f1285m;
    private boolean f1286n;
    private boolean f1287o;
    private OnChooseActivityListener f1288p;

    public final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo f1266a;
        public float f1267b;
        final /* synthetic */ ActivityChooserModel f1268c;

        public /* synthetic */ int compareTo(Object obj) {
            return m2689a((ActivityResolveInfo) obj);
        }

        public ActivityResolveInfo(ActivityChooserModel activityChooserModel, ResolveInfo resolveInfo) {
            this.f1268c = activityChooserModel;
            this.f1266a = resolveInfo;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.f1267b) + 31;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            if (Float.floatToIntBits(this.f1267b) != Float.floatToIntBits(((ActivityResolveInfo) obj).f1267b)) {
                return false;
            }
            return true;
        }

        public int m2689a(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.f1267b) - Float.floatToIntBits(this.f1267b);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("resolveInfo:").append(this.f1266a.toString());
            stringBuilder.append("; weight:").append(new BigDecimal((double) this.f1267b));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public interface ActivitySorter {
        void m2690a(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2);
    }

    public final class HistoricalRecord {
        public final ComponentName f1269a;
        public final long f1270b;
        public final float f1271c;

        public HistoricalRecord(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public HistoricalRecord(ComponentName componentName, long j, float f) {
            this.f1269a = componentName;
            this.f1270b = j;
            this.f1271c = f;
        }

        public int hashCode() {
            return (((((this.f1269a == null ? 0 : this.f1269a.hashCode()) + 31) * 31) + ((int) (this.f1270b ^ (this.f1270b >>> 32)))) * 31) + Float.floatToIntBits(this.f1271c);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            HistoricalRecord historicalRecord = (HistoricalRecord) obj;
            if (this.f1269a == null) {
                if (historicalRecord.f1269a != null) {
                    return false;
                }
            } else if (!this.f1269a.equals(historicalRecord.f1269a)) {
                return false;
            }
            if (this.f1270b != historicalRecord.f1270b) {
                return false;
            }
            if (Float.floatToIntBits(this.f1271c) != Float.floatToIntBits(historicalRecord.f1271c)) {
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("; activity:").append(this.f1269a);
            stringBuilder.append("; time:").append(this.f1270b);
            stringBuilder.append("; weight:").append(new BigDecimal((double) this.f1271c));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public interface OnChooseActivityListener {
        boolean m2691a(ActivityChooserModel activityChooserModel, Intent intent);
    }

    final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ ActivityChooserModel f1272a;

        private PersistHistoryAsyncTask(ActivityChooserModel activityChooserModel) {
            this.f1272a = activityChooserModel;
        }

        public /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2692a(objArr);
        }

        public Void m2692a(Object... objArr) {
            int i = 0;
            List list = (List) objArr[0];
            String str = (String) objArr[1];
            try {
                OutputStream openFileOutput = this.f1272a.f1279g.openFileOutput(str, 0);
                XmlSerializer newSerializer = Xml.newSerializer();
                try {
                    newSerializer.setOutput(openFileOutput, null);
                    newSerializer.startDocument(StringUtils.UTF8, Boolean.valueOf(true));
                    newSerializer.startTag(null, "historical-records");
                    int size = list.size();
                    while (i < size) {
                        HistoricalRecord historicalRecord = (HistoricalRecord) list.remove(0);
                        newSerializer.startTag(null, "historical-record");
                        newSerializer.attribute(null, "activity", historicalRecord.f1269a.flattenToString());
                        newSerializer.attribute(null, Time.ELEMENT, String.valueOf(historicalRecord.f1270b));
                        newSerializer.attribute(null, "weight", String.valueOf(historicalRecord.f1271c));
                        newSerializer.endTag(null, "historical-record");
                        i++;
                    }
                    newSerializer.endTag(null, "historical-records");
                    newSerializer.endDocument();
                    this.f1272a.f1284l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Throwable e2) {
                    Log.e(ActivityChooserModel.f1273a, "Error writing historical recrod file: " + this.f1272a.f1280h, e2);
                    this.f1272a.f1284l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (Throwable e22) {
                    Log.e(ActivityChooserModel.f1273a, "Error writing historical recrod file: " + this.f1272a.f1280h, e22);
                    this.f1272a.f1284l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (Throwable e222) {
                    Log.e(ActivityChooserModel.f1273a, "Error writing historical recrod file: " + this.f1272a.f1280h, e222);
                    this.f1272a.f1284l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (Throwable th) {
                    this.f1272a.f1284l = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e6) {
                        }
                    }
                }
            } catch (Throwable e2222) {
                Log.e(ActivityChooserModel.f1273a, "Error writing historical recrod file: " + str, e2222);
            }
            return null;
        }
    }

    static {
        f1273a = ActivityChooserModel.class.getSimpleName();
        f1274b = new Object();
        f1275c = new HashMap();
    }

    public int m2705a() {
        int size;
        synchronized (this.f1276d) {
            m2699e();
            size = this.f1277e.size();
        }
        return size;
    }

    public ResolveInfo m2707a(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.f1276d) {
            m2699e();
            resolveInfo = ((ActivityResolveInfo) this.f1277e.get(i)).f1266a;
        }
        return resolveInfo;
    }

    public int m2706a(ResolveInfo resolveInfo) {
        synchronized (this.f1276d) {
            m2699e();
            List list = this.f1277e;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((ActivityResolveInfo) list.get(i)).f1266a == resolveInfo) {
                    return i;
                }
            }
            return -1;
        }
    }

    public Intent m2708b(int i) {
        synchronized (this.f1276d) {
            if (this.f1281i == null) {
                return null;
            }
            m2699e();
            ActivityResolveInfo activityResolveInfo = (ActivityResolveInfo) this.f1277e.get(i);
            ComponentName componentName = new ComponentName(activityResolveInfo.f1266a.activityInfo.packageName, activityResolveInfo.f1266a.activityInfo.name);
            Intent intent = new Intent(this.f1281i);
            intent.setComponent(componentName);
            if (this.f1288p != null) {
                if (this.f1288p.m2691a(this, new Intent(intent))) {
                    return null;
                }
            }
            m2694a(new HistoricalRecord(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public ResolveInfo m2709b() {
        synchronized (this.f1276d) {
            m2699e();
            if (this.f1277e.isEmpty()) {
                return null;
            }
            ResolveInfo resolveInfo = ((ActivityResolveInfo) this.f1277e.get(0)).f1266a;
            return resolveInfo;
        }
    }

    public void m2710c(int i) {
        synchronized (this.f1276d) {
            float f;
            m2699e();
            ActivityResolveInfo activityResolveInfo = (ActivityResolveInfo) this.f1277e.get(i);
            ActivityResolveInfo activityResolveInfo2 = (ActivityResolveInfo) this.f1277e.get(0);
            if (activityResolveInfo2 != null) {
                f = (activityResolveInfo2.f1267b - activityResolveInfo.f1267b) + 5.0f;
            } else {
                f = 1.0f;
            }
            m2694a(new HistoricalRecord(new ComponentName(activityResolveInfo.f1266a.activityInfo.packageName, activityResolveInfo.f1266a.activityInfo.name), System.currentTimeMillis(), f));
        }
    }

    private void m2698d() {
        if (!this.f1285m) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        } else if (this.f1286n) {
            this.f1286n = false;
            if (!TextUtils.isEmpty(this.f1280h)) {
                AsyncTaskCompat.m691a(new PersistHistoryAsyncTask(), new ArrayList(this.f1278f), this.f1280h);
            }
        }
    }

    private void m2699e() {
        int g = m2701g() | m2702h();
        m2703i();
        if (g != 0) {
            m2700f();
            notifyChanged();
        }
    }

    private boolean m2700f() {
        if (this.f1282j == null || this.f1281i == null || this.f1277e.isEmpty() || this.f1278f.isEmpty()) {
            return false;
        }
        this.f1282j.m2690a(this.f1281i, this.f1277e, Collections.unmodifiableList(this.f1278f));
        return true;
    }

    private boolean m2701g() {
        if (!this.f1287o || this.f1281i == null) {
            return false;
        }
        this.f1287o = false;
        this.f1277e.clear();
        List queryIntentActivities = this.f1279g.getPackageManager().queryIntentActivities(this.f1281i, 0);
        int size = queryIntentActivities.size();
        for (int i = 0; i < size; i++) {
            this.f1277e.add(new ActivityResolveInfo(this, (ResolveInfo) queryIntentActivities.get(i)));
        }
        return true;
    }

    private boolean m2702h() {
        if (!this.f1284l || !this.f1286n || TextUtils.isEmpty(this.f1280h)) {
            return false;
        }
        this.f1284l = false;
        this.f1285m = true;
        m2704j();
        return true;
    }

    private boolean m2694a(HistoricalRecord historicalRecord) {
        boolean add = this.f1278f.add(historicalRecord);
        if (add) {
            this.f1286n = true;
            m2703i();
            m2698d();
            m2700f();
            notifyChanged();
        }
        return add;
    }

    private void m2703i() {
        int size = this.f1278f.size() - this.f1283k;
        if (size > 0) {
            this.f1286n = true;
            for (int i = 0; i < size; i++) {
                HistoricalRecord historicalRecord = (HistoricalRecord) this.f1278f.remove(0);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2704j() {
        /*
        r9 = this;
        r8 = 1;
        r0 = r9.f1279g;	 Catch:{ FileNotFoundException -> 0x00d3 }
        r1 = r9.f1280h;	 Catch:{ FileNotFoundException -> 0x00d3 }
        r1 = r0.openFileInput(r1);	 Catch:{ FileNotFoundException -> 0x00d3 }
        r2 = android.util.Xml.newPullParser();	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r0 = "UTF-8";
        r2.setInput(r1, r0);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r0 = 0;
    L_0x0013:
        if (r0 == r8) goto L_0x001d;
    L_0x0015:
        r3 = 2;
        if (r0 == r3) goto L_0x001d;
    L_0x0018:
        r0 = r2.next();	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        goto L_0x0013;
    L_0x001d:
        r0 = "historical-records";
        r3 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r0 = r0.equals(r3);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        if (r0 != 0) goto L_0x0052;
    L_0x0029:
        r0 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r2 = "Share records file does not start with historical-records tag.";
        r0.<init>(r2);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        throw r0;	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
    L_0x0031:
        r0 = move-exception;
        r2 = f1273a;	 Catch:{ all -> 0x00c8 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c8 }
        r3.<init>();	 Catch:{ all -> 0x00c8 }
        r4 = "Error reading historical recrod file: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c8 }
        r4 = r9.f1280h;	 Catch:{ all -> 0x00c8 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c8 }
        r3 = r3.toString();	 Catch:{ all -> 0x00c8 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x00c8 }
        if (r1 == 0) goto L_0x0051;
    L_0x004e:
        r1.close();	 Catch:{ IOException -> 0x00cf }
    L_0x0051:
        return;
    L_0x0052:
        r0 = r9.f1278f;	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r0.clear();	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
    L_0x0057:
        r3 = r2.next();	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        if (r3 != r8) goto L_0x0065;
    L_0x005d:
        if (r1 == 0) goto L_0x0051;
    L_0x005f:
        r1.close();	 Catch:{ IOException -> 0x0063 }
        goto L_0x0051;
    L_0x0063:
        r0 = move-exception;
        goto L_0x0051;
    L_0x0065:
        r4 = 3;
        if (r3 == r4) goto L_0x0057;
    L_0x0068:
        r4 = 4;
        if (r3 == r4) goto L_0x0057;
    L_0x006b:
        r3 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r4 = "historical-record";
        r3 = r4.equals(r3);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        if (r3 != 0) goto L_0x00a2;
    L_0x0077:
        r0 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r2 = "Share records file not well-formed.";
        r0.<init>(r2);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        throw r0;	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
    L_0x007f:
        r0 = move-exception;
        r2 = f1273a;	 Catch:{ all -> 0x00c8 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c8 }
        r3.<init>();	 Catch:{ all -> 0x00c8 }
        r4 = "Error reading historical recrod file: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c8 }
        r4 = r9.f1280h;	 Catch:{ all -> 0x00c8 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c8 }
        r3 = r3.toString();	 Catch:{ all -> 0x00c8 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x00c8 }
        if (r1 == 0) goto L_0x0051;
    L_0x009c:
        r1.close();	 Catch:{ IOException -> 0x00a0 }
        goto L_0x0051;
    L_0x00a0:
        r0 = move-exception;
        goto L_0x0051;
    L_0x00a2:
        r3 = 0;
        r4 = "activity";
        r3 = r2.getAttributeValue(r3, r4);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r4 = 0;
        r5 = "time";
        r4 = r2.getAttributeValue(r4, r5);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r4 = java.lang.Long.parseLong(r4);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r6 = 0;
        r7 = "weight";
        r6 = r2.getAttributeValue(r6, r7);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r6 = java.lang.Float.parseFloat(r6);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r7 = new android.support.v7.widget.ActivityChooserModel$HistoricalRecord;	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r7.<init>(r3, r4, r6);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        r0.add(r7);	 Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x007f }
        goto L_0x0057;
    L_0x00c8:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00ce;
    L_0x00cb:
        r1.close();	 Catch:{ IOException -> 0x00d1 }
    L_0x00ce:
        throw r0;
    L_0x00cf:
        r0 = move-exception;
        goto L_0x0051;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x00ce;
    L_0x00d3:
        r0 = move-exception;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActivityChooserModel.j():void");
    }
}
