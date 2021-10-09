package edu.demo.simpletodo.repository;

import edu.demo.simpletodo.model.Task;
import edu.demo.simpletodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{
    @Transactional
    void deleteAllByUser(User user);
    List<Task> findAllByUser(User user);
    Task getTaskById(Long id);
}
