package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import mobile.goeuro.ebeletskiy.goeuromobiletest.modules.screens.TravelModule;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.base.InjectableFragment;
import org.jetbrains.annotations.NotNull;

public class TravelFragment extends InjectableFragment implements TravelView {

  @Inject TravelPresenter presenter;

  @InjectView(R.id.travel_from_autocomplete) AutoCompleteTextView fromTextView;
  @InjectView(R.id.travel_to_autocomplete) AutoCompleteTextView toTextView;
  @InjectView(R.id.travel_search_button) Button searchButton;
  @InjectView(R.id.travel_progress_bar) ProgressBar progressBar;
  @InjectView(R.id.travel_calendar_button) Button calendarButton;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_travel, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // TODO: Refactor: make all observables as static classes and provide weak reference to this object
    fromTextView.addTextChangedListener(new FromTextWatcher());
    toTextView.addTextChangedListener(new ToTextWatcher());

    fromTextView.setOnItemClickListener(new FromOnItemClickListener());
    toTextView.setOnItemClickListener(new ToOnItemClickListener());
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
    calendarButton.setVisibility(View.VISIBLE);
  }

  @Override public void hideViews() {
    fromTextView.setVisibility(View.INVISIBLE);
    toTextView.setVisibility(View.INVISIBLE);
    searchButton.setVisibility(View.INVISIBLE);
    calendarButton.setVisibility(View.INVISIBLE);
  }

  @Override public void hideProgressBar() {
    progressBar.setVisibility(View.INVISIBLE);
  }

  @Override public void showErrorMessage(String errorMessage) {
    Crouton.makeText(getActivity(), errorMessage, Style.ALERT).show();
  }

  @Override public void setAdapterForToView(@NotNull ArrayAdapter<DestinationPoint> adapter) {
    toTextView.setAdapter(adapter);
  }

  @Override public void setAdapterForFromView(@NotNull ArrayAdapter<DestinationPoint> adapter) {
    fromTextView.setAdapter(adapter);
  }

  @Override public void enableSearchButton(boolean isEnable) {
    searchButton.setEnabled(isEnable);
  }

  // TODO: Set current date - implement in next coding round
  @Override public void showCalendarView() {
    final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
        new MyOnDateSetListener(), 2015, 3, 10);
    datePickerDialog.show(getFragmentManager(), "calendar");
  }

  @Override public void setDate(String date) {
    calendarButton.setText(date);
  }

  @Override public Object getModules() {
    return new TravelModule(this);
  }

  @OnClick(R.id.travel_search_button) public void onSearchButtonClick() {
    presenter.searchButtonClicked();
  }

  @OnClick(R.id.travel_calendar_button) public void onCalendarButtonClick() {
    presenter.calendarButtonClicked();
  }

  private class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener {
    @Override public void onDateSet(DatePickerDialog datePickerDialog, int i, int i2, int i3) {
      presenter.onDateSelected(String.valueOf(i + "." + i2  + "." + i3));
    }
  }

  private class FromOnItemClickListener implements AdapterView.OnItemClickListener {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      presenter.setToFieldFilledOut(true);

    }
  }

  private class ToOnItemClickListener implements AdapterView.OnItemClickListener {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      presenter.setSearchButtonSecondFlag(true);
    }
  }

  private class FromTextWatcher implements TextWatcher {
    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
      presenter.getDestinationPoints(s.toString());
      presenter.setWhichTextViewToUpdate(TravelAutocompleteView.FROM);
      presenter.setToFieldFilledOut(false);
    }

    @Override public void afterTextChanged(Editable s) {

    }
  }

  private class ToTextWatcher implements TextWatcher {
    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
      presenter.getDestinationPoints(s.toString());
      presenter.setWhichTextViewToUpdate(TravelAutocompleteView.TO);
      presenter.setSearchButtonSecondFlag(false);
    }

    @Override public void afterTextChanged(Editable s) {

    }
  }
}
