package mobile.goeuro.ebeletskiy.goeuromobiletest.jobs;

import android.content.Context;
import android.util.Log;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import org.jetbrains.annotations.NotNull;

public class JobManagerFactory {

  public static JobManager getConfiguredJobManager(@NotNull Context context) {
    return new JobManager(context, getConfiguration(context));
  }

  private static Configuration getConfiguration(Context context) {
    return new Configuration
        .Builder(context)
        .customLogger(new CustomLogger())
        .minConsumerCount(1)//always keep at least one consumer alive
        .maxConsumerCount(3) //up to 3 consumers at a time
        .loadFactor(3) //3 jobs per consumer
        .consumerKeepAlive(120) //wait 2 minute
        .injector(new JobDependencyInjector(context)).build();
  }

  private static class CustomLogger implements com.path.android.jobqueue.log.CustomLogger {
    private static final String TAG = "JOBS";

    @Override
    public boolean isDebugEnabled() {
      return true;
    }

    @Override
    public void d(String text, Object... args) {
      Log.d(TAG, String.format(text, args));
    }

    @Override
    public void e(Throwable t, String text, Object... args) {
      Log.e(TAG, String.format(text, args), t);
    }

    @Override
    public void e(String text, Object... args) {
      Log.e(TAG, String.format(text, args));
    }
  }
}
