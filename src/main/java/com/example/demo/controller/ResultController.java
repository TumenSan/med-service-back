package com.example.demo.controller;

import com.example.demo.dto.ResultDto;
import com.example.demo.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<ResultDto.Response> createTask(@RequestBody ResultDto.Create resultDto) {
        return new ResponseEntity<>(resultService.createResult(resultDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResultDto.Response>> getAllTasks() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDto.Response> getResultById(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.getResultById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
    }
}
