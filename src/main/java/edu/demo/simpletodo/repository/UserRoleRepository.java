package edu.demo.simpletodo.repository;


import edu.demo.simpletodo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>
{
    UserRole findByRole(String role);

    @Override
    void delete(UserRole entity);
}
