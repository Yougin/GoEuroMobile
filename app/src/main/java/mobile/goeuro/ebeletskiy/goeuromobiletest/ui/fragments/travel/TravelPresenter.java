package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import org.jetbrains.annotations.NotNull;

public interface TravelPresenter {

  void onResume();

  void onPause();

  void getDestinationPoints(@NotNull String city);

  void onEvent(LocationProviderEvents.LastKnownLocationSuccessEvent successEvent);

  void onEvent(LocationProviderEvents.LastKnownLocationFailEvent failEvent);

  void setWhichTextViewToUpdate(TravelAutocompleteView which);

  void setFromFieldFilledOut(boolean isFilled);

  void setToFieldFilledOut(boolean isFilled);

  void searchButtonClicked();

  void calendarButtonClicked();

  void onDateSelected(String date);
}
