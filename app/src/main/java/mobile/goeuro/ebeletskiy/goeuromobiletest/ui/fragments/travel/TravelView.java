package mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel;

public interface TravelView {
  void showViews();

  void hideViews();

  void showProgressBar();

  void hideProgressBar();

  void setErrorMessage(String message);

  void showErrorMessage();

  void hideErrorMessage();
}
