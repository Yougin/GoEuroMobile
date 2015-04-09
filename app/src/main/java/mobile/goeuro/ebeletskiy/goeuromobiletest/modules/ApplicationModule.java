package mobile.goeuro.ebeletskiy.goeuromobiletest.modules;

import com.path.android.jobqueue.JobManager;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.App;
import mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.JobManagerFactory;

@Module(injects = App.class,
    includes = {
        ApiModule.class,
        JobsModule.class,
        UtilsModule.class
    },
    library = true) // TODO: remove library once all dependencies are in use
public class ApplicationModule {

  private App app;

  public ApplicationModule(App app) {
    this.app = app;
  }

  @Provides @Singleton App provideApplicationContext() {
    return app;
  }

  @Provides @Singleton EventBus provideEventBus() {
    return EventBus.builder().throwSubscriberException(true).build();
  }

  @Provides @Singleton JobManager provideJobManager(App context) {
    return JobManagerFactory.getConfiguredJobManager(context);
  }
}
