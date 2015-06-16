package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.content.res.Resources;
import android.location.Location;
import de.greenrobot.event.EventBus;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.comparators.DistanceComparatorFactory;
import mobile.goeuro.ebeletskiy.goeuromobiletest.domain.interactor.UseCase;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.adapters.DestinationPointsAdapter;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.helpers.Preconditions;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.location.ILocationProvider;
import org.jetbrains.annotations.NotNull;
import rx.Subscriber;
import timber.log.Timber;

public class TravelPresenterImpl extends Subscriber<List<DestinationPoint>> implements TravelPresenter {

  private final TravelView view;
  private final EventBus bus;
  private final ILocationProvider locationProvider;
  private final DistanceComparatorFactory distanceComparatorFactory;
  private final Resources resources;
  private final DestinationPointsAdapter adapter;
  private final UseCase getDestinationPointsUseCase;

  private boolean isEventForFromField;
  private boolean isToFieldFilledOut;
  private boolean isFromFieldFilledOut;
  private boolean isCalendarFilledOut;

  @Inject public TravelPresenterImpl(TravelView view, EventBus bus,
      @Named("getDestinationPoints") UseCase getDestinationPointsUseCase,
      ILocationProvider locationProvider, Resources resources,
      DistanceComparatorFactory distanceComparatorFactory, DestinationPointsAdapter adapter) {
    this.getDestinationPointsUseCase = getDestinationPointsUseCase;
    this.view = Preconditions.checkNotNull(view);
    this.bus = Preconditions.checkNotNull(bus);
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
    getDestinationPointsUseCase.execute(this);
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

  private void setAdapter(DestinationPointsAdapter adapter) {
    if (isEventForFromField) {
      view.setAdapterForFromView(adapter);
    } else {
      view.setAdapterForToView(adapter);
    }
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

  @Override public void setFromFieldFilledOut(boolean isFilled) {
    isFromFieldFilledOut = isFilled;
    checkIfSearchButtonChangeState();
  }

  private void setCalendarFilledOut(boolean isFilled) {
    isCalendarFilledOut = isFilled;
    checkIfSearchButtonChangeState();
  }

  private void checkIfSearchButtonChangeState() {
    view.enableSearchButton(isFromFieldFilledOut && isToFieldFilledOut && isCalendarFilledOut);
  }

  @Override public void searchButtonClicked() {
    view.showErrorMessage(resources.getString(R.string.search_not_yet_implemented));
  }

  @Override public void calendarButtonClicked() {
    view.showCalendarView();
    setCalendarFilledOut(true);
  }

  @Override public void onDateSelected(String date) {
    view.setDate(date);
  }

  @Override public void onCompleted() {
    Timber.d(".......... Competed ..........");
  }

  @Override public void onError(Throwable e) {
    Timber.e(".......... Error ............");
  }

  @Override public void onNext(List<DestinationPoint> destinationPoints) {
    Timber.d("......... onNext ............");
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
}
