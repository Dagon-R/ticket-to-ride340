package Models;

public class CityLoc {
    float lon;
    float lat;
    String state;
    String city;

    public CityLoc(float lon, float lat, String state, String city) {
        this.lon = lon;
        this.lat = lat;
        this.state = state;
        this.city = city;
    }

    public float getLon() {
        return (lon+180)/360;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return (lat+90)/180;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityLoc{" +
                "lon=" + lon +
                ", lat=" + lat +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
