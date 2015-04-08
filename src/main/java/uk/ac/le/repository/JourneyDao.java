package uk.ac.le.repository;

import uk.ac.le.model.Journey;
import uk.ac.le.model.User;

import java.util.List;

public interface JourneyDao {
	
	Journey get(Long id) ;
	
	List<Journey> getAll();

    List<Journey> getAll(User user);

    List<Journey> getAll(Journey journey);

    void save(Journey journey);

    void delete(Long id);

}
