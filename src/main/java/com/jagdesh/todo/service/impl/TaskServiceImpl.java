package com.jagdesh.todo.service.impl;

import com.jagdesh.todo.entity.Task;
import com.jagdesh.todo.exception.TaskNotFoundException;
import com.jagdesh.todo.repository.TaskRepository;
import com.jagdesh.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> searchTasks(String wordkey) {
        return taskRepository.findAllByDescriptionContaining(wordkey);
    }

    @Override
    public Optional<Task> findByID(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Transactional
    @Override
    public void completeTask(Long id) {

        Optional<Task> task = this.findByID(id);

        task.ifPresent(entity -> taskRepository.completeTask(entity.getId()));
        task.orElseThrow(TaskNotFoundException::new);

    }

    @Transactional
    @Override
    public void uncompleteTask(Long id) {

        Optional<Task> task = this.findByID(id);

        task.ifPresent(entity -> taskRepository.uncompleteTask(entity.getId()));
        task.orElseThrow(TaskNotFoundException::new);

    }

}
