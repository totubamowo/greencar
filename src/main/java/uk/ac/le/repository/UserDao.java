package uk.ac.le.repository;

import uk.ac.le.model.User;

import java.util.List;

public interface UserDao {

    User get(Long id) ;

    User get(String username);

    List<User> getAll();

    void save(User user);

    void delete(Long id);

}
