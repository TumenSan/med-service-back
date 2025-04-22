package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.model.Model;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ModelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    @Transactional
    public TaskDto.Response createTask(TaskDto.Create taskDto) {
        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Model model = modelRepository.findById(taskDto.getModelId())
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        Task task = Task.builder()
                .user(user)
                .model(model)
                .taskType(taskDto.getTaskType())
                .parameters(taskDto.getParameters())
                .status(taskDto.getStatus())
                .build();

        Task savedTask = taskRepository.save(task);
        return toResponseDto(savedTask);
    }

    @Transactional(readOnly = true)
    public TaskDto.Response getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return toResponseDto(task);
    }

    @Transactional(readOnly = true)  // Исправлено: readOnly - это параметр, а не аннотация
    public List<TaskDto.Response> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDto.Response toResponseDto(Task task) {
        return TaskDto.Response.builder()
                .id(task.getId())
                .userId(task.getUser().getId())
                .modelId(task.getModel().getId())
                .taskType(task.getTaskType())
                .parameters(task.getParameters())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .build();
    }
}
