package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.content.res.Resources;
import android.location.Location;
import de.greenrobot.event.EventBus;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.comparators.DistanceComparatorFactory;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.adapters.DestinationPointsAdapter;
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
  private final DestinationPointsAdapter adapter;

  private boolean isEventForFromField;
  private boolean isToFieldFilledOut;
  private boolean isFromFieldFilledOut;

  @Inject public TravelPresenterImpl(TravelView view, EventBus bus, TravelInteractor interactor,
      ILocationProvider locationProvider, Resources resources,
      DistanceComparatorFactory distanceComparatorFactory, DestinationPointsAdapter adapter) {
    this.view = Preconditions.checkNotNull(view);
    this.bus = Preconditions.checkNotNull(bus);
    this.interactor = Preconditions.checkNotNull(interactor);
    this.locationProvider = Preconditions.checkNotNull(locationProvider);
    this.resources = Preconditions.checkNotNull(resources);
    this.distanceComparatorFactory = Preconditions.checkNotNull(distanceComparatorFactory);
    this.adapter = Preconditions.checkNotNull(adapter);
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
    view.showErrorMessage(resources.getString(R.string.failed_to_get_your_location));
  }

  @Override public void onEventMainThread(DestinationPointsEvents.SuccessEvent successEvent) {
    List<DestinationPoint> destinationPoints = successEvent.getDestinationPoints();
    Location lastKnownUserLocation = locationProvider.getLastKnownUserLocation();

    if (lastKnownUserLocation == null) {
      view.showErrorMessage(resources.getString(R.string.failed_to_get_your_location));
      return;
    }

    Collections.sort(destinationPoints,
        distanceComparatorFactory.getInstance(lastKnownUserLocation));

    adapter.setData(destinationPoints);
    setAdapter(adapter);
  }

  private void setAdapter(DestinationPointsAdapter adapter) {
    if (isEventForFromField) {
      view.setAdapterForFromView(adapter);
    } else {
      view.setAdapterForToView(adapter);
    }
  }

  @Override public void onEventMainThread(DestinationPointsEvents.FailEvent failEvent) {
    // TODO: next coding round
  }

  @Override public void setWhichTextViewToUpdate(TravelAutocompleteView which) {
    if (which == TravelAutocompleteView.FROM) {
      isEventForFromField = true;
    } else if (which == TravelAutocompleteView.TO) {
      isEventForFromField = false;
    }
  }

  @Override public void setToFieldFilledOut(boolean isFilled) {
    isToFieldFilledOut = isFilled;
    checkIfSearchButtonChangeState();
  }

  @Override public void setSearchButtonSecondFlag(boolean isFilled) {
    isFromFieldFilledOut = isFilled;
    checkIfSearchButtonChangeState();
  }

  private void checkIfSearchButtonChangeState() {
    view.enableSearchButton(isFromFieldFilledOut && isToFieldFilledOut);
  }

  @Override public void searchButtonClicked() {
    view.showErrorMessage(resources.getString(R.string.search_not_yet_implemented));
  }

  @Override public void calendarButtonClicked() {
    view.showCalendarView();
  }

  @Override public void onDateSelected(String date) {
    view.setDate(date);
  }
}
