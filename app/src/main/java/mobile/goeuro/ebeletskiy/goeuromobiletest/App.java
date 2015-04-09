package mobile.goeuro.ebeletskiy.goeuromobiletest;

import android.app.Application;
import dagger.ObjectGraph;
import java.util.Arrays;
import java.util.List;
import mobile.goeuro.ebeletskiy.goeuromobiletest.modules.ApplicationModule;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.ReleaseReportingTree;
import timber.log.Timber;

public class App extends Application {

  private ObjectGraph objectGraph;

  @Override public void onCreate() {
    super.onCreate();

    objectGraph = ObjectGraph.create(getModules().toArray());
    objectGraph.inject(this);

    setupTimberPlant();
  }

  public ObjectGraph createScopedGraph(Object... modules) {
    return objectGraph.plus(modules);
  }

  public ObjectGraph getObjectGraph() {
    return objectGraph;
  }

  public List<Object> getModules() {
    return Arrays.<Object>asList(new ApplicationModule(this));
  }

  private void setupTimberPlant() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Timber.plant(new ReleaseReportingTree());
    }
  }
}
