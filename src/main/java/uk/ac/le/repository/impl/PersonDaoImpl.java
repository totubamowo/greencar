package uk.ac.le.repository.impl;

import uk.ac.le.model.Person;
import uk.ac.le.repository.PersonDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "personDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PersonDaoImpl extends BaseDao<Person> implements PersonDao {

}
