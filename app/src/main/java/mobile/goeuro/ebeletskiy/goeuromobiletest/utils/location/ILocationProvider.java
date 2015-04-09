package mobile.goeuro.ebeletskiy.goeuromobiletest.utils.location;

import android.location.Location;
import org.jetbrains.annotations.Nullable;

public interface ILocationProvider {

  void connect();

  void disconnect();

  @Nullable Location getLastKnownUserLocation();

}
