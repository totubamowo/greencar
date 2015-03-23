package uk.ac.le.service;

import uk.ac.le.model.Journey;
import uk.ac.le.utils.TestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class JourneyManagerTest extends BaseManagerTest {

    @Autowired
    private JourneyManager journeyManager;

    @Test
    public void saveAJourney() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyManager.save(journey);

        assertNotNull(journey.getId());
    }

    @Test
    public void getAJourney() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyManager.save(journey);

        Journey savedJourney = journeyManager.get(journey.getId());

        assertNotNull(savedJourney);
    }

    @Test
    public void listJourneys() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyManager.save(journey);

        List<Journey> journeys = journeyManager.getAll();

        assertEquals(1, journeys.size());
    }

    @Test
    public void deleteAJourney() {
        Journey journey = TestUtils.getAJourneyObject();

        journeyManager.save(journey);

        Long journeyId = journey.getId();

        assertNotNull(journeyId);

        journeyManager.delete(journeyId);

        Journey deletedJourney = journeyManager.get(journeyId);

        assertNull(deletedJourney);
    }

}
