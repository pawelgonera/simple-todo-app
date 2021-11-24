package edu.demo.simpletodo.service;

import edu.demo.simpletodo.api.TaskService;
import edu.demo.simpletodo.exception.NotFoundDesiredDataRuntimeException;
import edu.demo.simpletodo.model.Task;
import edu.demo.simpletodo.model.User;
import edu.demo.simpletodo.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService
{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserServiceImpl userService;
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    /*@Autowired
    public TaskServiceImpl(UserServiceImpl userService, TaskRepository taskRepo)
    {
        this.userService = userService;
        this.taskRepo = taskRepo;
    }*/

    @Override
    public void createTask(Task task, String username) throws Exception {
        String messageInfo = "Successfully created new task";

        if (userService.getUserByUsername(username).isPresent())
        {
            User user = userService.getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
            task.setUser(user);
            task.setTaskDone(false);
        }

        try {
            taskRepository.save(task);
            logger.debug(messageInfo + " - " + task.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            String messageError = "The task could not be created";
            logger.error(messageError + " - " + task.getTitle());
            throw new Exception(messageError);
        }
    }

    @Override
    public List<Task> getAllTasksByUsername(String username) {
        Optional<User> user = Optional.empty();

        if (userService.getUserByUsername(username).isPresent())
        {
            user = userService.getUserByUsername(username);
        }

        try {
            return taskRepository.findAllByUser(user.orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException));

        } catch (Exception e) {
            e.printStackTrace();
            String messageError = "All tasks could not be downloaded";
            logger.error(messageError);
        }

        return Collections.emptyList();
    }

    @Override
    public void changeTask(Task task, String username) throws Exception {
        String messageInfo = "Task was successfully updated";

        if (userService.getUserByUsername(username).isPresent())
        {
            User user = userService.getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
            task.setUser(user);
        }

        try
        {
            Task taskToUpdate = new Task();
            taskToUpdate.setId(task.getId());
            taskToUpdate.setTitle(task.getTitle());
            taskToUpdate.setDate(task.getDate());
            taskToUpdate.setDescription(task.getDescription());
            taskToUpdate.setTaskDone(task.getTaskDone());
            taskRepository.save(task);
            logger.debug(messageInfo + " - " + task.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            String messageError = "Task could not be updated";
            logger.error(messageError + " - " + task.getTitle());
            throw new Exception(messageError);
        }
    }

    @Override
    public void deleteTaskById(Long taskId) throws Exception {
        String messageInfo = "Task was successfully deleted";
        try {
            taskRepository.deleteById(taskId);
            logger.debug(messageInfo + " - id: " + taskId);

        } catch (Exception e) {
            e.printStackTrace();
            String messageError = "Cannot delete the task";
            logger.error(messageError + " - id: " + taskId);
            throw new Exception(messageError);
        }
    }

    @Override
    public void deleteAllTasks(String username) {
        User user = userService.getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);

        try {
            taskRepository.deleteAllByUser(user);
            logger.debug("Usunięto wszystkie zadania użytkownika {}", username);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Nie udało się usunąć wszystkich zadań użytkownika {}", username);
        }
    }

    public Task getTaskById(String username, Long taskId)
    {
        return getAllTasksByUsername(username).stream()
                    .filter(task -> task.getId().equals(taskId))
                    .findFirst()
                    .orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
    }

    @Override
    public List<Task> getAllTasksByDate(String username, LocalDate dateFilter) {
        return getAllTasksByUsername(username).stream()
                .filter(task -> task.getDate().equals(dateFilter))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksByTaskDone(String username, boolean doneFilter) {
        return getAllTasksByUsername(username).stream()
                .filter(task -> task.getTaskDone() == doneFilter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByTitle(String username) {
        return getAllTasksByUsername(username).stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByDate(String username) {
        return getAllTasksByUsername(username).stream()
                .sorted(Comparator.comparing(Task::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByStatus(String username) {
        return getAllTasksByUsername(username).stream()
                .sorted(Comparator.comparing(Task::getTaskDone).reversed())
                .collect(Collectors.toList());
    }
}
