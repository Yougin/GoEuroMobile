package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.modules.screens.TravelModule;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.base.InjectableFragment;

public class TravelFragment extends InjectableFragment implements TravelView {

  @Inject TravelPresenter presenter;

  @InjectView(R.id.travel_from_autocomplete) AutoCompleteTextView fromTextView;
  @InjectView(R.id.travel_to_autocomplete) AutoCompleteTextView toTextView;
  @InjectView(R.id.travel_search_button) Button searchButton;
  @InjectView(R.id.travel_progress_bar) ProgressBar progressBar;
  @InjectView(R.id.travel_error_message) TextView errorMessage;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_travel, container, false);
    ButterKnife.inject(this, view);
    return view;
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
    fromTextView.setVisibility(View.VISIBLE);
    toTextView.setVisibility(View.VISIBLE);
    searchButton.setVisibility(View.VISIBLE);
  }

  @Override public void hideViews() {
    fromTextView.setVisibility(View.INVISIBLE);
    toTextView.setVisibility(View.INVISIBLE);
    searchButton.setVisibility(View.INVISIBLE);

  }

  @Override public void showProgressBar() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBar() {
    progressBar.setVisibility(View.INVISIBLE);
  }

  @Override public void setErrorMessage(String message) {
    errorMessage.setText(message);
  }

  @Override public void showErrorMessage() {
    errorMessage.setVisibility(View.VISIBLE);
  }

  @Override public void hideErrorMessage() {
    errorMessage.setVisibility(View.INVISIBLE);
  }

  @Override public Object getModules() {
    return new TravelModule(this);
  }
}
