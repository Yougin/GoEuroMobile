package mobile.goeuro.ebeletskiy.goeuromobiletest.utils.location;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import de.greenrobot.event.EventBus;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.helpers.Preconditions;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class UserLocationProvider
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    ILocationProvider {

  private final GoogleApiClient.Builder builder;
  private final EventBus bus;
  private final LocationProviderEvents.LastKnownLocationSuccessEvent successEvent;

  private GoogleApiClient mApiClient;

  public UserLocationProvider(@NotNull GoogleApiClient.Builder builder, @NotNull EventBus bus,
      @NotNull LocationProviderEvents.LastKnownLocationSuccessEvent successEvent) {

    this.builder =
        Preconditions.checkNotNull(builder, "builder can't be null, verify object instantiation");
    this.bus =
        Preconditions.checkNotNull(bus, "bus can't be null, verify object instantiation");
    this.successEvent =
        Preconditions.checkNotNull(successEvent,
            "successEvent can't be null, verify object instantiation");
  }

  public void connect() {
    Timber.d("Connecting to location services...");

    if (isConnectedOrConnecting()) {
      Timber.e("Looks like we connected already");
      return;
    }

    createClientIfRequired();
    mApiClient.connect();
  }

  private void createClientIfRequired() {
    if (mApiClient == null) {
      mApiClient = builder.addConnectionCallbacks(this)
          .addOnConnectionFailedListener(this)
          .addApi(LocationServices.API)
          .build();
    }
  }

  public void disconnect() {
    if (isConnectedOrConnecting()) {
      mApiClient.disconnect();
    }
  }

  /**
   * Dear reviewers, I'm omitting location updates deliberately, to be implemented in next round!
   */
  @Override public void onConnected(Bundle bundle) {
    Timber.d("onConnected");

    Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
    if (lastLocation != null) {
      Timber.d(
          "Location fetched " + lastLocation.getLatitude() + " " + lastLocation.getLongitude());

      successEvent.setLocation(lastLocation);
      bus.postSticky(successEvent);
    } else {
      Timber.d("last location is null");
    }
  }

  @Override public void onConnectionSuspended(int i) {
    Timber.d("onConnectionSuspended - to be implemented next release");
  }

  @Override public void onConnectionFailed(ConnectionResult connectionResult) {
    Timber.d("onConnectionFailed - to be implemented next release");
  }

  private boolean isConnectedOrConnecting() {
    return mApiClient != null && (mApiClient.isConnected() || mApiClient.isConnecting());
  }
}
