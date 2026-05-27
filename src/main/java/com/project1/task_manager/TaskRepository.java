package com.project1.task_manager;


import com.project1.task_manager.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(long id);
    boolean existsById(Long id);
    boolean deleteById(Long id);
}
