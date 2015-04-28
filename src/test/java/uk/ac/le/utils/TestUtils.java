package uk.ac.le.utils;

import uk.ac.le.model.Journey;
import uk.ac.le.model.User;
import uk.ac.le.utils.Point;

import java.sql.Time;
import java.util.Date;

public class TestUtils {

    public static Journey getAJourneyObject() {
        Journey journey = new Journey();
        journey.setSource("LE55HL");
        journey.setSink("LE17RH");
        journey.setPurpose("leisure");
        journey.setDeparture(new Time(new Date().getTime()));
        journey.setComments("This journey is really cool");


        return journey;
    }

    public static User getAnotherUserObject() {
        User user = new User();
        user.setUsername("test_");
        user.setEmail("test_@email.com");
        user.setPassword("qwerty");
        user.setFirstName("Test_");
        user.setLastName("Spring_");

        return user;
    }

    public static User getAUserObject() {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@email.com");
        user.setPassword("qwerty");
        user.setFirstName("Test");
        user.setLastName("Spring");

        return user;
    }

    public static Point source = new Point(-1.1209113, 52.6280341);
    public static Point sink = new Point(-1.1314161, 52.6218377);

    public static Long gid = 34L;

}
