package uk.ac.le.utils;

import uk.ac.le.model.Journey;
import uk.ac.le.model.Point;
import uk.ac.le.model.User;

public class TestUtils {

    public static Journey getAJourneyObject() {
        Journey journey = new Journey();
        journey.setSource("LE55HL");
        journey.setSink("LE17RH");
        journey.setDriver(true);

        return journey;
    }

    public static User getAUserObject() {
        User user = new User();
        user.setUsername("teerex_");
        user.setEmail("teerex_@email.com");
        user.setPassword("qwerty");
        user.setFirstName("Test");
        user.setLastName("Moreno");

        return user;
    }

    public static Point source = new Point(-1.1209113, 52.6280341);
    public static Point sink = new Point(-1.1314161, 52.6218377);

    public static Long gid = 34L;

}
