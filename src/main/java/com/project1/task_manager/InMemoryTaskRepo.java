package com.project1.task_manager;


import com.project1.task_manager.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTaskRepo implements TaskRepository{

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Task save(Task task) {
        if(task.getId() == null){
            task.setId(idGenerator.getAndIncrement());
        }
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return List.of(tasks.values().toArray(new Task[0]));
    }

    @Override
    public Optional<Task> findById(long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return tasks.containsKey(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return tasks.remove(id)!= null;
    }
}
