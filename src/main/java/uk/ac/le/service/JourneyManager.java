package uk.ac.le.service;

import uk.ac.le.model.Journey;

import java.util.List;

public interface JourneyManager {

    Journey get(Long id) ;

    List<Journey> getAll();

    void save(Journey journey);

    void delete(Long id);

}
