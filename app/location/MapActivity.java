package app.location;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import app.Main;
import app.common.BaseActivity;
import app.location.Geocoder.LimitExceededException;
import app.util.BitmapUtil;
import app.util.Utils;
import butterknife.ButterKnife;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;

public class MapActivity extends BaseActivity implements OnClickListener, ConnectionCallbacks, OnConnectionFailedListener, LocationListener, OnMapLoadedCallback, OnMapLongClickListener, OnMarkerClickListener, OnMarkerDragListener, OnMyLocationButtonClickListener {
    private boolean f3147A;
    private boolean f3148B;
    ImageView f3149o;
    FrameLayout f3150p;
    ImageView f3151q;
    GeocoderModel f3152r;
    Marker f3153s;
    Handler f3154t;
    private LocationRequest f3155u;
    private GoogleMap f3156v;
    private GoogleApiClient f3157w;
    private LatLng f3158x;
    private boolean f3159y;
    private String f3160z;

    /* renamed from: app.location.MapActivity.1 */
    class C02391 implements OnClickListener {
        final /* synthetic */ MapActivity f3136a;

        C02391(MapActivity mapActivity) {
            this.f3136a = mapActivity;
        }

        public void onClick(View view) {
            String str = "com.google.android.gms";
            this.f3136a.startActivity(Intent.createChooser(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.gms")), "Select your store ...."));
        }
    }

    /* renamed from: app.location.MapActivity.2 */
    class C02402 implements CancelableCallback {
        final /* synthetic */ MapActivity f3137a;

        C02402(MapActivity mapActivity) {
            this.f3137a = mapActivity;
        }

        public void m5623a() {
            this.f3137a.f3156v.m9601a(this.f3137a);
            Main.f1926a.m5685e("MapActivity: animate camera finished");
        }

        public void m5624b() {
            Main.f1926a.m5685e("MapActivity: animate camera canceled");
        }
    }

    /* renamed from: app.location.MapActivity.3 */
    class C02423 implements Runnable {
        final /* synthetic */ MapActivity f3139a;

        /* renamed from: app.location.MapActivity.3.1 */
        class C02411 implements SnapshotReadyCallback {
            final /* synthetic */ C02423 f3138a;

            C02411(C02423 c02423) {
                this.f3138a = c02423;
            }

            public void m5626a(Bitmap bitmap) {
                this.f3138a.f3139a.f3160z = BitmapUtil.m6978b(bitmap);
                this.f3138a.f3139a.m5658v();
            }
        }

        C02423(MapActivity mapActivity) {
            this.f3139a = mapActivity;
        }

        public void run() {
            this.f3139a.f3156v.m9606a(new C02411(this));
        }
    }

    /* renamed from: app.location.MapActivity.4 */
    class C02454 implements Runnable {
        final /* synthetic */ LatLng f3142a;
        final /* synthetic */ MapActivity f3143b;

        /* renamed from: app.location.MapActivity.4.1 */
        class C02431 implements Runnable {
            final /* synthetic */ C02454 f3140a;

            C02431(C02454 c02454) {
                this.f3140a = c02454;
            }

            public void run() {
                if (this.f3140a.f3143b.f3152r != null) {
                    this.f3140a.f3143b.f3153s.m10112a(this.f3140a.f3143b.getString(2131296330));
                    this.f3140a.f3143b.f3153s.m10114b(this.f3140a.f3143b.f3152r.toString());
                    if (this.f3140a.f3143b.f3152r.toString().length() == 0) {
                        this.f3140a.f3143b.f3153s.m10116d();
                    } else {
                        this.f3140a.f3143b.f3153s.m10115c();
                    }
                }
            }
        }

        /* renamed from: app.location.MapActivity.4.2 */
        class C02442 implements Runnable {
            final /* synthetic */ C02454 f3141a;

            C02442(C02454 c02454) {
                this.f3141a = c02454;
            }

            public void run() {
            }
        }

        C02454(MapActivity mapActivity, LatLng latLng) {
            this.f3143b = mapActivity;
            this.f3142a = latLng;
        }

        public void run() {
            try {
                Geocoder geocoder = new Geocoder(this.f3143b);
                this.f3143b.f3152r = null;
                if (this.f3142a.f6400a != 0.0d || this.f3142a.f6401b != 0.0d) {
                    try {
                        this.f3143b.f3152r = geocoder.m5610a(this.f3142a.f6400a, this.f3142a.f6401b, 5);
                    } catch (LimitExceededException e) {
                        e.printStackTrace();
                    }
                    if (this.f3143b.f3152r == null) {
                        throw new IOException("Manually thrown exception, returned address is empty");
                    }
                    this.f3143b.runOnUiThread(new C02431(this));
                }
            } catch (IOException e2) {
                this.f3143b.f3154t.post(new C02442(this));
                this.f3143b.f3152r = null;
            }
        }
    }

    /* renamed from: app.location.MapActivity.5 */
    class C02465 implements InfoWindowAdapter {
        final /* synthetic */ MapActivity f3144a;

        C02465(MapActivity mapActivity) {
            this.f3144a = mapActivity;
        }

        public View m5629a(Marker marker) {
            return null;
        }

        public View m5630b(Marker marker) {
            View inflate = this.f3144a.getLayoutInflater().inflate(2130903164, null);
            ((TextView) inflate.findViewById(2131755460)).setText(marker.m10113b());
            return inflate;
        }
    }

    /* renamed from: app.location.MapActivity.6 */
    class C02476 implements DialogInterface.OnClickListener {
        final /* synthetic */ MapActivity f3145a;

        C02476(MapActivity mapActivity) {
            this.f3145a = mapActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f3145a.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
    }

    /* renamed from: app.location.MapActivity.7 */
    class C02487 implements DialogInterface.OnClickListener {
        final /* synthetic */ MapActivity f3146a;

        C02487(MapActivity mapActivity) {
            this.f3146a = mapActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    public MapActivity() {
        this.f3147A = false;
        this.f3152r = null;
    }

    void m5670j() {
        this.f3150p.setVisibility(8);
    }

    boolean m5671k() {
        m5658v();
        return true;
    }

    void m5672l() {
        m5648b(false);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3147A = m5652p();
    }

    private boolean m5652p() {
        try {
            int i = getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
            int integer = getResources().getInteger(2131623942);
            Log.d("" + i, "" + integer);
            if (i < integer) {
                m5673m();
                return false;
            }
            m5653q();
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            m5673m();
            return false;
        }
    }

    private void m5653q() {
        this.f3154t = new Handler(Looper.getMainLooper());
        setContentView(2130903082);
        ButterKnife.m7741a((Activity) this);
        this.f3151q = (ImageView) findViewById(2131755205);
        this.f3151q.setOnClickListener(this);
        try {
            this.f3158x = new LatLng(getIntent().getExtras().getDouble("latitude"), getIntent().getExtras().getDouble("longitude"));
            this.f3159y = false;
        } catch (NullPointerException e) {
            this.f3158x = null;
            this.f3159y = true;
        }
        if (this.f3159y) {
            try {
                if (m5656t()) {
                    m5660x();
                }
            } catch (Throwable e2) {
                Main.f1926a.m5680b(e2);
            }
        }
        if (getActionBar() != null) {
            if (this.f3159y) {
                getActionBar().setTitle(getString(2131296736));
            } else {
                getActionBar().setTitle(getString(2131296740));
            }
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void m5673m() {
        setContentView(2130903083);
        findViewById(2131755208).setOnClickListener(new C02391(this));
    }

    protected void onResume() {
        super.onResume();
        this.f3148B = false;
        if (this.f3147A) {
            m5655s();
            m5654r();
            this.f3157w.m8384b();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.f3157w != null) {
            this.f3157w.m8386c();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.f3159y) {
            getMenuInflater().inflate(2131820557, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
            case 2131755630:
                if (this.f3158x != null) {
                    if (this.f3152r != null) {
                        if (this.f3152r.toString().length() != 0) {
                            if (!Utils.m7081a(this.f3156v.m9609b().f6375a.f6400a, this.f3158x.f6400a, 1.0E-4d) || !Utils.m7081a(this.f3156v.m9609b().f6375a.f6401b, this.f3158x.f6401b, 1.0E-4d)) {
                                Main.f1926a.m5685e("MapActivity: animate camera started");
                                this.f3156v.m9599a(m5642a(this.f3158x, this.f3156v.m9609b().f6376b, 0.0f, 0.0f), new C02402(this));
                                break;
                            }
                            Main.f1926a.m5685e("MapActivity: map camera is at center");
                            this.f3156v.m9601a((OnMapLoadedCallback) this);
                            break;
                        }
                        Main.m3905a(getString(2131296832));
                        break;
                    }
                    Main.m3905a(getString(2131296832));
                    break;
                }
                Main.m3905a(getString(2131296829));
                break;
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void m5654r() {
        if (this.f3157w == null) {
            if (this.f3159y) {
                Main.m3905a(getString(2131296833));
            }
            this.f3157w = new Builder(this).m8368a(LocationServices.f6205a).m8369a((ConnectionCallbacks) this).m8370a((OnConnectionFailedListener) this).m8372b();
        }
    }

    private void m5655s() {
        if (this.f3156v == null) {
            this.f3156v = ((CustomMapFragment) getFragmentManager().findFragmentById(2131755202)).m5606b();
            if (this.f3156v != null) {
                this.f3156v.m9611d().m9671a(false);
                this.f3156v.m9603a((OnMarkerClickListener) this);
                if (m5656t()) {
                    this.f3156v.m9608a(true);
                    this.f3156v.m9605a((OnMyLocationButtonClickListener) this);
                }
                this.f3156v.m9611d().m9672b(false);
                if (this.f3159y) {
                    this.f3156v.m9604a((OnMarkerDragListener) this);
                    this.f3156v.m9602a((OnMapLongClickListener) this);
                }
                m5659w();
                return;
            }
            Main.m3905a(getString(2131296831));
        }
    }

    private boolean m5656t() {
        return getPackageManager().hasSystemFeature("android.hardware.location.gps") || getPackageManager().hasSystemFeature("android.hardware.location.network");
    }

    private void m5648b(boolean z) {
        Location location = null;
        if (m5656t()) {
            location = LocationServices.f6206b.m9344a(this.f3157w);
        }
        if (location == null && this.f3159y) {
            Main.f1926a.m5679b("MapActivity: Location not found!");
            return;
        }
        if ((this.f3159y || this.f3148B) && location != null) {
            this.f3158x = new LatLng(location.getLatitude(), location.getLongitude());
        }
        m5645a(this.f3158x, false, z);
        this.f3148B = true;
    }

    private void m5645a(LatLng latLng, boolean z, boolean z2) {
        CameraUpdate a;
        if (z) {
            a = m5642a(latLng, this.f3156v.m9609b().f6376b, 0.0f, 0.0f);
        } else {
            a = m5649c(latLng);
        }
        this.f3156v.m9598a(a);
        if (z2) {
            this.f3156v.m9610c();
            m5647b(latLng);
        }
    }

    private void m5647b(LatLng latLng) {
        m5650d(latLng);
        m5651e(latLng);
    }

    private CameraUpdate m5649c(LatLng latLng) {
        return m5642a(latLng, 17.0f, 0.0f, 0.0f);
    }

    private CameraUpdate m5642a(LatLng latLng, float f, float f2, float f3) {
        return CameraUpdateFactory.m9558a(new CameraPosition.Builder().m10082a(latLng).m10081a(f).m10085c(f2).m10084b(f3).m10083a());
    }

    private void m5650d(LatLng latLng) {
        this.f3153s = this.f3156v.m9597a(new MarkerOptions().m10119a(latLng).m10118a(BitmapDescriptorFactory.m10078a(2130837762)).m10120a(true));
    }

    private void m5657u() {
        this.f3156v.m9608a(false);
        this.f3154t.postDelayed(new C02423(this), 500);
    }

    private void m5658v() {
        Intent intent = new Intent();
        intent.putExtra("extra_location_title", this.f3152r.m5616c());
        intent.putExtra("extra_location_address", this.f3152r.toString());
        intent.putExtra("extra_location_latitude", this.f3158x.f6400a);
        intent.putExtra("extra_location_longitude", this.f3158x.f6401b);
        intent.putExtra("extra_location_thumbnail", this.f3160z);
        setResult(-1, intent);
        finish();
    }

    private void m5651e(LatLng latLng) {
        new Thread(new C02454(this, latLng)).start();
    }

    private void m5659w() {
        this.f3156v.m9600a(new C02465(this));
    }

    private void m5660x() {
        LocationManager locationManager = (LocationManager) getSystemService("location");
        if (!locationManager.isProviderEnabled("gps") && !locationManager.isProviderEnabled("network")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, 2131558536);
            builder.m1980a(getString(2131296545));
            builder.m1986b(getString(2131296546));
            builder.m1981a(getString(2131296434), new C02476(this));
            builder.m1987b(getString(2131296380), new C02487(this));
            builder.m1989c(2130837729);
            builder.m1988b().show();
        }
    }

    public void m5662a(Bundle bundle) {
        this.f3155u = LocationRequest.m9356a();
        this.f3155u.m9360a(100);
        this.f3155u.m9361a(1000);
        LocationServices.f6206b.m9345a(this.f3157w, this.f3155u, this);
        m5648b(true);
        if (!this.f3159y) {
            m5645a(this.f3158x, false, false);
        }
    }

    public void m5666b(int i) {
        Main.m3905a(getString(2131296830));
    }

    public void m5663a(ConnectionResult connectionResult) {
        if (connectionResult.m8302a()) {
            try {
                connectionResult.m8301a(this, 9000);
            } catch (Throwable e) {
                Main.f1926a.m5682c(e);
            }
        }
    }

    public void m5661a(Location location) {
    }

    public boolean m5674n() {
        m5648b(false);
        return true;
    }

    public void m5664a(LatLng latLng) {
        this.f3158x = latLng;
        m5645a(latLng, true, true);
    }

    public void m5675o() {
        Main.f1926a.m5685e("MapActivity: onMapLoaded");
        m5657u();
    }

    public boolean m5665a(Marker marker) {
        if (this.f3152r != null) {
            marker.m10114b(this.f3152r.toString());
        }
        return false;
    }

    public void m5667b(Marker marker) {
        marker.m10116d();
    }

    public void m5668c(Marker marker) {
    }

    public void m5669d(Marker marker) {
        this.f3158x = marker.m10111a();
        m5651e(this.f3158x);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755205:
                if (this.f3152r != null && this.f3156v != null) {
                    this.f3156v.m9598a(CameraUpdateFactory.m9559a(this.f3153s.m10111a(), 17.0f));
                } else if (this.f3152r == null) {
                    Main.m3905a(getString(2131296829));
                } else {
                    Main.m3905a(getString(2131296831));
                }
            default:
        }
    }
}
