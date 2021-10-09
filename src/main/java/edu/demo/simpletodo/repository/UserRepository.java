package edu.demo.simpletodo.repository;

import edu.demo.simpletodo.exception.NotFoundDesiredDataRuntimeException;
import edu.demo.simpletodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findUserByUsername(String username) throws NotFoundDesiredDataRuntimeException;
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
}
