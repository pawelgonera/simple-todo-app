package edu.demo.simpletodo.api;

import edu.demo.simpletodo.exception.UserNotFoundException;
import edu.demo.simpletodo.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void create(User user) throws SQLException;

    User read(String username) throws UserNotFoundException;

    void update(User user) throws SQLException;

    void delete(Long user_id) throws SQLException;

    List<User> getAllUsers();
}
