package mobile.goeuro.ebeletskiy.goeuromobiletest.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import mobile.goeuro.ebeletskiy.goeuromobiletest.R;
import mobile.goeuro.ebeletskiy.goeuromobiletest.ui.fragments.travel.TravelFragment;
import timber.log.Timber;

public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.rootview, new TravelFragment())
          .commit();
    }
  }
}
