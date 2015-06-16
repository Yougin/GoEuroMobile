package mobile.goeuro.ebeletskiy.goeuromobiletest.modules;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.domain.interactor.GetDestinationPointsUseCase;
import mobile.goeuro.ebeletskiy.goeuromobiletest.domain.interactor.UseCase;

@Module(library = true, complete = false) public class UseCasesModule {

  @Provides @Singleton @Named("getDestinationPoints") UseCase providesGetDestinationPointsUseCase(
      GetDestinationPointsUseCase useCase) {
    return useCase;
  }
}
