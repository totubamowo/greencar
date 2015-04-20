package uk.ac.le.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.le.utils.Point;
import uk.ac.le.model.Route;
import uk.ac.le.service.RouteManager;

import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteAPIController extends BaseAPIController {

    @Autowired
    private RouteManager routeManager;

    @ResponseBody
    @RequestMapping(value = "/shortest-path",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Route> getRoutes(@RequestParam(value = "sourceLon", required = true) Double sourceLon,
                                 @RequestParam(value = "sourceLat", required = true) Double sourceLat,
                                 @RequestParam(value = "sinkLon", required = true) Double sinkLon,
                                 @RequestParam(value = "sinkLat", required = true) Double sinkLat) {
        return routeManager.getRoutes(new Point(sourceLon, sourceLat), new Point(sinkLon, sinkLat));
    }
}
