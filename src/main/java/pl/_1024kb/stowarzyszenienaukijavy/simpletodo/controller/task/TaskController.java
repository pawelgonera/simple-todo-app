package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.FilterOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.OrderOption;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.TaskServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
public class TaskController
{
    @Autowired
    private TaskServiceImpl taskServiceImpl;
    private List<Task> taskList = Collections.synchronizedList(new LinkedList<>());

    /*@Autowired
    public TaskController(TaskServiceImpl taskService)
    {
        this.taskService = taskService;
        this.taskList = Collections.synchronizedList(new LinkedList<>());
    }*/

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> allTask(@SessionAttribute String username)
    {
        List<Task> allTask = taskServiceImpl.getAllTasksByUsername(username);
        return ResponseEntity.ok(allTask);
    }

    @GetMapping("/addTask")
    public String redirectToAddTask(Model model)
    {
        model.addAttribute("task", new Task());
        return "add_task";
    }

    @PostMapping("/addTask")
    public String addTask(@Valid @ModelAttribute Task task, BindingResult result, Model model, @SessionAttribute String username)
    {
        String message = "Task was successfully created ;)";

        if(result.hasErrors())
        {
            return "add_task";
        }else
        {
            try
            {
                taskServiceImpl.createTask(task, username);

            } catch (Exception e)
            {
                e.printStackTrace();
                message = e.getMessage();
            }
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam Long id)
    {
        try
        {
            taskServiceImpl.deleteTaskById(id);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return "redirect:/tasks";
    }

    @GetMapping("/editTask")
    public String redirectToEditTask(Model model, @RequestParam String id, @RequestParam String title,
                           @RequestParam String date, @RequestParam String description, @RequestParam String taskDone, @SessionAttribute String username)
    {
        Task task = taskServiceImpl.getTaskById(username, Long.valueOf(id));
        task.setTitle(title);
        task.setDate(LocalDate.parse(date));
        task.setDescription(description);
        task.setTaskDone(Boolean.valueOf(taskDone));

        model.addAttribute("task", task);

        return "edit_task";
    }

    @PostMapping("/editTask")
    public String editTask(Model model, @Valid @ModelAttribute Task task, BindingResult result, @SessionAttribute String username)
    {
        String message = "Task was successfully updated ;)";

        if(result.hasErrors())
        {
            return "edit_task";
        }else
        {
            try
            {
                taskServiceImpl.changeTask(task, username);

            } catch (Exception e) {
                e.printStackTrace();
                message = e.getMessage();
            }
        }

        model.addAttribute("message", message);

        return "message";
    }

   @GetMapping("/tasks")
    public String redirectToTasks(Model model, @SessionAttribute String username)
    {
        taskList = taskServiceImpl.getAllTasksByUsername(username);

        model.addAttribute("tasksList", taskList);

        return "task_list";
    }

    @PostMapping("/tasks")
    public String orderTask(HttpServletRequest request, Model model, @SessionAttribute String username)
    {
        filterTask(username, request);
        sortTaskList(username, request);

        model.addAttribute("tasksList", taskList);

        return "task_list";
    }

    private void filterTask(String username, HttpServletRequest request)
    {
        String filterList = request.getParameter("filter");
        if(filterList != null && !filterList.isEmpty())
        {
            FilterOption filterOption = FilterOption.valueOf(filterList.toUpperCase());
            switch (filterOption)
            {
                case DATE:
                    LocalDate date = LocalDate.parse(request.getParameter("dateFilter"));
                    taskList = taskServiceImpl.getAllTasksByDate(username, date);
                    break;
                case TRUE:
                    taskList = taskServiceImpl.getAllTasksByTaskDone(username, true);
                    break;
                case FALSE:
                    taskList = taskServiceImpl.getAllTasksByTaskDone(username, false);
                    break;
                default:
                    taskList = taskServiceImpl.getAllTasksByUsername(username);
                    break;
            }

        }
    }

    private void sortTaskList(String username, HttpServletRequest request) {
        String sortList = request.getParameter("sort");
        if (sortList != null && !sortList.isEmpty()) {
            OrderOption orderOption = OrderOption.valueOf(sortList.toUpperCase());
            switch (orderOption) {
                case TITLE:
                    taskList = taskServiceImpl.getAllTasksOrderedByTitle(username);
                    break;
                case DATE:
                    taskList = taskServiceImpl.getAllTasksOrderedByDate(username);
                    break;
                case STATUS:
                    taskList = taskServiceImpl.getAllTasksOrderedByStatus(username);
                    break;
                default:
                    taskList = taskServiceImpl.getAllTasksByUsername(username);
                    break;
            }
        }
    }
}
