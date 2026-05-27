package com.project1.task_manager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime createdDate;
}
