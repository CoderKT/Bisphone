package app.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.Main;
import com.google.android.gms.maps.MapFragment;

public class CustomMapFragment extends MapFragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (onCreateView != null) {
            View findViewById;
            try {
                findViewById = ((View) onCreateView.findViewById(1).getParent()).findViewById(2);
            } catch (Throwable e) {
                Main.f1926a.m5680b(e);
                findViewById = null;
            }
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
        }
        return onCreateView;
    }
}
