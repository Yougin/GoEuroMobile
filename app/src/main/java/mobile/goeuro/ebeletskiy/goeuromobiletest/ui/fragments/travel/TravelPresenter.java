package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;

public interface TravelPresenter {

  void onResume();

  void onPause();

  void onEvent(LocationProviderEvents.LastKnownLocationSuccessEvent successEvent);

  void onEvent(LocationProviderEvents.LastKnownLocationFailEvent failEvent);
}
