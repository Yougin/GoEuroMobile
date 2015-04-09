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
    List<DestinationPoint> destinationPoints = getDestinationPoints();
    assertThat(destinationPoints.get(0).getName(), equalTo(BISMARKSTR));
    assertThat(destinationPoints.get(1).getName(), equalTo(KAISERDAMM));

    Collections.sort(destinationPoints, distanceComparator);

    assertThat(destinationPoints.get(0).getName(), equalTo(KAISERDAMM));
    assertThat(destinationPoints.get(1).getName(), equalTo(BISMARKSTR));
  }

  private List<DestinationPoint> getDestinationPoints() {

    ArrayList<DestinationPoint> locations = new ArrayList<>();

    DestinationPoint kaiserdammStation = new DestinationPoint();
    DestinationPoint.GeoPosition geoPositionKaiserdamm = new DestinationPoint.GeoPosition();
    geoPositionKaiserdamm.setLatitude(52.510138);
    geoPositionKaiserdamm.setLongitude(13.282046);
    kaiserdammStation.setGeoPosition(geoPositionKaiserdamm);
    kaiserdammStation.setName(KAISERDAMM);

    DestinationPoint bismarkstrStation = new DestinationPoint();
    DestinationPoint.GeoPosition geoPositionBismark = new DestinationPoint.GeoPosition();
    geoPositionBismark.setLatitude(52.512645);
    geoPositionBismark.setLongitude(13.305735);
    bismarkstrStation.setGeoPosition(geoPositionBismark);
    bismarkstrStation.setName(BISMARKSTR);

    locations.add(bismarkstrStation);
    locations.add(kaiserdammStation);

    return locations;
  }
}