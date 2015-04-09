package mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model;

import android.location.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class DistanceComparatorTest {

  public static final String THEODOR_HEUSS_PLATZ = "theodor_heuss_platz";

  public static final String KAISERDAMM = "kaiserdamm";
  public static final String BISMARKSTR = "bismarkstr";

  private DistanceComparator distanceComparator;

  @Before
  public void setUp() throws Exception {
    Location userLocation = new Location(THEODOR_HEUSS_PLATZ);
    userLocation.setLatitude(52.511026);
    userLocation.setLongitude(13.273033);

    distanceComparator = new DistanceComparator(userLocation);
  }

  @Test public void should_sort_locations_close_to_user_location() throws Exception {
    List<Location> locations = getListOfSubwayStations();
    assertThat(locations.get(0).getProvider(), equalTo(BISMARKSTR));
    assertThat(locations.get(1).getProvider(), equalTo(KAISERDAMM));

    Collections.sort(locations, distanceComparator);

    assertThat(locations.get(0).getProvider(), equalTo(KAISERDAMM));
    assertThat(locations.get(1).getProvider(), equalTo(BISMARKSTR));
  }

  private List<Location> getListOfSubwayStations() {

    ArrayList<Location> locations = new ArrayList<>();

    Location kaiserdammStation = new Location(KAISERDAMM);
    kaiserdammStation.setLatitude(52.510138);
    kaiserdammStation.setLongitude(13.282046);

    Location bismarkstrStation = new Location(BISMARKSTR);
    bismarkstrStation.setLatitude(52.512645);
    bismarkstrStation.setLongitude(13.305735);

    locations.add(bismarkstrStation);
    locations.add(kaiserdammStation);

    return locations;
  }
}