package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.support.v4.app.Fragment;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import org.jetbrains.annotations.NotNull;

public interface TravelPresenter {

  void onResume();

  void onPause();

  void getDestinationPoints(@NotNull String city);

  void onEvent(LocationProviderEvents.LastKnownLocationSuccessEvent successEvent);

  void onEvent(LocationProviderEvents.LastKnownLocationFailEvent failEvent);

  void onEventMainThread(DestinationPointsEvents.SuccessEvent successEvent);

  void onEventMainThread(DestinationPointsEvents.FailEvent failEvent);

  void setWhichTextViewToUpdate(TravelAutocompleteView which);

  void setSearchButtonSecondFlag(boolean b);

  void setToFieldFilledOut(boolean b);

  void searchButtonClicked();

  void calendarButtonClicked();

  void onDateSelected(String date);
}
