package com.jagdesh.todo.service;

import com.jagdesh.todo.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task save(Task task);

    Task update(Task task);

    List<Task> findAll();

    List<Task> searchTasks(String wordkey);

    Optional<Task> findByID(Long id);

    void delete(Task task);

    void completeTask(Long id);

    void uncompleteTask(Long id);

}
