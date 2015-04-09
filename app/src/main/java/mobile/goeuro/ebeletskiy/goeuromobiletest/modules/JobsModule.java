package mobile.goeuro.ebeletskiy.goeuromobiletest.modules;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJob;

@Module(injects = GetDestinationPointsJob.class,
    complete = false)
public class JobsModule {

  @Provides @Singleton DestinationPointsEvents.StartedEvent provideStartedEvent() {
    return new DestinationPointsEvents.StartedEvent();
  }

  @Provides @Singleton DestinationPointsEvents.SuccessEvent provideSuccessEvent() {
    return new DestinationPointsEvents.SuccessEvent();
  }

  @Provides @Singleton DestinationPointsEvents.FailEvent provideFailEvent() {
    return new DestinationPointsEvents.FailEvent();
  }
}
