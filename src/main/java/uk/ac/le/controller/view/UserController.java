package uk.ac.le.controller.view;

import org.springframework.web.bind.annotation.*;
import uk.ac.le.config.RouteConfig;
import uk.ac.le.model.User;
import uk.ac.le.service.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(RouteConfig.USER_BASE_MAPPING)
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = RouteConfig.USER_EDIT, method = RequestMethod.GET)
    public ModelAndView editUser() {
        LOGGER.debug("Received request to edit logged in user");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(RouteConfig.USER_EDIT_VIEW);

        org.springframework.security.core.userdetails.User loggedInUser = userManager.getLoggedInUser();

        User user = null;

        if (loggedInUser != null) {
            modelAndView.addObject("loggedInUserName", userManager.getLoggedInUser().getUsername());
            user = userManager.get(loggedInUser.getUsername());
        }

        modelAndView.addObject("user", user == null ? new User() : user);

        return modelAndView;
    }

    @RequestMapping(value = RouteConfig.USER_EDIT, method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user) {
        LOGGER.debug("Received postback on user " + user);

        userManager.save(user);

        return redirectTo(RouteConfig.USER_EDIT);

    }

    @RequestMapping(value = RouteConfig.USER_LIST, method = RequestMethod.GET)
    public ModelAndView listPeople() {
        LOGGER.debug("Received request to list users");

        ModelAndView modelAndView = new ModelAndView();

        List<User> users = userManager.getAll();

        LOGGER.debug("User Listing count = " + users.size());

        modelAndView.addObject("users", users);
        modelAndView.setViewName(RouteConfig.USER_LIST_VIEW);

        org.springframework.security.core.userdetails.User loggedInUser = userManager.getLoggedInUser();

        if (loggedInUser != null) {
            modelAndView.addObject("loggedInUserName", userManager.getLoggedInUser().getUsername());
        }

        userManager.getLoggedInUser();

        return modelAndView;
    }

    @RequestMapping(value = RouteConfig.USER_DELETE, method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute User user) {
        LOGGER.debug("Deleting user " + user);

        userManager.delete(user.getId());

        return redirectTo(RouteConfig.USER_LIST);
    }

}
