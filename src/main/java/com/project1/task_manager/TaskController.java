package com.project1.task_manager;
import com.project1.task_manager.dt0.TaskRequest;
import com.project1.task_manager.dt0.TaskResponse;
import com.project1.task_manager.model.Status;
import com.project1.task_manager.model.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        TaskResponse response = taskService.toResponse(
                taskService.createTask(request.getTitle(), request.getDescription(), request.getStatus(), request.getPriority())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@RequestParam(required = false)Status status) {
        List<TaskResponse> responses;
        if (status != null) {
            responses = taskService.getTaskByStatus(status)
                    .stream().map(taskService::toResponse).toList();
        } else {
            responses = taskService.getAllTasks()
                    .stream().map(taskService::toResponse).toList();
        }
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.toResponse(taskService.getTaskById(id)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@Valid @PathVariable Long id, @RequestBody TaskRequest request) {
        TaskResponse response = taskService.toResponse(
                taskService.updateTask(id, request.getTitle(), request.getDescription(), request.getStatus(), request.getPriority())

        );
                return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok(taskService.toResponse(taskService.getTaskById(id)));
    }
}
