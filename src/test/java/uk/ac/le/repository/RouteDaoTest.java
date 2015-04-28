package uk.ac.le.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.le.model.Route;
import uk.ac.le.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class RouteDaoTest extends BaseDaoTest {

    @Autowired
    private RouteDao routeDao;

    @Test
    public void getRoutes() {

        List<Route> routes = routeDao.getRoutes(TestUtils.source, TestUtils.sink);

        assertEquals(10, routes.size());
    }

    @Test
    public void getCost() {

        double cost = routeDao.getCost(TestUtils.source, TestUtils.sink);

        assertEquals(0.022655311988823555, cost, 0.0000001);
    }

    @Test
    public void selectRoad() {
        assertEquals("Ling Road", routeDao.selectRoad(TestUtils.gid));
    }

}
