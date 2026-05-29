package com.project1.task_manager.dt0;

import com.project1.task_manager.model.Priority;
import com.project1.task_manager.model.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    private String id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime createdAt;

}
