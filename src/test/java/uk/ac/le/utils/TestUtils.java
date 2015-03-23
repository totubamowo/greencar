package uk.ac.le.utils;

import uk.ac.le.model.Journey;
import uk.ac.le.model.User;

public class TestUtils {

    public static Journey getAJourneyObject() {
        Journey journey = new Journey();
        journey.setFirstName("Ayrton");
        journey.setLastName("Senna");

        return journey;
    }

    public static User getAUserObject() {
        User user = new User();
        user.setUsername("deniz@parallelbrains.com");
        user.setEmail("deniz@parallelbrains.com");
        user.setPassword("birdistheword");
        user.setFirstName("Deniz");
        user.setLastName("Ozger");

        return user;
    }

}
