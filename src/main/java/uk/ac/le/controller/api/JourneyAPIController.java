package uk.ac.le.controller.api;

import uk.ac.le.config.RouteConfig;
import uk.ac.le.model.Journey;
import uk.ac.le.service.JourneyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * todo find a way to test API endpoints
 */
@Controller
@RequestMapping(value = RouteConfig.BASE_API_URL  + RouteConfig.JOURNEY_API_BASE_URL)
public class JourneyAPIController extends BaseAPIController {

    @Autowired
    private JourneyManager journeyManager;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public List<Journey> getJourney() throws Exception {
        return journeyManager.getAll();
    }

    @ResponseBody
    @RequestMapping(value = RouteConfig.JOURNEY_API_SINGLE_JOURNEY_URL,
            method = RequestMethod.GET,
            produces = "application/json")
    public Journey getJourney(@PathVariable Long id) throws Exception {
        return journeyManager.get(id);
    }

    @ResponseBody
    @RequestMapping(value = RouteConfig.JOURNEY_API_SINGLE_JOURNEY_URL,
            method = RequestMethod.PUT,
            produces = "application/json")
    public Journey updateJourney(@RequestBody Journey journeyWithNewValues) throws Exception {
        journeyManager.save(journeyWithNewValues);

        return journeyWithNewValues;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,
            produces = "application/json")
    public Journey saveJourney(@RequestBody Journey journey, HttpServletResponse response) throws Exception {
        journeyManager.save(journey);
        return journey;
    }

    @ResponseBody
    @RequestMapping(value = RouteConfig.JOURNEY_API_SINGLE_JOURNEY_URL,
            method = RequestMethod.DELETE,
            produces = "application/json")
    public Journey deleteJourney(@PathVariable Long id, HttpServletResponse response) throws Exception {
        journeyManager.delete(id);

        // todo to trigger success() function of backbone upon deletion i needed to return something..
        return new Journey();
    }

}
