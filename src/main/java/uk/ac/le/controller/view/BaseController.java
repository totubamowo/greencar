package uk.ac.le.controller.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.le.config.RouteConfig;
import uk.ac.le.service.UserManager;

@Component
public abstract class BaseController {

    @Autowired
    private UserManager userManager;

    private static final String SPRING_REDIRECT = "redirect:";

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(RouteConfig.ERROR_VIEW);

        modelAndView.addObject("exception", ex);

        org.springframework.security.core.userdetails.User loggedInUser = userManager.getLoggedInUser();
        if (loggedInUser != null) {
            modelAndView.addObject("loggedInUserName", loggedInUser.getUsername());
        }

        return modelAndView;
    }

    protected String redirectTo(String viewToRedirect) {
        return SPRING_REDIRECT + viewToRedirect;
    }

}
