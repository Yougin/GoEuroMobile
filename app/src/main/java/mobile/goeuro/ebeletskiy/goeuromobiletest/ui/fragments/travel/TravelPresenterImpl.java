package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.content.res.Resources;
import de.greenrobot.event.EventBus;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.helpers.Preconditions;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.location.ILocationProvider;

public class TravelPresenterImpl implements TravelPresenter {

  private final TravelView view;
  private final EventBus bus;
  private final TravelInteractor interactor;
  private final ILocationProvider locationProvider;
  private final Resources resources;

  @Inject public TravelPresenterImpl(TravelView view, EventBus bus, TravelInteractor interactor,
      ILocationProvider locationProvider, Resources resources) {
    this.view = Preconditions.checkNotNull(view, "view can't be null, check object instantiation");
    this.bus = Preconditions.checkNotNull(bus, "bus can't be null, check object instantiation");
    this.interactor = Preconditions.checkNotNull(interactor,
        "interactor can't be null, check object instantiation");
    this.locationProvider = Preconditions.checkNotNull(locationProvider,
        "locationProvider can't be null, check object instantiation");
    this.resources = Preconditions.checkNotNull(resources,
        "resources can't be null, check object instantiation");
  }

  @Override public void onResume() {
    bus.registerSticky(this);
    locationProvider.connect();
  }

  @Override public void onPause() {
    bus.unregister(this);
    locationProvider.disconnect();
  }

  @Override public void onEvent(LocationProviderEvents.LastKnownLocationSuccessEvent successEvent) {
    view.showViews();
    view.hideProgressBar();
  }

  @Override public void onEvent(LocationProviderEvents.LastKnownLocationFailEvent failEvent) {
    view.hideViews();
    view.hideProgressBar();
    view.setErrorMessage(resources.getString(R.string.failed_to_get_your_location));
  }
}
