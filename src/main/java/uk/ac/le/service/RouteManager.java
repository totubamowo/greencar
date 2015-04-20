package uk.ac.le.service;

import uk.ac.le.utils.Point;
import uk.ac.le.model.Route;

import java.util.List;

public interface RouteManager {

    List<Route> getRoutes(Point source, Point sink);

    double getCost(Point source, Point sink);

    Object selectRoad(Long gid);

}
