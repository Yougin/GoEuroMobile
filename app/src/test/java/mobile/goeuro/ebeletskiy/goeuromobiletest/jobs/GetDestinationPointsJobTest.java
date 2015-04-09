package mobile.goeuro.ebeletskiy.goeuromobiletest.jobs;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.WebService;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import org.junit.Before;
import org.junit.Test;

import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.bus;
import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.startedEvent;

import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.webService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetDestinationPointsJobTest {

  private GetDestinationPointsJob job;

  @Module(injects = GetDestinationPointsJob.class)
  public static class TestModule {

    public static EventBus bus;
    static WebService webService;

    public static DestinationPointsEvents.StartedEvent startedEvent;
    static DestinationPointsEvents.SuccessEvent successEvent;
    static DestinationPointsEvents.FailEvent failEvent;

    @Provides EventBus provideEventBus() {
      bus = mock(EventBus.class);
      return bus;
    }

    @Provides WebService provideWebService() {
      webService = mock(WebService.class);
      return webService;
    }

    @Provides DestinationPointsEvents.StartedEvent provideStartEvent() {
      startedEvent = mock(DestinationPointsEvents.StartedEvent.class);
      return startedEvent;
    }

    @Provides DestinationPointsEvents.SuccessEvent provideSuccessEvent() {
      successEvent = mock(DestinationPointsEvents.SuccessEvent.class);
      return successEvent;
    }

    @Provides DestinationPointsEvents.FailEvent provideFailEvent() {
      failEvent = mock(DestinationPointsEvents.FailEvent.class);
      return failEvent;
    }
  }

  @Before public void setUp() throws Exception {
    job = new GetDestinationPointsJob();
    ObjectGraph.create(new TestModule()).inject(job);
  }

  @Test public void should_fire_start_event_when_job_added() throws Exception {
    job.onAdded();

    verify(bus).post(startedEvent);
  }

  //@Test public void should_invoke_getDestinationPoints_for_webservice_when_job_is_running()
  //    throws Exception {
  //  try {
  //    job.onRun();
  //  } catch (Throwable throwable) {
  //    // catch silently
  //  }
  //
  //  verify(webService).getDestinationPoints()
  //}
}