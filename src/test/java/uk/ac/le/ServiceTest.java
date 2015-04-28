package uk.ac.le;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.le.model.Journey;
import uk.ac.le.model.User;
import uk.ac.le.service.JourneyManager;
import uk.ac.le.service.UserManager;
import uk.ac.le.utils.TestUtils;

import static org.junit.Assert.*;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ServiceTest {

    @Autowired
    private JourneyManager journeyManager;
    @Autowired
    private UserManager userManager;

    @Test
    public void getAJourney() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyManager.save(journey);
        Journey savedJourney = journeyManager.get(journey.getId());

        assertNotNull(savedJourney);
    }

    @Test
    public void getAUser() {
        User user = TestUtils.getAUserObject();

        userManager.save(user);
        User savedUser = userManager.get(user.getId());

        assertNotNull(savedUser);
    }
}
