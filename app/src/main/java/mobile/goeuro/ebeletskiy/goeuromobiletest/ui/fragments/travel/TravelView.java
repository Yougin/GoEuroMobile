package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.widget.ArrayAdapter;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import org.jetbrains.annotations.NotNull;

public interface TravelView {
  void showViews();

  void hideViews();

  void showProgressBar();

  void hideProgressBar();

  void setErrorMessage(String message);

  void showErrorMessage();

  void hideErrorMessage();

  void setAdapterForToView(@NotNull ArrayAdapter<DestinationPoint> adapter);

  void setAdapterForFromView(@NotNull ArrayAdapter<DestinationPoint> adapter);
}
