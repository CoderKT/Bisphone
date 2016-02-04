package com.google.android.gms.location;

import android.location.Location;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public interface FusedLocationProviderApi {
    Location m9344a(GoogleApiClient googleApiClient);

    PendingResult<Status> m9345a(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener);
}
