package uk.ac.le.utils;


import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;
import org.apache.http.HttpStatus;

import java.util.List;

/**
 * Simple class for converting postcode to geolocation vice-versa based on
 * <a href="http://postcodes.io/">Postcode & Geolocation API for the UK</a>.
 *
 * @author Taiwo Otubamowo
 */
public class GeoToPostcode {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    /**
     * Represents the base result for the Postcodes API.
     */
    public static class PostcodesApiBaseResult {
        @Key
        public int status;

        @Key
        public String error;
    }

    /**
     * Represents the fields in the JSON data for the Postcodes API.
     */
    public static class Result {
        @Key
        public String incode;

        @Key
        public String outcode;

        @Key
        public String postcode;

        @Key
        public Double longitude;

        @Key
        public Double latitude;
    }

    /**
     * Represents a typical result for postcode to geolocation.
     */
    public static class PostcodeToGeoResult extends PostcodesApiBaseResult {
        @Key
        public Result result;
    }

    /**
     * Represents a typical result for geolocation to postcode.
     */
    public static class GeoToPostcodeResult extends PostcodesApiBaseResult {
        @Key
        public List<Result> result;
    }

    /**
     * URL for Postcodes API.
     */
    public static class PostcodesApiUrl extends GenericUrl {

        public static String API_BASE_URL = "http://api.postcodes.io/postcodes";

        public PostcodesApiUrl(String encodedUrl) {
            super(encodedUrl);
        }
    }

    public static Point postcodeToGeo(String postcode) throws Exception {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        PostcodesApiUrl url = new PostcodesApiUrl(PostcodesApiUrl.API_BASE_URL + "/" + postcode);

        HttpRequest request = requestFactory.buildGetRequest(url);
        PostcodeToGeoResult apiResult = request.execute().parseAs(PostcodeToGeoResult.class);

        if (apiResult.status == HttpStatus.SC_OK) {
            return new Point(apiResult.result.longitude, apiResult.result.latitude);
        }
        if (apiResult.status == HttpStatus.SC_NOT_FOUND) {
            throw new org.apache.http.client.HttpResponseException(HttpStatus.SC_NOT_FOUND, apiResult.error);
        } else
            throw new org.apache.http.client.HttpResponseException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Contact developer!");
    }

    public static Result geoToPostcode(Point point) throws Exception {
        HttpRequestFactory requestFactory =
                HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.setParser(new JsonObjectParser(JSON_FACTORY));
                    }
                });
        PostcodesApiUrl url = new PostcodesApiUrl(PostcodesApiUrl.API_BASE_URL + "?lon=" + point.getLon() + "&lat=" + point.getLat());
        HttpRequest request = requestFactory.buildGetRequest(url);
        GeoToPostcodeResult apiResult = request.execute().parseAs(GeoToPostcodeResult.class);

        if (apiResult.status == HttpStatus.SC_OK) {
            return apiResult.result.get(0);
        }
        if (apiResult.status == HttpStatus.SC_NOT_FOUND) {
            throw new org.apache.http.client.HttpResponseException(HttpStatus.SC_NOT_FOUND, apiResult.error);
        } else
            throw new org.apache.http.client.HttpResponseException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Contact developer!");
    }
}