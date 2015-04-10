package mobile.goeuro.ebeletskiy.goeuromobiletest.jobs;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.WebService;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.error.NetworkConnectionException;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.DestinationPointsEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.LanguageProvider;
import org.junit.Before;
import org.junit.Test;

import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.bus;
import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.failEvent;
import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.languageProvider;
import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.startedEvent;
import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.successEvent;
import static mobile.goeuro.ebeletskiy.goeuromobiletest.jobs.GetDestinationPointsJobTest.TestModule.webService;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetDestinationPointsJobTest {

  public static final String CITY_PARAMETER = "city";

  private GetDestinationPointsJob job;

  @Module(injects = GetDestinationPointsJob.class)
  public static class TestModule {

    public static EventBus bus;
    public static WebService webService;
    public static LanguageProvider languageProvider;

    public static DestinationPointsEvents.StartedEvent startedEvent;
    public static DestinationPointsEvents.SuccessEvent successEvent;
    public static DestinationPointsEvents.FailEvent failEvent;

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

    @Provides LanguageProvider provideLanguageProvider() {
      languageProvider = mock(LanguageProvider.class);
      return languageProvider;
    }
  }

  @Before public void setUp() throws Exception {
    job = new GetDestinationPointsJob(CITY_PARAMETER);
    ObjectGraph.create(new TestModule()).inject(job);
  }

  @Test public void should_invoke_getDestinationPoints_for_webservice_when_job_is_running()
      throws Exception, NetworkConnectionException {
    jobOnRun();

    verify(webService).getDestinationPoints(anyString(), anyString());
  }

  @Test public void should_invoke_getLanguage_for_language_provider_when_job_is_running()
      throws Exception {
    jobOnRun();

    verify(languageProvider).getUserLanguage();
  }

  @Test public void should_pass_exactly_the_city_the_job_was_initialized_with()
      throws Exception, NetworkConnectionException {
    jobOnRun();

    verify(webService).getDestinationPoints(anyString(), eq(CITY_PARAMETER));
  }

  @Test public void should_fire_failure_event_if_job_canceled() throws Exception {
    job.onCancel();

    verify(bus).post(failEvent);
  }

  @Test public void should_the_job_be_repeated_in_case_of_failure() throws Exception {
    boolean isRepeatable = job.shouldReRunOnThrowable(new RuntimeException());

    assertThat(isRepeatable, is(true));
  }

  @Test(expected = RuntimeException.class)
  public void should_runtime_exception_be_thrown_in_case_of_network_failure()
      throws Throwable {
    when(webService.getDestinationPoints(anyString(), anyString())).thenThrow(
        new NetworkConnectionException());

    job.onRun();
  }

  @Test
  public void should_set_points_to_success_event_and_fire_it_when_data_fetched()
      throws Exception, NetworkConnectionException {
    List<DestinationPoint> destinationPoints = createDestinationPoints();
    when(webService.getDestinationPoints(anyString(), anyString())).thenReturn(destinationPoints);

    jobOnRun();

    verify(successEvent).setDestinationPoints(destinationPoints);
    verify(bus).post(successEvent);
  }

  private List<DestinationPoint> createDestinationPoints() {
    ArrayList<DestinationPoint> destinationPoints = new ArrayList<>();
    destinationPoints.add(new DestinationPoint());
    destinationPoints.add(new DestinationPoint());
    return destinationPoints;
  }

  private void jobOnRun() {
    try {
      job.onRun();
    } catch (Throwable throwable) {
      // catch silently
    }
  }
}