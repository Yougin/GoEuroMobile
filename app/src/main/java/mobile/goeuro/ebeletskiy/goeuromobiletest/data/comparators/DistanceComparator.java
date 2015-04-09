package mobile.goeuro.ebeletskiy.goeuromobiletest.data.comparators;

import android.location.Location;
import java.util.Comparator;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.helpers.Preconditions;
import org.jetbrains.annotations.NotNull;

public class DistanceComparator implements Comparator<DestinationPoint> {

  private final Location userLocation;

  public DistanceComparator(@NotNull Location userLocation) {
    this.userLocation = Preconditions.checkNotNull(userLocation);
  }

  @Override public int compare(DestinationPoint lhs, DestinationPoint rhs) {
    Location firstLocation = new Location("firstLocation");
    DestinationPoint.GeoPosition lGeoPosition = lhs.getGeoPosition();
    if (lGeoPosition != null) {
      firstLocation.setLatitude(lGeoPosition.getLatitude());
      firstLocation.setLongitude(lGeoPosition.getLongitude());
    }

    Location secondLocation = new Location("secondLocation");
    DestinationPoint.GeoPosition rGeoPosition = rhs.getGeoPosition();
    if (rGeoPosition != null) {
      secondLocation.setLatitude(rGeoPosition.getLatitude());
      secondLocation.setLongitude(rGeoPosition.getLongitude());
    }

    int firstDistance = (int) firstLocation.distanceTo(userLocation);
    int secondDistance = (int) secondLocation.distanceTo(userLocation);

    return firstDistance - secondDistance;
  }
}
