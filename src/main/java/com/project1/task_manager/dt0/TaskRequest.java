package com.project1.task_manager.dt0;


import com.project1.task_manager.model.Priority;
import com.project1.task_manager.model.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TaskRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String description;

    private Status status;
    private Priority priority;
}
