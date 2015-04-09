package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import de.greenrobot.event.EventBus;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.helpers.Preconditions;

public class TravelPresenterImpl implements TravelPresenter {

  private TravelView view;
  private EventBus bus;
  private TravelInteractor interactor;

  @Inject public TravelPresenterImpl(TravelView view, EventBus bus, TravelInteractor interactor) {
    this.view = Preconditions.checkNotNull(view, "view can't be null, check object instantiation");
    this.bus = Preconditions.checkNotNull(bus, "bus can't be null, check object instantiation");
    this.interactor = Preconditions.checkNotNull(interactor,
        "interactor can't be null, check object instantiation");
  }

  @Override public void onResume() {
    bus.registerSticky(this);
  }

  @Override public void onPause() {
    bus.unregister(this);
  }

  @Override public void onEvent(LocationProviderEvents.LastKnownLocationSuccessEvent successEvent) {

  }

  @Override public void onEvent(LocationProviderEvents.LastKnownLocationFailEvent failEvent) {

  }
}
