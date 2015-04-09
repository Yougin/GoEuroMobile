package mobile.goeuro.ebeletskiy.goeuromobiletest.jobs;

import com.path.android.jobqueue.Params;
import de.greenrobot.event.EventBus;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.WebService;

public class GetDestinationPointsJob extends BaseJob {

  @Inject public EventBus bus;
  @Inject public WebService webService;

  protected GetDestinationPointsJob() {
    super(new Params(Priority.NORMAL).requireNetwork().persist());
  }

  @Override public void onAdded() {

  }

  @Override public void onRun() throws Throwable {

  }

  @Override protected void onCancel() {

  }

  @Override protected boolean shouldReRunOnThrowable(Throwable throwable) {
    return true;
  }
}
