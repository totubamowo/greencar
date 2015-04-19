package uk.ac.le.controller.view;

import org.springframework.web.bind.annotation.*;
import uk.ac.le.config.RouteConfig;
import uk.ac.le.model.Journey;
import uk.ac.le.service.JourneyManager;
import uk.ac.le.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

        org.springframework.security.core.userdetails.User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser != null) {
            modelAndView.addObject("loggedInUserName", loggedInUser.getUsername());
        }

        return modelAndView;
    }

    @RequestMapping(value = RouteConfig.JOURNEY_VIEW, method = RequestMethod.GET)
    public ModelAndView viewJourney(@RequestParam(value = "id", required = true) Long id) {
        LOGGER.debug("Received request to view journey id : " + id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(RouteConfig.JOURNEY_VIEW_VIEW);

        Journey journey = journeyManager.get(id);

        modelAndView.addObject("journey", journey == null ? new Journey() : journey);

        org.springframework.security.core.userdetails.User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser != null) {
            modelAndView.addObject("loggedInUserName", loggedInUser.getUsername());
        }

        return modelAndView;
    }

    @RequestMapping(value = RouteConfig.JOURNEY_EDIT, method = RequestMethod.POST)
    public String saveJourney(@ModelAttribute Journey journey) {
        LOGGER.debug("Received postback on journey " + journey);

        journey.setUser(userManager.get(userManager.getLoggedInUser().getUsername()));

        journeyManager.save(journey);

        return redirectTo(RouteConfig.JOURNEY_LIST);
    }

    @RequestMapping(value = RouteConfig.JOURNEY_PEER, method = RequestMethod.GET)
    public ModelAndView peerJourney(@RequestParam(value = "id", required = true) Long id) {
        LOGGER.debug("Received request to view journey id : " + id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(RouteConfig.JOURNEY_PEER_VIEW);

        Journey journey = journeyManager.get(id);
        modelAndView.addObject("journey", journey == null ? new Journey() : journey);

        List<Journey> journeys = journeyManager.getAll(journey);

        modelAndView.addObject("journeys", journeys);


        org.springframework.security.core.userdetails.User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser != null) {
            modelAndView.addObject("loggedInUserName", loggedInUser.getUsername());
        }

        return modelAndView;
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
            modelAndView.addObject("loggedInUserName", loggedInUser.getUsername());
        }

        return modelAndView;
    }

    @RequestMapping(value = RouteConfig.JOURNEY_LIST + "/{user}", method = RequestMethod.GET)
    public ModelAndView myJourneyList(@PathVariable String user) {
        LOGGER.debug("Received request to list journeys for user : " + user);

        ModelAndView modelAndView = new ModelAndView();

        List<Journey> journeys = null;

        org.springframework.security.core.userdetails.User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser != null) {
            journeys = journeyManager.getAll(userManager.get(loggedInUser.getUsername()));
            modelAndView.addObject("loggedInUserName", loggedInUser.getUsername());

            modelAndView.addObject("journeys", journeys);

        }

        modelAndView.setViewName(RouteConfig.JOURNEY_LIST_VIEW);

        LOGGER.debug("Journey Listing count = " + journeys.size());

        return modelAndView;
    }

    @RequestMapping(value = RouteConfig.JOURNEY_DELETE, method = RequestMethod.DELETE)
    public String deleteJourney(@ModelAttribute Journey journey) {
        LOGGER.debug("Deleting journey " + journey);

        journeyManager.delete(journey.getId());

        return redirectTo(RouteConfig.JOURNEY_LIST);
    }

}
