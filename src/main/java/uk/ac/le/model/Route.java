package uk.ac.le.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Route {

    String name;

    Double heading;

    Double cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading = heading;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", heading=" + heading +
                ", cost=" + cost +
                '}';
    }

    public static class RouteMapper implements RowMapper<Route> {
        public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
            Route route = new Route();
            route.setName(rs.getString("name"));
            route.setCost(rs.getDouble("cost"));
            route.setHeading(rs.getDouble("heading"));
            return route;
        }
    }
}