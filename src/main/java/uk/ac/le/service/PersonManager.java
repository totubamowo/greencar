package uk.ac.le.service;

import uk.ac.le.model.Person;

import java.util.List;

public interface PersonManager {

    Person get(Long id) ;

    List<Person> getAll();

    void save(Person person);

    void delete(Long id);

}
