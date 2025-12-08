package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@Table(name = "todo")
public class TodoModel {
    @Id
    @GeneratedValue
    private UUID id; 
    @Column(nullable = false)
    private String taskName;
    @Column(nullable = true)
    private Boolean completed = false;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateTime;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
