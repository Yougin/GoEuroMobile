package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import java.util.List;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import org.jetbrains.annotations.NotNull;

public class TravelInteractorImpl implements TravelInteractor {

  @Inject public TravelInteractorImpl() {
  }

  @Override public List<DestinationPoint> getDestinationPoints(@NotNull String city) {
    return null;
  }
}
