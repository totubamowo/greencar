package uk.ac.le.repository;

import uk.ac.le.model.Journey;

import java.util.List;

public interface JourneyDao {
	
	Journey get(Long id) ;
	
	List<Journey> getAll();
	
    void save(Journey journey);

    void delete(Long id);

}
