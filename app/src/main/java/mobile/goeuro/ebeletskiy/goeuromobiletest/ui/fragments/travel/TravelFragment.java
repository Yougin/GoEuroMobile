package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.modules.screens.TravelModule;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.base.InjectableFragment;

public class TravelFragment extends InjectableFragment implements TravelView {

  @Inject TravelPresenter presenter;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_travel, container, false);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override public void onPause() {
    super.onPause();
    presenter.onPause();
  }

  @Override public void showViews() {

  }

  @Override public void hideViews() {

  }

  @Override public void showProgressBar() {

  }

  @Override public void hideProgressBar() {

  }

  @Override public void setErrorMessage(String string) {

  }

  @Override public Object getModules() {
    return new TravelModule(this);
  }
}
