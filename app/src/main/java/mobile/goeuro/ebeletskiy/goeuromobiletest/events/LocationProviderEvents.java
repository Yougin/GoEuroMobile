package mobile.goeuro.ebeletskiy.goeuromobiletest.events;

import android.location.Location;

public class LocationProviderEvents {

  public static class LastKnownLocationSuccessEvent {
    private Location location;

    public Location getLocation() {
      return location;
    }

    public void setLocation(Location location) {
      this.location = location;
    }

    @Override public String toString() {
      return "LastKnownLocation{" +
          "location=" + location +
          '}';
    }
  }

  public static class LastKnownLocationFailEvent {

  }
}
