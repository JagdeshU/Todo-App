package com.jagdesh.todo.resource;

import com.jagdesh.todo.entity.Task;
import com.jagdesh.todo.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/tasks")
@Api(value = "Tasks")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    //  -------------------
    //  Creating a New Task

    @ApiOperation(value = "Create a New task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task created Successfully."),
            @ApiResponse(code = 400, message = "Validation error on Task data")
    })
    @PostMapping
    public ResponseEntity saveTask(@Valid @RequestBody Task taskData){

        Task task = taskService.save(taskData);

        URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    //  -----------------
    //  Updating the Task

    @ApiOperation(value = "Update Task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task updated successfully."),
            @ApiResponse(code = 400, message = "Validation error on Task Data."),
            @ApiResponse(code = 404, message = "Task Not Found.")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable("id") Long taskID,
                                     @Valid @RequestBody Task taskData) {

        Optional<Task> optionalTask = taskService.findByID(taskID);

        if(!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        taskData.setId(taskID);
        taskService.update(taskData);

        return ResponseEntity.noContent().build();

    }

    //  ---------------
    //  List all Tasks

    @ApiOperation(value = "List all Tasks")
    @ApiResponse(code = 200, message = "Return all tasks of project.")
    @GetMapping
    public List<Task> listTasks() {
        return taskService.findAll();
    }

    //  --------------------------
    //  Search Task by Description

    @ApiOperation(value = "Search Tasks by Description")
    @GetMapping("/search/{query}")
    public List<Task> searchTasks(@PathVariable("query") String query) {
        return taskService.searchTasks(query);
    }

    //  ---------------
    //  Find Task by ID

    @ApiOperation(value = "Find Task by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return details of task."),
            @ApiResponse(code = 404, message = "Task Not Found.")
    })
    public ResponseEntity fetchTaskByID(@PathVariable("id") Long taskID) {
        Optional<Task> optionalTask = taskService.findByID(taskID);
        return optionalTask
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  -------------
    //  Delete a Task

    @ApiOperation(value = "Delete a Task by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task Removed Successfully."),
            @ApiResponse(code = 404, message = "Task Not Found.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Long taskID) {
        Optional<Task> optionalTask = taskService.findByID(taskID);
        return  optionalTask
                .map(task -> {
                    taskService.delete(task);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  ---------------
    //  Complete a Task

    @ApiOperation(value = "Complete a Task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task Completed Successfully."),
            @ApiResponse(code = 400, message = "Validation error on Complete the Task."),
            @ApiResponse(code = 404, message = "Task Not Found.")
    })
    @PutMapping("/{id}/complete")
    public ResponseEntity completeTask(@PathVariable("id") Long taskID) {
        taskService.completeTask(taskID);
        return ResponseEntity.noContent().build();
    }

    //  -----------------
    //  Uncomplete a Task

    @ApiOperation(value = "Uncomplete a Task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Task Uncompleted Successfully."),
            @ApiResponse(code = 400, message = "Validation error on Uncomplete the Task."),
            @ApiResponse(code = 404, message = "Task Not Found.")
    })
    @PutMapping("/{id}/uncomplete")
    public ResponseEntity uncompleteTask(@PathVariable("id") Long taskID) {
        taskService.uncompleteTask(taskID);
        return ResponseEntity.noContent().build();
    }

}
