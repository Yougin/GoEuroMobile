package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.content.res.Resources;
import android.location.Location;
import de.greenrobot.event.EventBus;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.comparators.DistanceComparator;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.comparators.DistanceComparatorFactory;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.helpers.Preconditions;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.location.ILocationProvider;
import org.jetbrains.annotations.NotNull;

public class TravelPresenterImpl implements TravelPresenter {

  private final TravelView view;
  private final EventBus bus;
  private final TravelInteractor interactor;
  private final ILocationProvider locationProvider;
  private final DistanceComparatorFactory distanceComparatorFactory;
  private final Resources resources;

  @Inject public TravelPresenterImpl(TravelView view, EventBus bus, TravelInteractor interactor,
      ILocationProvider locationProvider, Resources resources,
      DistanceComparatorFactory distanceComparatorFactory) {
    this.view = Preconditions.checkNotNull(view);
    this.bus = Preconditions.checkNotNull(bus);
    this.interactor = Preconditions.checkNotNull(interactor);
    this.locationProvider = Preconditions.checkNotNull(locationProvider);
    this.resources = Preconditions.checkNotNull(resources);
    this.distanceComparatorFactory = Preconditions.checkNotNull(distanceComparatorFactory);
  }

  @Override public void onResume() {
    bus.registerSticky(this);
    locationProvider.connect();
  }

  @Override public void onPause() {
    bus.unregister(this);
    locationProvider.disconnect();
  }

  @Override public void getDestinationPoints(@NotNull String city) {
    interactor.getDestinationPoints(city);
  }

  @Override public void onEvent(LocationProviderEvents.LastKnownLocationSuccessEvent successEvent) {
    view.showViews();
    view.hideProgressBar();
  }

  @Override public void onEvent(LocationProviderEvents.LastKnownLocationFailEvent failEvent) {
    view.hideViews();
    view.hideProgressBar();
    view.showErrorMessage();
    view.setErrorMessage(resources.getString(R.string.failed_to_get_your_location));
  }

  @Override public void onEventMainThread(DestinationPointsEvents.SuccessEvent successEvent) {
    List<DestinationPoint> destinationPoints = successEvent.getDestinationPoints();
    Location lastKnownUserLocation = locationProvider.getLastKnownUserLocation();

    if (lastKnownUserLocation == null) {
      view.showErrorMessage();
      view.setErrorMessage(resources.getString(R.string.failed_to_get_your_location));
      return;
    } else {
      view.hideErrorMessage();
    }

    Collections.sort(destinationPoints,
        distanceComparatorFactory.getInstance(lastKnownUserLocation));

    // TODO: set adapter on proper view
  }

  @Override public void onEventMainThread(DestinationPointsEvents.FailEvent failEvent) {
    // TODO: show error message
  }
}
