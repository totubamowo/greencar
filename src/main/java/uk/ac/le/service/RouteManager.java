package uk.ac.le.service;

import uk.ac.le.model.Point;
import uk.ac.le.model.Route;

import java.util.List;

public interface RouteManager {

    List<Route> getRoutes(Point source, Point sink);

    Object selectRoad(Long gid);

}
