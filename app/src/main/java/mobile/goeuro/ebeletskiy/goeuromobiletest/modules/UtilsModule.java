package mobile.goeuro.ebeletskiy.goeuromobiletest.modules;

import com.google.android.gms.common.api.GoogleApiClient;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.App;
import mobile.goeuro.ebeletskiy.goeuromobiletest.events.LocationProviderEvents;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.ILocale;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.LanguageProvider;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.SystemLocaleWrapper;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.location.ILocationProvider;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.location.UserLocationProvider;

@Module(library = true, complete = false)
public class UtilsModule {

  @Provides @Singleton ILocale provideLocale() {
    return new SystemLocaleWrapper();
  }

  @Provides @Singleton LanguageProvider provideLanguageProvider(ILocale locale) {
    return new LanguageProvider(locale);
  }

  @Provides @Singleton GoogleApiClient.Builder provideGoogleApiClientBuilder(App app) {
    return new GoogleApiClient.Builder(app);
  }

  @Provides @Singleton ILocationProvider provideUserLocationProvider(GoogleApiClient.Builder client,
      EventBus bus) {
    return new UserLocationProvider(client, bus,
        new LocationProviderEvents.LastKnownLocationSuccessEvent());
  }
}
