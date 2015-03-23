package uk.ac.le.repository;

import uk.ac.le.model.Person;

import java.util.List;

public interface PersonDao {
	
	Person get(Long id) ;
	
	List<Person> getAll();
	
    void save(Person person);

    void delete(Long id);

}
