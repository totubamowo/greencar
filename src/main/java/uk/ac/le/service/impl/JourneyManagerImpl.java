package uk.ac.le.service.impl;

import uk.ac.le.model.Journey;
import uk.ac.le.model.User;
import uk.ac.le.repository.JourneyDao;
import uk.ac.le.service.JourneyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "journeyManager")
@Transactional(propagation = Propagation.REQUIRED)
public class JourneyManagerImpl implements JourneyManager {

    @Autowired
    private JourneyDao journeyDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(JourneyManagerImpl.class);

    public Journey get(Long id) {
        return journeyDao.get(id);
    }

    public List<Journey> getAll() {
        return journeyDao.getAll();
    }

    public List<Journey> getAll(User user) {
        return journeyDao.getAll(user);
    }

    public List<Journey> getAll(Journey journey) {
        return journeyDao.getAll(journey);
    }

    public void save(Journey journey) {
        journeyDao.save(journey);
    }

    public void delete(Long id) {
        journeyDao.delete(id);
    }
}
