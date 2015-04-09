package mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model;

import android.location.Location;
import java.util.Comparator;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.helpers.Preconditions;
import org.jetbrains.annotations.NotNull;

public class DistanceComparator implements Comparator<Location> {

  private final Location userLocation;

  public DistanceComparator(@NotNull Location userLocation) {
    this.userLocation = Preconditions.checkNotNull(userLocation);
  }

  @Override public int compare(Location lhs, Location rhs) {
    int firstDistance = (int) lhs.distanceTo(userLocation);
    int secondDistance = (int) rhs.distanceTo(userLocation);

    return firstDistance - secondDistance;
  }
}
