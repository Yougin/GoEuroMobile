package mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language;

import javax.inject.Inject;

public class LanguageProvider {

  private SystemLocaleWrapper defaultLocale;

  public static final String EN = "en";
  public static final String DE = "de";

  @Inject public LanguageProvider(SystemLocaleWrapper defaultLocale) {
    this.defaultLocale = defaultLocale;
  }

  public String getUserLanguage() {
    String language = defaultLocale.getLanguage();

    if (language.startsWith(EN)) {
      return EN;
    } else if (language.startsWith(DE)) {
      return DE;
    } else {
      return EN;
    }
  }
}
