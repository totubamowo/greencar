package uk.ac.le.repository;

import uk.ac.le.model.Point;
import uk.ac.le.model.Route;

import java.util.List;

public interface RouteDao {

    List<Route> getRoutes(Point source, Point sink);

    double getCost(Point source, Point sink);

    Object selectRoad(Long gid);

}
