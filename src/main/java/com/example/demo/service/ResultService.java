package com.example.demo.service;

import com.example.demo.dto.ResultDto;
import com.example.demo.model.Result;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.ResultRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public ResultDto.Response createResult(ResultDto.Create resultDto) {
        Task task = taskRepository.findById(resultDto.getTaskId())
                .orElseThrow(() -> new EntityNotFoundException("Result not found" + resultDto.getTaskId()));
        Result result = Result.builder()
                .task(task)
                .data(resultDto.getData())
                .conclusion(resultDto.getConclusion())
                .build();

        Result savedResult = resultRepository.save(result);
        return toResponseDto(savedResult);
    }

    @Transactional(readOnly = true)
    public ResultDto.Response getResultById(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + id));
        return toResponseDto(result);
    }

    @Transactional(readOnly = true)
    public List<ResultDto.Response> getAllResults() {
        return resultRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteResult(Long id) {
        if (!resultRepository.existsById(id)) {
            throw new EntityNotFoundException("Result not found with id: " + id);
        }
        resultRepository.deleteById(id);
    }

    private ResultDto.Response toResponseDto(Result result) {
        return ResultDto.Response.builder()
                .id(result.getId())
                .taskId(result.getTask().getId())
                .data(result.getData())
                .conclusion(result.getConclusion())
                .createdAt(result.getCreatedAt())
                .build();
    }
}
