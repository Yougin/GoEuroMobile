package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import java.util.List;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import org.jetbrains.annotations.NotNull;

public interface TravelInteractor {

  List<DestinationPoint> getDestinationPoints(@NotNull String city);

}
