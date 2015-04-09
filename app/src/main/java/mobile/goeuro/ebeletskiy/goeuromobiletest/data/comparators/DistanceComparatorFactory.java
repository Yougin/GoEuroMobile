package mobile.goeuro.ebeletskiy.goeuromobiletest.data.comparators;

import android.location.Location;
import org.jetbrains.annotations.NotNull;

public class DistanceComparatorFactory {

  public DistanceComparator getInstance(@NotNull Location userLocation) {
    return new DistanceComparator(userLocation);
  }

}
