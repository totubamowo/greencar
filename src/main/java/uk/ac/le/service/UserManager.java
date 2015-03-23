package uk.ac.le.service;

import uk.ac.le.model.User;

import java.util.List;

public interface UserManager {

    User get(Long id) ;

    User get(String username) ;

    List<User> getAll();

    void save(User user);

    void delete(Long id);

    org.springframework.security.core.userdetails.User getLoggedInUser();

}
