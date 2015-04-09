package mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language;

import java.util.Locale;

public class SystemLocaleWrapper implements ILocale {

  @Override public String getLanguage() {
    return Locale.getDefault().getLanguage();
  }

}
