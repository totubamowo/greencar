package uk.ac.le.utils;

public class Point {

    private double lon;

    private double lat;

    public Point() {
        super();
    }

    public Point(double lon, double lat) {
        this.setLon(lon);
        this.setLat(lat);
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void set(double lon, double lat) {
        this.setLon(lon);
        this.setLat(lat);
    }

    public final boolean equals(Point b) {
        return this.lon == b.lon && this.lat == lat;
    }
}
