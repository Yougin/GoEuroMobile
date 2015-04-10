package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.widget.ArrayAdapter;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import org.jetbrains.annotations.NotNull;

public interface TravelView {
  void showViews();

  void hideViews();

  void hideProgressBar();

  void showErrorMessage(String errorMessage);

  void setAdapterForToView(@NotNull ArrayAdapter<DestinationPoint> adapter);

  void setAdapterForFromView(@NotNull ArrayAdapter<DestinationPoint> adapter);

  void enableSearchButton(boolean isEnable);

  void showCalendarView();

  void setDate(String date);
}
