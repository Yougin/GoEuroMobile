package mobile.goeuro.ebeletskiy.goeuromobiletest.modules;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.IOException;
import javax.inject.Singleton;
import mobile.goeuro.ebeletskiy.goeuromobiletest.App;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.WebService;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.error.CustomErrorHandler;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.Client;
import retrofit.client.OkClient;
import timber.log.Timber;

import static java.util.concurrent.TimeUnit.SECONDS;

@Module(library = true, complete = false)
public class ApiModule {
  static final int DISK_CACHE_SIZE = 5 * 1024 * 1024; // 5MB

  public static final String PRODUCTION_API_URL = "http://www.goeuro.com";
  public static final String RETROFIT_LOG_TAG = "Retrofit";

  @Provides @Singleton Endpoint provideEndpoint() {
    return Endpoints.newFixedEndpoint(PRODUCTION_API_URL);
  }

  @Provides @Singleton Client provideOkHttpClient(App app) {
    OkHttpClient client = new OkHttpClient();
    client.setConnectTimeout(10, SECONDS);

    try {
      File cacheDir = new File(app.getCacheDir(), "http");
      Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
      client.setCache(cache);
    } catch (IOException e) {
      Timber.e(e, "Unable to install disk cache.");
    }

    return new OkClient(client);
  }

  @Provides @Singleton RestAdapter provideRestAdapter(Endpoint endpoint, Client client,
      ErrorHandler errorHandler) {
    return new RestAdapter.Builder().setClient(client)
        .setEndpoint(endpoint)
        .setErrorHandler(errorHandler)
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setLog(new AndroidLog(RETROFIT_LOG_TAG))
        .build();
  }

  @Provides @Singleton WebService provideWebService(RestAdapter restAdapter) {
    return restAdapter.create(WebService.class);
  }

  @Provides @Singleton ErrorHandler provideErrorHandler(EventBus bus) {
    return new CustomErrorHandler(bus);
  }
}
