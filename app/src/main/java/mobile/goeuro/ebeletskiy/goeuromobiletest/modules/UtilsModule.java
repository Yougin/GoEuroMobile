package mobile.goeuro.ebeletskiy.goeuromobiletest.modules;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.ILocale;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.LanguageProvider;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.SystemLocaleWrapper;

@Module(library = true)
public class UtilsModule {

  @Provides @Singleton ILocale provideLocale() {
    return new SystemLocaleWrapper();
  }

  @Provides @Singleton LanguageProvider provideLanguageProvider(ILocale locale) {
    return new LanguageProvider(locale);
  }

}
