package mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DestinationPointTest {

  public static final int EXPECTED_AMOUNT_OF_POINTS = 6;
  private Type type;

  @Before public void setUp() throws Exception {
    gson = new Gson();
    type = new TypeToken<List<DestinationPoint>>() {
    }.getType();
  }

  @Test public void should_parse_proper_json_into_pojo() throws Exception {
    List<DestinationPoint> points = gson.fromJson(properResponse, type);

    assertThat(points, is(notNullValue()));
  }

  @Test public void parsed_response_should_be_of_proper_size() throws Exception {
    List<DestinationPoint> points = gson.fromJson(properResponse, type);

    assertThat(points.size(), is(equalTo(EXPECTED_AMOUNT_OF_POINTS)));
  }

  @Test(expected = JsonSyntaxException.class)
  public void should_throw_an_exception_if_json_is_not_valid() throws Exception {
    gson.fromJson(corruptedJson, type);
  }

  private Gson gson;
  private String properResponse = "[\n"
      + "    {\n"
      + "        \"_id\": 376217,\n"
      + "        \"key\": null,\n"
      + "        \"name\": \"Berlin\",\n"
      + "        \"fullName\": \"Berlin, Germany\",\n"
      + "        \"iata_airport_code\": null,\n"
      + "        \"type\": \"location\",\n"
      + "        \"country\": \"Germany\",\n"
      + "        \"geo_position\": {\n"
      + "            \"latitude\": 52.52437,\n"
      + "            \"longitude\": 13.41053\n"
      + "        },\n"
      + "        \"locationId\": 8384,\n"
      + "        \"inEurope\": true,\n"
      + "        \"countryCode\": \"DE\",\n"
      + "        \"coreCountry\": true,\n"
      + "        \"distance\": null\n"
      + "    },\n"
      + "    {\n"
      + "        \"_id\": 448103,\n"
      + "        \"key\": null,\n"
      + "        \"name\": \"Berlingo\",\n"
      + "        \"fullName\": \"Berlingo, Italy\",\n"
      + "        \"iata_airport_code\": null,\n"
      + "        \"type\": \"location\",\n"
      + "        \"country\": \"Italy\",\n"
      + "        \"geo_position\": {\n"
      + "            \"latitude\": 45.50298,\n"
      + "            \"longitude\": 10.04366\n"
      + "        },\n"
      + "        \"locationId\": 147721,\n"
      + "        \"inEurope\": true,\n"
      + "        \"countryCode\": \"IT\",\n"
      + "        \"coreCountry\": true,\n"
      + "        \"distance\": null\n"
      + "    },\n"
      + "    {\n"
      + "        \"_id\": 425332,\n"
      + "        \"key\": null,\n"
      + "        \"name\": \"Berlingerode\",\n"
      + "        \"fullName\": \"Berlingerode, Germany\",\n"
      + "        \"iata_airport_code\": null,\n"
      + "        \"type\": \"location\",\n"
      + "        \"country\": \"Germany\",\n"
      + "        \"geo_position\": {\n"
      + "            \"latitude\": 51.45775,\n"
      + "            \"longitude\": 10.2384\n"
      + "        },\n"
      + "        \"locationId\": 124675,\n"
      + "        \"inEurope\": true,\n"
      + "        \"countryCode\": \"DE\",\n"
      + "        \"coreCountry\": true,\n"
      + "        \"distance\": null\n"
      + "    },\n"
      + "    {\n"
      + "        \"_id\": 425326,\n"
      + "        \"key\": null,\n"
      + "        \"name\": \"Bernau bei Berlin\",\n"
      + "        \"fullName\": \"Bernau bei Berlin, Germany\",\n"
      + "        \"iata_airport_code\": null,\n"
      + "        \"type\": \"location\",\n"
      + "        \"country\": \"Germany\",\n"
      + "        \"geo_position\": {\n"
      + "            \"latitude\": 52.67982,\n"
      + "            \"longitude\": 13.58708\n"
      + "        },\n"
      + "        \"locationId\": 124669,\n"
      + "        \"inEurope\": true,\n"
      + "        \"countryCode\": \"DE\",\n"
      + "        \"coreCountry\": true,\n"
      + "        \"distance\": null\n"
      + "    },\n"
      + "    {\n"
      + "        \"_id\": 314826,\n"
      + "        \"key\": null,\n"
      + "        \"name\": \"Berlin Tegel\",\n"
      + "        \"fullName\": \"Berlin Tegel (TXL), Germany\",\n"
      + "        \"iata_airport_code\": \"TXL\",\n"
      + "        \"type\": \"airport\",\n"
      + "        \"country\": \"Germany\",\n"
      + "        \"geo_position\": {\n"
      + "            \"latitude\": 52.5548,\n"
      + "            \"longitude\": 13.28903\n"
      + "        },\n"
      + "        \"locationId\": null,\n"
      + "        \"inEurope\": true,\n"
      + "        \"countryCode\": \"DE\",\n"
      + "        \"coreCountry\": true,\n"
      + "        \"distance\": null\n"
      + "    },\n"
      + "    {\n"
      + "        \"_id\": 314827,\n"
      + "        \"key\": null,\n"
      + "        \"name\": \"Berlin Schönefeld\",\n"
      + "        \"fullName\": \"Berlin Schönefeld (SXF), Germany\",\n"
      + "        \"iata_airport_code\": \"SXF\",\n"
      + "        \"type\": \"airport\",\n"
      + "        \"country\": \"Germany\",\n"
      + "        \"geo_position\": {\n"
      + "            \"latitude\": 52.3887261,\n"
      + "            \"longitude\": 13.5180874\n"
      + "        },\n"
      + "        \"locationId\": null,\n"
      + "        \"inEurope\": true,\n"
      + "        \"countryCode\": \"DE\",\n"
      + "        \"coreCountry\": true,\n"
      + "        \"distance\": null\n"
      + "    }\n"
      + "]";

  private String corruptedJson = "asdfasodifjasdf";
}