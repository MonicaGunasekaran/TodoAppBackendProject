package com.example.demo.projection;
import java.time.LocalDateTime;
import java.time.LocalDate;
public interface ProjectionInterface {
    String getTaskName();
    LocalDate getDueDateTime(); 
    LocalDateTime getCreatedAt();
}
