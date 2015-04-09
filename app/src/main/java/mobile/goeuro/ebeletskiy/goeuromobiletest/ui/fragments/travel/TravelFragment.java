package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import mobile.goeuro.ebeletskiy.goeuromobiletest.modules.screens.TravelModule;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.base.InjectableFragment;

public class TravelFragment extends InjectableFragment implements TravelView {

  @Override public Object getModules() {
    return new TravelModule(this);
  }
}
