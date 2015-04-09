package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import de.greenrobot.event.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class TravelPresenterImplTest {

  @Mock TravelView view;
  @Mock EventBus bus;
  @Mock TravelInteractor interactor;

  private TravelPresenterImpl presenter;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    presenter = new TravelPresenterImpl(view, bus, interactor);
  }

  @Test public void should_register_to_bus_event_on_resume() throws Exception {
    presenter.onResume();

    verify(bus).registerSticky(presenter);
  }

  @Test public void should_unregister_from_bus_on_pause() throws Exception {
    presenter.onPause();

    verify(bus).unregister(presenter);
  }
}