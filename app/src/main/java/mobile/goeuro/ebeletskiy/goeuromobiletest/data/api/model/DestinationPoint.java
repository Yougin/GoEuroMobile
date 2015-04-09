package mobile.goeuro.ebeletskiy.goeuromobiletest.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DestinationPoint {
  @SerializedName("_id") @Expose private long id;
  @Expose private Object key;
  @Expose private String name;
  @Expose private String fullName;
  @SerializedName("iata_airport_code") @Expose private String iataAirportCode;
  @Expose private String type;
  @Expose private String country;
  @SerializedName("geo_position") @Expose private GeoPosition geoPosition;
  @Expose private Object locationId;
  @Expose private boolean inEurope;
  @Expose private String countryCode;
  @Expose private boolean coreCountry;
  @Expose private Object distance;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Object getKey() {
    return key;
  }

  public void setKey(Object key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getIataAirportCode() {
    return iataAirportCode;
  }

  public void setIataAirportCode(String iataAirportCode) {
    this.iataAirportCode = iataAirportCode;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public GeoPosition getGeoPosition() {
    return geoPosition;
  }

  public void setGeoPosition(GeoPosition geoPosition) {
    this.geoPosition = geoPosition;
  }

  public Object getLocationId() {
    return locationId;
  }

  public void setLocationId(Object locationId) {
    this.locationId = locationId;
  }

  public boolean isInEurope() {
    return inEurope;
  }

  public void setInEurope(boolean inEurope) {
    this.inEurope = inEurope;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public boolean isCoreCountry() {
    return coreCountry;
  }

  public void setCoreCountry(boolean coreCountry) {
    this.coreCountry = coreCountry;
  }

  public Object getDistance() {
    return distance;
  }

  public void setDistance(Object distance) {
    this.distance = distance;
  }

  public static class GeoPosition {
    @Expose private double latitude;
    @Expose private double longitude;

    public double getLatitude() {
      return latitude;
    }

    public void setLatitude(double latitude) {
      this.latitude = latitude;
    }

    public double getLongitude() {
      return longitude;
    }

    public void setLongitude(double longitude) {
      this.longitude = longitude;
    }

    @Override public String toString() {
      return "GeoPosition{" +
          "latitude=" + latitude +
          ", longitude=" + longitude +
          '}';
    }
  }

  @Override public String toString() {
    return "DestinationPoint{" +
        "id=" + id +
        ", key=" + key +
        ", name='" + name + '\'' +
        ", fullName='" + fullName + '\'' +
        ", iataAirportCode='" + iataAirportCode + '\'' +
        ", type='" + type + '\'' +
        ", country='" + country + '\'' +
        ", geoPosition=" + geoPosition +
        ", locationId=" + locationId +
        ", inEurope=" + inEurope +
        ", countryCode='" + countryCode + '\'' +
        ", coreCountry=" + coreCountry +
        ", distance=" + distance +
        '}';
  }
}
