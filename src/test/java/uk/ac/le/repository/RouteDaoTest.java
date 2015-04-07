package uk.ac.le.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.le.model.Journey;
import uk.ac.le.model.Route;
import uk.ac.le.model.User;
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
    public void selectRoad() {
        assertEquals("Ling Road", routeDao.selectRoad(TestUtils.gid));
    }

}
