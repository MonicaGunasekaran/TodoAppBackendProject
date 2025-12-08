package com.example.demo.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PendingTasksOnDue {
	  String getTaskName();
	  LocalDate getDueDateTime(); 
	  LocalDateTime getCreatedAt();
}
