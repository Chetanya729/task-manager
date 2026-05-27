package com.project1.task_manager;

import com.project1.task_manager.dt0.TaskResponse;
import com.project1.task_manager.exceptions.TaskNotFoundException;
import com.project1.task_manager.model.Priority;
import com.project1.task_manager.model.Status;
import com.project1.task_manager.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title, String description, Status status, Priority priority) {
        Task task = Task.builder()
                .title(title)
                .description(description)
                .priority(priority!=null?priority:Priority.MEDIUM)
                .status(Status.TODO)
                .createdDate(LocalDateTime.now())
                .build();
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
    }

    public Task updateTask(Long id, String title, String description, Status status, Priority priority) {
        Task task = getTaskById(id);
        if (task != null) task.setTitle(title);
        if (task != null) task.setDescription(description);
        if (task != null) task.setPriority(priority!=null?priority:Priority.MEDIUM);
        if (task!=null) task.setStatus(status);
        if (task != null) task.setCreatedDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        if(!taskRepository.existsById(id)){
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    public List<Task> getTaskByStatus(Status status) {
        return taskRepository.findAll().stream().filter(task -> task.getStatus() == status).toList();
    }

    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .createdAt(task.getCreatedDate())
                .build();
    }
}
