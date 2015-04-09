package mobile.goeuro.ebeletskiy.goeuromobiletest.jobs;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

public abstract class BaseJob extends Job {

  protected BaseJob(Params params) {
    super(params);
  }

  protected void stopJob() {
    throw new RuntimeException("Stop the job");
  }
}

