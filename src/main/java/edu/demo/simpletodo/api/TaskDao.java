package edu.demo.simpletodo.api;

import edu.demo.simpletodo.model.Task;
import edu.demo.simpletodo.model.User;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {
    void create(Task task) throws SQLException;

    List<Task> getAllByUserId(User user) throws SQLException;

    void update(Task task) throws SQLException;

    void delete(Long taskId) throws SQLException;

    void deleteAllTasks(User user) throws SQLException;
}
