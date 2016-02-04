package app.location;

import android.content.Context;
import android.location.Address;
import android.util.Log;
import java.util.Locale;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.json.JSONArray;
import org.json.JSONObject;

public final class Geocoder {
    private static final String f3122a;
    private static final String f3123b;
    private final Context f3124c;

    public final class LimitExceededException extends Exception {
    }

    static {
        f3122a = Geocoder.class.getName() + ".GEOCODER";
        f3123b = Geocoder.class.getName() + ".KEY_ALLOW";
    }

    public Geocoder(Context context) {
        this.f3124c = context;
    }

    public GeocoderModel m5610a(double d, double d2, int i) {
        if (d < -90.0d || d > 90.0d) {
            throw new IllegalArgumentException("latitude == " + d);
        } else if (d2 < -180.0d || d2 > 180.0d) {
            throw new IllegalArgumentException("longitude == " + d2);
        } else if (m5608a(this.f3124c)) {
            throw new LimitExceededException();
        } else {
            GeocoderModel geocoderModel = new GeocoderModel();
            StringBuilder stringBuilder = new StringBuilder("http://maps.googleapis.com/maps/api/geocode/json?sensor=true&latlng=");
            stringBuilder.append(d);
            stringBuilder.append(',');
            stringBuilder.append(d2);
            stringBuilder.append("&language=");
            stringBuilder.append(Locale.ENGLISH);
            byte[] a = WebserviceClient.m5676a(stringBuilder.toString());
            if (a != null) {
                m5607a(geocoderModel, i, a);
            }
            return geocoderModel;
        }
    }

    private void m5607a(GeocoderModel geocoderModel, int i, byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr, StringUtils.UTF8));
            String string = jSONObject.getString(Status.ELEMENT);
            if (string.equals("OK")) {
                JSONArray jSONArray = jSONObject.getJSONArray("results");
                Address address = new Address(Locale.getDefault());
                jSONArray = jSONArray.getJSONObject(0).getJSONArray("address_components");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    jSONObject = jSONArray.getJSONObject(i2);
                    Log.d("temp is", jSONObject.toString());
                    JSONArray jSONArray2 = jSONObject.getJSONArray("types");
                    if (jSONArray2.toString().contains("street_number")) {
                        geocoderModel.m5613a(jSONObject.getString("long_name"));
                    } else if (jSONArray2.toString().contains("rout")) {
                        geocoderModel.m5615b(jSONObject.getString("long_name"));
                    } else if (jSONArray2.toString().contains("country")) {
                        geocoderModel.m5618d(jSONObject.getString("long_name"));
                    } else if (jSONArray2.toString().contains("political")) {
                        if (geocoderModel.m5614b().length() - geocoderModel.m5614b().replace("|", "").length() < 3 || jSONArray2.toString().contains("administrative_area_level_1")) {
                            geocoderModel.m5617c(jSONObject.getString("long_name"));
                        }
                    } else if (jSONArray2.toString().contains("sublocality")) {
                        geocoderModel.m5615b(geocoderModel.m5612a() + " " + jSONObject.getString("long_name"));
                    }
                }
            } else if (string.equals("OVER_QUERY_LIMIT")) {
                throw new LimitExceededException();
            }
        } catch (LimitExceededException e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean m5608a(Context context) {
        return System.currentTimeMillis() <= m5609b(context);
    }

    private static long m5609b(Context context) {
        return context.getSharedPreferences(f3122a, 0).getLong(f3123b, 0);
    }
}
