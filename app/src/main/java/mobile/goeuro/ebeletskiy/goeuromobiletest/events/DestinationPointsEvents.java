package mobile.goeuro.ebeletskiy.goeuromobiletest.events;

import java.util.Collections;
import java.util.List;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import org.jetbrains.annotations.NotNull;

public class DestinationPointsEvents {

  public static class SuccessEvent {

    private List<DestinationPoint> destinationPoints;

    @NotNull public List<DestinationPoint> getDestinationPoints() {
      if (destinationPoints == null) {
        return Collections.emptyList();
      }

      return destinationPoints;
    }

    public void setDestinationPoints(List<DestinationPoint> destinationPoints) {
      this.destinationPoints = destinationPoints;
    }
  }

  public static class FailEvent {

  }
}
