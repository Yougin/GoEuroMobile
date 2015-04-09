package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import com.path.android.jobqueue.JobManager;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJob;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.helpers.Preconditions;
import org.jetbrains.annotations.NotNull;

public class TravelInteractorImpl implements TravelInteractor {

  private final JobManager jobManager;

  @Inject public TravelInteractorImpl(JobManager jobManager) {
    this.jobManager = Preconditions.checkNotNull(jobManager);
  }

  @Override public void getDestinationPoints(@NotNull String city) {
    jobManager.addJob(new GetDestinationPointsJob(city));
  }
}
