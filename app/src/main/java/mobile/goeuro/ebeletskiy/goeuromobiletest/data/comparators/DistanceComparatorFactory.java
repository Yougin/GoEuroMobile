package mobile.goeuro.ebeletskiy.goeuromobiletest.data.comparators;

import android.location.Location;

public class DistanceComparatorFactory {

  public DistanceComparator getInstance(Location userLocation) {
    return new DistanceComparator(userLocation);
  }

}
