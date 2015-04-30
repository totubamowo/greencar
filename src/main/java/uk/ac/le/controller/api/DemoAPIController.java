package uk.ac.le.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.ac.le.config.RouteConfig;
import uk.ac.le.model.Journey;
import uk.ac.le.service.JourneyManager;
import uk.ac.le.utils.PeerAllocationDemo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * todo find a way to test API endpoints
 */
@Controller
@RequestMapping(value = RouteConfig.BASE_API_URL + RouteConfig.DEMO_API_BASE_URL)
public class DemoAPIController extends BaseAPIController {

    @Autowired
    private PeerAllocationDemo peerAllocationDemo;

    @ResponseBody
    @RequestMapping(value = "peer/{ref}/{n}", method = RequestMethod.GET,
            produces = "application/json")
    public String demoPeerAllocation(@PathVariable double ref, @PathVariable int n, HttpServletResponse response) throws Exception {

        return peerAllocationDemo.demoAlgorithm(ref, n);
    }
}
