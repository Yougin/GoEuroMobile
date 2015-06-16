package mobile.goeuro.ebeletskiy.goeuromobiletest.data.api;

import java.util.List;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface WebService {

  /**
   * The endpoint to fetch a list of destination points objects.
   *
   * @param city - you would like to get destination points for (from?)
   * @return If no matches are found an empty JSON array is returned.
   */
  @GET("/GoEuroAPI/rest/api/v2/position/suggest/{language}/{city}")
  Observable<List<DestinationPoint>> getDestinationPoints(@Path("language") String language,
      @Path("city") String city);
}
