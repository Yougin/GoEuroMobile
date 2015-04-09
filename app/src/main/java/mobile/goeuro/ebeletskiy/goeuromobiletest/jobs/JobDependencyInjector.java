package mobile.goeuro.ebeletskiy.goeuromobiletest.jobs;

import android.content.Context;
import com.path.android.jobqueue.BaseJob;
import com.path.android.jobqueue.di.DependencyInjector;
import mobile.goeuro.ebeletskiy.goeuromobiletest.App;

public class JobDependencyInjector implements DependencyInjector {
  private Context context;

  public JobDependencyInjector(Context context) {
    this.context = context;
  }

  @Override public void inject(BaseJob job) {
    ((App) context).getObjectGraph().inject(job);
  }
}
