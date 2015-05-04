package uk.ac.le.config;

/**
 * URL Mappings that Spring is supposed to intercept
 *
 * View (jsp) names that Spring needs to redirect the request
 */
public class RouteConfig {

    /**
     * Homepage
     */
    // Mapping
    public static final String HOMEPAGE = "/"; // GET

    // Views
    public static final String HOMEPAGE_VIEW = "home";


    /**
     * Exceptions
     */
    // Views
    public static final String ERROR_VIEW = "error";

    /**
     * Login
     */
    // Mappings
    public static final String LOGIN = "/login";
    public static final String LOGIN_FAILED = "/login-failed";
    public static final String PROCESS_REGISTER = "/process-register"; // POST

    // Views
    public static final String LOGIN_VIEW = "login";

    /**
     * Journey
     */
    // Base View Mapping
    public static final String JOURNEY_BASE_MAPPING = "/journey";
    public static final String USER_BASE_MAPPING = "/user";

    // Sub View Mappings
    public static final String JOURNEY_EDIT = "edit"; // GET, POST
    public static final String JOURNEY_VIEW = "view"; // GET
    public static final String JOURNEY_LIST = "list"; // GET
    public static final String JOURNEY_DELETE = "delete";
    public static final String JOURNEY_PEER_LIST = "peer/list"; // GET
    public static final String JOURNEY_PEER_SINGLE = "peer/single"; // GET

    public static final String USER_EDIT = "edit"; // GET, POST
    public static final String USER_LIST = "list"; // GET
    public static final String USER_DELETE = "delete";

    // Views
    public static final String JOURNEY_EDIT_VIEW = "journey/edit";
    public static final String JOURNEY_VIEW_VIEW = "journey/view";
    public static final String JOURNEY_LIST_VIEW = "journey/list";
    public static final String JOURNEY_PEER_LIST_VIEW = "journey/peer/list";
    public static final String JOURNEY_PEER_SINGLE_VIEW = "journey/peer/single";

    public static final String USER_EDIT_VIEW = "user/edit";
    public static final String USER_LIST_VIEW = "user/list";

    // API Mappings
    public static final String BASE_API_URL = "/api";

    public static final String JOURNEY_API_BASE_URL = "/journeys"; // GET, POST
    public static final String JOURNEY_API_SINGLE_JOURNEY_URL = "/{id}"; // GET, PUT, DELETE

    public static final String USER_API_BASE_URL = "/users"; // GET, POST
    public static final String USER_API_SINGLE_PERSON_URL = "/{id}"; // GET, PUT, DELETE

    public static final String DEMO_API_BASE_URL = "/demos"; // GET, POST
}
