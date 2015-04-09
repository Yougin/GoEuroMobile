package mobile.goeuro.ebeletskiy.goeuromobiletest.jobs;

import com.google.common.base.Preconditions;
import com.path.android.jobqueue.Params;
import de.greenrobot.event.EventBus;
import java.util.List;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.WebService;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.LanguageProvider;
import org.jetbrains.annotations.NotNull;

public class GetDestinationPointsJob extends BaseJob {

  @Inject EventBus bus;
  @Inject WebService webService;
  @Inject LanguageProvider languageProvider;

  @Inject DestinationPointsEvents.StartedEvent startedEvent;
  @Inject DestinationPointsEvents.SuccessEvent successEvent;
  @Inject DestinationPointsEvents.FailEvent failEvent;

  private String city;

  public GetDestinationPointsJob(@NotNull String city) {
    super(new Params(Priority.NORMAL).requireNetwork().persist());

    this.city =
        Preconditions.checkNotNull(city, "city can't be null, make sure you don't pass null here");
  }

  @Override public void onAdded() {
    bus.post(startedEvent);
  }

  @Override public void onRun() throws Throwable {
    List<DestinationPoint> destinationPoints =
        webService.getDestinationPoints(languageProvider.getUserLanguage(), city);
  }

  @Override protected void onCancel() {
    bus.postSticky(failEvent);
  }

  @Override protected boolean shouldReRunOnThrowable(Throwable throwable) {
    return true;
  }
}
