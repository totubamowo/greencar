package uk.ac.le.repository;

import uk.ac.le.model.Journey;
import uk.ac.le.utils.TestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class JourneyDaoTest extends BaseDaoTest {

	@Autowired
	private JourneyDao journeyDao;

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
	public void listJourneys() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyDao.save(journey);

		List<Journey> journeys = journeyDao.getAll();

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
