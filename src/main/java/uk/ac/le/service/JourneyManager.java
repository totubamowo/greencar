package uk.ac.le.service;

import uk.ac.le.model.Journey;
import uk.ac.le.model.User;

import java.util.List;

public interface JourneyManager {

    Journey get(Long id) ;

    List<Journey> getAll();

    List<Journey> getAll(User user);

    List<Journey> getAll(Journey journey);

    void save(Journey journey);

    void delete(Long id);

}
