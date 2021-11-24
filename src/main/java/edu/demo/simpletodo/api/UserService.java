package edu.demo.simpletodo.api;

import edu.demo.simpletodo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService
{

    void createUser(User user) throws Exception;

    void editUser(User user) throws Exception;

    void removeUser(String username) throws Exception;

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();

    boolean isUsernameAlreadyExist(String username);

    boolean isEmailAlreadyExist(String email);
}
