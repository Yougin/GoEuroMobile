package mobile.goeuro.ebeletskiy.goeuromobiletest.modules;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.comparators.DistanceComparatorFactory;

@Module(library = true)
public class DataModule {

  @Provides @Singleton DistanceComparatorFactory provideDistanceComparatorFactory() {
    return new DistanceComparatorFactory();
  }

}
