package com.project1.task_manager.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task not found with id : " + id);
    }
}
