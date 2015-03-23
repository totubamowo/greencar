package uk.ac.le.utils;

import uk.ac.le.model.Person;
import uk.ac.le.model.User;

public class TestUtils {

    public static Person getAPersonObject() {
        Person person = new Person();
        person.setFirstName("Ayrton");
        person.setLastName("Senna");

        return person;
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
