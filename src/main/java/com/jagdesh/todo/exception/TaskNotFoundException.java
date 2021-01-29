package com.jagdesh.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TaskNotFoundException extends EntityNotFoundException {

    public TaskNotFoundException() {
        super("Task not Found");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

}
