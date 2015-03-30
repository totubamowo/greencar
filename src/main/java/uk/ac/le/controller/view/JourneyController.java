package uk.ac.le.controller.view;

import uk.ac.le.config.RouteConfig;
import uk.ac.le.model.Journey;
import uk.ac.le.service.JourneyManager;
import uk.ac.le.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(RouteConfig.JOURNEY_BASE_MAPPING)
public class JourneyController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JourneyController.class);

    @Autowired
    private JourneyManager journeyManager;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = RouteConfig.JOURNEY_EDIT, method = RequestMethod.GET)
    public ModelAndView editJourney(@RequestParam(value = "id", required = false) Long id) {
        LOGGER.debug("Received request to edit journey id : " + id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(RouteConfig.JOURNEY_EDIT_VIEW);

        Journey journey = journeyManager.get(id);

        modelAndView.addObject("journey", journey == null ? new Journey() : journey);

        return modelAndView;
    }

    @RequestMapping(value = RouteConfig.JOURNEY_EDIT, method = RequestMethod.POST)
    public String saveJourney(@ModelAttribute Journey journey) {
        LOGGER.debug("Received postback on journey " + journey);

        journeyManager.save(journey);

        return redirectTo(RouteConfig.JOURNEY_LIST);

    }

    @RequestMapping(value = RouteConfig.JOURNEY_LIST, method = RequestMethod.GET)
    public ModelAndView listJourney() {
        LOGGER.debug("Received request to list journeys");

        ModelAndView modelAndView = new ModelAndView();

        List<Journey> journeys = journeyManager.getAll();

        LOGGER.debug("Journey Listing count = " + journeys.size());

        modelAndView.addObject("journeys", journeys);
        modelAndView.setViewName(RouteConfig.JOURNEY_LIST_VIEW);

        org.springframework.security.core.userdetails.User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser != null) {
            modelAndView.addObject("loggedInUserName", userManager.getLoggedInUser().getUsername());
        }

        userManager.getLoggedInUser();

        return modelAndView;
    }

    @RequestMapping(value = RouteConfig.JOURNEY_DELETE, method = RequestMethod.DELETE)
    public String deleteJourney(@ModelAttribute Journey journey) {
        LOGGER.debug("Deleting journey " + journey);

        journeyManager.delete(journey.getId());

        return redirectTo(RouteConfig.JOURNEY_LIST);
    }

}
