package mobile.goeuro.ebeletskiy.goeuromobiletest.data.api;

import java.util.List;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.error.NetworkConnectionException;
import mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model.DestinationPoint;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebService {

  /**
   * The endpoint to fetch a list of destination points objects.
   *
   * @param city - you would like to get destination points for (from?)
   * @return If no matches are found an empty JSON array is returned.
   */
  @GET("/GoEuroAPI/rest/api/v2/position/suggest/en/{city}")
  List<DestinationPoint> getDestinationPoints(@Path("city") String city)
      throws NetworkConnectionException;
}
