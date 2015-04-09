package mobile.goeuro.ebeletskiy.goeuromobiletest.jobs;

import com.path.android.jobqueue.Params;
import de.greenrobot.event.EventBus;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.WebService;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;

public class GetDestinationPointsJob extends BaseJob {

  @Inject EventBus bus;
  @Inject WebService webService;

  @Inject DestinationPointsEvents.StartedEvent startedEvent;
  @Inject DestinationPointsEvents.SuccessEvent successEvent;
  @Inject DestinationPointsEvents.FailEvent failEvent;

  public GetDestinationPointsJob() {
    super(new Params(Priority.NORMAL).requireNetwork().persist());
  }

  @Override public void onAdded() {
    bus.post(startedEvent);
  }

  @Override public void onRun() throws Throwable {

  }

  @Override protected void onCancel() {

  }

  @Override protected boolean shouldReRunOnThrowable(Throwable throwable) {
    return true;
  }
}
