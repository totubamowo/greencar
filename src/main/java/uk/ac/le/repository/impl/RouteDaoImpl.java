package uk.ac.le.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uk.ac.le.model.Point;
import uk.ac.le.model.Route;
import uk.ac.le.repository.RouteDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "routeDao")
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Route> getRoutes(Point source, Point sink) {
        List<Route> routes = this.jdbcTemplate.query(
                "SELECT name, heading, cost FROM gcar_shortestpath('roads'," + source.getLon() + "," + source.getLat() + "," + sink.getLon() + "," + sink.getLat() + ")",
                new Route.RouteMapper());
        return routes;
    }

    public Object selectRoad(Long gid) {
        String SQL = "SELECT name FROM public.roads WHERE gid = ?";
        Object road = this.jdbcTemplate.queryForObject(SQL, new Object[]{gid},
                new RowMapper<Object>() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Object object = rs.getString("name");
                        return object;
                    }
                });
        return road;
    }

}
