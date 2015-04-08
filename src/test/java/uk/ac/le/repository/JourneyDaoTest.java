package uk.ac.le.repository;

import uk.ac.le.model.Journey;
import uk.ac.le.model.User;
import uk.ac.le.utils.TestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class JourneyDaoTest extends BaseDaoTest {

    @Autowired
    private JourneyDao journeyDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void saveAJourney() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyDao.save(journey);

        assertNotNull(journey.getId());
    }

    @Test
    public void getAJourney() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyDao.save(journey);

        Journey savedJourney = journeyDao.get(journey.getId());

        assertNotNull(savedJourney);
    }

    @Test
    public void getAJourneyUser() {
        User user = TestUtils.getAUserObject();
        userDao.save(user);

        Journey journey = TestUtils.getAJourneyObject();
        journey.setUser(user);

        journeyDao.save(journey);

        User savedUser = userDao.get(journey.getUser().getId());

        assertEquals(journey.getUser(), savedUser);
    }

    @Test
    public void listJourneys() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyDao.save(journey);

        List<Journey> journeys = journeyDao.getAll();

        assertEquals(1, journeys.size());
    }

    @Test
    public void listUserJourneys() {
        User user = TestUtils.getAUserObject();
        userDao.save(user);

        Journey journey = TestUtils.getAJourneyObject();
        journey.setUser(user);

        journeyDao.save(journey);

        List<Journey> journeys = journeyDao.getAll(user);

        assertEquals(1, journeys.size());
    }

    @Test
    public void listJourneysForDriver() {
        User user = TestUtils.getAUserObject();
        userDao.save(user);

        Journey journey = TestUtils.getAJourneyObject();
        journey.setDriver(true);
        journey.setUser(user);
        journeyDao.save(journey);

        User user_ = TestUtils.getAnotherUserObject();
        userDao.save(user_);

        Journey journey_ = TestUtils.getAJourneyObject();
        journey_.setDriver(false);
        journey_.setUser(user_);
        journeyDao.save(journey_);

        List<Journey> journeys = journeyDao.getAll(journey);

        assertEquals(1, journeys.size());
    }

    @Test
    public void listJourneysForRider() {
        User user = TestUtils.getAUserObject();
        userDao.save(user);

        Journey journey = TestUtils.getAJourneyObject();
        journey.setDriver(false);
        journey.setUser(user);
        journeyDao.save(journey);

        User user_ = TestUtils.getAnotherUserObject();
        userDao.save(user_);

        Journey journey_ = TestUtils.getAJourneyObject();
        journey_.setDriver(true);
        journey_.setUser(user_);
        journeyDao.save(journey_);

        List<Journey> journeys = journeyDao.getAll(journey);

        assertEquals(1, journeys.size());
    }


    @Test
    public void deleteAJourney() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyDao.save(journey);

        Long journeyId = journey.getId();

        assertNotNull(journeyId);

        journeyDao.delete(journeyId);

        Journey deletedJourney = journeyDao.get(journeyId);

        assertNull(deletedJourney);
    }

}
