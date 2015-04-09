package mobile.goeuro.ebeletskiy.goeuromobiletest.utils.location;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import timber.log.Timber;

public class UserLocationProvider
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

  private final GoogleApiClient.Builder builder;
  private GoogleApiClient mApiClient;

  public UserLocationProvider(GoogleApiClient.Builder builder) {

    this.builder = builder;
  }

  public void connect() {
    Timber.d("Connecting to location services...");

    if (mApiClient != null && (mApiClient.isConnected() || mApiClient.isConnecting())) {
      Timber.e("Looks like we connected already");
      return;
    }

    if (mApiClient == null) {
      mApiClient = builder.addConnectionCallbacks(this)
          .addOnConnectionFailedListener(this)
          .addApi(LocationServices.API)
          .build();
    }

    mApiClient.connect();
  }

  public void disconnect() {
    if (mApiClient != null && (mApiClient.isConnected() || mApiClient.isConnecting())) {
      mApiClient.disconnect();
    }
  }

  @Override public void onConnected(Bundle bundle) {
    Timber.d("onConnected");

    Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
    if (lastLocation != null) {
      Timber.d(
          "Location fetched " + lastLocation.getLatitude() + " " + lastLocation.getLongitude());
    } else {
      Timber.d("last location is null");
    }
  }

  @Override public void onConnectionSuspended(int i) {
    Timber.d("onConnectionSuspended");
  }

  @Override public void onConnectionFailed(ConnectionResult connectionResult) {
    Timber.d("onConnectionFailed");
  }
}
