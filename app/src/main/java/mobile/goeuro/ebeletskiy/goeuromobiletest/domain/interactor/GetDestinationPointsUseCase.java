package mobile.goeuro.ebeletskiy.goeuromobiletest.domain.interactor;

import javax.inject.Inject;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.WebService;
import mobile.goeuro.ebeletskiy.goeuromobiletest.utils.language.LanguageProvider;
import rx.Observable;

public class GetDestinationPointsUseCase extends UseCase {

  private final WebService webService;
  private final LanguageProvider languageProvider;

  @Inject
  public GetDestinationPointsUseCase(WebService webService, LanguageProvider languageProvider) {
    this.webService = webService;
    this.languageProvider = languageProvider;
  }

  @Override protected Observable buildUseCaseObservable() {
    return webService.getDestinationPoints(languageProvider.getUserLanguage(), "Berlin");
  }
}
