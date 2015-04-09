package mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.error;

import de.greenrobot.event.EventBus;
import javax.inject.Inject;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class CustomErrorHandler implements ErrorHandler {

  private final EventBus bus;

  @Inject public CustomErrorHandler(EventBus bus) {
    this.bus = bus;
  }

  @Override public Throwable handleError(RetrofitError cause) {
    if (cause.getKind() == RetrofitError.Kind.NETWORK) {
      return new NetworkConnectionException();
    }

    Response retrofitResponse = cause.getResponse();

    if (retrofitResponse == null) {
      Timber.e("RetrofitError doesn't have a response");
      return cause;
    }

    int status = retrofitResponse.getStatus();
    Timber.i("Status code = " + status + " received");

    switch (status) {
      case 500:
        Timber.i("Handling status code 500");
        return new ServerErrorException();
    }

    Timber.i("Status code is not one of those which we handle, so returning a default one");
    return cause;
  }
}
