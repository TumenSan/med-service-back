package com.example.demo.controller;

import com.example.demo.dto.ModelDto;
import com.example.demo.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @PostMapping
    public ResponseEntity<ModelDto.Response> createTask(@RequestBody ModelDto.Create modelDto) {
        return new ResponseEntity<>(modelService.createResult(modelDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ModelDto.Response>> getAllTasks() {
        return ResponseEntity.ok(modelService.getAllResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDto.Response> getModelById(@PathVariable Long id) {
        return ResponseEntity.ok(modelService.getModelById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResult(@PathVariable Long id) {
        modelService.deleteModel(id);
    }
}
