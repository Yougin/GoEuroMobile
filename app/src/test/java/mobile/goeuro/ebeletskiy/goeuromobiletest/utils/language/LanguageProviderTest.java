package mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class LanguageProviderTest {

  public static final String EN = "en";
  public static final String DE = "de";
  private LanguageProvider languageProvider;

  @Mock SystemLocaleWrapper systemLocaleWrapper;

  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    languageProvider = new LanguageProvider(systemLocaleWrapper);
  }

  @Test public void when_en_return_en() throws Exception {
    when(systemLocaleWrapper.getLanguage()).thenReturn(EN);

    String userLanguage = languageProvider.getUserLanguage();

    assertThat(userLanguage, is(equalTo(EN)));
  }

  @Test public void when_de_return_de() {
    when(systemLocaleWrapper.getLanguage()).thenReturn(DE);

    String userLanguage = languageProvider.getUserLanguage();

    assertThat(userLanguage, is(equalTo(DE)));
  }

  @Test public void when_en_something_return_en() {
    when(systemLocaleWrapper.getLanguage()).thenReturn("en_something");

    String userLanguage = languageProvider.getUserLanguage();

    assertThat(userLanguage, is(equalTo(EN)));
  }

  @Test public void when_de_something_return_de() {
    when(systemLocaleWrapper.getLanguage()).thenReturn("de_something");

    String userLanguage = languageProvider.getUserLanguage();

    assertThat(userLanguage, is(equalTo(DE)));
  }

  @Test public void when_not_en_not_de_return_en() {
    when(systemLocaleWrapper.getLanguage()).thenReturn("al");

    String userLanguage = languageProvider.getUserLanguage();

    assertThat(userLanguage, is(equalTo(EN)));
  }
}
