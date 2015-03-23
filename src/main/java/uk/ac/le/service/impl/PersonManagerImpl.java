package uk.ac.le.service.impl;

import uk.ac.le.model.Person;
import uk.ac.le.repository.PersonDao;
import uk.ac.le.service.PersonManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "personManager")
@Transactional(propagation = Propagation.REQUIRED)
public class PersonManagerImpl implements PersonManager {

    @Autowired
    private PersonDao personDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonManagerImpl.class);

    public Person get(Long id) {
        return personDao.get(id);
    }

    public List<Person> getAll() {
        return personDao.getAll();
    }

    public void save(Person person) {
        personDao.save(person);
    }

    public void delete(Long id) {
        personDao.delete(id);
    }
}
