package mobile.goeuro.ebeletskiy.goeuromobiletest.modules;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.App;

@Module(injects = App.class,
    includes = ApiModule.class)
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

}
