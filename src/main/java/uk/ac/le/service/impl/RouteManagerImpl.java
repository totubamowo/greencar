package uk.ac.le.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.le.utils.Point;
import uk.ac.le.model.Route;
import uk.ac.le.repository.RouteDao;
import uk.ac.le.service.RouteManager;

import java.util.List;

@Service(value = "routeManager")
@Transactional(propagation = Propagation.REQUIRED)
public class RouteManagerImpl implements RouteManager {

    @Autowired
    private RouteDao routeDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteManagerImpl.class);

    public List<Route> getRoutes(Point source, Point sink) {
        return routeDao.getRoutes(source, sink);
    }

    public double getCost(Point source, Point sink) {
        return routeDao.getCost(source, sink);
    }

    public Object selectRoad(Long gid) {
        return routeDao.selectRoad(gid);
    }
}
