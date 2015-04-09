package mobile.goeuro.ebeletskiy.goeuromobiletest.modules.screens;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.modules.ApplicationModule;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel.TravelFragment;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel.TravelView;

@Module(injects = TravelFragment.class,
    addsTo = ApplicationModule.class, library = true, complete = false)
public class TravelModule {

  private final TravelView view;

  public TravelModule(TravelView view) {

    this.view = view;
  }

  @Provides @Singleton TravelView provideView() {
    return view;
  }
}
